package org.example.tests.trello.steps;

import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Attachment;
import org.example.tests.trello.entities.*;
import org.example.tests.trello.helpers.RestHelper;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.logging.Logger;

public class BaseStepsForTests extends RestHelper {

    private static final Logger LOG = Logger.getLogger(String.valueOf(BaseStepsForTests.class));
    private Board board;
    private Column column;
    private Card card;
    private BaseObject baseObject;

    @Given("Board is created by REST")
    public void createBoardByRest() {
        board = new Board("New Board");
        board.createByRest();
    }

    @Given("Column is created by REST")
    public void createColumnByRest() {
        column = new Column("New List");
        column.createByRest(board.getId());
    }

    @Given("Card is created by REST")
    public void createCardByRest() {
        card = new Card("New Card");
        card.createByRest(column.getId());
    }

    @When("User creates a (.*)$")
    public void createEntity(String entity) throws Exception {
        if (entity.equals(Team.class.getSimpleName()))
            baseObject = new Team("New command", "New description", "Инженерия/ИТ");
        else if (entity.equals(Board.class.getSimpleName()))
            baseObject = new Board("New Board");
        else if (entity.equals(Card.class.getSimpleName()))
            baseObject = new Card("New Card");
        else if (entity.equals(Column.class.getSimpleName()))
            baseObject = new Column("New List");
        else throw new Exception("Entity" + entity + " not found");
        baseObject.createByUI();
    }

    @Then("^(.*) is valid after (.*)")
    public void verifyEntity(String entity, String action) {
        switch (entity) {
            case "Team":
                baseObject.checkByUI();
                baseObject.deleteByRest();
                break;
            case "Board":
                try {
                    baseObject.checkByUI();
                } catch (AssertionError ae) {
                    attachFailingScreenShot();
                    throw new AssertionError(ae);
                } finally {
                    board.deleteByRest();
                }
                break;
            case "Card":
                try {
                    baseObject.checkByUI(action);
                } catch (AssertionError ae) {
                    attachFailingScreenShot();
                    throw new AssertionError(ae);
                } finally {
                    board.deleteByRest();
                }
                break;
        }
    }

    @And("User edits a card")
    public void editCard() {
        baseObject = card;
        ((Card) baseObject).editCard("Updated card", "Updated description", "14.5.2025", "20:00");
    }

    @And("User copies a card to board {string} and list {string} on position {string}")
    public void copyCard(String boardName, String listName, String position) {
        baseObject = card;
        ((Card) baseObject).copyCard(boardName, listName, position);
    }

    @And("User adds checklist to the card")
    public void addCheckListToCard() {
        baseObject = card;
        ((Card) baseObject).addCheckList();
    }

    @When("Card is updating by REST")
    public void updateCard() {
        updateCardByRest(card.getId(), "Update card", "Update update update");
    }

    @Then("Verify Card by REST")
    public void checkUpdateCard() {
        Assert.assertTrue(isCardUpdated(card.getId(), "Update card", "Update update update"));
    }

    @When("Column is archiving by REST")
    public void archiveColumn() {
        deleteColumnByRest(column.getId());
    }

    @Then("Verify Column by REST")
    public void checkArchiveColumn() {
        Assert.assertTrue(isColumnArchived(column.getId()));
    }

    @Then("Verify Board by REST")
    public void checkCreateBoard() {
        Assert.assertTrue(isBoardExist(board.getId()));
    }

    @Then("Test Data is cleaned")
    public void deleteBoard() {
        board.deleteByRest();
    }

        /*
    TODO: Реализовать проверки

    public void createBoardInCommand() {
        command.createCommandByRest();
        board.createBoardbyUI();
    }

    public void deleteCard(){
        command.createCommandByRest();
        card.deleteCardByUI();
    }

    public void deleteBoard()  {
        board.deleteBoardbyUI();
    }
     */

    @Attachment(value = "FAILING SCREENSHOT", type = "image/png")
    public byte[] attachFailingScreenShot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
