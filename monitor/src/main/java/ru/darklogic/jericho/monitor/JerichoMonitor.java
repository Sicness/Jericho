package ru.darklogic.jericho.monitor; /**
 * Created by abalashov on 6/1/14.
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import ru.darklogic.jericho.common.BindFormatException;
import ru.darklogic.jericho.common.ZmqMonitor;

import java.io.IOException;

@Component
public class JerichoMonitor {
    @Value("${zmq.queue.pub}")
    String zmqPub;

    public void mainMethod(String[] args){
        ZmqMonitor monitor = new ZmqMonitor();
        try { monitor.connect(zmqPub); }
        catch(BindFormatException e){
            System.out.println("ERROR: wrong zmq monitor bind string.");
            System.exit(1);
        }

        while (true){
            System.out.println(new String(monitor.recv()));
        }
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        JerichoMonitor center = context.getBean(JerichoMonitor.class);
        center.mainMethod(args);
    }
}
