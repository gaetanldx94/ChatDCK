package View;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Normalizer;
import java.util.regex.Pattern;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


import Model.Client;

public class PanelClient extends JPanel implements ActionListener {

	private final String COMMANDE_PSD = "/pseudo:";

	private FrameClient frameClient;
	private JTextField txtMessage;
	private JTextArea txtAChat;
	private JScrollPane scrollBar;
	private JScrollBar scrollbar;
	private Client client = new Client(this);

	public PanelClient(FrameClient frameClient) {

		this.setLayout(new BorderLayout());
		this.client.start();


		this.frameClient=frameClient;

		//Creation des composants
		this.txtMessage = new JTextField();
		this.txtAChat 	= new JTextArea();

		this.txtAChat.setEditable(false);
		this.txtAChat.setText("");

		this.scrollBar  = new JScrollPane(this.txtAChat, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.scrollbar = this.scrollBar.getVerticalScrollBar();
		//this.scrollBar.add
		this.txtMessage.setEditable(true);


		//Positionnement des composants

		this.add(this.scrollBar,   BorderLayout.CENTER);
		this.add(this.txtMessage, BorderLayout.SOUTH);

		//Activation des composants


		this.txtMessage.addActionListener(this);
	}

	//Enregistre le message de l'utilisateur et vide la barre de texte
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == txtMessage) {
			String message = this.txtMessage.getText();



			if(message.startsWith(this.COMMANDE_PSD))
			{
				this.getFrameClient().setTitle(message.substring(this.COMMANDE_PSD.length()));
			}

			this.client.setMessage(message);

			this.scrollbar.setValue(this.scrollbar.getMaximum());

			this.txtMessage.setText(null);

		}
	}




	//Methodes
	public String getPseudo(){ return this.client.getPseudo(); }
	public FrameClient getFrameClient(){ return this.frameClient; }

	public void appendTxt(String msg){ this.txtAChat.append(msg + "\n"); }

	public void disconnect(){ this.client.disconnect(); }

	public Client getClient() {return this.client;}

	//Méthode pour changer le pseudo du client
	public void changementPseudo(String ch)
	{
		this.client.setPseudo(ch);
	}
}
