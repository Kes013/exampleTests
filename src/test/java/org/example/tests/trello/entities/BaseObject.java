package org.example.tests.trello.entities;

public abstract class BaseObject {

    String name;

    public BaseObject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void createByUI();

    public abstract void checkByUI();

    public abstract void checkByUI(String action);

    public abstract void createByRest();

    public abstract void createByRest(String id);

    public abstract void deleteByRest();

    public abstract void deleteByUI();

}

