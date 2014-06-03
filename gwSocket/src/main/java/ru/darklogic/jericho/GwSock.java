package ru.darklogic.jericho; /**
 * Created by abalashov on 6/3/14.
 */

import org.zeromq.ZMQ;

import java.io.IOException;

public class GwSock {
    public static void main(String[] args){
        ZMQ.Context context = ZMQ.context(1);
        TcpSock sock = new TcpSock();
        ZMQ.Socket zmq = context.socket(ZMQ.REQ);

        try {
            zmq.connect("tcp://127.0.0.1:6000");

            sock.bind(10001);
        }
        catch (IOException e){
            System.out.println(e.toString());
            System.exit(1);
        }

        try {
            while (true) {
                String res = sock.recv();
                zmq.send(res);
                zmq.recv();
            }
        }
        catch (IOException e){
            System.out.println(e.toString());
        }


    }
}
