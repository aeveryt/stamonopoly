import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 
import java.awt.Font;
import java.io.*;

/** <h1> Main Menu and Game Play of St.Augustine Monopoly </h1>
 * This is the mainmenu and gameplay for game <br>
 * Number of Players: 1-4
 * @author Aevery Tam  Sheridan Fong  Vivian Phung
 * @since 2019-01-06*/
public class mainmenu implements ActionListener{

	//*****PROPERTIES*****
	/**Frame of the program from JFrame of javaswing<br>
	 * Resolution: 1280,720*/
	public JFrame theframe; 
	
	/**Panel of the program from JPanel of javaswing*/
	public JPanel thepanel;
	
	/**Instruction button in the main menu from JButton of javaswing <br>
	 * This button goes to the instruction page - learn how to play the game*/
	public JButton InstrucButt;
	
	/**Exit button in the main menu from JButton of javaswing <br>
	 * This button exits and closes the console/program*/
	public JButton ExitButt; 
	
	/**Play button in the main menu from JButton of java swing <br>
	 * This button leads to the actual gameplay of St.A monopoly*/
	public JButton PlayButt;
	
	/**Instructions panel from JPanel of javaswing <br>
	 * This entails all of the instruction content of the game*/
	public monopolyinstructions thepanelinstructions;
	
	/**Panel from JPanel of javaswing <br>
	 * This page allows you to decide whether or not you want to be a server or client */
	public serverpanel playpage1;
	
	/**Panel from JPanel of java swing <br>
	 * This page is for the player (server) who decides to start the game <br>
	 * Player must wait for other players (clients) to input IP address and then start the game.*/
	public startgame startpage;
	
	/**Panel from JPanel of java swing <br>
	 * This page is for the player (client) who decides to join another player's game <br>
	 * Player must input player's (server) IP address to join the game*/ 
	public joingame joinpage; 
	
	/** Mr.Cadawas' SuperSocketMaster that entails code for server and client*/
	public SuperSocketMaster ssm; 
	
	/**Animation Panel for all of the graphics and designs for the whole gameplay */
	public AnimationMonopolyPanel monopolypanel; 
	
	/**Panel from JPanel of java swing that allows players to choose their player colour. <br>
	 * Includes buttons - 1 (red), 2 (blue) , 3 (yellow) , 4 (green) */
	public stamonopolycharacters characterspanel;
	
	/**Timer that plays throughout the whole gameplay*/
	public Timer thetimer;
	
	//-Sending information over network variable 
	int intPiece = 1; 
	int intRoll = 2;  
	boolean blnPlay = false; 
	boolean blnDice = false; 
	boolean blnProperties = false;  // related to sending properties over the server.
	int intPlayerOwned; //checks which players owns which property
	
	//-Players
	int intMoney = 1500; // the amount of money that each player starts off with
	String strPropertyCost = "";
	int intPropertyCost = 0; // the cost of the property
	boolean blnOffice = false; //checks if the player is in jail
	int intOfficeTime = 0; //checks how many turns are left over when in jail (principal's office)
	int intPropertyPanel = 0 ;//property panel page
	int intPropertyDisplay;
	
	//-Properties (that you own)
	int intNumofProperties = 0; //number of properties owned
	//--property colours
	int intBrown = 0;
	int intRailRoad = 0;
	int intLightBlue = 0;
	int intPurple = 0;
	int intUtility = 0;
	int intOrange = 0;
	int intRed = 0;
	int intYellow = 0;
	int intGreen = 0;
	int intDarkBlue = 0;
	//-- house properties
	int intHouse = 0;
	
	//-File Variables 
	//--board
	boolean GoSpace = true;
	boolean blnFileFail = false;
	FileReader thefile = null;
	FileReader chancefile = null;
	FileReader communityfile = null;
	BufferedReader properties = null;
	BufferedReader community = null;
	BufferedReader chance = null;
	//--Data Array
	String strSplit[];
	/** The properties in the St.Augustine Monopoly Board <br>
	 * Array: [40][13]<br>
	 * [0] Subject<br>
	 * [1] Teacher<br>
	 * [2] Price<br>
	 * [3] Rent<br>
	 * [4] Rent with 1 houses <br>
	 * [5] Rent with 2 houses <br>
	 * [6] Rent with 3 houses<br>
	 * [7] Rent with hotel <br>
	 * [8] Mortgage<br>
	 * [9] Boolean - if the property is owned (if you landed on it)<br>
	 * [10] Colour of property<br>
	 * [11] Number of Houses on the property (4 houses = hotel)<br>
	 * [12] intPlayer that owns the property
	 * */
	public String strProperties[][] = new String[40][13];
	/**This array allows you to see the properties you own <br>
	 * The array length is dependent on how many properties you own*/
	int intPropertiesOwned[]; //properties that you own
	/**The Community Chest Cards in the St. Augustine Monopoly Board <br>
	 * These are randomly generated <br>
	 * Array: [30][3]<br>
	 * [0] - Card Number<br>
	 * [1] - Statement <br>
	 * [2] - Money Given or Owed 
	 * */
	public String strCommunity [][] = new String[30][3];
	/**The Chance cards in the St.Augustine Monopoly Board <br>
	 * [0] - Card Number<br>
	 * [1] - Statement <br>
	 * [2] - Effect of the card (location/money check)<br>
	 * [3] - Money or Location effect (cost/location)
	 * */
	public String strChance [][] = new String[30][4];	
	String strLine = "";
	int intRow;
	int intCol;
	int intChance;
	int intCommunity;
	
	//-Server 
	boolean blnServer; 
	boolean blnSent = false;
	
	//-Dice 
	int intdice1; 
	int intdice2;
	int intdiesum; 
	int intCount; 
	
	// Properties purchased: 
	boolean blnPropertyBought1 = false;
	boolean blnPropertyBought3 = false; 
	boolean blnPropertyBought5 = false; 
	boolean blnPropertyBought6 = false;
	boolean blnPropertyBought8 = false; 
	boolean blnPropertyBought9 = false; 
	boolean blnPropertyBought11 = false; 
	boolean blnPropertyBought12 = false; 
	boolean blnPropertyBought13 = false; 
	boolean blnPropertyBought14 = false;
	boolean blnPropertyBought15= false; 
	boolean blnPropertyBought16 = false; 
	boolean blnPropertyBought17 = false;
	boolean blnPropertyBought18 = false; 
	boolean blnPropertyBought19 = false; 
	boolean blnPropertyBought21 = false;
	boolean blnPropertyBought23 = false;
	boolean blnPropertyBought24 = false; 
	boolean blnPropertyBought25 = false;
	boolean blnPropertyBought26 = false;
	boolean blnPropertyBought27 = false; 
	boolean blnPropertyBought28 = false; 
	boolean blnPropertyBought29 = false; 
	boolean blnPropertyBought31 = false;
	boolean blnPropertyBought32 = false; 
	boolean blnPropertyBought34 = false; 
	boolean blnPropertyBought35 = false;
	boolean blnPropertyBought37 = false; 
	boolean blnPropertyBought39 = false; 
	boolean blnPropertyBought = false;
	
	// houses purchased:
	boolean blnHouse1 = false;
	boolean blnHouse3 = false;
	boolean blnHouse5 = false;
	boolean blnHouse6 = false;
	boolean blnHouse8 = false;
	boolean blnHouse9 = false; 
	boolean blnHouse11 = false;
	boolean blnHouse12 = false;
	boolean blnHouse13 = false;
	boolean blnHouse14 = false;
	boolean blnHouse15 = false;
	boolean blnHouse16 = false;
	boolean blnHouse17 = false;
	boolean blnHouse18 = false;
	boolean blnHouse19 = false;
	boolean blnHouse21 = false;
	boolean blnHouse23 = false;
	boolean blnHouse24 = false;
	boolean blnHouse25 = false;
	boolean blnHouse26 = false;
	boolean blnHouse27 = false;
	boolean blnHouse28 = false;
	boolean blnHouse29 = false;
	boolean blnHouse31 = false;
	boolean blnHouse32 = false;
	boolean blnHouse34= false;
	boolean blnHouse35 = false;
	boolean blnHouse37 = false;
	boolean blnHouse39 = false;
	
	
	
	//*****METHODS*****
	/**Override actionPerformed method from ActionListener that was implemented <br>
	 * To listen to methods from java swing: .addActionListener and  evt.getSource() <br>
	 * Listened to: instruction buttons, rolling die*/
	public void actionPerformed(ActionEvent evt){
		
		//INSTRUCTIONS SCREEN
		//-going to instructions screen
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
			theframe.setContentPane(thepanel);
			theframe.setVisible(true);
		}
		else if(evt.getSource() == thepanelinstructions.topage2){
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
		}
			
		//PLAY SCREEN
		else if(evt.getSource() == PlayButt){
			theframe.setContentPane(playpage1); 
			theframe.setVisible(true);
		}
		else if(evt.getSource() == playpage1.back){
			theframe.setContentPane(thepanel); 
			theframe.setVisible(true); 
		}
		else if(evt.getSource() == playpage1.start){
			System.out.println("started"); 
			theframe.setContentPane(startpage); 
			theframe.setVisible(true); 
			// opens super socket master if decides to start game	
			ssm = new SuperSocketMaster(1969, this);
			startpage.strAddress = ssm.getMyAddress(); 
			ssm.connect();
			blnServer = true;
			System.out.println(blnServer); 	
		}		
		else if(evt.getSource() == playpage1.existing){
			System.out.println("going to exisitng game"); 
			theframe.setContentPane(joinpage); 
			theframe.setVisible(true); 	
		}
		
		else if(evt.getSource() == startpage.back || evt.getSource() ==joinpage.back){ 
			theframe.setContentPane(thepanel); 
			theframe.setVisible(true); 
		}
		
		else if(evt.getSource() == characterspanel.gameplay){
			blnPlay = true; 
			theframe.setContentPane(monopolypanel);
			theframe.setVisible(true); 	
		}
		
		//JOINING A GAME
		else if(evt.getSource() == joinpage.TF){
			joinpage.strCode = joinpage.TF.getText(); 
			System.out.println(joinpage.strCode); 
			System.out.println(startpage.strAddress); 
		}
		
		//CHARACTER PANEL		
		else if(evt.getSource() == characterspanel.gameplay){
			theframe.setContentPane(monopolypanel);
			theframe.setVisible(true); 	
		}
		
		//ENTERING NAME (starting game)
		else if(evt.getSource() == startpage.TFname){
			startpage.strName = startpage.TFname.getText();  
			monopolypanel.strPlayerTurn = startpage.strName;		
		}
		//ENTERING NAME (joining game)
		else if(evt.getSource() == joinpage.TFname){
			joinpage.strName = joinpage.TFname.getText();
			monopolypanel.strPlayerTurn = joinpage.strName;	
		}
		
