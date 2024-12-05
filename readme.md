# DAI practical work 2

## Authors

- Rodrigo Lopes Dos Santos
- Urs Behrmann

## Introduction / purpose

You can find the purpose of the project in the separate file which also describes the protocol and gives an overview of the project.

[Application protocol](protocol.md)

## How to use the applications

Examples and outputs

## How to clone and build the applications


1. Clone the repository to your local machine.
2. Open a terminal and navigate to the client project directory.
3. Run the following command:


```bash

mvn clean package

```

## How to run the jar files

Run the following command:

```bash

java -jar target/chat_client-1.0-SNAPSHOT-all.jar client -H "aaa.bbb.ccc.ddd" -p XXXX

```

The arguments are as follows:

- -H or --host: The IP address of the server.
- -p or --port: The port number of the server.

## How to build and publish the application with docker

1. Clone the repository to your local machine.
2. Open a terminal and navigate to the server project directory.
3. Run the following command:

```bash

docker build -t chat-server:1.0 .

```

## How to use the application with docker

Run the following command:

```bash

docker run -d --name chat-server:1.0

```
