import javax.swing.*; 
import java.awt.*; 
import java.awt.image.*; 
import java.io.*; 
import javax.imageio.*; 

public class serverpanel extends JPanel{
	// properties
	
	// methods
	
	public void paintComponent(Graphics g){
		g.setColor(Color.WHITE); 
		g.fillRect(0,0,1280,720); 	
	}

	
	// constructor 
	
	
	public serverpanel(){
		super(); 
	}
	

}
