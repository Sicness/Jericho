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

    public Ultra(String audioUlr)  {
        url = audioUlr;
    }

    public boolean isPlaying() {
        return (player != null);
    }

    public String getUrl() {
        return url;
    }

    public void play()
    {
        InputStream is = null;
        try {
            is = new BufferedInputStream(new URL(url).openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            player = new Player(is);
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
        try {
            player. play();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }

    public void stop()
    {
       if (player == null)
           return;

        player.close();
        player = null;
    }

    public void toggle()
    {
        if (player != null)
            stop();
        else
            play();
    }

    Player player = null;
    private String url;
}
