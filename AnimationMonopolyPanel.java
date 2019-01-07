import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class AnimationMonopolyPanel extends JPanel{
	//Properties
	
	BufferedImage monopolyboard;
	
	//Methods
	public void paintComponent(Graphics g){
		g.drawImage(monopolyboard,-260,-3,null);
	}

	//Constructor
	public AnimationMonopolyPanel(){
		super();
		try{
			monopolyboard = ImageIO.read(new File("MonopolyGameBoard.png"));
		}catch(IOException e){
			System.out.println("Unable to upload image");
		}
	}
	
}
