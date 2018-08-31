package compositePack;

import java.awt.Color;

import javax.swing.JPanel;

import graphicsPack.MyFrame;
import shapePack.IShape;
import shapePack.Rectangle; 

public class AmericanHouse extends CompositeShape{
	MyFrame frame;

	public AmericanHouse(MyFrame frame) {
		super();
		this.frame = frame;
	}
	@Override
	public void prepare() {
		Rectangle r1 = new Rectangle(200,110,100,80,Color.magenta);
		shapeList.add(r1);
		Rectangle r6 = new Rectangle(190,100,10,100,Color.black);
		shapeList.add(r6);
		Rectangle r7 = new Rectangle(190,210,10,100,Color.black);
		shapeList.add(r7);
		

		System.out.println("American house is created\nShape needed: " + shapeList.size());

		for(IShape is: shapeList){
			frame.add(is);
			is.drawing();
		}
	}

}
