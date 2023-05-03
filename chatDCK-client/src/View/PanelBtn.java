package View;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class PanelBtn extends JPanel implements ActionListener
{	
    private JButton btnValider;
    private JButton btnAnnuler;
    private FrameInit frame;

    public PanelBtn(FrameInit frame)
    {
        this.frame = frame;
        this.setLayout(new GridLayout(0,2));

        /*Création des composants*/
        this.btnAnnuler     = new JButton("Annuler"); 
	    this.btnValider  = new JButton("Valider"); 
       
        /* Positionnement des composants */
        this.add(this.btnAnnuler);
		this.add(this.btnValider);

		/* Activation des composants */
		this.btnAnnuler.addActionListener(this);
		this.btnValider.addActionListener(this);
    }
   
    @Override
	public void actionPerformed(ActionEvent e) {
        //Si on appuie sur le bouton "annuler", on ferme la fenêtre sans rien faire
		if(e.getSource() == this.btnAnnuler) {
			frame.dispose();
		}
		
        //Si on appuie sur Valider, on vérifie les valeurs et on lance une nouvelle fenêtre avec le client
		if(e.getSource() == this.btnValider)
		{
            //Si les valeurs sont vides, on émet un avertissement sur la fenêtre
			if (frame.getTxtIP().getText().equals(null) && frame.getTxtPort().getText().equals(null) ) 
			{
				JTextField Error = new JTextField("Veuillez remplir les deux champs d'abord !");
				frame.add(Error,BorderLayout.NORTH);
			}
			else //Si les valeurs sont bonnes, on lance une nouvelle fenêtre avec le client et on ferme l'ancienne fenêtre
			{
				new FrameClient( frame.getTxtIP().getText(), Integer.parseInt(frame.getTxtPort().getText()) );
                frame.dispose();
			}
		}
	}

   
}