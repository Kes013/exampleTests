package org.example.tests.trello.pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;

public class BasePage {

    public void waitForPageToLoad(SelenideElement element) {
        element.waitUntil(Condition.enabled, 200000L);
    }

    public void waitForPageToLoad() {
        try {
            wait(300000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void checkField(By field, String value) {
        SelenideElement element = $(field);
        String actualResult;
        if (element.getTagName().equals("input")||element.getTagName().equals("textarea"))
            actualResult = element.getValue();
        else
            actualResult = element.text();
        Assert.assertEquals("Incorrect field of element " + field, value, actualResult);
    }

}
