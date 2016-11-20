package com.theprogrammingturkey.hackohio2016.util;


import android.content.Context;
import android.os.Environment;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.reimaginebanking.api.nessieandroidsdk.requestclients.NessieClient;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NessieHook {

    private static NessieClient client;

    private static File data;
    private static JsonObject dataJson;

    public static void init(Context context) {

        data = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS) + "/data.json");
        if (!data.exists()) {
            try {
                data.createNewFile();
                JSONUtil.writeJsonToFile(data, new JsonObject());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        dataJson = JSONUtil.getJSonFromFile(data).getAsJsonObject();

        if (!dataJson.has("APIKeys")) {
            dataJson.add("APIKeys", new JsonArray());
            JSONUtil.writeJsonToFile(data, dataJson);
        }
    }

    public static List<String> getStoredAPIKeys() {
        JsonArray keys = dataJson.get("APIKeys").getAsJsonArray();

        List<String> toReturn = new ArrayList<>();
        for (JsonElement element : keys) {
            toReturn.add(element.getAsString());
        }
        return toReturn;
    }

    public static void setCurrentApiKey(String key) {
        client = NessieClient.getInstance(key);
    }

    public static void addApiKey(String key) {
        JsonArray keys = dataJson.get("APIKeys").getAsJsonArray();
        keys.add(new JsonPrimitive(key));
        JSONUtil.writeJsonToFile(data, dataJson);
    }

    public static void removeApiKey(String key) {
        JsonArray keys = dataJson.get("APIKeys").getAsJsonArray();
        for (int i = keys.size() - 1; i >= 0; i--) {
            if (keys.get(i).getAsString().equals(key)) {
                keys.remove(i);
            }
        }
        JSONUtil.writeJsonToFile(data, dataJson);
    }
}