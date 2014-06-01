/**
 * Created by abalashov on 6/1/14.
 */

import org.zeromq.ZMQ;

class BindFormatException extends Exception{};

public class ZmqMonitor {
    private ZMQ.Socket sub;

    /**
     * Constructor for zmq monitor
     * @param bind tcp://<ip>:<port>
     */
    public void connect(String bind) throws BindFormatException{
        if (! isBindGood(bind)){
            throw new BindFormatException();
        }

        ZMQ.Context context = ZMQ.context(1);
        sub = context.socket(ZMQ.SUB);

        sub.connect(bind);
        sub.subscribe(new byte[0]);
    }

    public static boolean isBindGood(String bind){
        return (bind instanceof String) && bind.matches("^tcp://\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}:\\d{1,5}$");
    }

    /** Get the next message */
    public byte[] recv(){
        return this.sub.recv();
    }
}
