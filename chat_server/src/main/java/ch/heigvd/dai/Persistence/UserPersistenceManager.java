package ch.heigvd.dai.Persistence;

import ch.heigvd.dai.Types.User;

import java.util.ArrayList;
import java.util.List;

public class UserPersistenceManager {
    private static final String USER_FILE = "users.txt";

    // Sauvegarder un utilisateur dans le fichier
    public boolean saveUser(User user) {
        List<String> lines = new ArrayList<>();
        lines.add(user.getName() + " " + user.getPassword());
        return FileManager.saveToFile(USER_FILE, lines, true); // true = append
    }

    // Charger tous les utilisateurs depuis le fichier
    public List<User> loadAllUsers() {
        List<String> lines = FileManager.loadFromFile(USER_FILE);
        List<User> users = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split(" ");
            if (parts.length == 2) {
                String name = parts[0];
                String password = parts[1];
                users.add(new User(name, password));
            }
        }
        return users;
    }

    // Supprimer un utilisateur du fichier
    public boolean deleteUser(String userName) {
        List<String> lines = FileManager.loadFromFile(USER_FILE);
        List<String> updatedLines = new ArrayList<>();
        for (String line : lines) {
            if (!line.startsWith(userName + " ")) {
                updatedLines.add(line);
            }
        }
        return FileManager.saveToFile(USER_FILE, updatedLines, false); // Écrase le fichier
    }
}
