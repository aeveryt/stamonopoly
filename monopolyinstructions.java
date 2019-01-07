import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.awt.Font;

public class monopolyinstructions extends JPanel{
	//Properties
	Font title;
	
	//-initialize

	//Methods
	public void paintComponent(Graphics g){
		//g.loadFont("kabel.ttf",12);
		g.setFont(title);
		g.setColor(Color.BLACK);
		g.fillRect(0,0,1280,720);
	}
	
	//Constructor
	public monopolyinstructions(){
		//from JPanel
		super();
	}
}
