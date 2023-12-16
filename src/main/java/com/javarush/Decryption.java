package com.javarush;

import java.util.ArrayList;
import java.util.List;

class Decryption {
    CommandUtilities commandUtilities = new CommandUtilities();

    /*
    Method decrypts the data using a Caesar cipher
    Params: fileData - data for decoding
            key - key value for the Caesar cipher
    Returns: decrypted data
     */
    List<String> decryptData(List<String> fileData, int key) {
        ArrayList<Character> alphabet = commandUtilities.determineAlphabetLanguageForFileData(fileData);
        key = commandUtilities.normalizeKey(alphabet, key);
        List<String> decryptedData = new ArrayList<>();

        for (var str : fileData) {
            String decryptedString = decryptString(str, alphabet, key);
            decryptedData.add(decryptedString);
        }
        return decryptedData;
    }

    /*
    Method decrypts string using a Caesar cipher
    Params: stringValue - string for decoding
            alphabet - alphabet in the language used in the string
            key - key value for the Caesar cipher
    Returns: decrypted string
     */
    String decryptString(String stringValue, ArrayList<Character> alphabet, int key) {
        String decryptedString = "";
        var stringValueArray = stringValue.toCharArray();
        for (int i = 0; i < stringValueArray.length; i++) {
            if (alphabet.contains(stringValueArray[i])) {
                int newPosition = findNewCharPositionForDecryption(stringValueArray[i], alphabet, key);
                decryptedString = decryptedString + alphabet.get(newPosition);
                continue;
            }
            decryptedString = decryptedString + stringValueArray[i];
        }
        return decryptedString;
    }

    /*
    Method finds new char position in alphabet via key using in Caesar cipher
    Params: charValue - char for decoding
            alphabet - alphabet in the language used in the string
            key - key value for the Caesar cipher
    Returns: position for decrypted char
     */
    int findNewCharPositionForDecryption(char charValue, ArrayList<Character> alphabet, int key) {
        int newPosition = alphabet.indexOf(charValue) - key;
        if (newPosition < 0) {
            newPosition = newPosition + alphabet.size();
        }
        return newPosition;
    }
}

