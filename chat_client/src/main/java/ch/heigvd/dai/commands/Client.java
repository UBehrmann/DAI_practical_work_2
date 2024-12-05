//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ch.heigvd.dai.commands;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Callable;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(
        name = "client",
        description = {"Start the client part of the chat application."}
)
public class Client implements Callable<Integer> {
    public static String END_OF_LINE = "\n";
    @Option(
            names = {"-H", "--host"},
            description = {"Host to connect to."},
            required = true
    )
    private String host;
    @Option(
            names = {"-p", "--port"},
            description = {"Port to use (default: ${DEFAULT-VALUE})."},
            defaultValue = "2001"
    )
    protected int port;

    public Client() {
    }

    public Integer call() {
        try {
            Socket socket = new Socket(this.host, this.port);

            try {
                Reader reader = new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8);

                try {
                    BufferedReader in = new BufferedReader(reader);

                    try {
                        Writer writer = new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8);

                        try {
                            BufferedWriter out = new BufferedWriter(writer);

                            try {
                                System.out.println("[Client] Connected to " + this.host + ":" + this.port);
                                System.out.println();
                                printHelp();

                                while(!socket.isClosed()) {
                                    System.out.print("> ");
                                    BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
                                    String userInput = consoleReader.readLine();
                                    if (userInput != null && !userInput.isBlank()) {
                                        String[] userInputParts = userInput.split(" ", 2);
                                        String command = userInputParts[0].toUpperCase();
                                        String argument = userInputParts.length > 1 ? userInputParts[1] : "";
                                        String request = this.prepareRequest(command, argument);
                                        if (request != null) {
                                            out.write(request);
                                            out.flush();
                                            String serverResponse = in.readLine();
                                            if (serverResponse == null) {
                                                socket.close();
                                                break;
                                            }

                                            this.processResponse(serverResponse);
                                        }
                                    }
                                }

                                System.out.println("[Client] Closing connection and quitting...");
                            } catch (Throwable var18) {
                                try {
                                    out.close();
                                } catch (Throwable var17) {
                                    var18.addSuppressed(var17);
                                }

                                throw var18;
                            }

                            out.close();
                        } catch (Throwable var19) {
                            try {
                                ((Writer)writer).close();
                            } catch (Throwable var16) {
                                var19.addSuppressed(var16);
                            }

                            throw var19;
                        }

                        ((Writer)writer).close();
                    } catch (Throwable var20) {
                        try {
                            in.close();
                        } catch (Throwable var15) {
                            var20.addSuppressed(var15);
                        }

                        throw var20;
                    }

                    in.close();
                } catch (Throwable var21) {
                    try {
                        ((Reader)reader).close();
                    } catch (Throwable var14) {
                        var21.addSuppressed(var14);
                    }

                    throw var21;
                }

                ((Reader)reader).close();
            } catch (Throwable var22) {
                try {
                    socket.close();
                } catch (Throwable var13) {
                    var22.addSuppressed(var13);
                }

                throw var22;
            }

