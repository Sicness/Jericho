package ru.darklogic.jericho.gwSocket; /**
 * Created by abalashov on 6/3/14.
 */

import org.zeromq.ZMQ;

import java.io.IOException;
import ru.darklogic.jericho.common.PropsControl;

public class GwSock {
    static PropsControl props = new PropsControl();
    public static void main(String[] args){
        try {
            props.read("/jericho.properties");
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        ZMQ.Context context = ZMQ.context(1);
        TcpSock sock = new TcpSock();
        ZMQ.Socket zmq = context.socket(ZMQ.REQ);

        try {
            zmq.connect(props.get("zmq.queue.in"));

            sock.bind(Integer.parseInt(props.get("gwSocket.tcp.bind")));
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
