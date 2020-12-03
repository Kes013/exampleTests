package org.example.tests.trello.entities;

import com.codeborne.selenide.Selenide;
import com.google.gson.annotations.SerializedName;
import org.example.tests.trello.helpers.ConfigParser;
import org.example.tests.trello.helpers.ResponseParser;
import org.example.tests.trello.pageobjects.TeamPage;
import org.example.tests.trello.pageobjects.MainPage;
import org.example.tests.trello.helpers.RestHelper;

public class Team extends BaseObject {

    @SerializedName("id")
    private String id;
    private String description;
    private String type;

    public Team(String name, String description, String type) {
        super(name);
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void createByUI() {
        try {
            TeamPage tp = new TeamPage();
            tp.openMenu();
            tp.fillTeamForm(name, description, type);
            tp.submitCreate();
            this.id = ResponseParser.getTeamId(RestHelper.getTeams());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void checkByUI() {
        Selenide.open((ConfigParser.getUrl()));
        new MainPage().openTeam(name);
        TeamPage tp = new TeamPage();
        tp.checkFields(name, description, type);
    }

    @Override
    public void checkByUI(String action) {

    }

    @Override
    public void deleteByUI() {
    }

    @Override
    public void createByRest() {
        RestHelper.createTeam(name, this);
    }

    @Override
    public void createByRest(String id) {
    }

    @Override
    public void deleteByRest() {
        RestHelper.deleteTeam(this.id);
    }
}

