package ch.heigvd.dai.commands.getter;

import ch.heigvd.dai.ErrorCodes;
import ch.heigvd.dai.Managers.RoomManager;
import ch.heigvd.dai.Managers.UserManager;
import ch.heigvd.dai.Types.Room;
import ch.heigvd.dai.Types.User;
import ch.heigvd.dai.commands.Command;

import java.io.BufferedWriter;
import java.util.Map;

public class GetRoomNames implements Command {
    //-------------------------------------------------------
    //GET_ROOM_NAMES <applicantName>
    //-------------------------------------------------------
    @Override
    public String execute(String[] args, BufferedWriter out) {
        if (args.length < 2) {
            return ErrorCodes.MISSING_ARGUMENTS.getMessage();
        }
        String username = args[1];

        Map<String, User> users = UserManager.getUsers();
        if (!users.containsKey(username)) {
            return ErrorCodes.USER_NOT_FOUND.getMessage();
        }

        User user = users.get(username);
        if(!user.isOnline()){
            return ErrorCodes.USER_NOT_CONNECTED_TO_SERVER.getMessage();
        }

        Map<String, Room> rooms = RoomManager.getRooms();
        StringBuilder result = new StringBuilder("OK ");
        for (String roomName : rooms.keySet()) {
            result.append(roomName).append(" ");
        }

        return result.toString().trim();
    }
}

