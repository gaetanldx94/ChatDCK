# Chat DCK

# Code du client

Dans cette branche se trouvent tout ce qui se rapportent au client.

Dans le dossier "src" se trouvent les ".java" 
Dans le dossier "out" se trouvent les ".class"

il y a 3 façons de compiler avec le compile.sh / .bat / .list

      -"javac @compile compile.list"
      -"./compile.sh"
      -"./compile.bat"

Ici on définit le client ainsi que son interface graphique.
Dans un premier temps, le serveur demande au client,à travers une IHM,de renseigner l'adresse IP et le port. Si ces derniers sont valides alors le serveur se connecte.
Ensuite,le serveur demande au client de renseigner son pseudo. Ce dernier sera alors stocké et affiché dans le titre de la fenêtre graphique.

Une fois cette étape passée, le client peut ainsi commencer à discuter. Le serveur étant de type écho, lorsqu'il reçoit un message venant d'un client, il renvoie ce message précédé du pseudo de l'expéditeur à tous les autres clients connecté. C'est grâce à cette propriété que l'interface graphique du client fonctionne, en effet, l'IHM n'affiche que les messages envoyés par le serveur. 
 
Afin de rajouter un peu de sécurité au serveur. Le client envoie un code de contrôle au serveur.
Si le code correspond à ce que le serveur attend alors la connexion peut continuer sinon le serveur envoie "Clé incorrecte" et ferme la connexion.

Le client possède plusieurs méthodes personnelles:
- La méthode publique setMessage() qui permet de modifier l'attribut message. C'est le contenu de cet attribut qui est envoyé au serveur.
- La méthode publique disconnect() qui ferme la connexion avec le serveur. Cette méthode est activée lorsque l'on ferme la fenêtre. Cela permet d'éviter que le client reste connecté indéfiniment.
- La méthode publique getPseudo() qui retourne simplement le pseudo du client.
