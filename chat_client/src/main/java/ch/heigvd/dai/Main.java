package ch.heigvd.dai;

import picocli.CommandLine;
import ch.heigvd.dai.commands.Root;

public class Main {
    public static void main(String[] args) {
        int exitCode = new CommandLine(new Root()).execute(args);
        System.exit(exitCode);
    }
}

