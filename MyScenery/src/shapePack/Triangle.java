package shapePack;

import java.awt.Color;
import java.awt.Graphics;

public class Triangle extends IShape{
	public Color color;
	public Triangle(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public void drawing() {
		repaint();
	}
	
	public void paint(Graphics g) {
		int []xpoints = {x, x+50, x+100};
		int []ypoints = {y,y-50, y};
		g.setColor(color);
		g.fillPolygon(xpoints, ypoints, 3);
	}


}
