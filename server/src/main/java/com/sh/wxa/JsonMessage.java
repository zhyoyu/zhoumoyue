package com.sh.wxa;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

public class JsonMessage implements Message {

    private static final Gson[] GSON_ARRAY = new Gson[40];

    static {
        for (int i = 0; i < GSON_ARRAY.length; ++i) {
            GSON_ARRAY[i] = new GsonBuilder().disableHtmlEscaping().create();
        }
    }

    private static int currentThreadIdx() {
        long threadId = Thread.currentThread().getId();
        int idx = (int) (threadId % GSON_ARRAY.length);
        return idx;
    }

    private static final Type mapType = new TypeToken<Map<String, String>>() {
    }.getType();

    public static Map<String, String> jsonStringToMap(String jsonString) {
        int idx = currentThreadIdx();
        return GSON_ARRAY[idx].fromJson(jsonString, mapType);
    }

    public static <T> T fromJsonString(String jsonString, Class<T> clazz) {
        int idx = currentThreadIdx();
        return GSON_ARRAY[idx].fromJson(jsonString, clazz);
    }

    public final String toJsonString() {
        int idx = currentThreadIdx();
        return GSON_ARRAY[idx].toJson(this);
    }

    public static String toJsonString(Object obj) {
        int idx = currentThreadIdx();
        return GSON_ARRAY[idx].toJson(obj);
    }

}
