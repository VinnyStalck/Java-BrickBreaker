package vinnystalck.jbrickbreaker.game.objects;

import vinnystalck.jbrickbreaker.game.GameFrame;

public class Paddle {
	private final int WIDTH = 100;
	private final int HEIGHT = 10;
	private final int SPEED = 20;
	private final int yPos = GameFrame.height - 60;

	private int xPos = (GameFrame.width - WIDTH) / 2;

	public Paddle() {

	}

	public void moveRight() {
		xPos += SPEED;
	}
	public void moveLeft() {
		xPos -= SPEED;
	}

	// Getters and Setter

	public int getXPos() {
		return xPos;
	}
	public void setXPos(int xPos) {
		this.xPos = xPos;
	}

	public int getYPos() {
		return yPos;
	}

	public int getWidth() {
		return WIDTH;
	}

	public int getHeight() {
		return HEIGHT;
	}

	public int getSpeed() {
		return SPEED;
	}
}
