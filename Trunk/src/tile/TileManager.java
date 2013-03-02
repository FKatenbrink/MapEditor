package tile;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;

import misc.Tuple;

public class TileManager
{
	public static ImagePool Tiles = new ImagePool();
	
	public static DefaultListModel getModel()
	{
		return Tiles.getModel();
	}
	
	public static void initialize()
	{
		BufferedReader in;
		
		try
		{
			in = new BufferedReader(new FileReader("./tiles.txt"));
		}
		catch (FileNotFoundException ex)
		{
			return;
		}
		
		String temp;
		String[] split;
		try
		{
			while((temp = in.readLine()) != null)
			{
				split = temp.split(";");
				BufferedImage bi = ImageIO.read(new File(split[1]));
				Tiles.addItem(split[0], new Tuple<String, BufferedImage>(split[1], bi));
			}
		}
		catch (IOException ex)
		{
		}
		catch(ArrayIndexOutOfBoundsException ex)
		{
		}
	}
}
