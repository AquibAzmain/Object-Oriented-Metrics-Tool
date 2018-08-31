package shapePack;

import java.awt.*;

public class Circle extends IShape{
	public int diameter;
	public Color color;

	public Circle(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	public void drawing() {
		repaint();
	}

	public void paint(Graphics g) {
		g.setColor(color);
		g.fillOval(x, y, diameter, diameter);
	}

}
