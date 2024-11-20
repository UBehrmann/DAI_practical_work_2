package ch.heigvd.dai.commands.connect;

import ch.heigvd.dai.ErrorCodes;
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
            return ErrorCodes.MISSING_ARGUMENTS.getMessage();
        }

        String userName = args[1];
        String password = args[2];

        Map<String, User> users = UserManager.getUsers();

        if (!users.containsKey(userName)) {
            return ErrorCodes.USER_NOT_FOUND.getMessage();
        }

        if(!users.get(userName).isPasswordCorrect(password)){
            return ErrorCodes.PASSWORD_WRONG.getMessage();
        }

        users.get(userName).setOnline(true);
        return "OK";
    }
}
