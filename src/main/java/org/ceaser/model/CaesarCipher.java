package org.ceaser.model;

import org.ceaser.setting.DateAlphabet;

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
        setTheAlphabet(text);
        checkSelectKey(key);
        this.dataArrayAlphabetToUp = dateAlphabet.toUpperCase().toCharArray();
        this.dataArrayAlphabetToLower = dateAlphabet.toLowerCase().toCharArray();

        this.dataText = text.toCharArray();
    }
    public CaesarCipher(String filePath) {
        this.text = fileService.readFile(filePath);
        setTheAlphabet(text);
        this.dataArrayAlphabetToUp = this.dateAlphabet.toUpperCase().toCharArray();
        this.dataArrayAlphabetToLower = this.dateAlphabet.toLowerCase().toCharArray();

        this.dataText = this.text.toCharArray();
    }

    private void setTheAlphabet(String text){
        TextService textService = new TextService();
        if (textService.isTextLanguageEnglish(text)) {
            this.dateAlphabet = DateAlphabet.ENGLISH.getDate();
        } else if (textService.isTextLanguageUkraine(text)) {
            this.dateAlphabet = DateAlphabet.UKRAINIAN.getDate();
        }
    }

    private void checkSelectKey(int key){
        if(key <= 0){
            this.retreat = generationRandomKey();
        }else {
            this.retreat = key;
        }
    }

    private int generationRandomKey(){
        int key;
        int maxKeyValue = 264;
        int minKeyValue = 1;
        maxKeyValue -= minKeyValue;
        key = (int) (Math.random() * ++maxKeyValue) + minKeyValue;
        return  key;
    }

    public void encryption() {
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
        new FileService().writerFile(result.toString(), filePath, "\\[ENCRYPTED].txt");
    }

    public void decryption() {
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
        new FileService().writerFile(result.toString(), filePath, "\\[DECRYPTED].txt");
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

    public void brutForce() {

    }
}
