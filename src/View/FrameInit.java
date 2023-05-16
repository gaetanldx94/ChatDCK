package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.FlowLayout;

public class FrameInit extends JFrame {

	/*
	 * VARIABLES
	 */
	private JPanel     paneltext;
	private PanelBtn   panelBtn;
	private JTextField txtIP;
	private JTextField txtPort;

	/*
	 * Constructeur principale
	 */
	public FrameInit() {
		this.setLayout(new BorderLayout());
		this.setTitle("Connection");
		this.setLocationRelativeTo(null);
		this.setSize(250,130);
		this.setResizable(false);
		this.setIconImage(new ImageIcon(getClass().getResource("./images/icon.png")).getImage());

		this.paneltext = new JPanel(new FlowLayout());
		this.panelBtn = new PanelBtn(this);

		this.txtIP      = new JTextField();
		this.txtPort    = new JTextField();

		this.txtIP  .setPreferredSize(new Dimension(125, 25));
		this.txtPort.setPreferredSize(new Dimension(125, 25));

		this.txtIP  .setEditable(true);
		this.txtPort.setEditable(true);

		this.paneltext.add(new JLabel("adresse IP : "));
		this.paneltext.add(this.txtIP);

		this.paneltext.add(new JLabel("port : "));
		this.paneltext.add(this.txtPort);

		this.add(this.paneltext,BorderLayout.CENTER);
		this.add(this.panelBtn,BorderLayout.SOUTH);

		this.setVisible(true);
	}

	public JTextField getTxtIP() {return this.txtIP;}
	public JTextField getTxtPort() {return this.txtPort;}

	public void setTxtPort(int p ) { this.txtPort.setText( Integer.toString(p) );}
	public void setTxtIP(String s ) { this.txtPort.setText(s);}
}
