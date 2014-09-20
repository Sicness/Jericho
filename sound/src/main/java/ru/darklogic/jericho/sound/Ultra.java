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

public class Ultra implements Runnable{
    private  UltraTh thread = null;
    public Ultra(String audioUlr)  {
        url = audioUlr;
    }

    public void run() {
        thread = new UltraTh(player);
        thread.start();
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
        run();
    }

    public void toggle()
    {
        if (thread == null)
            play();
        else {
            player.close();
            thread = null;
        }
    }

    private volatile Player player = null;
    private String url;
}
