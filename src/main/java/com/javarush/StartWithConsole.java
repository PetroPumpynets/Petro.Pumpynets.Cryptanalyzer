package com.javarush;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class StartWithConsole {
    /*
    Method receives parameters for the application through the console
    Returns: parameters for application
     */
    public String[] getArgs() {
        String[] args = new String[3];
        System.out.println("Write one of the commands and press enter.\n [e]ncript | [d]ecript | [b]rute force : ");
        Scanner scanner = new Scanner(System.in);
        args[0] = scanner.nextLine();
        while (!args[0].equals("e") && !args[0].equals("d") && !args[0].equals("b")) {
            System.out.println("Invalid team selection! \nWrite one of the commands and press enter." +
                    "\n [e]ncript | [d]ecript | [b]rute force : ");
            args[0] = scanner.nextLine();
        }

        System.out.println("Enter the file path : ");
        args[1] = scanner.nextLine().replace("\"","");
        while (!Files.exists(Path.of(args[1]))) {
            System.out.println("Invalid path! No file exists at this link. \nEnter the file path : ");
            args[1] = scanner.nextLine().replace("\"","");
        }

        if (!args[0].equals("b")) {
            System.out.println("Enter the key : ");
            args[2] = scanner.nextLine();

            var keyToArray = args[2].toCharArray();
            boolean isKey = false;
            for (int i = 0; i < keyToArray.length; i++) {
                if (!(Character.isDigit(keyToArray[i]) || (i == 0 && (keyToArray[i] == '+' || keyToArray[i] == '-')))){
                    isKey = true;
                }
            }
            while (isKey) {
                System.out.println("Invalid path! No file exists at this link. \nEnter the file path : ");
                args[2] = scanner.nextLine();
                keyToArray = args[2].toCharArray();
                isKey = false;
                for (int i = 0; i < keyToArray.length; i++) {
                    if (!(Character.isDigit(keyToArray[i]) || (i == 0 && (keyToArray[i] == '+' || keyToArray[i] == '-')))) {
                        isKey = true;
                    }
                }
            }
        }
        return args;
    }
}
