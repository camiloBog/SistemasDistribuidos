///////////////////////////////////////////////////////////
// DeJaved by mDeJava v1.0. Copyright 1999 MoleSoftware. //
//       To download last version of this software:      //
//            http://molesoftware.hypermatr.net          //
//               e-mail:molesoftware@mail.ru             //
///////////////////////////////////////////////////////////
//          JAD JavaDecompiler by Pavel Kuznetsov        //
// www.geocities.com/SiliconValley/Bridge/8617/jad.html  //
///////////////////////////////////////////////////////////

import java.awt.*;

class MRV extends GameObject
{

    final int mrvWidth = 12;
    final int mrvHeight = 6;
    final int jump = 10;
    int x = 0;
    int y = 0;
    int endy = 0;
    int speed = 0;
    Image mrvImage = null;
    MissileCommando parent = null;

    MRV(int i, int j, int k, int l, Image image, MissileCommando missilecommando)
    {
        x = i;
        y = j;
        endy = k;
        speed = l;
        mrvImage = image;
        parent = missilecommando;
    }

    void erase(Graphics g)
    {
        g.setColor(skyColor);
        g.fillOval(x - 6, y - 3, 12, 6);
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
        double d = Math.random();
        if(d < 0.25D)
            x -= 10;
        else
        if(d > 0.75D)
            x += 10;
        y += speed;
        if(y > endy)
            alive = false;
        if(alive)
        {
            g.setColor(Color.orange);
            g.fillOval(x - 6, y - 3, 12, 6);
        }
    }

    boolean collision(int i, int j, int k)
    {
        if(!alive || explode)
            return false;
        int l = (int)Math.sqrt((i - x) * (i - x) + (j - y) * (j - y));
        k += 6;
        return l <= k;
    }

    boolean collision(int i, int j, int k, int l)
    {
        if(!alive || explode)
            return false;
        return x >= i && x <= i + k && y >= j && y <= j + l;
    }
}
