<div align="justify" style="margin-right:25px;margin-left:25px">

# Application protocol

The goal of the protocol is to establish clear communication between the client and the server by defining the messages, actions, and commands they can exchange. It addresses the problem of standardizing these interactions to ensure seamless and efficient communication. The application protocol serves as the framework for specifying these interactions and facilitating the exchange of information between the client and the server.

The transport protocol used is TCP, with the server listening on port 2001. Messages and actions are encoded in text to keep the communication simple, with each message/action delimited by a newline character. All interactions are treated as text. The client is responsible for initiating the communication, while the server handles closing it. In case of an unknown message, action, or exception, the server responds with an error message to the client.

## Messages

- [Application protocol](#application-protocol)
  - [Messages](#messages)
    - [CREATE\_USER](#create_user)
      - [request](#request)
      - [response](#response)
    - [CONNECT\_TO\_SERVER](#connect_to_server)
      - [request](#request-1)
      - [response](#response-1)
    - [SET\_NAME](#set_name)
      - [request](#request-2)
      - [response](#response-2)
    - [SET\_PASSWORD](#set_password)
      - [request](#request-3)
      - [response](#response-3)
    - [GET\_USER\_NAMES](#get_user_names)
      - [request](#request-4)
      - [response](#response-4)
    - [GET\_ROOM\_NAMES](#get_room_names)
      - [request](#request-5)
      - [response](#response-5)
    - [CONNECT\_TO\_ROOM](#connect_to_room)
      - [request](#request-6)
      - [response](#response-6)
    - [CREATE\_ROOM](#create_room)
      - [request](#request-7)
      - [response](#response-7)
    - [PULL\_MESSAGES](#pull_messages)
      - [request](#request-8)
      - [response](#response-8)
    - [PUSH\_MESSAGE](#push_message)
      - [request](#request-9)
      - [response](#response-9)
    - [QUIT\_ROOM](#quit_room)
      - [request](#request-10)
      - [response](#response-10)
    - [QUIT\_SERVER](#quit_server)
      - [request](#request-11)
      - [response](#response-11)
    - [DELETE\_USER](#delete_user)
      - [request](#request-12)
      - [response](#response-12)
    - [DELETE\_ROOM](#delete_room)
      - [request](#request-13)
      - [response](#response-13)
  - [Errors](#errors)
  - [Diagram](#diagram)
    - [Connection au serveur](#connection-au-serveur)
    - [1. **Phase de configuration (Setup)** :](#1-phase-de-configuration-setup-)
    - [2. **Établissement de la connexion** :](#2-établissement-de-la-connexion-)
    - [3. **Création de l'utilisateur** :](#3-création-de-lutilisateur-)
    - [4. **Connexion de l'utilisateur** :](#4-connexion-de-lutilisateur-)
    - [Connexion à une salle et échange de messages](#connexion-à-une-salle-et-échange-de-messages)
    - [1. **Récupération des salles disponibles** :](#1-récupération-des-salles-disponibles-)
    - [2. **Récupération des utilisateurs dans Room1** :](#2-récupération-des-utilisateurs-dans-room1-)
    - [3. **Récupération des utilisateurs dans Room2** :](#3-récupération-des-utilisateurs-dans-room2-)
    - [4. **Connexion à une salle (Room1)** :](#4-connexion-à-une-salle-room1-)
    - [5. **Récupération des messages dans Room1** :](#5-récupération-des-messages-dans-room1-)
    - [6. **Envoi d'un message à Room1** :](#6-envoi-dun-message-à-room1-)

### CREATE_USER

Asks the server to create a new user. The user must provide a name and a password.

#### request

```text
CREATE_USER <name> <password>
```

#### response

- `OK` - the user was created successfully
- `ERROR <code>` - an error occurred while creating the user. The error code is
  an integer between 1 and 1 inclusive. The error code is as follow:
  - `1` - the name is already taken

### CONNECT_TO_SERVER

Asks the server to connect the client. The client must provide a name and a password.

#### request

```text
CONNECT_TO_SERVER <name> <password>
```

#### response

- `OK` - the client was connected successfully
- `ERROR <code>` - an error occurred while connecting the client. The error code is
  an integer between 1 and 1 inclusive. The error code is as follow:
  - `1` - the user or password is incorrect

### SET_NAME

Asks the server to set the name of the client. The client must provide a name.

#### request

```text
SET_NAME <name> <password> <newName>
```

#### response

- `OK` - the name was set successfully	
- `ERROR <code>` - an error occurred while sending the message. The error code is
  an integer between 1 and 1 inclusive. The error code is as follow:
  - `1` - the name is already taken

### SET_PASSWORD

Asks the server to set the password of the client. The client must provide the old password and the new password.

#### request

```text
SET_PASSWORD <name> <password> <newPassword>
```

#### response

- `OK` - the password was set successfully
- `ERROR <code>` - an error occurred while sending the message. The error code is
  an integer between 1 and 1 inclusive. The error code is as follow:
  - `1` - the password cannot be empty

### GET_USER_NAMES

Asks the server to get the list of all the names of the users in a room. The client must provide the name of the room.

#### request

```text
GET_USER_NAMES <applicantName> <roomName>
```

#### response

- `OK <name1> <name2> ...` - the list of names was retrieved successfully
- `ERROR <code>` - an error occurred while sending the message. The error code is
  an integer between 1 and 1 inclusive. The error code is as follow:
  - `1` - the room does not exist

### GET_ROOM_NAMES

Asks the server to get the list of all the rooms.

#### request

```text
GET_ROOM_NAMES <applicantName>
```

#### response

- `OK <room1> <room2> ...` - the list of rooms was retrieved successfully
- `ERROR <code>` - an error occurred while sending the message. The error code is
  an integer between 1 and 1 inclusive. The error code is as follow:
  - `1` - no room exists

### CONNECT_TO_ROOM

Asks the server to connect the client to a room. If the room needs a password, the client must provide it.

#### request

```text
CONNECT_TO_ROOM <userName> <roomName> <password>
```

#### response

- `OK` - the client was connected to the room successfully
- `ERROR <code>` - an error occurred while connecting the client to the room. The error code is
  an integer between 1 and 1 inclusive. The error code is as follow:
  - `1` - the room does not exist
  - `2` - the password is incorrect

### CREATE_ROOM

Asks the server to create a new room. The client must provide a name and a password.

If no password is provided, the room is created without a password.

#### request

```text
CREATE_ROOM <creatorName> <roomName> <roomPassword>
```

#### response

- `OK` - the room was created successfully
- `ERROR <code>` - an error occurred while creating the room. The error code is
  an integer between 1 and 1 inclusive. The error code is as follow:
  - `1` - the room name is already taken

### PULL_MESSAGES

Asks the server to get the list of all the messages in a room. If the room needs a password, the client must provide it.

We decide to add the password to the request because otherwise someone could get the messages of a room without being connected to it.

#### request

```text
GET_MESSAGES <userName> <roomName>
```

#### response

- `OK <message1> <message2> ...` - the list of messages was retrieved successfully
- `ERROR <code>` - an error occurred while sending the message.

### PUSH_MESSAGE

#### request

```text
PUSH_MESSAGE <userName> <roomName> <content>
```
#### response

- `OK` - the message was sent successfully
- `ERROR <code>` - an error occurred while sending the message. 

### QUIT_ROOM

#### request

```text
QUIT_ROOM <userName> <roomName>
```

#### response

- `OK` - the client was disconnected from the room successfully
- `ERROR <code>` - an error occurred while sending the message. 

### QUIT_SERVER

#### request

```text
QUIT_SERVER <userName>
```

#### response

- `OK` - the client was disconnected from the server successfully
- `ERROR <code>` - an error occurred while sending the message.

### DELETE_USER

#### request

```text
DELETE_USER <userName> <passwordApplicant>
```

#### response

- `OK` - the user was deleted successfully
- `ERROR <code>` - an error occurred while sending the message. 

### DELETE_ROOM

#### request

```text
DELETE_ROOM <userName> <userPassowrd> <roomName> <roomPassword>
```

#### response

- `OK` - the room was deleted successfully
- `ERROR <code>` - an error occurred while sending the message.

## Errors

- `ERROR <code>` - an error occurred while processing the request. The error code is
  an integer between 1 and 10 inclusive. The error codes are as follows:
- `1` - Missing arguments
- `2` - User name doesn't exist
- `3` - User name already taken
- `4` - User not connected to server
- `4` - User not connected to room
- `5` - Wrong user's password
- `6` - Room name dosen't exist
- `7` - Room name already taken
- `8` - User isn't admin
- `9` - Wrong room's password
- `10` - Failed to store, please try again

## Diagram

### Connection au serveur

![Connection diagram](/imgs/connect.svg)

### 1. **Phase de configuration (Setup)** :
   - Le diagramme commence par une étape de configuration initiale entre le **client** et le **serveur**.

### 2. **Établissement de la connexion** :
   - Le **client** envoie une demande pour établir une connexion avec le **serveur**.
   - Le **serveur** répond pour confirmer que la connexion a été établie avec succès.

### 3. **Création de l'utilisateur** :
   - Le **client** envoie une commande `CREATE_USER` avec un nom d'utilisateur (`USER5`) et un mot de passe (`PWD`) au **serveur**.
   - Le **serveur** traite la demande, crée l'utilisateur, et répond avec `OK` pour indiquer que la création a été effectuée avec succès.

### 4. **Connexion de l'utilisateur** :
   - Le **client** envoie une commande `CONNECT_TO_SERVER` avec le même nom d'utilisateur (`USER5`) et le mot de passe (`PWD`) pour se connecter au serveur.
   - Le **serveur** authentifie les informations fournies et répond avec `OK` pour confirmer que l'utilisateur est connecté avec succès.

### Connexion à une salle et échange de messages

![Chat diagram](/imgs/chat.svg)

### 1. **Récupération des salles disponibles** :
   - Le **client** envoie une commande `GET_ROOMS` au **serveur** pour obtenir la liste des salles disponibles.
   - Le **serveur** répond avec `OK Room1 Room2`, indiquant que deux salles, `Room1` et `Room2`, sont disponibles.

### 2. **Récupération des utilisateurs dans Room1** :
   - Le **client** envoie une commande `GET_NAMES Room1` au **serveur** pour obtenir la liste des utilisateurs connectés dans la salle `Room1`.
   - Le **serveur** répond avec `OK User`, indiquant qu'un utilisateur nommé `User` est présent dans cette salle.

### 3. **Récupération des utilisateurs dans Room2** :
   - Le **client** envoie une commande `GET_NAMES Room2` au **serveur** pour obtenir la liste des utilisateurs connectés dans la salle `Room2`.
   - Le **serveur** répond avec `OK User3 User4`, indiquant que deux utilisateurs, `User3` et `User4`, sont présents dans cette salle.

### 4. **Connexion à une salle (Room1)** :
   - Le **client** envoie une commande `CONNECT_TO_ROOM Room1` pour se connecter à la salle `Room1`.
   - Le **serveur** répond avec `OK`, confirmant que le client est connecté à cette salle.

### 5. **Récupération des messages dans Room1** :
   - Le **client** envoie une commande `PULL_MESSAGES Room1` pour récupérer les messages disponibles dans la salle `Room1`.
   - Le **serveur** répond avec `OK Message1 Message2`, contenant les deux messages présents dans la salle.

### 6. **Envoi d'un message à Room1** :
   - Le **client** envoie une commande `PUSH_MESSAGE Hello` pour envoyer le message `Hello` à la salle `Room1`.
   - Le **serveur** répond avec `OK`, confirmant que le message a été envoyé avec succès.



</div>