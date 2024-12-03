package ch.heigvd.dai.Managers;

import ch.heigvd.dai.Persistence.UserPersistenceManager;
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
    public static synchronized boolean removeUser(String userName) {
        if (!users.containsKey(userName)) return false;
        users.remove(userName);
        return persistenceManager.deleteUser(userName);
    }
    public static synchronized boolean updateUserName(String userName, String newUserName) {
        if (users.containsKey(newUserName)) return false;
        if (!users.containsKey(userName)) return false;
        if(!persistenceManager.deleteUser(userName)) return false;

        User user = users.remove(userName);
        user.setName(newUserName);
        users.put(newUserName, user);
        return persistenceManager.saveUser(user);
    }
    public static synchronized boolean updateUserPassword(String userName, String newPassword) {
        if (!users.containsKey(userName)) return false;
        if(!persistenceManager.deleteUser(userName)) return false;

        User user = users.remove(userName);
        user.setPassword(newPassword);
        users.put(userName, user);
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
