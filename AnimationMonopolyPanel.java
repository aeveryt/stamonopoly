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
	JButton rolldie;
	JButton next;
	JButton back;
	Font text = null;
	String strColor; 
	
	
	// JScrollPane scroll = new JScrollPane(textarea);
	
	
	//Methods
	public void paintComponent(Graphics g){
		g.setColor(Color.GRAY); 
		g.fillRect(0,0,1280,720); 
		g.drawImage(monopolyboard,-260,-3,null);
		
		//DRAWING DIE
		g.setColor(Color.BLACK);
		g.fillRect(720,3,142,100);
		g.setFont(text);
		g.drawString("CHATBOX",1000,300);
		g.setColor(Color.WHITE);
		g.fillRect(727,36,60,60);
		g.fillRect(795,36,60,60);
	
		
		//status bar
		g.fillRect(867,3,407,99);
		
		//turn display
		g.fillRect(724,229,374,40);
		
		//cards
		g.fillRect(844,108,255,115);
		
		//profile
		g.fillRect(1104,108,170,160);
		
		//properties outline
		g.fillRect(725,331,282,315);
		
		//Icon header
		g.setColor(Color.BLACK); 
		g.drawString("ICON:",1104,1125);
		
		if(strColor.equals("red")){
			g.setColor(Color.red);
		}else if(strColor.equals("blue")){
			g.setColor(Color.BLUE); 
		}
		
		// draw that box in:
		
		g.fillRect(1164,163,50,50); 
		
	}

	//Constructor
	public AnimationMonopolyPanel(){
		super();
		
		//CHAT FEATURE
		//-chatbox
		textfield.setLocation(1020,635);
		textfield.setSize(255,80);
		
		//-scroll
		scroll = new JScrollPane(textarea); 
		scroll.setLocation(1020,330);
		scroll.setSize(255,297);
		textarea.setEnabled(false);
		
		//buttons
		buy = new JButton("Buy");
		buy.setSize(120,60);
		buy.setLocation(720,105);
		
		dontbuy = new JButton("Don't buy");
		dontbuy.setSize(120,60);
		dontbuy.setLocation(720,166);
		
		rolldie = new JButton("Roll the die!");
		rolldie.setSize(130,20);
		rolldie.setLocation(727,10);
		
		next = new JButton("Next");
		next.setLocation(723,655);
		next.setSize(141,60);
		
		back = new JButton("Back");
		back.setLocation(867,655);
		back.setSize(141,60);
		
		text = new Font("kabel.ttf", Font.PLAIN, 12);
		try{
			monopolyboard = ImageIO.read(new File("MonopolyGameBoard.png"));
		}catch(IOException e){
			System.out.println("Unable to upload image");
		}
	}
	
}
