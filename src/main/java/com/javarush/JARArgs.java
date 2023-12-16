package com.javarush;

import java.nio.file.Files;
import java.nio.file.Path;

import static jdk.internal.net.http.common.Utils.close;


class JARArgs {
    ArgConstants argConstants = new ArgConstants();
    ConsoleArgs consoleArgs = new ConsoleArgs();

    /*
    Method checks whether the received parameters match the program criteria
    Params: args - parameters for application
     */
    void checkArgs(String[] args) {
        checkCommand(args[argConstants.commandName]);
        checkFilePath(args[argConstants.filePath]);

        if (isKeyRequired(args)) {
            checkKey(args[argConstants.key]);
        }
    }

    boolean isKeyRequired(String[] args) {
        return !isCommandBruteForce(args[argConstants.commandName]);
    }

    void checkCommand(String commandValue) {
        if (!isCommandValueEqualsExistingCommand(commandValue)) {
            System.out.println("You entered the wrong command - " + commandValue +
                    ". Rerun the jar file with the correct settings.");
            close();
        }
    }

    /*
    Method returns true if command value equals one of existing commands
    Params: commandValue - input command name
     */
    boolean isCommandValueEqualsExistingCommand(String commandValue) {
        return isCommandEncrypt(commandValue) || isCommandDecrypt(commandValue) ||
                isCommandBruteForce(commandValue);
    }

    boolean isCommandEncrypt(String commandValue) {
        return CommandEnum.ENCRYPT.toString().equals(commandValue);
    }

    boolean isCommandDecrypt(String commandValue) {
        return CommandEnum.DECRYPT.toString().equals(commandValue);
    }

    boolean isCommandBruteForce(String commandValue) {
        return CommandEnum.BRUTE_FORCE.toString().equals(commandValue);
    }

    void checkFilePath(String filePath) {
        if (!consoleArgs.isFileExist(filePath)) {
            System.out.println("The linked file does not exist (link - " + filePath +
                    "). Rerun the jar file with the correct settings.");
            close();
        }
    }

    void checkKey(String key) {
        if (!consoleArgs.isKeyNumber(key)) {
            System.out.println("Key (" + key + ") is not digit. Rerun the jar file with the correct settings.");
            close();
        }
    }
}
