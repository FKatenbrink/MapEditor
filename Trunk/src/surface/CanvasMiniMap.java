package surface;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class CanvasMiniMap extends JPanel
{
	private static final long serialVersionUID = 6112531659143177585L;
	private BufferedImage bufImg;
	
	public CanvasMiniMap(BufferedImage bufImg)
	{
		initImg(bufImg);
		this.setPreferredSize(new Dimension(this.bufImg.getWidth(), this.bufImg.getHeight()));
	}
	
	public void setImg(BufferedImage img)
	{
		initImg(img);
	}
	
	private void initImg(BufferedImage img)
	{
		int w = img.getWidth()/4, h = img.getHeight()/4;
		Image scaledImg = img.getScaledInstance(w, h, BufferedImage.SCALE_DEFAULT);
        bufImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics g = bufImg.getGraphics();
        g.drawImage(scaledImg, 0, 0, null);
		g.dispose();
		
		this.repaint();
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		g.drawImage(bufImg, 0, 0, null);
	}
}
