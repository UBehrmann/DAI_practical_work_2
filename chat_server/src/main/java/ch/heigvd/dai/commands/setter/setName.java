package ch.heigvd.dai.commands.setter;

import ch.heigvd.dai.Managers.UserManager;
import ch.heigvd.dai.Types.User;
import ch.heigvd.dai.commands.Command;

import java.io.BufferedWriter;
import java.util.Map;

public class setName implements Command {
    @Override
    public String execute(String[] args, BufferedWriter out) {
        if (args.length < 4) {
            return "ERROR 1 -Missing arguments"; // Missing arguments
        }

        String userName     = args[1];
        String password     = args[2];
        String newUserName  = args[3];

        Map<String, User> users = UserManager.getUsers();
        if (!users.containsKey(userName)) {
            return "ERROR 2 -User name dosen't exist"; // User name dosen't exist
        }

        if(!users.get(userName).getPassword().equals(password)){
            return "ERROR 3 -Wrong password"; //Wrong password
        }

        if(users.containsKey(newUserName)){
            return "ERROR 4 -New user name not available"; //New user name not available
        }

        //Copier le user et changer son nom
        User user = new User(users.get(userName));
        user.setName(newUserName); // Modifier le nom

        //Supprimer le user de la map
        users.remove(userName);

        //Ajouter le user modifié dans la mapo
        users.put(newUserName, user);

        return "OK";
    }
}
