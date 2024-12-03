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

# USERS     :   user1, user2, user3, user4, user5
# ONLINE    :   user1, user2, user3, user4, user5
# ROOMS     :   myRoom (pwRm) -> user1, user2
#               myPrivateRoom (pwPvRm) -> user3, user4, user5
cmds_users_push_message_test =[
    "PUSH_MESSAGE user6 myRoooom",
    "PUSH_MESSAGE user6 myRoooom hello",

    "CREATE_USER user6 pw6",  # Création d'utilisateur
    "PUSH_MESSAGE user6 myRoooom hello",

    "CONNECT_TO_SERVER user6 pw6",
    "PUSH_MESSAGE user6 myRoooom hello",
    "PUSH_MESSAGE user6 myRoom hello",

    "CONNECT_TO_ROOM user6 myRoom pwRm",
    "PUSH_MESSAGE user6 myRoom hello",
    "PUSH_MESSAGE user1 myRoom hi",
    "PUSH_MESSAGE user6 myRoom how are you ?",
    "PUSH_MESSAGE user1 myRoom fine and you ?",
    "PUSH_MESSAGE user6 myRoom yes, fine !",

    "PULL_MESSAGES user6 myRoom",
]

# USERS     :   user1, user2, user3, user4, user5, user6
# ONLINE    :   user1, user2, user3, user4, user5, user6
# ROOMS     :   myRoom (pwRm) -> user1, user2, user6
#               myPrivateRoom (pwPvRm) -> user3, user4, user5
cmds_users_quit_room_test =[
    "QUIT_ROOM user7",
    "QUIT_ROOM user7 myRoom2",
    "CREATE_USER user7 pw7",  # Création d'utilisateur
    "QUIT_ROOM user7 myRoom2",
    "CONNECT_TO_SERVER user7 pw7",
    "QUIT_ROOM user7 myRoom2",

    "QUIT_ROOM user7 myRoom",
    "CONNECT_TO_ROOM user7 myRoom pwRm",
    "GET_USER_NAMES user7 myRoom",
    "QUIT_ROOM user7 myRoom",
    "GET_USER_NAMES user6 myRoom",
]

# USERS     :   user1, user2, user3, user4, user5, user6, user7
# ONLINE    :   user1, user2, user3, user4, user5, user6, user7
# ROOMS     :   myRoom (pwRm) -> user1, user2, user6
#               myPrivateRoom (pwPvRm) -> user3, user4, user5
cmds_users_quit_server_test =[
    "QUIT_SERVER",
    "QUIT_SERVER user8",
    "CREATE_USER user8 pw8",  # Création d'utilisateur
    "QUIT_SERVER user8",

    "CONNECT_TO_SERVER user8 pw8",
    "QUIT_SERVER user8",
    "CONNECT_TO_ROOM user8 myRoom pwRm",
    "CONNECT_TO_SERVER user8 pw8",
    "CONNECT_TO_ROOM user8 myRoom pwRm",
    "GET_USER_NAMES user6 myRoom",
]

# USERS     :   user1, user2, user3, user4, user5, user6, user7, user8
# ONLINE    :   user1, user2, user3, user4, user5, user6, user7, user8
# ROOMS     :   myRoom (pwRm) -> user1, user2, user6, user8
#               myPrivateRoom (pwPvRm) -> user3, user4, user5
cmds_users_delete_room_test =[
    "CREATE_ROOM user8 myRoom8 pwRm8",
    "CONNECT_TO_ROOM user7 myRoom8 pwRm8",
    "CONNECT_TO_ROOM user6 myRoom8 pwRm8",
    "GET_ROOM_NAMES user8",

    "GET_USER_NAMES user6 myRoom8",

    "QUIT_SERVER user6",

    "DELETE_ROOM user6",
    "DELETE_ROOM user9 pw9 myRoom88",
    "DELETE_ROOM user6 pw6 myRoom88",
    "CONNECT_TO_SERVER user6 pw6",
    "DELETE_ROOM user6 pw6 myRoom88",
    "DELETE_ROOM user6 pw6 myRoom8",
    "DELETE_ROOM user8 pw8 myRoom8",
    "GET_ROOM_NAMES user8",
]

# USERS     :   user1, user2, user3, user4, user5, user6, user7, user8
# ONLINE    :   user1, user2, user3, user4, user5, user6, user7, user8
# ROOMS     :   myRoom (pwRm) -> user1, user2, user6, user8
#               myPrivateRoom (pwPvRm) -> user3, user4, user5
cmds_users_delete_user_test =[
    "DELETE_USER",
    "DELETE_USER user9 pw9",

    "DELETE_USER user7 pw7",
    "GET_USER_NAMES user6 myRoom",
    "GET_USER_NAMES user5 myPrivateRoom",

    "DELETE_USER user8 pw8",
    "GET_USER_NAMES user6 myRoom",
    "GET_USER_NAMES user5 myPrivateRoom",

    "DELETE_USER user4 pw4",
    "GET_USER_NAMES user6 myRoom",
    "GET_USER_NAMES user5 myPrivateRoom",

    "DELETE_USER user1 pw1",
    "GET_USER_NAMES user6 myRoom",
    "GET_USER_NAMES user5 myPrivateRoom",
]

# USERS     :   user2, user3, user5, user6
# ONLINE    :   user2, user3, user5, user6
# ROOMS     :   myPrivateRoom (pwPvRm) -> user3, user5

