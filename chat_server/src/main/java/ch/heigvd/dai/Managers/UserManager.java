package ch.heigvd.dai.Managers;

import ch.heigvd.dai.Types.Room;
import ch.heigvd.dai.Types.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserManager {
    // Centralise les salles
    private static final Map<String, User> users = new ConcurrentHashMap<>();

    public static Map<String, User> getUsers() {
        return users;
    }
}
