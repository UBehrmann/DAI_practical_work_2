# Étape 1 : Utiliser une image de base contenant Java
FROM eclipse-temurin:22-jdk

# Étape 2 : Définir le répertoire de travail dans le conteneur
WORKDIR /app

# Étape 3 : Copier le fichier JAR généré dans le conteneur
COPY target/chat_server-1.0-SNAPSHOT.jar app.jar

# Étape 4 : Exposer un port (5000 par défaut)
EXPOSE 1234

# Étape 5 : Configurer la commande principale (ENTRYPOINT)
ENTRYPOINT ["java", "-jar", "app.jar"]

# Étape 6 : Fournir des arguments par défaut via CMD
CMD ["--help"]
