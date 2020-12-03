package org.example.tests.trello.entities;

import com.google.gson.annotations.SerializedName;
import org.example.tests.trello.pageobjects.BoardPage;
import org.example.tests.trello.pageobjects.CreateBoardPage;
import org.example.tests.trello.helpers.RestHelper;

public class Board extends BaseObject {

    @SerializedName("id")
    private String id;
    private String description;

    public Board(String name) {
        super(name);
        this.name=name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void createByRest() {
        RestHelper.createBoardByRest(this, name);
    }

    @Override
    public void createByRest(String id) {
    }

    public void createByUI() {
        CreateBoardPage bp = new CreateBoardPage();
        bp.openMenu();
        bp.fillBoardForm(name);
        bp.submitCreate();
    }

    @Override
    public void deleteByUI() {
        BoardPage bp = new BoardPage();
        bp.openBoard();
        bp.deleteBoard();
    }

    @Override
    public void deleteByRest() {
        RestHelper.deleteBoardByRest(this.id);
    }


    @Override
    public void checkByUI() {
    }

    @Override
    public void checkByUI(String action) {
    }

}
