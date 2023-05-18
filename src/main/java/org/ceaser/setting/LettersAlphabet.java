package org.ceaser.setting;

public enum LettersAlphabet {
    ENGLISH("ABCDEFGHIJKLMNOPQRSTUVWXYZ"),
    UKRAINIAN("АБВГҐДЕЄЖЗИІЇЙКЛМНОПРСТУФХЦЧШЩьЮЯ"),
    SPECIAL_CHARACTERS_ALL(".,\":-!? «»\\"),
    ALPHABET("");


    private String date;
    LettersAlphabet(String s) {
        this.date = s;
    }

    public String getAlphabetToUp(String alphabet){
        return alphabet + SPECIAL_CHARACTERS_ALL.getDate();
    }

    public String getAlphabetToLower(String alphabet){
        return alphabet.toLowerCase() + SPECIAL_CHARACTERS_ALL.getDate().toLowerCase();
    }

    public String getDate(){
        return date;
    }
}
