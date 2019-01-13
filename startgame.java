import javax.swing.*; 
import java.awt.*; 
import java.awt.image.*; 
import java.io.*; 
import javax.imageio.*; 
import java.awt.event.*; 


public class startgame extends JPanel{
	// properties
	BufferedImage background; 
	JButton back; 
	Font title;
	Font bold; 
	public mainmenu menu; 
	String strAddress = "1345";
	JButton gameplay; 
	int intConnections;
	 
	
	
	
	// methods
	
	public void paintComponent(Graphics g){
		g.setColor(Color.WHITE);
		g.setFont(title);  
		g.fillRect(0,0,1280,720); 	
		g.drawImage(background, 0,0, null); 
		g.drawString("Your unique code is:", 400,150); 
		g.setColor(Color.BLUE); 
		g.setFont(bold); 
		g.drawString(""+strAddress,375,300); 
		System.out.println("made it "+strAddress); 
		
	}

	
	// constructor 
	
	
	public startgame(){
		super(); 
		strAddress = "12345"; 
		
		// Exit button
		back = new JButton("HOMEPAGE"); 
		back.setSize(140, 30); 
		back.setLocation(1160, 0); 
		

		// start game
		gameplay = new JButton("start game"); 
		gameplay.setSize(140,100); 
		gameplay.setLocation(1140,620); 
		
		try{
			background = ImageIO.read(new File("monopoly.jpg")); 
			title = new Font("kabel.ttf", Font.BOLD, 50);
			bold = new Font("kabel.ttf", Font.BOLD, 100); 
		}catch(IOException e){
			System.out.println("Error"); 
		}
		
		
	}
	

}
