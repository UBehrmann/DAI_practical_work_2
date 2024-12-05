package ch.heigvd.dai;

import ch.heigvd.dai.commands.Command;
import ch.heigvd.dai.commands.CommandFactory;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientHandler implements Runnable {

    private final Socket socket;
    private final int server_id;

    private final String client_id;
    private final String prompt;

    public ClientHandler(Socket socket, int server_id, String prompt) {
        this.socket     = socket;
        this.server_id  = server_id;
        this.client_id  = this.socket.getInetAddress().getHostAddress() + ":" + this.socket.getPort();
        this.prompt     = prompt;
    }

    @Override
    public void run() {
        try (socket; // This allow to use try-with-resources with the socket
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8))) {

            System.out.println(prompt +  "NEW CLIENT : connected from -> " + client_id);


            String request;
            while ((request = in.readLine()) != null) {
                System.out.println(prompt + "Received request -> " + request); // Log la commande reçue

                String[] parts = request.trim().split(" ");
                String action = parts[0].toUpperCase();

                // Obtenir la commande via la CommandFactory
                Command command = CommandFactory.getCommand(action);
                if (command != null) {
                    // Exécuter la commande et envoyer la réponse
                    String response = command.execute(parts, out);
                    out.write(response + "\n");
                } else {
                    // Commande non reconnue
                    out.write("ERROR Unknown command\n");
                }
                out.flush();
            }

            System.out.println(prompt + "closing connection");
        } catch (IOException e) {
            System.out.println(prompt + "exception -> " + e);
        }
    }
}
