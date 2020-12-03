package org.example.tests.trello.entities;

import com.google.gson.annotations.SerializedName;
import org.example.tests.trello.helpers.RestHelper;

public class Column extends BaseObject {

    @SerializedName("id")
    private String id;
    @SerializedName("closed")
    private String closed;

    public Column(String name) {
        super(name);
        this.name=name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClosed() {
        return closed;
    }

    public void setClosed(String closed) {
        this.closed = closed;
    }


    @Override
    public void createByRest(String boardId) {
        RestHelper.createColumnByRest(this, name, boardId);
    }

    @Override
    public void deleteByRest() {
        RestHelper.deleteColumnByRest(this.id);
    }

    @Override
    public void createByRest() {
    }


    @Override
    public void createByUI() {

    }

    @Override
    public void checkByUI() {
    }

    @Override
    public void checkByUI(String action) {

    }

    @Override
    public void deleteByUI() {
    }
}
