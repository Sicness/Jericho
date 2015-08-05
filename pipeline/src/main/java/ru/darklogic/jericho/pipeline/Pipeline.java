package ru.darklogic.jericho.pipeline;

/**
 * Created by abalashov on 6/2/14.
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.zeromq.ZMQ;

import java.io.IOException;

@Component
public class Pipeline {
    @Value("${zmq.queue.bind}")
    String queueBind;

    public void mainMethod(){
        ZMQ.Context context = ZMQ.context(1);
        ZMQ.Socket queue = context.socket(ZMQ.PUB);

        System.out.println("Binding queue: " + queueBind);
        queue.bind(queueBind);

        ZMQ.Socket incomeSock = context.socket(ZMQ.REP);
        incomeSock.bind("tcp://0.0.0.0:6000");

        while (true){
            byte[] data = incomeSock.recv();
            queue.send(data);
            incomeSock.send("OK");
        }
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        Pipeline center = context.getBean(Pipeline.class);
        center.mainMethod();
    }
}
