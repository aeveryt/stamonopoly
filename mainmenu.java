// This is mainmenu and gameplay for game 
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 
import java.awt.Font;
import java.io.*;

public class mainmenu implements ActionListener{

	//*****PROPERTIES*****
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
	// public SuperSocketMaster ssmclient; 
	public AnimationMonopolyPanel monopolypanel; 
	public stamonopolycharacters characterspanel;
	Timer thetimer;
	
	// Sending information over network variable 
	int intPiece = 1; 
	int intRoll = 2;  
	
	
	//-Players
	int intMoney = 1500;
	String strPropertyCost = "";
	int intPropertyCost = 0;
	
	//-File Variables 
		
		//-board
		boolean GoSpace = true;
		boolean blnFileFail = false;
		FileReader thefile = null;
		FileReader chancefile = null;
		FileReader communityfile = null;
		BufferedReader properties = null;
		BufferedReader community = null;
		BufferedReader chance = null;

	//-Data Array
		String strSplit[];
		String strProperties[][] = new String[40][10];
		String strCommunity [][] = new String[30][3];
		String strChance [][] = new String[30][3];	
	
	
	//-Server 
	boolean blnServer; 
	boolean blnSent = false;
	
	//-Dice 
	int intdice1; 
	int intdice2;
	int intdiesum; 
	int intCount; 
	
