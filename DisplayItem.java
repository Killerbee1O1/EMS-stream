
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.swing.*;

public class DisplayItem
{
	public DisplayItem(JFrame frame) throws IOException
	{
		JFrame display = new DisplayFrame(frame);
		display.setVisible(true);
	}

	
}

class DisplayFrame extends JFrame
{
	public DisplayFrame(JFrame frame) throws IOException
	{
		JFrame f = this;
		//seting size
		setSize(600 , 600);
		//Setting title of the frame
		setTitle("EMPLOYEE MANAGEMENT SYSTEM");
		
		
		//creating a panel to add 
		JPanel displayPanel = new DisplayPanel();
		
		//adding panel
		add(displayPanel , BorderLayout.NORTH);
		
		//add a button exit to return to main frame
		JButton exit = new JButton("EXIT");
		add(exit,BorderLayout.SOUTH);
		//add exit button action listener
		exit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent evt)
			{
				f.dispose();
				frame.setVisible(true);
				
			}
			
		});
	}
	
}


class DisplayPanel extends JPanel
{
	public DisplayPanel() throws IOException
	{
		that = this;
		
		int count=(int)(MainFrame.io.length()/Employee.SIZE);
		//setting grid layout
		GridLayout g = new GridLayout((count+1),5);
		this.setLayout(g);
		
		//making a table
	
		
		JLabel EmpId = new JLabel("EMP ID:");
		add(EmpId);
		
		
		JLabel EmpNm = new JLabel("EMP NAME:");
		add(EmpNm);
		
		
		JLabel Emps = new JLabel("EMP SAL:");
		add(Emps);
		
		
		JLabel Empg = new JLabel("EMP GENDER:");
		add(Empg);
		
		
		JLabel Empd = new JLabel("EMP DESC:");
		add(Empd);
		
		int j = 1;
		Employee e = new Employee();
		MainFrame.io.seek(0);
		//adding the data
		for(int i=0;i<count; i++)
		{
			//adding
			long  go = i*Employee.SIZE;
			e.readFile(MainFrame.io,go);
			
			String Id =Integer.toString(e.getEmpId());
			JLabel tEmpId = new JLabel(Id);
			that.add(tEmpId);
			
			
			JLabel tEmpNm = new JLabel(e.getEmpName());
			that.add(tEmpNm);
			
			String sal = Double.toString(e.getEmpSal());
			JLabel tEmps = new JLabel(sal);
			that.add(tEmps);
			
			
			JLabel tEmpg = new JLabel(e.getEmpGen());
			that.add(tEmpg);
			
			
			JLabel tEmpd = new JLabel(e.getEmpDes());
			that.add(tEmpd);
			
			
		}
		
	}
	private DisplayPanel that;
}

