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

![AdresseIp](https://github.com/gaetanldx94/ChatDCK/assets/119732048/2a8fc461-e634-44f2-8222-9f0450958430)



Dans un 1er temps, le serveur vous demande de rentrer votre pseudo qui sera visible par tous les utilisateurs.

Vous pouvez maintenant utiliser le client pour envoyer et recevoir des messages dans la messagerie instantanée.
Voilà, vous avez maintenant configuré et exécuté le projet de messagerie instantanée.


# Commande disponible 

Il existe plusieurs commande pour le chat:
- /help qui référence toute les commandes existantes. 

![help](https://github.com/gaetanldx94/ChatDCK/assets/119732048/e343e31e-6603-4d16-b79c-3400f6a66973)

- /pseudo qui permet de changer de pseudo.

![pseudoCmd](https://github.com/gaetanldx94/ChatDCK/assets/119732048/6ecbd64a-e551-4f57-92d0-2a7a0bd9957e)


- /list qui liste tout les clients connectés.

![list](https://github.com/gaetanldx94/ChatDCK/assets/119732048/d14c1ed7-6921-4f80-ac37-f74379810465)


- /rank qui permet de connaitre le classement du jeu Typing Race.

# Changement de pseudo 

Si vous n'aimez plus le pseudo que vous avez rentré, vous avez la possibilité de le changer avec le bouton "Changement de pseudo". Cela ouvre alors une fenètre qui vous permet de rentré votre nouvelle identité.

![Changement de pseudo](https://github.com/gaetanldx94/ChatDCK/assets/119732048/6e1a5d10-a19b-48b7-8a22-2775600c3f1d)
![Pseudo](https://github.com/gaetanldx94/ChatDCK/assets/119732048/c20df7fe-623f-4075-920a-59a32d47bcce)



# Typing Race

Vous avez aussi la possiblité de défier les membres du chat en ligne grâce au jeu "Typing Race". C'est donc un jeu de clavier, ou vous devez recopier une phrase à l'identique le plus rapidement possible. Votre temps est stocké dans un classement qui est actualissé en temps réel.
![Typing Race](https://github.com/gaetanldx94/ChatDCK/assets/119732048/4c77c6a3-b76b-4767-9e63-bb372b8c036f)

![TypingRaceFenetre](https://github.com/gaetanldx94/ChatDCK/assets/119732048/950e6798-66c1-4ac4-a4e1-55a7c77fddd6)



