package shapePack;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends IShape{
	public int heigth,width;
	public Color color;
	public Rectangle(int x, int y, int height, int width, Color color) {
		super();
		this.x = x;
		this.y = y;
		this.heigth = height;
		this.width = width;
		this.color = color;
	}

	public void drawing() {
		repaint();
	}
	
	public void paint(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, width,heigth);
	}

}
