package ru.darklogic.jericho.pipeline;

/**
 * Created by abalashov on 6/2/14.
 */

import org.zeromq.ZMQ;
import ru.darklogic.jericho.common.PropsControl;

import java.io.IOException;

public class Pipeline {
    static PropsControl props = new PropsControl();
    static String queueBind = null;

    public static void main(String[] args){
        String bindAddr = null;
        try {
            props.read("/jericho.properties");
            queueBind = props.get("zmq.queue.bind");
            if (queueBind == null) throw new IOException("Can't find zmq.queue.bind in props");
        }
        catch (IOException e){
            System.out.println("Error at reading properties: " + e);
            System.exit(1);
        }

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
}
