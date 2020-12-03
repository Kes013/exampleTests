package org.example.tests.trello.pageobjects;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class TeamPage extends BasePage{

    private static final By MENU_BUTTON= By.xpath("//*[@data-test-id='header-create-menu-button']");
    private static final By CREATE_BUTTON= By.xpath("//*[@data-test-id='header-create-team-button']");
    private static final By INPUT_TEAM_NAME= By.cssSelector("._1CLyNodCAa-vQi");
    private static final By BUTTON_TEAM_TYPE= By.xpath("//*[@data-test-id='header-create-team-type-input']");
    private static final By INPUT_TEAM_DESC= By.cssSelector("._15aIJYNKhrO4vB");
    private static By SELECT_TEAM_TYPE(String text){return By.xpath("//*[text()='"+text+"']");}
    private static final By SUBMIT_BUTTON= By.cssSelector(".X1P6DVryq8CYGC");
    private static final By EDIT_SETTINGS_BUTTON=By.cssSelector("._2DZdmHnY2Nw7gI");
    private static final By TEAM_NAME_FIELD=By.cssSelector("#displayName");
    private static final By TEAM_TYPE_FIELD=By.cssSelector(".css-1yqjdp6");
    private static final By TEAM_DESC_FIELD=By.cssSelector("#desc");

    public void openMenu() {
        $(MENU_BUTTON).click();
        $(CREATE_BUTTON).click();
    }

    public void fillTeamForm(String name,String desc,String type){
        $(INPUT_TEAM_NAME).sendKeys(name);
        $(BUTTON_TEAM_TYPE).click();
        $(SELECT_TEAM_TYPE(type)).click();
        $(INPUT_TEAM_DESC).waitUntil(Condition.enabled,2000L).sendKeys(desc);
    }

    public void submitCreate(){
        $(SUBMIT_BUTTON).click();
        $(By.linkText("Я сделаю это позже")).click();
    }

    public void checkFields(String name,String desc,String type){
        $(EDIT_SETTINGS_BUTTON).click();
        checkField(TEAM_NAME_FIELD,name);
        checkField(TEAM_TYPE_FIELD,type);
        checkField(TEAM_DESC_FIELD,desc);
    }

}
