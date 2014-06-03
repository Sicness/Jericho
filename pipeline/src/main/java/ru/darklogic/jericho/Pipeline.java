package ru.darklogic.jericho;

/**
 * Created by abalashov on 6/2/14.
 */

import org.zeromq.ZMQ;

public class Pipeline {
    public static void main(String[] args){
        ZMQ.Context context = ZMQ.context(1);
        ZMQ.Socket queue = context.socket(ZMQ.PUB);

        queue.bind("tcp://0.0.0.0:5000");

        ZMQ.Socket incomeSock = context.socket(ZMQ.REP);
        incomeSock.bind("tcp://0.0.0.0:6000");

        while (true){
            byte[] data = incomeSock.recv();
            queue.send(data);
            incomeSock.send("OK");
        }
    }
}
