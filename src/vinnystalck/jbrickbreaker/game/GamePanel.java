package vinnystalck.jbrickbreaker.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import vinnystalck.jbrickbreaker.game.objects.Ball;
import vinnystalck.jbrickbreaker.game.objects.Paddle;
import vinnystalck.jbrickbreaker.game.objects.Wall;

public class GamePanel extends JPanel implements KeyListener, ActionListener {

	public static final Color BACKGROUND_COLOR = Color.DARK_GRAY;
	public static final Color OBJECTS_COLOR = Color.LIGHT_GRAY;
	public static final int RIGHT_BORDER_OFFSET = 19;

	Paddle paddle = new Paddle();
	Ball ball = new Ball();

	private boolean play = false;

	private Timer timer;
	private int delay = 8;
	private int totalBricks;
	private int score = 0;

	private Wall wall;

	public GamePanel() {
		setGame(paddle);

		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();

		setBounds(0, 0, GameFrame.WIDTH, GameFrame.HEIGHT);
	}

	private void setGame(Paddle player) {
		ball.set(player);
		int wallRows = 3;
		int wallCols = 8;
		totalBricks = wallRows * wallCols;
		wall = new Wall(wallRows, wallCols);

		repaint();
	}

	public void paint(Graphics g) {
		String font = "SansSerif";

		// Background
		g.setColor(BACKGROUND_COLOR);
		g.fillRect(1, 1, GameFrame.width - 8, GameFrame.height - 8);

		// Bricks
		wall.draw((Graphics2D) g);

		// Score
		g.setColor(OBJECTS_COLOR);
		g.setFont(new Font(font, Font.BOLD, 30));
		g.drawString(""+score, 60, 40);

		// Game over
		if (ball.getYPos() > GameFrame.height) {
			play = false;

			ball.setXDir(0);
			ball.setYDir(0);

			g.setColor(Color.RED);
			g.setFont(new Font(font, Font.BOLD, 30));
			g.drawString("Game Over, Score:" + score, 60, 6*(GameFrame.height/10));
			
		}
		if (totalBricks <= 0) {
			play = false;

			g.setColor(Color.GREEN);
			g.setFont(new Font(font, Font.BOLD, 30));
			g.drawString("You have Won, Score:" + score, 60, 6*(GameFrame.height/10));
		}

		if (!play) {
			g.setColor(OBJECTS_COLOR);
			g.setFont(new Font(font, Font.BOLD, 25));
			g.drawString("Press Enter to Restart", 60, 7*(GameFrame.height/10));
		}

		// Border
		int borderWidth = 3;
		g.setColor(OBJECTS_COLOR);
		g.fillRect(0, 0, borderWidth, GameFrame.height - 8);
		g.fillRect(0, 0, GameFrame.width - 8, borderWidth);
		g.fillRect(GameFrame.width - RIGHT_BORDER_OFFSET, 0, borderWidth, GameFrame.height - 8);

		// Paddle
		g.setColor(OBJECTS_COLOR);
		g.fillRoundRect(paddle.getXPos(), paddle.getYPos(), paddle.getWidth(), paddle.getHeight(), 3, 3);

		// Ball
		g.setColor(OBJECTS_COLOR);
		g.fillOval(ball.getXPos(), ball.getYPos(), ball.getSize(), ball.getSize());

		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();

		if (play) {
			// Bounce on peddle
			if (new Rectangle(ball.getXPos(), ball.getYPos(), ball.getSize(), ball.getSize()).intersects(
					new Rectangle(paddle.getXPos(), paddle.getYPos(), paddle.getWidth(), paddle.getHeight()))) {
				ball.bounceUp();
			}

			// Bounce on brick
			for (int i = 0; i < wall.grid.length; i++) {
				for (int j = 0; j < wall.grid[i].length; j++) {
					if (wall.grid[i][j] > 0) {
						int brickXPos = j * wall.brick.getWidth() + wall.getLeftDistance();
						int brickYPos = i * wall.brick.getHeight() + wall.getTopDistance();
						int brickWidth = wall.brick.getWidth();
						int brickHeight = wall.brick.getHeight();

						int ballXPos = ball.getXPos();
						int ballYPos = ball.getYPos();
						int ballSize = ball.getSize();

						Rectangle brickRect = new Rectangle(brickXPos, brickYPos, brickWidth, brickHeight);
						Rectangle ballRect = new Rectangle(ballXPos, ballYPos, ballSize, ballSize);

						if (ballRect.intersects(brickRect)) {
							int num = 3;
							// Hits top
							if (ballXPos + ballSize-num >= brickRect.x && ballYPos+ballSize < brickRect.y + brickRect.height) {
								ball.bounceUp();
							// Hits bottom
							} else if (ballYPos+5 >= brickRect.y + brickRect.height) {
								ball.bounceDown();
							} else {
								ball.bounceXDir();
							}

							wall.setBrickValue(i, j, 0);
							totalBricks--;
							score++;
						}

					}
				}
			}

			// Move Ball
			ball.move();

			// Bounce on left wall
			if (ball.getXPos() < 0) {
				ball.bounceXDir();
			}
			// Bounce on top wall
			if (ball.getYPos() < 0) {
				ball.bounceYDir();
			}
			// Bounce on right wall
			if (ball.getXPos() > GameFrame.width - RIGHT_BORDER_OFFSET - ball.getSize()) {
				ball.bounceXDir();
			}

		}

		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (paddle.getXPos() > GameFrame.width - paddle.getWidth() - RIGHT_BORDER_OFFSET - paddle.getSpeed()) {
				paddle.setXPos(GameFrame.width - paddle.getWidth() - RIGHT_BORDER_OFFSET);
			} else {
				play = true;
				paddle.moveRight();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (paddle.getXPos() < 3 + paddle.getSpeed()) {
				paddle.setXPos(3);
			} else {
				play = true;
				paddle.moveLeft();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (!play) {
				play = true;
				setGame(paddle);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
