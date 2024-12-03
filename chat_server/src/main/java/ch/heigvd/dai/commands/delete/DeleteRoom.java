package ch.heigvd.dai.commands.delete;

import ch.heigvd.dai.ErrorCodes;
import ch.heigvd.dai.Managers.RoomManager;
import ch.heigvd.dai.Managers.UserManager;
import ch.heigvd.dai.Types.Room;
import ch.heigvd.dai.Types.User;
import ch.heigvd.dai.commands.Command;

import java.io.BufferedWriter;
import java.util.Map;

public class DeleteRoom implements Command {
    //-------------------------------------------------------
    //DELETE_ROOM <applicantName> <roomName>
    //-------------------------------------------------------
    @Override
    public String execute(String[] args, BufferedWriter out) {
        if (args.length < 3) {
            return ErrorCodes.MISSING_ARGUMENTS.getMessage();
        }

        String userName = args[1];
        String roomName = args[2];

        Map<String, User> users = UserManager.getUsers();
        if (!users.containsKey(userName)) {
            return ErrorCodes.USER_NOT_FOUND.getMessage();
        }

        User user = users.get(userName);
        if(!user.isOnline()){
            return ErrorCodes.USER_NOT_CONNECTED_TO_SERVER.getMessage();
        }

        Map<String, Room> rooms = RoomManager.getRooms();
        if (!rooms.containsKey(roomName)) {
            return ErrorCodes.ROOM_NOT_FOUND.getMessage();
        }

        Room room = rooms.get(roomName);
        if(!room.isUserInRoom(user)){
            return ErrorCodes.USER_NOT_CONNECTED_TO_ROOM.getMessage();
        }

        if(user != room.getAdmin()){
            return ErrorCodes.ROOM_USER_NOT_ADMIN.getMessage();
        }

        RoomManager.removeRoom(roomName);
        return "OK";
    }
}