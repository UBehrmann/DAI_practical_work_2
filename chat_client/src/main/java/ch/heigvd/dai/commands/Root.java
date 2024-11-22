package ch.heigvd.dai.commands;

import picocli.CommandLine;

@CommandLine.Command(
        name = "chat",
        description = "Chat client for interacting with the server.",
        subcommands = {Client.class} // Link to the new Client implementation
)
public class Root implements Runnable {

    @Override
    public void run() {
        // Display help by default
        CommandLine.usage(this, System.out);
    }
}
