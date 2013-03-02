package surface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import tile.Terrain;
import tile.TerrainTile;

public class CanvasMap extends JPanel
{
	private static final long serialVersionUID = 2467678636120806697L;
	private Terrain terrain;
	private BufferedImage bufImg, curImg;
	private CanvasMiniMap miniMap;
	private PictureList pl;
	
	private MouseListener mouseListener = new MouseListener()
	{

		@Override
		public void mouseClicked(MouseEvent me)
		{
			BufferedImage bi = pl.getFocusedImg();
			if(bi != null)
				curImg = bi;
			repaint();
		}

		@Override
		public void mousePressed(MouseEvent me)
		{
		}

		@Override
		public void mouseReleased(MouseEvent me)
		{
		}

		@Override
		public void mouseEntered(MouseEvent me)
		{
		}

		@Override
		public void mouseExited(MouseEvent me)
		{
		}
		
	};
	
	public CanvasMap(PictureList pl)
	{
		this(16, 16, pl);
	}
	
	public CanvasMap(int x, int y, PictureList pl)
	{
		super();
		terrain = new Terrain(x, y);
		init();
		this.pl = pl;
		this.addMouseListener(mouseListener);
	}
	
	public CanvasMap(TerrainTile[][] tt, PictureList pl)
	{
		super();
		terrain = new Terrain(tt);
		init();
		this.pl = pl;
		this.addMouseListener(mouseListener);
	}
	
	private void init()
	{
		//stdImg
		curImg = new BufferedImage(terrain.getTileW(), terrain.getTileH(), BufferedImage.TYPE_INT_RGB);
		Graphics g = curImg.getGraphics();
		g.setColor(Color.white);
		g.fillRect(1, 1, terrain.getTileW() - 2, terrain.getTileH() - 2);
		g.setColor(Color.gray);
		g.drawRect(0, 0, terrain.getTileW(), terrain.getTileH());
		
		//bufImg
		bufImg = new BufferedImage(terrain.getTileW()*terrain.getW(), terrain.getTileH()*terrain.getH(), BufferedImage.TYPE_INT_RGB);
		
		this.setPreferredSize(new Dimension(bufImg.getWidth(), bufImg.getHeight()));
		miniMap = new CanvasMiniMap(bufImg);
	}
	
	public CanvasMiniMap getMiniMap()
	{
		return miniMap;
	}
	
	@Override
	public void paint(Graphics gra)
	{
		Graphics g = bufImg.getGraphics();
		super.paint(g);
		int w = terrain.getW();
		int h = terrain.getH();
		int tileW = terrain.getTileW();
		int tileH = terrain.getTileH();
		
		for(int i = 0; i < w; i++)
		{
			for(int j = 0; j < h; j++)
			{
				if(terrain.getTile(i, j) == null)
					g.drawImage(curImg, i*tileW, j*tileH, null);
			}
		}
		
		gra.drawImage(bufImg, 0, 0, null);
		
		miniMap.setImg(bufImg);
	}
}
