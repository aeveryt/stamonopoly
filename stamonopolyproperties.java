//Properties File 
// Change Arc to one used in Exception....

//Fix CSV File 

import arc.*;
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
	BufferedReader properties = null;
	
	//Split Data
	String strSplit[];
	String strProperties[][] = new String[31][9];
		
	
	//Methods
	

		//reading the file 
		try{
			thefile = new FileReader("properties.csv");
		}catch(FileNotFoundException e){
			System.out.println("Unable to read File");
			blnFileFail = true;
		}
		
		
		properties = new BufferedReader(thefile);
		
		String strLine = "";
		int intRow;
		int intCol;
		
			for (intRow = 0; intRow < 31; intRow ++){
				try{	
					strLine = properties.readLine();
				}catch(IOException e){
					System.out.println("false");
			}
				strSplit = strLine.split(",");
			for (intCol = 0; intCol < 9; intCol++){
			
				strProperties[intRow][intCol] = strSplit[intCol];
			
				System.out.println(strProperties[intRow][intCol]);
			}
		}
	}
}
