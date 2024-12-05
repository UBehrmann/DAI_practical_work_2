package ch.heigvd.dai.Persistence;

import ch.heigvd.dai.Managers.UserManager;
import ch.heigvd.dai.Types.Message;
import ch.heigvd.dai.Types.Room;
import ch.heigvd.dai.Types.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RoomPersistenceManager {

    // Sauvegarde les informations globales d'une salle dans rooms.txt
    public boolean saveRoomInfo(Room room) {
        String line = room.getName() + " " + room.getPassword();
        return FileManager.saveToFile("rooms.txt", List.of(line), true);
    }

    // Supprime une salle de rooms.txt
    public boolean deleteRoomInfo(String roomName) {
        List<String> lines = FileManager.loadFromFile("rooms.txt");
        List<String> updatedLines = lines.stream()
                .filter(line -> !line.startsWith(roomName + " "))
                .collect(Collectors.toList());
        return FileManager.saveToFile("rooms.txt", updatedLines, false);
    }

    // Charge toutes les informations globales depuis rooms.txt
    public List<Room> loadRoomInfo() {
        List<String> roomLines = FileManager.loadFromFile("rooms.txt");
        List<Room> rooms = new ArrayList<>();

        for (String line : roomLines) {
            String[] parts = line.split(" ");
            if (parts.length == 2) {
                String roomName = parts[0];
                String password = parts[1];

                // Charger l'admin depuis le fichier associé
                String dataFileName = roomName + "_data.txt";
                File adminFile = new File(dataFileName);

                if (!adminFile.exists()) {
                    System.err.println("Data file for room " + roomName + " not found. Skipping...");
                    continue;
                }

                List<String> dataLines = FileManager.loadFromFile(dataFileName);
                String adminName = dataLines.stream()
                        .filter(lineData -> lineData.startsWith("admin: "))
                        .map(lineData -> lineData.substring(7).trim())
                        .findFirst()
                        .orElse(null);

                if (adminName == null || !UserManager.getUsers().containsKey(adminName)) {
                    System.err.println("Admin for room " + roomName + " is missing or invalid. Skipping...");
                    continue;
                }

                User admin = UserManager.getUsers().get(adminName);
                rooms.add(new Room(roomName, password, admin));
            }
        }
        return rooms;
    }

    // Sauvegarde les détails d'une salle dans roomName_data.txt
    public boolean saveRoomData(Room room) {
        List<String> lines = new ArrayList<>();
        lines.add("admin: " + room.getAdmin().getName());
        lines.add("users: " + room.getUsers().stream().map(User::getName).collect(Collectors.joining(", ")));
        lines.add("messages:");
        for (Message message : room.pullMessage()) {
            lines.add(String.format("%s %s: %s",
                    message.getDate().getFullDate(),
                    message.getTime().getFullTime(),
                    message.getContent()
            ));
        }
        return FileManager.saveToFile(room.getName() + "_data.txt", lines, false);
    }

    // Supprime les détails d'une salle
    public boolean deleteRoomData(String roomName) {
        File dataFile = new File(roomName + "_data.txt");
        return !dataFile.exists() || dataFile.delete();
    }

    // Charge les détails d'une salle depuis roomName_data.txt
    public void loadRoomData(Room room, Map<String, User> users) {
        String dataFileName = room.getName() + "_data.txt";
        List<String> dataLines = FileManager.loadFromFile(dataFileName);

        for (String line : dataLines) {
            if (line.startsWith("users: ")) {
                String[] userNames = line.substring(7).split(", ");
                for (String userName : userNames) {
                    User user = users.get(userName);
                    if (user != null) {
                        room.addUser(user);
                    } else {
                        System.err.println("User " + userName + " not found in users list.");
                    }
                }
            } else if (line.startsWith("messages:")) {
                continue; // Ligne "messages:" à ignorer
            } else if (!line.isBlank()) {
                String[] parts = line.split("->", 2);
                if (parts.length == 2) {
                    String[] metaParts = parts[0].split("-", 3);
                    if (metaParts.length == 3) {
                        String editorName = metaParts[2].trim();
                        User editor = users.get(editorName);
                        if (editor == null) {
                            // Utilisateur introuvable, créer un utilisateur fictif
                            System.err.println("Message editor " + editorName + " not found in users list. Creating placeholder user.");
                            editor = new User(editorName, "deleted-user");
                            editor.setOnline(false);
                        }
                        room.pushMessage(new Message(editor, parts[1].trim()));
                    }
                }
            }
        }
    }


}
