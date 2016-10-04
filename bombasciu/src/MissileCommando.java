///////////////////////////////////////////////////////////
// DeJaved by mDeJava v1.0. Copyright 1999 MoleSoftware. //
//       To download last version of this software:      //
//            http://molesoftware.hypermatr.net          //
//               e-mail:molesoftware@mail.ru             //
///////////////////////////////////////////////////////////
//          JAD JavaDecompiler by Pavel Kuznetsov        //
// www.geocities.com/SiliconValley/Bridge/8617/jad.html  //
///////////////////////////////////////////////////////////

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.util.Enumeration;
import java.util.Vector;

public class MissileCommando extends Applet
    implements Runnable
{

    final String version = "v1.2";
    final int worldWidth = 500;
    final int worldHeight = 300;
    final int scoreHeight = 25;
    final int controlsHeight = 50;
    final int screenWidth = 500;
    final int screenHeight = 375;
    final int baseWidth = 64;
    final int baseHeight = 32;
    final int baseGap = 30;
    final int maxBases = 5;
    final int missilePoints = 75;
    final int mrvPoints = 150;
    final int bombPoints = 0;
    final int extraShotPoints = 10;
    final int extraBasePoints = 50;
    final int rebuildBasePoints = 10000;
    AudioClip missileSound = null;
    AudioClip missileExplosionSound = null;
    AudioClip mrvExplosionSound = null;
    AudioClip baseExplosionSound = null;
    AudioClip bombSound = null;
    boolean playing = false;
    boolean readyToPlay = false;
    boolean clearScreen = false;
    boolean paused = false;
    boolean soundEnabled = false;
    Thread thread = null;
    GameObjectMover mover = null;
    Controls controls = null;
    Vector bases = null;
    Vector missiles = null;
    Vector shots = null;
    Vector mrvs = null;
    Vector explosions = null;
    Vector bombs = null;
    Vector bombDebris = null;
    int score = 0;
    int extraBaseScore = 0;
    int level = 0;
    int shotCount = 0;
    int missileCount = 0;
    int missileSpeed = 0;
    int missileDelay = 0;
    int mrvSpeed = 0;
    int bombSpeed = 0;
    Color skyColor = null;
    Graphics world = null;
    MediaTracker tracker = null;
    Image baseImage = null;
    Image mrvImage = null;
    Image bombImage = null;
    Font font = null;
    FontMetrics fontMetrics = null;
    String screenMessage = null;

    public void init()
    {
        font = new Font("TimesRoman", 1, 20);
        fontMetrics = getFontMetrics(font);
        setFont(font);
        tracker = new MediaTracker(this);
        baseImage = getImage(getDocumentBase(), "images/base.gif");
        tracker.addImage(baseImage, 1);
        mrvImage = getImage(getDocumentBase(), "images/mrv.gif");
        tracker.addImage(mrvImage, 1);
        bombImage = getImage(getDocumentBase(), "images/bomb.gif");
        tracker.addImage(bombImage, 1);
        missileSound = getAudioClip(getDocumentBase(), "sounds/Rocket.au");
        missileExplosionSound = getAudioClip(getDocumentBase(), "sounds/Oomph.au");
        mrvExplosionSound = getAudioClip(getDocumentBase(), "sounds/Oomph.au");
        baseExplosionSound = getAudioClip(getDocumentBase(), "sounds/Explosion-2.au");
        bombSound = getAudioClip(getDocumentBase(), "sounds/ship_alarm.au");
        setBackground(Color.white);
        setLayout(new BorderLayout());
        controls = new Controls(this);
        add("South", controls);
        resize(500, 375);
        thread = new Thread(this);
        thread.start();
    }

    public void start()
    {
        if(thread == null)
        {
            thread = new Thread(this);
            thread.start();
        }
    }

    public void stop()
    {
        if(thread != null && thread.isAlive())
        {
            thread.stop();
            thread = null;
        }
        if(mover != null && mover.isAlive())
        {
            mover.stop();
            mover = null;
        }
    }

    public boolean mouseDown(Event event, int i, int j)
    {
        if(playing)
            createShot(i, j - 25);
        return true;
    }

    void startGame()
    {
        readyToPlay = true;
    }

    void quitGame()
    {
        playing = false;
    }

    void resetScore()
    {
        clearScreen = true;
        score = 0;
        extraBaseScore = 0;
        level = 0;
        message(null);
    }

    void resetGame()
    {
        clearScreen = true;
        missiles = new Vector(100);
        shots = new Vector(100);
        mrvs = new Vector(100);
        explosions = new Vector(100);
        bombs = new Vector(100);
        bombDebris = new Vector(100);
        createBases();
        message(null);
    }

    void suspendGame()
    {
        if(thread != null && playing)
        {
            thread.suspend();
            if(mover != null && mover.isAlive())
                mover.suspend();
            paused = true;
            clearScreen = true;
            repaint();
        }
    }

    void resumeGame()
    {
        if(thread != null && playing)
        {
            thread.resume();
            if(mover != null && mover.isAlive())
                mover.resume();
            paused = false;
            clearScreen = true;
        }
    }

    void enableSound()
    {
        soundEnabled = true;
    }

    void disableSound()
    {
        soundEnabled = false;
    }

    public void run()
    {
        showStatus("Loading images...");
        tracker.checkAll(true);
        try
        {
            tracker.waitForAll();
        }
        catch(Exception _ex)
        {
            return;
        }
        showStatus("");
        resetGame();
        resetScore();
        do
        {
            if(!playing && readyToPlay)
            {
                resetGame();
                resetScore();
                playing = true;
                readyToPlay = false;
            }
            if(playing)
                level++;
            else
            if(level == 0)
                level = 1;
            if(playing)
            {
                missileDelay = 2000 - (level - 1) * 200;
                if(missileDelay < 500)
                    missileDelay = 500;
            }
            else
            {
                missileDelay = 500;
            }
            missileSpeed = 5;
            mrvSpeed = 6;
            bombSpeed = 4;
            missileCount = 5 + (level - 1) * 5;
            if(level < 7)
                shotCount = missileCount * 2;
            else
            if(level < 15)
                shotCount = missileCount + 2 * level;
            else
            if(level < 30)
                shotCount = missileCount;
            else
                shotCount = missileCount - level;
            message("Level " + level);
            try
            {
                Thread.sleep(3000L);
            }
            catch(Exception _ex) { }
            message(null);
            mover = new GameObjectMover(100, this);
            mover.start();
            while(missileCount > 0) 
            {
                int i = missileSpeed;
                if(level > 1 && Math.random() > 0.75D)
                    i += (int)(Math.random() * (double)missileSpeed);
                createMissile(i);
                missileCount--;
                if(mrvs.size() < level)
                {
                    double d = Math.random();
                    if(d > 0.69999999999999996D)
                        createMRV(mrvSpeed);
                }
                if(level > 1 && bombs.size() < 2)
                {
                    double d1 = Math.random();
                    if(d1 > 0.84999999999999998D)
                        createBomb(bombSpeed);
                }
                if(!playing)
                    starWars();
                if(!playing && readyToPlay)
                    break;
                try
                {
                    Thread.sleep(missileDelay);
                }
                catch(Exception _ex) { }
            }

            if(!playing && readyToPlay)
            {
                mover.stop();
            }
            else
            {
                while(missiles.size() > 0 || mrvs.size() > 0 || explosions.size() > 0 || bombs.size() > 0 || bombDebris.size() > 0 || shots.size() > 0) 
                {
                    if(!playing && readyToPlay)
                        break;
                    if(!playing && (missiles.size() > 0 || mrvs.size() > 0 || bombs.size() > 0))
                        starWars();
                    try
                    {
                        Thread.sleep(500L);
                    }
                    catch(Exception _ex) { }
                }

                mover.stop();
                int j = 0;
                j += shotCount * 10;
                j += countAliveBases() * 50;
                incrementScore(j);
                if(playing)
                {
                    message("Bonus: " + j);
                    try
                    {
                        Thread.sleep(2000L);
                    }
                    catch(Exception _ex) { }
                    message(null);
                }
                rebuildBases();
                if(countAliveBases() == 0)
                {
                    playing = false;
                    mover = new GameObjectMover(400, this);
                    mover.start();
                    destroyWorld();
                    while(explosions.size() > 0) 
                        try
                        {
                            Thread.sleep(500L);
                        }
                        catch(Exception _ex) { }

                    mover.stop();
                    resetGame();
                    controls.playButton.setLabel("Start");
                    controls.suspendButton.setLabel("Suspend");
                }
            }
        }
        while(true);
    }

    void starWarsFireAt(Vector vector)
    {
        for(Enumeration enumeration = vector.elements(); enumeration.hasMoreElements();)
        {
            GameObject gameobject = (GameObject)enumeration.nextElement();
            int i;
            int j;
            if(gameobject instanceof Missile)
            {
                Missile missile = (Missile)gameobject;
                i = (int)missile.x;
                j = (int)missile.y;
            }
            else
            if(gameobject instanceof MRV)
            {
                MRV mrv = (MRV)gameobject;
                i = mrv.x;
                j = mrv.y;
            }
            else
            if(gameobject instanceof Bomb)
            {
                Bomb bomb = (Bomb)gameobject;
                i = bomb.x;
                j = bomb.y;
            }
            else
            {
                i = 250;
                j = 150;
            }
            if(j > 60)
            {
                byte byte0;
                if(Math.random() > 0.5D)
                    byte0 = 1;
                else
                    byte0 = -1;
                shots.addElement(new Shot(i + byte0 * (int)(Math.random() * 40D), j + byte0 * (int)(Math.random() * 40D), 60));
            }
        }

    }

    void starWars()
    {
        starWarsFireAt(missiles);
        starWarsFireAt(mrvs);
        starWarsFireAt(bombs);
    }

    void createShot(int i, int j)
    {
        if(shotCount > 0)
        {
            shots.addElement(new Shot(i, j, 60));
            shotCount--;
        }
    }

    void createMissile(int i, int j, int k)
    {
        int l = 30 + (int)(Math.random() * 470D);
        char c = '\u012B';
        if(soundEnabled && missileSound != null && playing)
            missileSound.play();
        missiles.addElement(new Missile(i, j, l, c, k));
    }

    void createMissile(int i)
    {
        int j = (int)(Math.random() * 500D);
        int k = 1;
        createMissile(j, k, i);
    }

    void destroyMissile(Missile missile)
    {
        if(soundEnabled && missileExplosionSound != null && playing)
            missileExplosionSound.play();
        missile.explode();
        createExplosion((int)missile.x, (int)missile.y, 20);
        incrementScore(75);
    }

    void createMRV(int i)
    {
        int j = (int)(Math.random() * 500D);
        int k = 1;
        char c = '\u012B';
        mrvs.addElement(new MRV(j, k, c, i, mrvImage, this));
    }

    void destroyMRV(MRV mrv)
    {
        if(soundEnabled && mrvExplosionSound != null && playing)
            mrvExplosionSound.play();
        mrv.explode();
        createExplosion(mrv.x, mrv.y, 10);
        incrementScore(150);
    }

    void createBomb(int i)
    {
        if(soundEnabled && bombSound != null && playing)
            bombSound.play();
        int j = (int)(Math.random() * 500D);
        int k = 1;
        char c = '\u012B';
        bombs.addElement(new Bomb(j, k, j, c, i, bombImage, this));
    }

    void destroyBomb(Bomb bomb)
    {
        bomb.explode();
        createBombDebris(bomb.x, bomb.y);
    }

    void createBombDebris(int i, int j)
    {
        bombDebris.addElement(new BombDebris(i, j, 200));
    }

    void createBases()
    {
        bases = new Vector(5);
        int i = 0;
        int j = 30;
        for(; i < 5; i++)
        {
            bases.addElement(new Base(j, 268, 64, 32, baseImage, this));
            j += 94;
        }

    }

    int countAliveBases()
    {
        int i = 0;
        for(int j = 0; j < 5; j++)
        {
            Base base = (Base)bases.elementAt(j);
            if(base.alive())
                i++;
        }

        return i;
    }

    void destroyBase(Base base, Missile missile)
    {
        if(soundEnabled && baseExplosionSound != null && playing)
            baseExplosionSound.play();
        missile.explode();
        base.explode();
        createExplosion((int)missile.x, (int)missile.y, 100);
    }

    void destroyBase(Base base, MRV mrv)
    {
        if(soundEnabled && baseExplosionSound != null && playing)
            baseExplosionSound.play();
        mrv.explode();
        base.explode();
        createExplosion(mrv.x, mrv.y, 100);
    }

    void destroyBase(Base base, BombDebris bombdebris)
    {
        if(soundEnabled && baseExplosionSound != null && playing)
            baseExplosionSound.play();
        base.explode();
        createExplosion(base.x + base.w / 2, base.y + base.h / 2, 100);
    }

    void rebuildBases()
    {
        int ai[] = new int[5];
        for(int i = 0; i < ai.length; i++)
            ai[i] = -1;

        for(int j = 0; j < 5; j++)
        {
            int l;
            for(l = (int)(Math.random() * 5D); ai[l] != -1;)
                if(++l == 5)
                    l = 0;

            ai[l] = j;
        }

        for(int k = 0; k < 5; k++)
        {
            Base base = (Base)bases.elementAt(ai[k]);
            if(!base.alive() && extraBaseScore >= 10000)
            {
                extraBaseScore -= 10000;
                base.rebuild();
            }
        }

    }

    void createExplosion(int i, int j, int k)
    {
        explosions.addElement(new Explosion(i, j, k));
    }

    void incrementScore(int i)
    {
        if(playing)
        {
            score += i;
            extraBaseScore += i;
        }
    }

    void destroyWorld()
    {
        for(int i = 0; i < 10; i++)
        {
            int j = (int)(Math.random() * 500D);
            int k = (int)(Math.random() * 300D);
            createExplosion(j, k, 100);
        }

    }

    public void update(Graphics g)
    {
        paint(g);
    }

    void paintGameObjects(Vector vector, Graphics g)
    {
        if(vector == null)
            return;
        GameObject gameobject;
        for(Enumeration enumeration = vector.elements(); enumeration.hasMoreElements(); gameobject.paint(g))
            gameobject = (GameObject)enumeration.nextElement();

    }

    void message(String s)
    {
        if(s != null)
        {
            screenMessage = s;
        }
        else
        {
            screenMessage = null;
            clearScreen = true;
        }
        repaint();
    }

    void paintMessage(String s, Graphics g)
    {
        fontMetrics.getHeight();
        int i = fontMetrics.stringWidth(s);
        g.setColor(Color.black);
        g.drawString(s, 250 - i / 2, 150);
    }

    void paintStatus(Graphics g)
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("Score: ");
        stringbuffer.append(score);
        stringbuffer.append(" Level: ");
        stringbuffer.append(level);
        stringbuffer.append(" Shots: ");
        stringbuffer.append(shotCount);
        stringbuffer.append(" Missiles: ");
        stringbuffer.append(missileCount);
        int i = fontMetrics.getHeight();
        int j = fontMetrics.stringWidth(stringbuffer.toString());
        int k = 250 - j / 2;
        g.setColor(Color.white);
        g.fillRect(0, 0, 500, 25);
        g.setColor(Color.black);
        g.drawString(stringbuffer.toString(), k, i);
        j = fontMetrics.stringWidth("v1.2");
        g.drawString("v1.2", 500 - j, i);
    }

    public void paint(Graphics g)
    {
        if(world == null)
            world = g.create(0, 25, 500, 300);
        if(clearScreen)
        {
            g.setColor(Color.white);
            g.fillRect(0, 0, 500, 300);
            world.setColor(skyColor);
            world.fillRect(0, 0, 500, 300);
            clearScreen = false;
        }
        if(paused)
        {
            paintMessage("PAUSED", world);
            return;
        }
        paintStatus(g);
        paintGameObjects(mrvs, world);
        paintGameObjects(missiles, world);
        paintGameObjects(bombs, world);
        paintGameObjects(bases, world);
        paintGameObjects(shots, world);
        paintGameObjects(explosions, world);
        paintGameObjects(bombDebris, world);
        if(!playing)
        {
            paintMessage("GAME OVER", world);
            return;
        }
        if(screenMessage != null)
            paintMessage(screenMessage, world);
    }

    public MissileCommando()
    {
        playing = false;
        readyToPlay = false;
        clearScreen = false;
        paused = false;
        soundEnabled = true;
        skyColor = new Color(148, 198, 231);
    }
}
