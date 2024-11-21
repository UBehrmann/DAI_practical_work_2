package ch.heigvd.dai.commands.setter;

import ch.heigvd.dai.Managers.UserManager;
import ch.heigvd.dai.Types.User;
import ch.heigvd.dai.commands.Command;

import java.io.BufferedWriter;
import java.util.Map;

public class SetPassword implements Command {
    @Override
    public String execute(String[] args, BufferedWriter out) {
        if (args.length < 4) {
            return "ERROR 1 -Missing arguments"; // Missing arguments
        }

        String userName     = args[1];
        String password     = args[2];
        String newPassword  = args[3];

        Map<String, User> users = UserManager.getUsers();
        if (!users.containsKey(userName)) {
            return "ERROR 2 -User name dosen't exist"; //User name dosen't exist
        }

        if(!users.get(userName).getPassword().equals(password)){
            return "ERROR 3 -Wrong password"; //Wrong password
        }

        //Copier le user et changer son mot de passe
        User user = new User(users.get(userName));
        user.setPassword(newPassword); // Modifier le mot de passe

        //Supprimer le user de la map
        users.remove(userName);

        //Ajouter le user modifié dans la map
        users.put(userName, user);

        return "OK";
    }
}