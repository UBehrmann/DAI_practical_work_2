package ch.heigvd.dai.Managers;

import ch.heigvd.dai.Types.Room;
import ch.heigvd.dai.Types.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RoomManager {
    // Centralise les salles
    private static final Map<String, Room> rooms = new ConcurrentHashMap<>();

    public static synchronized boolean addRoom(Room room) {
        if (storeRoomToFile(room) && storeAdminRoomToFile(room)) {
            rooms.put(room.getName(), room);
            return true;
        }
        return false;
    }

    public static synchronized boolean removeUserInRoom(Room room, User user){
        if (!rooms.containsKey(room.getName())) return false;

        if (!room.getUsers().contains(user)) return false;


        // Supprime l'utilisateur de la mémoire
        room.removeUser(user);

        // Met à jour le fichier <room_name>_users.txt
        List<String> lines = FileManager.loadFromFile(room.getName() + "_users.txt");
        List<String> updatedLines = new ArrayList<>();
        for (String line : lines) {
            if (!line.equals(user.getName())) {
                updatedLines.add(line);
            }
        }

        return FileManager.saveToFile(room.getName() + "_users.txt", updatedLines, false); // Écrase le fichier
    }

    public static synchronized boolean removeRoom(Room room) {
        if (!rooms.containsKey(room.getName())) return false;

        // Supprime la salle de la mémoire
        rooms.remove(room.getName());

        // Met à jour le fichier rooms_names.txt
        List<String> lines = FileManager.loadFromFile("rooms_names.txt");
        List<String> updatedLines = new ArrayList<>();
        for (String line : lines) {
            if (!line.startsWith(room.getName() + " ")) {
                updatedLines.add(line);
            }
        }

        boolean roomFileUpdated = FileManager.saveToFile("rooms_names.txt", updatedLines, false);

        // Supprime les fichiers associés (admin et users)
        File adminFile = new File(room.getName() + "_admin.txt");
        File userFile = new File(room.getName() + "_users.txt");

        boolean adminDeleted = !adminFile.exists() || adminFile.delete();
        boolean usersDeleted = !userFile.exists() || userFile.delete();

        return roomFileUpdated && adminDeleted && usersDeleted;
    }


    public static synchronized boolean addUserInRoom(Room room, User user) {
        if (room.getUsers().contains(user)) return false;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(room.getName() + "_users.txt", true))) {
            writer.write(user.getName());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        room.addUser(user);
        return true;
    }

    public static synchronized boolean loadRooms() {
        List<String> lines = FileManager.loadFromFile("rooms_names.txt");
        if (lines.isEmpty()) {
            return false;
        }

        for (String line : lines) {
            String[] parts = line.split(" ");
            if (parts.length == 2) {
                String roomName = parts[0];
                String roomPassword = parts[1];

                File adminFile = new File(roomName + "_admin.txt");
                if (!adminFile.exists()) continue;

                try (BufferedReader adminReader = new BufferedReader(new FileReader(adminFile))) {
                    String adminName = adminReader.readLine();
                    if (adminName != null && UserManager.getUsers().containsKey(adminName)) {
                        User admin = UserManager.getUsers().get(adminName);
                        Room room = new Room(roomName, roomPassword, admin);
                        rooms.put(roomName, room);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    private static synchronized boolean storeRoomToFile(Room room) {
        List<String> lines = new ArrayList<>();
        lines.add(room.getName() + " " + room.getPassword());
        return FileManager.saveToFile("rooms_names.txt", lines, true); // true = append
    }

    private static synchronized boolean storeAdminRoomToFile(Room room) {
        if (room.getAdmin() == null) return false;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(room.getName() + "_admin.txt"))) {
            writer.write(room.getAdmin().getName());
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Map<String, Room> getRooms() {
        return rooms;
    }
}
