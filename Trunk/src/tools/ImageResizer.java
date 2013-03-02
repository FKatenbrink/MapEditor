package tools;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class ImageResizer extends JFrame
{
	private static final long serialVersionUID = 3197543761953910196L;
	private JTextField txt1, txt2;
	private JButton but;

	public ImageResizer()
	{
		txt1 = new JTextField("Breite");
		txt2 = new JTextField("Hšhe");
		but = new JButton("Los");
		but.addMouseListener(
				new MouseListener()
				{
					@Override
					public void mouseClicked(MouseEvent me)
					{
						JFileChooser c = new JFileChooser();
						c.setFileSelectionMode(JFileChooser.FILES_ONLY);
						c.showOpenDialog(ImageResizer.this);
						File dir = c.getCurrentDirectory();
						int w = Integer.parseInt(txt1.getText());
						int h = Integer.parseInt(txt2.getText());
						File[] files = dir.listFiles();
						BufferedImage bi;
						String newDir = dir.getAbsolutePath() + "/x-" + txt1.getText() + "-y-" + txt2.getText();
						new File(newDir).mkdir();

						PrintWriter out = null;
						try
						{
							out = new PrintWriter(dir.getAbsolutePath() + "/tiles.txt");
						}
						catch (IOException ex)
						{
						}

						for (int i = 0; i < files.length; i++)
						{
							try
							{
								bi = ImageIO.read(files[i]);
								Image scaledImg = bi.getScaledInstance(w, h, BufferedImage.SCALE_DEFAULT);
								BufferedImage bufImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
								Graphics g = bufImg.getGraphics();
								g.drawImage(scaledImg, 0, 0, null);
								String n = files[i].getName();
								String nex = n.substring(0, n.lastIndexOf('.'));
								out.println(nex + ";" + "./x-" + txt1.getText() + "-y-" + txt2.getText() + "/" + nex + ".png");
								//Hier die TextDatei erstellen.
								File outputfile = new File(newDir + "/" + nex + ".png");
								ImageIO.write(bufImg, "png", outputfile);
							}
							catch (IOException ex)
							{
							}
						}
						
						out.close();
					}

					@Override
					public void mousePressed(MouseEvent me)
					{
					}

					@Override
					public void mouseReleased(MouseEvent me)
					{
					}

					@Override
					public void mouseEntered(MouseEvent me)
					{
					}

					@Override
					public void mouseExited(MouseEvent me)
					{
					}
				});
		add(txt1, BorderLayout.LINE_START);
		add(txt2, BorderLayout.LINE_END);
		add(but, BorderLayout.CENTER);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	public static void main(String[] args)
	{
		@SuppressWarnings("unused")
		ImageResizer ir = new ImageResizer();
	}
}
