package org.ceaser.model;

import org.ceaser.setting.LettersAlphabet;

public class TextService {

    public boolean isTextLanguageUkraine(String text){
        if(checkTextLanguageUkraine(text) > checkTextLanguageEnglish(text)){
            return true;
        }
        return false;
    }

    public boolean isTextLanguageEnglish(String text){
        if(checkTextLanguageUkraine(text) < checkTextLanguageEnglish(text)){
            return true;
        }
        return false;
    }

    private int checkTextLanguageUkraine(String text){
        char[] dataArrayAlphabet = LettersAlphabet.UKRAINIAN.getDate().toCharArray();
        char[] dataArrayMessage = text.toCharArray();

        int result = 0;
        for(int i = 0; i < dataArrayMessage.length; i++){
            for(int j = 0; j < dataArrayAlphabet.length; j++){
                if(String.valueOf(dataArrayMessage[i]).equals(String.valueOf(dataArrayAlphabet[j]))){
                    result++;
                    break;
                }
            }
        }
        return  result;
    }

    private int checkTextLanguageEnglish(String text){
        char[] dataArrayAlphabet = LettersAlphabet.ENGLISH.getDate().toCharArray();
        char[] dataArrayMessage = text.toCharArray();

        int result = 0;
        for(int i = 0; i < dataArrayMessage.length; i++){
            for(int j = 0; j < dataArrayAlphabet.length; j++){
                if(String.valueOf(dataArrayMessage[i]).equals(String.valueOf(dataArrayAlphabet[j]))){
                    result++;
                    break;
                }
            }
        }
        return  result;
    }
}
