package com.javarush;

import java.util.List;

public class Runner {
    Encryption encryption = new Encryption();
    Decryption decryption = new Decryption();
    BruteForce bruteForce = new BruteForce();
    FileUtilities fileUtilities = new FileUtilities();

    /*
    Method performs the task according to the command passed in the parameters
    Params: args - parameters for application
    */
    public void runCommand(String[] args) {
        List<String> fileData = fileUtilities.getDataFromFile(args[1]);

        if (args[0].equals(CommandEnum.BRUTE_FORCE.toString()) || args[0].equals("b")) {
            var resultKey = bruteForce.bruteForceKey(fileData);
            System.out.println(resultKey);
            var resultData = decryption.decryptData(fileData, Integer.parseInt(resultKey));
            fileUtilities.writeDataToFile(args[1].replace(".txt","") + "[DECRYPTED].txt",
                    resultData);
        }

        if (args[0].equals(CommandEnum.ENCRYPT.toString()) || args[0].equals("e")) {
            var resultData = encryption.encryptData(fileData, Integer.parseInt(args[2]));
            fileUtilities.writeDataToFile(args[1].replace(".txt","") + "[ENCRYPTED].txt",
                    resultData);
        }

        if (args[0].equals(CommandEnum.DECRYPT.toString()) || args[0].equals("d")) {
            var resultData = decryption.decryptData(fileData, Integer.parseInt(args[2]));
            fileUtilities.writeDataToFile(args[1].replace(".txt","") + "[DECRYPTED].txt",
                    resultData);
        }
    }
}
