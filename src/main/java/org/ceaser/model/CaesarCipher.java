package org.ceaser.model;

import org.ceaser.setting.LettersAlphabet;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CaesarCipher {
    private String dateAlphabet;
    private String text;
    private String filePath;
    private int retreat;
    private char[] dataArrayAlphabetToUp;
    private char[] dataArrayAlphabetToLower;
    private char[] dataText;

   private FileService fileService = new FileService();
    public CaesarCipher(int key, String filePath) {
        this.filePath = filePath;
        this.text = fileService.readFile(filePath);
        this.retreat = key;

        setTheAlphabet(text);
        setDataArrayAlphabet();

        this.dataText = text.toCharArray();
    }
    public CaesarCipher(String filePath) {
        this.filePath = filePath;
        this.text = fileService.readFile(filePath);
        setTheAlphabet(text);
        setDataArrayAlphabet();

        this.dataText = text.toCharArray();
    }

    private void setDataArrayAlphabet(){
        this.dataArrayAlphabetToUp = LettersAlphabet.DATA_ALPHABET.getDataAlphabetToUp(dateAlphabet).toCharArray();
        this.dataArrayAlphabetToLower = LettersAlphabet.DATA_ALPHABET.getDataAlphabetToLower(dateAlphabet).toCharArray();
    }

    private void setTheAlphabet(String text){
        TextService textService = new TextService();
        if (textService.isTextLanguageEnglish(text)) {
            this.dateAlphabet = LettersAlphabet.ENGLISH.getDate();
        } else if (textService.isTextLanguageUkraine(text)) {
            this.dateAlphabet = LettersAlphabet.UKRAINIAN.getDate();
        }
    }

    public String encryption() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < dataText.length; i++) {
            for (int y = 0; y < dataArrayAlphabetToUp.length; y++) {
                int indexNewChar;
                if (dataArrayAlphabetToLower[y] == dataText[i]) {
                    indexNewChar = getIndexOffsetByKeyEncrypt(y);
                    result.append(dataArrayAlphabetToLower[indexNewChar]);
                    break;
                } else if (dataArrayAlphabetToUp[y] == dataText[i]) {
                    indexNewChar = getIndexOffsetByKeyEncrypt(y);
                    result.append(dataArrayAlphabetToUp[indexNewChar]);
                    break;
                }
            }
        }
        System.out.println(result);
        return  result.toString();
    }

    public String decryption() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < dataText.length; i++) {
            for (int y = 0; y < dataArrayAlphabetToUp.length; y++) {
                int indexNewChar;
                if (dataArrayAlphabetToLower[y] == dataText[i]) {
                    indexNewChar = getIndexOffsetByKeyDecrypt(y);
                    result.append(dataArrayAlphabetToLower[indexNewChar]);
                    break;
                } else if (dataArrayAlphabetToUp[y] == dataText[i]) {
                    indexNewChar = getIndexOffsetByKeyDecrypt(y);
                    result.append(dataArrayAlphabetToUp[indexNewChar]);
                    break;
                }
            }
        }
        System.out.println(result);
        return  result.toString();
    }

    private int getIndexOffsetByKeyEncrypt(int start){
        int indexAlphabet = start;
        for(int x = 0; x < retreat; x++){
            if(indexAlphabet == dataArrayAlphabetToUp.length-1){
                indexAlphabet = 0;
            }else {
                indexAlphabet++;
            }
            if(x == retreat-1){
                return indexAlphabet;
            }
        }
        return 0;
    }

    private int getIndexOffsetByKeyDecrypt(int start){
        int indexAlphabet = start;
        for(int x = 0; x < retreat; x++){
            if(indexAlphabet == 0){
                indexAlphabet = dataArrayAlphabetToUp.length-1;
            }else {
                indexAlphabet--;
            }
            if(x == retreat-1){
                return indexAlphabet;
            }
        }
        return 0;
    }

    private int getIndexOffsetByKeyBrutForce(int start, int key){
        int indexAlphabet = start;
        for(int x = 0; x < key; x++){
            if(indexAlphabet == 0){
                indexAlphabet = dataArrayAlphabetToUp.length-1;
            }else {
                indexAlphabet--;
            }
            if(x == key-1){
                return indexAlphabet;
            }
        }
        return 0;
    }

    List<String> decipheredVariantsOfText;
    public String brutForce() {
        int startKey = 0;
        int endKey = 30;
        brutForceAttack(startKey,endKey);
           AnalysisManager analysisManager = new AnalysisManager(dateAlphabet);
           if(analysisManager.start(decipheredVariantsOfText) == null){
               startKey =  endKey;
               endKey = startKey +30;
               brutForceAttack(startKey, endKey);
           }
           else {
               System.out.println("BRUTE_FORCE пройшла УСПІШНО. Ключь: ");
               System.out.println("_______________________________");
               System.out.println(analysisManager.getResultBrutForceAttack());
               System.out.println("_______________________________");
               return analysisManager.start(decipheredVariantsOfText);
           }
           return  null;
    }

    private void brutForceAttack(int startKey, int endKey){
        decipheredVariantsOfText = new ArrayList<>();
        for (int key = startKey; key < endKey; key++) {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < dataText.length; i++) {
                for (int y = 0; y < dataArrayAlphabetToUp.length; y++) {
                    int indexNewChar;
                    if (dataArrayAlphabetToLower[y] == dataText[i]) {
                        indexNewChar = getIndexOffsetByKeyBrutForce(y, key);
                        result.append(dataArrayAlphabetToLower[indexNewChar]);
                        break;
                    } else if (dataArrayAlphabetToUp[y] == dataText[i]) {
                        indexNewChar = getIndexOffsetByKeyBrutForce(y, key);
                        result.append(dataArrayAlphabetToUp[indexNewChar]);
                        break;
                    }
                }
            }
            decipheredVariantsOfText.add(result.toString());
        }
    }

}
