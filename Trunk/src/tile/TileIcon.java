package tile;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import javax.swing.Icon;

public class TileIcon implements Icon, Serializable
{
	private static final long serialVersionUID = -5270715948783480354L;
	private static int w, h;
	private BufferedImage refImg;

	public TileIcon(BufferedImage img)
	{
		refImg = img;
	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y)
	{
		g.drawImage(refImg, 0, 0, TerrainTile.width, TerrainTile.height, c);
	}
	
	public BufferedImage getImage()
	{
		return refImg;
	}

	@Override
	public int getIconWidth()
	{
		return w;
	}

	@Override
	public int getIconHeight()
	{
		return h;
	}
}
