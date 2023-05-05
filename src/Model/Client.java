package Model;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.io.PrintWriter;
import java.net.Socket;


import View.PanelClient;

public class Client extends Thread{

	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	private String message;
	private String pseudo;
	private PanelClient pnlClt;
	private boolean bPseudo = false;

	public Client(PanelClient pnlClt) {
		this.pnlClt = pnlClt;
	}

	public void run() {
		try {
			clientSocket = new Socket(this.pnlClt.getFrameClient().getIp(), this.pnlClt.getFrameClient().getPort());

			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in  = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

			out.println("5f4ky478l1qs35d178ksd5");

			this.pnlClt.appendTxt(in.readLine());

			while(!clientSocket.isClosed()) {

				// Demande un pseudo
				if(this.bPseudo == false) {
					if(this.message != null) {
						out.println(message);
						this.pseudo = message;
						this.message = null;
						this.bPseudo = true;
						
						
						this.pnlClt.getFrameClient().setTitle(this.pseudo);
					}
				}

				//Envoie le message au serveur
				if(this.bPseudo)
					if(this.message != null) {				
						out.println(this.message);
						this.message = null;
				}

				//Reçois un message du serveur
				if(in.ready()) {
					this.pnlClt.appendTxt(in.readLine());
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	//Methodes
	public void setMessage(String msg) { this.message = msg; }
	public String getPseudo(){return this.pseudo;}
	public void   setPseudo(String ch)
	{
		out.println("/pseudo:"+ch);
		this.pseudo = ch;
		this.pnlClt.getFrameClient().setTitle(this.pseudo);
	}

	public void disconnect()
	{
		try 
		{
			clientSocket.close();
		} catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		
	}
}