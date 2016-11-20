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

    private NessieClient client;

    private File data;
    private JsonObject dataJson;

    public void init(Context context, String apiKey) {

        File docFolder = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS) + "/LED/");
        if (!docFolder.exists()) {
            docFolder.mkdir();
        }

        data = new File(docFolder.getAbsolutePath() + "/data.json");
        if (!data.exists()) {
            try {
                data.createNewFile();
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

    public List<String> getStoredAPIKeys() {
        JsonArray keys = dataJson.get("APIKeys").getAsJsonArray();

        List<String> toReturn = new ArrayList<>();
        for (JsonElement element : keys) {
            toReturn.add(element.getAsString());
        }
        return toReturn;
    }

    public void setCurrentApiKey(String key) {
        client = NessieClient.getInstance(key);
    }

    public void addApiKey(String key) {
        JsonArray keys = dataJson.get("APIKeys").getAsJsonArray();
        keys.add(new JsonPrimitive(key));
        JSONUtil.writeJsonToFile(data, dataJson);
    }

    public void removeApiKey(String key) {
        JsonArray keys = dataJson.get("APIKeys").getAsJsonArray();
        for (int i = key.length() - 1; i >= 0; i--) {
            if (keys.get(i).getAsString().equals(key)) {
                keys.remove(i);
            }
        }
        JSONUtil.writeJsonToFile(data, dataJson);
    }
}