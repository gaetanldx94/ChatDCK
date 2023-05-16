# ChatDCK

# Docker

Cette branche a pour fonction d'installer et configurer le docker et donc le serveur pour qu'il soit prêt à accueillier un client.

Dans un 1er temps, le docker installe son OS qui est donc debian 11 ainsi que les commandes sudo pour donner les droits d'administrateur.

// Système de base du docker
FROM debian:11

// Mise à jour du système
RUN apt update && apt -y upgrade

// Configuration de l'utilisateur sudo
RUN apt-get install -y sudo
RUN sudo -i

Ensuite, on installe java et git pour pouvoir récuperer, compiler et lancer le programme du serveur. 

// installation de java
RUN sudo apt-get install -y openjdk-11-jdk-headless

// installation de divers outils réseaux
RUN sudo apt-get install -y git
RUN sudo apt-get install -y net-tools

Pour finir, le docker donne les droit d'execution à notre application et la lance avant d'ouvrir le port 9000 pour accueillier de nouveau client.

ADD . /ChatDCK/
WORKDIR /ChatDCK

# Ajout des droits pour démarrer le serveur
RUN chmod +x start.sh

# Lancement par défaut de notre application
CMD ./start.sh

# on ouvre le port 9000
EXPOSE 9000

