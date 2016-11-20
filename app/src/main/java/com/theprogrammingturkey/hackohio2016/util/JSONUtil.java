package com.theprogrammingturkey.hackohio2016.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class JSONUtil {
    private static final JsonParser PARSER = new JsonParser();
    private static final Gson GSON = new Gson();

    public static JsonElement getJSonFromFile(File file) {
        try {
            return PARSER.parse(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writeJsonToFile(File file, JsonElement json) {
        try {
            GSON.toJson(json, new FileWriter(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
