package com.example.narek.project_mobilization_yandex;

import android.app.Application;


public class App extends Application{

    private static App instance = new App();

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

}
