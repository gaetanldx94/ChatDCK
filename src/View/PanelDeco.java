package View;

import java.awt.Color;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Model.Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelDeco extends JPanel implements ActionListener {

	/*
	 * VARIABLES
	 */
	private JPanel      pnlDeco;
	private JLabel      lblImage;
	private JButton     btnPseudo;
	private JButton     btnJeux;
	private FrameClient frameClient;
	private Client      clt;

	/*
	 * Constructeur principal
	 */
	public PanelDeco(FrameClient frameClient, Client clt) {
		this.setBackground(new Color(21, 31, 40));

		this.frameClient 	= frameClient;
		this.clt 			= clt;

		this.pnlDeco = new JPanel();

		Icon logo = new ImageIcon(getClass().getResource("./images/logo.png"));
		this.lblImage = new JLabel(logo);

		this.btnPseudo 	= new JButton(String.format("%-20s","Changement de pseudo"));
		this.btnJeux  	= new JButton(String.format("%-32s","Typing Race"));

		this.add(this.lblImage );
		this.add(this.pnlDeco  );
		this.add(this.btnPseudo);
		this.add(this.btnJeux  );

		this.btnPseudo.addActionListener(this);
		this.btnJeux  .addActionListener(this);
	}

	/*
	 * Action des bouttons
	 */
	@SuppressWarnings("unused")
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btnPseudo)
			new FramePseudo(this.frameClient);

		if(e.getSource() == this.btnJeux) {
			Jeux jeux = new Jeux(this.clt);
		}
	}
}
