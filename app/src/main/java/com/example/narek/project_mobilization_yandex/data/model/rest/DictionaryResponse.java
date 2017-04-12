package com.example.narek.project_mobilization_yandex.data.model.rest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DictionaryResponse {

    @SerializedName("head")
    @Expose
    private Head head;
    @SerializedName("def")
    @Expose
    private List<Def> def = null;

    @Override
    public String toString() {
        return "DictionaryResponse{" +
                "head=" + head +
                ", def=" + def +
                '}';
    }

    public List<Def> getDictionaryList() {
        return def;
    }

    private class Head {
    }

    public static class Def {

        @SerializedName("text")
        @Expose
        private String text;
        @SerializedName("pos")
        @Expose
        private String pos;
        @SerializedName("ts")
        @Expose
        private String ts = null;
        @SerializedName("tr")
        @Expose
        private List<Tr> tr = null;

        public String getText() {
            return text;
        }

        public String getPartOfSpeech() {
            return pos;
        }

        public String getTranscription() {
            return ts != null ? ts : null;
        }

        public List<Tr> getTranslatedList() {
            return tr;
        }

        @Override
        public String toString() {
            return "Def{" +
                    "text='" + text + '\'' +
                    ", pos='" + pos + '\'' +
                    ", ts='" + ts + '\'' +
                    ", tr=" + tr +
                    '}';
        }
    }


    public static class Ex {

        @SerializedName("text")
        @Expose
        private String text;
        @SerializedName("tr")
        @Expose
        private List<Tr_> tr = null;

        public String getText() {
            return text;
        }

        public List<Tr_> getTranslatedTextList() {
            return tr;
        }

        @Override
        public String toString() {
            return "Ex{" +
                    "text='" + text + '\'' +
                    ", tr=" + tr +
                    '}';
        }
    }

    public static class Tr {

        @SerializedName("text")
        @Expose
        public String text;
        @SerializedName("pos")
        @Expose
        public String pos;
        @SerializedName("gen")
        @Expose
        public String gen;
        @SerializedName("syn")
        @Expose
        public List<Syn> syn = null;
        @SerializedName("mean")
        @Expose
        public List<Mean> mean = null;
        @SerializedName("ex")
        @Expose
        public List<Ex> ex = null;


        public String getText() {
            return text;
        }

        public String getGen() {
            return gen;
        }

        public List<Mean> getMeanList() {
            return mean;
        }

        public List<Ex> getExampleList() {
            return ex;
        }

        public List<Syn> getSynonymList() {
            return syn;
        }

        @Override
        public String toString() {
            return "Tr{" +
                    "text='" + text + '\'' +
                    ", pos='" + pos + '\'' +
                    ", gen='" + gen + '\'' +
                    ", syn=" + syn +
                    ", mean=" + mean +
                    ", ex=" + ex +
                    '}';
        }
    }

    public static class Mean {

        @SerializedName("text")
        @Expose
        private String text;

        public String getText() {
            return text;
        }

        @Override
        public String toString() {
            return "Mean{" +
                    "text='" + text + '\'' +
                    '}';
        }
    }

    public static class Tr_ {

        @SerializedName("text")
        @Expose
        private String text;

        public String getText() {
            return text;
        }

        @Override
        public String toString() {
            return "Tr_{" +
                    "text='" + text + '\'' +
                    '}';
        }
    }


    public static class Syn {

        @SerializedName("text")
        @Expose
        private String text;
        @SerializedName("pos")
        @Expose
        private String pos;
        @SerializedName("gen")
        @Expose
        private String gen;

        public String getText() {
            return text;
        }

        public String getGen() {
            return gen;
        }

        @Override
        public String toString() {
            return "Syn{" +
                    "text='" + text + '\'' +
                    ", pos='" + pos + '\'' +
                    ", gen='" + gen + '\'' +
                    '}';
        }
    }
}
