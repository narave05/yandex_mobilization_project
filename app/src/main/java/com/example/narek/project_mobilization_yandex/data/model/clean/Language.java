package com.example.narek.project_mobilization_yandex.data.model.clean;

import java.io.Serializable;

public class Language implements Serializable {

    private final String code;
    private final String fullName;

    public Language(String code, String fullName) {
        this.code = code;
        this.fullName = fullName;
    }

    public String getCode() {
        return code;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Language)) {
            return false;
        }
        Language language = ((Language) obj);
        return code.equals(language.code);
    }

    @Override
    public int hashCode() {
        return 36 * code.hashCode();
    }
}
