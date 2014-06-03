/**
 * Created by abalashov on 6/1/14.
 */

/** The main class for monitor module */
public class JerichoMonitor {
    public static void main(String[] args){
        ZmqMonitor monitor = new ZmqMonitor();
        try { monitor.connect("tcp://192.168.2.7:5000"); }
        catch(BindFormatException e){
            System.out.println("ERROR: wrong zmq monitor bind string.");
            System.exit(1);
        }

        while (true){
            System.out.println(new String(monitor.recv()));
        }
    }
}
