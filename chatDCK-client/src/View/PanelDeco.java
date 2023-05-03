package View;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class PanelDeco extends JPanel
{
    private JPanel pnlDeco;
    private JLabel lblImage;

    public PanelDeco()
    {
        this.setBackground(new Color(21, 31, 40));

        /*Creation des composants */
        
        this.pnlDeco = new JPanel();
        Icon logo = new ImageIcon(getClass().getResource("./images/logo.png"));  
        this.lblImage = new JLabel(logo);

        

        

        /*positionnement des composants */

        this.add(this.lblImage);
        this.add(this.pnlDeco);
        

    }
}
