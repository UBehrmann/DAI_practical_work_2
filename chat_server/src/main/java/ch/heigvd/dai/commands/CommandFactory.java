package ch.heigvd.dai.commands;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static final Map<String, Class<? extends Command>> commandMap = new HashMap<>();

    static {
        commandMap.put("CREATE_ROOM", CreateRoom.class);
        commandMap.put("CONNECT_TO_ROOM", ConnectToRoom.class);
        // Ajouter d'autres commandes ici
    }

    public static Command getCommand(String action) {
        Class<? extends Command> commandClass = commandMap.get(action.toUpperCase());

        if (commandClass == null) {
            return null;
        }

        try {
            return commandClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Error creating command instance", e);
        }
    }
}
