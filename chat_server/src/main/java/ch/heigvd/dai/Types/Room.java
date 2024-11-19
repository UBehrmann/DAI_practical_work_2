package ch.heigvd.dai.Types;

import java.util.HashSet;
import java.util.Set;

public class Room {
    private final String name;
    private final String password;
    private final Set<String> clients = new HashSet<>();

    public Room(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public boolean isPasswordCorrect(String password) {
        return this.password == null || this.password.equals(password);
    }

    public void addClient(String clientId) {
        clients.add(clientId);
    }

    public Set<String> getClients() {
        return clients;
    }
}
