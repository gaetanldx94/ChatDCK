package src.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server
{
	// Liste des threads clients
	private final LinkedList<ClientThread> clientThreads = new LinkedList<>();

	// Constantes
	private static final int               PORT          = 9000;
	private static final String            AUTH_KEY      = "5f4ky478l1qs35d178ksd5";
	private static final String            CONNECTED_MSG = "Le serveur de chat est en ligne sur le port " + PORT + "...";
	private static final String            PROMPT_PSEUDO = "Rentrez votre pseudo : ";
	private static final String            KEY_ERROR_MSG = "Clé incorrect !";

	// Constructeur
 	public Server() throws IOException
	{
		try (ServerSocket serverSocket = new ServerSocket(PORT))
		{
			System.out.println(CONNECTED_MSG);

			while (true)
			{
				// Attente de connexion d'un client
				Socket clientSocket = serverSocket.accept();
				System.out.println("Nouvelle connexion de " + clientSocket.getInetAddress().getHostAddress());

				/*
				* Le serveur n'accepte aucun client en dehors de ceux utilisant notre application
				* dédiée grâce à une clé d'authentification.
				*/

				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

				if (!in.readLine().equals(AUTH_KEY))
				{
					out.println(KEY_ERROR_MSG);
					System.out.println("Connexion fermée par " + clientSocket.getInetAddress().getHostAddress());
					clientSocket.close();
				}
				else
				{
					/*
					* Si la clé est correcte, on initialise le pseudo de la personne et on lance un nouveau thread
					* indépendant
					*/

					out.println(PROMPT_PSEUDO);
					String pseudo = in.readLine();

					ClientThread clientThread = new ClientThread(clientSocket, this, pseudo);
					clientThreads.add(clientThread);
					clientThread.start();
				}

				/*
				* On retire les threads clients qui se sont déconnectés pour éviter toute surcharge
				*/
				clientThreads.removeIf(clientThread -> clientThread.getSocket().isClosed());
			}
		}
	}

	/*
	* Quand un message est reçu, le serveur le renvoie à tous les clients connectés
	* tout en vérifiant si la connexion n'est pas fermée
	*/

	public void sendMsg(String msg) throws IOException
	{
		for (ClientThread client : clientThreads)
		{
			if (!client.getSocket().isClosed())
			{
				PrintWriter out = new PrintWriter(new OutputStreamWriter(client.getSocket().getOutputStream(), "UTF-8"), true);
				out.println(msg);
			}
		}
	}

	public LinkedList<ClientThread> getClientThreads()
	{
		return clientThreads;
	}
}