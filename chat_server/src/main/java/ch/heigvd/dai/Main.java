package ch.heigvd.dai;

import ch.heigvd.dai.Managers.ApplicationDataLoaderManager;
import ch.heigvd.dai.Managers.RoomManager;
import ch.heigvd.dai.Managers.UserManager;
import ch.heigvd.dai.Managers.CommandLineArgumentsManager;

import java.net.ServerSocket;
import java.net.Socket;


public class Main {

    private static final int DEFAULT_PORT = 1234;
    private static final String DEFAULT_SERVER_NAME = "my_chat_tcp_server";
    private static final int SERVER_ID = (int) (Math.random() * 1000000);

    public static void main(String[] args) {
        // Gestion des arguments avec CommandLineArgumentsManager
        CommandLineArgumentsManager cliManager = new CommandLineArgumentsManager();
        cliManager.addOption("port", "Port sur lequel le serveur doit écouter (par défaut : 1234)");
        cliManager.addOption("name", "Nom du serveur (par défaut : my_chat_tcp_server)");
        cliManager.addOption("load", "Charger les données avant de démarrer le serveur (true/false, par défaut : false)");
        cliManager.addOption("help", "Afficher l'aide");
        cliManager.parse(args);

        /*----------Demande d'aide----------*/
        if (cliManager.hasOption("help")) {
            cliManager.printHelp();
            return;
        }

        /*----------Configuration du servuer----------*/
        int port = DEFAULT_PORT;
        String serverName = DEFAULT_SERVER_NAME;
        boolean loadData = false;
        if (cliManager.hasOption("port")) {
            try {
                port = Integer.parseInt(cliManager.getOptionValue("port"));
            } catch (NumberFormatException e) {
                System.err.println("Le port spécifié est invalide. Utilisation du port par défaut : " + DEFAULT_PORT);
            }
        }
        if (cliManager.hasOption("name")) {
            serverName = cliManager.getOptionValue("name");
        }

        /*----------Charger les données si demandé----------*/
        if (cliManager.hasOption("load")) {
            String loadValue = cliManager.getOptionValue("load");
            loadData = loadValue != null && loadValue.equalsIgnoreCase("true");
        }
        if (loadData) {
            System.out.println("Chargement des données...");
            ApplicationDataLoaderManager.load();
            ApplicationDataLoaderManager.print();
        }

        /*----------Start du serveur----------*/
        String prompt = "[" + serverName + "] > ";
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println(prompt + "starting with id " + SERVER_ID);
            System.out.println(prompt + "listening on port " + port);

            while (!serverSocket.isClosed()) {
                //Attente de client
                System.out.println(prompt + "En attente de connexions...");
                Socket clientSocket = serverSocket.accept();

                //Nouveau client
                System.out.println(prompt + "Connexion acceptée de : " + clientSocket.getInetAddress().getHostAddress());
                Thread clientThread = new Thread(new ClientHandler(clientSocket, SERVER_ID, prompt + clientSocket.getInetAddress().getHostAddress() + " > "));
                //Lancement du thread du client
                clientThread.start();
            }
        } catch (Exception e) {
            System.out.println(prompt +  "exception: " + e);
        }
    }
}