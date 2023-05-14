package org.ceaser.setting;

public enum LettersAlphabet {
    ENGLISH("ABCDEFGHIJKLMNOPQRSTUVWXYZ.,\":-!? «»\\"),
    UKRAINIAN("АБВГҐДЕЄЖЗИІЇЙКЛМНОПРСТУФХЦЧШЩьЮЯ.,\":-!? «»\\"),
    SPECIAL_CHARACTERS_ALL(".,\":-!? «»\\");

    private String date;
    LettersAlphabet(String s) {
        this.date = s;
    }

    public String getDate(){
        return date;
    }
}
