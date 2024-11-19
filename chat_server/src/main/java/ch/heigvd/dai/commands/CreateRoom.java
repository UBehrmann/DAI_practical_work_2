package ch.heigvd.dai.commands;

import ch.heigvd.dai.Managers.RoomManager;
import ch.heigvd.dai.Types.Room;

import java.io.BufferedWriter;
import java.util.Map;



public class CreateRoom implements Command {
    @Override
    public String execute(String[] args, BufferedWriter out) {
        if (args.length < 2) {
            return "ERROR 1"; // Missing room name
        }

        String roomName = args[1];
        String password = args.length > 2 ? args[2] : null;

        Map<String, Room> rooms = RoomManager.getRooms();
        if (rooms.containsKey(roomName)) {
            return "ERROR 1"; // Room name already taken
        }

        rooms.put(roomName, new Room(roomName, password));
        return "OK";
    }
}

