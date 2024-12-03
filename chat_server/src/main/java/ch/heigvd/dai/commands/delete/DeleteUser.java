package ch.heigvd.dai.commands.delete;

import ch.heigvd.dai.ErrorCodes;
import ch.heigvd.dai.Managers.RoomManager;
import ch.heigvd.dai.Managers.UserManager;
import ch.heigvd.dai.Types.Room;
import ch.heigvd.dai.Types.User;
import ch.heigvd.dai.commands.Command;

import java.io.BufferedWriter;
import java.util.Map;

public class DeleteUser implements Command {
    //-------------------------------------------------------
    //DELETE_USER <applicantName>
    //-------------------------------------------------------
    @Override
    public String execute(String[] args, BufferedWriter out) {
        if (args.length < 2){
            return ErrorCodes.MISSING_ARGUMENTS.getMessage();
        }

        String userName = args[1];

        Map<String, User> users = UserManager.getUsers();
        if (!users.containsKey(userName)) {
            return ErrorCodes.USER_NOT_FOUND.getMessage();
        }

        User user = users.get(userName);
        if(!user.isOnline()){
            return ErrorCodes.USER_NOT_CONNECTED_TO_SERVER.getMessage();
        }

        for(Room room : RoomManager.getRooms().values()){
            if(room.isUserInRoom(user)) RoomManager.removeUserFromRoom(room.getName(), user);
        }

        UserManager.removeUser(user);
        return "OK";
    }
}
