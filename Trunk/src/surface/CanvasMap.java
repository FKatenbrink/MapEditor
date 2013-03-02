package surface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Arrays;

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
	private BufferedImage[][] tileMap;
	
	private MouseListener mouseListener = new MouseListener()
	{

		@Override
		public void mouseClicked(MouseEvent me)
		{
			BufferedImage bi = pl.getFocusedImg();
			if(bi != null)
			{
				int xI = me.getX()/TerrainTile.Width;
				int yI = me.getY()/TerrainTile.Height;
				int xPos = xI*TerrainTile.Width;
				int yPos = yI*TerrainTile.Height;;
				tileMap[xI][yI] = bi;
				Rectangle rect = new Rectangle(xPos, yPos, xPos + TerrainTile.Width, yPos + TerrainTile.Height);
				repaint(rect);
				miniMap.repaint(bi, rect);
			}
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
		init(x, y, null, pl);
	}
	
	public CanvasMap(TerrainTile[][] tt, PictureList pl)
	{
		super();
		init(-1, -1, tt, pl);
	}
	
	private void init(int x, int y, TerrainTile[][] tt, PictureList pl)
	{
		if(tt != null)
		{
			terrain = new Terrain(tt);
			this.tileMap = new BufferedImage[tt.length][tt[0].length];
		}
		else
		{
			terrain = new Terrain(x, y);
			this.tileMap = new BufferedImage[x][y];
		}
		
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
		
		this.pl = pl;
		this.addMouseListener(mouseListener);
		for(BufferedImage[] bi : tileMap)
		Arrays.fill(bi, curImg);
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
		
		for(int i = 0; i < w; i++)
		{
			for(int j = 0; j < h; j++)
			{
				if(terrain.getTile(i, j) == null)
					g.drawImage(tileMap[i][j], i*TerrainTile.Width, j*TerrainTile.Height, null);
			}
		}
		
		gra.drawImage(bufImg, 0, 0, null);
		
		miniMap.repaint(bufImg);
	}
}
