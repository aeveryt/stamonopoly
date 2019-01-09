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
	BufferedImage page2;
	public JButton backtomain;
	public JButton topage2;
	public JButton backtopage1;
	
	boolean blnpage1 = true;
	boolean blnpage2 = false;
	boolean blnpage3 = false;
	
	//-initialize

	//Methods
	public void paintComponent(Graphics g){
		g.setFont(title);
		g.setColor(Color.BLACK);
		g.fillRect(0,0,1280,720);
		
		//images
		if(blnpage1){
			g.drawImage(page1,0,0,null);
		}
		if(blnpage2){
			g.drawImage(page2,0,0,null);
		}
	}
	
	//Constructor
	public monopolyinstructions(){
		//from JPanel
		super();
		
		//-INSTRUCTIONS PAGE 1
		if(blnpage1){
			//- back to main menu button
			backtomain = new JButton("BACK TO MAIN MENU");
			backtomain.setSize(150,35);
			backtomain.setLocation(15,25);
		
			//- to page 2
			topage2 = new JButton("NEXT PAGE");
			topage2.setSize(150,35);
			topage2.setLocation(1100,25);
		
		}
		if(blnpage2){
			//-back to page 1
			backtopage1 = new JButton("BACK TO PAGE 1");
			backtopage1.setSize(150,35);
			backtopage1.setLocation(15,25);
		}
		//checking whether or not image or font is available
		try{
			page1 = ImageIO.read(new File("instructions.jpg"));
			page2 = ImageIO.read(new File("instructions2.jpg"));
			title = new Font("kabel.ttf", Font.PLAIN, 12);
		}
		catch(IOException e){
			System.out.println("Unable to find image/font.");
		}
	}
}
