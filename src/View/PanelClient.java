package View;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Model.Client;

public class PanelClient extends JPanel implements ActionListener {

	/*
	 * CONSTANTES
	 */
	private final String COMMANDE_PSD = "/pseudo:";

	/*
	 * VARIABLES
	 */
	private FrameClient frameClient;
	private JTextField  txtMessage;
	private JTextArea   txtAChat;
	private JScrollPane scrollBar;
	private JScrollBar  scrollbar;
	private Client      client = new Client(this);

	/*
	 * Constructeur principale
	 */
	public PanelClient(FrameClient frameClient) {
		this.setLayout(new BorderLayout());
		this.client.start();

		this.frameClient=frameClient;

		this.txtMessage = new JTextField();
		this.txtAChat 	= new JTextArea();

		this.txtAChat.setEditable(false);
		this.txtAChat.setText("");

		this.scrollBar  = new JScrollPane(this.txtAChat, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.scrollbar = this.scrollBar.getVerticalScrollBar();
		this.txtMessage.setEditable(true);

		this.add(this.scrollBar,   BorderLayout.CENTER);
		this.add(this.txtMessage, BorderLayout.SOUTH);

		this.txtMessage.addActionListener(this);
	}

	/*
	 * Action des bouttons
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == txtMessage) {
			String message = this.txtMessage.getText();

			if(message.startsWith(this.COMMANDE_PSD)) {
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
	public void changementPseudo(String ch) { this.client.setPseudo(ch); }
}