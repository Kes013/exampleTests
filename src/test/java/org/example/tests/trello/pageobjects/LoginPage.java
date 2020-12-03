package org.example.tests.trello.pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.example.tests.trello.helpers.ConfigParser;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;


public class LoginPage extends BasePage {

    private static final By USER_INPUT = By.id("user");
    private static final By LOGIN_BUTTON = By.id("login");
    private static final By PASSWORD_INPUT = By.id("password");
    private static final By LOGIN_SUBMIT_BUTTON = By.id("login-submit");
    private static final By MEMBER_MENU_BUTTON = By.xpath("//*[@data-test-id='header-member-menu-button']");
    private static final By LOGOUT_BUTTON = By.xpath("//*[@data-test-id='header-member-menu-logout']");


    public void login() {
        EnterPage enterPage = new EnterPage();
        enterPage.chooseEnter();
        $(USER_INPUT).sendKeys(ConfigParser.getUser());
        $(LOGIN_BUTTON).waitUntil(Condition.visible, 30000L).click();
        Selenide.switchTo().window("Войдите, чтобы продолжить - Войти с аккаунтом Atlassian");
        $(PASSWORD_INPUT).waitUntil(Condition.visible, 30000L).sendKeys(ConfigParser.getPass());
        $(LOGIN_SUBMIT_BUTTON).click();
        waitForPageToLoad($(".home-container"));
    }

    public void logout() {
        Selenide.open(ConfigParser.getUrl());
        $(MEMBER_MENU_BUTTON).click();
        $(LOGOUT_BUTTON).click();
    }
}
