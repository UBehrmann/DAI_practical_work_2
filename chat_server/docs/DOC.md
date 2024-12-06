# Structure principale

## Gestion des clients
### 1. **Main**
Le point d'entrée principal du programme, situé dans la classe `Main`. Ce fichier configure et démarre le serveur, puis gère les connexions des clients.

#### Fonctionnalités principales :
- Analyse des arguments de la ligne de commande à l'aide de `CommandLineArgumentsManager`.
- Chargement des données persistantes via `ApplicationDataLoaderManager`.
- Gestion des connexions clients et création de threads via `ClientHandler`.

### 2. **ClientHandler**
Une classe dédiée à la gestion des connexions clients, implémentant `Runnable`.

#### Fonctionnalités principales :
- Gère une connexion client via un `Socket`.
- Lit les requêtes envoyées par le client et utilise `CommandFactory` pour exécuter la commande appropriée.
- Retourne les réponses au client.


## Gestion des commandes

### 3. **CommandFactory**
Une classe centrale pour mapper les noms des commandes (ex : `CREATE_USER`, `CONNECT_TO_SERVER`) à leurs implémentations respectives.

#### Fonctionnalités principales :
- Fournit une instance de la commande associée en fonction d'une action reçue.
- Les commandes sont organisées en plusieurs catégories :
  - **Création** : `CreateUser`, `CreateRoom`
  - **Connexion** : `ConnectToServer`, `ConnectToRoom`
  - **Messages** : `PushMessage`, `PullMessages`
  - **Suppression** : `DeleteUser`, `DeleteRoom`
  - **Paramètres** : `SetName`, `SetPassword`

## Gestion des données

### **RoomManager** et **UserManager**
Ces classes gèrent les entités principales de l'application : les utilisateurs et les salles.

#### Fonctionnalités principales :
- `RoomManager` :
  - Création, suppression et mise à jour des salles.
  - Ajout et suppression d'utilisateurs dans les salles.
  - Gestion des messages dans les salles.
- `UserManager` :
  - Création, suppression et mise à jour des utilisateurs.
  - Chargement des utilisateurs depuis le stockage persistant.

## Gestion du stockage et chargement

### **FileManager**
Cette classe fournit des méthodes utilitaires pour lire et écrire des fichiers.

#### Fonctionnalités principales :
- **Sauvegarde dans un fichier** :
  - Permet de sauvegarder une liste de chaînes de caractères dans un fichier, avec une option pour ajouter ou écraser les données existantes.
- **Chargement depuis un fichier** :
  - Charge les lignes d'un fichier dans une liste, tout en vérifiant l'existence du fichier.

### **RoomPersistenceManager et UserPersistenceManager**
Cette classe gère la persistance des données relatives aux salles et des utilisateurs.

#### Fonctionnalités principales :
- `RoomPersistenceManager`
    - **Sauvegarde des informations de salle** :
        - Enregistre le nom et le mot de passe d'une salle dans un fichier global `rooms.txt`.
    - **Chargement des informations de salle** :
        - Charge les informations des salles depuis `rooms.txt`, incluant les détails de l'administrateur.
    - **Sauvegarde des détails de salle** :
        - Enregistre les utilisateurs, messages, et l'administrateur d'une salle dans un fichier spécifique à la salle.
    - **Chargement des détails de salle** :
        - Charge les utilisateurs et messages d'une salle en récupérant les données à partir d'un fichier.
    - **Suppression des informations et des détails de salle** :
        - Supprime une salle et ses données associées.
- `UserPersistenceManager`
    - **Sauvegarde des utilisateurs** :
        - Ajoute un nouvel utilisateur dans le fichier global `users.txt`.
    - **Chargement des utilisateurs** :
        - Récupère tous les utilisateurs enregistrés depuis `users.txt`.
    - **Suppression d’un utilisateur** :
        - Retire un utilisateur du fichier global `users.txt`.

# Dockerfile
Ce Dockerfile est utilisé pour créer une image Docker de l'application Java basée sur un fichier JAR.

## Étapes principales :
1. **Image de base** :
   - Utilise l'image officielle `eclipse-temurin:22-jdk` pour fournir un environnement Java.

2. **Répertoire de travail** :
   - Définit `/app` comme répertoire de travail dans le conteneur.

3. **Copie du fichier JAR** :
   - Copie le fichier JAR (`chat_server-1.0-SNAPSHOT.jar`) dans le conteneur sous le nom `app.jar`.

4. **Exposition de port** :
   - Expose le port `1234` pour permettre la communication avec l'application.

5. **Commande principale (ENTRYPOINT)** :
   - Configure l'exécution du fichier JAR avec la commande : `java -jar app.jar`.

6. **Commande par défaut (CMD)** :
   - Définit `--help` comme argument par défaut, modifiable lors de l'exécution du conteneur.

# Utilisation

## Arguments

   Vous pouvez personnaliser le comportement du serveur avec les arguments suivants :

   - `--port` (par défaut : `1234`) : Spécifie le port d'écoute du serveur.
   - `--name` (par défaut : `my_chat_tcp_server`) : Définit un nom personnalisé pour le serveur.
   - `--load` (par défaut : `false`) : Charge les données de l'application avant de démarrer le serveur.
   - `--help` : Affiche le menu d'aide.

## Lancement du serveur (sans docker)

1. Ouvrir un terminal à la racine du projet. 
2. Compilation et construction du projet Maven avec Maven.
```bash
mvn clean package
```
3. Exécuter l'application sans arguments (par défaut).
```bash
java -jar target/chat_server-1.0-SNAPSHOT.jar
```
4. Exécuter l'application sans arguments (par défaut).
```bash
java -jar target/chat_server-1.0-SNAPSHOT.jar -name MyServer --port 3424
```

## Lancement du serveur (avec docker)
1. Créer le `Dockerfile` a la racine du projet.
2. Contruire l'image Docker.
```bash
docker build -t chat_server .
```
3. Supprimer le conteneur existant (si y on a)
```bash
docker rm chat_server_container
```
4. Lancer un nouveau conteneur
```bash
docker run -d -p 1234:1234 --name chat_server_container chat_server
```
```bash
docker run -d -p 1234:1234 --name chat_server_container chat_server --port 1234 --name my_chat_tcp_server --load false
```
```bash
docker run -d -p 5678:5678 --name chat_server_container chat_server --port 5678 --name custom_server --load true
```
5. Arreter le conteneur
```bash
docker stop chat_server_container
```
6. Lancer le conteneur
```bash
docker start chat_server_container
```
5. Lister les Conteneur actifs
```bash
docker ps
```
6. Voir les Logs du Conteneur
```bash
docker logs chat_server_container
```
7. Arréter le conteneur 
```bash
docker stop chat_server_container
```