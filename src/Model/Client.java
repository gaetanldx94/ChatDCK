package Model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import View.PanelClient;

public class Client extends Thread {

	/*
	 * VARIABLES
	 */
	private Socket         clientSocket;
	private PrintWriter    out;
	private BufferedReader in;
	private String         message;
	private String         pseudo;
	private PanelClient    pnlClt;

	private boolean        bPseudo = false;

	public Client(PanelClient pnlClt) {
		this.pnlClt = pnlClt;
	}

	/*
	 * Thread principale du client
	 */
	public void run() {
		try {
			clientSocket = new Socket(this.pnlClt.getFrameClient().getIp(), this.pnlClt.getFrameClient().getPort());

			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));
			out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"), true);

			out.println("5f4ky478l1qs35d178ksd5"); //Flag de sécuriter

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

	/*
	 * Change le pseudo utilisé
	 */
	public void setPseudo(String ch) {
		out.println("/pseudo:"+ch);
		this.pseudo = ch;
		this.pnlClt.getFrameClient().setTitle(this.pseudo);
	}

	/*
	 * Déconnection du client
	 * (Fermeture de l'IHM)
	 */
	public void disconnect() {
		try {
			clientSocket.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void setMessage(String msg) { this.message = msg; }
	public String getPseudo(){return this.pseudo;}
}