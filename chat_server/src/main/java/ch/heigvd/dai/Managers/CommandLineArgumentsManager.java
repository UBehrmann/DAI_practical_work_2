package ch.heigvd.dai.Managers;

import java.util.HashMap;
import java.util.Map;

public class CommandLineArgumentsManager {
    private final Map<String, String> options;
    private final Map<String, String> parsedArguments;

    public CommandLineArgumentsManager() {
        this.options = new HashMap<>();
        this.parsedArguments = new HashMap<>();
    }

    /**
     * Ajoute une option à gérer.
     *
     * @param name        Nom de l'option (ex : "file").
     * @param description Description de l'option pour l'aide.
     */
    public void addOption(String name, String description) {
        options.put(name, description);
    }

    /**
     * Parse les arguments de la ligne de commande.
     *
     * @param args Tableau des arguments passés au programme.
     */
    public void parse(String[] args) {
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];

            if (arg.startsWith("--")) {
                String optionName = arg.substring(2);

                // Vérifie si l'option attend une valeur
                if (options.containsKey(optionName)) {
                    String value = (i + 1 < args.length && !args[i + 1].startsWith("--")) ? args[++i] : null;
                    parsedArguments.put(optionName, value);
                } else {
                    System.err.println("Option inconnue : " + optionName);
                }
            }
        }
    }

    /**
     * Vérifie si une option a été spécifiée.
     *
     * @param name Nom de l'option.
     * @return true si l'option est présente, sinon false.
     */
    public boolean hasOption(String name) {
        return parsedArguments.containsKey(name);
    }

    /**
     * Récupère la valeur associée à une option.
     *
     * @param name Nom de l'option.
     * @return La valeur associée, ou null si non définie.
     */
    public String getOptionValue(String name) {
        return parsedArguments.get(name);
    }

    /**
     * Affiche l'aide pour toutes les options disponibles.
     */
    public void printHelp() {
        System.out.println("Options disponibles :");
        for (Map.Entry<String, String> entry : options.entrySet()) {
            System.out.println("--" + entry.getKey() + " : " + entry.getValue());
        }
    }
}
