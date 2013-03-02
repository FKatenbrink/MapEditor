package tile;

public class Terrain
{
	private TerrainTile[][] map;
	private int w, h;
	private int tileW, tileH;
	
	public Terrain(int w, int h)
	{
		this(w, h, TerrainTile.width, TerrainTile.height);
	}
	
	public Terrain(int w, int h, int tileW, int tileH)
	{
		map = new TerrainTile[w][h];
		
		for(int i = 0; i < w; i++)
			for(int j = 0; j < h; j++)
				map[i][j] = null;
		
		this.w = w;
		this.h = h;
		this.tileW = tileW;
		this.tileH = tileH;
	}
	
	public int getTileW()
	{
		return tileW;
	}
	
	public int getTileH()
	{
		return tileH;
	}
	
	public int getW()
	{
		return w;
	}
	
	public int getH()
	{
		return h;
	}
	
	//IMGs in der CanvasMap verändern
	public void setTileW(int tileW)
	{
		this.tileW = tileW;
	}
	
	public Terrain(TerrainTile[][] tt)
	{
		this.map = tt;
	}
	
	public void setTile(int x, int y, TerrainTile tt)
	{
		map[x][y] = tt;
	}
	
	public TerrainTile getTile(int x, int y)
	{
		return map[x][y];
	}
}
