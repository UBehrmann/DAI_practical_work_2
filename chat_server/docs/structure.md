 Documentation de la structure du code

## Structure principale

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

---