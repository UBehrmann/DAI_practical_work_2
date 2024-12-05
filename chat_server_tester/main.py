import socket
import threading
import time
import cmds
from cmds import cmds_users_create, cmds_users_set_name_test, cmds_users_set_password_test, \
    cmds_users_connect_to_server_test, cmds_users_create_room_test, cmds_users_connect_to_room_test, \
    cmds_users_get_room_names_test, cmds_users_get_users_names_test, cmds_users_push_message_test, \
    cmds_users_quit_room_test, cmds_users_quit_server_test, cmds_users_delete_room_test, \
    cmds_users_delete_user_test


def send_command(client_id, host, port, command):
    """Envoie une commande au serveur et affiche la réponse."""
    try:
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


def simulate_clients(host="127.0.0.1", port=8080, commands = []):
    """Teste toutes les commandes du serveur avec plusieurs clients."""


    threads = []
    for client_id, command in enumerate(commands, start=1):
        # Lancer un thread par commande
        thread = threading.Thread(target=send_command, args=(client_id, host, port, command))
        threads.append(thread)
        thread.start()
        time.sleep(0.1)  # Petite pause pour éviter des collisions

    # Attendre que tous les threads se terminent
    for thread in threads:
        thread.join()
    print("Tous les tests des commandes sont terminés.")


if __name__ == "__main__":
    # Tester le serveur avec les commandes
    simulate_clients(host="127.0.0.1", port=1234, commands=cmds_users_create)
    print("\n\n")
    simulate_clients(host="127.0.0.1", port=1234, commands=cmds_users_set_name_test)
    print("\n\n")
    simulate_clients(host="127.0.0.1", port=1234, commands=cmds_users_set_password_test)
    print("\n\n")
    simulate_clients(host="127.0.0.1", port=1234, commands=cmds_users_connect_to_server_test)
    print("\n\n")
    simulate_clients(host="127.0.0.1", port=1234, commands=cmds_users_create_room_test)
    print("\n\n")
    simulate_clients(host="127.0.0.1", port=1234, commands=cmds_users_connect_to_room_test)
    print("\n\n")
    simulate_clients(host="127.0.0.1", port=1234, commands=cmds_users_get_room_names_test)
    print("\n\n")
    simulate_clients(host="127.0.0.1", port=1234, commands=cmds_users_get_users_names_test)
    print("\n\n")
    simulate_clients(host="127.0.0.1", port=1234, commands=cmds_users_push_message_test)
    print("\n\n")
    simulate_clients(host="127.0.0.1", port=1234, commands=cmds_users_quit_room_test)
    print("\n\n")
    simulate_clients(host="127.0.0.1", port=1234, commands=cmds_users_quit_server_test)
    print("\n\n")
    simulate_clients(host="127.0.0.1", port=1234, commands=cmds_users_delete_room_test)
    print("\n\n")
    simulate_clients(host="127.0.0.1", port=1234, commands=cmds_users_delete_user_test)