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
           // System.out.println(element+ " "+collectionOfResultsAnalysis.get(element));
            calculationOfPointsOfElements(element);
        }
        return searchForTheMaximumPoint();
    }



    private String searchForTheMaximumPoint(){
        int maxPoint = 0;
        String result = "";
        for(String element : collectionOfResultsAnalysis.keySet()){
            int pointElement = collectionOfResultsAnalysis.get(element);
            if(pointElement > maxPoint){
                maxPoint =  pointElement;
                result = element;

            }
        }
        if(maxPoint < result.length()){
            return null;
        }
        this.getTextResultBrutForceAttack = result;
        return result;
    }


    private String getTextResultBrutForceAttack;
    public String getResultBrutForceAttack() {
        return getTextResultBrutForceAttack;
    }


    private void calculationOfPointsOfElements(String element){
        sentenceConstructionCheck(element);

        checkIsCorrectlyIsSpecialCharacters(element);

        checkTheSpellingOfTheWord(element);
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
    private final char[] END_PUNCTUATION = {'.','!','?',':'};
    private final char SPACE = ' ';

    private List<String> separatingSentences(String element){
        List<String> resultList = new ArrayList<>();
        for(int i = 0; i < END_PUNCTUATION.length; i++){
            StringTokenizer stringTokenizer = new StringTokenizer(element,String.valueOf(END_PUNCTUATION[i])+SPACE);
            while (stringTokenizer.hasMoreTokens()){
                resultList.add(stringTokenizer.nextToken()+ END_PUNCTUATION[i]);
            }
        }
        return resultList;
    }

    private final char[] SPECIAL_CHARACTERS = {'.', ',', '!', '?', ':'};
    private void checkIsCorrectlyIsSpecialCharacters(String element){
        final char[] arrayLettersElement = element.toCharArray();

        for(int i = 0; i < arrayLettersElement.length; i++){
            if(i == arrayLettersElement.length-2){
                break;
            }
            for(int j = 0; j < SPECIAL_CHARACTERS.length; j++){
                if(arrayLettersElement[i] == SPECIAL_CHARACTERS[j] && arrayLettersElement[i+1] == SPACE){
                    addPointResultsAnalysis(1,element);
                }
            }
        }
    }

    private final char[] SPECIAL_CHARACTERS_ALL = LettersAlphabet.SPECIAL_CHARACTERS_ALL.getDate().toCharArray();
    private void checkTheSpellingOfTheWord(String element){
        List<String> words = splitWords(element);
        for(String word: words){
            char firstLetter = word.charAt(0);
            for(int i = 0; i < SPECIAL_CHARACTERS_ALL.length; i++){
                if(firstLetter != SPECIAL_CHARACTERS_ALL[i]){
                    addPointResultsAnalysis(1,element);
                }
                if(!(word.length() > 6) && !(word.length() < 3) ){
                    addPointResultsAnalysis(1,element);
                }
            }
        }
    }

    private List<String>splitWords(String element){
        List<String> words = new ArrayList<>();
        StringTokenizer stringTokenizer = new StringTokenizer(element," ");
        while (stringTokenizer.hasMoreTokens()){
            words.add(stringTokenizer.nextToken());
        }
        return words;
    }


    private void addPointResultsAnalysis(int point, String element){
        for(String c : collectionOfResultsAnalysis.keySet()){
            if(c.equals(element)){
                collectionOfResultsAnalysis.put(element,collectionOfResultsAnalysis.get(c)+point);
            }
        }
    }

    private void deletePointResultsAnalysis(int point, String element){
        for(String c : collectionOfResultsAnalysis.keySet()){
            if(c.equals(element)){
                collectionOfResultsAnalysis.put(element,collectionOfResultsAnalysis.get(c)-point);
            }
        }
    }
    private void collectionOfResultsAnalysisPutAll(List<String> elements){
        for(String element : elements){
            collectionOfResultsAnalysis.put(element,0);
        }
    }
}
