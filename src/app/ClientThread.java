package src.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread
{
	/*
	 * Variable de l'objet
	 */
	private Socket clientSocket;
	private String pseudo;
	private BufferedReader in;
	private PrintWriter out;
	private Server server;
	private StringBuilder strBuilder;
	private boolean bool  = true;

	private String[] commandes = {
		"/pseudo:",
		"/list",
		"/help",
	};

	//Initialisation des différentes donné necessaire au client
	public ClientThread(Socket socket, Server server,String pseudo)
	{
		this.clientSocket = socket;
		this.server = server;
		this.pseudo = pseudo;
	}

	//Méthode d'initialisation du thread (Client connecté)
	public void run()
	{
		try
		{
			//Initialisation des différentes variables
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"), true);

			out.println("Bienvenue sur le serveur de chat !");
			server.sendMsg(this.pseudo + " s'est connecté!");

			while (bool)
			{
				strBuilder = new StringBuilder();
				//On lis à chaque fois que le client envoie un message
				String inputLine = in.readLine();
				if (inputLine == null) break;

				//On print dans la console pour les logs et on envoie le message à tout les clients
				System.out.println("Message recu de " + clientSocket.getInetAddress().getHostAddress() + " : " + inputLine);

				try {
					//On passe ici quand une commande spécifique est saisie
					if(inputLine.startsWith(commandes[0]))
					{
						server.sendMsg(this.pseudo + " a changé de pseudo pour " + inputLine.split(":")[1]);
						this.pseudo = inputLine.split(":")[1];
					}
					else if(inputLine.startsWith(commandes[1]))
					{
						strBuilder.append("Liste des clients connectés : \n");
						for(int i = 0; i < server.getListeClient().size(); i++)
						{
							strBuilder.append("--> " + server.getListeClient().get(i).pseudo + "\n");
						}
						out.println(strBuilder.toString());
					}
					else if(inputLine.startsWith(commandes[2]))
					{
						strBuilder.append(commandes[0] + "+pseudo\n");
						for(int i = 1; i < commandes.length; i++)
						{
							strBuilder.append(commandes[i] + "\n");
						}
						out.println(strBuilder.toString());
					}
					else server.sendMsg(this.pseudo + ": " + inputLine);
				}
				catch (StringIndexOutOfBoundsException e) { server.sendMsg(this.pseudo + ": " + inputLine); }
				catch (ArrayIndexOutOfBoundsException ex) { server.sendMsg(this.pseudo + ": " + inputLine); }
			}

			//Si l'on sort de la boucle c'est que le client est déconnecté
			System.out.println("Connexion fermee par " + clientSocket.getInetAddress().getHostAddress());
			server.sendMsg(this.pseudo + " s'est déconnecté!");
			clientSocket.close();
		}
		/*
		 * On récupère toute erreur de connexion et tout autre exception
		 * qui empecherait le bon fonctionnement du thread client
		*/
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	//Accesseur
	public Socket getSocket() { return this.clientSocket; }
}