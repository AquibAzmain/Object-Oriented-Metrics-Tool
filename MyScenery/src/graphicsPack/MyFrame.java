package graphicsPack;

import javax.swing.JButton;
import javax.swing.JFrame;

import actionListenerPack.AfricaBuilder;
import actionListenerPack.AmericaBuilder;
import actionListenerPack.AsiaBuilder;
import continentPack.Continent;

public class MyFrame extends JFrame{

	public void initialize() {
		setBounds(100, 100, 500, 300);
		setVisible(true);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton buttonAsia = new JButton("Asia");
//		buttonAsia.addActionListener(new AsiaBuilder(this));
		buttonAsia.setBounds(390, 11, 85, 23);
		add(buttonAsia);
		
		JButton buttonAfrica = new JButton("Africa");
//		buttonAfrica.addActionListener(new AfricaBuilder(this));
		buttonAfrica.setBounds(390, 45, 85, 23);
		add(buttonAfrica);
		
		JButton buttonAmerica = new JButton("America");
//		buttonAmerica.addActionListener(new AmericaBuilder(this));
		buttonAmerica.setBounds(390, 79, 85, 23);
		add(buttonAmerica);
		
	}
}
