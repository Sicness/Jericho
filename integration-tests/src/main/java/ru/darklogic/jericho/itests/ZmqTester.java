package ru.darklogic.jericho.itests;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.zeromq.ZMQ;
import ru.darklogic.jericho.common.BindFormatException;
import ru.darklogic.jericho.common.ZmqMonitor;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Sicness on 05.08.2015.
 */
@Component
public class ZmqTester implements Runnable{
    ZmqMonitor zmqMonitor;
    ZMQ.Socket zmqIn;
    ZMQ.Context context;
    String expect;
    AtomicBoolean stopped;
    AtomicBoolean success;
    @Value("${zmq.queue.in}")
    String zmq;

    @Value("${zmq.queue.pub}")
    String zmqPub;

    @Value("${zmq.wait}")
    String waitForSuccessStr;

    public void init() {
        System.out.println("zmqPub=" + zmqPub);
        zmqMonitor = new ZmqMonitor();
        try {
            zmqMonitor.connect(zmqPub);
            return;
        } catch (BindFormatException e) {
            e.printStackTrace();
        }
        context = ZMQ.context(1);
        zmqIn = context.socket(ZMQ.REQ);
        zmqMonitor.setRcvTimeout(500);
        success = new AtomicBoolean();
        stopped = new AtomicBoolean();

    }

    public boolean zmqTest(String send, String expect) {
        this.expect = expect;
        success.set(false);
        stopped.set(false);

        Thread thread = new Thread(this);
        thread.start();

        zmqSend(send);
        while (!stopped.get()) {
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return success.get();
    }

    private void zmqSend(String send) {
        zmqIn.send(send);
        zmqIn.recv();
    }

    @Override
    public void run() {
        String buf;
        long start = System.currentTimeMillis();
        while (start + Integer.parseInt(waitForSuccessStr) < System.currentTimeMillis()) {
            buf = new String(zmqMonitor.recv());
            if (buf.equals(expect)) {
                success.set(true);
                break;
            }
        }
        stopped.set(true);
    }
}
