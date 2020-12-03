package org.example.tests.trello.tests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
        features = {"src/test/java/org/example/tests/trello/tests/features/"},
        tags = "@UI",
        glue = "org/example/tests/trello/steps")
public class RunTests {
}
