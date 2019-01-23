import javax.swing.*; 
import java.awt.*; 
import java.awt.image.*; 
import java.io.*; 
import javax.imageio.*; 
import java.awt.event.*; 

/**<h1>Server Page</h1>
 * Player has decided to be the server that starts the game <br>
 * Player must wait for players (clients) to type in the IP address displayed <br>
 * Player can type their name in the textfield but must press enter or it will not display
 * */
public class startgame extends JPanel{
	// properties
	BufferedImage background; 
	JButton back; 
	Font title;
	Font bold; 
	String strAddress = "1345";
	JButton gameplay; 
	int intConnections;
	Font subtitle; 
	JTextField TFname; 
	String strName; 
	
	
	
	// methods
	/**Overriding paint component method from JPanel*/
	public void paintComponent(Graphics g){
		g.setColor(Color.WHITE);
		g.setFont(title);  
		g.fillRect(0,0,1280,720); 	
		g.drawImage(background, 0,0, null); 
		g.drawString("Your IP Address is:", 400,150); 
		g.setColor(Color.BLUE); 
		g.setFont(bold); 
		g.drawString(""+strAddress,375,250); 
		g.setColor(Color.WHITE); 
		g.setFont(subtitle); 
		g.drawString("Enter Name", 575, 300);
		
	}

	
	// constructor 
	
	/**Constructor for server page (player that starts the game)*/
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
		
		// Textfield name button
		TFname = new JTextField(); 
		TFname.setSize(200,25); 
		TFname.setLocation(530,320); 
		
		try{
			background = ImageIO.read(new File("monopoly.jpg")); 
			title = new Font("kabel.ttf", Font.BOLD, 50);
			bold = new Font("kabel.ttf", Font.BOLD, 100); 
			subtitle = new Font("kabel.ttf", Font.BOLD, 20); 
		}catch(IOException e){
			System.out.println("Error"); 
		}
		
		
	}
	

}
