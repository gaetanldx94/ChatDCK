package src.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Logger;

public class ClientThread extends Thread
{
	// Initialisation d'un logger pour la classe
	private static final Logger LOGGER    = Logger.getLogger(ClientThread.class.getName());

	private static final String RANK_SAVE = "./../res/Save.rank";

	//Variables
	private boolean             isRunning = true;
	private Socket clientSocket;
	private String pseudo      ;
	private BufferedReader in  ;
	private PrintWriter out    ;
	private Server server      ;

	// Définition des commandes possibles pour le chat
	private static final String[] COMMANDS =
	{
		"/pseudo:",
		"/list",
		"/help",
		"/rank",
		"fsd6qkoz256f4s7dfjnq:"
	};

	// Constructeur de la classe
	public ClientThread(Socket socket, Server server, String pseudo)
	{
		clientSocket = socket;
		this.server = server;
		this.pseudo = pseudo;
	}

	// Redéfinition de la méthode run() de la classe Thread
	public void run()
	{
		try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));
			 PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"), true))
		{
			// Envoi d'un message de bienvenue au client
			out.println("Bienvenue sur le serveur de chat !");
			// Envoi d'un message de connexion au serveur
			server.sendMsg(pseudo + " s'est connecté!");

			String inputLine;
			// Boucle d'écoute des messages envoyés par le client
			while (isRunning && (inputLine = in.readLine()) != null)
			{
				LOGGER.info("Message recu de " + clientSocket.getInetAddress().getHostAddress() + " : " + inputLine);

				// Traitement des commandes
				if (inputLine.startsWith(COMMANDS[0]))
				{
					// Commande de changement de pseudo
					String newPseudo = inputLine.substring(COMMANDS[0].length());
					server.sendMsg(pseudo + " a changé de pseudo pour " + newPseudo);
					pseudo = newPseudo;
				}
				else if (inputLine.startsWith(COMMANDS[1]))
				{
					// Commande d'affichage de la liste des clients connectés
					StringBuilder builder = new StringBuilder("Liste des clients connectés : \n");
					for (ClientThread client : server.getClientThreads()) {
						builder.append("--> ").append(client.pseudo).append("\n");
					}
					out.println(builder);
				}
				else if (inputLine.startsWith(COMMANDS[2]))
				{
					// Commande d'affichage de la liste des commandes disponibles
					StringBuilder builder = new StringBuilder();
					for (String cmd : COMMANDS) {
						builder.append(cmd).append("\n");
					}
					out.println(builder);
				}
				else if(inputLine.startsWith(COMMANDS[3]))
				{
					out.println("\nRank : ");
					for(int i = 0; i < server.getTimeRank().size(); i++)
					{
						out.println( (int)(i+1) + " --> " + server.getTimeRank().get(i));
					}
				}
				else if(inputLine.startsWith(COMMANDS[4]))
				{
					ArrayList<String> tmpListRank = server.getTimeRank();

					for(int i = 0; i < tmpListRank.size(); i++)
					{
						if(tmpListRank.get(i).startsWith(this.pseudo))
						{
							tmpListRank.remove(i);
						}
					}
					tmpListRank.add(this.pseudo + " : " + inputLine.split(":")[1]);
					server.sendMsg(this.pseudo + " a fais " + inputLine.split(":")[1] + " bravo à lui !!");

					File rankSave         = new File(RANK_SAVE);
					PrintWriter print     = new PrintWriter(rankSave);
					StringBuilder builder = new StringBuilder();

					if(!rankSave.exists())
						rankSave.createNewFile();

					for(int i = 0; i < tmpListRank.size(); i++)
					{
						builder.append(tmpListRank.get(i) + "\n");
					}

					print.write(builder.toString());
					tmpListRank.sort(new RankComparator());
					server.setTimeRank(tmpListRank);

					print.close();
				}
				else
				{
					// Envoi du message à tous les clients connectés au serveur
					server.sendMsg(pseudo + ": " + inputLine);
				}
			}
			// Envoi d'un message de déconnexion au serveur
			LOGGER.info("Connexion fermee par " + clientSocket.getInetAddress().getHostAddress());
			server.sendMsg(pseudo + " s'est déconnecté!");
			clientSocket.close();
		}
		catch (IOException e)
		{
			LOGGER.warning(e.getMessage());
		}
	}

	// Méthode permettant d'obtenir le socket du client
	public Socket getSocket()
	{
		return clientSocket;
	}

	// Méthode permettant d'arrêter le thread
	public void stopRunning()
	{
		isRunning = false;
	}
}