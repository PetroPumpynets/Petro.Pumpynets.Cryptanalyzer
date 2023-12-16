package com.javarush;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

class ConsoleArgs {
    ArgConstants argConstants = new ArgConstants();
    Scanner scanner = new Scanner(System.in);

    /*
    Method receives parameters for the application through the console
    Returns: parameters for application
     */
    String[] getArgs() {
        String[] args = new String[argConstants.argCount];
        args[argConstants.commandName] = getCommandFromConsole();
        args[argConstants.filePath] = getPathFromConsole();
        args[argConstants.key] = getKeyFromConsole(args[argConstants.commandName]);

        return args;
    }

    String getCommandFromConsole() {
        String commandName = "";
        System.out.println("Write one of the commands and press enter.\n [e]ncrypt | [d]ecrypt | [b]rute force : ");
        commandName = scanner.nextLine();
        while (!isInputDataEqualsExistingCommand(commandName)) {
            System.out.println("Invalid team selection! \nWrite one of the commands and press enter." +
                    "\n [e]ncrypt | [d]ecrypt | [b]rute force : ");
            commandName = scanner.nextLine();
        }
        return commandName;
    }

    /*
    Method returns true if input data equals one of existing commands
    Params: inputCommandName - input command name
     */
    boolean isInputDataEqualsExistingCommand(String inputCommandName) {
        return isCommandEncrypt(inputCommandName) || isCommandDecrypt(inputCommandName) ||
                isCommandBruteForce(inputCommandName);
    }

    boolean isCommandEncrypt(String inputCommandName) { return "e".equals(inputCommandName); }

    boolean isCommandDecrypt(String inputCommandName) {
        return "d".equals(inputCommandName);
    }

    boolean isCommandBruteForce(String inputCommandName) {
        return "b".equals(inputCommandName);
    }

    String getPathFromConsole() {
        String path = "";
        System.out.println("Enter the file path : ");
        path = scanner.nextLine().replace("\"", "");
        while (!isFileExist(path)) {
            System.out.println("Invalid path! No file exists at this link. \nEnter the file path : ");
            path = scanner.nextLine().replace("\"", "");
        }
        return path;
    }

    boolean isFileExist(String filePath) {
        return Files.exists(Path.of(filePath));
    }

    String getKeyFromConsole(String commandName) {
        String key = "";
        if (!isCommandBruteForce(commandName)) {
            System.out.println("Enter the key : ");
            key = scanner.nextLine();
            while (!isKeyNumber(key)) {
                System.out.println("Invalid path! No file exists at this link. \nEnter the file path : ");
                key = scanner.nextLine();
            }
        }
        return key;
    }

    /*
    Method returns true if input key is number (including numbers that begin with the symbols + and -)
    Params: key - input key
     */
    boolean isKeyNumber(String key) {
        var keyCharArray = key.toCharArray();
        boolean isKey = false;
        for (int i = 0; i < keyCharArray.length; i++) {
            if (Character.isDigit(keyCharArray[i]) || isValueStartWithPlusOrMinusCharacter(key)) {
                isKey = true;
            }
        }
        return isKey;
    }

    boolean isValueStartWithPlusOrMinusCharacter(String key) {
        return key.startsWith("+") || key.startsWith("-");
    }
}
