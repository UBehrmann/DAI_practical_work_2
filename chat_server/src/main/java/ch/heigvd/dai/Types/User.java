package ch.heigvd.dai.Types;

public class User {
    private String name;
    private String password;
    private boolean online;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.online = false;
    }

    // Constructeur par copie
    public User(User other) {
        this.name = other.name;
        this.password = other.password;
    }

    public boolean isPasswordCorrect(String password) {
        return this.password == null || this.password.equals(password);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }
}
