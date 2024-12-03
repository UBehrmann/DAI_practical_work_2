package ch.heigvd.dai.commands;

import ch.heigvd.dai.commands.connect.ConnectToRoom;
import ch.heigvd.dai.commands.connect.ConnectToServer;
import ch.heigvd.dai.commands.create.CreateRoom;
import ch.heigvd.dai.commands.create.CreateUser;
import ch.heigvd.dai.commands.delete.DeleteRoom;
import ch.heigvd.dai.commands.delete.DeleteUser;
import ch.heigvd.dai.commands.getter.GetUserNames;
import ch.heigvd.dai.commands.getter.GetRoomNames;
import ch.heigvd.dai.commands.messages.PullMessages;
import ch.heigvd.dai.commands.messages.PushMessage;
import ch.heigvd.dai.commands.quit.QuitRoom;
import ch.heigvd.dai.commands.quit.QuitServer;
import ch.heigvd.dai.commands.setter.SetName;
import ch.heigvd.dai.commands.setter.SetPassword;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static final Map<String, Class<? extends Command>> commandMap = new HashMap<>();

    static {
        //TESTÉ -> OK !
        commandMap.put("CREATE_USER", CreateUser.class);
        commandMap.put("CONNECT_TO_SERVER", ConnectToServer.class);

        commandMap.put("SET_NAME", SetName.class); //!!!CHECK IS ONLINE
        commandMap.put("SET_PASSWORD", SetPassword.class); //!!!CHECK IS ONLINE

        commandMap.put("CREATE_ROOM", CreateRoom.class);
        commandMap.put("CONNECT_TO_ROOM", ConnectToRoom.class);

        commandMap.put("GET_ROOM_NAMES", GetRoomNames.class);
        commandMap.put("GET_USER_NAMES", GetUserNames.class);

        commandMap.put("PUSH_MESSAGE", PushMessage.class);
        commandMap.put("PULL_MESSAGES", PullMessages.class);

        //EN ATTENTE DE TEST
        commandMap.put("QUIT_SERVER", QuitServer.class);
        commandMap.put("QUIT_ROOM", QuitRoom.class);

        commandMap.put("DELETE_USER", DeleteUser.class);
        commandMap.put("DELETE_ROOM", DeleteRoom.class);

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
