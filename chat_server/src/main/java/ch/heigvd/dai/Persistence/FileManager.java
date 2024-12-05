package ch.heigvd.dai.Persistence;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    /**
     * Sauvegarde une liste de lignes dans un fichier.
     */
    public static boolean saveToFile(String fileName, List<String> lines, boolean append) {
        File file = new File(fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, append))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Charge les lignes d'un fichier dans une liste.
     */
    public static List<String> loadFromFile(String fileName) {
        List<String> lines = new ArrayList<>();
        File file = new File(fileName);
        if (!file.exists()) {
            return lines;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}