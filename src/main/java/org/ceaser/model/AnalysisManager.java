package org.ceaser.model;

import org.ceaser.setting.LettersAlphabet;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class AnalysisManager {
    private String dateAlphabet;
    private TreeMap<String,Integer>collectionOfResultsAnalysis = new TreeMap<>();

    public AnalysisManager(String dateAlphabet){
        this.dateAlphabet = dateAlphabet;
    }
    public String start(List<String> decipheredVariantsOfText){
       return analyzerBruteForce(decipheredVariantsOfText);
    }

    private String analyzerBruteForce(List<String> decipheredVariantsOfText) {
        //Cтворили загальну базу балов варіантів розшифрованого тексту.
        collectionOfResultsAnalysisPutAll(decipheredVariantsOfText);

        for(String element : collectionOfResultsAnalysis.keySet()){
            calculationOfPointsOfElements(element);
        }
        return getResultBrutForceAttack();
    }

    private String getResultBrutForceAttack() {
        int maxPoint = 0;
        String result = "";

        for(String element : collectionOfResultsAnalysis.keySet()){
            int pointElement = collectionOfResultsAnalysis.get(element);
            if(pointElement > maxPoint){
                maxPoint =  pointElement;
                result = element;
            }
        }

        return result;
    }


    private void calculationOfPointsOfElements(String element){
        sentenceConstructionCheck(element);

        checkIsCorrectlyIsSpecialCharacters(element);
    }

    //Здійснює перевірку правильного речення.
    private void sentenceConstructionCheck(String element){
        List<String>sentencesList = separatingSentences(element);

        for(String s : sentencesList){
            if(checkFirstLetterBeginsWithUp(s.charAt(0)) && !checkIsFirstSpecialCharacters(s.charAt(0))){
                addPointResultsAnalysis(1,element);
            }
        }
    }

    //Здійснює перевірку правильного добавлення, пунктуації.
    private boolean checkIsFirstSpecialCharacters(char letter){
        final char[] SPECIAL_CHARACTERS_ALL = LettersAlphabet.SPECIAL_CHARACTERS_ALL.getDate().toCharArray();
        boolean result = false;
        for(int i=0; i < SPECIAL_CHARACTERS_ALL.length; i++){
            if(String.valueOf(letter).equals(String.valueOf(SPECIAL_CHARACTERS_ALL[i]))){
                result = true;
                break;
            }
        }
        return result;
    }

    private boolean checkFirstLetterBeginsWithUp(char letter){
        final char[] CHARACTERS_ALL_UP =  dateAlphabet.toUpperCase().toCharArray();
        boolean result = false;
        for(int i=0; i< CHARACTERS_ALL_UP.length; i++){
            if(String.valueOf(letter).equals(String.valueOf(CHARACTERS_ALL_UP[i]))){
                result = true;
                break;
            }
        }
        return result;
    }
    private final char[] SPECIAL_CHARACTERS = {'.','!','?',':'};
    private final char SPACE = ' ';

    private List<String> separatingSentences(String element){
        List<String> resultList = new ArrayList<>();
        for(int i = 0; i < SPECIAL_CHARACTERS.length; i++){
            StringTokenizer stringTokenizer = new StringTokenizer(element,String.valueOf(SPECIAL_CHARACTERS[i])+SPACE);
            while (stringTokenizer.hasMoreTokens()){
                resultList.add(stringTokenizer.nextToken()+SPECIAL_CHARACTERS[i]);
            }
        }
        return resultList;
    }

    private final char[] SPECIAL_CHARACTERS_ALL = {'.', ',', '!', '?', ':'};
    private void checkIsCorrectlyIsSpecialCharacters(String element){
        final char[] arrayLettersElement = element.toCharArray();

        for(int i = 0; i < arrayLettersElement.length; i++){
            if(i == arrayLettersElement.length-2){
                break;
            }
            for(int j = 0; j < SPECIAL_CHARACTERS_ALL.length; j++){
                if(arrayLettersElement[i] == SPECIAL_CHARACTERS_ALL[j] && arrayLettersElement[i+1] == SPACE){
                    addPointResultsAnalysis(1,element);
                }
            }
        }
    }

    private void addPointResultsAnalysis(int point, String element){
        for(String c : collectionOfResultsAnalysis.keySet()){
            if(c.equals(element)){
                collectionOfResultsAnalysis.put(element,collectionOfResultsAnalysis.get(c)+point);
            }
        }
    }

    private void collectionOfResultsAnalysisPutAll(List<String> elements){
        for(String element : elements){
            collectionOfResultsAnalysis.put(element,0);
        }
    }

      /*private TreeMap<String, Integer> letterFrequency(String element){

        TreeMap<String,Integer> letterFrequency = new TreeMap<>();
        char[] dateAlphabetArray = dateAlphabet.toCharArray();
        char[] elementArray = element.toUpperCase().toCharArray();

        for(int i=0; i < elementArray.length; i++){

            for(int j=0; j < dateAlphabetArray.length; j++){

                if(dateAlphabetArray[j] == elementArray[i]){
                    String key = String.valueOf(dateAlphabetArray[j]);
                    Integer value;
                    if(letterFrequency.get(key) == null){
                        value = 1;
                    }else {
                        value = letterFrequency.get(key)+1;
                    }
                    letterFrequency.put(key, value);
                    break;
                }
            }
        }
        return  letterFrequency;
    }
*/
}
