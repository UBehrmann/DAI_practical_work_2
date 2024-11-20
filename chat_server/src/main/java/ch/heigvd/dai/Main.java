package ch.heigvd.dai;

import ch.heigvd.dai.Managers.FileManager;
import ch.heigvd.dai.Managers.RoomManager;
import ch.heigvd.dai.Managers.UserManager;

import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.


public class Main {
    private static final int PORT = 1234;
    private static final int SERVER_ID = (int) (Math.random() * 1000000);
    private static final String SERVER_NAME = "my_chat_tcp_server";

    public static void main(String[] args) {
        loadDatas();

        printData();

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("[" + SERVER_NAME + " - " + SERVER_ID + "] starting with id " + SERVER_ID);
            System.out.println("[" + SERVER_NAME + " - " + SERVER_ID + "] listening on port " + PORT);

            while (!serverSocket.isClosed()) {
                System.out.println(">" + SERVER_ID + " : " + "En attente de connexions...");
                Socket clientSocket = serverSocket.accept();
                System.out.println(">" + SERVER_ID + " : " + "Connexion acceptée de : " + clientSocket.getInetAddress().getHostAddress());
                Thread clientThread = new Thread(new ClientHandler(clientSocket, SERVER_ID));
                clientThread.start();
            }
        } catch (Exception e) {
            System.out.println("[" + SERVER_NAME + " - " + SERVER_ID + "] exception: " + e);
        }
    }


    public static void loadDatas(){
        System.out.println("------LOAD-DATA------");

        // Charger les utilisateurs
        System.out.println("Loading users...");
        if (UserManager.loadUsers()) {
            System.out.println("Users loaded successfully.");
        } else {
            System.err.println("Failed to load users.");
        }

        // Charger les salles
        System.out.println("Loading rooms...");
        if (RoomManager.loadRooms()) {
            System.out.println("Rooms loaded successfully.");
        } else {
            System.err.println("Failed to load rooms.");
        }
        System.out.println("---------------------");
    }

    public static void printData(){
        System.out.println("-----PRINT-DATA------");

        // Afficher les utilisateurs
        System.out.println("Users:");
        UserManager.getUsers().forEach((name, user) -> {
            System.out.println(" - " + name);
        });
        System.out.println("---------------------");
        // Afficher les salles et leurs fichiers associés
        System.out.println("Rooms:");
        RoomManager.getRooms().forEach((roomName, room) -> {
            System.out.println(" - Room Name: " + roomName);
            System.out.println("   Admin: " + room.getAdmin().getName());

            // Afficher les utilisateurs du fichier <roomName>_users.txt
            File userFile = new File(roomName + "_users.txt");
            if (userFile.exists()) {
                System.out.println("   Users in Room (from file):");
                List<String> usersInRoom = FileManager.loadFromFile(userFile.getName());
                usersInRoom.forEach(user -> System.out.println("     - " + user));
            } else {
                System.out.println("   Users in Room: No file found.");
            }
        });
        System.out.println("---------------------");
    }
}