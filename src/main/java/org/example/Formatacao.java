package org.example;

import org.json.JSONObject;

public class Formatacao {
    public static String getContent(String response) {

        JSONObject jsonObject = new JSONObject(response);
        String content = jsonObject.getJSONObject("message").getString("content");

        return content;
    }
}
