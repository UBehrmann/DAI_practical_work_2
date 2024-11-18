package ch.heigvd.dai;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int port = 1234; // Port sur lequel le serveur écoute

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Serveur TCP démarré sur le port " + port);

            while (true) {
                System.out.println("En attente de connexions...");
                Socket clientSocket = serverSocket.accept(); // Accepter une connexion client
                System.out.println("Connexion acceptée de : " + clientSocket.getInetAddress());

                // Création des flux pour communiquer avec le client
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);

                // Lecture et réponse
                String message = in.readLine(); // Lire le message du client
                System.out.println("Message reçu du client : " + message);

                out.println("Message reçu : " + message); // Répondre au client

                // Fermeture du socket
                clientSocket.close();
                System.out.println("Connexion fermée avec le client.");
            }
        } catch (Exception e) {
            System.err.println("Erreur dans le serveur : " + e.getMessage());
            e.printStackTrace();
        }
    }
}