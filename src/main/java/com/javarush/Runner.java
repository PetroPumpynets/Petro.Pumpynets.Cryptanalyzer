package com.javarush;

import java.util.List;

public class Runner {
    Encryption encryption = new Encryption();
    Decryption decryption = new Decryption();
    BruteForce bruteForce = new BruteForce();
    FileUtilities fileUtilities = new FileUtilities();
    ArgConstants argConstants = new ArgConstants();
    ConsoleArgs consoleArgs = new ConsoleArgs();
    JARArgs jarArgs = new JARArgs();

    /*
    Method performs the task according to the command passed in the parameters
    Params: args - parameters for application
    */
    public void runCommand(String[] args) {
        List<String> fileData = fileUtilities.getDataFromFile(args[argConstants.filePath]);

        if (consoleArgs.isCommandBruteForce(args[argConstants.commandName]) ||
                jarArgs.isCommandBruteForce(args[argConstants.commandName])) {
            var resultKey = bruteForce.bruteForceKey(fileData);
            System.out.println(resultKey);
            var resultData = decryption.decryptData(fileData, Integer.parseInt(resultKey));
            fileUtilities.writeDecryptedDataToNewFile(args[argConstants.filePath], resultData);
        }

        if (consoleArgs.isCommandEncrypt(args[argConstants.commandName]) ||
                jarArgs.isCommandEncrypt(args[argConstants.commandName])) {
            var resultData = encryption.encryptData(fileData, Integer.parseInt(args[argConstants.key]));
            fileUtilities.writeEncryptedDataToNewFile(args[argConstants.filePath], resultData);
        }

        if (consoleArgs.isCommandDecrypt(args[argConstants.commandName]) ||
                jarArgs.isCommandDecrypt(args[argConstants.commandName])) {
            var resultData = decryption.decryptData(fileData, Integer.parseInt(args[argConstants.key]));
            fileUtilities.writeDecryptedDataToNewFile(args[argConstants.filePath], resultData);
        }
    }
}
