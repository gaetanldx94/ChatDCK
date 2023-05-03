package View;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.PermissionCollection;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import Model.Client;

public class PanelClient extends JPanel implements ActionListener {

    private FrameClient frameClient;
    private JTextField txtMessage;
    private JTextArea txtAChat;
    private JScrollPane scrollBar;
	private Client client = new Client(this);

    public PanelClient(FrameClient frameClient) {
        this.frameClient=frameClient;
		this.client.start();
		this.setLayout(new BorderLayout());

        //Creation des composants
        this.txtMessage = new JTextField();
        this.txtAChat = new JTextArea();
        this.txtAChat.setEditable(false);
        this.txtAChat.setText("");

        this.scrollBar  = new JScrollPane(this.txtAChat, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
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
            this.client.setMessage(message);
			this.txtMessage.setText(null);
		}
	}

    //Methodes
    public void appendTxt(String msg) { this.txtAChat.append(msg + "\n");}
    public String getPseudo(){return this.client.getPseudo();}
    public String getTxt(){return this.txtMessage.getText();}
    public FrameClient getFrameClient(){return this.frameClient;}
    public void disconnect(){this.client.disconnect();}
}
