package org.example.tests.trello.pageobjects;

import org.example.tests.trello.helpers.ConfigParser;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class EnterPage extends BasePage {

    private static final By ENTER_BUTTON = By.xpath("//a[text()='Войти']");

    public void chooseEnter() {
        open(ConfigParser.getUrl());
        $(ENTER_BUTTON).click();
    }

}
