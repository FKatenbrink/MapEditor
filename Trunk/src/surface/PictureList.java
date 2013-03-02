package surface;

import java.awt.image.BufferedImage;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

import tile.TerrainTile;
import tile.TileIcon;
import tile.TileIconRenderer;
import tile.TileManager;

public class PictureList extends JList
{
	private static final long serialVersionUID = -7052617311159640869L;
	private TileIconRenderer iconRenderer = new TileIconRenderer();
	private DefaultListModel dlm;
	
	public PictureList()
	{
		TileManager.initialize();
		dlm = TileManager.Tiles.getModel();
		this.setModel(dlm);
		
		this.setCellRenderer(iconRenderer);
		
		setLayoutOrientation(JList.HORIZONTAL_WRAP);
		setVisibleRowCount(-1);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setFixedCellWidth((int)1.5*TerrainTile.width);
		setFixedCellHeight((int)1.5*TerrainTile.height);
	}
	
	public void addItem(BufferedImage img)
	{
		dlm.addElement(new TileIcon(img));
	}
	
	public BufferedImage getFocusedImg()
	{
		if(this.getSelectedIndex() == -1)
			return null;
		
		return ((TileIcon)this.getModel().getElementAt(this.getSelectedIndex())).getImage();
	}
}
