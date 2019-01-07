import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;

public class stamonopoly{
	//Properties
	JFrame frame = new JFrame("STA MONOPOLY");
	JPanel panel = new AnimationMonopolyPanel();
	Timer timer;
	
	//Methods
	
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
	public static void main (String[]  args){
		new stamonopoly();
	}
	
}
