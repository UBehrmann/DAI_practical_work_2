package ch.heigvd.dai.commands.getter;

import ch.heigvd.dai.Managers.RoomManager;
import ch.heigvd.dai.Managers.UserManager;
import ch.heigvd.dai.Types.Room;
import ch.heigvd.dai.Types.User;
import ch.heigvd.dai.commands.Command;

import java.io.BufferedWriter;
import java.util.Map;

public class GetUserNames implements Command {
    @Override
    public String execute(String[] args, BufferedWriter out) {
        if (args.length < 3) {
            return "ERROR 1 -Missing arguments"; // Missing arguments
        }

        String userName = args[1];
        String roomName = args[2];

        // Récupère les utilisateurs
        Map<String, User> users = UserManager.getUsers();

        //Si pas un user connu alors erreur
        if (!users.containsKey(userName)) {
            return "ERROR 2 -User name dosen't exist"; // User name dosen't exist
        }

        //Si pas en ligne alors erreur
        if(!users.get(userName).isOnline()){
            return "ERROR 3 -User isn't connect"; // User isn't connect
        }

        // Récupère les salles
        Map<String, Room> rooms = RoomManager.getRooms();

        //Si pas une room connu alors erreur
        if (!rooms.containsKey(roomName)) {
            return "ERROR 4 -Room name dosen't exist"; // Room name dosen't exist
        }
        // Construit la réponse avec un StringBuilder
        StringBuilder result = new StringBuilder("OK ");
        for (User user : rooms.get(roomName).getUsers()) {
            result.append(user.getName()).append(" ");
        }

        // Retourne la réponse en supprimant l'espace final
        return result.toString().trim();
    }
}
