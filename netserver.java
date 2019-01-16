//NETWORK TEXT SERVER

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class netserver implements ActionListener{
	
	//Properties
	JFrame theframe = new JFrame("Network Server");
	JPanel thepanel = new JPanel();
	JTextArea thearea = new JTextArea();
	JScrollPane thescroll = new JScrollPane(thearea);
	JTextField thefield = new JTextField();
	SuperSocketMaster ssm;
	
	//Methods
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == thefield){
			System.out.println("going to send this out to network: " +thefield.getText());
			ssm.sendText("aevery: " + thefield.getText());
			thearea.append("you: " + thefield.getText() +"\n");
			thefield.setText("");
		}
		//if data comes through, it triggers this event
		else if(evt.getSource() == ssm){
			String strData;
			strData = ssm.readText();
			//add the data that was just received from the network to data
			thearea.append(strData +"\n");
		}
	}
	
	//Constructor
	public netserver(){
		//panel
		thepanel.setLayout(null);
		thepanel.setPreferredSize(new Dimension(400,500));
		
		//scroll
		thescroll.setSize(400,400);
		thescroll.setLocation(0,0);
		
		//field
		thefield.setSize(400,100);
		thefield.setLocation(0,400);
		
		//add
		thepanel.add(thescroll);
		thepanel.add(thefield);
		
		//add action listener
		thefield.addActionListener(this);
		
		//frame
		theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theframe.setContentPane(thepanel);
		theframe.pack();
		theframe.setVisible(true);
		
		ssm = new SuperSocketMaster(1337, this);
		System.out.println("My server ip is: " + ssm.getMyAddress());
		//connected
		ssm.connect();
	}
	
	//Main Method
	public static void main (String [] args){
		new netserver();
	}
}
