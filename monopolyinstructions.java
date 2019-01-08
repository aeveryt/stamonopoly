import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.awt.Font;

public class monopolyinstructions extends JPanel{
	//Properties
	Font title;
	BufferedImage page1;
	
	//-initialize

	//Methods
	public void paintComponent(Graphics g){
		g.setFont(title);
		g.setColor(Color.BLACK);
		g.fillRect(0,0,1280,720);
	}
	
	//Constructor
	public monopolyinstructions(){
		//from JPanel
		super();
		try{
			page1 = ImageIO.read(new File("instructions.jpg"));
			title = new Font("kabel.ttf", Font.PLAIN, 12);
		}
		catch(IOException e){
			System.out.println("Unable to find image.");
		}
		/*try{
			
		}
		catch(IOException e){
			System.out.println("Unable to load font.");
		}*/
	}
}
