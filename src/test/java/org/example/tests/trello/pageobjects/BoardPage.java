package org.example.tests.trello.pageobjects;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class BoardPage extends BasePage {

    private static final By BOARD_BUTTON = By.cssSelector(".boards-page-board-section-list li > a");
    private static final By ADD_CARD_BUTTON = By.cssSelector(".js-card-composer-container > a");
    private static final By INPUT_CARD_NAME = By.cssSelector(".list-card-composer-textarea");
    private static final By SUBMIT_BUTTON = By.cssSelector(".js-add-card");
    private static final By SHOW_MENU_BUTTON = By.cssSelector(".mod-show-menu");
    private static final By OPEN_MORE_BUTTON = By.cssSelector(".js-open-more");
    private static final By CLOSE_BOARD_BUTTON = By.cssSelector(".js-close-board");
    private static final By CONFIRM_CLOSE_BOARD_BUTTON = By.cssSelector(".js-confirm");
    private static final By DELETE_BUTTON = By.cssSelector(".js-delete");
    private static By BOARD_CARD(String text){return By.xpath("//*[@class='board-tile-details-name'][@title='"+text+"']");}


    public void openBoard() {
        waitForPageToLoad($(BOARD_BUTTON));
        $$(BOARD_BUTTON).get(2).click();
    }

    public void openBoard(String name) {
        waitForPageToLoad($(".home-container"));
        $(BOARD_CARD(name)).click();
    }

    public void createSimpleCard(String name) {
        $$(ADD_CARD_BUTTON).get(0).click();
        $(INPUT_CARD_NAME).sendKeys(name);
        $(SUBMIT_BUTTON).click();
    }

    public void deleteBoard() {
        $(SHOW_MENU_BUTTON).click();
        $(OPEN_MORE_BUTTON).click();
        $(CLOSE_BOARD_BUTTON).click();
        $(CONFIRM_CLOSE_BOARD_BUTTON).click();
        $(DELETE_BUTTON).waitUntil(Condition.enabled, 3000L).click();
    }


}
