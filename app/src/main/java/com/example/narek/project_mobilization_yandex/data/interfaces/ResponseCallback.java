package com.example.narek.project_mobilization_yandex.data.interfaces;

public interface ResponseCallback<T> {

    void onResponse(T response);

    void onFailure(Throwable t);
}
