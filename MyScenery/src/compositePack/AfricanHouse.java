package compositePack;

import java.awt.Color;

import javax.swing.JPanel;

import graphicsPack.MyFrame; 
import shapePack.Circle;
import shapePack.IShape;
import shapePack.Triangle;

public class AfricanHouse extends CompositeShape{
	MyFrame frame;

	public AfricanHouse(MyFrame frame) {
		super();
		this.frame = frame;
	}

	@Override
	public void prepare() {
		Circle c1 = new Circle(40,100);
		c1.diameter = 80;
		c1.color = Color.red;
		shapeList.add(c1);
		Triangle t1 = new Triangle(30,120);
		t1.color = Color.red;
		shapeList.add(t1);
		
		System.out.println("african house is created\nShape needed: " + shapeList.size());
	

		for(IShape is: shapeList){
			frame.add(is);
			is.drawing();
		}
	}

}
