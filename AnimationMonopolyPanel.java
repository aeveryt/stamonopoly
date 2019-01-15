import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;

public class AnimationMonopolyPanel extends JPanel{
	//Properties
	
	BufferedImage monopolyboard;
	JTextField textfield = new JTextField();
	Timer timer;
	JTextArea textarea = new JTextArea();
	JScrollPane scroll; 
	JButton buy;
	JButton dontbuy;
	
	// JScrollPane scroll = new JScrollPane(textarea);
	
	
	//Methods
	public void paintComponent(Graphics g){
		g.setColor(Color.GRAY); 
		g.fillRect(0,0,1280,720); 
		g.drawImage(monopolyboard,-260,-3,null);
		
		//DRAWING DIE
		g.setColor(Color.BLACK);
		g.fillRect(720,3,142,100);
		g.setColor(Color.WHITE);
		g.fillRect(727,36,60,60);
		g.fillRect(795,36,60,60);
		
		//status bar
		g.fillRect(867,3,405,100);
		
	}

	//Constructor
	public AnimationMonopolyPanel(){
		super();
		
		//CHAT FEATURE
		//-chatbox
		textfield.setSize(500,50);
		textfield.setLocation(750,655);
		//-scroll
		scroll = new JScrollPane(textarea); 
		scroll.setSize(500,150);
		scroll.setLocation(750,500); 
		
		//buttons
		buy = new JButton("Buy");
		buy.setSize(150,60);
		buy.setLocation(720,110);
		
		dontbuy = new JButton("Don't buy");
		dontbuy.setSize(150,60);
		dontbuy.setLocation(720,175);
		
		try{
			monopolyboard = ImageIO.read(new File("MonopolyGameBoard.png"));
		}catch(IOException e){
			System.out.println("Unable to upload image");
		}
	}
	
}
