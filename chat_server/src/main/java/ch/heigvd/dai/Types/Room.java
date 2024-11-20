package ch.heigvd.dai.Types;

import java.util.HashSet;
import java.util.Set;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

public class Room {
    private final String name;
    private final String password;
    private final User admin;
    private CopyOnWriteArraySet<User> users = new CopyOnWriteArraySet<>();

    public Room(String name, String password, User admin) {
        this.name = name;
        this.password = password;
        this.admin = admin;
    }

    public boolean isPasswordCorrect(String password) {
        return this.password == null || this.password.equals(password);
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public boolean isUserInRoom(User user) {
        return this.admin.equals(user) || this.users.contains(user);
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }


    public Set<User> getUsers() {
        return users;
    }

    public void removeUser(User user) {
        if (!users.contains(user)) return;

        users.remove(user);
    }

    public User getAdmin() {
        return admin;
    }
}
