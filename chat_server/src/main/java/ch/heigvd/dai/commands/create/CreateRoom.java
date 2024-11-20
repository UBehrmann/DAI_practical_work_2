package ch.heigvd.dai.commands.create;

import ch.heigvd.dai.ErrorCodes;
import ch.heigvd.dai.Managers.RoomManager;
import ch.heigvd.dai.Managers.UserManager;
import ch.heigvd.dai.Types.Room;
import ch.heigvd.dai.Types.User;
import ch.heigvd.dai.commands.Command;

import java.io.BufferedWriter;
import java.util.Map;
import java.io.FileWriter;
import java.io.IOException;


public class CreateRoom implements Command {
    @Override
    public String execute(String[] args, BufferedWriter out) {
        if (args.length < 4) {
            return ErrorCodes.MISSING_ARGUMENTS.getMessage();
        }

        String creatorName  = args[1];
        String roomName     = args[2];
        String roomPassword = args[3];

        Map<String, User> users = UserManager.getUsers();
        if (!users.containsKey(creatorName)) {
            return ErrorCodes.USER_NOT_FOUND.getMessage();
        }

        if(!users.get(creatorName).isOnline()){
            return ErrorCodes.USER_NOT_CONNECTED.getMessage();
        }

        Map<String, Room> rooms = RoomManager.getRooms();
        if (rooms.containsKey(roomName)) {
            return ErrorCodes.ROOM_ALREADY_EXISTS.getMessage();
        }

        if(!RoomManager.addRoom(new Room(roomName, roomPassword, users.get(creatorName)))){
            return ErrorCodes.STORAGE_FAILED.getMessage();
        }
        return "OK";
    }
}

