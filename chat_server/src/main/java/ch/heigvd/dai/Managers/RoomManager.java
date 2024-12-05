package ch.heigvd.dai.Managers;

import ch.heigvd.dai.Persistence.RoomPersistenceManager;
import ch.heigvd.dai.Types.Message;
import ch.heigvd.dai.Types.Room;
import ch.heigvd.dai.Types.User;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class RoomManager {
    //---------------------------------------------------------------------------------------------
    //      ATTRIBUTS
    //---------------------------------------------------------------------------------------------
    private static final Map<String, Room> rooms = new ConcurrentHashMap<>();
    private static final RoomPersistenceManager persistenceManager = new RoomPersistenceManager();


    //---------------------------------------------------------------------------------------------
    //      ROOM
    //---------------------------------------------------------------------------------------------
    public static synchronized boolean addRoom(Room room) {
        if (rooms.containsKey(room.getName())) return false;
        rooms.put(room.getName(), room);
        return persistenceManager.saveRoomInfo(room) && persistenceManager.saveRoomData(room);
    }
    public static synchronized boolean removeRoom(String roomName) {
        if(!rooms.containsKey(roomName)) return false;
        rooms.remove(roomName);
        boolean roomInfoDeleted = persistenceManager.deleteRoomInfo(roomName);
        boolean roomDataDeleted = persistenceManager.deleteRoomData(roomName);
        return roomInfoDeleted && roomDataDeleted;
    }


    //---------------------------------------------------------------------------------------------
    //      ROOMS
    //---------------------------------------------------------------------------------------------
    public static synchronized void loadRooms(Map<String, User> users) {
        List<Room> loadedRooms = persistenceManager.loadRoomInfo();
        for (Room room : loadedRooms) {
            if (room != null) {
                rooms.put(room.getName(), room);
                persistenceManager.loadRoomData(room, users);
            }
        }
    }
    public static Map<String, Room> getRooms() {
        return rooms;
    }


    //---------------------------------------------------------------------------------------------
    //      USER IN ROOM
    //---------------------------------------------------------------------------------------------
    public static synchronized boolean addUserToRoom(String roomName, User user) {
        Room room = rooms.get(roomName);
        if (room == null) return false; // La salle n'existe pas
        boolean added = room.addUser(user); // Utilise la méthode `addUser` avec un retour booléen
        return added && persistenceManager.saveRoomData(room); // Sauvegarde si l'ajout a réussi;
    }
    public static synchronized boolean removeUserFromRoom(String roomName, User user) {
        Room room = rooms.get(roomName);
        if (room == null) return false; // La salle n'existe pas
        boolean removed = room.removeUser(user); // Utilise la méthode `removeUser` avec un retour booléen
        return removed && persistenceManager.saveRoomData(room); // Sauvegarde si la suppression a réussi;
    }


    //---------------------------------------------------------------------------------------------
    //      MESSAGES IN ROOM
    //---------------------------------------------------------------------------------------------
    public static synchronized List<Message> pullMessagesFromRoom(String roomName) {
        Room room = rooms.get(roomName);
        if (room == null) return new ArrayList<>(); // La salle n'existe pas
        return new ArrayList<>(room.pullMessage());
    }
    public static synchronized boolean pushMessageToRoom(String roomName, Message message){
        Room room = rooms.get(roomName);
        if (room == null) return false;
        room.pushMessage(message);
        return persistenceManager.saveRoomData(room);
    }
}
