package ch.heigvd.dai.commands.getter;

import ch.heigvd.dai.Managers.RoomManager;
import ch.heigvd.dai.Types.Room;
import ch.heigvd.dai.Types.User;
import ch.heigvd.dai.commands.Command;

import java.io.BufferedWriter;
import java.util.Map;
import java.util.Set;

public class getNames implements Command {
    @Override
    public String execute(String[] args, BufferedWriter out) {
        if (args.length < 1) {
            return "ERROR 1"; // Missing room name
        }

        String roomName = args[1];

        // Récupère les salles
        Map<String, Room> rooms = RoomManager.getRooms();

        // Vérifie si la salle existe
        if (!rooms.containsKey(roomName)) {
            return "ERROR 2"; // Room name dosen't existe
        }

        // Récupère les noms des utilisateurs
        Set<User> users = rooms.get(roomName).getUsers();

        // Construit la réponse avec un StringBuilder
        StringBuilder result = new StringBuilder("OK ");
        for (User user : users) {
            result.append(user.getName()).append(" ");
        }

        // Retourne la réponse en supprimant l'espace final
        return result.toString().trim();
    }
}
