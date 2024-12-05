package ch.heigvd.dai.Managers;

public class ApplicationDataLoaderManager {

    private static void print_separation(){
        System.out.println("--------------------");
    }

    public static void load() {
        // Charger les utilisateurs
        print_separation();
        System.out.println("Loading users...");
        if (UserManager.loadUsers()) {
            System.out.println(" > Users loaded successfully.");
        } else {
            System.err.println(" > Failed to load users.");
        }
        print_separation();

        // Charger les salles
        print_separation();
        System.out.println("Loading rooms...");
        if (!UserManager.getUsers().isEmpty()) { // Vérifiez si les utilisateurs ont été chargés
            RoomManager.loadRooms(UserManager.getUsers());
            System.out.println(" > Rooms loaded successfully.");
        } else {
            System.err.println(" > Failed to load rooms: No users available.");
        }
        print_separation();
    }

    public static void print() {
        // Afficher les utilisateurs chargés
        print_separation();
        System.out.println("Users:");
        UserManager.getUsers().forEach((name, user) -> {
            System.out.println(" > " + name + " (online: " + user.isOnline() + ")");
        });
        print_separation();

        // Afficher les salles et leurs détails
        print_separation();
        System.out.println("Rooms:");
        RoomManager.getRooms().forEach((roomName, room) -> {
            System.out.println(" > Room Name: " + roomName);
            System.out.println("   - Admin: " + (room.getAdmin() != null ? room.getAdmin().getName() : "No admin"));

            // Afficher les utilisateurs dans la salle
            System.out.println("   - Users in Room:");
            room.getUsers().forEach(user -> System.out.println("       - " + user.getName()));

            // Afficher les messages de la salle
            System.out.println("   - Messages:");
            room.pullMessage().forEach(message -> System.out.println("       - "  + message.getContent()));
        });
        print_separation();
    }
}
