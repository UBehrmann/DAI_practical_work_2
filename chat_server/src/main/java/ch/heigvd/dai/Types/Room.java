package ch.heigvd.dai.Types;

import java.util.LinkedHashSet;
import java.util.Set;

public class Room {
    private final String name;
    private final String password;
    private User admin;
    private Set<User> users = new LinkedHashSet<>();

    private Set<Message> messages = new LinkedHashSet<>();

    public Room(String name, String password, User admin) {
        this.name = name;
        this.password = password;
        this.admin = admin;
    }
    public Room(Room other){
        this.name = other.name;
        this.password = other.password;
        this.admin = other.admin;
        this.users = other.users;
        this.messages = other.messages;
    }

    public void pushMessage(Message message){
        this.messages.add(message); // Ajoute le Message au Set
    }

    public Set<Message> pullMessage(){
        return messages;
    }

    public boolean isPasswordCorrect(String password) {
        return this.password == null || this.password.equals(password);
    }

    public boolean addUser(User user) {
        if (users.contains(user)) {
            return false; // L'utilisateur est déjà présent
        }
        users.add(user);
        return true;
    }

    public boolean isUserInRoom(User user) {
        return isAdminInRoom(user) || this.users.contains(user);
    }

    public boolean isAdminInRoom(User user){
        return this.admin.equals(user);
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

    public boolean removeUser(User user) {
        if (!users.contains(user)) {
            return false; // L'utilisateur n'est pas dans la salle
        }
        pushMessage(new Message(user, "---Départ membre : " + user.getName()));
        users.remove(user);
        return true;
    }


    public User getAdmin() {
        return admin;
    }
    public void setAdmin(User user) {
        admin = user;
    }
}
