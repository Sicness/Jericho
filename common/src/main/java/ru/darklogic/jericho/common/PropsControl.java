package ru.darklogic.jericho.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by abalashov on 6/4/14.
 */
public class PropsControl {
    static PropsControl propsControl;
    Properties props = new Properties();

    public PropsControl(String fileName) throws IOException{
        InputStream stream = null;
        stream = this.getClass().getResourceAsStream(fileName);
        if (stream == null) throw new IOException("Can't find properties file " + fileName);
        props.load(stream);
    }

    public static PropsControl getInstance(String fileName) {
        if (propsControl == null)
            try {
                propsControl = new PropsControl(fileName);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        return propsControl;
    }

    public String get(String key){
        return props.getProperty(key);
    }
}