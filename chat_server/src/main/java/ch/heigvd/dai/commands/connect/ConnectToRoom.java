package ch.heigvd.dai.commands.connect;

import ch.heigvd.dai.ErrorCodes;
import ch.heigvd.dai.Managers.RoomManager;
import ch.heigvd.dai.Managers.UserManager;
import ch.heigvd.dai.Types.Message;
import ch.heigvd.dai.Types.Room;
import ch.heigvd.dai.Types.User;
import ch.heigvd.dai.commands.Command;

import java.io.BufferedWriter;
import java.util.Map;



public class ConnectToRoom implements Command {
    //-------------------------------------------------------
    //CONNECT_TO_ROOM <userName> <roomName> <roomPassword>
    //-------------------------------------------------------
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

        User user = users.get(userName);
        if(!user.isOnline()){
            return ErrorCodes.USER_NOT_CONNECTED_TO_SERVER.getMessage();
        }

        Map<String, Room> rooms = RoomManager.getRooms();
        if (!rooms.containsKey(roomName)) {
            return ErrorCodes.ROOM_NOT_FOUND.getMessage();
        }

        Room room = rooms.get(roomName);
        if(!room.isPasswordCorrect(password)){
            return ErrorCodes.ROOM_WRONG_PASSWORD.getMessage();
        }

        if(room.isUserInRoom(user)){
            return "OK";
        }

        if(!RoomManager.addUserToRoom(roomName, user)) {
            return ErrorCodes.STORAGE_FAILED.getMessage();
        }

        RoomManager.pushMessageToRoom(roomName, new Message(user, "---Arrivée membre : " + userName));
        return "OK";
    }
}
