import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 
import java.awt.Font; 

public class mainmenu implements ActionListener{

	//Properties
	public JFrame theframe; 
	public JPanel thepanel;
	public JButton InstrucButt;
	public JButton ExitButt; 
	public JButton PlayButt;
	public monopolyinstructions thepanelinstructions;

	
	//Methods
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == InstrucButt){
			theframe.setContentPane(thepanelinstructions);
			theframe.setVisible(true);	
		}
		else if(evt.getSource() == thepanelinstructions.backtomain){
			System.out.println("Trying to go back to main menu");
			theframe.setContentPane(thepanel);
			theframe.setVisible(true);
		}
		else if(evt.getSource() == thepanelinstructions.topage2){
			System.out.println("Trying to go page 2");
			thepanelinstructions.blnpage1 = false;
			thepanelinstructions.blnpage2 = true;
			theframe.setContentPane(thepanelinstructions);
			theframe.setVisible(true);
		}
		else if(evt.getSource() == ExitButt){
			System.exit(0);
		
		}else if(evt.getSource() == PlayButt){
	
		}
	}
	
	//Constructor
	public mainmenu(){
	
		//set variables
		theframe = new JFrame("Main Menu");
		thepanel = new JPanel(); 
		thepanelinstructions = new monopolyinstructions();
		
		//set panel
		thepanel.setPreferredSize(new Dimension(1280,720)); 
		thepanel.setLayout(null); 
		
		//instruction button 
		InstrucButt = new JButton("Instructions"); 
		InstrucButt.setSize(120, 30); 
		InstrucButt.setLocation(110,250); 
		InstrucButt.addActionListener(this);
	
		//play button 
		//PlayButt.setFont(new Font("kabel.ttf", Font.PLAIN, 40));
		PlayButt = new JButton("Play"); 
		PlayButt.setSize(200, 35); 
		PlayButt.setLocation(75,200); 
		PlayButt.addActionListener(this); 
		
		//exit button 
		ExitButt = new JButton("Exit"); 
		ExitButt.setSize(80, 30); 
		ExitButt. setLocation(1200, 0); 
		ExitButt.addActionListener(this);
		
		//insert image
		ImageIcon icon = new ImageIcon("Homepage.jpg"); 
		JLabel label = new JLabel(icon);
		label.setSize(1280,720);  
		label.setLocation(0,0); 
		
		//INSTRUCTIONS SCREEN
		thepanelinstructions.setLayout(null);
		thepanelinstructions.backtomain.addActionListener(this);
		
		//add features
		thepanel.add(InstrucButt);
		thepanel.add(PlayButt); 
		thepanel.add(ExitButt);
		thepanel.add(label); 
		thepanelinstructions.add(thepanelinstructions.backtomain);
		thepanelinstructions.add(thepanelinstructions.topage2);
 
		//set frame
		theframe.setContentPane(thepanel);
		theframe.pack();
		theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		theframe.setVisible(true);
	
	}
	
	public static void main(String []args){
		new mainmenu(); 
	
		
	}
	
	



}
