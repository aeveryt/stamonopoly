import javax.swing.*; 
import java.awt.*; 
import java.awt.image.*; 
import java.io.*; 
import javax.imageio.*; 
import java.awt.event.*; 


public class joingame extends JPanel{
	// properties
	BufferedImage background; 
	JButton back;
	JTextField TF; 
	Font title;

	
	
	// methods
	
	public void paintComponent(Graphics g){
		g.setColor(Color.WHITE); 
		g.fillRect(0,0,1280,720); 	
		g.drawImage(background, 0, 0, null); 
		g.setFont(title); 
		g.drawString("Enter game code:", 400,250);
	}

	
	// constructor 
	
	
	public joingame(){
		super(); 
		
				
		// Exit button
		back = new JButton("HOMEPAGE"); 
		back.setSize(140, 30); 
		back.setLocation(1160, 0); 
		
		// textfield
		
		TF = new JTextField();
		TF.setSize(500,50); 
		TF.setLocation(370, 340);
		
		
		try{
			background = ImageIO.read(new File("monopoly.jpg")); 
			title = new Font("kabel.ttf", Font.BOLD, 50);
		}catch(IOException e){
			System.out.println("Error"); 
		}
		
		
	}
	

}
