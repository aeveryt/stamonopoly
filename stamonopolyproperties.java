//Properties File 
// Change Arc to one used in Exception....
import arc.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.Font.*;

public class stamonopolyproperties{
	//Properties
	
	boolean GoSpace = true;
	boolean Geo = false;
	
	//Methods
	
	//Constructors
	public stamonopolyproperties(boolean blnGo,boolean blnGeo){
		this.GoSpace = blnGo;
		this.Geo =blnGeo;
		
	}
	public static void main (String[] args){

		
		//Reading textfile
		TextInputFile properties = new TextInputFile("properties.csv");
		
		//read Line
		String strLine;
		
		//Split Data
		String strSplit[];
		String strProperties[][];
		
		strProperties = new String [31][8];
		
		//Reading data
		int intRow;
		int intCol;
		
		for(intRow =0; intRow < 31; intRow++){
			strLine = properties.readLine();
			strSplit = strLine.split(",");
			for(intCol = 0; intCol < 8; intCol++){
				strProperties[intRow][intCol] = strSplit[intCol];
			}
			
		}
		
		//Column Categories
		/* [0] Subject
		 * [1] Teacher
		 * [2] Rent
		 * [3] Rent with 1 hosue
		 * [4] Rent with 2 houses
		 * [5] Rent with otel 
		 * [6] Mortgage
		 * [7] Boolean (if you landed on it)
		 * */
		for(intRow = 0; intRow < 31; intRow++){
			for(intCol = 0; intCol < 8; intCol++){
				System.out.println(strProperties[intRow][intCol]);
		}
	}
}
}
