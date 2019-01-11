import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;

public class stamonopoly implements ActionListener{
	//Properties
	JFrame frame = new JFrame("STA MONOPOLY");
	JPanel panel = new AnimationMonopolyPanel();
	JTextArea textarea = new JTextArea();
	JScrollPane scroll = new JScrollPane(textarea);
	JTextField textfield = new JTextField();
	Timer timer = new Timer(1000/60,this);
	
	
	//Methods
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == timer){
			panel.repaint();
		}
		
		//CHATROOM
		else if(evt.getSource() == textfield){
			System.out.println("Going to send this over network: " + textfield.getText());
			textarea.append("You: "+textfield.getText()+"\n");
			textfield.setText("");
		}
	}
	
	//Constructor
	public stamonopoly(){
		//Panel 
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(1280,720));
		timer.start();
		
		//Frame
		frame.setContentPane(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false); //prevents windows from being resized
		
		//Chatbox
		textarea.setSize(500,150);
		textarea.setLocation(750,500);
		textarea.setEnabled(false);
		panel.add(textarea);
		
		textfield.setSize(500,50);
		textfield.setLocation(750,655);
		textfield.addActionListener(this);
		panel.add(textfield);
		
		
		panel.add(scroll);
		
		
	}
	//Main Method
	public static void main (String[] args){
		new stamonopoly();
	}
	
}
