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

import java.awt.*;

class Controls extends Panel
{

    MissileCommando parent = null;
    Button playButton = null;
    Button suspendButton = null;
    Button soundButton = null;

    public Controls(MissileCommando missilecommando)
    {
        parent = missilecommando;
        setLayout(new FlowLayout());
        playButton = new Button("Start");
        suspendButton = new Button("Suspend");
        soundButton = new Button("Sound Off");
        add(playButton);
        add(suspendButton);
        add(soundButton);
    }

    public boolean action(Event event, Object obj)
    {
        if(event.target instanceof Button)
        {
            if("Start".equals(obj))
            {
                if(!parent.playing && !parent.readyToPlay)
                    parent.startGame();
                playButton.setLabel("Quit");
            }
            else
            if("Quit".equals(obj))
            {
                if(parent.playing)
                    parent.quitGame();
                playButton.setLabel("Start");
            }
            else
            if("Suspend".equals(obj))
            {
                if(parent.playing)
                {
                    parent.suspendGame();
                    suspendButton.setLabel("Resume");
                }
            }
            else
            if("Resume".equals(obj))
            {
                if(parent.paused)
                {
                    parent.resumeGame();
                    suspendButton.setLabel("Suspend");
                }
            }
            else
            if("Sound Off".equals(obj))
            {
                parent.disableSound();
                soundButton.setLabel("Sound On");
            }
            else
            if("Sound On".equals(obj))
            {
                parent.enableSound();
                soundButton.setLabel("Sound Off");
            }
            return true;
        }
        else
        {
            return false;
        }
    }
}
