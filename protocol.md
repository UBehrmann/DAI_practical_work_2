# Application protocol

The goal of the protocol is to establish clear communication between the client and the server by defining the messages, actions, and commands they can exchange. It addresses the problem of standardizing these interactions to ensure seamless and efficient communication. The application protocol serves as the framework for specifying these interactions and facilitating the exchange of information between the client and the server.

The transport protocol used is TCP, with the server listening on port 2001. Messages and actions are encoded in text to keep the communication simple, with each message/action delimited by a newline character. All interactions are treated as text. The client is responsible for initiating the communication, while the server handles closing it. In case of an unknown message, action, or exception, the server responds with an error message to the client.

## Messages

- [CREATE_USER](#create_user)
- [CONNECT](#connect)
- [SET_NAME](#set_name)
- [SET_PASSWORD](#set_password)
- [GET_NAMES](#get_names)
- [GET_ROOMS](#get_rooms)
- [CONNECT_TO_ROOM](#connect_to_room)
- [CREATE_ROOM](#create_room)
- [GET_MESSAGES](#get_messages)
- [SEND_MESSAGE](#send_message)

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

### CONNECT

Asks the server to connect the client. The client must provide a name and a password.

#### request

```text
CONNECT <name> <password>
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
SET_NAME <name>
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
SET_PASSWORD <old_password> <password>
```

#### response

- `OK` - the password was set successfully
- `ERROR <code>` - an error occurred while sending the message. The error code is
  an integer between 1 and 1 inclusive. The error code is as follow:
  - `1` - the password cannot be empty

### GET_NAMES

Asks the server to get the list of all the names of the users in a room. The client must provide the name of the room.

#### request

```text
GET_NAMES <room name>
```

#### response

- `OK <name1> <name2> ...` - the list of names was retrieved successfully
- `ERROR <code>` - an error occurred while sending the message. The error code is
  an integer between 1 and 1 inclusive. The error code is as follow:
  - `1` - the room does not exist

### GET_ROOMS

Asks the server to get the list of all the rooms.

#### request

```text
GET_ROOMS
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
CONNECT_TO_ROOM <room name> <password>
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
CREATE_ROOM <room name> <password>
```

#### response

- `OK` - the room was created successfully
- `ERROR <code>` - an error occurred while creating the room. The error code is
  an integer between 1 and 1 inclusive. The error code is as follow:
  - `1` - the room name is already taken

### GET_MESSAGES

Asks the server to get the list of all the messages in a room. If the room needs a password, the client must provide it.

We decide to add the password to the request because otherwise someone could get the messages of a room without being connected to it.

#### request

```text
GET_MESSAGES <room name> <password>
```

#### response

- `OK <message1> <message2> ...` - the list of messages was retrieved successfully
- `ERROR <code>` - an error occurred while sending the message. The error code is
  an integer between 1 and 1 inclusive. The error code is as follow:
  - `1` - the room does not exist
  - `2` - the password is incorrect

## Diagram


