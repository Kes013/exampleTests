package org.example.tests.trello.pageobjects;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class MainPage extends BasePage{

    private By TEAM_BUTTON(String name) { return By.xpath("//*[@class='_1T7jXM3PAP_MoF']//span[text()='" + name + "']"); }
    private static final By TEAM_SETTINGS_BUTTON=By.xpath("//*[@data-test-id='home-team-settings-tab']");

    public void openTeam(String name) {
        $(TEAM_BUTTON(name)).waitUntil(Condition.appear,2000L).find(TEAM_SETTINGS_BUTTON).click();
    }


}
