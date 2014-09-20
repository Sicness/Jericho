package ru.darklogic.jericho.sound;


import java.io.IOException;
import java.net.URL;

import java.io.BufferedInputStream;
import java.io.InputStream;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 * Created by Sicness on 20.09.2014.
 */


public class Ultra {

    public Ultra(String audioUlr) throws IOException {
        playing = false;
        InputStream is = new BufferedInputStream(new URL(audioUlr).openConnection().getInputStream());
        Player player = null;
        try {
            player = new Player(is);
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
        try {
            player.play();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }

    public boolean isPlaying() {
        return playing;
    }

    public String getUrl() {
        return url;
    }

    public void play()
    {
        //player.play();
    }

    public void stop()
    {
       // player.stop();
    }

    private boolean playing;
    private String url;

}
