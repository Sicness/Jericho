package ru.darklogic.jericho;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by abalashov on 6/4/14.
 */
public class PropsControl {
    Properties props = new Properties();

    public void read(String finaName) throws IOException {
        InputStream stream = null;
        stream = this.getClass().getResourceAsStream(finaName);
        if (stream == null) throw new IOException("Can't find properties file " + finaName);
        props.load(stream);
    }

    public String get(String key){
        return props.getProperty(key);
    }
}