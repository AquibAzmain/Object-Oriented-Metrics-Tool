package actionListenerPack;

import java.awt.event.ActionEvent; 
import continentPack.Africa;
import graphicsPack.MyFrame;

public class AfricaBuilder extends Builder{

	public AfricaBuilder(MyFrame frame) {
		super();
		this.frame = frame;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		frame.initialize();
		continent = new Africa(frame);
		continent.buildContinent();
		frame.revalidate();
		
	}

}
