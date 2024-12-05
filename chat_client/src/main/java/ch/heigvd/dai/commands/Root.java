//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ch.heigvd.dai.commands;

import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(
        name = "chat",
        description = {"Chat client for interacting with the server."},
        subcommands = {Client.class}
)
public class Root implements Runnable {
    public Root() {
    }

    public void run() {
        CommandLine.usage(this, System.out);
    }
}
