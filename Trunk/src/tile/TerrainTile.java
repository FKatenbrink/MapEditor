package tile;

public class TerrainTile
{
	private String img;
	private Type type;
	public static int Width = 64, Height = 64;
	
	public static enum Type
	{
		Walkable, Blocking
	}
	
	public TerrainTile(String img, Type type)
	{
		this.img = img;
		this.type = type;
	}
	
	public void setTile(String img)
	{
		this.img = img;
	}
	
	public void setTile(Type type)
	{
		this.type = type;
	}
	
	public void setTile(String img, Type type)
	{
		this.img = img;
		this.type = type;
	}
	
	public String getImage()
	{
		return img;
	}
	
	public Type getType()
	{
		return type;
	}
}
