package viewSwing;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class testets extends JPanel{
	String fname;
	Image img = null;
	
	public testets(String aName) {
		fname = "image/"+aName+".jpg";
		Dimension d = new Dimension(300, 300);
		this.setPreferredSize(d);
		this.setVisible(true);
		img = new ImageIcon(fname).getImage();
	}
	public void paint(Graphics g) {
		g.drawImage(img,0,0,this.getWidth(),this.getHeight(),this);	
	}
}
