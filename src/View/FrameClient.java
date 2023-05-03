package View;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class FrameClient extends JFrame
{
	private String IP;
	private int port;

	private PanelClient panelClient;
	private PanelDeco  panelJolie;

	public FrameClient(String IP,int port) 
	{
		this.IP=IP;
		this.port=port;

		//Ajoute le panel à la frame
		this.setSize(new Dimension(600, 500));
		this.setLayout(new GridLayout(1, 2));
		this.setLocationRelativeTo(null);
		Image icon = new ImageIcon(getClass().getResource("./images/icon.png")).getImage();
		this.setIconImage(icon);

		this.panelJolie  = new PanelDeco();
		this.panelClient = new PanelClient(this);
			
		
		this.add(this.panelJolie);
		this.add(this.panelClient);

		this.setVisible(true);


		//Activation de la fenêtre
		
		this.addWindowListener(new GereFenetre());
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//Methodes
	public PanelClient getPanel(){return this.panelClient;}
	public String getIp(){return this.IP;}
	public int getPort(){return this.port;}

	//Creation de la classe privé GereFenetre.
	private class GereFenetre extends WindowAdapter
	{
		public void windowClosing(WindowEvent e)
		{
			FrameClient.this.panelClient.disconnect();
		}
	}
	
	
    
}

