package ch.heigvd.dai.commands.getter;

import ch.heigvd.dai.Managers.RoomManager;
import ch.heigvd.dai.Types.Room;
import ch.heigvd.dai.commands.Command;

import java.io.BufferedWriter;
import java.util.Map;

public class getRooms implements Command {
    @Override
    public String execute(String[] args, BufferedWriter out) {
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

