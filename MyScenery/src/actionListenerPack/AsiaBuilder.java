package actionListenerPack;

import java.awt.event.ActionEvent; 
import continentPack.Asia;
import graphicsPack.MyFrame;

public class AsiaBuilder extends Builder{

	public AsiaBuilder(MyFrame frame) {
		super();
		this.frame = frame;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		frame.initialize();
		continent = new Asia(frame);
		continent.buildContinent();
		frame.revalidate();
	}

}
