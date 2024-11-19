package ch.heigvd.dai.commands.create;

import ch.heigvd.dai.Managers.UserManager;
import ch.heigvd.dai.Types.User;
import ch.heigvd.dai.commands.Command;

import java.io.BufferedWriter;
import java.util.Map;

public class CreateUser implements Command {
    @Override
    public String execute(String[] args, BufferedWriter out) {
        if (args.length < 3) {
            return "ERROR 1 -Missing arguments"; // Missing arguments
        }

        String userName = args[1];
        String password = args.length > 2 ? args[2] : null;

        Map<String, User> users = UserManager.getUsers();
        if (users.containsKey(userName)) {
            return "ERROR 2 -User name already taken"; // User name already taken
        }

        users.put(userName, new User(userName, password));
        return "OK";
    }
}