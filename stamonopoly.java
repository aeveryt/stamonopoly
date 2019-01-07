import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;

public class stamonopoly implements ActionListener{
	//Properties
	JFrame frame = new JFrame("STA MONOPOLY");
	JPanel panel = new AnimationMonopolyPanel();
	Timer timer = new Timer(1000/60,this);
	
	//Methods
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == timer){
			panel.repaint();
		}
	}
	
	//Constructor
	public stamonopoly(){
		//Panel 
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(1280,720));
		
		
		//Frame
		frame.setContentPane(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false); //prevents windows from being resized
		
		
	}
	//Main Method
	public static void main (String[] args){
		new stamonopoly();
	}
	
}
