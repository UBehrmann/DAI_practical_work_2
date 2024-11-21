package ch.heigvd.dai.Types;

import java.util.LinkedHashSet;
import java.util.Set;

public class Room {
    private final String name;
    private final String password;
    private final User admin;
    private Set<User> users = new LinkedHashSet<>();

    private Set<Message> messages = new LinkedHashSet<>();

    public Room(String name, String password, User admin) {
        this.name = name;
        this.password = password;
        this.admin = admin;

        pushMessage(new Message(this.admin, "---Création du room : " + this.name));
        pushMessage(new Message(this.admin, "---Admin du room : " + this.admin.getName()));
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

    public void addUser(User user) {
        this.users.add(user);
        pushMessage(new Message(user, "---Arrivée membre : " + user.getName()));
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

        pushMessage(new Message(user, "---Départ membre : " + user.getName()));
        users.remove(user);
    }

    public User getAdmin() {
        return admin;
    }
}
