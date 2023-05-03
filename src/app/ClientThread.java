package src.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

	private boolean bool  = true;

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
			out = new PrintWriter(clientSocket.getOutputStream(), true);

			out.println("Bienvenue sur le serveur de chat !");

			while (bool)
			{
				//On lis à chaque fois que le client envoie un message
				String inputLine = in.readLine();
				if (inputLine == null) break;

				//On print dans la console pour les logs et on envoie le message à tout les clients
				System.out.println("Message recu de " + clientSocket.getInetAddress().getHostAddress() + " : " + inputLine);
				server.sendMsg(this.pseudo + ": " + inputLine);
			}

			//Si l'on sort de la boucle c'est que le client est déconnecté
			System.out.println("Connexion fermee par " + clientSocket.getInetAddress().getHostAddress());
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