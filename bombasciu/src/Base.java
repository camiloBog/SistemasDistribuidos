///////////////////////////////////////////////////////////
// DeJaved by mDeJava v1.0. Copyright 1999 MoleSoftware. //
//       To download last version of this software:      //
//            http://molesoftware.hypermatr.net          //
//               e-mail:molesoftware@mail.ru             //
///////////////////////////////////////////////////////////
//          JAD JavaDecompiler by Pavel Kuznetsov        //
// www.geocities.com/SiliconValley/Bridge/8617/jad.html  //
///////////////////////////////////////////////////////////

import java.awt.Graphics;
import java.awt.Image;

class Base extends GameObject
{

    int x = 0;
    int y = 0;
    int w = 0;
    int h = 0;
    Image baseImage = null;
    MissileCommando parent = null;

    Base(int i, int j, int k, int l, Image image, MissileCommando missilecommando)
    {
        x = i;
        y = j;
        w = k;
        h = l;
        baseImage = image;
        parent = missilecommando;
    }

    void erase(Graphics g)
    {
        g.setColor(skyColor);
        g.fillRect(x, y, w, h);
    }

    void paint(Graphics g)
    {
        if(explode)
        {
            alive = false;
            explode = false;
        }
        if(alive)
            g.drawImage(baseImage, x, y, w, h, parent);
    }

    void rebuild()
    {
        alive = true;
        explode = false;
    }
}
