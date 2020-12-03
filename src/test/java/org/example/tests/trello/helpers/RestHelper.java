package org.example.tests.trello.helpers;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.example.tests.trello.entities.*;
import org.junit.Assert;

import java.util.logging.Logger;

public class RestHelper {

    private static final Logger LOG = Logger.getLogger(String.valueOf(RestHelper.class));
    private static final String token = "44cd87fe1aa910a775e183dcf3efac25918c7da1243667df74ae0fc11c75bef5";
    private static final String key = "7bdcfebb4ed4a13cc11f74210715511f";


    public static void createTeam(String name, Team team) {
        HttpResponse<String> response = null;
        try {
            response = Unirest.post("https://api.trello.com/1/organizations")
                    .queryString("key", key)
                    .queryString("token", token)
                    .queryString("displayName", name)
                    .asString();
        } catch (UnirestException ex) {
            ex.printStackTrace();
        }
        Assert.assertNotNull("Request was not sent", response);
        Assert.assertTrue(response.getStatus() >= 200 && response.getStatus() < 400);
        Assert.assertNotNull("Incorrect response result", response.getBody());
        Gson g = new Gson();
        Team c = g.fromJson(response.getBody(), Team.class);
        team.setId(c.getId());
    }

    public static String getTeams() {
        HttpResponse<String> response = null;
        try {
            response = Unirest.get("https://api.trello.com/1/members/me/organizations")
                    .header("Accept", "application/json")
                    .queryString("key", key)
                    .queryString("token", token)
                    .asString();
        } catch (UnirestException ex) {
            ex.printStackTrace();
        }
        Assert.assertNotNull("Request was not sent", response);
        Assert.assertTrue(response.getStatus() >= 200 && response.getStatus() < 400);
        Assert.assertNotNull("Incorrect response result", response.getBody());
        return response.getBody();
    }

    public static void deleteTeam(String id) {
        HttpResponse<String> response = null;
        try {
            response = Unirest.delete("https://api.trello.com/1/organizations/" + id)
                    .queryString("key", key)
                    .queryString("token", token)
                    .asString();
        } catch (UnirestException ex) {
            ex.printStackTrace();
        }
        Assert.assertNotNull("Request was not sent", response);
        Assert.assertTrue(response.getStatus() >= 200 && response.getStatus() < 400);
    }

    public static void createBoardByRest(Board board, String name) {
        HttpResponse<String> response = null;
        try {
            response = Unirest.post("https://api.trello.com/1/boards/")
                    .queryString("key", key)
                    .queryString("token", token)
                    .queryString("name", name)
                    .asString();
        } catch (UnirestException ex) {
            ex.printStackTrace();
        }
        Assert.assertNotNull("Request was not sent", response);
        Assert.assertTrue(response.getStatus() >= 200 && response.getStatus() < 400);
        Assert.assertNotNull("Incorrect response result", response.getBody());
        Gson g = new Gson();
        Board b = g.fromJson(response.getBody(), Board.class);
        board.setId(b.getId());
        board.setName(b.getName());
    }

    public static boolean isBoardExist(String id) {
        HttpResponse<String> response = null;
        try {
            response = Unirest.get("https://api.trello.com/1/boards/" + id)
                    .header("Accept", "application/json")
                    .queryString("key", key)
                    .queryString("token", token)
                    .asString();
        } catch (UnirestException ex) {
            ex.printStackTrace();
        }
        Assert.assertNotNull("Request was not sent", response);
        Assert.assertTrue(response.getStatus() >= 200 && response.getStatus() < 400);
        Assert.assertNotNull("Incorrect response result", response.getBody());
        return !response.getBody().equals("[]");
    }

    public static void deleteBoardByRest(String id) {
        HttpResponse<String> response = null;
        try {
            response = Unirest.delete("https://api.trello.com/1/boards/" + id)
                    .queryString("key", key)
                    .queryString("token", token)
                    .asString();
        } catch (UnirestException ex) {
            ex.printStackTrace();
        }
        Assert.assertNotNull("Request was not sent", response);
        Assert.assertTrue(response.getStatus() >= 200 && response.getStatus() < 400);
    }

