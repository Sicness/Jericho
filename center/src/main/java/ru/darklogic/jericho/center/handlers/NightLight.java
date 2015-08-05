package ru.darklogic.jericho.center.handlers;

import org.joda.time.LocalTime;
import org.springframework.stereotype.Component;
import ru.darklogic.jericho.center.Handler;
import org.springframework.beans.factory.annotation.Value;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


/**
 * Created by sicness on 05.08.15.
 */

@Component
public class NightLight implements Handler {
    @Value("${nightLight.start}")
    private String timeStart;
    @Value("${nightLight.end}")
    private String timeEnd;
    DateFormat dateFormat;
    boolean inited = false;

    public NightLight() {
        dateFormat = new SimpleDateFormat("HH:mm");
    }

    @Override
    public void handle(String msg) {
        System.out.println("Received " + msg);
        System.out.println(timeStart);
        System.out.println(timeEnd);
        if (chkTime()) action();
    }

    private boolean chkTime() {
        LocalTime now = new LocalTime().now();

        LocalTime start = new LocalTime(timeStart);
        LocalTime end = new LocalTime(timeEnd);
        return ((now.isAfter(start) || now.isBefore(end)));
    }

    private void action() {
        System.out.println("NightLight action()");
    }
}
