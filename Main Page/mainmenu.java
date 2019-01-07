
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 
import java.awt.Font; 

public class mainmenu implements ActionListener{

	public JFrame theframe; 
	public JPanel thepanel;
	public JButton InstrucButt;
	public JButton ExitButt; 
	public JButton PlayButt;
	

	
	// method 
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == InstrucButt){
		
		}else if(evt.getSource() == ExitButt){
			System.exit(0);
		
		}else if(evt.getSource() == PlayButt){
		
		}
	
	}
	
	// constructor 
	public mainmenu(){
	
		theframe = new JFrame("Main Menu");
		thepanel = new JPanel(); 
		thepanel.setPreferredSize(new Dimension(1280,720)); 
		thepanel.setLayout(null); 
		
		
		// instruction button 
		
		InstrucButt = new JButton("Instructions"); 
		InstrucButt.setSize(120, 30); 
		InstrucButt.setLocation(110,250); 
		InstrucButt.addActionListener(this); 
		
		// play button 

	// 	PlayButt.setFont(new Font("kabel.ttf", Font.PLAIN, 40));
		PlayButt = new JButton("Play"); 
		PlayButt.setSize(200, 35); 
		PlayButt.setLocation(75,200); 
		PlayButt.addActionListener(this); 
		
		// exit button 
		ExitButt = new JButton("Exit"); 
		ExitButt.setSize(80, 30); 
		ExitButt. setLocation(1200, 0); 
		ExitButt.addActionListener(this);
		
		// insert image
		ImageIcon icon = new ImageIcon("Homepage.jpg"); 
		JLabel label = new JLabel(icon);
		label.setSize(1280,720);  
		label.setLocation(0,0); 
		
		
		
		thepanel.add(InstrucButt);
		thepanel.add(PlayButt); 
		thepanel.add(ExitButt); 
		thepanel.add(label); 
 
		
		theframe.setContentPane(thepanel);
		theframe.pack();
		
		theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		theframe.setVisible(true);
	
	}
	
	public static void main(String []args){
		new mainmenu(); 
	
		
	}
	
	



}
