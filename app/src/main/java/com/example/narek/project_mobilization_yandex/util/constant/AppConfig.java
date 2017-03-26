package com.example.narek.project_mobilization_yandex.util.constant;

public class AppConfig {

    private AppConfig() {
        //no instance
        throw new RuntimeException("Private constructor cannot be accessed");
    }

    public static final String TRANSLATE_API_URL = "https://translate.yandex.net/api/v1.5/tr.json/";
    public static final String DICTIONARY_API_URL = "https://dictionary.yandex.net/api/v1/dicservice.json/";
    public static final String TRANSLATE_API_KEY = "trnsl.1.1.20170325T083814Z.a90dabde4f02f1eb.dc709a8a9f138d56d4771cd70ad742942f2d5648";
    public static final String DICTIONARY_API_KEY = "dict.1.1.20170325T114708Z.3cf2d18f8052b275.470969b808ff80f1346b556f10aef5455df1f7ac";

}
