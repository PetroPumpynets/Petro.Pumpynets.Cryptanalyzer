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
    public static String[] getArgsFromStartMethod(String[] args) {
        if (args.length > 0) {
            if (args.length == 3 || (args[0].equals(CommandEnum.BRUTE_FORCE.toString()) && args.length == 2)) {
                StartWithJAR startWithJAR = new StartWithJAR();
                startWithJAR.checkArgs(args);
                return args;
            } else {
                StartWithConsole startWithConsole = new StartWithConsole();
                return startWithConsole.getArgs();
            }
        } else {
            StartWithConsole startWithConsole = new StartWithConsole();
            return startWithConsole.getArgs();
        }
    }
}


