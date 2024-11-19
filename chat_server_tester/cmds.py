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

# USERS     : user1, user2, user3, user4
# ONLINE    : user1, user2
# ROOMS     :  myRoom (pwRm) -> user1, user2
cmds_users_get_room_names_test =[
    "CONNECT_TO_SERVER user3 pw3",

    "CREATE_ROOM user3 myPrivateRoom pwPvRm",
    "CONNECT_TO_ROOM user4 myPrivateRoom pwPvR",

    "GET_ROOM_NAMES user12312",
    "GET_ROOM_NAMES user4",

    "CONNECT_TO_SERVER user4 pw4",
    "GET_ROOM_NAMES user4"
]

# USERS     :   user1, user2, user3, user4
# ONLINE    :   user1, user2, user3, user4
# ROOMS     :   myRoom (pwRm) -> user1, user2
#               myPrivateRoom (pwPvRm) -> user3
cmds_users_get_users_names_test =[
    "CREATE_USER user5 pw5",  # Création d'utilisateur

    "GET_USER_NAMES user5",
    "GET_USER_NAMES user5 myRoom",

    "CONNECT_TO_SERVER user5 pw5",
    "GET_USER_NAMES user5 myRoom2",

    "GET_USER_NAMES user5 myRoom",
    "GET_USER_NAMES user5 myPrivateRoom",

    "CONNECT_TO_ROOM user5 myPrivateRoom pwPvRm",
    "CONNECT_TO_ROOM user4 myPrivateRoom pwPvRm",
    "GET_USER_NAMES user5 myPrivateRoom",

]
