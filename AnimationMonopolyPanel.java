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
	Font title = null;
	String strColor; 
	String strDice1 = "";
	String strDice2 = ""; 
	String strDiceSum = "";
	int intMoney = 1500;
	String strMoney = "$"+intMoney;
	int intPropertyN = 0; 
	String strPropertyN = ""; 
	int intYOUx =  650; 
	int intYOUy = 650;
	int intPlayerCount =0; 
	int intPlayer = 0; 
	int intTurn = 1; 
	
	// indicate which colours were selected
	boolean blnBlue;
	boolean blnGreen; 
	boolean blnYellow; 
	boolean blnRed; 
	
	// Player one: 
	String strColor1 = "";  
	int intPlayerX1 = -50;
	int intPlayerY1 = -50; 
	
	
	
	// JScrollPane scroll = new JScrollPane(textarea);
	
	
	//Methods
	public void paintComponent(Graphics g){
		g.setColor(Color.GRAY); 
		g.fillRect(0,0,1280,720); 
		g.drawImage(monopolyboard,-260,-3,null);
		
		//DRAWING DIE
		g.setColor(Color.BLACK);
		g.fillRect(720,3,142,100);
		g.setFont(title);
		g.drawString("CHATBOX",1050,310);
		g.drawString("PROPERTIES",730,310);
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
		
		// load the rolled number into the dice area
		g.setFont(title);
		g.setColor(Color.BLACK); 
		g.drawString(strDice1, 740,73); 
		g.drawString(strDice2, 810, 73);
		
		// Update the status bar: 
		g.setFont(text);
		g.drawString("You rolled a: " +strDiceSum, 875,20);
		g.drawString("You landed on: "+strPropertyN,875, 36);  
		
		//Icon header
		g.setFont(text);
		g.setColor(Color.BLACK); 
		g.drawString("ICON:",1110,130);
		g.drawString("MONEY:" + strMoney,1110,260);
		
		if(strColor.equals("red1")){
			g.setColor(Color.red);
			strColor = "red"; 
		}else if(strColor.equals("blue1")){
			g.setColor(Color.BLUE); 
			strColor = "blue"; 
		}else if(strColor.equals("yellow1")){
			strColor = "yellow"; 
			g.setColor(Color.YELLOW); 
		}else if(strColor.equals("green1")){
			strColor = "green"; 
			g.setColor(Color.GREEN); 
		}
		
		
		// draw the profile box in:
		g.fillRect(1164,163,50,50); 
		

		// set up your character
		g.fillRect(intYOUx, intYOUy, 25,25);
		
		// Other peoples characters: 
		
		// Player one set up: 
		if(strColor1.equals("red")){
			g.setColor(Color.red);
		}else if(strColor1.equals("blue")){
			g.setColor(Color.BLUE); 
		}else if(strColor1.equals("yellow")){
			g.setColor(Color.YELLOW); 
		}else if(strColor1.equals("green")){
			g.setColor(Color.GREEN); 
		}
		g.fillRect(intPlayerX1, intPlayerY1, 25,25);
		
		// Player two set up: 
		
		
		
		
		
		
		
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
		
		
		try{
			monopolyboard = ImageIO.read(new File("MonopolyGameBoard.png"));
		}catch(IOException e){
			System.out.println("Unable to upload image");
		}
		
		//fixing fonts
		//-text font
		try{
			text = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("kabel.ttf")); 
			text = text.deriveFont(Font.PLAIN, 14);
		}
		catch(Exception e){
			//System.out.println(e.toString());
		}
		try{
			text = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("kabel.ttf")); 
			text = text.deriveFont(Font.PLAIN, 14);
		}
		catch(Exception e){
			//System.out.println(e.toString());
			System.out.println("Unable to load font file kabel.ttf. Setting default font"); 
		}
		//-title font
		try{
			title = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("kabel.ttf")); 
			title = text.deriveFont(Font.PLAIN, 30);
		}
		catch(Exception e){
			//System.out.println(e.toString());
		}
		try{
			title = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("kabel.ttf")); 
			title = text.deriveFont(Font.PLAIN, 30);
		}
		catch(Exception e){
			//System.out.println(e.toString());
			System.out.println("Unable to load font file kabel.ttf. Setting default font"); 
		}
	}	
}
