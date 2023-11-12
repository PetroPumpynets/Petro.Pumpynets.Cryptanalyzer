package com.javarush;

import java.nio.file.Files;
import java.nio.file.Path;

import static jdk.internal.net.http.common.Utils.close;


public class StartWithJAR {
    /*
    Method checks whether the received parameters match the program criteria
    Params: args - parameters for application
     */
    public void checkArgs(String[] args) {
        if (!(args[0].equals(CommandEnum.BRUTE_FORCE.toString()) || args[0].equals(CommandEnum.DECRYPT.toString()) ||
                args[0].equals(CommandEnum.ENCRYPT.toString()))) {
            System.out.println("You entered the wrong command - " + args[0] +
                    ". Rerun the jar file with the correct settings.");
            close();
        }

        if (!Files.exists(Path.of(args[1]))) {
            System.out.println("The linked file does not exist (link - " + args[1] +
                    "). Rerun the jar file with the correct settings.");
            close();
        }

        if (!args[0].equals(CommandEnum.BRUTE_FORCE.toString()) && args.length == 3) {
            var keyToArray = args[2].toCharArray();
            boolean isKey = false;
            for (int i = 0; i < keyToArray.length; i++) {
                if (!(Character.isDigit(keyToArray[i]) || (i == 0 && (keyToArray[i] == '+' || keyToArray[i] == '-')))){
                    isKey = true;
                }
            }
            if (isKey) {
                System.out.println("Key (" + args[2] + ") is not digit. Rerun the jar file with the correct settings.");
                close();
            }
        }
    }
}
