package com.example.narek.project_mobilization_yandex.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DictionaryResponse {

    @SerializedName("head")
    @Expose
    public Head head;
    @SerializedName("def")
    @Expose
    public List<Def> def = null;

    private class Head {


    }

    private class Def {

        @SerializedName("text")
        @Expose
        public String text;
        @SerializedName("pos")
        @Expose
        public String pos;
        @SerializedName("ts")
        @Expose
        public String ts;
        @SerializedName("tr")
        @Expose
        public List<Tr> tr = null;

    }


    private class Ex {

        @SerializedName("text")
        @Expose
        public String text;
        @SerializedName("tr")
        @Expose
        public List<Tr_> tr = null;

    }

    private class Tr {

        @SerializedName("text")
        @Expose
        public String text;
        @SerializedName("pos")
        @Expose
        public String pos;
        @SerializedName("mean")
        @Expose
        public List<Mean> mean = null;
        @SerializedName("ex")
        @Expose
        public List<Ex> ex = null;

    }

    private class Mean {

        @SerializedName("text")
        @Expose
        public String text;

    }

    private class Tr_ {

        @SerializedName("text")
        @Expose
        public String text;

    }
}
