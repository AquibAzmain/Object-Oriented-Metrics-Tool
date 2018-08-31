package continentPack;

import compositePack.CompositeShape;
import graphicsPack.MyFrame;

public abstract class Continent {
	public CompositeShape tree, house;
	public MyFrame frame;

	public abstract void buildContinent();
}
