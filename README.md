# ChatDCK

Dans cette branche se trouvent tout ce qui se rapportent au serveur.

Dans le dossier "src" se trouvent les ".java". 
Dans le dossier "out" se trouvent les ".class".

On lance le serveur avec le fichier : "start.sh"

Le serveur permet la connexion de plusieurs utilisateurs et reçoit les messages de ses derniers pour les retransmettre à tous les 
utilisateurs présent, les utilisateurs sont stockés dans une LinkedList de ClientThread.
Le serveur n'a pas d'interface graphique et utilise donc la console comme moyen d'affichage.
Il nous indique lorsqu'un utilisateur se connecte en nous donnant son adresse IP mais également 
lorsqu'un utilisateur se déconnecte en l'enlevant de la liste des utilisateurs connectés.
Il affiche également tous les messages qu'il reçoit et de qui ils proviennent avant de les retransmettre aux utilisateurs.
A chaque message transmis, la connexion avec les utilisateurs est vérifiée pour ne pas envoyer un message vers une connexion qui est fermée


ClientThread est une clase que nous avons créée :
Elle prend en paramètre un socket, un serveur et un pseudo.
Le ClientThread lit chaque message que le client envoie et l'affiche dans la console pour les logs.
Si le client associé se déconnecte, il l'affiche dans la console.
Les erreurs sont récupérées et ignorées pour le bon fonctionnement du code grâce à un try catch.
