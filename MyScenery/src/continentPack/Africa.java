package continentPack;

import compositePack.AfricanHouse; 
import compositePack.AfricanTree;
import graphicsPack.MyFrame;

public class Africa extends Continent{
	
	public Africa( MyFrame frame) {
		super();
		this.frame = frame;
	}

	@Override
	public void buildContinent() {
		tree = new AfricanTree(this.frame);
		house = new AfricanHouse(this.frame);
		tree.prepare();
		house.prepare();
	}
}
