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
	public JButton gameplay = new JButton("Play"); 
	
	boolean blnsta1;
	boolean blnsta2;
	boolean blnsta3;
	boolean blnsta4;
	String strData = "";
	
	//Methods
	public void paintComponent(Graphics g){
		g.setFont(title);
		g.setColor(Color.BLACK);
		g.fillRect(0,0,1280,720);
	//Character 1
		g.setColor(Color.RED);
		g.fillRect(140,400,25,25);
	//Character 2
		g.setColor(Color.BLUE);
		g.fillRect(440,400,25,25);
	//Character 3	
		
		g.setColor(Color.YELLOW);
		g.fillRect(780,400,25,25);
	//Character 4
		g.setColor(Color.GREEN);
		g.fillRect(1100,400,25,25);
		
		if(strData.equals("select1")){
			select1.setEnabled(false); 
		}
		
		
		
	}
	
	
	//Constructors
	public stamonopolycharacters(){
		super();
		
		//Select 1;
		select1.setSize(100,50);
		select1.setLocation(100,480);
		
	
		
		//Select 2;
		select2.setSize(100,50);
		select2.setLocation(400,480);
		
		//Select 3;
		select3.setSize(100,50);
		select3.setLocation(740,480);
		
		//Select 4
		select4.setSize(100,50);
		select4.setLocation(1060,480);
		
		//Reading Image
		
		
		// gameplay
		gameplay.setSize(140,100); 
		gameplay.setLocation(1140,620); 
		
		try{
			student1 = ImageIO.read(new File("student1.jpg"));
			title = new Font("kabel.ttf", Font.PLAIN, 12);
		}catch(IOException e){
			System.out.println("unable to load image ");
		}
	}

}
