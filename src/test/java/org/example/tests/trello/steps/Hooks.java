package org.example.tests.trello.steps;

import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Attachment;
import org.example.tests.trello.helpers.BrowserDriver;
import org.example.tests.trello.pageobjects.LoginPage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {

    private LoginPage loginPage = new LoginPage();

    @Before(value = "@UI")
    public void setUp() {
        WebDriverRunner.setWebDriver(BrowserDriver.chrome());
        loginPage.login();
    }

    @After(value = "@UI")
    public void tearDown(Scenario scenario) {
        loginPage.logout();
        if (scenario.isFailed()) {
            attachFailingScreenShot();
        }
        WebDriverRunner.getWebDriver().close();
    }

    @Attachment(value = "FAILING SCREENSHOT END SCENARIO", type = "image/png")
    public byte[] attachFailingScreenShot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

}

