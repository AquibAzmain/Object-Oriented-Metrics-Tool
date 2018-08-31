package shapePack;

import java.awt.Color;

import javax.swing.JPanel;

public abstract class IShape extends JPanel{
	int x,y;
	
	public IShape() {
		super();
		setBounds(10, 10, 400, 500);
	}

	public abstract void drawing();
	
}
