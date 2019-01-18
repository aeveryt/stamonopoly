import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;

public class monopolytrial implements ActionListener{
	//Properties
	public JFrame theframe; 
	public JPanel thepanel;
	public Timer thetimer;
	public JButton chancebutton = new JButton("Chance card");
	public JButton communitybutton = new JButton("community card");
	int intRand;
	int intRand2;
	
	
	//Method
	public void actionPerformed (ActionEvent evt){
		if(evt.getSource() == thetimer){
			thepanel.repaint();
		}
		//When they click, random number will generate and pop up a number which will be what the card says
		else if(evt.getSource()== chancebutton){
			System.out.println("Got chance card");
			intRand = (int) (Math.random()*30+1);
			System.out.println(intRand);
		}else if (evt.getSource() == communitybutton){
			System.out.println("Got community card");
			intRand2 = (int) (Math.random()*30+1);
			System.out.println(intRand2);
		}
	}
	
	//Constructor
	public monopolytrial(){
		
		theframe = new JFrame("test screen");
		thepanel = new JPanel(); 
		
		//set panel
		thepanel.setPreferredSize(new Dimension(1280,720)); 
		thepanel.setLayout(null); 
		
		// set frame
		theframe.setContentPane(thepanel);
		theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theframe.pack();
		theframe.setVisible(true);
		
		//buttons
		chancebutton.setSize(150,25);
		chancebutton.setLocation(200,400);
		chancebutton.addActionListener(this);
		thepanel.add(chancebutton);
		
		communitybutton.setSize(150,25);
		communitybutton.setLocation(600,400);
		
		communitybutton.addActionListener(this);
		thepanel.add(communitybutton);
				
		//set timer
		thetimer = new Timer (1000/60,this);
		thetimer.start();
	}
	//Main Method
	public static void main(String [] args){
		new monopolytrial();
		
			//Properties
	
	boolean GoSpace = true;
	boolean Geo = false;
	boolean blnFileFail = false;
	FileReader thefile = null;
	FileReader chancefile = null;
	FileReader communityfile = null;
	BufferedReader properties = null;
	BufferedReader community = null;
	BufferedReader chance = null;
	
	
	//Split Data
	String strSplit[];
	String strProperties[][] = new String[31][10];
	String strCommunity [][] = new String[30][3];
	String strChance [][] = new String[30][3];
	
	//Methods	
		try{
			// Reading files 
			thefile = new FileReader("properties.csv");
			communityfile = new FileReader("community.csv");	
			chancefile = new FileReader("chance.csv");
		}catch(FileNotFoundException e){
			System.out.println("Unable to read File");
			blnFileFail = true;
		}
	
		properties = new BufferedReader(thefile);
		community = new BufferedReader(communityfile);
		chance = new BufferedReader(chancefile);
		
		
		String strLine = "";
		int intRow;
		int intCol;
		
		
		int intRand;
	
		
		
		
		
		//Properties File
			for (intRow = 0; intRow < 31; intRow ++){
				try{	
					strLine = properties.readLine();
				}catch(IOException e){
					System.out.println("false");
			}
				strSplit = strLine.split(",");
			for (intCol = 0; intCol < 10; intCol++){	
				strProperties[intRow][intCol] = strSplit[intCol];				
				System.out.println(strProperties[intRow][intCol]);
			}
		}
		//Community Files
		//Organized as Card Number, Statement, Money Given or Owed
		
		for (intRow = 0; intRow < 30; intRow ++){
				try{
					strLine = community.readLine();
				}catch(IOException e){
					System.out.println("false");
			}
				strSplit = strLine.split(",");
			for (intCol = 0; intCol < 3; intCol++){
				
				strCommunity[intRow][intCol] = strSplit[intCol];
				System.out.println(strCommunity[intRow][intCol]);
				
				/*if(strCommunity[intRow][0].equals("1") && intRand == 1){
					System.out.println(strCommunity[intRow][1]);
				}*/
				
			}
		}
		
		//Chance
		//Organized as Card Number, Statement, Money Given or Owed
		
		for (intRow = 0; intRow < 30; intRow ++){
				try{
					strLine = chance.readLine();
				}catch(IOException e){
					System.out.println("false");
			}
				strSplit = strLine.split(",");
			for (intCol = 0; intCol < 3; intCol++){
				strChance[intRow][intCol] = strSplit[intCol];
				System.out.println(strChance[intRow][intCol]);

			}
		}
		
		intRand = (int) (Math.random()*30+1);
			System.out.println(intRand);
		
		//Closing file
		
		try{
			thefile.close();
			communityfile.close();
			chance.close();
		
		}catch(IOException e){
			System.out.println("CANNOT CLOSE FILE");
		}
			
		
	}	
}

