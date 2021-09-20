package vinnystalck.jbrickbreaker.game.objects;

import java.awt.Graphics2D;

import vinnystalck.jbrickbreaker.game.GameFrame;
import vinnystalck.jbrickbreaker.game.GamePanel;

import java.awt.BasicStroke;
import java.awt.Color;

public class Wall {
	public int grid[][];
	public Brick brick = new Brick();
	private int brickArc = 3;

	private int padding = 60;
	private int topPadding = padding;
	private int leftPadding = padding;
	private int rightPadding = leftPadding;

	public Wall(int row, int col) {
		grid = new int[row][col];

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j] = 1;
			}
		}

		brick.setWidth((GameFrame.width - (rightPadding * 2) - GamePanel.RIGHT_BORDER_OFFSET) / col);
		brick.setHeight(150 / row);
	}

	public void draw(Graphics2D g) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] > 0) {
					g.setColor(Color.LIGHT_GRAY);
					g.fillRoundRect(j * brick.getWidth() + leftPadding, i * brick.getHeight() + topPadding,
							brick.getWidth(), brick.getHeight(), brickArc, brickArc);

					g.setStroke(new BasicStroke(3));
					g.setColor(GamePanel.BACKGROUND_COLOR);
					g.drawRoundRect(j * brick.getWidth() + leftPadding, i * brick.getHeight() + topPadding,
							brick.getWidth(), brick.getHeight(), brickArc, brickArc);
				}
			}
		}
	}

	public void setBrickValue(int row, int col, int value) {
		grid[row][col] = value;
	}

	public int getDistance() {
		return padding;
	}

	public void setDistance(int distance) {
		this.padding = distance;
	}

	public int getTopDistance() {
		return topPadding;
	}

	public void setTopDistance(int topDistance) {
		this.topPadding = topDistance;
	}

	public int getLeftDistance() {
		return leftPadding;
	}

	public void setLeftDistance(int leftDistance) {
		this.leftPadding = leftDistance;
	}

	public int getRightDistance() {
		return rightPadding;
	}

	public void setRightDistance(int rightDistance) {
		this.rightPadding = rightDistance;
	}

}
