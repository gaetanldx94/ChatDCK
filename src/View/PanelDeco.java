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

public class PanelDeco extends JPanel implements ActionListener
{
	private JPanel pnlDeco;

	private JLabel lblImage;

	private JButton btnPseudo;
	private JButton btnJeux;
	private double temps;

	private FrameClient frameClient;

	private Client clt;

	public PanelDeco(FrameClient frameClient, Client clt)
	{
		this.setBackground(new Color(21, 31, 40));

		this.frameClient 	= frameClient;
		this.clt 			= clt;

		/*Creation des composants */

		this.pnlDeco = new JPanel();

		//affichage du logo
		Icon logo = new ImageIcon(getClass().getResource("./images/logo.png"));
		this.lblImage = new JLabel(logo);

		//génération des différents boutons
		this.btnPseudo 	= new JButton(String.format("%-20s","Changement de pseudo"));
		this.btnJeux  	= new JButton(String.format("%-32s","Typing Race"));






		/*positionnement des composants */

		this.add(this.lblImage );
		this.add(this.pnlDeco  );
		this.add(this.btnPseudo);
		this.add(this.btnJeux  );


		/*activation des composants */

		this.btnPseudo.addActionListener(this);
		this.btnJeux  .addActionListener(this);



	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		//determine quel bouton a été activé
		if(e.getSource() == this.btnPseudo)
			new FramePseudo(this.frameClient); //lancement de la frame de selection de pseudo

		if(e.getSource() == this.btnJeux)
		{
			Jeux jeux = new Jeux(this.clt);

		}
	}
}
