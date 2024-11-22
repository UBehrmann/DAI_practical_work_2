package ch.heigvd.dai.commands;

import java.io.*;
        import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Callable;
import picocli.CommandLine;

@CommandLine.Command(name = "client", description = "Start the client part of the chat application.")
public class Client implements Callable<Integer> {

    // End of line character
    public static String END_OF_LINE = "\n";

    @CommandLine.Option(
            names = {"-H", "--host"},
            description = "Host to connect to.",
            required = true)
    private String host;

    @CommandLine.Option(
            names = {"-p", "--port"},
            description = "Port to use (default: ${DEFAULT-VALUE}).",
            defaultValue = "2001")
    protected int port;

    @Override
    public Integer call() {
        try (Socket socket = new Socket(host, port);
             Reader reader = new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8);
             BufferedReader in = new BufferedReader(reader);
             Writer writer = new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8);
             BufferedWriter out = new BufferedWriter(writer)) {

            System.out.println("[Client] Connected to " + host + ":" + port);
            System.out.println();

            printHelp();

            while (!socket.isClosed()) {
                System.out.print("> ");

                // Read user input from the console
                BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
                String userInput = consoleReader.readLine();

                if (userInput == null || userInput.isBlank()) {
                    continue;
                }

                // Prepare the request
                String[] userInputParts = userInput.split(" ", 2);
                String command = userInputParts[0].toUpperCase();
                String argument = userInputParts.length > 1 ? userInputParts[1] : "";

                String request = prepareRequest(command, argument);

                if (request != null) {
                    // Send the request to the server
                    out.write(request);
                    out.flush();
                } else {
                    continue;
                }

                // Read and process the server response
                String serverResponse = in.readLine();

                if (serverResponse == null) {
                    socket.close();
                    break;
                }

                processResponse(serverResponse);
            }

            System.out.println("[Client] Closing connection and quitting...");
        } catch (Exception e) {
            System.out.println("[Client] Exception: " + e.getMessage());
            return 1;
        }

        return 0;
    }

    private String prepareRequest(String command, String argument) {
        try {
            // Split arguments by space but ensure trailing arguments (like message content) are handled
            String[] args = argument.isBlank() ? new String[0] : argument.split(" ", 3);

            // Validate commands
            return switch (command) {
                case "CREATE_USER" -> {
                    if (args.length != 2) {
                        System.out.println("[Client] Error: CREATE_USER requires <name> <password>");
                        yield null;
                    }
                    yield "CREATE_USER " + args[0] + " " + args[1] + END_OF_LINE;
                }
                case "CONNECT_TO_SERVER" -> {
                    if (args.length != 2) {
                        System.out.println("[Client] Error: CONNECT_TO_SERVER requires <name> <password>");
                        yield null;
                    }
                    yield "CONNECT_TO_SERVER " + args[0] + " " + args[1] + END_OF_LINE;
                }
                case "SET_NAME" -> {
                    if (args.length != 3) {
                        System.out.println("[Client] Error: SET_NAME requires <name> <password> <newName>");
                        yield null;
                    }
                    yield "SET_NAME " + args[0] + " " + args[1] + " " + args[2] + END_OF_LINE;
                }
                case "SET_PASSWORD" -> {
                    if (args.length != 3) {
                        System.out.println("[Client] Error: SET_PASSWORD requires <name> <password> <newPassword>");
                        yield null;
                    }
                    yield "SET_PASSWORD " + args[0] + " " + args[1] + " " + args[2] + END_OF_LINE;
                }
                case "GET_USER_NAMES" -> {
                    if (args.length != 2) {
                        System.out.println("[Client] Error: GET_USER_NAMES requires <applicantName> <roomName>");
                        yield null;
                    }
                    yield "GET_USER_NAMES " + args[0] + " " + args[1] + END_OF_LINE;
                }
                case "GET_ROOM_NAMES" -> {
                    if (args.length != 1) {
                        System.out.println("[Client] Error: GET_ROOM_NAMES requires <applicantName>");
                        yield null;
                    }
                    yield "GET_ROOM_NAMES " + args[0] + END_OF_LINE;
                }
                case "CONNECT_TO_ROOM" -> {
                    if (args.length < 2 || args.length > 3) {
                        System.out.println("[Client] Error: CONNECT_TO_ROOM requires <userName> <roomName> [<password>]");
                        yield null;
                    }
                    String password = args.length == 3 ? args[2] : "";
                    yield "CONNECT_TO_ROOM " + args[0] + " " + args[1] + " " + password + END_OF_LINE;
                }
                case "CREATE_ROOM" -> {
                    if (args.length < 2 || args.length > 3) {
                        System.out.println("[Client] Error: CREATE_ROOM requires <creatorName> <roomName> [<password>]");
                        yield null;
                    }
                    String password = args.length == 3 ? args[2] : "";
                    yield "CREATE_ROOM " + args[0] + " " + args[1] + " " + password + END_OF_LINE;
                }
                case "GET_MESSAGES" -> {
                    if (args.length != 2) {
                        System.out.println("[Client] Error: GET_MESSAGES requires <applicantName> <roomName>");
                        yield null;
                    }
                    yield "GET_MESSAGES " + args[0] + " " + args[1] + END_OF_LINE;
                }
                case "PUSH_MESSAGE" -> {
                    if (args.length < 3) {
                        System.out.println("[Client] Error: PUSH_MESSAGE requires <applicantName> <roomName> <content>");
                        yield null;
                    }
                    String content = args[2]; // Capture message content
                    yield "PUSH_MESSAGE " + args[0] + " " + args[1] + " " + content + END_OF_LINE;
                }
                case "QUIT" -> "QUIT" + END_OF_LINE; // QUIT does not require arguments
                default -> {
                    System.out.println("[Client] Unknown command. Type HELP for a list of commands.");
                    yield null;
                }
            };
        } catch (Exception e) {
            System.out.println("[Client] Error preparing request: " + e.getMessage());
            return null;
        }
    }



    private void processResponse(String response) {
        if (response.startsWith("OK")) {
            System.out.println("[Server] Success: " + response.substring(3));
        } else if (response.startsWith("ERROR")) {
            System.out.println("[Server] Error: " + response.substring(6));
        } else {
            System.out.println("[Server] " + response);
        }
    }

    private static void printHelp() {
        System.out.println("Available commands:");
        System.out.println("  CREATE_USER <name> <password> - Create a new user.");
        System.out.println("  CONNECT_TO_SERVER <name> <password> - Connect to the server.");
        System.out.println("  SET_NAME <currentName> <password> <newName> - Change the username.");
        System.out.println("  SET_PASSWORD <name> <currentPassword> <newPassword> - Change the password.");
        System.out.println("  GET_USER_NAMES <roomName> - Get user names in a room.");
        System.out.println("  GET_ROOM_NAMES - Get all room names.");
        System.out.println("  CONNECT_TO_ROOM <userName> <roomName> <password> - Connect to a chat room.");
        System.out.println("  CREATE_ROOM <creatorName> <roomName> <password> - Create a new chat room.");
        System.out.println("  GET_MESSAGES <userName> <roomName> - Get messages from a chat room.");
        System.out.println("  PUSH_MESSAGE <userName> <roomName> <content> - Send a message to a chat room.");
        System.out.println("  HELP - Show this help message.");
        System.out.println("  QUIT - Disconnect from the server and exit.");
    }
}
