package org.example.tests.trello.helpers;


import org.json.JSONObject;
import org.json.JSONArray;


public class ResponseParser {

    public static String getTeamId(String json) {
        JSONArray jsonArray = new JSONArray(json);
        JSONObject jsonObject = jsonArray.getJSONObject(jsonArray.length()-1);
        return jsonObject.getString("id");
            }
        }
