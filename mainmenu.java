// This is mainmenu for game 
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
	public serverpanel playpage1;
	public startgame startpage; 
	public joingame joinpage; 
	public SuperSocketMaster ssm; 
	public SuperSocketMaster ssmclient; 
	public AnimationMonopolyPanel monopolypanel; 
	public stamonopolycharacters characterspanel;
	
	boolean blnServer; 
	int intdice1; 
	int intdice2;
	int intdiesum; 



	
	//Methods
	public void actionPerformed(ActionEvent evt){
		//TO INSTRUCTIONS SCREEN
		if(evt.getSource() == InstrucButt){
			thepanelinstructions.backtopage1.setVisible(false);
			thepanelinstructions.topage3.setVisible(false);
			theframe.setContentPane(thepanelinstructions);
			theframe.setVisible(true);	
		}
		//INSTRUCTIONS SCREEN
		else if(evt.getSource() == thepanelinstructions.backtomain){
			System.out.println("Trying to go back to main menu");
			theframe.setContentPane(thepanel);
			theframe.setVisible(true);
		}
		else if(evt.getSource() == thepanelinstructions.topage2){
			System.out.println("Trying to go page 2");
			thepanelinstructions.backtomain.setVisible(false);
			thepanelinstructions.topage2.setVisible(false);
			thepanelinstructions.backtopage1.setVisible(true);
			thepanelinstructions.topage3.setVisible(true);
			thepanelinstructions.blnpage1 = false;
			thepanelinstructions.blnpage2 = true;
			thepanelinstructions.blnpage3 = false;
			thepanelinstructions.blnpage4 = false;
			theframe.setContentPane(thepanelinstructions);
			theframe.setVisible(true);

		}
		else if(evt.getSource() == thepanelinstructions.backtopage1){
			System.out.println("Trying to go back to page 1");
			thepanelinstructions.backtomain.setVisible(true);
			thepanelinstructions.topage2.setVisible(true);
			thepanelinstructions.backtopage1.setVisible(false);
			thepanelinstructions.topage3.setVisible(false);
			thepanelinstructions.blnpage1 = true;
			thepanelinstructions.blnpage2 = false;
			thepanelinstructions.blnpage3 = false;
			thepanelinstructions.blnpage4 = false;
			theframe.setContentPane(thepanelinstructions);
			theframe.setVisible(true);
		}
		else if(evt.getSource() == thepanelinstructions.topage3){
			System.out.println("Trying to go to page 3");
			thepanelinstructions.backtomain.setVisible(false);
			thepanelinstructions.topage2.setVisible(false);
			thepanelinstructions.backtopage1.setVisible(false);
			thepanelinstructions.topage3.setVisible(false);
			thepanelinstructions.backtopage2.setVisible(true);
			thepanelinstructions.topage4.setVisible(true);
			thepanelinstructions.blnpage1 = false;
			thepanelinstructions.blnpage2 = false;
			thepanelinstructions.blnpage3 = true;
			thepanelinstructions.blnpage4 = false;
			theframe.setContentPane(thepanelinstructions);
			theframe.setVisible(true);
		}
		else if(evt.getSource() == thepanelinstructions.backtopage2){
			System.out.println("Trying to go to page 2");
			thepanelinstructions.backtomain.setVisible(false);
			thepanelinstructions.topage2.setVisible(false);
			thepanelinstructions.backtopage1.setVisible(true);
			thepanelinstructions.topage3.setVisible(true);
			thepanelinstructions.backtopage2.setVisible(false);
			thepanelinstructions.topage4.setVisible(false);
			thepanelinstructions.blnpage1 = false;
			thepanelinstructions.blnpage2 = true;
			thepanelinstructions.blnpage3 = false;
			thepanelinstructions.blnpage4 = false;
			theframe.setContentPane(thepanelinstructions);
			theframe.setVisible(true);
		}
		else if(evt.getSource() == thepanelinstructions.topage4){
			System.out.println("Trying to go to page 4");
			thepanelinstructions.backtomain.setVisible(false);
			thepanelinstructions.topage2.setVisible(false);
			thepanelinstructions.backtopage1.setVisible(false);
			thepanelinstructions.topage3.setVisible(false);
			thepanelinstructions.backtopage2.setVisible(false);
			thepanelinstructions.topage4.setVisible(false);
			thepanelinstructions.backtopage3.setVisible(true);
			thepanelinstructions.topage5.setVisible(true);
			thepanelinstructions.blnpage1 = false;
			thepanelinstructions.blnpage2 = false;
			thepanelinstructions.blnpage3 = false;
			thepanelinstructions.blnpage4 = true;
			theframe.setContentPane(thepanelinstructions);
			theframe.setVisible(true);
		}
		else if(evt.getSource() == thepanelinstructions.backtopage3){
			System.out.println("Trying to go back to page 3");
			thepanelinstructions.backtomain.setVisible(false);
			thepanelinstructions.topage2.setVisible(false);
			thepanelinstructions.backtopage1.setVisible(false);
			thepanelinstructions.topage3.setVisible(false);
			thepanelinstructions.backtopage2.setVisible(true);
			thepanelinstructions.topage4.setVisible(true);
			thepanelinstructions.backtopage3.setVisible(false);
			thepanelinstructions.topage5.setVisible(false);
			thepanelinstructions.blnpage1 = false;
			thepanelinstructions.blnpage2 = false;
			thepanelinstructions.blnpage3 = true;
			thepanelinstructions.blnpage4 = false;
			theframe.setContentPane(thepanelinstructions);
			theframe.setVisible(true);
		}
	//TO LEAVE GAME
		else if(evt.getSource() == ExitButt){
			System.exit(0);
	// PLAY SCREEN
		}else if(evt.getSource() == PlayButt){
			theframe.setContentPane(playpage1); 
			theframe.setVisible(true);
		}else if(evt.getSource() == playpage1.back){
			theframe.setContentPane(thepanel); 
			theframe.setVisible(true); 
		}else if(evt.getSource() == playpage1.start){
			System.out.println("started"); 
			theframe.setContentPane(startpage); 
			theframe.setVisible(true); 
			// opens super socket master if decides to start game	
			ssm = new SuperSocketMaster(1969, this);
			startpage.strAddress = ssm.getMyAddress(); 
			ssm.connect();
			blnServer = true; 
			
		}else if(evt.getSource() == playpage1.existing){
			System.out.println("going to exisitng game"); 
			theframe.setContentPane(joinpage); 
			blnServer = false; 
			theframe.setVisible(true); 
			
			ssmclient = new SuperSocketMaster(joinpage.strCode,1969, this); 
			ssmclient.connect();
			System.out.println(joinpage.strCode); 
		
			
			// System.out.println(blnServer); 
			
		}else if(evt.getSource() == startpage.back || evt.getSource() ==joinpage.back){ 
			theframe.setContentPane(thepanel); 
			theframe.setVisible(true); 
		}else if(evt.getSource() == characterspanel.gameplay){
			theframe.setContentPane(monopolypanel);
			theframe.setVisible(true); 
			
		}
		// Joining a game:
		else if(evt.getSource() == joinpage.TF){
			joinpage.strCode = joinpage.TF.getText(); 
			System.out.println(joinpage.strCode); 
			System.out.println(startpage.strAddress); 
			
			
		}else if(evt.getSource() == characterspanel.gameplay){
			theframe.setContentPane(monopolypanel);
			theframe.setVisible(true); 
			

			
		}// Entering name
		else if(evt.getSource() == startpage.TFname){
			startpage.strName = startpage.TFname.getText();  
			
		}else if(evt.getSource() == joinpage.TFname){
			joinpage.strName = joinpage.TFname.getText(); 
			
		}
		
		// chosing characters page: 
		else if(evt.getSource() == startpage.gameplay || evt.getSource() == joinpage.gameplay){
			theframe.setContentPane(characterspanel);
			theframe.setVisible(true); 
		}else if(evt.getSource() == characterspanel.select1){
			if(blnServer == true){
				ssm.sendText("select1");
			}else if(blnServer == false){
				ssmclient.sendText("select1"); 
			}

			if(blnServer == true){
				characterspanel.strData = ssm.readText(); 
			}else if(blnServer == false){
				characterspanel.strData = ssmclient.readText(); 
			}
			
		}
		
		// talking to people over server (SUPERSOCKETMASTER)
		else if(evt.getSource() == monopolypanel.textfield){
			System.out.println("Going to send this out over network: "+monopolypanel.textfield.getText()); 

			if(blnServer == true){
				System.out.println("I am the server"); 
				ssm.sendText(startpage.strName+" :"+monopolypanel.textfield.getText()); 
				monopolypanel.textarea.append("\nYou: "+monopolypanel.textfield.getText());
				monopolypanel.textfield.setText("");
			}else if(blnServer == false){
				System.out.println("I am the client"); 
				ssmclient.sendText(joinpage.strName+" :"+monopolypanel.textfield.getText()); 
				monopolypanel.textarea.append("\nYou: "+monopolypanel.textfield.getText());
				monopolypanel.textfield.setText("");
			}			
		}else if(evt.getSource() == ssm){
			String strData; 
			strData = ssm.readText(); 
			monopolypanel.textarea.append("\n"+strData); 
		}else if(evt.getSource() == ssmclient){
			String strData; 
			strData = ssmclient.readText(); 
			monopolypanel.textarea.append("\n"+strData); 
		}
		
		// Rolling the dice: 
		else if(evt.getSource() == monopolypanel.rolldie){
			intdice1 = (int)(Math.random() *6+1); 
			intdice2 = (int)(Math.random()*6+1); 
			intdiesum = intdice1 + intdice2; 
			System.out.println(intdice1+" + " +intdice2); 
		}
		
		
	}
	
	//Constructor
	public mainmenu(){
	
		//set variables
		theframe = new JFrame("Main Menu");
		thepanel = new JPanel(); 
		thepanelinstructions = new monopolyinstructions();
		playpage1 = new serverpanel();
		startpage = new startgame(); 
		joinpage = new joingame(); 
		monopolypanel = new AnimationMonopolyPanel();
		characterspanel = new stamonopolycharacters();  
	
		
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
		
		//INSTRUCTIONS SCREEN (ACTIONLISTENER)
		thepanelinstructions.setLayout(null);
		thepanelinstructions.backtomain.addActionListener(this);
		thepanelinstructions.topage2.addActionListener(this);
		thepanelinstructions.backtopage1.addActionListener(this);
		thepanelinstructions.topage3.addActionListener(this);
		thepanelinstructions.backtopage2.addActionListener(this);
		thepanelinstructions.topage4.addActionListener(this);
		thepanelinstructions.backtopage3.addActionListener(this);
		thepanelinstructions.topage5.addActionListener(this);
		
		// PLAY PAGE 1
		playpage1.setLayout(null); 
		playpage1.back.addActionListener(this); 
		playpage1.start.addActionListener(this);
		playpage1.existing.addActionListener(this);  
		
		
		// Start game: 
		startpage.setLayout(null); 
		startpage.back.addActionListener(this); 
		startpage.gameplay.addActionListener(this); 
		startpage.TFname.addActionListener(this); 
		
		// Join game: 
		joinpage.setLayout(null); 
		joinpage.back.addActionListener(this); 
		joinpage.TF.addActionListener(this); 
		joinpage.gameplay.addActionListener(this); 
		joinpage.TFname.addActionListener(this); 
		
		// characters panel:
		characterspanel.setLayout(null); 
		characterspanel.select1.addActionListener(this); 
		characterspanel.select2.addActionListener(this); 
		characterspanel.select3.addActionListener(this); 
		characterspanel.select4.addActionListener(this); 
		characterspanel.gameplay.addActionListener(this); 
		
		// PLAY PAGE: 
		monopolypanel.setLayout(null); 
		monopolypanel.textfield.addActionListener(this);
		
		
		
		//add features
		thepanel.add(InstrucButt);
		thepanel.add(PlayButt); 
		thepanel.add(ExitButt);
		thepanel.add(label); 
		
		// Add features: Instructions Screen
		thepanelinstructions.add(thepanelinstructions.backtomain);
		thepanelinstructions.add(thepanelinstructions.topage2);
		thepanelinstructions.add(thepanelinstructions.backtopage1);
		thepanelinstructions.add(thepanelinstructions.topage3);
		thepanelinstructions.add(thepanelinstructions.backtopage2);
		thepanelinstructions.add(thepanelinstructions.topage4);
		thepanelinstructions.add(thepanelinstructions.backtopage3);
		thepanelinstructions.add(thepanelinstructions.topage5);
		
		// Add features: Play page 1 :
		playpage1.add(playpage1.back); 
		playpage1.add(playpage1.start); 
		playpage1.add(playpage1.existing); 
		
		// Add features: Join page and start page
		joinpage.add(joinpage.back); 
		startpage.add(startpage.back); 
		joinpage.add(joinpage.TF); 
		startpage.add(startpage.gameplay); 
		joinpage.add(joinpage.gameplay); 
		startpage.add(startpage.TFname); 
		joinpage.add(joinpage.TFname); 
		
		//add features: to character page
		characterspanel.add(characterspanel.select1); 
		characterspanel.add(characterspanel.select2); 
		characterspanel.add(characterspanel.select3); 
		characterspanel.add(characterspanel.select4); 
		characterspanel.add(characterspanel.gameplay); 
		
		

		// Add features: to play page (ACTUAL GAMEPLAY)
		monopolypanel.add(monopolypanel.scroll); 
		monopolypanel.add(monopolypanel.textfield);
		monopolypanel.add(monopolypanel.buy);
		monopolypanel.add(monopolypanel.dontbuy);
		monopolypanel.add(monopolypanel.rolldie);
		monopolypanel.add(monopolypanel.next);
		monopolypanel.add(monopolypanel.back);
		
		// Game play - ACTION LISTENER
		monopolypanel.rolldie.addActionListener(this);
		monopolypanel.next.addActionListener(this);
		monopolypanel.back.addActionListener(this);
		
		//set frame
		theframe.setContentPane(thepanel);
		theframe.pack();
		theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		theframe.setVisible(true);
		theframe.setResizable(false); //prevents windows from being resized
	
	}
	
	
	
	public static void main(String []args){
		new mainmenu(); 
	
		
	}
	
	



}
