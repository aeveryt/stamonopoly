import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.awt.Font;

public class stamonopolycharacters extends JPanel{
	
	//Properties
	Font title;
	BufferedImage student1;
	BufferedImage student2;
	BufferedImage student3;
	BufferedImage student4;
	public JButton select1 = new JButton("Select");
	public JButton select2 = new JButton("Select");
	public JButton select3 = new JButton("Select");
	public JButton select4 = new JButton("Select");
	
	boolean blnsta1;
	boolean blnsta2;
	boolean blnsta3;
	boolean blnsta4;
	
	//Methods
	public void paintComponent(Graphics g){
		g.setFont(title);
		g.setColor(Color.BLACK);
		g.fillRect(0,0,1280,720);
		
	}
	
	
	//Constructors
	public stamonopolycharacters(){
		super();
		
		//Select 1;
		select1.setSize(100,50);
		select1.setLocation(120,430);
		
		//Select 2;
		select2.setSize(100,50);
		select2.setLocation(440,430);
		
		//Select 3;
		select3.setSize(100,50);
		select3.setLocation(780,430);
		
		//Select 4
		select4.setSize(100,50);
		select4.setLocation(1100,430);
		
		//Reading Image
		
		try{
			student1 = ImageIO.read(new File("student1.jpg"));
			title = new Font("kabel.ttf", Font.PLAIN, 12);
		}catch(IOException e){
			System.out.println("unable to load image ");
		}
	}

}
