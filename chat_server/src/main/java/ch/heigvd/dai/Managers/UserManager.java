package ch.heigvd.dai.Managers;

import ch.heigvd.dai.Types.Room;
import ch.heigvd.dai.Types.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserManager {
    // Centralise les salles
    private static final Map<String, User> users = new ConcurrentHashMap<>();

    public static synchronized boolean addUser(User user) {
        if (storeUserToFile(user)) {
            users.put(user.getName(), user);
            return true;
        }
        return false;
    }

    public static synchronized boolean storeUserToFile(User user) {
        List<String> lines = new ArrayList<>();
        lines.add(user.getName() + " " + user.getPassword());
        return FileManager.saveToFile("users.txt", lines, true); // true = append
    }

    public static synchronized boolean loadUsers() {
        List<String> lines = FileManager.loadFromFile("users.txt");
        if (lines.isEmpty()) {
            return false;
        }

        for (String line : lines) {
            String[] parts = line.split(" ");
            if (parts.length == 2) {
                String userName = parts[0];
                String userPassword = parts[1];
                if (!userName.isEmpty() && !userPassword.isEmpty()) {
                    users.put(userName, new User(userName, userPassword));
                }
            }
        }
        return true;
    }

    public static Map<String, User> getUsers() {
        return users;
    }

    public static synchronized boolean removeUser(User user) {
        if (!users.containsKey(user.getName())) return false;

        // Supprime l'utilisateur de la mémoire
        users.remove(user.getName());

        // Met à jour le fichier users.txt
        List<String> lines = FileManager.loadFromFile("users.txt");
        List<String> updatedLines = new ArrayList<>();
        for (String line : lines) {
            if (!line.startsWith(user.getName() + " ")) {
                updatedLines.add(line);
            }
        }

        return FileManager.saveToFile("users.txt", updatedLines, false); // Écrase le fichier
    }
}
