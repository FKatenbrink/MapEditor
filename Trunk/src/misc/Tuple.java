package misc;

@SuppressWarnings("hiding")
public class Tuple<String, BufferedImage>
{
	public final String Path;
	public final BufferedImage Image;
	
	public Tuple(String path, BufferedImage img)
	{
		Path = path;
		Image = img;
	}
}
