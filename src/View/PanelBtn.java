package View;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class PanelBtn extends JPanel implements ActionListener {

	/*
	 * VARIABLES
	 */
	private JButton   btnValider;
	private JButton   btnAnnuler;
	private FrameInit frame;

	/*
	 * Constructeur principal
	 */
	public PanelBtn(FrameInit frame) {
		this.frame = frame;
		this.setLayout(new GridLayout(0,2));

		this.btnAnnuler     = new JButton("Annuler");
		this.btnValider  = new JButton("Valider");

		this.add(this.btnAnnuler);
		this.add(this.btnValider);

		this.btnAnnuler.addActionListener(this);
		this.btnValider.addActionListener(this);
	}

	/*
	 * Action des bouttons
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btnAnnuler) {
			frame.dispose();
		}

		if(e.getSource() == this.btnValider) {
			if (frame.getTxtIP().getText().equals(null)
			&&  frame.getTxtPort().getText().equals(null) ) {
				JTextField Error = new JTextField("Veuillez remplir les deux champs d'abord !");
				frame.add(Error,BorderLayout.NORTH);
			} else {
				new FrameClient( frame.getTxtIP().getText(), Integer.parseInt(frame.getTxtPort().getText()) );
				frame.dispose();
			}
		}
	}
}