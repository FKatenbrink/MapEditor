package surface;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

public class ScreenMain extends JFrame
{
	private static final long serialVersionUID = -1281440475811245840L;
	private JMenuBar menuBar;
	private JMenu fileM, editM, viewM, helpM;
	private JMenuItem fileMNewProject, fileMOpenProject;
	private JScrollPane scrollPaneMap;
	private CanvasMap canvasMap;
	private CanvasMiniMap canvasMiniMap;
	private PictureList pictureList;
	private JScrollPane scrollPanePictureList;
	private JSplitPane splitPaneMain, splitPaneEast;
	
	public ScreenMain()
	{
		initComponents();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationByPlatform(true);
		this.setVisible(true);
	}
	
	private void initComponents()
	{	
		//Menü#######################
		menuBar = new JMenuBar();
		
		//Datei-Menü
		fileMNewProject = new JMenuItem("Neues Projekt");
		fileMOpenProject = new JMenuItem("Projekt Öffnen");
		
		fileM = new JMenu("Datei");
		fileM.add(fileMNewProject);
		fileM.add(fileMOpenProject);
		
		menuBar.add(fileM);
		
		//Bearbeiten-Menü
		
		
		editM = new JMenu("Bearbeiten");
		
		menuBar.add(editM);
		
		//Ansicht-Menü
		
		
		viewM = new JMenu("Ansicht");
		
		menuBar.add(viewM);
		
		//Hilfe-Menü
		
		
		helpM = new JMenu("Hilfe");
		
		menuBar.add(helpM);
		
		this.setJMenuBar(menuBar);
		
		//PictureList################
		pictureList = new PictureList();
		scrollPanePictureList = new JScrollPane(pictureList);
		
		//CanvasMap##################
		canvasMap = new CanvasMap(pictureList);
		scrollPaneMap = new JScrollPane(canvasMap);
		
		//Mini-Map###################
		canvasMiniMap = canvasMap.getMiniMap();
		
		splitPaneEast = new JSplitPane(JSplitPane.VERTICAL_SPLIT, canvasMiniMap, scrollPanePictureList);
		
		splitPaneMain = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPaneMap, splitPaneEast);
		splitPaneMain.setResizeWeight(0.5);
		this.add(splitPaneMain);
		
		this.pack();
	}
}
