# Application protocol

## Introduction

Section 1 - Overview

This section defines the purpose of the protocol:

    What is the goal of the protocol?
    What is the problem that it tries to solve?
    What the application protocol is used for?

Section 2 - Transport protocol

This section defines the transport protocol used by the application protocol:

    What protocol(s) is/are involved? On which port(s)?
    How are messages/actions encoded?
    How are messages/actions delimited?
    How are messages/actions treated (text or binary)?
    Who initiates/closes the communication?
    What happens on an unknown message/action/exception?

Section 3 - Messages

This section defines the messages that can be exchanged between the client and the server.

    What are the messages/actions?
    What are the parameters?
    What are the return values?
    What are the exceptions?


- The application protocol defines the overview of the network application
- The application protocol defines the transport protocol(s) the network application uses
- The application protocol defines the available messages/actions/commands for the client/server to communicate
- The application protocol defines the success/error codes and their explanations
- The application protocol is described using successful and unsuccessful examples with one or multiple diagrams

## Commands

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

Asks the server to create a new user.

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

Asks the server to connect the client.

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

Asks the server to set the name of the client.

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

Asks the server to set the password of the client.

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

Asks the server to get the list of all the names of the users in a room.

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

Asks the server to create a new room.

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


