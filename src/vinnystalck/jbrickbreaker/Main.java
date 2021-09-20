package vinnystalck.jbrickbreaker;

import vinnystalck.jbrickbreaker.game.GameFrame;
import vinnystalck.jbrickbreaker.game.GamePanel;

public class Main {
	public static void main(String[] args) throws Exception {
		GameFrame gameFrame = new GameFrame();
		GamePanel gamePanel = new GamePanel();

		gameFrame.setVisible(true);
		gameFrame.add(gamePanel);
	}
}
