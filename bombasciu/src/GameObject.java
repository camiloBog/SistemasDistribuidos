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

abstract class GameObject
{

    boolean alive = false;
    boolean explode = false;
    Color skyColor = null;

    abstract void erase(Graphics g);

    abstract void paint(Graphics g);

    void explode()
    {
        explode = true;
    }

    boolean alive()
    {
        return alive;
    }

    GameObject()
    {
        alive = true;
        explode = false;
        skyColor = new Color(148, 198, 231);
    }
}
