package compositePack;

import java.awt.Color;

import javax.swing.JPanel;

import graphicsPack.MyFrame;
import shapePack.Circle;
import shapePack.IShape;
import shapePack.Rectangle;

public class AfricanTree extends CompositeShape{
	MyFrame frame;

	public AfricanTree(MyFrame frame) {
		super();
		this.frame = frame;
	}

	@Override
	public void prepare() {
		Circle c1 = new Circle(210,50);
		Circle c2 = new Circle(240,40);
		c1.color = Color.green;
		c1.diameter = 80;
		c2.color = Color.yellow;
		c2.diameter = 60;
		
		shapeList.add(c1);
		shapeList.add(c2);
		Rectangle r1 = new Rectangle(230,120,90,30, Color.ORANGE);
		shapeList.add(r1);
		
		System.out.println("african tree is created\nShape needed: " + shapeList.size());

		for(IShape is: shapeList){
			frame.add(is);
			is.drawing();
		}
	}

}
