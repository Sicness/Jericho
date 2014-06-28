package ru.darklogic.jericho.monitor; /**
 * Created by abalashov on 6/1/14.
 */

import ru.darklogic.jericho.common.PropsControl;

import java.io.IOException;

/** The main class for monitor module */
public class JerichoMonitor {
    static PropsControl props = new PropsControl();
    public static void main(String[] args){
        try {
            props.read("/jericho.properties");
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        ZmqMonitor monitor = new ZmqMonitor();
        try { monitor.connect(props.get("zmq.queue.bind")); }
        catch(BindFormatException e){
            System.out.println("ERROR: wrong zmq monitor bind string.");
            System.exit(1);
        }

        while (true){
            System.out.println(new String(monitor.recv()));
        }
    }
}
