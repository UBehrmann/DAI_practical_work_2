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