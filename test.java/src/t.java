import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;

public class t {
	public static void main(String[] args) {   
	   	Diagram dia=new Diagram();  
	}  
}
class Diagram extends JFrame implements ActionListener {  
	JMenuBar menu=new JMenuBar();  
	JMenu file=new JMenu("File");  
	JMenuItem start=new JMenuItem("Start");  
	Container con=getContentPane();  
	double [] DATA = new double[1001];
	Diagram() {   
		super("Program");   
		this.setBounds(500,200,400,250);   
		setJMenuBar(menu);   
		menu.add(file);
		file.add(start);      
		start.addActionListener(this);  
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);   
		this.setVisible(true);    
	}     
	public void actionPerformed(ActionEvent e) {   
		// TODO Auto-generated method stub   
		if(e.getSource()==start) {    
			con.repaint();     
			choice();    
			paintComponents(getGraphics());    
		}
	}     
	public void paintComponents(Graphics g) {  
		String s=JOptionPane.showInputDialog(null, "输入点数", "生成散点图", JOptionPane.QUESTION_MESSAGE);
	  	g.setColor(Color.RED);     
	   	g.drawLine(55, 80, 55, 180);    
	   	g.drawLine(55, 180, 355, 180);    
	   	g.drawString("0", 45, 180);    
	   	g.drawString("800", 345, 200);    
	   	g.drawString("10000", 12, 80);    
	   	int i=0;     
	   	while(i<DATA.length) {         
	   		i=i+1;      
	   		g.setColor(Color.BLUE);      
	   		g.drawLine(i+55,(int)(180-DATA[i]),i+55,(int)(180-DATA[i]));    
		}     
	}   
	void choice() {
		int t=0;
		int z=5;
		int w=10;
		int Y=6000;
		//int Y=60;
		double R1,R2,D;
		double G=10000.00;
		//double G=100.00;
		double I=1000.00;
		//double I=10.00;
		//double [] DATA = new double[1001];
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
			DATA[t]=I/100;
		}
	}
}
