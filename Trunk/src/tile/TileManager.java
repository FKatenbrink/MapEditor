package tile;

import java.awt.Color;
import java.awt.Graphics;
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
	public static int W = 32, H = 32;
	
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
				
				BufferedImage img = new BufferedImage(TerrainTile.Width, TerrainTile.Height, BufferedImage.TYPE_INT_RGB);
				Graphics g = img.getGraphics();
				g.drawImage(bi, 0, 0, TerrainTile.Width, TerrainTile.Height, null);

				Tiles.addItem(split[0], new Tuple<String, BufferedImage>(split[1], img));
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
