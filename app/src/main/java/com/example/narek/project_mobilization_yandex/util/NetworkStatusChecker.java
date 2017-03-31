package com.example.narek.project_mobilization_yandex.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.narek.project_mobilization_yandex.App;

public class NetworkStatusChecker {

    private NetworkStatusChecker() {
        //no instance
        throw new RuntimeException("Private constructor cannot be accessed");
    }

    public static boolean isNetworkAvailable() {

        ConnectivityManager connectivityManager = (ConnectivityManager) App.getInstance().getSystemService(Context
                .CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}
