package ru.darklogic.jericho.sound;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 * Created by Sicness on 28.09.2014.
 */
public class UltraTh extends Thread{
    private Player player = null;
    public UltraTh(Player player) { this.player = player; }

    public void run(){
        try {
            player.play();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }

}
