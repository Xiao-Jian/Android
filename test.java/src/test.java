import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class test
{
	public static void main(String[] args) 
	{
		//System.out.println("t        (I) ");
		int t=0;
		int z=5;
		int w=10;
		int Y=6000;
		double R1,R2,D;
		double G=10000.00;
		double I=1000.00;
		double [] DATA = new double[1001];
		D=Y-I;
		R1=D/z;
		R2=G/w;
		//System.out.println(t+"        "+I);
		DATA[0]=I;
		while(t<1000)
		{
			t=t+1;
			I=I+R2;
			G=G+R1-R2;
			D=Y-I;
			R1=D/z;
			R2=G/w;
			//System.out.println(t+"        "+I);
			DATA[t]=I;
		}
		Graphics g = null;
		g.setColor(Color.RED);     
	   	g.drawLine(30, 70, 30, 370);    
	   	g.drawLine(30, 370, 330, 370);    
	   	g.drawString("0", 15, 380);    
	   	g.drawString("300", 325, 385);    
	   	g.drawString("300", 8, 65);    
	   	double x,y;
	   	int i=0;     
	   	while(i<DATA.length) {  
	   		g.setColor(Color.BLUE);      
	   		g.drawString("*", t+30, (int) (400-DATA[i]));  
	        i=i+1;      
		} 
	}
}

class Diagram extends JFrame {
	Diagram() {   
		super("ProgrammingV1.1");   
		this.setBounds(500,200,400,400);  
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);   
		this.setVisible(true);  
	} 
	public void paintComponents(Graphics g) {   
		g.setColor(Color.RED);     
	   	g.drawLine(30, 70, 30, 370);    
	   	g.drawLine(30, 370, 330, 370);    
	   	g.drawString("0", 15, 380);    
	   	g.drawString("300", 325, 385);    
	   	g.drawString("300", 8, 65);    
	   	int x,y,i=0;     
	   	while(i<DATA.length) {      
	   		i=i+1;      
	   		g.setColor(Color.BLUE);      
	   		g.drawString("*", i+30, (int) (400-DATA[i]));    
		}  
	}    
}