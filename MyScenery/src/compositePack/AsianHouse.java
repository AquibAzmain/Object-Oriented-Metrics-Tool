package compositePack;

import java.awt.Color;

import javax.swing.JPanel;

import graphicsPack.MyFrame;
import shapePack.IShape;
import shapePack.Rectangle;
import shapePack.Triangle;

public class AsianHouse extends CompositeShape{
	MyFrame frame;
	
	public AsianHouse(MyFrame frame) {
		super();
		this.frame = frame;
	}

	@Override
	public void prepare() {
		Rectangle r1 = new Rectangle(50,100, 50,100, Color.blue);
		shapeList.add(r1);
		Triangle t1 = new Triangle(50,100);
		t1.color = Color.red;
		shapeList.add(t1);

		System.out.println("Asian house is created\nShape needed: " + shapeList.size());

		for(IShape is: shapeList){
			frame.add(is);
			is.drawing();
		}
	}

}
