///////////////////////////////////////////////////////////
// DeJaved by mDeJava v1.0. Copyright 1999 MoleSoftware. //
//       To download last version of this software:      //
//            http://molesoftware.hypermatr.net          //
//               e-mail:molesoftware@mail.ru             //
///////////////////////////////////////////////////////////
//          JAD JavaDecompiler by Pavel Kuznetsov        //
// www.geocities.com/SiliconValley/Bridge/8617/jad.html  //
///////////////////////////////////////////////////////////

import java.awt.Color;
import java.awt.Graphics;

class Missile extends GameObject
{

    double m = 0;
    double b = 0;
    double x = 0;
    double y = 0;
    int speed = 0;
    int startx = 0;
    int starty = 0;
    int endx = 0;
    int endy = 0;
    Color color = null;
    boolean split = false;
    boolean splitme = false;

    Missile(int i, int j, int k, int l, int i1, Color color1)
    {
        split = false;
        splitme = false;
        startx = i;
        starty = j;
        endx = k;
        endy = l;
        speed = i1;
        color = color1;
        x = i;
        y = j;
        m = (double)(l - j) / (double)(k - i);
        b = (double)j - m * (double)i;
        if(Math.random() > 0.90000000000000002D)
            split = true;
    }

    Missile(int i, int j, int k, int l, int i1)
    {
        this(i, j, k, l, i1, Color.red);
    }

    void erase(Graphics g)
    {
        g.setColor(skyColor);
        g.drawLine(startx, starty, (int)x, (int)y);
    }

    void paint(Graphics g)
    {
        erase(g);
        if(!alive)
            return;
        if(explode)
        {
            alive = false;
            explode = false;
            return;
        }
        y += speed;
        x = (y - b) / m;
        if(y > (double)endy)
            alive = false;
        else
        if(split && y > (double)((endy - starty) / 3) && Math.random() > 0.5D)
        {
            alive = false;
            splitme = true;
        }
        if(alive)
        {
            if(split && y > (double)((endy - starty) / 3))
                g.setColor(Color.magenta);
            else
                g.setColor(color);
            g.drawLine(startx, starty, (int)x, (int)y);
        }
    }

    boolean collision(int i, int j, int k)
    {
        if(!alive || explode)
            return false;
        int l = (int)Math.sqrt(((double)i - x) * ((double)i - x) + ((double)j - y) * ((double)j - y));
        return l <= k;
    }

    boolean collision(int i, int j, int k, int l)
    {
        if(!alive || explode)
            return false;
        return x >= (double)i && x <= (double)(i + k) && y >= (double)j && y <= (double)(j + l);
    }
}
