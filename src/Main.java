package src;

import java.io.IOException;

import src.app.Server;

public class Main
{
	//Unique endroit ou le port est définie pour plus de modulariter !
	private static final int PORT = 9000;

	public static void main(String[] args) throws IOException
	{
		//Initialisation du serveur
		new Server(PORT);
	}
}