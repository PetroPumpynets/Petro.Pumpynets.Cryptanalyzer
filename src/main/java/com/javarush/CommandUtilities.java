package com.javarush;

import java.util.ArrayList;
import java.util.List;

public class CommandUtilities {
    Constants alphabetArray = new Constants();
    /*
    Method determines the language of the data and returns the appropriate alphabet
    Params: fileData - data for encoding/decoding
    Returns: appropriate alphabet
     */
    public ArrayList<Character> determineAlphabetLanguage (List<String> fileData) {
        ArrayList<Character> alphabet = null;
        for (int j = 0; j < fileData.size(); j++) {
            char[] firstLine = fileData.get(j).toCharArray();
            for (int i = 0; i < firstLine.length; i++) {
                if (Character.isLetter(firstLine[i]) && alphabetArray.englishAlphabet.contains(firstLine[i])) {
                    alphabet = alphabetArray.englishAlphabet;
                    break;
                } else if (Character.isLetter(firstLine[i]) && alphabetArray.ukrainianAlphabet.contains(firstLine[i])) {
                    alphabet = alphabetArray.ukrainianAlphabet;
                    break;
                }
            }
            if (alphabet != null){
                break;
            }
        }
        return alphabet;
    }

    /*
    Method transforms the key so that it does not exceed the alphabet length
    Params: alphabet - the alphabet to be used
            key - initial key value
    Returns: normalized key value
     */
    public int normalizeKey(ArrayList<Character> alphabet, int key){
        if (key >= 0){
            while (key >= alphabet.size()){
                key = key - alphabet.size();
            }
        }else {
            while (key<= -alphabet.size()){
                key = key + alphabet.size();
            }
        key = key + alphabet.size();
        }
        return key;
    }
}
