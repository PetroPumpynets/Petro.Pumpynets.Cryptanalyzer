package com.javarush;

import java.util.ArrayList;
import java.util.List;

public class Encryption {
    CommandUtilities commandUtilities = new CommandUtilities();

    /*
    Method encrypts the data using a Caesar cipher
    Params: fileData - data for encoding/decoding
            key - key value for the Caesar cipher
    Returns: encrypted data
     */
    public List<String> encryptData(List<String> fileData, int key) {
        ArrayList<Character> alphabet = commandUtilities.determineAlphabetLanguage(fileData);
        key = commandUtilities.normalizeKey(alphabet, key);
        List<String> encryptedData = new ArrayList<>();

        for (var str : fileData) {
            String newStr = "";
            for (int i = 0; i < str.length(); i++) {
                if (alphabet.contains(str.toCharArray()[i])) {
                    int newPosition = key + alphabet.indexOf(str.toCharArray()[i]);
                    if (newPosition >= alphabet.size()) {
                        newPosition = newPosition - alphabet.size();
                    }
                    newStr = newStr + alphabet.get(newPosition);
                    continue;
                }
                newStr = newStr + str.toCharArray()[i];
            }
            encryptedData.add(newStr);

        }
        return encryptedData;
    }
}
