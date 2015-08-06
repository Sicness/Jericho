package ru.darklogic.jericho.itests.steps;

import junit.framework.Assert;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.embedder.Embedder;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Sicness on 17.02.2015.
 */
@Component
public class StatusSteps extends Embedder {
    int i;
    Throwable throwable = null;

    @Given("Int = $i")
    public void setI(int i) {
        this.i = i;
    }

    @When("I want to plus $i")
    public void plus(int i) {
        this.i += i;
    }

    @Then("Result should be $i")
    public void throwableChk(int i) {
        Assert.assertEquals(this.i, i);
    }
}
