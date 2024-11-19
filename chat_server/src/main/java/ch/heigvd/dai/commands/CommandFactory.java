package ch.heigvd.dai.commands;

import ch.heigvd.dai.commands.connect.ConnectToRoom;
import ch.heigvd.dai.commands.connect.ConnectToServer;
import ch.heigvd.dai.commands.create.CreateRoom;
import ch.heigvd.dai.commands.create.CreateUser;
import ch.heigvd.dai.commands.getter.getUserNames;
import ch.heigvd.dai.commands.getter.getRoomNames;
import ch.heigvd.dai.commands.setter.setName;
import ch.heigvd.dai.commands.setter.setPassword;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static final Map<String, Class<? extends Command>> commandMap = new HashMap<>();

    static {
        //TESTÉ -> OK !
        commandMap.put("CREATE_USER", CreateUser.class);
        commandMap.put("CONNECT_TO_SERVER", ConnectToServer.class);

        commandMap.put("SET_NAME", setName.class); //!!!CHECK IS ONLINE
        commandMap.put("SET_PASSWORD", setPassword.class); //!!!CHECK IS ONLINE

        commandMap.put("CREATE_ROOM", CreateRoom.class);
        commandMap.put("CONNECT_TO_ROOM", ConnectToRoom.class);

        commandMap.put("GET_ROOM_NAMES", getRoomNames.class);
        commandMap.put("GET_USER_NAMES", getUserNames.class);

        //EN ATTENTE DE TEST
        // commandMap.put("GET_MSG", ...);
        // commandMap.put("SET_MSG", ...);

        // commandMap.put("LEAVE_ROOM", ...);
        // commandMap.put("LEAVE_SERVER", ...);

        // commandMap.put("DELETE_ROOM", ...);
        // commandMap.put("DELETE_USER", ...);

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
