package ch.heigvd.dai.commands.getter;

import ch.heigvd.dai.Managers.RoomManager;
import ch.heigvd.dai.Managers.UserManager;
import ch.heigvd.dai.Types.Room;
import ch.heigvd.dai.Types.User;
import ch.heigvd.dai.commands.Command;

import java.io.BufferedWriter;
import java.util.Map;

public class getRoomNames implements Command {
    @Override
    public String execute(String[] args, BufferedWriter out) {
        if (args.length < 2) {
            return "ERROR 1 -Missing arguments"; // Missing arguments
        }

        String username = args[1];

        // Récupère les utilisateurs
        Map<String, User> users = UserManager.getUsers();

        //Si pas un user connu alors erreur
        if (!users.containsKey(username)) {
            return "ERROR 2 -User name dosen't exist"; // User name dosen't exist
        }

        //Si pas en ligne alors erreur
        if(!users.get(username).isOnline()){
            return "ERROR 3 -User isn't connect"; // User isn't connect
        }

        // Récupère les salles
        Map<String, Room> rooms = RoomManager.getRooms();

        // Construit la réponse avec un StringBuilder
        StringBuilder result = new StringBuilder("OK ");
        for (String roomName : rooms.keySet()) {
            result.append(roomName).append(" ");
        }

        // Retourne la réponse en supprimant l'espace final
        return result.toString().trim();
    }
}

