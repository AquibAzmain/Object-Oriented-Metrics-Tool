package compositePack;

import java.awt.Color;

import javax.swing.JPanel;

import graphicsPack.MyFrame;
import shapePack.IShape;
import shapePack.Rectangle;
import shapePack.Triangle;

public class AmericanTree extends CompositeShape{
	MyFrame frame;

	public AmericanTree(MyFrame frame) {
		super();
		this.frame = frame;
	}

	@Override
	public void prepare() {
		Triangle t1 = new Triangle(50,100);
		t1.color = Color.green;
		shapeList.add(t1);
		Rectangle r1 = new Rectangle(90,100, 50, 20, Color.ORANGE);
		shapeList.add(r1);

		System.out.println("American tree is created\nShape needed: " + shapeList.size());

		for(IShape is: shapeList){
			frame.add(is);
			is.drawing();
		}
	}

}
