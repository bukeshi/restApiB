package org.bulatAPI.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {
                "src/test/resources/features/Rebrandly.feature"
        },
        glue = {
                "org.bulatAPI.steps.rebrandly"
        },
        tags = {

        },
        dryRun = false
)
public class RebrandlyRunner {
}
