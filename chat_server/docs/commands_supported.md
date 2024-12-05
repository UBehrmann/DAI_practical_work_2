
# Documentation des Commandes

## Structure des Commandes

Chaque commande implémente l'interface `Command`, qui définit une méthode :
- `String execute(String[] args, BufferedWriter out)` : exécute la commande avec les arguments spécifiés.

Les commandes sont gérées par la classe `CommandFactory`, qui mappe des chaînes d'action à des classes de commandes spécifiques.

---

## Liste des Commandes

### 1. `CREATE_USER`
- **Description** : Crée un nouvel utilisateur avec un nom et un mot de passe.
- **Utilisation** : `CREATE_USER <userName> <password>`
- **Classe associée** : `CreateUser`
- **Fichier** : [CreateUser.java](#)
- **Exemple** :
  ```
  CREATE_USER John Doe123
  ```

---

### 2. `CONNECT_TO_SERVER`
- **Description** : Connecte un utilisateur au serveur.
- **Utilisation** : `CONNECT_TO_SERVER <userName> <password>`
- **Classe associée** : `ConnectToServer`
- **Fichier** : [ConnectToServer.java](#)
- **Exemple** :
  ```
  CONNECT_TO_SERVER John Doe123
  ```

---

### 3. `SET_NAME`
- **Description** : Met à jour le nom d'un utilisateur. *(À tester)*
- **Utilisation** : `SET_NAME <userName> <newName>`
- **Classe associée** : `SetName`
- **Exemple** :
  ```
  SET_NAME John JohnDoe
  ```

---

### 4. `SET_PASSWORD`
- **Description** : Met à jour le mot de passe d'un utilisateur. *(À tester)*
- **Utilisation** : `SET_PASSWORD <userName> <newPassword>`
- **Classe associée** : `SetPassword`
- **Exemple** :
  ```
  SET_PASSWORD John NewPassword123
  ```

---

### 5. `CREATE_ROOM`
- **Description** : Crée une nouvelle salle avec un administrateur.
- **Utilisation** : `CREATE_ROOM <creatorName> <roomName> <roomPassword>`
- **Classe associée** : `CreateRoom`
- **Fichier** : [CreateRoom.java](#)
- **Exemple** :
  ```
  CREATE_ROOM John MyRoom Room123
  ```

---

### 6. `CONNECT_TO_ROOM`
- **Description** : Connecte un utilisateur à une salle.
- **Utilisation** : `CONNECT_TO_ROOM <userName> <roomName> <roomPassword>`
- **Classe associée** : `ConnectToRoom`
- **Fichier** : [ConnectToRoom.java](#)
- **Exemple** :
  ```
  CONNECT_TO_ROOM John MyRoom Room123
  ```

---

### 7. `GET_ROOM_NAMES`
- **Description** : Récupère les noms de toutes les salles disponibles.
- **Utilisation** : `GET_ROOM_NAMES <applicantName>`
- **Classe associée** : `GetRoomNames`
- **Fichier** : [GetRoomNames.java](#)
- **Exemple** :
  ```
  GET_ROOM_NAMES John
  ```

---

### 8. `GET_USER_NAMES`
- **Description** : Récupère les noms des utilisateurs connectés dans une salle.
- **Utilisation** : `GET_USER_NAMES <applicantName> <roomName>`
- **Classe associée** : `GetUserNames`
- **Fichier** : [GetUserNames.java](#)
- **Exemple** :
  ```
  GET_USER_NAMES John MyRoom
  ```

---

### 9. `PUSH_MESSAGE`
- **Description** : Envoie un message dans une salle.
- **Utilisation** : `PUSH_MESSAGE <applicantName> <roomName> <content>`
- **Classe associée** : `PushMessage`
- **Fichier** : [PushMessage.java](#)
- **Exemple** :
  ```
  PUSH_MESSAGE John MyRoom Hello Everyone!
  ```

---

### 10. `PULL_MESSAGES`
- **Description** : Récupère les messages d'une salle.
- **Utilisation** : `PULL_MESSAGES <applicantName> <roomName>`
- **Classe associée** : `PullMessages`
- **Fichier** : [PullMessages.java](#)
- **Exemple** :
  ```
  PULL_MESSAGES John MyRoom
  ```

---

### 11. `QUIT_ROOM`
- **Description** : Quitte une salle.
- **Utilisation** : `QUIT_ROOM <applicantName> <roomName>`
- **Classe associée** : `QuitRoom`
- **Exemple** :
  ```
  QUIT_ROOM John MyRoom
  ```

---

### 12. `QUIT_SERVER`
- **Description** : Déconnecte un utilisateur du serveur.
- **Utilisation** : `QUIT_SERVER <userName>`
- **Classe associée** : `QuitServer`
- **Exemple** :
  ```
  QUIT_SERVER John
  ```

---

### 13. `DELETE_USER`
- **Description** : Supprime un utilisateur.
- **Utilisation** : `DELETE_USER <applicantName> <passwordApplicant>`
- **Classe associée** : `DeleteUser`
- **Fichier** : [DeleteUser.java](#)
- **Exemple** :
  ```
  DELETE_USER John Doe123
  ```

---

### 14. `DELETE_ROOM`
- **Description** : Supprime une salle.
- **Utilisation** : `DELETE_ROOM <applicantName> <passwordApplicant> <roomName> <roomPassword>`
- **Classe associée** : `DeleteRoom`
- **Fichier** : [DeleteRoom.java](#)
- **Exemple** :
  ```
  DELETE_ROOM John Doe123 MyRoom Room123
  ```

---

## Notes

- Les erreurs possibles lors de l'exécution d'une commande sont définies dans `ErrorCodes`.
- Les fichiers spécifiques pour chaque commande se trouvent dans le dossier `ch.heigvd.dai.commands`.

