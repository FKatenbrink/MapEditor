package tile;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

public class TileIconRenderer extends JPanel implements ListCellRenderer
{
	private static final long serialVersionUID = 6874803287029929569L;
	private JLabel imgLabel;
	private DefaultListCellRenderer dlcr = new DefaultListCellRenderer();
	
	public TileIconRenderer()
	{
		this.setLayout(new BorderLayout());
		imgLabel = new JLabel();
		add(imgLabel, BorderLayout.CENTER);
	}
	
	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
	{
		dlcr.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		setBorder(dlcr.getBorder());
		setBackground(dlcr.getBackground());
		imgLabel.setIcon((Icon) value);
		
		return this;
	}
}
