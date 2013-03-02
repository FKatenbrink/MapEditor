package tile;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.swing.DefaultListModel;

import misc.Tuple;

public class ImagePool
{
	private HashMap<String, Tuple<String, BufferedImage>> pool;
	
	public ImagePool()
	{
		this.pool = new HashMap<String, Tuple<String, BufferedImage>>();
	}
	
	public ImagePool(HashMap<String, Tuple<String, BufferedImage>> pool)
	{
		this.pool = pool;
	}
	
	public DefaultListModel getModel()
	{
		DefaultListModel dlm = new DefaultListModel();
		
		Iterator<Entry<String, Tuple<String, BufferedImage>>> it = pool.entrySet().iterator();
		while(it.hasNext())
		{
			Entry<String, Tuple<String, BufferedImage>> e = it.next();
			dlm.addElement(new TileIcon(e.getValue().Image));
		}
		
		return dlm;
	}
	
	/*
	 * Entfernt falsche Einträge
	 */
	public void deleteCorruptEntries()
	{
		File f;
		Iterator<Entry<String, Tuple<String, BufferedImage>>> it = pool.entrySet().iterator();
		while(it.hasNext())
		{
			Entry<String, Tuple<String, BufferedImage>> e = it.next();
			f = new File(e.getValue().Path);
			if(!f.exists())
				pool.remove(e.getKey());
		}
	}
	
	/*
	 * @return -1 = Key schon vorhanden, 1 = Pfad schon vorhanden, 0 = Key/Pfad wurde hinzugefügt.
	 */
	public int addItem(String key, Tuple<String, BufferedImage> tuple)
	{
		Iterator<Entry<String, Tuple<String, BufferedImage>>> it = pool.entrySet().iterator();
		while(it.hasNext())
		{
			Entry<String, Tuple<String, BufferedImage>> e = it.next();
			if(e.getKey().equals(key))
				return -1;
			if(e.getValue().Path.equals(tuple.Path))
				return 1;
		}
		
		pool.put(key, tuple);
		return 0;
	}
	
	public String getPath(String key)
	{
		return pool.get(key).Path;
	}
	
	public BufferedImage getImage(String key)
	{
		return pool.get(key).Image;
	}
}
