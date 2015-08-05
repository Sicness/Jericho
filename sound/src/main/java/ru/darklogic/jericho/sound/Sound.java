package ru.darklogic.jericho.sound;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import ru.darklogic.jericho.common.BindFormatException;
import ru.darklogic.jericho.common.ZmqMonitor;


/**
 * Created by Sicness on 20.09.2014.
 */
@Component
public class Sound {
    @Value("${zmq.queue.pub}")
    String zmqPub;
    // TODO: move the url to properties
    static Ultra ultra = new Ultra("http://mp3.nashe.ru:80/ultra-128.mp3");

    public void mainMethod() {
        ZmqMonitor monitor = new ZmqMonitor();
        try {
            monitor.connect(zmqPub);
        } catch (BindFormatException e) {
            e.printStackTrace();
            System.exit(1);
        }

        while (true){
            String data = new String(monitor.recv());
            if (data.matches("^msg radio$"))
                ultra.toggle();
        }
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        Sound center = context.getBean(Sound.class);
        center.mainMethod();
    }
}
