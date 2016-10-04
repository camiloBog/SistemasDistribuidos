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

class Bomb extends GameObject
{

    final int bombWidth = 19;
    final int bombHeight = 26;
    int x = 0;
    int y = 0;
    int endx = 0;
    int endy = 0;
    int speed = 0;
    Image bombImage = null;
    MissileCommando parent = null;

    Bomb(int i, int j, int k, int l, int i1, Image image, MissileCommando missilecommando)
    {
        x = i;
        y = j;
        endx = k;
        endy = l;
        speed = i1;
        bombImage = image;
        parent = missilecommando;
    }

    void erase(Graphics g)
    {
        g.setColor(skyColor);
        g.fillRect(x - 9, y - 9, 19, 26);
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
        if(y > endy)
            alive = false;
        if(alive)
            g.drawImage(bombImage, x - 9, y - 9, 19, 26, parent);
    }

    boolean collision(int i, int j, int k)
    {
        if(!alive || explode)
            return false;
        int l = (int)Math.sqrt((i - x) * (i - x) + (j - y) * (j - y));
        k += 9;
        return l <= k;
    }

    boolean collision(int i, int j, int k, int l)
    {
        if(!alive || explode)
            return false;
        return x >= i && x <= i + k && y >= j && y <= j + l;
    }
}