		//CHARACTERS PAGE
		//-starting game
		else if(evt.getSource() == startpage.gameplay){
			theframe.setContentPane(characterspanel);
			theframe.setVisible(true); 
		}
		//CHARACTERS PAGE
		//-joining game
		else if(evt.getSource() == joinpage.gameplay){
			theframe.setContentPane(characterspanel); 
			theframe.setVisible(true); 
			//-supersocket master
			ssm = new SuperSocketMaster(joinpage.strCode,1969, this); 
			ssm.connect();
			blnServer = false;
			System.out.println(joinpage.strCode); 
		}
		//--choosing character 1
		else if(evt.getSource() == characterspanel.select1){ 
			characterspanel.gameplay.setEnabled(true); 
			monopolypanel.intPlayerCount = monopolypanel.intPlayerCount + 1;
			// If you are the server send this text.
			monopolypanel.strColor = "red1"; 
			monopolypanel.intPlayer = monopolypanel.intPlayerCount;
			System.out.println("you're player number:"+monopolypanel.intPlayer); 
			System.out.println("this is the turn number: "+monopolypanel.intTurn); 
			System.out.println("this is your colour: "+monopolypanel.strColor); 
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
		//--choosing character 2
		else if(evt.getSource() == characterspanel.select2){ 
			characterspanel.gameplay.setEnabled(true); 
			monopolypanel.intPlayerCount = monopolypanel.intPlayerCount + 1;
			monopolypanel.intPlayer = monopolypanel.intPlayerCount;
			monopolypanel.strColor = "blue1";  
			System.out.println("you're player number:"+monopolypanel.intPlayer); 
			System.out.println("this is the turn number: "+monopolypanel.intTurn);  
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
		//--choosing character 3
		else if(evt.getSource() == characterspanel.select3){ 
			characterspanel.gameplay.setEnabled(true); 
			monopolypanel.intPlayerCount = monopolypanel.intPlayerCount + 1;
			monopolypanel.intPlayer = monopolypanel.intPlayerCount;
			System.out.println(monopolypanel.intPlayer);
			System.out.println("you're player number:"+monopolypanel.intPlayer); 
			System.out.println("this is the turn number: "+monopolypanel.intTurn); 
			monopolypanel.strColor = "yellow1"; 
			System.out.println(monopolypanel.strColor); 
			// If you are the server send this text.
			if(blnServer){ 
				ssm.sendText("select3");
				System.out.println("Server sent"); 
				characterspanel.select3.setEnabled(false); 
			// Symbolized that you sent the message. 
				blnSent = true; 
			}
			// If you are not the server use the client ssm to send text. 
			else if(blnServer == false){
				ssm.sendText("select3"); 
				System.out.println("client sent"); 
				characterspanel.select3.setEnabled(false); 
			// Symbolized that you sent the message
				blnSent = true; 
			}
		} 
		//--choosing character 4
		else if(evt.getSource() == characterspanel.select4){
			monopolypanel.strColor = "green1"; 
			characterspanel.gameplay.setEnabled(true); 
			monopolypanel.intPlayerCount = monopolypanel.intPlayerCount + 1; 
			monopolypanel.intPlayer = monopolypanel.intPlayerCount;
			System.out.println("you're player number:"+monopolypanel.intPlayer); 
			System.out.println("this is the turn number: "+monopolypanel.intTurn); 
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
			}
			else if(blnServer == false){
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
		String strData; 
		boolean blnMovement = true; 
		strData = ssm.readText(); 
		
		// ----- see if someone bought a house:
		if(strData.equals("1Geography") || strData.equals("3History") || strData.equals("5Guidance 1") || strData.equals("6Visual Arts") || strData.equals("8Music") || strData.equals("9Drama") || strData.equals("11Accounting") || strData.equals("12Student Council") || strData.equals("13Marketing") || strData.equals("14Business Leadership")|| strData.equals("15Guidance 2") || strData.equals("16Religion") || strData.equals("18World Religions")|| strData.equals("19Philosphy")|| strData.equals("21Indigenous Studies")|| strData.equals("23Writer's Craft") || strData.equals("24English")|| strData.equals("25Guidance 3")|| strData.equals("26Advanced Functions") || strData.equals("27Data Management") || strData.equals("28Athletic Council") || strData.equals("29Calculas and Vectors") || strData.equals("31Biology") || strData.equals("32Chemistry") || strData.equals("34Physics") || strData.equals("35Guidance 4") || strData.equals("37Tech Design") || strData.equals("39Computer Science") || blnHouse1 == true || blnHouse3 == true || blnHouse5 == true|| blnHouse6 == true || blnHouse8 == true || blnHouse9 == true || blnHouse11 == true || blnHouse12 == true || blnHouse13 == true || blnHouse14 == true || blnHouse15 == true || blnHouse16 == true || blnHouse17 == true || blnHouse18 == true|| blnHouse19 == true || blnHouse21 == true|| blnHouse23 == true || blnHouse24 == true || blnHouse25 == true || blnHouse26 == true || blnHouse27 == true|| blnHouse28 == true || blnHouse29 == true || blnHouse31 == true|| blnHouse32 == true|| blnHouse34 == true || blnHouse35 == true || blnHouse37 == true || blnHouse39 == true){
			blnMovement = false; 
			
			if(strData.equals("1Geography") || blnHouse1 == true){
				blnHouse1 = true; 
				
				if(blnHouse1 == true){
					strProperties[1][11] = strData; 
					blnHouse1 = false; 
				}
			}else if(strData.equals("3History") || blnHouse3 == true){
				blnHouse3 = true; 
				
				if(blnHouse3 == true){
					strProperties[3][11] = strData;
					blnHouse3 = false; 
				}
			
			
			}else if(strData.equals("5Guidance 1") || blnHouse5 == true){
				blnHouse5 = true; 
				
				if(blnHouse5 == true){
					strProperties[5][11] = strData;
					blnHouse5 = false; 
				}
				
				
				
			}else if(strData.equals("6Visual Arts") || blnHouse6 == true){
				blnHouse6 = true;  
				
				if(blnHouse6 == true){
					strProperties[6][11] = strData;
					blnHouse6 = false; 
				}
				
			}else if(strData.equals("8Music") || blnHouse8 == true){
				blnHouse8 = true; 
				
				if(blnHouse8 == true){
					strProperties[8][11] = strData;
					blnHouse8 = false; 
				}
			}else if(strData.equals("9Drama") || blnHouse9 == true){
				blnHouse9 = true; 
				
				if(blnHouse9 == true){
					strProperties[9][11] = strData;
					blnHouse9 = false; 
				}
				
			}else if(strData.equals("11Accounting") || blnHouse11 == true){
				blnHouse11 = true; 
				
				if(blnHouse11 == true){
					strProperties[11][11] = strData;
					blnHouse11 = false; 
				}
			
			
			
				
			}else if(strData.equals("12Student Council") || blnHouse12 == true){
			blnHouse12= true; 
				
				if(blnHouse12 == true){
					strProperties[12][11] = strData;
					blnHouse12 = false; 
				}
			
			
				
			}else if(strData.equals("13Marketing") || blnHouse13 == true){
				blnHouse13 = true; 
				
				if(blnHouse13 == true){
					strProperties[13][11] = strData;
					blnHouse13 = false; 
				}
				
			}else if(strData.equals("14Business Leadership") || blnHouse14 == true){
				blnHouse14 = true; 
				
				if(blnHouse14 == true){
					strProperties[14][11] = strData;
					blnHouse14 = false; 
				}
			}else if(strData.equals("15Guidance 2") || blnHouse15 == true){
			blnHouse15 = true; 
				
				if(blnHouse15 == true){
					strProperties[15][11] = strData;
					blnHouse15 = false; 
				}
				
			}else if(strData.equals("16Religion") || blnHouse16 == true){
				
				blnHouse16= true; 
				
				if(blnHouse16 == true){
					strProperties[16][11] = strData;
					blnHouse16 = false; 
				}
				
				
			}else if(strData.equals("18World Religions") || blnHouse18 == true){
			blnHouse18 = true; 
				
				if(blnHouse18 == true){
					strProperties[18][11] = strData;
					blnHouse18 = false; 
				}
			
				
			}else if(strData.equals("19Philosphy") || blnHouse19 == true){
				blnHouse19 = true; 
				
				if(blnHouse19 == true){
					strProperties[19][11] = strData;
					blnHouse19 = false; 
				}
			}else if(strData.equals("21Indigenous Studies")|| blnHouse21 == true){
				blnHouse21 = true; 
				
				if(blnHouse21 == true){
					strProperties[21][11] = strData;
					blnHouse21 = false; 
				}
			}else if(strData.equals("23Writer's Craft") || blnHouse23 == true){
			blnHouse23 = true; 
				
				if(blnHouse23 == true){
					strProperties[23][11] = strData;
					blnHouse23 = false; 
				}
			
				
			}else if(strData.equals("24English") || blnHouse24 == true){
				blnHouse24 = true; 
				
				if(blnHouse24 == true){
					strProperties[24][11] = strData;
					blnHouse24 = false; 
				}
				
			}else if(strData.equals("25Guidance 3") || blnHouse25 == true){
				blnHouse25 = true; 
				
				if(blnHouse25 == true){
					strProperties[25][11] = strData;
					blnHouse25 = false; 
				}
			}else if(strData.equals("26Advanced Functions") || blnHouse26 == true){
				blnHouse26 = true; 
				
				if(blnHouse26 == true){
					strProperties[26][11] = strData;
					blnHouse26 = false; 
				}
			}else if(strData.equals("27Data Management") || blnHouse27 == true){
				blnHouse27 = true; 
				
				if(blnHouse27 == true){
					strProperties[27][11] = strData;
					blnHouse27 = false; 
				}
			}else if(strData.equals("28Athletic Council") || blnHouse28 == true){
			blnHouse28 = true; 
				
				if(blnHouse28 == true){
					strProperties[28][11] = strData;
					blnHouse28 = false; 
				}
			
				
			}else if(strData.equals("29Calculas and Vectors") || blnHouse29 == true){
				blnHouse29 = true; 
				
				if(blnHouse29 == true){
					strProperties[29][11] = strData;
					blnHouse29 = false; 
				}
			}else if(strData.equals("31Biology") || blnHouse31 == true){
			blnHouse31 = true; 
				
				if(blnHouse31 == true){
					strProperties[31][11] = strData;
					blnHouse31 = false; 
				}
				
			}else if(strData.equals("32Chemistry") || blnHouse32 == true){
				blnHouse32 = true; 
				
				if(blnHouse32 == true){
					strProperties[32][11] = strData;
					blnHouse32 = false; 
				}
			}else if(strData.equals("34Physics") || blnHouse34 == true){
				blnHouse34 = true; 
				
				if(blnHouse34 == true){
					strProperties[34][11] = strData;
					blnHouse34 = false; 
				}
			}else if(strData.equals("35Guidance 4") || blnHouse35 == true){
				blnHouse35 = true; 
				
				if(blnHouse35 == true){
					strProperties[35][11] = strData;
					blnHouse35 = false; 
				}
			}else if(strData.equals("37Tech Design") || blnHouse37 == true){
				blnHouse37 = true; 
				
				if(blnHouse37 == true){
					strProperties[37][11] = strData;
					blnHouse37 = false; 
				}
			}else if(strData.equals("39Computer Science39") || blnHouse39 == true){
				blnHouse39 = true; 
				
				if(blnHouse39 == true){
					strProperties[39][11] = strData;
					blnHouse39 = false; 
				}
			}
			
			
			
			monopolypanel.textarea.setText("");
			
			
		}
		
		
		
	
		
		
		//----- see if someone landed on your property so you can collect rent!
		if(strData.equals("Geography1") || strData.equals("History3") || strData.equals("Guidance 15") || strData.equals("Visual Arts6") || strData.equals("Music8") || strData.equals("Drama9") || strData.equals("Accounting11") || strData.equals("Student Council12") || strData.equals("Marketing13") || strData.equals("Business Leadership14")|| strData.equals("Guidance 215") || strData.equals("Religion16") || strData.equals("World Religions18")|| strData.equals("Philosphy19")|| strData.equals("Indigenous Studies21")|| strData.equals("Writer's Craft23") || strData.equals("English24")|| strData.equals("Guidance 325")|| strData.equals("Advanced Functions26") || strData.equals("Data Management27") || strData.equals("Athletic Council28") || strData.equals("Calculas and Vectors29") || strData.equals("Biology31") || strData.equals("Chemistry32") || strData.equals("Physics34") || strData.equals("Guidance 435") || strData.equals("Tech Design37") || strData.equals("Computer Science39")){
			blnMovement = false; 
			//add collected rent to your total amount of money, if the person who owns it equals to your player number you collect rent
			if(strData.equals("Geography1")){
				if(strProperties[1][12].equals(monopolypanel.intPlayer+"")){
					int intCost; 
					String strRent; 
					
					// check to see if houses are owned:
					if(strProperties[1][11].equals("0")){
						strRent = strProperties[1][3];
						intCost = Integer.parseInt(strRent);
						intMoney = intMoney + intCost;  
					}else if(strProperties[1][11].equals("1")){
						strRent = strProperties[1][4];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[1][11].equals("2")){
						strRent = strProperties[1][5];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[1][11].equals("3")){
						strRent = strProperties[1][6];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[1][11].equals("4")){
						strRent = strProperties[1][7]; 
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}
				}
			}else if(strData.equals("History3")){
			if(strProperties[3][12].equals(monopolypanel.intPlayer+"")){
					int intCost; 
					String strRent; 
					
					// check to see if houses are owned:
					if(strProperties[3][11].equals("0")){
						strRent = strProperties[3][3];
						intCost = Integer.parseInt(strRent);
						intMoney = intMoney + intCost;  
					}else if(strProperties[3][11].equals("1")){
						strRent = strProperties[3][4];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[3][11].equals("2")){
						strRent = strProperties[3][5];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[3][11].equals("3")){
						strRent = strProperties[3][6];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[3][11].equals("4")){
						strRent = strProperties[3][7]; 
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}
				}
			}else if(strData.equals("Guidance 15")){
				if(strProperties[5][12].equals(monopolypanel.intPlayer+"")){
					int intCost; 
					String strRent; 
					
					// check to see if houses are owned:
					if(strProperties[5][11].equals("0")){
						strRent = strProperties[5][3];
						intCost = Integer.parseInt(strRent);
						intMoney = intMoney + intCost;  
					}else if(strProperties[5][11].equals("1")){
						strRent = strProperties[5][4];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[5][11].equals("2")){
						strRent = strProperties[5][5];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[5][11].equals("3")){
						strRent = strProperties[5][6];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[5][11].equals("4")){
						strRent = strProperties[5][7]; 
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}
				}
			}else if(strData.equals("Visual Arts6")){
				if(strProperties[6][12].equals(monopolypanel.intPlayer+"")){
					int intCost; 
					String strRent; 
					
					// check to see if houses are owned:
					if(strProperties[6][11].equals("0")){
						strRent = strProperties[6][3];
						intCost = Integer.parseInt(strRent);
						intMoney = intMoney + intCost;  
					}else if(strProperties[6][11].equals("1")){
						strRent = strProperties[6][4];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[6][11].equals("2")){
						strRent = strProperties[6][5];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[6][11].equals("3")){
						strRent = strProperties[6][6];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[6][11].equals("4")){
						strRent = strProperties[6][7]; 
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}
				}
			}else if(strData.equals("Music8")){
				if(strProperties[8][12].equals(monopolypanel.intPlayer+"")){
					int intCost; 
					String strRent; 
					
					// check to see if houses are owned:
					if(strProperties[8][11].equals("0")){
						strRent = strProperties[8][3];
						intCost = Integer.parseInt(strRent);
						intMoney = intMoney + intCost;  
					}else if(strProperties[8][11].equals("1")){
						strRent = strProperties[8][4];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[8][11].equals("2")){
						strRent = strProperties[8][5];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[8][11].equals("3")){
						strRent = strProperties[8][6];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[8][11].equals("4")){
						strRent = strProperties[8][7]; 
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}
				}
			}else if(strData.equals("Drama9")){
				if(strProperties[9][12].equals(monopolypanel.intPlayer+"")){
					int intCost; 
					String strRent; 
					
					// check to see if houses are owned:
					if(strProperties[9][11].equals("0")){
						strRent = strProperties[9][3];
						intCost = Integer.parseInt(strRent);
						intMoney = intMoney + intCost;  
					}else if(strProperties[9][11].equals("1")){
						strRent = strProperties[9][4];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[9][11].equals("2")){
						strRent = strProperties[9][5];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[9][11].equals("3")){
						strRent = strProperties[9][6];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[9][11].equals("4")){
						strRent = strProperties[9][7]; 
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}
				}
			}else if(strData.equals("Accounting11")){
				if(strProperties[11][12].equals(monopolypanel.intPlayer+"")){
					int intCost; 
					String strRent; 
					
					// check to see if houses are owned:
					if(strProperties[11][11].equals("0")){
						strRent = strProperties[11][3];
						intCost = Integer.parseInt(strRent);
						intMoney = intMoney + intCost;  
					}else if(strProperties[11][11].equals("1")){
						strRent = strProperties[11][4];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[11][11].equals("2")){
						strRent = strProperties[11][5];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[11][11].equals("3")){
						strRent = strProperties[11][6];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[11][11].equals("4")){
						strRent = strProperties[11][7]; 
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}
				}
			}else if(strData.equals("Student Council12")){
				if(strProperties[12][12].equals(monopolypanel.intPlayer+"")){
					int intCost; 
					String strRent; 
					
					// check to see if houses are owned:
					if(strProperties[12][11].equals("0")){
						strRent = strProperties[12][3];
						intCost = Integer.parseInt(strRent);
						intMoney = intMoney + intCost;  
					}else if(strProperties[12][11].equals("1")){
						strRent = strProperties[12][4];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[12][11].equals("2")){
						strRent = strProperties[12][5];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[12][11].equals("3")){
						strRent = strProperties[12][6];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[12][11].equals("4")){
						strRent = strProperties[12][7]; 
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}
				}
			}else if(strData.equals("Marketing13")){
				if(strProperties[13][12].equals(monopolypanel.intPlayer+"")){
					int intCost; 
					String strRent; 
					
					// check to see if houses are owned:
					if(strProperties[13][11].equals("0")){
						strRent = strProperties[13][3];
						intCost = Integer.parseInt(strRent);
						intMoney = intMoney + intCost;  
					}else if(strProperties[13][11].equals("1")){
						strRent = strProperties[13][4];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[13][11].equals("2")){
						strRent = strProperties[13][5];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[13][11].equals("3")){
						strRent = strProperties[13][6];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[13][11].equals("4")){
						strRent = strProperties[13][7]; 
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}
				}
			}else if(strData.equals("Business Leadership14")){
				if(strProperties[14][12].equals(monopolypanel.intPlayer+"")){
					int intCost; 
					String strRent; 
					
					// check to see if houses are owned:
					if(strProperties[14][11].equals("0")){
						strRent = strProperties[14][3];
						intCost = Integer.parseInt(strRent);
						intMoney = intMoney + intCost;  
					}else if(strProperties[14][11].equals("1")){
						strRent = strProperties[14][4];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[14][11].equals("2")){
						strRent = strProperties[14][5];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[14][11].equals("3")){
						strRent = strProperties[14][6];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[14][11].equals("4")){
						strRent = strProperties[14][7]; 
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}
				}
			}else if(strData.equals("Guidance 215")){
				if(strProperties[15][12].equals(monopolypanel.intPlayer+"")){
					int intCost; 
					String strRent; 
					
					// check to see if houses are owned:
					if(strProperties[15][11].equals("0")){
						strRent = strProperties[15][3];
						intCost = Integer.parseInt(strRent);
						intMoney = intMoney + intCost;  
					}else if(strProperties[15][11].equals("1")){
						strRent = strProperties[15][4];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[15][11].equals("2")){
						strRent = strProperties[15][5];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[15][11].equals("3")){
						strRent = strProperties[15][6];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[15][11].equals("4")){
						strRent = strProperties[15][7]; 
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}
				}
			}else if(strData.equals("Religion16")){
				if(strProperties[16][12].equals(monopolypanel.intPlayer+"")){
					int intCost; 
					String strRent; 
					
					// check to see if houses are owned:
					if(strProperties[16][11].equals("0")){
						strRent = strProperties[16][3];
						intCost = Integer.parseInt(strRent);
						intMoney = intMoney + intCost;  
					}else if(strProperties[16][11].equals("1")){
						strRent = strProperties[16][4];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[16][11].equals("2")){
						strRent = strProperties[16][5];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[16][11].equals("3")){
						strRent = strProperties[16][6];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[16][11].equals("4")){
						strRent = strProperties[16][7]; 
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}
				}
			}else if(strData.equals("World Religions18")){
				if(strProperties[18][12].equals(monopolypanel.intPlayer+"")){
					int intCost; 
					String strRent; 
					
					// check to see if houses are owned:
					if(strProperties[18][11].equals("0")){
						strRent = strProperties[18][3];
						intCost = Integer.parseInt(strRent);
						intMoney = intMoney + intCost;  
					}else if(strProperties[18][11].equals("1")){
						strRent = strProperties[18][4];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[18][11].equals("2")){
						strRent = strProperties[18][5];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[18][11].equals("3")){
						strRent = strProperties[18][6];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[18][11].equals("4")){
						strRent = strProperties[18][7]; 
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}
				}
			}else if(strData.equals("Philosphy19")){
				if(strProperties[19][12].equals(monopolypanel.intPlayer+"")){
					int intCost; 
					String strRent; 
					
					// check to see if houses are owned:
					if(strProperties[19][11].equals("0")){
						strRent = strProperties[19][3];
						intCost = Integer.parseInt(strRent);
						intMoney = intMoney + intCost;  
					}else if(strProperties[19][11].equals("1")){
						strRent = strProperties[19][4];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[19][11].equals("2")){
						strRent = strProperties[19][5];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[19][11].equals("3")){
						strRent = strProperties[19][6];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[19][11].equals("4")){
						strRent = strProperties[19][7]; 
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}
				}
			}else if(strData.equals("Indigenous Studies21")){
				if(strProperties[21][12].equals(monopolypanel.intPlayer+"")){
					int intCost; 
					String strRent; 
					
					// check to see if houses are owned:
					if(strProperties[21][11].equals("0")){
						strRent = strProperties[21][3];
						intCost = Integer.parseInt(strRent);
						intMoney = intMoney + intCost;  
					}else if(strProperties[21][11].equals("1")){
						strRent = strProperties[21][4];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[21][11].equals("2")){
						strRent = strProperties[21][5];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[21][11].equals("3")){
						strRent = strProperties[21][6];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[21][11].equals("4")){
						strRent = strProperties[21][7]; 
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}
				}
			}else if(strData.equals("Writer's Craft23")){
				if(strProperties[23][12].equals(monopolypanel.intPlayer+"")){
					int intCost; 
					String strRent; 
					
					// check to see if houses are owned:
					if(strProperties[23][11].equals("0")){
						strRent = strProperties[23][3];
						intCost = Integer.parseInt(strRent);
						intMoney = intMoney + intCost;  
					}else if(strProperties[23][11].equals("1")){
						strRent = strProperties[23][4];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[23][11].equals("2")){
						strRent = strProperties[23][5];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[23][11].equals("3")){
						strRent = strProperties[23][6];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[23][11].equals("4")){
						strRent = strProperties[23][7]; 
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}
				}
			}else if(strData.equals("English24")){
				if(strProperties[24][12].equals(monopolypanel.intPlayer+"")){
					int intCost; 
					String strRent; 
					
					// check to see if houses are owned:
					if(strProperties[24][11].equals("0")){
						strRent = strProperties[24][3];
						intCost = Integer.parseInt(strRent);
						intMoney = intMoney + intCost;  
					}else if(strProperties[24][11].equals("1")){
						strRent = strProperties[24][4];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[24][11].equals("2")){
						strRent = strProperties[24][5];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[24][11].equals("3")){
						strRent = strProperties[24][6];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[24][11].equals("4")){
						strRent = strProperties[24][7]; 
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}
				}
			}else if(strData.equals("Guidance 325")){
				if(strProperties[25][12].equals(monopolypanel.intPlayer+"")){
					int intCost; 
					String strRent; 
					
					// check to see if houses are owned:
					if(strProperties[25][11].equals("0")){
						strRent = strProperties[25][3];
						intCost = Integer.parseInt(strRent);
						intMoney = intMoney + intCost;  
					}else if(strProperties[25][11].equals("1")){
						strRent = strProperties[25][4];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[25][11].equals("2")){
						strRent = strProperties[25][5];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[25][11].equals("3")){
						strRent = strProperties[25][6];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[25][11].equals("4")){
						strRent = strProperties[25][7]; 
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}
				}
			}else if(strData.equals("Advanced Functions26")){
				if(strProperties[26][12].equals(monopolypanel.intPlayer+"")){
					int intCost; 
					String strRent; 
					
					// check to see if houses are owned:
					if(strProperties[26][11].equals("0")){
						strRent = strProperties[26][3];
						intCost = Integer.parseInt(strRent);
						intMoney = intMoney + intCost;  
					}else if(strProperties[26][11].equals("1")){
						strRent = strProperties[26][4];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[26][11].equals("2")){
						strRent = strProperties[26][5];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[26][11].equals("3")){
						strRent = strProperties[26][6];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[26][11].equals("4")){
						strRent = strProperties[26][7]; 
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}
				}
			}else if(strData.equals("Data Management27")){
				if(strProperties[27][12].equals(monopolypanel.intPlayer+"")){
					int intCost; 
					String strRent; 
					
					// check to see if houses are owned:
					if(strProperties[27][11].equals("0")){
						strRent = strProperties[27][3];
						intCost = Integer.parseInt(strRent);
						intMoney = intMoney + intCost;  
					}else if(strProperties[27][11].equals("1")){
						strRent = strProperties[27][4];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[27][11].equals("2")){
						strRent = strProperties[27][5];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[27][11].equals("3")){
						strRent = strProperties[27][6];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[27][11].equals("4")){
						strRent = strProperties[27][7]; 
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}
				}
			}else if(strData.equals("Athletic Council28")){
				if(strProperties[28][12].equals(monopolypanel.intPlayer+"")){
					int intCost; 
					String strRent; 
					
					// check to see if houses are owned:
					if(strProperties[28][11].equals("0")){
						strRent = strProperties[28][3];
						intCost = Integer.parseInt(strRent);
						intMoney = intMoney + intCost;  
					}else if(strProperties[28][11].equals("1")){
						strRent = strProperties[28][4];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[28][11].equals("2")){
						strRent = strProperties[28][5];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[28][11].equals("3")){
						strRent = strProperties[28][6];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[28][11].equals("4")){
						strRent = strProperties[28][7]; 
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}
				}
			}else if(strData.equals("Calculas and Vectors29")){
				if(strProperties[29][12].equals(monopolypanel.intPlayer+"")){
					int intCost; 
					String strRent; 
					
					// check to see if houses are owned:
					if(strProperties[29][11].equals("0")){
						strRent = strProperties[29][3];
						intCost = Integer.parseInt(strRent);
						intMoney = intMoney + intCost;  
					}else if(strProperties[29][11].equals("1")){
						strRent = strProperties[29][4];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[29][11].equals("2")){
						strRent = strProperties[29][5];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[29][11].equals("3")){
						strRent = strProperties[29][6];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[29][11].equals("4")){
						strRent = strProperties[29][7]; 
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}
				}
			}else if(strData.equals("Biology31")){
				if(strProperties[31][12].equals(monopolypanel.intPlayer+"")){
					int intCost; 
					String strRent; 
					
					// check to see if houses are owned:
					if(strProperties[31][11].equals("0")){
						strRent = strProperties[31][3];
						intCost = Integer.parseInt(strRent);
						intMoney = intMoney + intCost;  
					}else if(strProperties[31][11].equals("1")){
						strRent = strProperties[31][4];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[31][11].equals("2")){
						strRent = strProperties[31][5];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[31][11].equals("3")){
						strRent = strProperties[31][6];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[31][11].equals("4")){
						strRent = strProperties[31][7]; 
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}
				}
			}else if(strData.equals("Chemistry32")){
				if(strProperties[32][12].equals(monopolypanel.intPlayer+"")){
					int intCost; 
					String strRent; 
					
					// check to see if houses are owned:
					if(strProperties[32][11].equals("0")){
						strRent = strProperties[32][3];
						intCost = Integer.parseInt(strRent);
						intMoney = intMoney + intCost;  
					}else if(strProperties[32][11].equals("1")){
						strRent = strProperties[32][4];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[32][11].equals("2")){
						strRent = strProperties[32][5];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[32][11].equals("3")){
						strRent = strProperties[32][6];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[32][11].equals("4")){
						strRent = strProperties[32][7]; 
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}
				}
			}else if(strData.equals("Physics34")){
				if(strProperties[34][12].equals(monopolypanel.intPlayer+"")){
					int intCost; 
					String strRent; 
					
					// check to see if houses are owned:
					if(strProperties[34][11].equals("0")){
						strRent = strProperties[34][3];
						intCost = Integer.parseInt(strRent);
						intMoney = intMoney + intCost;  
					}else if(strProperties[34][11].equals("1")){
						strRent = strProperties[34][4];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[34][11].equals("2")){
						strRent = strProperties[34][5];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[34][11].equals("3")){
						strRent = strProperties[34][6];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[34][11].equals("4")){
						strRent = strProperties[34][7]; 
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}
				}
			}else if(strData.equals("Guidance 435")){
				if(strProperties[35][12].equals(monopolypanel.intPlayer+"")){
					int intCost; 
					String strRent; 
					
					// check to see if houses are owned:
					if(strProperties[35][11].equals("0")){
						strRent = strProperties[35][3];
						intCost = Integer.parseInt(strRent);
						intMoney = intMoney + intCost;  
					}else if(strProperties[35][11].equals("1")){
						strRent = strProperties[35][4];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[35][11].equals("2")){
						strRent = strProperties[35][5];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[35][11].equals("3")){
						strRent = strProperties[35][6];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[35][11].equals("4")){
						strRent = strProperties[35][7]; 
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}
				}
			}else if(strData.equals("Tech Design37")){
				if(strProperties[37][12].equals(monopolypanel.intPlayer+"")){
					int intCost; 
					String strRent; 
					
					// check to see if houses are owned:
					if(strProperties[37][11].equals("0")){
						strRent = strProperties[37][3];
						intCost = Integer.parseInt(strRent);
						intMoney = intMoney + intCost;  
					}else if(strProperties[37][11].equals("1")){
						strRent = strProperties[37][4];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[37][11].equals("2")){
						strRent = strProperties[37][5];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[37][11].equals("3")){
						strRent = strProperties[37][6];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[37][11].equals("4")){
						strRent = strProperties[37][7]; 
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}
				}
			}else if(strData.equals("Computer Science39")){
				if(strProperties[39][12].equals(monopolypanel.intPlayer+"")){
					int intCost; 
					String strRent; 
					
					// check to see if houses are owned:
					if(strProperties[39][11].equals("0")){
						strRent = strProperties[39][3];
						intCost = Integer.parseInt(strRent);
						intMoney = intMoney + intCost;  
					}else if(strProperties[39][11].equals("1")){
						strRent = strProperties[39][4];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[39][11].equals("2")){
						strRent = strProperties[39][5];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[39][11].equals("3")){
						strRent = strProperties[39][6];
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}else if(strProperties[39][11].equals("4")){
						strRent = strProperties[39][7]; 
						intCost = Integer.parseInt(strRent); 
						intMoney = intMoney + intCost; 
					}
				}
			}
			
			
			monopolypanel.textarea.setText("");
			
			
		}
		
		
	
				
		// --------- see if a message is regarding the purchase of a property by another player--------
		if(strData.equals("Geography") || strData.equals("History") || strData.equals("Guidance 1") || strData.equals("Visual Arts") || strData.equals("Music") || strData.equals("Drama") || strData.equals("Accounting") || strData.equals("Student Council") || strData.equals("Marketing") || strData.equals("Business Leadership")|| strData.equals("Guidance 2") || strData.equals("Religion") || strData.equals("World Religions")|| strData.equals("Philosphy")|| strData.equals("Indigenous Studies")|| strData.equals("Writer's Craft") || strData.equals("English")|| strData.equals("Guidance 3")|| strData.equals("Advanced Functions") || strData.equals("Data Management") || strData.equals("Athletic Council") || strData.equals("Calculas and Vectors") || strData.equals("Biology") || strData.equals("Chemistry") || strData.equals("Physics") || strData.equals("Guidance 4") || strData.equals("Tech Design") || strData.equals("Computer Science") || blnPropertyBought == true || strData.equals("Principal's Office") || blnPropertyBought1 == true || blnPropertyBought5 == true || blnPropertyBought6 == true ||blnPropertyBought8 == true ||blnPropertyBought9 == true||blnPropertyBought11 == true||blnPropertyBought12 == true||blnPropertyBought13 == true||blnPropertyBought14 == true||blnPropertyBought15 == true || blnPropertyBought16 == true||blnPropertyBought17 == true|| blnPropertyBought18 == true ||blnPropertyBought19 == true || blnPropertyBought21 ==true||blnPropertyBought23 == true|| blnPropertyBought24 == true || blnPropertyBought25 == true || blnPropertyBought26 ==true|| blnPropertyBought27 == true || blnPropertyBought28 == true|| blnPropertyBought29 == true||blnPropertyBought31 == true|| blnPropertyBought32 == true|| blnPropertyBought34 == true ||blnPropertyBought35 == true || blnPropertyBought37 == true || blnPropertyBought39 == true){
			blnMovement = false; 
		
		// this is for principals office:
		if(strData.equals("Principal's Ofice") || blnPropertyBought == true){
			blnPropertyBought = true; 
			if(blnPropertyBought == true){
				blnPropertyBought = false; 
			}
			
		}


			// this is for property number 1: 
			if(strData.equals("Geography") || blnPropertyBought1 == true){
				strProperties[1][9] = "t"; 
				blnPropertyBought1 = true; 
				if(blnPropertyBought1 == true){
					strProperties[1][12] = strData;
					blnPropertyBought1 = false; 
				}
			}else if(strData.equals("History") || blnPropertyBought3 == true){
				strProperties[3][9] = "t"; 
				blnPropertyBought3 = true; 
				if(blnPropertyBought3 == true){
					strProperties[3][12] = strData; 
					blnPropertyBought3 = false; 
				}
			}else if(strData.equals("Guidance 1") || blnPropertyBought5 == true){
				strProperties[5][9] = "t"; 
				blnPropertyBought5 = true; 
				if(blnPropertyBought5 == true){
					strProperties[5][12] = strData; 
					blnPropertyBought5 = false; 
				}
			}else if(strData.equals("Visual Arts") || blnPropertyBought6 == true){
				strProperties[6][9] = "t"; 
				blnPropertyBought6 = true; 
				if(blnPropertyBought6 == true){
					strProperties[6][12] = strData; 
					blnPropertyBought6 = false; 
				}
			}else if(strData.equals("Music") || blnPropertyBought8 == true){
				strProperties[8][9] = "t"; 
				blnPropertyBought8 = true; 
				if(blnPropertyBought8 == true){
					strProperties[8][12] = strData; 
					blnPropertyBought8 = false; 
				}
			}else if(strData.equals("Drama") || blnPropertyBought9 == true){
				strProperties[9][9] = "t"; 
				blnPropertyBought9 = true; 
				if(blnPropertyBought9 == true){
					strProperties[9][12] = strData; 
					blnPropertyBought9 = false; 
				}
			}else if(strData.equals("Accounting") || blnPropertyBought11 == true){
				strProperties[11][9] = "t"; 
				blnPropertyBought11 = true; 
				if(blnPropertyBought11 == true){
					strProperties[11][12] = strData; 
					blnPropertyBought11 = false; 
				}
			}else if(strData.equals("Student Council") || blnPropertyBought12 == true){
				strProperties[12][9] = "t"; 
				blnPropertyBought12 = true; 
				if(blnPropertyBought12== true){
					strProperties[12][12] = strData; 
					blnPropertyBought12 = false; 
				}
			}else if(strData.equals("Marketing") || blnPropertyBought13 == true){
				strProperties[13][9] = "t"; 
				blnPropertyBought13 = true; 
				if(blnPropertyBought13 == true){
					strProperties[13][12] = strData; 
					blnPropertyBought13 = false; 
				}
			}else if(strData.equals("Business Leadership") || blnPropertyBought14 == true){
				strProperties[14][9] = "t"; 
				blnPropertyBought14 = true; 
				if(blnPropertyBought14 == true){
					strProperties[14][12] = strData; 
					blnPropertyBought14 = false; 
				}
			}else if(strData.equals("Guidance 2") || blnPropertyBought15 == true){
				strProperties[15][9] = "t"; 
				blnPropertyBought15 = true; 
				if(blnPropertyBought15 == true){
					strProperties[15][12] = strData; 
					blnPropertyBought15 = false; 
				}
			}else if(strData.equals("Religion") || blnPropertyBought16 == true){
				strProperties[16][9] = "t"; 
				blnPropertyBought16 = true; 
				if(blnPropertyBought16 == true){
					strProperties[16][12] = strData; 
					blnPropertyBought16 = false; 
				}
			}else if(strData.equals("World Religions") || blnPropertyBought18== true){
				strProperties[18][9] = "t"; 
				blnPropertyBought18 = true; 
				if(blnPropertyBought18 == true){
					strProperties[18][12] = strData; 
					blnPropertyBought18 = false; 
				}
			}else if(strData.equals("Philosphy") || blnPropertyBought19 == true){
				strProperties[19][9] = "t"; 
				blnPropertyBought19 = true; 
				if(blnPropertyBought19 == true){
					strProperties[19][12] = strData; 
					blnPropertyBought19 = false; 
				}
			}else if(strData.equals("Indigenous Studies") || blnPropertyBought21 == true){
				strProperties[21][9] = "t"; 
				blnPropertyBought21 = true; 
				if(blnPropertyBought21 == true){
					strProperties[21][12] = strData; 
					blnPropertyBought21 = false; 
				}
			}else if(strData.equals("Writer's Craft") || blnPropertyBought23 == true){
				strProperties[23][9] = "t"; 
				blnPropertyBought23 = true; 
				if(blnPropertyBought23 == true){
					strProperties[23][12] = strData; 
					blnPropertyBought23 = false; 
				}
			}else if(strData.equals("English") || blnPropertyBought24 == true){
				strProperties[24][9] = "t"; 
				blnPropertyBought24 = true; 
				if(blnPropertyBought24 == true){
					strProperties[24][12] = strData; 
					blnPropertyBought24 = false; 
				}
			}else if(strData.equals("Guidance 3") || blnPropertyBought25 == true){
				strProperties[25][9] = "t"; 
				blnPropertyBought25 = true; 
				if(blnPropertyBought25 == true){
					strProperties[25][12] = strData; 
					blnPropertyBought25 = false; 
				}
			}else if(strData.equals("Advanced Functions") || blnPropertyBought26 == true){
				strProperties[26][9] = "t"; 
				blnPropertyBought26 = true; 
				if(blnPropertyBought26 == true){
					strProperties[26][12] = strData; 
					blnPropertyBought26 = false; 
				}
			}else if(strData.equals("Data Management") || blnPropertyBought27 == true){
				strProperties[27][9] = "t"; 
				blnPropertyBought27 = true; 
				if(blnPropertyBought27 == true){
					strProperties[27][12] = strData; 
					blnPropertyBought27 = false; 
				}
			}else if(strData.equals("Athletic Council") || blnPropertyBought28 == true){
				strProperties[28][9] = "t"; 
				blnPropertyBought28 = true; 
				if(blnPropertyBought28 == true){
					strProperties[28][12] = strData; 
					blnPropertyBought28 = false; 
				}
			}else if(strData.equals("Calculas and Vectors") || blnPropertyBought29 == true){
				strProperties[29][9] = "t"; 
				blnPropertyBought29 = true; 
				if(blnPropertyBought29 == true){
					strProperties[29][12] = strData; 
					blnPropertyBought29 = false; 
				}
			}else if(strData.equals("Biology") || blnPropertyBought31 == true){
				strProperties[31][9] = "t"; 
				blnPropertyBought31 = true; 
				if(blnPropertyBought31 == true){
					strProperties[31][12] = strData; 
					blnPropertyBought31 = false; 
				}
			}else if(strData.equals("Chemistry") || blnPropertyBought32 == true){
				strProperties[32][9] = "t"; 
				blnPropertyBought32 = true; 
				if(blnPropertyBought32 == true){
					strProperties[32][12] = strData; 
					blnPropertyBought32 = false; 
				}
			}else if(strData.equals("Physics") || blnPropertyBought34 == true){
				strProperties[34][9] = "t"; 
				blnPropertyBought34 = true; 
				if(blnPropertyBought34 == true){
					strProperties[34][12] = strData; 
					blnPropertyBought34 = false; 
				}
			}else if(strData.equals("Guidance 4") || blnPropertyBought35 == true){
				strProperties[35][9] = "t"; 
				blnPropertyBought35 = true; 
				if(blnPropertyBought35 == true){
					strProperties[35][12] = strData; 
					blnPropertyBought35 = false; 
				}
			}else if(strData.equals("Tech Design") || blnPropertyBought37 == true){
				strProperties[37][9] = "t"; 
				blnPropertyBought37 = true; 
				if(blnPropertyBought37 == true){
					strProperties[37][12] = strData; 
					blnPropertyBought37 = false; 
				}
			}else if(strData.equals("Computer Science") || blnPropertyBought39 == true){
				strProperties[39][9] = "t"; 
				blnPropertyBought39 = true; 
				if(blnPropertyBought39 == true){
					strProperties[39][12] = strData; 
					blnPropertyBought39 = false; 
				}
			}
		
			
		monopolypanel.textarea.setText("");
		
		}
				
		
		
			// If you are the server 
			if(blnServer == true){
				characterspanel.strData = ssm.readText(); 
				System.out.println("SERVER RECIEVED THIS: "+characterspanel.strData); 
				//Set button to false;
				if(characterspanel.strData.equals("select1")){
					characterspanel.select1.setEnabled(false); 
					monopolypanel.intPlayerCount = monopolypanel.intPlayerCount + 1;
					characterspanel.repaint();   
				}
				else if(characterspanel.strData.equals("select2")){
					monopolypanel.intPlayerCount = monopolypanel.intPlayerCount + 1;  
					characterspanel.select2.setEnabled(false); 
					characterspanel.repaint(); 
				}
				else if(characterspanel.strData.equals("select3")){
					characterspanel.select3.setEnabled(false); 
					characterspanel.repaint(); 
					monopolypanel.intPlayerCount = monopolypanel.intPlayerCount + 1;  
				}
				else if(characterspanel.strData.equals("select4")){
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
				}
				else if(characterspanel.strData.equals("select3")){
					monopolypanel.intPlayerCount = monopolypanel.intPlayerCount + 1;
					characterspanel.repaint();  	
					characterspanel.select3.setEnabled(false); 
				}
				else if(characterspanel.strData.equals("select4")){
					monopolypanel.intPlayerCount = monopolypanel.intPlayerCount + 1; 
					characterspanel.repaint(); 
					characterspanel.select4.setEnabled(false); 
				}
				// make the player count equal 
				characterspanel.intPlayerN = monopolypanel.intPlayerCount; 
				System.out.println(characterspanel.intPlayerN); 
				characterspanel.repaint(); 
			}
			
			System.out.println("This is the turn number: "+monopolypanel.intTurn); 
			System.out.println("this is the piece number: "+intPiece); 
			
			// only updates screen if the message is regard to movement
			if(blnMovement == true){
				// Update other players scren: 
				if(monopolypanel.intTurn == 1 && intPiece == 1){
				System.out.println("testing" +monopolypanel.strColor); 
				
					// how to know which information is being received?! 
					if(monopolypanel.strColor.equals("red") || monopolypanel.strColor.equals("blue") || monopolypanel.strColor.equals("yellow") || monopolypanel.strColor.equals("green")) {
						System.out.println("i'm back in here"); 
						monopolypanel.strColor1 = ssm.readText(); 
						intPiece = intPiece + 1; 
					}
				}
				else if(monopolypanel.intTurn == 1 && intPiece == 2){
					String strPlayerX; 
					strPlayerX = ssm.readText(); 
					monopolypanel.intPlayerX1 = Integer.parseInt(strPlayerX); 
					intPiece = intPiece + 1; 
				}
				else if(monopolypanel.intTurn == 1 && intPiece == 3){
					String strPlayerY; 
					strPlayerY = ssm.readText(); 
					monopolypanel.intPlayerY1 = Integer.parseInt(strPlayerY); 
					intPiece = intPiece +1; 
				}
				else if(monopolypanel.intTurn == 1 && intPiece == 4){
					// updates whos turn it is. 
					String strTurn; 
					strTurn = ssm.readText(); 
					monopolypanel.intTurn = Integer.parseInt(strTurn); 
					System.out.println("This is the turn number: "+monopolypanel.intTurn); 
					intPiece = 1; 
					
					
				}
				
				// code for second player: 
				if(monopolypanel.intTurn == 2 && intPiece == 1){
				System.out.println("testing" +monopolypanel.strColor); 
					// how to know which information is being received?! 
					if(monopolypanel.strColor.equals("red") || monopolypanel.strColor.equals("blue") || monopolypanel.strColor.equals("yellow") || monopolypanel.strColor.equals("green")) {
						System.out.println("I'm receiving it here"); 
						monopolypanel.strColor2 = ssm.readText(); 
						intPiece = intPiece + 1; 
					}
				}
				else if(monopolypanel.intTurn == 2 && intPiece == 2){
					String strPlayerX; 
					strPlayerX = ssm.readText(); 
					monopolypanel.intPlayerX2 = Integer.parseInt(strPlayerX); 
					intPiece = intPiece + 1; 
				}else if(monopolypanel.intTurn == 2 && intPiece == 3){
					String strPlayerY; 
					strPlayerY = ssm.readText(); 
					monopolypanel.intPlayerY2 = Integer.parseInt(strPlayerY); 
					intPiece = intPiece +1; 
				}
				else if(monopolypanel.intTurn == 2 && intPiece == 4){
					// updates whos turn it is. 
					String strTurn; 
					strTurn = ssm.readText(); 
					monopolypanel.intTurn = Integer.parseInt(strTurn); 
					System.out.println("This is the turn number: "+monopolypanel.intTurn); 
					intPiece = 1; 
				
					
				}
				
				
				// code for third player: 
				if(monopolypanel.intTurn == 3 && intPiece == 1){
				System.out.println("testing" +monopolypanel.strColor); 
					// how to know which information is being received?! 
					if(monopolypanel.strColor.equals("red") || monopolypanel.strColor.equals("blue") || monopolypanel.strColor.equals("yellow") || monopolypanel.strColor.equals("green")) {
						monopolypanel.strColor3 = ssm.readText(); 
						intPiece = intPiece + 1; 
					}
				}
				else if(monopolypanel.intTurn == 3 && intPiece == 2){
					String strPlayerX; 
					strPlayerX = ssm.readText(); 
					monopolypanel.intPlayerX3 = Integer.parseInt(strPlayerX); 
					intPiece = intPiece + 1; 
				}
				else if(monopolypanel.intTurn == 3 && intPiece == 3){
					String strPlayerY; 
					strPlayerY = ssm.readText(); 
					monopolypanel.intPlayerY3 = Integer.parseInt(strPlayerY); 
					intPiece = intPiece +1; 
				}
				else if(monopolypanel.intTurn == 3 && intPiece == 4){
					// updates whos turn it is. 
					String strTurn; 
					strTurn = ssm.readText(); 
					monopolypanel.intTurn = Integer.parseInt(strTurn); 
					System.out.println("This is the turn number: "+monopolypanel.intTurn); 
					intPiece = 1; 
					
					
				}
				
				// code for player four: 
				if(monopolypanel.intTurn == 4 && intPiece == 1){
				System.out.println("testing" +monopolypanel.strColor); 
					// how to know which information is being received?! 
					if(monopolypanel.strColor.equals("red") || monopolypanel.strColor.equals("blue") || monopolypanel.strColor.equals("yellow") || monopolypanel.strColor.equals("green")) {
						monopolypanel.strColor4 = ssm.readText(); 
						intPiece = intPiece + 1; 
					}
				}
				else if(monopolypanel.intTurn == 4 && intPiece == 2){
					String strPlayerX; 
					strPlayerX = ssm.readText(); 
					monopolypanel.intPlayerX4 = Integer.parseInt(strPlayerX); 
					intPiece = intPiece + 1; 
				}
				else if(monopolypanel.intTurn == 4 && intPiece == 3){
					String strPlayerY; 
					strPlayerY = ssm.readText(); 
					monopolypanel.intPlayerY4 = Integer.parseInt(strPlayerY); 
					intPiece = intPiece +1; 
				}
				else if(monopolypanel.intTurn == 4 && intPiece == 4){
					// updates whos turn it is. 
					String strTurn; 
					strTurn = ssm.readText(); 
					monopolypanel.intTurn = Integer.parseInt(strTurn); 
					System.out.println("This is the turn number: "+monopolypanel.intTurn); 
					intPiece = 1; 
					

				}
				
				// fifth code to reset the turn number if there are four players playing, the turn number will turn to this and reset the code
				if(monopolypanel.intTurn == 5 && intPiece == 1){
					monopolypanel.intTurn = 1; 
					intPiece = 1;
					// allowing button to reset
					
				}
				
				monopolypanel.textarea.setText("");
			}
			
			monopolypanel.textarea.append("\n"+strData); 
			blnMovement = true; 
			 
			
		}
	
			
		//ROLLING THE DICE
		else if(evt.getSource() == monopolypanel.rolldie){
			//- making sure you can't access these buttons
			monopolypanel.house.setEnabled(false);
			monopolypanel.hotel.setEnabled(false);
			monopolypanel.buy.setEnabled(false);
			monopolypanel.dontbuy.setEnabled(false);
			//- random die rolling
			intdice1 = (int)(Math.random() *6+1); 
			intdice2 = (int)(Math.random()*6+1); 
			intdiesum = intdice1 + intdice2; 
			blnDice = true; 
			
			// make it even number
			intRoll = intRoll*2; 
			//send stuff to update location of your character 
			// make if you get to a certain point the intYOUy is changed instead.
			// it is not going up 
			
			//- printing out dice to animation monopoly panel
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

			for(intCount = 1; intCount <= intdiesum; intCount++){
				if(monopolypanel.intYOUx <= 650 && monopolypanel.intYOUx > 50 && monopolypanel.intYOUy >= 650){
					monopolypanel.intYOUx = monopolypanel.intYOUx - 60; 	
				}
				else if(monopolypanel.intYOUy > 50 && monopolypanel.intYOUy > 40 && monopolypanel.intYOUx == 50){
					monopolypanel.intYOUy = monopolypanel.intYOUy - 60; 
				}
				else if(monopolypanel.intYOUy < 51 && monopolypanel.intYOUx < 650){
					monopolypanel.intYOUx =monopolypanel.intYOUx + 60; 
				}
				else if(monopolypanel.intYOUy < 650 && monopolypanel.intYOUx == 650){
					monopolypanel.intYOUy = monopolypanel.intYOUy + 60; 
				}
			}
		
			
			//-If you pass GO
			if(monopolypanel.intPropertyN>39){
				monopolypanel.intPropertyN = monopolypanel.intPropertyN - 40;
				intMoney = intMoney+200;
			}
			
			//-Landing on chance or community cards
			String strCard;
			String strNum;
			String strCheck; //check if the card affects the location or money
			int intLocation; //if it affects location
			int intMoneyChance; //money affected from chance
			int intMoneyCommunity; //money affected from community
			int intCard; // card that is randomly chosen
			int intNum; // random num generated.
			int intLength; // measure the number of letters in a string
			int intLength2;
			int intLandedRent; //the rent of the property that you landed on 
			int intRemain = 0; // the amount of letters remaining. 
			int intLines = 0; // lines needed to print (might not need this nayrmoe)
			int intLengthMax = 25;
		
			//initialize
			strCard = strProperties[monopolypanel.intPropertyN][0];
			
			//-community chest
			if(strCard.equalsIgnoreCase("community")){
				monopolypanel.strCard = "community card";
				monopolypanel.strDisplayLength = "";
				monopolypanel.strDisplayLength2 = "";
				monopolypanel.strDisplayLength3 = "";
				//generates random number
				intNum = (int) (Math.random()*29+0);
				//card info
				strCard = strCommunity[intNum][1];
				monopolypanel.strCardinfo = strCard;
				System.out.println(strCommunity[intNum][1]);// prints out what the community card says

				
				// if intLength is greater than 25 letters

				intLength = strCommunity[intNum][1].length();
				intRemain = intLength - intLengthMax; 
				
					if(intLength > 25){
						monopolypanel.strDisplayLength = strCommunity[intNum][1].substring(0,intLengthMax);
						intRemain = intLength - intRemain; 
						monopolypanel.strDisplayLength2 = strCommunity[intNum][1].substring(intRemain,intLength);
							if(intRemain > 25){
								monopolypanel.strDisplayLength2 = strCommunity[intNum][1].substring(intRemain,intLength - intRemain);
								intRemain = intLength - intRemain;
								monopolypanel.strDisplayLength3 = strCommunity[intNum][1].substring(intRemain,intLength);
						}else if (intRemain < 25){
							monopolypanel.strDisplayLength2 = strCommunity[intNum][1].substring(intRemain,intLength);
						}							
						}else{
							monopolypanel.strDisplayLength = strCommunity[intNum][1].substring(0,intLength);
						}
					
						
				intMoneyCommunity = Integer.parseInt(strCommunity[intNum][2]);
				intMoney = intMoney + intMoneyCommunity;
				monopolypanel.intMoney = intMoney;
			}
			//-chance
			else if (strCard.equalsIgnoreCase("chance")){
				monopolypanel.strCard = "chance";
				monopolypanel.strDisplayLength = "";
				monopolypanel.strDisplayLength2 = "";
				monopolypanel.strDisplayLength3 = "";
				// generates random card number
				intNum = (int) (Math.random()*29+0);
				// prints out what the community card says
				strCard = strChance[intNum][1];
				monopolypanel.strCardinfo = strCard;
				System.out.println(strChance[intNum][1]);
				//check if it affects money or location
				strCheck = strChance[intNum][2].substring(0,1);
				System.out.println(strCheck);
				
				//--if statements
				//---location
				if(strCheck.equalsIgnoreCase("l")){
					intLocation = Integer.parseInt(strChance[intNum][3]);
					monopolypanel.intPropertyN = intLocation;
					//Printing out location effect on the board from animation panel 
					if(intLocation>=0 && intLocation<10){
						monopolypanel.intYOUx = 650-((intLocation)*60);
						monopolypanel.intYOUy = 650;
					}
					else if(intLocation>=10 && intLocation<20){
						monopolypanel.intYOUx = 50;
						monopolypanel.intYOUy = 650 - ((intLocation-10)*60);
					}
					else if(intLocation>=20 && intLocation<30){
						monopolypanel.intYOUx = 50 + ((intLocation-20)*60);
						monopolypanel.intYOUy = 50;
					}
					else if(intLocation>=30 && intLocation<40){
						monopolypanel.intYOUx = 650;
						monopolypanel.intYOUy = 50 + ((intLocation-30)*60);
					}
					//if chance card sends you to jail
					if(intLocation == 10){
						blnOffice = true;
						intOfficeTime = 4;
					}
					//*** need to add logic that if location passes go then add money :)
				}
				//-money
				else if(strCheck.equalsIgnoreCase("m")){
					intMoneyChance = Integer.parseInt(strChance[intNum][3]);
					intMoney = intMoney + intMoneyChance;
					monopolypanel.intMoney = intMoney;
				}
				//-Printing Chance CardInfo to panel 
				// if intLength is greater than 25 letters

				intLength = strChance[intNum][1].length();
				intRemain = intLength - intLengthMax;
					
					if(intLength > 25){
						monopolypanel.strDisplayLength = strChance[intNum][1].substring(0,intLengthMax);
						intRemain = intLength - intRemain; 
						monopolypanel.strDisplayLength2 = strChance[intNum][1].substring(intRemain,intLength);
							if(intRemain > 25){
								monopolypanel.strDisplayLength2 = strChance[intNum][1].substring(intRemain,intLength - intRemain);
								intRemain = intLength - intRemain;
								monopolypanel.strDisplayLength3 = strChance[intNum][1].substring(intRemain,intLength);
						}else if (intRemain < 25){
							monopolypanel.strDisplayLength2 = strChance[intNum][1].substring(intRemain,intLength);
						}							
						}else{
							monopolypanel.strDisplayLength = strChance[intNum][1].substring(0,intLength);
						}
					
				//monopolypanel.intCardY = 145;// resets intcardY value

			}
			
			//-check if property is owned
			//--if property isn't owned
			if(strProperties[monopolypanel.intPropertyN][9].equalsIgnoreCase("f")){
				intPropertyCost = Integer.parseInt(strProperties[monopolypanel.intPropertyN][2]);
				//checks to see if you can afford the property
				if(intMoney>intPropertyCost){
					monopolypanel.buy.setEnabled(true);
					monopolypanel.dontbuy.setEnabled(true);
				}
				else{
					monopolypanel.buy.setEnabled(false);
					monopolypanel.dontbuy.setEnabled(true);
				}
			}
			//--if property is owned
			else if(strProperties[monopolypanel.intPropertyN][9].equalsIgnoreCase("t")){
				monopolypanel.buy.setEnabled(false);
				monopolypanel.dontbuy.setEnabled(false);
				intPlayerOwned = Integer.parseInt(strProperties[monopolypanel.intPropertyN][12]);
				if(intPlayerOwned == monopolypanel.intPlayer){
					intLandedRent = 0;
				}
				else{
					intLandedRent = Integer.parseInt(strProperties[monopolypanel.intPropertyN][3]);
				}
				//if utilities owned
				if(monopolypanel.intPropertyN == 12 || monopolypanel.intPropertyN == 28){
					intLandedRent = intdiesum*intLandedRent;
				}

				intMoney = intMoney - intLandedRent;
			}
			//--printing out from animation panel your money and the property you landed on
			monopolypanel.strPropertyN = strProperties[monopolypanel.intPropertyN][0]; 	
			monopolypanel.strMoney = intMoney+"";
			
			//--landing on go to prinicpal office
			if(strProperties[monopolypanel.intPropertyN][0].equalsIgnoreCase("Go To Principal's Office")){
				//Player moves to principal office
				monopolypanel.intPropertyN = 10;
				monopolypanel.intYOUx = 50;
				monopolypanel.intYOUy = 650; 
				blnOffice = true;
				intOfficeTime = 4;
			}
		
			//if player is sent to principal's office
			if(blnOffice){
				intOfficeTime = intOfficeTime-1;
				System.out.println(intOfficeTime + " turns remaining till you can leave the office.");
				monopolypanel.intPropertyN = 10;
				monopolypanel.intYOUx = 50;
				monopolypanel.intYOUy = 650; 
				monopolypanel.strPropertyN = strProperties[10][0];
				monopolypanel.buy.setEnabled(true);
				monopolypanel.dontbuy.setEnabled(false);
				//rolling doubles can get you out of jail
				//- but you need to be in jail for at least one turn
				if(intdice1==intdice2 && intOfficeTime<3 && intOfficeTime>0){
					blnOffice = false;
				}
			}
			//how many turns after being sent to the principal's office
			if(intOfficeTime<1){
				blnOffice = false;
			}
			
			intHouse = Integer.parseInt(strProperties[monopolypanel.intPropertyN][11]);
			//-------------------------- Checking if you have the option to buy a house --------------
			if(strProperties[monopolypanel.intPropertyN][10].equalsIgnoreCase("br")){
				if(intHouse<3 && intBrown == 2){
					monopolypanel.house.setEnabled(true);
					monopolypanel.hotel.setEnabled(false);
				}
				else if(intHouse==3 && intBrown == 2){
					monopolypanel.hotel.setEnabled(true);
					monopolypanel.house.setEnabled(false);
				}
			}
			else if(strProperties[monopolypanel.intPropertyN][10].equalsIgnoreCase("lb")){
				if(intHouse<3 && intLightBlue == 3){
					monopolypanel.house.setEnabled(true);
					monopolypanel.hotel.setEnabled(false);
				}
				else if(intHouse==3 && intLightBlue == 3){
					monopolypanel.hotel.setEnabled(true);
					monopolypanel.house.setEnabled(false);
				}
			}
			else if(strProperties[monopolypanel.intPropertyN][10].equalsIgnoreCase("pur")){
				if(intHouse<3 && intPurple == 3){
					monopolypanel.house.setEnabled(true);
					monopolypanel.hotel.setEnabled(false);
				}
				else if(intHouse==3 && intPurple == 3){
					monopolypanel.hotel.setEnabled(true);
					monopolypanel.house.setEnabled(false);
				}
			}
			else if(strProperties[monopolypanel.intPropertyN][10].equalsIgnoreCase("or")){
				if(intHouse<3 && intOrange == 3){
					monopolypanel.house.setEnabled(true);
					monopolypanel.hotel.setEnabled(false);
				}
				else if(intHouse==3 && intOrange == 3){
					monopolypanel.hotel.setEnabled(true);
					monopolypanel.house.setEnabled(false);
				}
			}
			else if(strProperties[monopolypanel.intPropertyN][10].equalsIgnoreCase("red")){
				if(intHouse<3 && intRed == 3){
					monopolypanel.house.setEnabled(true);
					monopolypanel.hotel.setEnabled(false);
				}
				else if(intHouse==3 && intRed == 3){
					monopolypanel.hotel.setEnabled(true);
					monopolypanel.house.setEnabled(false);
				}
			}
			else if(strProperties[monopolypanel.intPropertyN][10].equalsIgnoreCase("yel")){
				if(intHouse<3 && intYellow == 3){
					monopolypanel.house.setEnabled(true);
					monopolypanel.hotel.setEnabled(false);
				}
				else if(intHouse==3 && intYellow == 3){
					monopolypanel.hotel.setEnabled(true);
					monopolypanel.house.setEnabled(false);
				}
			}
			else if(strProperties[monopolypanel.intPropertyN][10].equalsIgnoreCase("gre")){
				if(intHouse<3 && intGreen == 3){
					monopolypanel.house.setEnabled(true);
					monopolypanel.hotel.setEnabled(false);
				}
				else if(intHouse==3 && intGreen == 3){
					monopolypanel.hotel.setEnabled(true);
					monopolypanel.house.setEnabled(false);
				}
			}
			else if(strProperties[monopolypanel.intPropertyN][10].equalsIgnoreCase("db")){
				if(intHouse<3 && intDarkBlue == 3){
					monopolypanel.house.setEnabled(true);
					monopolypanel.hotel.setEnabled(false);
				}
				else if(intHouse==3 && intDarkBlue == 3){
					monopolypanel.hotel.setEnabled(true);
					monopolypanel.house.setEnabled(false);
				}
			}	
		}
		
		//TIMER (REPAINT)
		//updating the animation panel using a timer
		else if(evt.getSource() == thetimer){
			monopolypanel.repaint(); 
			// determining whose turn it is!
			
			if(monopolypanel.intPlayer == monopolypanel.intTurn){
				monopolypanel.rolldie.setEnabled(true); 
			//	System.out.println("This is your colour" +monopolypanel.strColor); 
				if(blnDice == true){
					// sending colour
					ssm.sendText(monopolypanel.strColor); 
					// sending x-coordinate
					ssm.sendText(monopolypanel.intYOUx+""); 
					// sending y - coordinate
					ssm.sendText(monopolypanel.intYOUy+""); 
					// sending out text to say to change intTurn variable
					if(intRoll%2 == 0 && blnPlay == true && blnDice == true){
						 
						monopolypanel.intTurn = monopolypanel.intTurn +1; 
						System.out.println("Turn number before change over: "+monopolypanel.intTurn); 
						System.out.println("Player count: "+monopolypanel.intPlayerCount); 
						
						
						if(monopolypanel.intTurn < monopolypanel.intPlayerCount+1){
							ssm.sendText(monopolypanel.intTurn+""); 
							intRoll = intRoll + 1; 
							System.out.println("I got here");
							intPiece = 1; 
						}else if(monopolypanel.intTurn == monopolypanel.intPlayerCount +1){
							System.out.println("hereee you go"); 
							ssm.sendText("1");
							monopolypanel.intTurn = 1; 
							intPiece = 1; 
						}
						
						// set button to false after rolling:
						monopolypanel.rolldie.setEnabled(false);
						blnDice = false; 
					}
				}
			}
			else{
				monopolypanel.rolldie.setEnabled(false); 
			}	
		}
		
		//BUYING PROPERTIES
		else if(evt.getSource()== monopolypanel.buy){
			monopolypanel.buy.setEnabled(false);
			monopolypanel.dontbuy.setEnabled(false);
			monopolypanel.blnOwned = true;
			//When property is bought
			strPropertyCost = strProperties[monopolypanel.intPropertyN][2]; // cost of property
			intPropertyCost = Integer.parseInt(strProperties[monopolypanel.intPropertyN][2]); // converting string to int
			intMoney = intMoney-intPropertyCost; // subtracting
			
		
			monopolypanel.strPropertyOwned = strProperties[monopolypanel.intPropertyN][0];
			monopolypanel.strMoney = intMoney+"";
			monopolypanel.intMoney = intMoney;
			monopolypanel.intRent = Integer.parseInt(strProperties[monopolypanel.intPropertyN][3]);
			monopolypanel.intHouse1 = Integer.parseInt(strProperties[monopolypanel.intPropertyN][4]);
			monopolypanel.intHouse2 = Integer.parseInt(strProperties[monopolypanel.intPropertyN][5]);
			monopolypanel.intHouse3 = Integer.parseInt(strProperties[monopolypanel.intPropertyN][6]);
			monopolypanel.intHotel = Integer.parseInt(strProperties[monopolypanel.intPropertyN][7]);
			strProperties[monopolypanel.intPropertyN][9]= "t";
			strProperties[monopolypanel.intPropertyN][12] = monopolypanel.intPlayer+""; 
			
			//---------------------------- Trying to make player pay for jail--------------------------
			if(monopolypanel.intPropertyN==10){
				System.out.println("bought my way out of jail");
				blnOffice = false;
				intOfficeTime = 0;
				intMoney = intMoney - 50;
				monopolypanel.buy.setEnabled(false);
				monopolypanel.dontbuy.setEnabled(false);
			}
			//-------------------------- Buying the colour --------------------------
			if(strProperties[monopolypanel.intPropertyN][10].equalsIgnoreCase("br")){
				monopolypanel.strColour= strProperties[monopolypanel.intPropertyN][10];
				intBrown = intBrown + 1;
				intNumofProperties = intNumofProperties +1;
				System.out.println("Properties of this colour: " + intBrown);
			}
			if(strProperties[monopolypanel.intPropertyN][10].equalsIgnoreCase("u")){
				monopolypanel.strColour= strProperties[monopolypanel.intPropertyN][10];
				intUtility = intUtility + 1;
				intNumofProperties = intNumofProperties +1;
				System.out.println("Properties of this utility: " +intUtility);
			}
			else if(strProperties[monopolypanel.intPropertyN][10].equalsIgnoreCase("rail")){
				monopolypanel.strColour= strProperties[monopolypanel.intPropertyN][10];
				intRailRoad = intRailRoad + 1;
				intNumofProperties = intNumofProperties +1;
				strProperties[monopolypanel.intPropertyN][11] = intRailRoad+"";
				System.out.println("Properties of this colour: " + intRailRoad);
			}
			else if(strProperties[monopolypanel.intPropertyN][10].equalsIgnoreCase("lb")){
				monopolypanel.strColour= strProperties[monopolypanel.intPropertyN][10];
				intLightBlue = intLightBlue	+ 1;
				intNumofProperties = intNumofProperties +1;
				System.out.println("Properties of this colour: " + intLightBlue);
			}
			else if(strProperties[monopolypanel.intPropertyN][10].equalsIgnoreCase("pur")){
				monopolypanel.strColour= strProperties[monopolypanel.intPropertyN][10];
				intPurple = intPurple + 1;
				intNumofProperties = intNumofProperties +1;
				System.out.println("Properties of this colour: " + intPurple);
			}
			else if(strProperties[monopolypanel.intPropertyN][10].equalsIgnoreCase("or")){
				monopolypanel.strColour= strProperties[monopolypanel.intPropertyN][10];
				intOrange = intOrange + 1;
				intNumofProperties = intNumofProperties +1;
				System.out.println("Properties of this colour: " + intOrange);
			}
			else if(strProperties[monopolypanel.intPropertyN][10].equalsIgnoreCase("red")){
				monopolypanel.strColour= strProperties[monopolypanel.intPropertyN][10];
				intRed = intRed + 1;
				System.out.println("Properties of this colour: " + intRed);
			}
			else if(strProperties[monopolypanel.intPropertyN][10].equalsIgnoreCase("yel")){
				monopolypanel.strColour= strProperties[monopolypanel.intPropertyN][10];
				intYellow = intYellow + 1;
				intNumofProperties = intNumofProperties +1;
				System.out.println("Properties of this colour: " + intYellow);
			}
			else if(strProperties[monopolypanel.intPropertyN][10].equalsIgnoreCase("gre")){
				monopolypanel.strColour= strProperties[monopolypanel.intPropertyN][10];
				intGreen = intGreen + 1;
				intNumofProperties = intNumofProperties +1;
				System.out.println("Properties of this colour: " + intGreen);
			}
			else if(strProperties[monopolypanel.intPropertyN][10].equalsIgnoreCase("db")){
				monopolypanel.strColour= strProperties[monopolypanel.intPropertyN][10];
				intDarkBlue = intDarkBlue + 1;
				intNumofProperties = intNumofProperties +1;
				System.out.println("Properties of this colour: " + intDarkBlue);
			}
			// ---------------------- Sending message over the server that the property can no longer be bought --------------
			ssm.sendText(monopolypanel.strPropertyN); 
			ssm.sendText(monopolypanel.intPlayer+""); 
		
			System.out.println(monopolypanel.strPropertyN); 
			System.out.println(monopolypanel.intPlayer); 
			// to load in that you bought the property
			System.out.println("This is what is inserted into the CSV: "); 
			System.out.println(strProperties[monopolypanel.intPropertyN][9]); 
			System.out.println(strProperties[monopolypanel.intPropertyN][12]); 
			
			// ------------- Sending message over the server about money when you owe someone -----------
			ssm.sendText(monopolypanel.strPropertyN+monopolypanel.intPropertyN);
			System.out.println(monopolypanel.strPropertyN+monopolypanel.intPropertyN);
			
			//set viewing properties enabled
			if(intNumofProperties>1){
				monopolypanel.next.setEnabled(true);
				monopolypanel.back.setEnabled(true);
			}
			
		}
		//PROPERTIES PANEL (that you own)
		//-next
		else if(evt.getSource()==monopolypanel.next){
			intNumofProperties = 0;
			// - checking if you own the property
			for (intRow = 0; intRow < 40; intRow ++){	
			intPlayerOwned = Integer.parseInt(strProperties[intRow][12]);
			if(intPlayerOwned == monopolypanel.intPlayer){
		  		intNumofProperties = intNumofProperties +1;
				}
			}
		  System.out.println("You own this many properties: " +intNumofProperties);
			// - intialize array
			intPropertiesOwned = new int [intNumofProperties];
			intCol = -1;
		 
			// - load array
			for(intRow = 0; intRow < 40; intRow ++){	
				intPlayerOwned = Integer.parseInt(strProperties[intRow][12]);
				if(intPlayerOwned == monopolypanel.intPlayer){
					intCol = intCol+1;
					intPropertiesOwned[intCol] = intRow; 		
					}
				}
			intPropertyPanel = intPropertyPanel+1;	
			//printing out array (clicking the next button shows next property that you also owned
			if(intPropertyPanel>intNumofProperties){
				intPropertyPanel = intNumofProperties-1;
			}	
			
			//set colour of property in property outline
			monopolypanel.strPropertyOwned = strProperties[monopolypanel.intPropertyN][0];
			monopolypanel.strColour = strProperties[intPropertiesOwned[intPropertyPanel]][10];
			monopolypanel.strPropertyOwned = strProperties[intPropertiesOwned[intPropertyPanel]][0];
			monopolypanel.intRent = Integer.parseInt(strProperties[intPropertiesOwned[intPropertyPanel]][3]);
			monopolypanel.intHouse1 = Integer.parseInt(strProperties[intPropertiesOwned[intPropertyPanel]][4]);
			monopolypanel.intHouse2 = Integer.parseInt(strProperties[intPropertiesOwned[intPropertyPanel]][5]);
			monopolypanel.intHouse3 = Integer.parseInt(strProperties[intPropertiesOwned[intPropertyPanel]][6]);
			monopolypanel.intHotel = Integer.parseInt(strProperties[intPropertiesOwned[intPropertyPanel]][7]);
		
		}
		//-back
		else if(evt.getSource() == monopolypanel.back){
			intNumofProperties= 0;
			// - checking if you own the property
			for (intRow = 0; intRow < 40; intRow ++){	
			intPlayerOwned = Integer.parseInt(strProperties[intRow][12]);
			if(intPlayerOwned == monopolypanel.intPlayer){
		  		intNumofProperties = intNumofProperties +1;
				}
			}
		  System.out.println("You own this many properties: " +intNumofProperties);
			// - intialize array
			intPropertiesOwned = new int [intNumofProperties];
			intCol = -1;
		 
			// - load array
			for(intRow = 0; intRow < 40; intRow ++){
				intPlayerOwned = Integer.parseInt(strProperties[intRow][12]);
				if(intPlayerOwned == monopolypanel.intPlayer){
					intCol = intCol+1;
					intPropertiesOwned[intCol] = intRow; 		
					}
				}
			intPropertyPanel = intPropertyPanel-1;	
			//printing out array (clicking the next button shows next property that you also owned
			if(intPropertyPanel<0){
				intPropertyPanel = 0;
			}	
			monopolypanel.strPropertyOwned = strProperties[intPropertiesOwned[intPropertyPanel]][0];
			monopolypanel.strColour = strProperties[intPropertiesOwned[intPropertyPanel]][10];
			monopolypanel.intRent = Integer.parseInt(strProperties[intPropertiesOwned[intPropertyPanel]][3]);
			monopolypanel.intHouse1 = Integer.parseInt(strProperties[intPropertiesOwned[intPropertyPanel]][4]);
			monopolypanel.intHouse2 = Integer.parseInt(strProperties[intPropertiesOwned[intPropertyPanel]][5]);
			monopolypanel.intHouse3 = Integer.parseInt(strProperties[intPropertiesOwned[intPropertyPanel]][6]);
			monopolypanel.intHotel = Integer.parseInt(strProperties[intPropertiesOwned[intPropertyPanel]][7]);
			
		}
		
		//BUYING A HOUSE (Classroom Improvement)
		else if(evt.getSource() == monopolypanel.house){
			if(intHouse<3){
				intMoney = intMoney - 50; 
				intHouse = intHouse + 1;
				monopolypanel.strMoney = intMoney+"";
				monopolypanel.intMoney = intMoney;
			}
			monopolypanel.house.setEnabled(false);
			monopolypanel.hotel.setEnabled(false);
			System.out.println(intHouse + "on this property now");
			strProperties[monopolypanel.intPropertyN][11] = intHouse+"";
			//send text that you own
			ssm.sendText(monopolypanel.intPropertyN+monopolypanel.strPropertyN); 
			ssm.sendText(intHouse+"");
		}
		
		//BUYING A HOTEL (Upgrade to STEM)
		else if(evt.getSource() == monopolypanel.hotel){
			System.out.println("bought a hotel");
			intHouse = 4;
			strProperties[monopolypanel.intPropertyN][11] = intHouse+"";
			intMoney = intMoney - 100;
			monopolypanel.strMoney = intMoney+"";
			monopolypanel.intMoney = intMoney;
			monopolypanel.house.setEnabled(false);
			monopolypanel.hotel.setEnabled(false);
		}
		
		//Ending of game
		if (intMoney <= 0){
			intMoney = 0;
			monopolypanel.buy.setVisible(false);
			monopolypanel.dontbuy.setVisible(false);
			monopolypanel.house.setVisible(false);
			monopolypanel.rolldie.setVisible(false);
			monopolypanel.next.setVisible(false);
			monopolypanel.back.setVisible(false);
			monopolypanel.hotel.setVisible(false);
			monopolypanel.textarea.setVisible(false);
			monopolypanel.scroll.setVisible(false);
			monopolypanel.textfield.setVisible(false);
			monopolypanel.intTimer = 0;
			monopolypanel.blnwinner = false;//  
		}
				
	}
	
	//Constructor
	/** Constructor for the St. Augustine Monopoly */
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
		 * [9] Boolean - if the property is owned (if you landed on it)
		 * [10] Colour of property
		 * [11] Number of Houses on the property (4 houses = hotel)
		 * [12] intPlayer that owns the property
		 * */
		
		for (intRow = 0; intRow < 40; intRow ++){
			try{	
				strLine = properties.readLine();
			}
			catch(IOException e){
				System.out.println("false");
			}
			strSplit = strLine.split(",");
			for (intCol = 0; intCol < 13; intCol++){	
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
		 * [2] - Effect of the card (location/money check)
		 * [3] - Money or Location effect (cost/location)
		 * */
		for (intRow = 0; intRow < 30; intRow ++){
				try{
					strLine = chance.readLine();
				}catch(IOException e){
					System.out.println("false");
			}
				strSplit = strLine.split(",");
			for (intCol = 0; intCol < 4; intCol++){
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
		theframe = new JFrame("St. Augustine Monopoly");
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
		/** This inserts an image into the main menu */
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
		characterspanel.gameplay.setEnabled(false); 
		
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
		monopolypanel.add(monopolypanel.house);
		monopolypanel.add(monopolypanel.hotel);
		
		// Game play - ACTION LISTENER
		monopolypanel.rolldie.addActionListener(this);
		monopolypanel.next.addActionListener(this);
		monopolypanel.back.addActionListener(this);
		monopolypanel.buy.addActionListener(this);
		monopolypanel.dontbuy.addActionListener(this);
		monopolypanel.house.addActionListener(this);
		monopolypanel.hotel.addActionListener(this);
		monopolypanel.buy.setEnabled(false);
		monopolypanel.dontbuy.setEnabled(false);
		monopolypanel.house.setEnabled(false);
		monopolypanel.hotel.setEnabled(false);
		monopolypanel.next.setEnabled(false);
		monopolypanel.back.setEnabled(false);
		
		// timer: 
		//Timer plays throughout the whole entirety of the game 
		thetimer = new Timer(1000/60, this);
		thetimer.start(); 
		
		//set frame
		theframe.setContentPane(thepanel);
		theframe.pack();
		theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		theframe.setVisible(true);
		// prevents windows from being resized
		theframe.setResizable(false); 	
	
	}
	
	
	/**This method actually executes the code and displays the program on screen from main menu
	 * @param String [] args*/
	public static void main(String [] args){
		new mainmenu(); 
	}
}
