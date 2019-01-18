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
	BufferedImage page3;
	BufferedImage page4;
	BufferedImage page5;
	public JButton backtomain;
	public JButton topage2;
	public JButton backtopage1;
	public JButton topage3;
	public JButton topage4;
	public JButton backtopage2;
	public JButton backtopage3;
	public JButton topage5;
	public JButton backtopage4;
	public JButton tomain5;
	
	boolean blnpage1 = true;
	boolean blnpage2 = false;
	boolean blnpage3 = false;
	boolean blnpage4 = false;
	boolean blnpage5 = false;
	
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
		else if(blnpage2){
			g.setColor(Color.BLACK);
			g.fillRect(0,0,1280,720);
			g.drawImage(page2,0,0,null);
		}
		else if(blnpage3){
			g.setColor(Color.BLACK);
			g.fillRect(0,0,1280,720);
			g.drawImage(page3,0,0,null);
		}
		else if(blnpage4){
			g.setColor(Color.BLACK);
			g.fillRect(0,0,1280,720);
			g.drawImage(page4,0,0,null);
		}
		else if(blnpage5){
			g.setColor(Color.BLACK);
			g.fillRect(0,0,1280,720);
			g.drawImage(page5,0,0,null);
		}
	}
	
	//Constructor
	public monopolyinstructions(){
		//from JPanel
		super();
		
		//-INSTRUCTIONS PAGE 1
		//- back to main menu button
		backtomain = new JButton("BACK TO MAIN MENU");
		backtomain.setSize(150,35);
		backtomain.setLocation(15,25);
		
		//- to page 2
		topage2 = new JButton("NEXT PAGE");
		topage2.setSize(150,35);
		topage2.setLocation(1100,25);
		
		//-INSTRUCTION PAGE 2
		//-back to page 1
		backtopage1 = new JButton("BACK TO PAGE 1");
		backtopage1.setSize(150,35);
		backtopage1.setLocation(15,25);
		
		//- to page 3
		topage3 = new JButton("NEXT PAGE");
		topage3.setSize(150,35);
		topage3.setLocation(1100,25);
		
		//- INSTRUCTION PAGE 3
		//-back to page 2
		backtopage2 = new JButton("BACK TO PAGE 2");
		backtopage2.setSize(150,35);
		backtopage2.setLocation(15,25);
		
		//-to page 4
		topage4 = new JButton("NEXT PAGE");
		topage4.setSize(150,35);
		topage4.setLocation(1100,25);
		
		//INSTRUCTIONS PAGE 4
		//-back to page 3
		backtopage3 = new JButton("BACK TO PAGE 3");
		backtopage3.setSize(150,35);
		backtopage3.setLocation(15,25);
		//-to page 5
		topage5 = new JButton("NEXT PAGE");
		topage5.setSize(150,35);
		topage5.setLocation(1100,25);
		
		//-INSTRUCTION PAGE 5
		//-back to page 4
		backtopage4 = new JButton("BACK TO PAGE 4");
		backtopage4.setSize(150,35);
		backtopage4.setLocation(15,25);
		//-to main menu from page 5
		tomain5 = new JButton ("TO MAIN MENU");
		tomain5.setSize(150,35);
		tomain5.setLocation(1100,25);
		
		
		//checking whether or not image or font is available
		try{
			page1 = ImageIO.read(new File("instructions.jpg"));
			page2 = ImageIO.read(new File("instructions2.jpg"));
			page3 = ImageIO.read(new File("instructions3.jpg"));
			page4 = ImageIO.read(new File("instructions4.jpg"));
			page5 = ImageIO.read(new File("instructions5.jpg"));
			title = new Font("kabel.ttf", Font.PLAIN, 12);
		}
		catch(IOException e){
			System.out.println("Unable to find image/font.");
		}
	}
}
