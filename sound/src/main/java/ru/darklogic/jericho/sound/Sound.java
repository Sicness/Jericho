package ru.darklogic.jericho.sound;

import java.io.IOException;

/**
 * Created by Sicness on 20.09.2014.
 */
public class Sound {
    public static void main(String[] args) {
        Ultra ultra = null;
        try {
            ultra = new Ultra("http://mp3.nashe.ru:80/ultra-128.mp3");
        } catch (IOException e) {
            e.printStackTrace();
        }
        ultra.play();
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
