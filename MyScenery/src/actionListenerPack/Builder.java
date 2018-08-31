package actionListenerPack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

import continentPack.Continent;
import graphicsPack.MyFrame;

public abstract class Builder implements ActionListener{
	MyFrame frame;
	JPanel panel;
	Continent continent;
	
	@Override
	public abstract void actionPerformed(ActionEvent e);
}
