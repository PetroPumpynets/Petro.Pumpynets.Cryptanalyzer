package com.javarush;

import java.util.ArrayList;
import java.util.List;

class CommandUtilities {
    AlphabetConstants alphabetArray = new AlphabetConstants();

    /*
    Method determines the language of the data and returns the appropriate alphabet
    Params: fileData - data for encoding/decoding
    Returns: appropriate alphabet
     */
    ArrayList<Character> determineAlphabetLanguageForFileData(List<String> fileData) {
        ArrayList<Character> alphabet = null;
        for (int j = 0; j < fileData.size(); j++) {
            alphabet = checkLineToDetermineAlphabet(fileData.get(j));
            if (alphabet != null) {
                break;
            }
        }
        return alphabet;
    }

    /*
    Method checks which alphabet the string is written in
    Params: stringValue - string for check
    Returns: appropriate alphabet
     */
    ArrayList<Character> checkLineToDetermineAlphabet(String stringValue) {
        ArrayList<Character> alphabet = null;
        char[] stringValueArray = stringValue.toCharArray();
        for (int i = 0; i < stringValueArray.length; i++) {
            alphabet = determineWhichAlphabetContainsCharacter(stringValueArray[i]);
            if (alphabet != null) {
                break;
            }
        }
        return alphabet;
    }

    /*
    Method determines which alphabet contains character
    Params: charValue - character for check
    Returns: appropriate alphabet
     */
    ArrayList<Character> determineWhichAlphabetContainsCharacter(char charValue) {
        ArrayList<Character> alphabet = null;
        if (Character.isLetter(charValue) && alphabetArray.englishAlphabet.contains(charValue)) {
            alphabet = alphabetArray.englishAlphabet;
        } else if (Character.isLetter(charValue) && alphabetArray.ukrainianAlphabet.contains(charValue)) {
            alphabet = alphabetArray.ukrainianAlphabet;
        }
        return alphabet;
    }

    /*
    Method transforms the key so that it does not exceed the alphabet length
    Params: alphabet - the alphabet to be used
            key - initial key value
    Returns: normalized key value
     */
    int normalizeKey(ArrayList<Character> alphabet, int key) {
        if (isKeyNotNegative(key)) {
            while (key >= alphabet.size()) {
                key = key - alphabet.size();
            }
        } else {
            while (isNegativeKeyLessThanNegativeAlphabetSize(alphabet, key)) {
                key = key + alphabet.size();
            }
            key = key + alphabet.size();
        }
        return key;
    }

    /*
    Method returns true if the value of the key is not negative
    Params: key - key value
     */
    boolean isKeyNotNegative(int key) {
        return key >= 0;
    }

    /*
   Method returns true if the user-specified negative value of the key is less than the negative length of the alphabet
   Params: alphabet - the alphabet to be used
           key - key value
    */
    boolean isNegativeKeyLessThanNegativeAlphabetSize(ArrayList<Character> alphabet, int key) {
        return key <= -alphabet.size();
    }
}