            socket.close();
        } catch (Exception var23) {
            Exception e = var23;
            System.out.println("[Client] Exception: " + e.getMessage());
            return 1;
        }

        return 0;
    }

    private String prepareRequest(String command, String argument) {
        try {
            String[] args = argument.isBlank() ? new String[0] : argument.split(" ", 3);
            String var10000;
            String content;
            switch (command) {
                case "CREATE_USER":
                    if (args.length != 2) {
                        System.out.println("[Client] Error: CREATE_USER requires <name> <password>");
                        var10000 = null;
                    } else {
                        var10000 = "CREATE_USER " + args[0] + " " + args[1] + END_OF_LINE;
                    }
                    break;
                case "CONNECT_TO_SERVER":
                    if (args.length != 2) {
                        System.out.println("[Client] Error: CONNECT_TO_SERVER requires <name> <password>");
                        var10000 = null;
                    } else {
                        var10000 = "CONNECT_TO_SERVER " + args[0] + " " + args[1] + END_OF_LINE;
                    }
                    break;
                case "SET_NAME":
                    if (args.length != 3) {
                        System.out.println("[Client] Error: SET_NAME requires <name> <password> <newName>");
                        var10000 = null;
                    } else {
                        var10000 = "SET_NAME " + args[0] + " " + args[1] + " " + args[2] + END_OF_LINE;
                    }
                    break;
                case "SET_PASSWORD":
                    if (args.length != 3) {
                        System.out.println("[Client] Error: SET_PASSWORD requires <name> <password> <newPassword>");
                        var10000 = null;
                    } else {
                        var10000 = "SET_PASSWORD " + args[0] + " " + args[1] + " " + args[2] + END_OF_LINE;
                    }
                    break;
                case "GET_USER_NAMES":
                    if (args.length != 2) {
                        System.out.println("[Client] Error: GET_USER_NAMES requires <applicantName> <roomName>");
                        var10000 = null;
                    } else {
                        var10000 = "GET_USER_NAMES " + args[0] + " " + args[1] + END_OF_LINE;
                    }
                    break;
                case "GET_ROOM_NAMES":
                    if (args.length != 1) {
                        System.out.println("[Client] Error: GET_ROOM_NAMES requires <applicantName>");
                        var10000 = null;
                    } else {
                        var10000 = "GET_ROOM_NAMES " + args[0] + END_OF_LINE;
                    }
                    break;
                case "CONNECT_TO_ROOM":
                    if (args.length >= 2 && args.length <= 3) {
                        content = args.length == 3 ? args[2] : "";
                        var10000 = "CONNECT_TO_ROOM " + args[0] + " " + args[1] + " " + content + END_OF_LINE;
                    } else {
                        System.out.println("[Client] Error: CONNECT_TO_ROOM requires <userName> <roomName> [<password>]");
                        var10000 = null;
                    }
                    break;
                case "CREATE_ROOM":
                    if (args.length >= 2 && args.length <= 3) {
                        content = args.length == 3 ? args[2] : "";
                        var10000 = "CREATE_ROOM " + args[0] + " " + args[1] + " " + content + END_OF_LINE;
                    } else {
                        System.out.println("[Client] Error: CREATE_ROOM requires <creatorName> <roomName> [<password>]");
                        var10000 = null;
                    }
                    break;
                case "PULL_MESSAGES":
                    if (args.length != 2) {
                        System.out.println("[Client] Error: PULL_MESSAGES requires <applicantName> <roomName>");
                        var10000 = null;
                    } else {
                        var10000 = "PULL_MESSAGES " + args[0] + " " + args[1] + END_OF_LINE;
                    }
                    break;
                case "PUSH_MESSAGE":
                    if (args.length < 3) {
                        System.out.println("[Client] Error: PUSH_MESSAGE requires <applicantName> <roomName> <content>");
                        var10000 = null;
                    } else {
                        content = args[2];
                        var10000 = "PUSH_MESSAGE " + args[0] + " " + args[1] + " " + content + END_OF_LINE;
                    }
                    break;
                case "QUIT":
                    var10000 = "QUIT" + END_OF_LINE;
                    break;
                default:
                    System.out.println("[Client] Unknown command. Type HELP for a list of commands.");
                    var10000 = null;
            }

            return var10000;
        } catch (Exception var7) {
            Exception e = var7;
            System.out.println("[Client] Error preparing request: " + e.getMessage());
            return null;
        }
    }

    private void processResponse(String response) {
        if (!response.startsWith("OK") && !response.startsWith("ERROR")) {
            System.out.println("[Server] " + response);
        } else {
            String[] parts = response.split(" ", 2);
            System.out.println("[Server] " + parts[0]);
            if (parts.length > 1) {
                String[] messages = parts[1].split("(?=\\d{2}/\\d{2}/\\d{4}-\\d{2}:\\d{2}:\\d{2}-)");
                String[] var4 = messages;
                int var5 = messages.length;

                for(int var6 = 0; var6 < var5; ++var6) {
                    String message = var4[var6];
                    System.out.println(message.trim());
                }
            }
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
        System.out.println("  PULL_MESSAGES <userName> <roomName> - Get messages from a chat room.");
        System.out.println("  PUSH_MESSAGE <userName> <roomName> <content> - Send a message to a chat room.");
        System.out.println("  HELP - Show this help message.");
        System.out.println("  QUIT - Disconnect from the server and exit.");
    }
}
