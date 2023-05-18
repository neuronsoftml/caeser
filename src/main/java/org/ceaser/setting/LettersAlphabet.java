package org.ceaser.setting;

public enum LettersAlphabet {
    ENGLISH("ABCDEFGHIJKLMNOPQRSTUVWXYZ"),
    UKRAINIAN("АБВГҐДЕЄЖЗИІЇЙКЛМНОПРСТУФХЦЧШЩьЮЯ"),
    SPECIAL_CHARACTERS_ALL(".,\":-!? «»\\"),
    DATA_ALPHABET("");


    private String date;
    LettersAlphabet(String s) {
        this.date = s;
    }

    public String getDataAlphabetToUp(String dateAlphabet){
        return dateAlphabet + SPECIAL_CHARACTERS_ALL.getDate();
    }

    public String getDataAlphabetToLower(String dateAlphabet){
        return dateAlphabet.toLowerCase() + SPECIAL_CHARACTERS_ALL.getDate().toLowerCase();
    }

    public String getDate(){
        return date;
    }
}
