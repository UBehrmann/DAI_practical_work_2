package ch.heigvd.dai.commands.connect;

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
            return "ERROR 1 -Missing arguments"; // Missing arguments
        }

        String username = args[1];
        String roomName = args[2];
        String password = args[3];

        Map<String, User> users = UserManager.getUsers();
        if (!users.containsKey(username)) {
            return "ERROR 2 -User name dosen't exist"; // User name dosen't exist
        }

        if(!users.get(username).isOnline()){
            return "ERROR 3 -User isn't connect"; // User isn't connect
        }

        Map<String, Room> rooms = RoomManager.getRooms();
        if (!rooms.containsKey(roomName)) {
            return "ERROR 4 -Room name dosen't existe"; // Room name dosen't existe
        }

        if(!rooms.get(roomName).getPassword().equals(password)){
            return "ERROR 5 -Wrong password"; //Wrong password
        }

        rooms.get(roomName).addUser(users.get(username));
        return "OK";
    }
}
