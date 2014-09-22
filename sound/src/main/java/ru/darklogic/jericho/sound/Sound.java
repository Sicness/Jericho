package ru.darklogic.jericho.sound;

import java.io.IOException;
import org.zeromq.ZMQ;
import ru.darklogic.jericho.common.PropsControl;
import ru.darklogic.jericho.monitor.*;


/**
 * Created by Sicness on 20.09.2014.
 */
public class Sound {
    static PropsControl props = new PropsControl();
    static String queueBind = null;
    // TODO: move the url to properties
    static Ultra ultra = new Ultra("http://mp3.nashe.ru:80/ultra-128.mp3");

    public static void main(String[] args) {
        try {
            props.read("/jericho.properties");
            queueBind = props.get("zmq.queue.bind");
            if (queueBind == null) throw new IOException("Can't find zmq.queue.bind in props");
        }
        catch (IOException e){
            System.out.println("Error at reading properties: " + e);
            System.exit(1);
        }

        ZmqMonitor monitor = new ZmqMonitor();
        try {
            monitor.connect(queueBind);
        } catch (BindFormatException e) {
            e.printStackTrace();
        }

        while (true){
            String data = new String(monitor.recv());
            if (data.matches("^msg radio$"))
                ultra.toggle();
        }
    }
}
