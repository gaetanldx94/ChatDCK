# Système de base du docker
FROM debian:11

# Mise à jour du système
RUN apt update && apt -y upgrade

# Configuration de l'utilisateur sudo
RUN apt-get install -y sudo
RUN sudo -i

# installation de java
RUN sudo apt-get install -y openjdk-11-jdk-headless

# installation de divers outils réseaux
RUN sudo apt-get install -y git
RUN sudo apt-get install -y net-tools

# nettoyage du noyau
RUN sudo apt-get clean

# installation et configuration de notre application
RUN git clone https://github.com/gaetanldx94/ChatDCK.git -b server

ADD . /chatDCK/
WORKDIR /chatDCK

VOLUME /chatDCK/logs
RUN chmod +x start.sh

# Lancement par défaut de notre application
CMD ./start.sh

# on ouvre le port 9000
EXPOSE 9000