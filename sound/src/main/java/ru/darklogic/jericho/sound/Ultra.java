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


public class Ultra extends Thread{

    public Ultra(String audioUlr)  {
        url = audioUlr;
    }

    public void run() {
        try {
            player.play();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
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
        start();
    }

    public void toggle()
    {
        if (player == null)
            play();
        else {
            player.close();
            player = null;
        }
    }

    private volatile Player player = null;
    private String url;
}
