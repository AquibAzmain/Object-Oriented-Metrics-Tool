package actionListenerPack;

import java.awt.event.ActionEvent; 
import continentPack.America;
import graphicsPack.MyFrame;

public class AmericaBuilder extends Builder{

	public AmericaBuilder(MyFrame frame) {
		super();
		this.frame = frame;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		frame.initialize();
		continent = new America(frame);
		continent.buildContinent();
		frame.revalidate();
	}

}
