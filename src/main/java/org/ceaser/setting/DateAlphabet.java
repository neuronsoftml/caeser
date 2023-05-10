package org.ceaser.setting;

public enum DateAlphabet {
    ENGLISH("ABCDEFGHIJKLMNOPQRSTUVWXYZ.,\":-!? «»\\"),
    UKRAINIAN("АБВГҐДЕЄЖЗИІЇЙКЛМНОПРСТУФХЦЧШЩьЮЯ.,\":-!? «»\\"),
    SPECIAL_CHARACTERS(",.\":-!? ");

    private String date;
    DateAlphabet(String s) {
        this.date = s;
    }

    public String getDate(){
        return date;
    }
}
