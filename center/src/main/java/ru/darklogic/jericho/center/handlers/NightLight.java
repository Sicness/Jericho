package ru.darklogic.jericho.center.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;
import ru.darklogic.jericho.center.Handler;
import ru.darklogic.jericho.common.PropsControl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import javax.annotation.PostConstruct;
import java.util.Date;

/**
 * Created by sicness on 05.08.15.
 */
@Component
public class NightLight implements Handler {
    private PropsControl props;

    private String timeStart;
    private String timeEnd;


    @Autowired
    public NightLight(@Value("${nightLight.start}")String timeStart, @Value("nightLight.end") String timeEnd) {
        System.out.println("timeStart=" + timeStart);
        System.out.println("timeEnd=" + timeEnd);
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    @Override
    public void handle(String message) {

    }

    private boolean chkTime() {
        return false;
    }
}
