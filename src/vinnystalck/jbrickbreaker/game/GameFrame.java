package vinnystalck.jbrickbreaker.game;

import java.awt.HeadlessException;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

public class GameFrame extends JFrame {

	public static final int height = 540;
	public static final int width = 960;

	public GameFrame() throws HeadlessException {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int screenWidth = gd.getDisplayMode().getWidth();
		int screenHeight = gd.getDisplayMode().getHeight();

		int x = (screenHeight - height) / 2;
		int y = (screenWidth - width) / 2;

		setBounds(x, y, width, height);
		setTitle("Java BrickBreaker");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
