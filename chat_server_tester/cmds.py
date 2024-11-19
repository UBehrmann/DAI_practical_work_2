cmds_users_create = [
    "CREATE_USER user1 pw1",  # Création d'utilisateur
    "CREATE_USER user2 pw2",  # Création d'utilisateur
    "CREATE_USER user3 pw3",  # Création d'utilisateur
    "CREATE_USER user4 pw4",  # Création d'utilisateur
]

#######################################################################################################################

cmds_users_create_test = [
    "CREATE_USER user1 password123",  # Création d'utilisateur
    "CREATE_USER user2",  # Création d'utilisateur sans mot de passe
    "CREATE_USER user1 password",  # Création d'un utilisateur déjà existant
]
cmds_users_set_name_test = [
    "SET_NAME user1 pw1",  # Changement de nom utilisateur
    "SET_NAME user1 pw1 us1",  # Changement de nom utilisateur
    "SET_NAME us1 pw1 user2",  # Changement de nom utilisateur
    "SET_NAME us1 pw1 user1",  # Changement de nom utilisateur
]
cmds_users_set_password_test = [
    "SET_PASSWORD user1 pw1",  # Changement de mot de passe
    "SET_PASSWORD user1 pw1 pw2",  # Changement de mot de passe
    "SET_PASSWORD user1 pw1 pw3",  # Changement de mot de passe
    "SET_PASSWORD user1 pw2 pw1",  # Changement de mot de passe
]

cmds_users_connect_to_server_test =[
    "CONNECT_TO_SERVER user1",
    "CONNECT_TO_SERVER user239492309 pw1",
    "CONNECT_TO_SERVER user1 pw239492309",
    "CONNECT_TO_SERVER user1 pw1",
]

cmds_users_create_room_test =[
    "CREATE_ROOM user1 ",
    "CREATE_ROOM user1 myRoom",
    "CREATE_ROOM user1233213 myRoom pwRm",
    "CREATE_ROOM user2 myRoom pwRm",
    "CREATE_ROOM user1 myRoom pwRm",
    "CREATE_ROOM user1 myRoom pwRm",
]

cmds_users_connect_to_room_test =[
    "CONNECT_TO_ROOM user123",
    "CONNECT_TO_ROOM user123 myRoom",
    "CONNECT_TO_ROOM user123 myRoom pwRm",

    "CONNECT_TO_ROOM user2 myRoom2 pwRm2",
    "CONNECT_TO_SERVER user2 pw2",
    "CONNECT_TO_ROOM user2 myRoom pwRm2",
    "CONNECT_TO_ROOM user2 myRoom pwRm",
]


commands = [
    "CREATE_ROOM room1 password123",  # Création d'une salle avec mot de passe
    "CREATE_ROOM room2",  # Création d'une salle sans mot de passe

    "CONNECT_TO_ROOM user1 room1 password123",  # Connexion à une salle avec mot de passe
    "CONNECT_TO_ROOM user2 room2",  # Connexion à une salle sans mot de passe

    "CONNECT_TO_ROOM user1 room1 fauxMotDePasse",  # Connexion avec mot de passe incorrect
    "CONNECT_TO_ROOM user2 room3",  # Connexion à une salle inexistante



    "GET_NAMES room1",  # Liste des utilisateurs dans une salle
    "GET_NAMES room3",  # Salle inexistante

    "GET_ROOMS",  # Liste des salles disponibles

    "UNKNOWN_COMMAND",  # Commande non valide pour tester les erreurs
]