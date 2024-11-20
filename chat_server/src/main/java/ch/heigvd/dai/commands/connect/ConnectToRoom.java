package ch.heigvd.dai.commands.connect;

import ch.heigvd.dai.ErrorCodes;
import ch.heigvd.dai.Managers.RoomManager;
import ch.heigvd.dai.Managers.UserManager;
import ch.heigvd.dai.Types.Room;
import ch.heigvd.dai.Types.User;
import ch.heigvd.dai.commands.Command;

import java.io.BufferedWriter;
import java.util.Map;



public class ConnectToRoom implements Command {
    @Override
    public String execute(String[] args, BufferedWriter out) {
        if (args.length < 4) {
            return ErrorCodes.MISSING_ARGUMENTS.getMessage();
        }

        String userName = args[1];
        String roomName = args[2];
        String password = args[3];

        Map<String, User> users = UserManager.getUsers();
        if (!users.containsKey(userName)) {
            return ErrorCodes.USER_NOT_FOUND.getMessage();
        }

        if(!users.get(userName).isOnline()){
            return ErrorCodes.USER_NOT_CONNECTED.getMessage();
        }

        Map<String, Room> rooms = RoomManager.getRooms();
        if (!rooms.containsKey(roomName)) {
            return ErrorCodes.ROOM_NOT_FOUND.getMessage();
        }

        if(!rooms.get(roomName).isPasswordCorrect(password)){
            return ErrorCodes.PASSWORD_WRONG.getMessage();
        }

        if(rooms.get(roomName).isUserInRoom(users.get(userName))){
            return "OK";
        }

        if(!RoomManager.addUserInRoom(rooms.get(roomName), users.get(userName))) {
            return ErrorCodes.STORAGE_FAILED.getMessage();
        }

        return "OK";
    }
}
