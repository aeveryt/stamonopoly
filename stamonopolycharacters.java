import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.awt.Font;

/**<h1>St.Augustine Monopoly Characters Page</h1>
 * Players can decide what colour to represent their character.
 * */
public class stamonopolycharacters extends JPanel{
	
	//Properties
	Font title;
	/**Player selects red as their icon character*/
	public JButton select1 = new JButton("Select");
	/**Player selects blue as their icon character*/
	public JButton select2 = new JButton("Select");
	/**Player selects yellow as their icon character*/
	public JButton select3 = new JButton("Select");
	/**Player selects green as their icon character*/
	public JButton select4 = new JButton("Select");
	/**After selecting a colour, player can start gameplay*/
	public JButton gameplay = new JButton("Play"); 
	
	boolean blnsta1;
	boolean blnsta2;
	boolean blnsta3;
	boolean blnsta4;
	int intPlayerN = 1; 
	Font text; 
	String strData = "";
	
	//Methods
	public void paintComponent(Graphics g){
		g.setFont(title);
		g.setColor(Color.BLACK);
		g.fillRect(0,0,1280,720);
	//Character 1
		g.setColor(Color.RED);
		g.fillRect(140,400,25,25);
	//Character 2
		g.setColor(Color.BLUE);
		g.fillRect(440,400,25,25);
	//Character 3	
		
		g.setColor(Color.YELLOW);
		g.fillRect(780,400,25,25);
	//Character 4
		g.setColor(Color.GREEN);
		g.fillRect(1100,400,25,25);
		
	// lISTS THE NUMBER OF PLAYERS: 
		g.setColor(Color.WHITE); 
		g.drawString("Number of players: "+intPlayerN+"", 1150, 25); 
		
		
	}
	
	
	//Constructors
	/**Constructor for St.Augustine Monopoly Characters Page*/
	public stamonopolycharacters(){
		super();
		
		//Select 1;
		select1.setSize(100,50);
		select1.setLocation(100,480);

		
		//Select 2;
		select2.setSize(100,50);
		select2.setLocation(400,480);
		
		//Select 3;
		select3.setSize(100,50);
		select3.setLocation(740,480);
		
		//Select 4
		select4.setSize(100,50);
		select4.setLocation(1060,480);
		
		// gameplay
		gameplay.setSize(140,100); 
		gameplay.setLocation(1140,620); 
		
		
		// font
		try{
			text = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("kabel.ttf")); 
			text = text.deriveFont(Font.BOLD, 35);
		}
		catch(Exception e){
			System.out.println("Unable to load font file kabel.ttf. Setting default font"); 
		}
		
		
	}

}