	//*****METHODS*****
	public void actionPerformed(ActionEvent evt){
		
		//INSTRUCTIONS SCREEN
		//-to instructions screen
		if(evt.getSource() == InstrucButt){
			thepanelinstructions.blnpage1 = true;
			thepanelinstructions.blnpage2 = false;
			thepanelinstructions.blnpage3 = false;
			thepanelinstructions.blnpage4 = false;
			thepanelinstructions.blnpage5 = false;
			thepanelinstructions.backtomain.setVisible(true);
			thepanelinstructions.topage2.setVisible(true);
			thepanelinstructions.backtopage1.setVisible(false);
			thepanelinstructions.topage3.setVisible(false);
			theframe.setContentPane(thepanelinstructions);
			theframe.setVisible(true);	
		}
		//-page 1
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
			thepanelinstructions.blnpage5 = false;
			theframe.setContentPane(thepanelinstructions);
			theframe.setVisible(true);
		}
		//-page 2
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
			thepanelinstructions.blnpage5 = false;
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
			thepanelinstructions.backtopage3.setVisible(false);
			thepanelinstructions.topage5.setVisible(false);
			thepanelinstructions.blnpage1 = false;
			thepanelinstructions.blnpage2 = false;
			thepanelinstructions.blnpage3 = true;
			thepanelinstructions.blnpage4 = false;
			thepanelinstructions.blnpage5 = false;
			theframe.setContentPane(thepanelinstructions);
			theframe.setVisible(true);
		}
		//-page 3
		else if(evt.getSource() == thepanelinstructions.backtopage2){
			System.out.println("Trying to go to page 2");
			thepanelinstructions.backtomain.setVisible(false);
			thepanelinstructions.topage2.setVisible(false);
			thepanelinstructions.backtopage1.setVisible(true);
			thepanelinstructions.topage3.setVisible(true);
			thepanelinstructions.backtopage2.setVisible(false);
			thepanelinstructions.topage4.setVisible(false);
			thepanelinstructions.backtopage3.setVisible(false);
			thepanelinstructions.topage5.setVisible(false);
			thepanelinstructions.tomain5.setVisible(false);
			thepanelinstructions.backtopage4.setVisible(false);
			thepanelinstructions.blnpage1 = false;
			thepanelinstructions.blnpage2 = true;
			thepanelinstructions.blnpage3 = false;
			thepanelinstructions.blnpage4 = false;
			thepanelinstructions.blnpage5 = false;
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
			thepanelinstructions.tomain5.setVisible(false);
			thepanelinstructions.backtopage4.setVisible(false);
			thepanelinstructions.blnpage1 = false;
			thepanelinstructions.blnpage2 = false;
			thepanelinstructions.blnpage3 = false;
			thepanelinstructions.blnpage4 = true;
			thepanelinstructions.blnpage5 = false;
			theframe.setContentPane(thepanelinstructions);
			theframe.setVisible(true);
		}
		//-page 4
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
			thepanelinstructions.tomain5.setVisible(false);
			thepanelinstructions.backtopage4.setVisible(false);
			thepanelinstructions.blnpage1 = false;
			thepanelinstructions.blnpage2 = false;
			thepanelinstructions.blnpage3 = true;
			thepanelinstructions.blnpage4 = false;
			thepanelinstructions.blnpage5 = false;
			theframe.setContentPane(thepanelinstructions);
			theframe.setVisible(true);
		}
		else if(evt.getSource() == thepanelinstructions.topage5){
			System.out.println("Trying to go to page 5");
			thepanelinstructions.backtomain.setVisible(false);
			thepanelinstructions.topage2.setVisible(false);
			thepanelinstructions.backtopage1.setVisible(false);
			thepanelinstructions.topage3.setVisible(false);
			thepanelinstructions.backtopage2.setVisible(false);
			thepanelinstructions.topage4.setVisible(false);
			thepanelinstructions.backtopage3.setVisible(false);
			thepanelinstructions.topage5.setVisible(false);
			thepanelinstructions.tomain5.setVisible(true);
			thepanelinstructions.backtopage4.setVisible(true);
			thepanelinstructions.blnpage1 = false;
			thepanelinstructions.blnpage2 = false;
			thepanelinstructions.blnpage3 = false;
			thepanelinstructions.blnpage4 = false;
			thepanelinstructions.blnpage5 = true;
			theframe.setContentPane(thepanelinstructions);
			theframe.setVisible(true);
		}
		//-page 5
		else if(evt.getSource() == thepanelinstructions.backtopage4){
			System.out.println("Trying to go back to page 4");
			thepanelinstructions.backtomain.setVisible(false);
			thepanelinstructions.topage2.setVisible(false);
			thepanelinstructions.backtopage1.setVisible(false);
			thepanelinstructions.topage3.setVisible(false);
			thepanelinstructions.backtopage2.setVisible(false);
			thepanelinstructions.topage4.setVisible(false);
			thepanelinstructions.backtopage3.setVisible(true);
			thepanelinstructions.topage5.setVisible(true);
			thepanelinstructions.tomain5.setVisible(false);
			thepanelinstructions.backtopage4.setVisible(false);
			thepanelinstructions.blnpage1 = false;
			thepanelinstructions.blnpage2 = false;
			thepanelinstructions.blnpage3 = false;
			thepanelinstructions.blnpage4 = true;
			thepanelinstructions.blnpage5 = false;
			theframe.setContentPane(thepanelinstructions);
			theframe.setVisible(true);
		}
		else if(evt.getSource() == thepanelinstructions.tomain5){
			System.out.println("Trying to go back to main menu");
			thepanelinstructions.backtomain.setVisible(false);
			thepanelinstructions.topage2.setVisible(false);
			thepanelinstructions.backtopage1.setVisible(false);
			thepanelinstructions.topage3.setVisible(false);
			thepanelinstructions.backtopage2.setVisible(false);
			thepanelinstructions.topage4.setVisible(false);
			thepanelinstructions.backtopage3.setVisible(false);
			thepanelinstructions.topage5.setVisible(false);
			thepanelinstructions.tomain5.setVisible(false);
			thepanelinstructions.backtopage4.setVisible(false);
			thepanelinstructions.blnpage1 = false;
			thepanelinstructions.blnpage2 = false;
			thepanelinstructions.blnpage3 = false;
			thepanelinstructions.blnpage4 = false;
			thepanelinstructions.blnpage5 = false;
			theframe.setContentPane(thepanel);
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
			System.out.println(blnServer); 

			
		}else if(evt.getSource() == playpage1.existing){
			System.out.println("going to exisitng game"); 
			theframe.setContentPane(joinpage); 
			theframe.setVisible(true); 

			
		}else if(evt.getSource() == startpage.back || evt.getSource() ==joinpage.back){ 
			theframe.setContentPane(thepanel); 
			theframe.setVisible(true); 
		}else if(evt.getSource() == characterspanel.gameplay){
			theframe.setContentPane(monopolypanel);
			theframe.setVisible(true); 
			
		}
		
		//JOINING A GAME
		else if(evt.getSource() == joinpage.TF){
			joinpage.strCode = joinpage.TF.getText(); 
			System.out.println(joinpage.strCode); 
			System.out.println(startpage.strAddress); 
		
		//CHARACTER PANEL		
		}else if(evt.getSource() == characterspanel.gameplay){
			theframe.setContentPane(monopolypanel);
			theframe.setVisible(true); 
			
		}//ENTERING NAME
		else if(evt.getSource() == startpage.TFname){
			startpage.strName = startpage.TFname.getText();  	
		}
		else if(evt.getSource() == joinpage.TFname){
			joinpage.strName = joinpage.TFname.getText(); 	
		}
		
		//CHARACTERS PAGE 
		else if(evt.getSource() == startpage.gameplay){
			theframe.setContentPane(characterspanel);
			theframe.setVisible(true); 
		}else if(evt.getSource() == joinpage.gameplay){
			theframe.setContentPane(characterspanel); 
			theframe.setVisible(true); 
			//-supersocket master
			ssm = new SuperSocketMaster(joinpage.strCode,1969, this); 
			ssm.connect();
			blnServer = false;
			System.out.println(joinpage.strCode); 
		}
		//-choosing character 1
		else if(evt.getSource() == characterspanel.select1){ 
			monopolypanel.intPlayerCount = monopolypanel.intPlayerCount + 1;
			// If you are the server send this text.
			monopolypanel.strColor = "red1"; 
			monopolypanel.intPlayer = monopolypanel.intPlayerCount;
			System.out.println("you're player number:"+monopolypanel.intPlayer); 
			System.out.println("this is the turn number: "+monopolypanel.intTurn); 
			if(blnServer){ 
				ssm.sendText("select1");
				System.out.println("Server sent"); 
				characterspanel.select1.setEnabled(false); 
			}// If you are not the server use the client ssm to send text. 
			else if(blnServer == false){
				ssm.sendText("select1"); 
				System.out.println("client sent"); 
				characterspanel.select1.setEnabled(false); 
			}
		} 
		//-choosing character 2
		else if(evt.getSource() == characterspanel.select2){ 
			monopolypanel.intPlayerCount = monopolypanel.intPlayerCount + 1;
			monopolypanel.intPlayer = monopolypanel.intPlayerCount;
			monopolypanel.strColor = "blue1";  
			System.out.println(monopolypanel.intPlayer); 
			// If you are the server send this text.
			if(blnServer){ 
				ssm.sendText("select2");
				System.out.println("Server sent"); 
				characterspanel.select2.setEnabled(false); 
			// Symbolized that you sent the message. 
				blnSent = true; 
			}// If you are not the server use the client ssm to send text. 
			else if(blnServer == false){
				ssm.sendText("select2"); 
				System.out.println("client sent"); 
				characterspanel.select2.setEnabled(false); 
			// Symbolized that you sent the message
				blnSent = true; 
			}
		}
		//-choosing character 3
		else if(evt.getSource() == characterspanel.select3){ 
			monopolypanel.intPlayerCount = monopolypanel.intPlayerCount + 1;
			monopolypanel.intPlayer = monopolypanel.intPlayerCount;
			System.out.println(monopolypanel.intPlayer);
			monopolypanel.strColor = "yellow1"; 
			// If you are the server send this text.
			if(blnServer){ 
				ssm.sendText("select3");
				System.out.println("Server sent"); 
				characterspanel.select3.setEnabled(false); 
			// Symbolized that you sent the message. 
				blnSent = true; 
			}// If you are not the server use the client ssm to send text. 
			else if(blnServer == false){
				ssm.sendText("select3"); 
				System.out.println("client sent"); 
				characterspanel.select3.setEnabled(false); 
			// Symbolized that you sent the message
				blnSent = true; 
			}
		} 
		//-choosing character 4
		else if(evt.getSource() == characterspanel.select4){
			monopolypanel.strColor = "green1"; 
			monopolypanel.intPlayerCount = monopolypanel.intPlayerCount + 1; 
			monopolypanel.intPlayer = monopolypanel.intPlayerCount;
			System.out.println(monopolypanel.intPlayer);
			// If you are the server send this text.
			if(blnServer){ 
				ssm.sendText("select4");
				System.out.println("Server sent"); 
				characterspanel.select4.setEnabled(false); 
			// Symbolized that you sent the message. 
				blnSent = true; 
			}// If you are not the server use the client ssm to send text. 
			else if(blnServer == false){
				ssm.sendText("select4"); 
				System.out.println("client sent"); 
				characterspanel.select4.setEnabled(false); 
			// Symbolized that you sent the message
				blnSent = true; 
			}
		}
		
		//CHATBOX (SUPERSOCKETMASTER)
		//-talking to people over server 
		else if(evt.getSource() == monopolypanel.textfield){
			String strChat;
			System.out.println("Going to send this out over network: "+monopolypanel.textfield.getText()); 

			if(blnServer == true){
				System.out.println("I am the server"); 
				strChat = monopolypanel.textfield.getText();
				ssm.sendText(startpage.strName+" :"+strChat); 
				System.out.println(strChat);
				monopolypanel.textarea.append("\nYou: "+strChat);
				monopolypanel.textfield.setText("");
			}else if(blnServer == false){
				System.out.println("I am the client"); 
				strChat = monopolypanel.textfield.getText();
				System.out.println(strChat);
				ssm.sendText(joinpage.strName+" :"+strChat); 
				monopolypanel.textarea.append("\nYou: "+strChat);
				monopolypanel.textfield.setText("");
			}
		}		
		
		//CHARACTERS SERVER PAGE	
		else if(evt.getSource() == ssm){
			// If you are the server 
			if(blnServer == true){
				characterspanel.strData = ssm.readText(); 
				System.out.println("SERVER RECIEVED THIS: "+characterspanel.strData); 
				//Set button to false;
				if(characterspanel.strData.equals("select1")){
					characterspanel.select1.setEnabled(false); 
					monopolypanel.intPlayerCount = monopolypanel.intPlayerCount + 1;
					characterspanel.repaint();   
				}else if(characterspanel.strData.equals("select2")){
					monopolypanel.intPlayerCount = monopolypanel.intPlayerCount + 1;  
					characterspanel.select2.setEnabled(false); 
					characterspanel.repaint(); 
				}else if(characterspanel.strData.equals("select3")){
					characterspanel.select3.setEnabled(false); 
					characterspanel.repaint(); 
					monopolypanel.intPlayerCount = monopolypanel.intPlayerCount + 1;  
				}else if(characterspanel.strData.equals("select4")){
					characterspanel.select4.setEnabled(false); 
					monopolypanel.intPlayerCount = monopolypanel.intPlayerCount + 1;  
					characterspanel.repaint(); 
				}
				System.out.println("number of players: "+monopolypanel.intPlayerCount); 
				characterspanel.intPlayerN = monopolypanel.intPlayerCount; 
				// System.out.println(characterspanel.intPlayerN);
				characterspanel.repaint(); 
			} 
			
			// If you are not the server and did not send the message about shutting the button off, this code should run.
			if(blnServer == false){
				characterspanel.strData = ssm.readText(); 
				System.out.println("CLIENT received this:"+characterspanel.strData); 
				// set to false;
				if(characterspanel.strData.equals("select1")){
					characterspanel.select1.setEnabled(false);
					monopolypanel.intPlayerCount = monopolypanel.intPlayerCount + 1; 
					characterspanel.repaint(); 
				}
				else if(characterspanel.strData.equals("select2")){
					monopolypanel.intPlayerCount = monopolypanel.intPlayerCount + 1; 
					characterspanel.select2.setEnabled(false); 
					characterspanel.repaint(); 
				}else if(characterspanel.strData.equals("select3")){
					monopolypanel.intPlayerCount = monopolypanel.intPlayerCount + 1;
					characterspanel.repaint();  	
					characterspanel.select3.setEnabled(false); 
				}else if(characterspanel.strData.equals("select4")){
					monopolypanel.intPlayerCount = monopolypanel.intPlayerCount + 1; 
					characterspanel.repaint(); 
					characterspanel.select4.setEnabled(false); 
				}
				// make the player count equal 
				characterspanel.intPlayerN = monopolypanel.intPlayerCount; 
				System.out.println(characterspanel.intPlayerN); 
				characterspanel.repaint(); 
			}
			
			// Check who is going : Reset compared to the number of players?
			
			if(monopolypanel.intTurn == 1 && intPiece == 1){
				// how to know which information is being received?! 
				monopolypanel.strColor = ssm.readText(); 
				if(monopolypanel.strColor.equals("red") || monopolypanel.strColor.equals("blue") || monopolypanel.strColor.equals("yellow") || monopolypanel.strColor.equals("green")) {
					intPiece = intPiece + 1; 
				}
			}else if(monopolypanel.intTurn == 1 && intPiece == 2){
				String strPlayerX; 
				strPlayerX = ssm.readText(); 
				monopolypanel.intPlayerX1 = Integer.parseInt(strPlayerX); 
				intPiece = intPiece + 1; 
			}else if(monopolypanel.intTurn == 1 && intPiece == 3){
				String strPlayerY; 
				strPlayerY = ssm.readText(); 
				monopolypanel.intPlayerY1 = Integer.parseInt(strPlayerY); 
				intPiece = intPiece +1; 
			}else if(monopolypanel.intTurn == 1 && intPiece == 4){
				// updates whos turn it is. 
				String strTurn; 
				strTurn = ssm.readText(); 
				monopolypanel.intTurn = Integer.parseInt(strTurn); 
			}
			
			
			
			 
		
			//System.out.println(blnServer); 
			String strData; 
			strData = ssm.readText(); 
			monopolypanel.textarea.append("\n"+strData);
			
		}
		
		//ROLLING THE DICE
		else if(evt.getSource() == monopolypanel.rolldie){
			//- random die rolling
			intdice1 = (int)(Math.random() *6+1); 
			intdice2 = (int)(Math.random()*6+1); 
			intdiesum = intdice1 + intdice2; 
			
			// make it even number
			intRoll = intRoll*2; 
			//send stuff to update location of your character 
			// make if you get to a certain point the intYOUy is changed instead.
			// it is not going up 
				
			for(intCount = 1; intCount <= intdiesum; intCount++){
				//System.out.println(intCount); 
				if(monopolypanel.intYOUx <= 650 && monopolypanel.intYOUx > 50 && monopolypanel.intYOUy >= 650){
					System.out.println("going to the left"); 
					monopolypanel.intYOUx = monopolypanel.intYOUx - 60; 	
				}
				else if(monopolypanel.intYOUy > 50 && monopolypanel.intYOUy > 40 && monopolypanel.intYOUx == 50){
					System.out.println("going up");
					System.out.println(monopolypanel.intYOUx); 
					System.out.println(monopolypanel.intYOUy);  
					//monopolypanel.intYOUx = 30;
					monopolypanel.intYOUy = monopolypanel.intYOUy - 60; 
				}
				else if(monopolypanel.intYOUy < 51 && monopolypanel.intYOUx < 650){
					System.out.println("going right");
					System.out.println(monopolypanel.intYOUx); 
					System.out.println(monopolypanel.intYOUy);
					//monopolypanel.intYOUy = 30; 
					monopolypanel.intYOUx =monopolypanel.intYOUx + 60; 
				}
				else if(monopolypanel.intYOUy < 650 && monopolypanel.intYOUx == 650){
					System.out.println("going down"); 
					monopolypanel.intYOUy = monopolypanel.intYOUy + 60; 
				}
			}
			
			//- printing out dice to animation monopoly panel
			System.out.println(intdice1+" + " +intdice2); 
			String strDice1; 
			String strDice2; 
			String strDiceSum;
			strDice1 = (intdice1+""); 
			strDice2 = (intdice2+""); 
			strDiceSum = (intdiesum+"");
			monopolypanel.strDice1 = strDice1;
			monopolypanel.strDice2 = strDice2;
			monopolypanel.strDiceSum = strDiceSum;
			monopolypanel.intPropertyN = monopolypanel.intPropertyN + intdiesum; 
			
			
			
			
			//-Trying to load property names in:
			//position
			if(monopolypanel.intPropertyN>39){
				monopolypanel.intPropertyN = monopolypanel.intPropertyN - 40;
				intMoney = intMoney+200;
			}
			monopolypanel.strPropertyN = strProperties[monopolypanel.intPropertyN][0]; 	
			monopolypanel.strMoney = intMoney+"";
			//-Landing on chance or community cards
			
			String strCard;
			String strNum;
			int intCard;
			int intNum;
			
			 strCard = strProperties[monopolypanel.intPropertyN][0];
			if (strCard.equalsIgnoreCase("community")){
				intCard = (int) (Math.random()*30+1);
				strCard = strCommunity[intCard][1];
				System.out.println(strCommunity[intCard][1]);// prints out what the community card says
				
				strNum = strCommunity[intCard][0];
				System.out.println(strNum);
				
			}else if (strCard.equalsIgnoreCase("chance")){
				intCard = (int) (Math.random()*30+1);
				strCard = strChance[intCard][1];
				System.out.println(strChance[intCard][1]); //prints out what chance card says
			}
			
		}
		
		//TIMER (REPAINT)
		//updating the animation panel using a timer
		else if(evt.getSource() == thetimer){
			monopolypanel.repaint(); 
			
			// determining whose turn it is!
			if(monopolypanel.intPlayer == monopolypanel.intTurn){
				monopolypanel.rolldie.setEnabled(true); 
				// sending colour
				ssm.sendText(monopolypanel.strColor); 
				// sending x-coordinate
				ssm.sendText(monopolypanel.intYOUx+""); 
				// sending y - coordinate
				ssm.sendText(monopolypanel.intYOUy+""); 
				// sending out text to say to change intTurn variable
				if(intRoll%2 == 0){
					System.out.println("I got here"); 
					monopolypanel.intTurn = monopolypanel.intTurn +1; 
					System.out.println(monopolypanel.intTurn); 
					ssm.sendText(monopolypanel.intTurn+""); 
					intRoll = intRoll + 1; 
					// set button to false after rolling:
					monopolypanel.rolldie.setEnabled(false); 
				}
			}else{
				//System.out.println("hehe false"); 
				monopolypanel.rolldie.setEnabled(false); 
			}
			
				
			
			
			
			
			
		}
		
		//BUYING PROPERTIES
		else if(evt.getSource()== monopolypanel.buy){
			strPropertyCost = strProperties[monopolypanel.intPropertyN][10];
			intMoney = intMoney-intPropertyCost;
			monopolypanel.strMoney = intMoney+"";
		}	
	}
	
	
	//Constructor
	public mainmenu(){
	
	//Reading CSV files	
		try{
			thefile = new FileReader("properties.csv");
			communityfile = new FileReader("community.csv");	
			chancefile = new FileReader("chance.csv");
		}catch(FileNotFoundException e){
			System.out.println("Unable to read File");
			blnFileFail = true;
		}
	
	
		//Variables and Initialize
		properties = new BufferedReader(thefile);
		community = new BufferedReader(communityfile);
		chance = new BufferedReader(chancefile);
		
		String strLine = "";
		int intRow;
		int intCol;
		
		
		//Properties File
		
		/* [0] Subject
		 * [1] Teacher
		 * [2] Price
		 * [3] Rent
		 * [4] Rent with 1 houses
		 * [5] Rent with 2 houses
		 * [6] Rent with 3 houses
		 * [7] Rent with hotel 
		 * [8] Mortgage
		 * [9] Boolean (if you landed on it)
		 * */
		
			for (intRow = 0; intRow < 40; intRow ++){
				try{	
					strLine = properties.readLine();
				}catch(IOException e){
					System.out.println("false");
			}
				strSplit = strLine.split(",");
			for (intCol = 0; intCol < 10; intCol++){	
				strProperties[intRow][intCol] = strSplit[intCol];				
			}
		}
		
		//Community Files
		/* [0] - Card Number
		 * [1] - Statement 
		 * [2] - Money Given or Owed
		 * */
		for (intRow = 0; intRow < 30; intRow ++){
				try{
					strLine = community.readLine();
				}catch(IOException e){
					System.out.println("false");
			}
				strSplit = strLine.split(",");
			for (intCol = 0; intCol < 3; intCol++){
				
				strCommunity[intRow][intCol] = strSplit[intCol];
			}
		}
		
		//Chance
		//Organized as Card Number, Statement, Money given or owed.
		/* [0] - Card Number
		 * [1] - Statement 
		 * [2] - Effect of the card
		 * */
		for (intRow = 0; intRow < 30; intRow ++){
				try{
					strLine = chance.readLine();
				}catch(IOException e){
					System.out.println("false");
			}
				strSplit = strLine.split(",");
			for (intCol = 0; intCol < 3; intCol++){
				strChance[intRow][intCol] = strSplit[intCol];
			}
		}

		//Closing file
		try{
			thefile.close();
			communityfile.close();
			chance.close();
		
		}catch(IOException e){
			System.out.println("CANNOT CLOSE FILE");
		}
		
		
		
	
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
		thepanelinstructions.backtopage4.addActionListener(this);
		thepanelinstructions.tomain5.addActionListener(this);
		
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
		thepanelinstructions.add(thepanelinstructions.backtopage4);
		thepanelinstructions.add(thepanelinstructions.tomain5);
		
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
		monopolypanel.buy.addActionListener(this);
		monopolypanel.dontbuy.addActionListener(this);
		
		// timer: 
		thetimer = new Timer(1000/60, this);
		thetimer.start(); 
		
		//set frame
		theframe.setContentPane(thepanel);
		theframe.pack();
		theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		theframe.setVisible(true);
		//prevents windows from being resized
		theframe.setResizable(false); 	
	
	}
	
	
	
	public static void main(String []args){
		new mainmenu(); 
	}
}
