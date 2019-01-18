//Can implement in Animation Panel layer
//Tester file for the properties program before adding to main

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.awt.Font;
import java.io.*;

public class cardsandtiles extends JPanel{

	//Properties
	BufferedImage communitycards;
	BufferedImage chancecards;
	
	
	//Methods
	public void paintComponent(Graphics g){
		
		
	}
	
	//Constructors
	public cardsandtiles(){
		super();
	
	
		try{
			communitycards = ImageIO.read(new File("community.jpg"));
		}catch(IOException e){
			System.out.println("unable to load image ");
		}
	}
	
	


}
