package src.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;


public class Server
{
	private LinkedList<ClientThread> threads; //List des clients connectés sur le serveur

	public Server(int port) throws IOException
	{
		try (ServerSocket serverSocket = new ServerSocket(port)) // Initialisation du serveur
		{
			threads = new LinkedList<ClientThread>();

			System.out.println("Le serveur de chat est en ligne sur le port 9000...");

			while (true) //Boucle infini qui gère les clients se connectant en initialisant un nouveau thread pour chacun d'entre eux
			{
				Socket         clientSocket = serverSocket.accept();
				System.out.println("Nouvelle connexion de " + clientSocket.getInetAddress().getHostAddress());

				String         pseudo       = "";
				BufferedReader in           = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				PrintWriter    out          = new PrintWriter(clientSocket.getOutputStream(), true);

				//clé : 5f4ky478l1qs35d178ksd5
				/*
				 * Le serveur n'accepte aucun client en dehors de ceux utilisant notre application
				 * dédiée grâce à une clé d'authentification.
				*/
				if (!in.readLine().equals("5f4ky478l1qs35d178ksd5"))
				{
					out.println("Clé incorrect !");
					System.out.println("Connexion fermee par " + clientSocket.getInetAddress().getHostAddress());
					clientSocket.close();
				}
				else
				{
					/*
					 * si la clé est correcte on initialise le pseudo de la personne et on lance un nouveau thread
					 * indépendant
					*/
					out.println("Rentrez votre pseudo : ");
					pseudo = in.readLine();

					threads.add( new ClientThread(clientSocket, this, pseudo));
					threads.getLast().start();
				}

				/*
				 * Si un client est fermé mais encore enregistré
				 * on le retire de la liste pour éviter toute surcharge
				 */
				for(int i = 0; i < threads.size(); i++)
					if(threads.get(i).getSocket().isClosed())
						threads.remove(i);
			}
		}
	}

	/*
	 * Quand un message est reçue le serveur le renvoie à tous les clients connectés
	 * tout en vérifiant si la connection n'est pas fermée
	*/
	public void sendMsg(String msg) throws IOException
	{
		for(ClientThread client : threads)
		{
			if(!client.getSocket().isClosed())
			{
				PrintWriter out = new PrintWriter(client.getSocket().getOutputStream(), true);
				out.println(msg);
			}
		}
	}
}
