/**
 * Created by abalashov on 6/1/14.
 */
public class JerichoMonitor {
    public static void main(String[] args){
        ZmqMonitor monitor = new ZmqMonitor("tcp://192.168.2.7:5000");

        while (true){
            System.out.println(new String(monitor.recv()));
        }
    }
}
