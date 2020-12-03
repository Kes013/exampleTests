package org.example.tests.trello.entities;

import com.codeborne.selenide.Selenide;
import com.google.gson.annotations.SerializedName;
import org.example.tests.trello.helpers.ConfigParser;
import org.example.tests.trello.pageobjects.BoardPage;
import org.example.tests.trello.pageobjects.CardPage;
import org.example.tests.trello.helpers.RestHelper;

import java.util.ArrayList;
import java.util.List;


public class Card extends BaseObject {

    @SerializedName("id")
    private String id;
    @SerializedName("desc")
    private String desc;
    private String dueDate;
    private String dueTime;
    private List<String> checkList;
    private String targetBoard;
    private String targetList;

    public Card(String name) {
        super(name);
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    private List<String> setCheckList() {
        List<String> listItems = new ArrayList<>();
        for (int i = 0; i <= 5; i++) {
            listItems.add("test" + i);
        }
        return listItems;
    }

    public void createByUI() {
        BoardPage bp = new BoardPage();
        bp.openBoard("New Board");
        bp.createSimpleCard(name);
    }

    public void editCard(String name, String desc, String dueDate, String dueTime) {
        BoardPage bp = new BoardPage();
        bp.openBoard("New Board");
        CardPage cp = new CardPage();
        cp.openCard();
        cp.changeCardDetails(name, desc);
        cp.selectLabel();
        cp.selectDueDate(dueDate, dueTime);
        cp.closeCard();
        this.name = name;
        this.desc = desc;
        this.dueDate = dueDate;
        this.dueTime = dueTime;
    }

    public void copyCard(String boardName, String listName,String position) {
        BoardPage bp = new BoardPage();
        bp.openBoard("New Board");
        CardPage cp = new CardPage();
        cp.copyingCard(boardName, listName,position);
        this.targetBoard = boardName;
        this.targetList = listName;
    }

    public void addCheckList() {
        BoardPage bp = new BoardPage();
        bp.openBoard("New Board");
        CardPage cp = new CardPage();
        cp.openCard();
        cp.addingCheckList(setCheckList());
        cp.closeCard();
        this.checkList = setCheckList();
    }


    @Override
    public void deleteByUI() {
        BoardPage bp = new BoardPage();
        bp.openBoard("New Board");
        CardPage cp = new CardPage();
        cp.openCard();
        cp.deletingCard();
    }

    @Override
    public void checkByUI(String action) {
        Selenide.open((ConfigParser.getUrl()));
        BoardPage bp = new BoardPage();
        CardPage cp = new CardPage();
        if (action.equals("copy")) {
            bp.openBoard(this.targetBoard);
            cp.openCard(this.targetList);
        } else {
            bp.openBoard("New Board");
            cp.openCard();
        }
        switch (action) {
            case "edit":
                cp.checkFields(this.name, this.desc, this.dueDate, this.dueTime);
                break;
            case "addCheckList":
                cp.checkFields(checkList);
                break;
            default:
                cp.checkFields(this.name);
                break;
        }
        cp.closeCard();
    }


    @Override
    public void createByRest(String listId) {
        RestHelper.createCardByRest(this, listId, name);
    }

    @Override
    public void deleteByRest() {
        RestHelper.deleteCardByRest(this.id);
    }

    @Override
    public void createByRest() {
    }

    @Override
    public void checkByUI() {
    }

}



