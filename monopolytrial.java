import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;

public class monopolytrial implements ActionListener{
	//Properties
	public JFrame theframe; 
	public monopolyinstructions thepanel;
	public Timer thetimer;
	
	
	//Method
	public void actionPerformed (ActionEvent evt){
		//if(evt.getSource() == thetimer){
		//}
	}
	
	//Constructor
	public monopolytrial(){
		
		theframe = new JFrame("test screen");
		thepanel = new monopolyinstructions(); 
		
		//set panel
		thepanel.setPreferredSize(new Dimension(1280,720)); 
		thepanel.setLayout(null); 
		
		// set frame
		theframe.setContentPane(thepanel);
		thepanel.add(thepanel.backtomain);
		theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theframe.pack();
		theframe.setVisible(true);
		
		//set timer
		//thetimer = new Timer (1000/60,this);
		//thetimer.start();
	}
	//Main Method
	public static void main(String [] args){
		new monopolytrial();
	}

}
