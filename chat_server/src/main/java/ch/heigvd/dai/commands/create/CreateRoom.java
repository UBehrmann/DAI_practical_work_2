package ch.heigvd.dai.commands.create;

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
            return "ERROR 1 -Missing arguments"; // Missing arguments
        }

        String creatorName  = args[1];
        String roomName     = args[2];
        String roomPassword = args[3];

        Map<String, User> users = UserManager.getUsers();
        if (!users.containsKey(creatorName)) {
            return "ERROR 2 -User name dosen't exist"; // User name dosen't exist
        }

        if(!users.get(creatorName).isOnline()){
            return "ERROR 3 -User isn't connect"; // User isn't connect
        }

        Map<String, Room> rooms = RoomManager.getRooms();
        if (rooms.containsKey(roomName)) {
            return "ERROR 4 -Room name already taken"; // Room name already taken
        }

        // Écrire dans rooms_names.txt
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("rooms_names.txt", true))) {
            writer.write(roomName + " " + roomPassword);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            return "ERROR 5 - Unable to write to rooms file";
        }

        // Créer le fichier nomSalle.txt
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(roomName + "_admin" + ".txt"))) {
            writer.write(creatorName); // Ajouter le créateur comme premier utilisateur
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            return "ERROR 6 - Unable to create room file";
        }

        rooms.put(roomName, new Room(roomName, roomPassword, users.get(creatorName)));
        return "OK";
    }
}

