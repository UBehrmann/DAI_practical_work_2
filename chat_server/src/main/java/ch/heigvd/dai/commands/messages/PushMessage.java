package ch.heigvd.dai.commands.messages;

import ch.heigvd.dai.ErrorCodes;
import ch.heigvd.dai.Managers.RoomManager;
import ch.heigvd.dai.Managers.UserManager;
import ch.heigvd.dai.Types.Message;
import ch.heigvd.dai.Types.Room;
import ch.heigvd.dai.Types.User;
import ch.heigvd.dai.commands.Command;

import java.io.BufferedWriter;
import java.util.Map;

public class PushMessage implements Command {
    //-------------------------------------------------------
    //PUSH_MESSAGE <applicantName> <roomName> <content>
    //-------------------------------------------------------
    @Override
    public String execute(String[] args, BufferedWriter out) {
        if (args.length < 4) {
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

        // Construire le contenu du message à partir des arguments
        StringBuilder content = new StringBuilder();
        for (int i = 3; i < args.length; i++) {
            content.append(args[i]).append(" ");
        }

        if(!RoomManager.pushMessageToRoom(roomName, new Message(user, content.toString().trim()))){
            return ErrorCodes.STORAGE_FAILED.getMessage();
        }

        return "OK";
    }
}