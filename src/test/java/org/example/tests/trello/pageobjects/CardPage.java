package org.example.tests.trello.pageobjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CardPage extends BasePage {

    private static final By CARD_DETAILS_BUTTON = By.cssSelector(".js-card-details");
    private static final By LIST_CARD = By.cssSelector(".list-card");
    private static final By INPUT_TITLE_CARD = By.cssSelector(".js-card-detail-title-input");
    private static final By INPUT_DESC_CARD = By.cssSelector(".card-description");
    private static final By EDIT_LABELS_BUTTON = By.cssSelector(".js-edit-labels");
    private static final By SELECT_LABEL = By.cssSelector(".card-label-red");
    private static final By CLOSE_POPUP_LABEL_BUTTON = By.cssSelector(".pop-over-header-close-btn");
    private static final By ADD_DUE_DATE_BUTTON = By.cssSelector(".js-add-due-date");
    private static final By SELECT_DATE_INPUT = By.cssSelector(".js-dpicker-date-input");
    private static final By SELECT_TIME_INPUT = By.cssSelector(".js-dpicker-time-input");
    private static final By SAVE_DUE_BUTTON = By.cssSelector(".datepicker-confirm-btns > input");
    private static final By CLOSE_FORM_BUTTON = By.cssSelector(".js-close-window");
    private static final By EDIT_CARD_BUTTON = By.cssSelector(".icon-edit");
    private static final By COPY_BUTTON = By.cssSelector(".js-copy-card");
    private static final By SELECT_BOARD = By.cssSelector(".js-select-board");
    private static final By SELECT_COLUMN = By.cssSelector(".js-select-list");
    private static final By SELECT_POSITION = By.cssSelector(".js-select-position");
    private static final By SUBMIT_COPY_BUTTON = By.cssSelector(".js-submit");
    private static final By ADD_LIST_BUTTON = By.cssSelector(".js-add-checklist-menu");
    private static final By ARCHIVE_CARD_BUTTON = By.cssSelector(".js-archive-card");
    private static final By DELETE_CARD_BUTTON = By.cssSelector(".js-delete-card");
    private static final By CONFIRM_DELETE_CARD_BUTTON = By.cssSelector(".js-confirm");
    private static final By CARD_NAME_FIELD = By.cssSelector(".js-card-detail-title-input");
    private static final By CARD_DESC_FIELD = By.cssSelector("div.js-show-with-desc > p");
    private static final By CARD_LABEL_FIELD = By.cssSelector("span.card-label.card-label-red.mod-card-detail");
    private static final By CARD_LIST_FIELDS = By.cssSelector(".checklist-item-text-and-controls > span");
    private static final By CREATE_CHECKLIST_BUTTON = By.cssSelector("#id-checklist");
    private static final By ADD_CHECKLIST_BUTTON = By.cssSelector(".js-add-checklist");
    private static final By CHECKLIST_ITEM = By.cssSelector(".js-new-checklist-item-input");
    private static By CARD_DETAILS(String text) {
        return By.xpath("//textarea[text()='" + text + "']/ancestor::div[@class='list js-list-content']");
    }

    public void openCard() {
        waitForPageToLoad($(CARD_DETAILS_BUTTON));
        $$(CARD_DETAILS_BUTTON).get(0).click();
    }

    public void openCard(String listName) {
        $(CARD_DETAILS(listName)).findAll(LIST_CARD).get(0).click();
    }

    public void changeCardDetails(String name, String desc) {
        $(INPUT_TITLE_CARD).click();
        $(INPUT_TITLE_CARD).clear();
        $(INPUT_TITLE_CARD).sendKeys(name);
        $(INPUT_DESC_CARD).click();
        $(INPUT_DESC_CARD).clear();
        $(INPUT_DESC_CARD).sendKeys(desc);
    }

    public void selectLabel() {
        $(EDIT_LABELS_BUTTON).click();
        $(SELECT_LABEL).click();
        $(CLOSE_POPUP_LABEL_BUTTON).click();
    }

    public void selectDueDate(String dueDate, String dueTime) {
        $(ADD_DUE_DATE_BUTTON).click();
        $(SELECT_DATE_INPUT).clear();
        $(SELECT_DATE_INPUT).sendKeys(dueDate);
        $(SELECT_TIME_INPUT).sendKeys("\b\b\b\b\b\b");
        $(SELECT_TIME_INPUT).sendKeys(dueTime);
        $(SAVE_DUE_BUTTON).click();
    }

    public void closeCard() {
        $(CLOSE_FORM_BUTTON).click();
    }

    public void copyingCard(String boardName, String listName, String position) {
        waitForPageToLoad($(CARD_DETAILS_BUTTON));
        $(EDIT_CARD_BUTTON).hover();
        $(EDIT_CARD_BUTTON).click();
        $(COPY_BUTTON).click();
        $(SELECT_BOARD).selectOptionContainingText(boardName);
        $(SELECT_COLUMN).selectOptionContainingText(listName);
        $(SELECT_POSITION).selectOptionContainingText(position);
        $(SUBMIT_COPY_BUTTON).click();
    }

    public void addingCheckList(List<String> checkList) {
        $(ADD_LIST_BUTTON).click();
        $(CREATE_CHECKLIST_BUTTON).sendKeys("New checklist");
        $(ADD_CHECKLIST_BUTTON).click();
        for (String item : checkList) {
            $(CHECKLIST_ITEM).sendKeys(item);
            $(CHECKLIST_ITEM).sendKeys(Keys.ENTER);
        }
    }

    public void deletingCard() {
        $(ARCHIVE_CARD_BUTTON).click();
        $(DELETE_CARD_BUTTON).click();
        $(CONFIRM_DELETE_CARD_BUTTON).click();
    }

    public void checkFields(String name) {
        checkField(CARD_NAME_FIELD, name);
    }

    public void checkFields(String name, String desc, String dueDate, String dueTime) {
        checkField(CARD_NAME_FIELD, name);
        checkField(CARD_DESC_FIELD, desc);
        Assert.assertTrue("Labels are not exist ", $(CARD_LABEL_FIELD).exists());
        $(ADD_DUE_DATE_BUTTON).click();
        checkField(SELECT_DATE_INPUT, dueDate);
        checkField(SELECT_TIME_INPUT, dueTime);
        $(SAVE_DUE_BUTTON).click();
    }

    public void checkFields(List<String> checklist) {
        List<String> list = new ArrayList<>($$(CARD_LIST_FIELDS).texts());
        Assert.assertEquals("CheckList is incorrect ", list, checklist);
    }


}
