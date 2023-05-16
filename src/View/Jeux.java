package View;

import Model.Client;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Jeux extends JFrame implements ActionListener {

	/*
	 * VARIABLES
	 */
	private JTextField txtInput;
	private JLabel     phrase, erreur;
	private double     temps;
	private Client     clt;

	/*
	 * Constructeur principal
	 */
	public Jeux(Client clt) {
		this.setTitle("Typing Race");
		this.setSize(850, 150);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new FlowLayout());

		txtInput = new JTextField(35);
		txtInput.addActionListener(this);

		this.clt = clt;

		phrase = new JLabel(getPhrase());
		erreur = new JLabel("Vite !");

		this.add(phrase);
		this.add(txtInput);
		this.add(erreur);

		this.setVisible(true);

		temps = System.currentTimeMillis();
	}

	/*
	 * Récupère la saisi utilisateur
	 */
	public String getPhrase() {
		String phrase = "";

		try {
			int randomNumber = (int) (Math.random() * 400) + 1;
			Scanner sc = new Scanner(new File("./../res/phrases.list"));

			for(int i = 0; sc.hasNextLine(); i++) {
				if(i == randomNumber)
				{
					phrase = sc.nextLine();
				}
				else sc.nextLine();
			}
		} catch(IOException e) {
			e.printStackTrace();
		}

		return phrase;
	}

	/*
	 * Action des bouttons
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.txtInput) {
			if(this.txtInput.getText().equals(phrase.getText())) {
				temps = (System.currentTimeMillis() - temps)/1000;
				this.clt.setMessage("fsd6qkoz256f4s7dfjnq:"+temps);
				this.dispose();
			} else {
				erreur.setText("Phrase incorrect !");
			}
		}
	}
}