    public static void createColumnByRest(Column column, String name, String boardId) {
        HttpResponse<String> response = null;
        try {
            response = Unirest.post("https://api.trello.com/1/boards/" + boardId + "/lists")
                    .queryString("key", key)
                    .queryString("token", token)
                    .queryString("name", name)
                    .asString();
        } catch (UnirestException ex) {
            ex.printStackTrace();
        }
        Assert.assertNotNull("Request was not sent", response);
        Assert.assertTrue(response.getStatus() >= 200 && response.getStatus() < 400);
        Assert.assertNotNull("Incorrect response result", response.getBody());
        Gson g = new Gson();
        Column l = g.fromJson(response.getBody(), Column.class);
        column.setId(l.getId());
    }

    public static void createCardByRest(Card card, String listId, String name) {
        HttpResponse<String> response = null;
        try {
            response = Unirest.post("https://api.trello.com/1/cards/")
                    .queryString("key", key)
                    .queryString("token", token)
                    .queryString("idList", listId)
                    .queryString("name", name)
                    .asString();
        } catch (UnirestException ex) {
            ex.printStackTrace();
        }
        Assert.assertNotNull("Request was not sent", response);
        Assert.assertTrue(response.getStatus() >= 200 && response.getStatus() < 400);
        Assert.assertNotNull("Incorrect response result", response.getBody());
        Gson g = new Gson();
        Card c = g.fromJson(response.getBody(), Card.class);
        card.setId(c.getId());
    }

    public static void updateCardByRest(String id, String name, String desc) {
        HttpResponse<String> response = null;
        try {
            response = Unirest.put("https://api.trello.com/1/cards/" + id)
                    .header("Accept", "application/json")
                    .queryString("key", key)
                    .queryString("token", token)
                    .queryString("name", name)
                    .queryString("desc", desc)
                    .asString();
        } catch (UnirestException ex) {
            ex.printStackTrace();
        }
        Assert.assertNotNull("Request was not sent", response);
        Assert.assertTrue(response.getStatus() >= 200 && response.getStatus() < 400);
    }

    public static boolean isCardUpdated(String id, String name, String desc) {
        HttpResponse<String> response = null;
        try {
            response = Unirest.get("https://api.trello.com/1/cards/" + id)
                    .header("Accept", "application/json")
                    .queryString("key", key)
                    .queryString("token", token)
                    .asString();
        } catch (UnirestException ex) {
            ex.printStackTrace();
        }
        Assert.assertNotNull("Request was not sent", response);
        Assert.assertTrue(response.getStatus() >= 200 && response.getStatus() < 400);
        Assert.assertNotNull("Incorrect response result", response.getBody());
        Gson g = new Gson();
        Card c = g.fromJson(response.getBody(), Card.class);
        return c.getName().equals(name) && (c.getDesc().equals(desc));
    }


    public static void deleteCardByRest(String id) {
        HttpResponse<String> response = null;
        try {
            response = Unirest.delete("https://api.trello.com/1/cards/" + id)
                    .queryString("key", key)
                    .queryString("token", token)
                    .asString();
        } catch (UnirestException ex) {
            ex.printStackTrace();
        }
        Assert.assertNotNull("Request was not sent", response);
        Assert.assertTrue(response.getStatus() >= 200 && response.getStatus() < 400);
    }

    public static void deleteColumnByRest(String id) {
        HttpResponse<String> response = null;
        try {
            response = Unirest.put("https://api.trello.com/1/lists/" + id + "/closed")
                    .queryString("key", key)
                    .queryString("token", token)
                    .queryString("value", true)
                    .asString();
        } catch (UnirestException ex) {
            ex.printStackTrace();
        }
        Assert.assertNotNull("Request was not sent", response);
        Assert.assertTrue(response.getStatus() >= 200 && response.getStatus() < 400);
    }

    public static boolean isColumnArchived(String id) {
        HttpResponse<String> response = null;
        try {
            response = Unirest.put("https://api.trello.com/1/lists/" + id)
                    .queryString("key", key)
                    .queryString("token", token)
                    .asString();
        } catch (UnirestException ex) {
            ex.printStackTrace();
        }
        Assert.assertNotNull("Request was not sent", response);
        Assert.assertTrue(response.getStatus() >= 200 && response.getStatus() < 400);
        Assert.assertNotNull("Incorrect response result", response.getBody());
        Gson g = new Gson();
        Column c = g.fromJson(response.getBody(), Column.class);
        return c.getClosed().equals("true");
    }
}


