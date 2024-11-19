package ch.heigvd.dai.commands;

import ch.heigvd.dai.Managers.RoomManager;
import ch.heigvd.dai.Types.Room;

import java.io.BufferedWriter;
import java.util.Map;



public class ConnectToRoom implements Command {
    @Override
    public String execute(String[] args, BufferedWriter out) {
        if (args.length < 2) {
            return "ERROR 1"; // Missing room name
        }

        String roomName = args[1];
        String password = args.length > 2 ? args[2] : null;

        Map<String, Room> rooms = RoomManager.getRooms();
        Room room = rooms.get(roomName);
        if (room == null) {
            return "ERROR 1"; // Room does not exist
        }

        if (!room.isPasswordCorrect(password)) {
            return "ERROR 2"; // Incorrect password
        }

        room.addClient("PlaceholderClientID");
        return "OK";
    }
}
