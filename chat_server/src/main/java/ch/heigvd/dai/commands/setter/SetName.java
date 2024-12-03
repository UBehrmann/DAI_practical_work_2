package ch.heigvd.dai.commands.setter;

import ch.heigvd.dai.ErrorCodes;
import ch.heigvd.dai.Managers.UserManager;
import ch.heigvd.dai.Types.User;
import ch.heigvd.dai.commands.Command;

import java.io.BufferedWriter;
import java.util.Map;

public class SetName implements Command {
    //-------------------------------------------------------
    //SET_NAME <userName> <password> <newUserName>
    //-------------------------------------------------------
    @Override
    public String execute(String[] args, BufferedWriter out) {
        if (args.length < 4) {
            return ErrorCodes.MISSING_ARGUMENTS.getMessage();
        }
        String userName     = args[1];
        String password     = args[2];
        String newUserName  = args[3];

        Map<String, User> users = UserManager.getUsers();
        if (!users.containsKey(userName)) {
            return ErrorCodes.USER_NOT_FOUND.getMessage();
        }

        User user = users.get(userName);
        if(!user.isPasswordCorrect(password)){
            return ErrorCodes.USER_WRONG_PASSWORD.getMessage();
        }

        if(users.containsKey(newUserName)){
            return ErrorCodes.USER_ALREADY_EXISTS.getMessage();
        }

        if (!UserManager.updateUserName(userName, newUserName)) {
            return ErrorCodes.STORAGE_FAILED.getMessage();
        }

        return "OK";
    }
}
