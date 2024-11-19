package ch.heigvd.dai.commands.connect;

import ch.heigvd.dai.Managers.RoomManager;
import ch.heigvd.dai.Managers.UserManager;
import ch.heigvd.dai.Types.Room;
import ch.heigvd.dai.Types.User;
import ch.heigvd.dai.commands.Command;

import java.io.BufferedWriter;
import java.util.Map;

public class ConnectToServer implements Command {
    @Override
    public String execute(String[] args, BufferedWriter out) {
        if (args.length < 3) {
            return "ERROR 1 -Missing arguments"; // Missing arguments
        }

        String userName = args[1];
        String password = args[2];

        Map<String, User> users = UserManager.getUsers();

        if (!users.containsKey(userName)) {
            return "ERROR 2 -User name dosen't exist"; //User name dosen't exist
        }

        if(!users.get(userName).getPassword().equals(password)){
            return "ERROR 3 -Wrong password"; //Wrong password
        }

        users.get(userName).setOnline(true);
        return "OK";
    }
}
