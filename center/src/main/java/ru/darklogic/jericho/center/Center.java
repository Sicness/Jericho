package ru.darklogic.jericho.center;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.darklogic.jericho.center.handlers.NightLight;
import ru.darklogic.jericho.common.BindFormatException;
import ru.darklogic.jericho.common.PropsControl;
import ru.darklogic.jericho.common.ZmqMonitor;

import java.io.IOException;

/**
 * Created by sicness on 05.08.15.
 */


@Component
public class Center {
    static PropsControl props = PropsControl.getInstance("/jericho.properties");
    @Value("${zmq.queue.pub}")
    String queueBind;
    ZmqMonitor monitor;

    @Autowired
    NightLight nightLight;

    public void mainMethod(String[] args) {
        System.out.println("zmq.queue.pub=" + queueBind);
        System.exit(0);
        try {
            if (queueBind == null) throw new IOException("Can't find zmq.queue.bind in props");
        } catch (IOException e) {
            System.out.println("Error at reading properties: " + e);
            System.exit(1);
        }

        monitor = new ZmqMonitor();
        try {
            monitor.connect(queueBind);
        } catch (BindFormatException e) {
            e.printStackTrace();
            System.exit(1);
        }

        while (true) {
            nightLight.handle("Peow!");
            String data = new String(monitor.recv());
            // TODO: call handlers
        }
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        Center center = context.getBean(Center.class);
        center.mainMethod(args);
    }
}
