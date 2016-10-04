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

class BombDebris extends Explosion
{

    BombDebris(int i, int j, int k)
    {
        super(i, j, k);
        color = Color.yellow;
        growScale = 0.20000000000000001D;
        shrinkScale = 0.20000000000000001D;
    }

    boolean collision(int i, int j, int k)
    {
        int l = (int)Math.sqrt((i - x) * (i - x) + (j - y) * (j - y));
        k += currentSize() / 2;
        return l <= k;
    }
}
