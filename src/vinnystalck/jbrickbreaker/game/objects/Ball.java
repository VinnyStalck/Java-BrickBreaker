package vinnystalck.jbrickbreaker.game.objects;

public class Ball {

	private final int SIZE = 20;
	private final int SPEED = 2;

	private int xPos = 110;
	private int yPos = 350;
	private int xDir = 1*SPEED;
	private int yDir = -2*SPEED;

	public Ball() {
	}

	/**
	 * Moves the ball to its corresponding direction.
	 */
	public void move() {
		xPos += xDir;
		yPos += yDir;
	}
	/**
	 * Changes the ball direction on the X axis
	 */
	public void bounceXDir() {
		setXDir(-xDir);
	}
	/**
	 * Changes the ball direction on the y axis
	 */
	public void bounceYDir() {
		setYDir(-yDir);
	}
	
	public void bounceUp() {
		setYDir(-Math.abs(yDir));
	}
	
	public void bounceDown() {
		setYDir(Math.abs(yDir));
	}
	
	public void set(Paddle paddle) {
		xPos = paddle.getXPos() + (paddle.getWidth()/2) - (SIZE/2);
		yPos = paddle.getYPos() - (SIZE*2);
		xDir = 1 * SPEED;
		yDir = -2 * SPEED;
	}

	// Getters and Setters
	public int getXPos() {
		return xPos;
	}
	public void setXPos(int xPos) {
		this.xPos = xPos;
	}
	public int getYPos() {
		return yPos;
	}
	public void setYPos(int yPos) {
		this.yPos = yPos;
	}
	public int getXDir() {
		return xDir;
	}
	public void setXDir(int xDir) {
		this.xDir = xDir;
	}
	public int getYDir() {
		return yDir;
	}
	public void setYDir(int yDir) {
		this.yDir = yDir;
	}

	public int getSize() {
		return SIZE;
	}

	public int getSpeed() {
		return SPEED;
	}
}
