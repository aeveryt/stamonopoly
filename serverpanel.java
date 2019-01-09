import javax.swing.*; 
import java.awt.*; 
import java.awt.image.*; 
import java.io.*; 
import javax.imageio.*; 
import java.awt.event.*; 


public class serverpanel extends JPanel{
	// properties
	BufferedImage background; 
	public JButton start;
	public JButton existing; 
	public JButton back; 
	public serverpanel thepanel; 
	
	
	// methods
	
	public void paintComponent(Graphics g){
		g.setColor(Color.WHITE); 
		g.fillRect(0,0,1280,720); 	
		
		g.drawImage(background, 0, 0, null); 
		
	}

	
	// constructor 
	
	
	public serverpanel(){
		super(); 
		

		
		// first button
		start = new JButton("Start a new game"); 
		start.setSize(300, 60); 
		start.setLocation(290, 340); 
		
		
		// second button 
		existing = new JButton("Join an existing game"); 
		existing.setSize(300,60); 
		existing.setLocation(690, 340); 
		
		
		
		// Exit button
		back = new JButton("BACK"); 
		back.setSize(80, 30); 
		back.setLocation(1200, 0); 
		
		
		try{
			background = ImageIO.read(new File("monopoly.jpg")); 
		}catch(IOException e){
			System.out.println("Error"); 
		}
		
		
	}
	

}
