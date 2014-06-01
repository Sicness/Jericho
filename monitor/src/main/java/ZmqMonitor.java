/**
 * Created by abalashov on 6/1/14.
 */

import org.zeromq.ZMQ;

public class ZmqMonitor {
    private ZMQ.Socket sub;

    /**
     * Constructor for zmq monitor
     * @param bind tcp://<ip>:<port>
     */
    public ZmqMonitor(String bind){
        ZMQ.Context context= ZMQ.context(1);
        sub = context.socket(ZMQ.SUB);

        sub.connect(bind);
        sub.subscribe(new byte[0]);
    }

    /** Get the next message */
    public byte[] recv(){
        return this.sub.recv();
    }
}
