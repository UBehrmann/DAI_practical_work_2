package ch.heigvd.dai.commands;

import java.io.BufferedWriter;

public interface Command {
    String execute(String[] args, BufferedWriter out);
}
