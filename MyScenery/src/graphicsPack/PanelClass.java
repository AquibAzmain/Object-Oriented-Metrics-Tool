package graphicsPack;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import actionListenerPack.AfricaBuilder;
import actionListenerPack.AmericaBuilder;
import actionListenerPack.AsiaBuilder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class PanelClass {
	private MyFrame frame;
	
	public PanelClass() {
		initialize();
	}

	private void initialize() {
		frame = new MyFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JButton asiaButton = new JButton("Asia");
		asiaButton.setBounds(10, 19, 89, 23);
		asiaButton.addActionListener(new AsiaBuilder(this.frame));
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(asiaButton);
		
		JButton africaButton = new JButton("Africa");
		africaButton.setBounds(109, 19, 89, 23);
		africaButton.addActionListener(new AfricaBuilder(this.frame));
		frame.getContentPane().add(africaButton);
		
		JButton americaButton = new JButton("America");
		americaButton.setBounds(221, 19, 89, 23);
		americaButton.addActionListener(new AmericaBuilder(this.frame));
		frame.getContentPane().add(americaButton);
		
		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				initialize();
			}
		});
		resetButton.setBounds(335, 19, 89, 23);
		frame.getContentPane().add(resetButton);
	}
}
