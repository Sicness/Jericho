package ru.darklogic.jericho.center.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;
import ru.darklogic.jericho.center.Handler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import javax.annotation.PostConstruct;
import java.util.Date;

/**
 * Created by sicness on 05.08.15.
 */
public class NightLight implements Handler {
    @Value("${nightLight.start}")
    private String timeStart;
    @Value("${nightLight.end}")
    private String timeEnd;


    @Override
    public void handle(String msg) {
        System.out.println("Received " + msg);
        System.out.println(timeStart);
        System.out.println(timeEnd);
    }

    private boolean chkTime() {
        return false;
    }
}
