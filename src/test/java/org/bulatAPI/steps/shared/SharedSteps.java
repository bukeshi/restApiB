package org.bulatAPI.steps.shared;

import cucumber.api.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

public class SharedSteps {
    private SharedData sharedData;

    public SharedSteps(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    private final static Logger LOGGER = LogManager.getLogger(SharedSteps.class);

    @Then("^status code is (\\d+)$")
    public void status_code_is(int expectedStatusCode)  {
        LOGGER.debug("Status code returned is "+ sharedData.response.getStatusCode());
        MatcherAssert.assertThat(sharedData.response.getStatusCode(), Matchers.is(expectedStatusCode));
    }

}
