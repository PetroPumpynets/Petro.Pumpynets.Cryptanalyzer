package com.javarush;

import java.util.ArrayList;
import java.util.List;

class Encryption {
    CommandUtilities commandUtilities = new CommandUtilities();

    /*
    Method encrypts the data using a Caesar cipher
    Params: fileData - data for encoding
            key - key value for the Caesar cipher
    Returns: encrypted data
    */
    List<String> encryptData(List<String> fileData, int key) {
        ArrayList<Character> alphabet = commandUtilities.determineAlphabetLanguageForFileData(fileData);
        key = commandUtilities.normalizeKey(alphabet, key);
        List<String> encryptedData = new ArrayList<>();

        for (var str : fileData) {
            String encryptedString = encryptString(str, alphabet, key);
            encryptedData.add(encryptedString);
        }
        return encryptedData;
    }

    /*
    Method encrypts string using a Caesar cipher
    Params: stringValue - string for encoding
            alphabet - alphabet in the language used in the string
            key - key value for the Caesar cipher
    Returns: encrypted string
     */
    String encryptString(String stringValue, ArrayList<Character> alphabet, int key) {
        String encryptedString = "";
        var stringValueArray = stringValue.toCharArray();
        for (int i = 0; i < stringValueArray.length; i++) {
            if (alphabet.contains(stringValueArray[i])) {
                int newPosition = findNewCharPositionForEncryption(stringValueArray[i], alphabet, key);
                encryptedString = encryptedString + alphabet.get(newPosition);
                continue;
            }
            encryptedString = encryptedString + stringValueArray[i];
        }
        return encryptedString;
    }

    /*
    Method finds new char position in alphabet via key using in Caesar cipher
    Params: charValue - char for encoding
            alphabet - alphabet in the language used in the string
            key - key value for the Caesar cipher
    Returns: position for encrypted char
     */
    int findNewCharPositionForEncryption(char charValue, ArrayList<Character> alphabet, int key) {
        int newPosition = key + alphabet.indexOf(charValue);
        if (newPosition >= alphabet.size()) {
            newPosition = newPosition - alphabet.size();
        }
        return newPosition;
    }
}
