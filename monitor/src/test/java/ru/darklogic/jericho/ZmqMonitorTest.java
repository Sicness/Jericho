package ru.darklogic.jericho;
/**
 * Created by abalashov on 6/1/14.
 */

import junit.framework.TestCase;
import ru.darklogic.jericho.common.ZmqMonitor;

public class ZmqMonitorTest extends junit.framework.TestCase {
    public void testTypeIsBindGood() {
        TestCase.assertTrue("Doesn't take correct value", ZmqMonitor.isBindGood("tcp://192.168.2.7:5000"));
        TestCase.assertTrue("Doesn't take correct value", ZmqMonitor.isBindGood("tcp://127.0.0.1:2670"));

        TestCase.assertFalse("Shouldn't take random string", ZmqMonitor.isBindGood("asda"));
        TestCase.assertFalse("Shouldn't take without port", ZmqMonitor.isBindGood("tcp://192.168.2.7"));
    }
/*
   public void testConnect() throws Exception{
       String etalon = "Etalon";
       String bind = new String("tcp://127.0.0.1:21670");

       ZMQ.Context context= ZMQ.context(1);
       ZMQ.Socket queue = context.socket(ZMQ.PUB);
       queue.bind(bind);

       ZmqMonitor monitor = new ZmqMonitor();
       monitor.connect(bind);

       queue.send(etalon);
       String recv = new String(monitor.recv());
       assertTrue("Trying to receive by monitor", (recv.equals(etalon)));
       queue.close();
    }
    */
}