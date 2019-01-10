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
	public mainmenu menu; 
	
	
	// methods
	
	public void paintComponent(Graphics g){
		g.setColor(Color.WHITE);
		g.setFont(title);  
		g.fillRect(0,0,1280,720); 	
		g.drawImage(background, 0,0, null); 
		g.drawString("Your unique code is:", 400,150); 
		// g.drawString(menu.ssm.getMyAddress(), 0,0); 
		// g.drawString(menu.strAddress,0,0); 
	}

	
	// constructor 
	
	
	public startgame(){
		super(); 
				
		// Exit button
		back = new JButton("HOMEPAGE"); 
		back.setSize(140, 30); 
		back.setLocation(1160, 0); 

		
		try{
			background = ImageIO.read(new File("monopoly.jpg")); 
			title = new Font("kabel.ttf", Font.BOLD, 50);
		}catch(IOException e){
			System.out.println("Error"); 
		}
		
		
	}
	

}
