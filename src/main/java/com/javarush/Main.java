package com.javarush;

public class Main {

    public static void main(String[] args) {
        Runner runner = new Runner();
        runner.runCommand(getArgsFromStartMethod(args));
    }

    /*
    Method defines how to get the parameters and returns them
    Params: args - available parameters when starting the program
    Returns: parameters for application
    */
    static String[] getArgsFromStartMethod(String[] args) {
        if (args.length > 0) {
            if (areThereEnoughParametersToRun(args)) {
                JARArgs jarArgs = new JARArgs();
                jarArgs.checkArgs(args);
                return args;
            } else {
                ConsoleArgs consoleArgs = new ConsoleArgs();
                return consoleArgs.getArgs();
            }
        } else {
            ConsoleArgs consoleArgs = new ConsoleArgs();
            return consoleArgs.getArgs();
        }
    }

    static boolean areThereEnoughParametersToRun(String[] args) {
        ConsoleArgs consoleArgs = new ConsoleArgs();
        ArgConstants argConstants = new ArgConstants();
        boolean areAllParameters = args.length == argConstants.argCount;
        boolean areAllParametersForBrutForce = consoleArgs.isCommandBruteForce(args[argConstants.commandName]) &&
                args.length == argConstants.argCountForBrutForce;
        return areAllParameters || areAllParametersForBrutForce;
    }
}


