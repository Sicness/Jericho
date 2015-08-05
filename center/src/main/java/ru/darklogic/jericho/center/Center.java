package ru.darklogic.jericho.center;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import ru.darklogic.jericho.center.handlers.NightLight;
import ru.darklogic.jericho.common.BindFormatException;
import ru.darklogic.jericho.common.ZmqMonitor;

import java.util.ArrayList;

/**
 * Created by sicness on 05.08.15.
 */


@Component
public class Center {
    @Value("${zmq.queue.pub}")
    String queueBind;
    ZmqMonitor monitor;
    ArrayList<Handler> handlers;

    @Autowired
    NightLight nightLight;


    public void mainMethod(String[] args) {
        monitor = new ZmqMonitor();
        try {
            monitor.connect(queueBind);
        } catch (BindFormatException e) {
            e.printStackTrace();
            System.exit(1);
        }

        while (true) {
            String msg = new String(monitor.recv());
            for (Handler handler : handlers) {
                handler.handle(msg);
            }
        }
    }

    private void addHandlers() {
        handlers = new ArrayList<Handler>();
        handlers.add(nightLight);
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        Center center = context.getBean(Center.class);
        center.mainMethod(args);
    }
}
