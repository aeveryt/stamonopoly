// CHANGE TO MOUSE LISTENER instead of MouseMotionListner!!!

import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 

public class serverpage implements ActionListener, KeyListener, MouseMotionListener{
	// Properties
	JFrame theframe; 
	JPanel thepanel; 
	JTextField TF; 
	
	
	// methods
	
	// Repaints panel to the screen this is the event listener
	public void actionPerformed(ActionEvent evt){
		
	}
	
	// Keyboard listener
	public void keyReleased(KeyEvent evt){
	
	}
	
	public void keyPressed(KeyEvent evt){
	
	}
	
	public void keyTyped(KeyEvent evt){
	
	}
	
	// mouse events
		public void mouseMoved(MouseEvent evt){
	
	}
	
	public void mouseDragged(MouseEvent evt){

	}
	
	// Constructor
	public serverpage(){
		theframe = new JFrame("Animation with keyboard and mouse"); 
		thepanel = new JPanel(); 
		thepanel.setLayout(null); 
		
	
		thepanel.addMouseMotionListener(this); 
		theframe.addKeyListener(this); 
		
		// textfield
		TF = new JTextField();
		TF.setSize(500,50); 
		TF.setLocation(370, 340);
		TF.addActionListener(this); 
		
		thepanel.add(TF); 
		
		thepanel.setPreferredSize(new Dimension(1280, 720)); 
		
		theframe.setContentPane(thepanel); 
		theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		theframe.pack(); 
		theframe.setVisible(true); 
		
		
	}
	
	
	
	
	// main program 
	public static void main(String[] args){
		new serverpage(); 
	
	}


}

