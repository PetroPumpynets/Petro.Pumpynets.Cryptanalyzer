package com.javarush;

import java.util.ArrayList;
import java.util.List;

public class Decryption {
    CommandUtilities commandUtilities = new CommandUtilities();

    /*
    Method decrypts the data using a Caesar cipher
    Params: fileData - data for encoding/decoding
            key - key value for the Caesar cipher
    Returns: decrypted data
     */
    public List<String> decryptData(List<String> fileData, int key) {
        ArrayList<Character> alphabet = commandUtilities.determineAlphabetLanguage(fileData);
        key = commandUtilities.normalizeKey(alphabet, key);
        List<String> decryptedData = new ArrayList<>();

        for (var str : fileData) {
            String newStr = "";
            for (int i = 0; i < str.length(); i++) {
                if (alphabet.contains(str.toCharArray()[i])) {
                    int newPosition = alphabet.indexOf(str.toCharArray()[i]) - key;
                    if (newPosition < 0) {
                        newPosition = newPosition + alphabet.size();
                    }
                    str.toCharArray()[i] = alphabet.get(newPosition);
                    newStr = newStr + alphabet.get(newPosition);
                    continue;
                }
                newStr = newStr + str.toCharArray()[i];
            }
            decryptedData.add(newStr);
        }
        return decryptedData;
    }
}

