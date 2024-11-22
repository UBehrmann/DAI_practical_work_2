package ch.heigvd.dai.Managers;

import ch.heigvd.dai.Types.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserManager {
    //---------------------------------------------------------------------------------------------
    //      ATTRIBUTS
    //---------------------------------------------------------------------------------------------
    private static final Map<String, User> users = new ConcurrentHashMap<>();
    private static final UserPersistenceManager persistenceManager = new UserPersistenceManager();


    //---------------------------------------------------------------------------------------------
    //      USER
    //---------------------------------------------------------------------------------------------
    public static synchronized boolean addUser(User user) {
        if (users.containsKey(user.getName())) return false;
        if (persistenceManager.saveUser(user)) {
            users.put(user.getName(), user);
            return true;
        }
        return false;
    }
    public static synchronized boolean removeUser(User user) {
        if (!users.containsKey(user.getName())) return false;
        users.remove(user.getName());
        return persistenceManager.deleteUser(user.getName());
    }
    public static synchronized boolean updateUserName(User user, String newUserName) {
        if (users.containsKey(newUserName)) return false;
        users.remove(user.getName());
        if(!persistenceManager.deleteUser(user.getName())) return false;
        user.setName(newUserName);
        users.put(newUserName, user);
        return persistenceManager.saveUser(user);
    }
    public static synchronized boolean updateUserPassword(User user, String newPassword) {
        if (!users.containsKey(user.getName())) return false;
        users.remove(user.getName());
        if(!persistenceManager.deleteUser(user.getName())) return false;
        user.setPassword(newPassword);
        users.put(user.getName(), user);
        return persistenceManager.saveUser(user);
    }


    //---------------------------------------------------------------------------------------------
    //      USERS
    //---------------------------------------------------------------------------------------------
    public static synchronized boolean loadUsers() {
        users.clear();
        persistenceManager.loadAllUsers().forEach(user -> users.put(user.getName(), user));
        return !users.isEmpty(); // Retourne true si des utilisateurs ont été chargés
    }

    public static Map<String, User> getUsers() {
        return users;
    }
}
