package continentPack;

import compositePack.AmericanHouse; 
import compositePack.AmericanTree;
import graphicsPack.MyFrame;

public class America extends Continent{
	public America( MyFrame frame) {
		super();
		this.frame = frame ;
	}

	@Override
	public void buildContinent() {
		tree = new AmericanTree(this.frame);
		house = new AmericanHouse(this.frame);
		tree.prepare();
		frame.revalidate();
		house.prepare();
	}
}
