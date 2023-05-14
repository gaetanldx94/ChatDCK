# ChatDCK

# ÉQUIPE 10

Pour ce projet, nosu avons choisi de réaliser une application de chat à distance à l'aide de sockets et de threads, le tout stocké sur un dockerfile.

COMPOSITION DE L'ÉQUIPE :

    LEDOUX GAÉTAN
    BOUDEELE THOMAS
    CHUZEVILLE JULES
    CHAMPVILLARD SÉBASTIEN
    
    
  Avantages :
  
    -Possibilité de changer l'IP et le port facilement à l'aide d'une fenêtre type formulaire.
    -Fenêtre graphique côté client et non un affichage console.

  Limites :
  
    -Les clients et le serveur doivent être sous le même réseau.
    
    Assurez-vous que Docker est installé sur votre machine Windows. Si vous ne l'avez pas déjà installé, vous pouvez suivre les instructions sur le site officiel de Docker: https://www.docker.com/get-started

# Guide d'utilisation

Récupérez le fichier Dockerfile dans la branche Docker du projet git.
Ouvrez votre terminal et naviguez vers le répertoire contenant le fichier Dockerfile.
Lancer le fichier dockerrun.bat pour créer le serveur.

Pour connaître l'adresse IP de votre machine Windows, ouvrez une nouvelle fenêtre de commande et tapez:

ipconfig

Cela affichera l'adresse IP de votre machine Windows.

Pour permettre la connexion au serveur Docker depuis l'extérieur, vous devez ouvrir le port 9000 de votre pare-feu Windows. Vous pouvez suivre les instructions fournies par Microsoft pour ouvrir des ports dans le pare-feu Windows : https://docs.microsoft.com/en-us/windows/security/threat-protection/windows-firewall/create-an-inbound-port-rule.

Pour le client, récuperer le code java dans la bracnhe client.

Double-cliquez sur le fichier compile.bat pour le lancer. Cela va compiler le client et ouvrir une invite de commande.

Dans l'invite de commande, entrez l'adresse IP de votre machine Windows avec le port du serveur (9000 par défaut).
Cela va connecter le client au serveur Docker.

Vous pouvez maintenant utiliser le client pour envoyer et recevoir des messages dans la messagerie instantanée.
Voilà, vous avez maintenant configuré et exécuté le projet de messagerie instantanée.
