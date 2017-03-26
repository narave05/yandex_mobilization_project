package com.example.narek.project_mobilization_yandex.util;

import java.util.ArrayList;
import java.util.List;

public class StringUtils {
    private StringUtils() {
        //no instance
        throw new RuntimeException("Private constructor cannot be accessed");
    }

    public static String join(List<String> strings) {
        StringBuilder sb = new StringBuilder();
        for (String string : strings) {
            sb.append(string).append(" ");
        }
        return sb.toString();
    }
}
