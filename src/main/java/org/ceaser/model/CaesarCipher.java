package org.ceaser.model;

import org.ceaser.setting.LettersAlphabet;

import java.util.ArrayList;
import java.util.List;

public class CaesarCipher {
    private String alphabet;
    private String text;
    private String filePath;
    private int retreat;
    private char[] arrayAlphabetToUp;
    private char[] arrayAlphabetToLower;
    private char[] arrayLettersText;

   private FileService fileService = new FileService();
    public CaesarCipher(int key, String filePath) {
        this.filePath = filePath;
        this.text = fileService.readFile(filePath);
        this.retreat = key;

        setTheAlphabet(text);
        setDataArrayAlphabet();

        this.arrayLettersText = text.toCharArray();
    }
    public CaesarCipher(String filePath) {
        this.filePath = filePath;
        this.text = fileService.readFile(filePath);
        setTheAlphabet(text);
        setDataArrayAlphabet();

        this.arrayLettersText = text.toCharArray();
    }

    private void setDataArrayAlphabet(){
        this.arrayAlphabetToUp = LettersAlphabet.ALPHABET.getAlphabetToUp(alphabet).toCharArray();
        this.arrayAlphabetToLower = LettersAlphabet.ALPHABET.getAlphabetToLower(alphabet).toCharArray();
    }

    private void setTheAlphabet(String text){
        TextService textService = new TextService();
        if (textService.isTextLanguageEnglish(text)) {
            this.alphabet = LettersAlphabet.ENGLISH.getDate();
        } else if (textService.isTextLanguageUkraine(text)) {
            this.alphabet = LettersAlphabet.UKRAINIAN.getDate();
        }
    }

    public String encryption() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < arrayLettersText.length; i++) {
            for (int y = 0; y < arrayAlphabetToUp.length; y++) {
                int indexNewChar;
                if (arrayAlphabetToLower[y] == arrayLettersText[i]) {
                    indexNewChar = getIndexOffsetByKeyEncrypt(y);
                    result.append(arrayAlphabetToLower[indexNewChar]);
                    break;
                } else if (arrayAlphabetToUp[y] == arrayLettersText[i]) {
                    indexNewChar = getIndexOffsetByKeyEncrypt(y);
                    result.append(arrayAlphabetToUp[indexNewChar]);
                    break;
                }
            }
        }
        System.out.println(result);
        return  result.toString();
    }

    public String decryption() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < arrayLettersText.length; i++) {
            for (int y = 0; y < arrayAlphabetToUp.length; y++) {
                int indexNewChar;
                if (arrayAlphabetToLower[y] == arrayLettersText[i]) {
                    indexNewChar = getIndexOffsetByKeyDecrypt(y);
                    result.append(arrayAlphabetToLower[indexNewChar]);
                    break;
                } else if (arrayAlphabetToUp[y] == arrayLettersText[i]) {
                    indexNewChar = getIndexOffsetByKeyDecrypt(y);
                    result.append(arrayAlphabetToUp[indexNewChar]);
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
            if(indexAlphabet == arrayAlphabetToUp.length-1){
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
                indexAlphabet = arrayAlphabetToUp.length-1;
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
                indexAlphabet = arrayAlphabetToUp.length-1;
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


    public String brutForce(int startKey, int endKey) {
        brutForceAttack(startKey,endKey);
           AnalysisManager analysisManager = new AnalysisManager(alphabet);
           if(analysisManager.start(decipheredVariantsOfText) == null){
               startKey =  endKey;
               endKey = startKey +30;
           }
           else {
               System.out.println(endKey);
               System.out.println("BRUTE_FORCE пройшла УСПІШНО. Ключь: ");
               System.out.println("_______________________________");
               System.out.println(analysisManager.getResultBrutForceAttack());
               System.out.println("_______________________________");
               return analysisManager.start(decipheredVariantsOfText);
           }
           return analysisManager.start(decipheredVariantsOfText);
    }

    private void brutForceAttack(int startKey, int endKey){
        decipheredVariantsOfText = new ArrayList<>();
        for (int key = startKey; key < endKey; key++) {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < arrayLettersText.length; i++) {
                for (int y = 0; y < arrayAlphabetToUp.length; y++) {
                    int indexNewChar;
                    if (arrayAlphabetToLower[y] == arrayLettersText[i]) {
                        indexNewChar = getIndexOffsetByKeyBrutForce(y, key);
                        result.append(arrayAlphabetToLower[indexNewChar]);
                        break;
                    } else if (arrayAlphabetToUp[y] == arrayLettersText[i]) {
                        indexNewChar = getIndexOffsetByKeyBrutForce(y, key);
                        result.append(arrayAlphabetToUp[indexNewChar]);
                        break;
                    }
                }
            }
            decipheredVariantsOfText.add(result.toString());
        }
    }

}
