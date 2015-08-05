package ru.darklogic.jericho.common;

import org.zeromq.ZMQ;

/**
 * Created by Sicness on 26.10.2014.
 */
public class Zmq {
    ZMQ.Context context;
    ZMQ.Socket pub = null;
    ZMQ.Socket in = null;

    public Zmq() {
        context = ZMQ.context(1);
    }

    public void setPub(String uri) throws BindFormatException {
        if (! isAddrGood(uri)) {
            throw new BindFormatException();
        }
        this.pub = this.context.socket(ZMQ.SUB);
    }

    public static boolean isAddrGood(String bind){
        return (bind instanceof String) && bind.matches("^tcp://\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}:\\d{1,5}$");
    }
}
