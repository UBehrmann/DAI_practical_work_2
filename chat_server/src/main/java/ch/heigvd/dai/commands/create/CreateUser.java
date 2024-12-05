package ch.heigvd.dai.commands.create;

import ch.heigvd.dai.ErrorCodes;
import ch.heigvd.dai.Managers.UserManager;
import ch.heigvd.dai.Types.User;
import ch.heigvd.dai.commands.Command;

import java.io.BufferedWriter;
import java.util.Map;
import java.io.FileWriter;
import java.io.IOException;

public class CreateUser implements Command {
    //-------------------------------------------------------
    //CREATE_USER <userName> <password>
    //-------------------------------------------------------
    @Override
    public String execute(String[] args, BufferedWriter out) {
        if (args.length < 3) {
            return ErrorCodes.MISSING_ARGUMENTS.getMessage();
        }
        String userName = args[1];
        String password = args[2];

        Map<String, User> users = UserManager.getUsers();
        if (users.containsKey(userName)) {
            return ErrorCodes.USER_ALREADY_EXISTS.getMessage();
        }

        if(!UserManager.addUser(new User(userName, password))){
            return ErrorCodes.STORAGE_FAILED.getMessage();
        }
        return "OK";
    }
}
