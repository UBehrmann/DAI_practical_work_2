import socket
import threading
import time


def send_command(client_id, host, port, command):
    """Envoie une commande au serveur et affiche la réponse."""
    try:
        # Créer une socket et se connecter au serveur
        with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as client_socket:
            client_socket.connect((host, port))
            print(f"Client {client_id}: Connecté au serveur {host}:{port}")

            # Envoyer la commande avec '\n' à la fin
            client_socket.sendall((command + "\n").encode("utf-8"))
            print(f"Client {client_id}: Commande envoyée -> {command}")

            # Recevoir une réponse du serveur
            response = client_socket.recv(1024).decode("utf-8").strip()
            print(f"Client {client_id}: Réponse du serveur -> {response}")

    except Exception as e:
        print(f"Client {client_id}: Erreur -> {e}")


def simulate_clients(host="127.0.0.1", port=8080):
    """Teste différentes commandes du serveur avec plusieurs clients."""
    commands = [
        "CREATE_ROOM room1 password123",  # Création d'une salle avec mot de passe
        "CREATE_ROOM room2",              # Création d'une salle sans mot de passe
        "CONNECT_TO_ROOM room1 password123",  # Connexion à une salle avec mot de passe
        "CONNECT_TO_ROOM room2",          # Connexion à une salle sans mot de passe
        "GET_ROOMS",                      # Liste des salles disponibles
        "GET_MESSAGES room1 password123", # Récupérer les messages d'une salle
        "SET_NAME ClientName",            # Définir un nom pour l'utilisateur
        "UNKNOWN_COMMAND",                # Commande non valide pour tester les erreurs
    ]

    threads = []
    for client_id, command in enumerate(commands, start=1):
        # Lancer un thread par commande
        thread = threading.Thread(target=send_command, args=(client_id, host, port, command))
        threads.append(thread)
        thread.start()
        # Petite pause pour éviter des collisions exactes dans l'ordre
        time.sleep(0.1)

    # Attendre que tous les threads se terminent
    for thread in threads:
        thread.join()
    print("Tous les tests des commandes sont terminés.")


if __name__ == "__main__":
    # Tester le serveur avec les commandes
    simulate_clients(host="127.0.0.1", port=1234)
