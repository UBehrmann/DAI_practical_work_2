package ch.heigvd.dai.Types;

import java.util.HashSet;
import java.util.Set;

public class Room {
    private final String name;
    private final String password;
    private final Set<User> users = new HashSet<>();

    public Room(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public boolean isPasswordCorrect(String password) {
        return this.password == null || this.password.equals(password);
    }

    public void addUser(User userName) {
        this.users.add(userName);
    }

    public Set<User> getUsers() {
        return users;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
