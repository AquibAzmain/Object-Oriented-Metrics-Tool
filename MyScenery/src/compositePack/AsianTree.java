package compositePack;

import java.awt.Color;

import javax.swing.JPanel;

import graphicsPack.MyFrame;
import shapePack.Circle;
import shapePack.IShape;
import shapePack.Rectangle;

public class AsianTree extends CompositeShape{
	MyFrame frame;

	public AsianTree(MyFrame frame) {
		super();
		this.frame = frame;
	}

	@Override
	public void prepare() {
		Rectangle r1 = new Rectangle(240,150,80,20,Color.ORANGE);
		shapeList.add(r1);

		Circle c1 = new Circle(200,80);
		c1.color = Color.green;
		c1.diameter = 100;
		shapeList.add(c1);

		System.out.println("Asian tree is created\nShape needed: " + shapeList.size());
		
		for(IShape is: shapeList){
			frame.add(is);
			is.drawing();
		}
		
	}

}
