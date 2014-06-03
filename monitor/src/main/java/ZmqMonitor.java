/**
 * Created by abalashov on 6/1/14.
 */

import org.zeromq.ZMQ;

/** will be raised when connection string is not like "tcp://<ip>:<port>" */
class BindFormatException extends Exception{};

public class ZmqMonitor {
    private ZMQ.Socket sub;

    /**
     * Connect to remote ZMQ.PUB
     * @param bind tcp://<ip>:<port>
     * @throws BindFormatException
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

    /**
     * Check does bind var meet required format
     * @param bind string in format "tcp://<ip>:<port>"
     * @return does bind var meet required format
     */
    public static boolean isBindGood(String bind){
        return (bind instanceof String) && bind.matches("^tcp://\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}:\\d{1,5}$");
    }

    /**
     * Get the next message from queue
     * @return received messages in type byte[]
     */
    public byte[] recv(){
        return this.sub.recv();
    }
}
