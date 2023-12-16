package com.javarush;

import java.util.ArrayList;
import java.util.List;

class BruteForce {
    CommandUtilities commandUtilities = new CommandUtilities();
    Decryption decryption = new Decryption();

    /*
    Method finds the encryption key for data encoded with a Caesar cipher
    Params: fileData - encoded data
    Returns: encryption key for data encoded with a Caesar cipher
     */
    String bruteForceKey(List<String> fileData) {
        int bruteForceKey = 0;
        ArrayList<Character> alphabet = commandUtilities.determineAlphabetLanguageForFileData(fileData);

        for (int j = 0; j < alphabet.size(); j++) {
            var newFileData = decryption.decryptData(fileData, j);
            if (isDecrypted(newFileData)) {
                bruteForceKey = j;
                break;
            }
        }
        return Integer.toString(bruteForceKey);
    }

    /*
    Checks whether the data is decoded
    Params: fileData - conditionally decoded data
    Returns: true if data is decoded
     */
    boolean isDecrypted(List<String> fileData) {
        int countDotAndSpase = 0;
        int countCommaAndSpase = 0;
        for (var str : fileData) {
            if (str.contains(". ")) {
                countDotAndSpase++;
            }
            if (str.contains(", ")) {
                countCommaAndSpase++;
            }
        }
        if (countCommaAndSpase > 0 && countDotAndSpase > 0) {
            return true;
        }
        return false;
    }
}

