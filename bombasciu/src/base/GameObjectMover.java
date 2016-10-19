package base;
///////////////////////////////////////////////////////////
// DeJaved by mDeJava v1.0. Copyright 1999 MoleSoftware. //
//       To download last version of this software:      //
//            http://molesoftware.hypermatr.net          //
//               e-mail:molesoftware@mail.ru             //
///////////////////////////////////////////////////////////
//          JAD JavaDecompiler by Pavel Kuznetsov        //
// www.geocities.com/SiliconValley/Bridge/8617/jad.html  //
///////////////////////////////////////////////////////////

import java.awt.Component;
import java.util.Enumeration;
import java.util.Vector;

class GameObjectMover extends Thread
{

    int rate = 0;
    MissileCommando parent = null;

    GameObjectMover(int i, MissileCommando missilecommando)
    {
        rate = i;
        parent = missilecommando;
    }

    public void run()
    {
        do
        {
            parent.repaint();
            try
            {
                Thread.sleep(rate);
            }
            catch(Exception _ex)
            {
                return;
            }
            checkCollisions();
            removeZombies();
        }
        while(true);
    }

    void checkCollisions()
    {
        Enumeration enumeration = parent.shots.elements();
        Enumeration enumeration1 = parent.bases.elements();
        do
        {
            Shot shot = enumeration.hasMoreElements() ? (Shot)enumeration.nextElement() : null;
            Base base = enumeration1.hasMoreElements() ? (Base)enumeration1.nextElement() : null;
            if(shot != null || base != null)
            {
                for(Enumeration enumeration2 = parent.missiles.elements(); enumeration2.hasMoreElements();)
                {
                    Missile missile = (Missile)enumeration2.nextElement();
                    if(shot != null && missile.collision(shot.x, shot.y, shot.currentSize()))
                        parent.destroyMissile(missile);
                    if(base != null && base.alive() && missile.collision(base.x, base.y, base.w, base.h))
                        parent.destroyBase(base, missile);
                }

                for(Enumeration enumeration3 = parent.mrvs.elements(); enumeration3.hasMoreElements();)
                {
                    MRV mrv = (MRV)enumeration3.nextElement();
                    if(shot != null && mrv.collision(shot.x, shot.y, shot.currentSize()))
                        parent.destroyMRV(mrv);
                    if(base != null && base.alive() && mrv.collision(base.x, base.y, base.w, base.h))
                        parent.destroyBase(base, mrv);
                }

                for(Enumeration enumeration4 = parent.bombs.elements(); enumeration4.hasMoreElements();)
                {
                    Bomb bomb = (Bomb)enumeration4.nextElement();
                    if(shot != null && bomb.collision(shot.x, shot.y, shot.currentSize()))
                        parent.destroyBomb(bomb);
                    else
                    if(base != null && base.alive() && bomb.collision(base.x, base.y, base.w, base.h))
                        parent.destroyBomb(bomb);
                    else
                    if(bomb.y > 268)
                        parent.destroyBomb(bomb);
                }

                for(Enumeration enumeration5 = parent.bombDebris.elements(); enumeration5.hasMoreElements();)
                {
                    BombDebris bombdebris = (BombDebris)enumeration5.nextElement();
                    if(base != null && base.alive() && bombdebris.collision(base.x + base.w / 2, base.y + base.h / 2, base.w))
                        parent.destroyBase(base, bombdebris);
                }

            }
            else
            {
                return;
            }
        }
        while(true);
    }

    void removeZombies(Vector vector)
    {
        for(Enumeration enumeration = vector.elements(); enumeration.hasMoreElements();)
        {
            GameObject gameobject = (GameObject)enumeration.nextElement();
            if(!gameobject.alive())
            {
                if(gameobject instanceof Missile)
                {
                    Missile missile = (Missile)gameobject;
                    if(missile.splitme)
                    {
                        for(int i = 0; i < 3; i++)
                            parent.createMissile((int)missile.x, (int)missile.y, missile.speed);

                    }
                }
                vector.removeElement(gameobject);
            }
        }

    }

    void removeZombies()
    {
        removeZombies(parent.missiles);
        removeZombies(parent.mrvs);
        removeZombies(parent.bombs);
        removeZombies(parent.shots);
        removeZombies(parent.explosions);
        removeZombies(parent.bombDebris);
    }
}
