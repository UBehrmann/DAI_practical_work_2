//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ch.heigvd.dai;

import ch.heigvd.dai.commands.Root;
import picocli.CommandLine;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        int exitCode = (new CommandLine(new Root())).execute(args);
        System.exit(exitCode);
    }
}
