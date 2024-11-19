package ch.heigvd.dai;

import java.net.ServerSocket;
import java.net.Socket;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.


public class Main {
    private static final int PORT = 1234;
    private static final int SERVER_ID = (int) (Math.random() * 1000000);
    private static final String SERVER_NAME = "my_chat_tcp_server";

    public static void main(String[] args) {
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
}