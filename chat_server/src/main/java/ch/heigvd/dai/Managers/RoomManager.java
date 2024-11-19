package ch.heigvd.dai.Managers;

import ch.heigvd.dai.Types.Room;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RoomManager {
    // Centralise les salles
    private static final Map<String, Room> rooms = new ConcurrentHashMap<>();

    public static Map<String, Room> getRooms() {
        return rooms;
    }
}
