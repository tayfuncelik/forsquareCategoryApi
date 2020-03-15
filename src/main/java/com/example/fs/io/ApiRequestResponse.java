package com.example.fs.io;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ApiRequestResponse {



    private JSONObject response;
    private JSONArray notifications;
    private ResultMeta meta;

    public ApiRequestResponse(ResultMeta meta, JSONObject response, JSONArray notifications) throws JSONException {
        this.meta = meta;
        this.response = response;
        this.notifications = notifications;
    }

    public JSONObject getResponse() {
        return response;
    }

    public JSONArray getNotifications() {
        return notifications;
    }

    public ResultMeta getMeta() {
        return meta;
    }

}