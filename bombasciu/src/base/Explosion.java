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

import java.awt.Color;
import java.awt.Graphics;

class Explosion extends GameObject
{

    int x = 0;
    int y = 0;
    int size = 0;
    Color color = null;
    double growScale = 0;
    double shrinkScale = 0;
    boolean growing = false;
    double scale = 0;

    Explosion(int i, int j, int k)
    {
        growScale = 0.10000000000000001D;
        shrinkScale = 0.14999999999999999D;
        growing = true;
        scale = 0.10000000000000001D;
        x = i;
        y = j;
        size = k;
        color = Color.red;
    }

    void erase(Graphics g)
    {
        g.setColor(skyColor);
        int i = (int)((double)size * scale);
        g.fillOval(x - i / 2, y - i / 2, i, i);
    }

    void paint(Graphics g)
    {
        if(!alive)
            return;
        if(growing)
        {
            scale += growScale;
            if(scale >= 1.0D)
                growing = false;
        }
        else
        {
            erase(g);
            scale -= shrinkScale;
            if(scale < 0.050000000000000003D)
                alive = false;
        }
        if(alive)
        {
            g.setColor(color);
            int i = (int)((double)size * scale);
            g.fillOval(x - i / 2, y - i / 2, i, i);
        }
    }

    int currentSize()
    {
        return (int)((double)size * scale) / 2;
    }
}
