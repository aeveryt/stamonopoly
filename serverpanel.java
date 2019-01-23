import javax.swing.*; 
import java.awt.*; 
import java.awt.image.*; 
import java.io.*; 
import javax.imageio.*; 
import java.awt.event.*; 

/**<h1> Client or Server Page</h1>
 * Player decides whether or not to start (server) or join (client) the game
 * */
public class serverpanel extends JPanel{
	// properties
	BufferedImage background; 
	/**Player can decide to start the game and become a server*/
	public JButton start;
	/**Player can decide to join a game and become a client*/
	public JButton existing; 
	/**Button goes back to main menu*/
	public JButton back; 
	
	// methods
	/**Overriding paint component method from JPanel*/
	public void paintComponent(Graphics g){
		g.setColor(Color.WHITE); 
		g.fillRect(0,0,1280,720); 	
		g.drawImage(background, 0, 0, null); 
		
	}

	// constructor 
	
	/**The constructor for the panel to decide to be client or server*/
	public serverpanel(){
		super(); 
	
		// first button - choosing to be the server
		start = new JButton("Start a new game"); 
		start.setSize(300, 60); 
		start.setLocation(290, 340); 
		
		
		// second button - choosing to be the client
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
