package mapeditor;

import surface.ScreenMain;

public class MapEditor
{
	private static ScreenMain mainScreen = new ScreenMain();
	
	public MapEditor()
	{
		
	}
	
	public static ScreenMain getMainScreen()
	{
		return mainScreen;
	}
	
	public static void main(String[] args)
	{
		@SuppressWarnings("unused")
		MapEditor me = new MapEditor();
	}
}
