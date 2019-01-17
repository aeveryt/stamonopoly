//Properties File 
import java.io.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.Font.*;

public class stamonopolyproperties{
		public static void main (String[] args){
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
	

		//reading the Properties file
		try{
			thefile = new FileReader("properties.csv");
			communityfile = new FileReader("community.csv");
			//chancefile = new FileReader("chance.csv");
		}catch(FileNotFoundException e){
			System.out.println("Unable to read File");
			blnFileFail = true;
		}
	
		properties = new BufferedReader(thefile);
		community = new BufferedReader(communityfile);
		
		
		String strLine = "";
		int intRow;
		int intCol;
		
		
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
			}
		}
		
		//Closing file
		
		try{
			thefile.close();
			communityfile.close();
		
		}catch(IOException e){
			System.out.println("CANNOT CLOSE FILE");
		}
			
	}
}
