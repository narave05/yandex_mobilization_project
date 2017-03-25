package com.example.narek.project_mobilization_yandex.data.interfaces;

public interface OnLoadEventListener<T> {
    void onLoadStart(boolean isShow);
    void onLoadComplete(T data);
}
