package ru.darklogic.jericho.gwSocket; /**
 * Created by abalashov on 6/3/14.
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.zeromq.ZMQ;

import java.io.IOException;

@Component
public class GwSock {
    @Value("${zmq.queue.in}")
    String zmqIn;
    @Value("${gwSocket.tcp.bind}")
    String tcpBind;

    public void mainMethod(String[] args){
        ZMQ.Context context = ZMQ.context(1);
        TcpSock sock = new TcpSock();
        ZMQ.Socket zmq = context.socket(ZMQ.REQ);

        try {
            zmq.connect(zmqIn);

            System.out.println("Binding TCP socket on " + tcpBind + "...");
            sock.bind(Integer.parseInt(tcpBind));
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

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        GwSock center = context.getBean(GwSock.class);
        center.mainMethod(args);
    }
}
