package ch.heigvd.dai.Types;

import java.util.HashSet;
import java.util.Set;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Room {
    private final String name;
    private final String password;
    private final User admin;
    private final Set<User> users = new HashSet<>();

    public Room(String name, String password, User admin) {
        this.name = name;
        this.password = password;
        this.admin = admin;
    }

    public boolean isPasswordCorrect(String password) {
        return this.password == null || this.password.equals(password);
    }

    public void addUser(User user) {
        // Ajouter l'utilisateur dans le fichier nomRoom_users.txt
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(name + "_users.txt", true))) {
            writer.write(user.getName());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace(); // Affiche les erreurs en cas de problème d'écriture
        }

        this.users.add(user);
    }

    public Set<User> getUsers() {
        return users;
    }

    public User getAdmin() {
        return admin;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
