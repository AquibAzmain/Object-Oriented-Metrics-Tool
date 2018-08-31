package continentPack;

import compositePack.AsianHouse;
import compositePack.AsianTree;
import graphicsPack.MyFrame;

public class Asia extends Continent {

	public Asia( MyFrame frame) {
		super();
		this.frame = frame;
	}

	@Override
	public void buildContinent() {
		tree = new AsianTree(this.frame);
		house = new AsianHouse(this.frame);
		house.prepare();
		tree.prepare();
	}
}
