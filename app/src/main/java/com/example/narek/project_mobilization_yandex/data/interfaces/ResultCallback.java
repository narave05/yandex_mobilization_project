package com.example.narek.project_mobilization_yandex.data.interfaces;

public interface ResultCallback<T> {
    void onStart();

    void onResult(T data);

    void onError(Throwable error);
}
