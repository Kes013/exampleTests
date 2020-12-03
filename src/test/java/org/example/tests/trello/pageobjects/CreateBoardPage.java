package org.example.tests.trello.pageobjects;

import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;

public class CreateBoardPage extends BasePage{

    private static final By MENU_BUTTON= By.xpath("//*[@data-test-id='header-create-menu-button']");
    private static final By CREATE_BUTTON= By.xpath("//*[@data-test-id='header-create-board-button']");
    private static final By INPUT_BOARD_NAME= By.cssSelector("._23NUW98LaZfBpQ");
    private static final By BUTTON_BOARD_TYPE= By.cssSelector("._3JfnLi33JtGtIk");
    private static final By SELECT_BOARD_TYPE= By.xpath("//*[@name='private']");
    private static final By SELECT_BOARD_PICTURE= By.xpath("//*[@title='red']");
    private static final By SUBMIT_BUTTON= By.xpath("//*[@data-test-id='create-board-submit-button']");


    public void openMenu() {
        $(MENU_BUTTON).click();
        $(CREATE_BUTTON).click();
    }

    public void fillBoardForm(String name){
       $(INPUT_BOARD_NAME).sendKeys(name);
       $(BUTTON_BOARD_TYPE).click();
       $(SELECT_BOARD_TYPE).click();
       $(SELECT_BOARD_PICTURE).click();
    }

    public void submitCreate(){
        $(SUBMIT_BUTTON).click();
    }
}
