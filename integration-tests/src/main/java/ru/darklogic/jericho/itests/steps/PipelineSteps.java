package ru.darklogic.jericho.itests.steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.embedder.Embedder;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.darklogic.jericho.common.ZmqMonitor;
import ru.darklogic.jericho.itests.ZmqTester;

/**
 * Created by sicness on 06.08.15.
 */
@Component
public class PipelineSteps {
    @Autowired
    ZmqTester zmqTester;

    @Given("ZmqTester object")
    public void initMonitor() {
        zmqTester.init();
    }

    @When("I send $msg I want to receive the same")
    public void plus(String msg) {
        Assert.assertTrue(zmqTester.zmqTest(msg, msg));
    }
}