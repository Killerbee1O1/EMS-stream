
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;


import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class DeleteItem {

	public DeleteItem(JFrame frame)
	{
		
		//creating frame for display option
		JFrame deleteFrame = new DeleteFrame(frame);
		deleteFrame.setVisible(true);
		
	}
	
	
}


class DeleteFrame extends JFrame
{
	public DeleteFrame(JFrame frame)
	{
		
		JFrame f = this;
		
		setSize(600 , 400);
		//Setting title of the frame
		setTitle("EMPLOYEE MANAGEMENT SYSTEM");
		
		JPanel deletePanel = new DeletePanel();
		JPanel btn = new DeleteBtnPanel(deletePanel ,frame,f);
		
		add(btn , BorderLayout.NORTH);
		add(deletePanel , BorderLayout.CENTER);
		
	}

}

class DeleteBtnPanel extends JPanel
{
	public DeleteBtnPanel(JPanel ob,JFrame frame,JFrame f)
	{	
		panel = (DeletePanel)ob;
		
		JLabel label = new JLabel("ENTER ID");
		jId = new JTextField(5);
		JButton disBtn = new JButton("SHOW");
		JButton delBtn = new JButton("DELETE");
		add(label);
		add(jId);
		add(disBtn);
		add(delBtn);
		JButton exit = new JButton("EXIT");
		add(exit);
		exit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent evt)
			{
				f.dispose();
				frame.setVisible(true);
				
			}
			
		});
		
		
		disBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent evt)
			{
				//received the id
				Id = jId.getText();
				
				 try {
						l = (int)MainFrame.io.length();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					int count = l/Employee.SIZE;
					//searchig in the file
					for(int i =0 ; i<count; i++)
					{
						e = new Employee();
						
						try {
							e.readEmpId(MainFrame.io,i);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						String temp = Integer.toString(e.getEmpId());
						if(temp.equals(Id))
						{	e.setCurrentPos(i*132);	
							try {
								e.readFile(MainFrame.io,(i*132));
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							//sending data from employee to fields
							panel.setID(Integer.toString(e.getEmpId()));
							panel.setName(e.getEmpName());
							panel.setGender(e.getEmpGen());
							panel.setSal(Double.toString(e.getEmpSal()));
							panel.setDesc(e.getEmpDes());
		
						}
						
					}
					
					
				
				
				
			}
			
		});
		
		//delbtn action listener
		delBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt)
			{
				deleteRecord();
				panel.setID(" ");
				panel.setName(" ");
				panel.setGender(" ");
				panel.setSal(" ");
				panel.setDesc(" ");
			}
		});
	}
	
	public void deleteRecord()
	{
		try
		{
			RandomAccessFile io=MainFrame.io;
			int recCount=((int)io.length())/Employee.SIZE;
			File temp=new File("temp.dat");
			RandomAccessFile io1=new RandomAccessFile(temp,"rw");
			Employee e=new Employee();
			for(int i=1;i<=recCount;i++)
			{
				e.readFile(io,(i-1)*Employee.SIZE);
				if(e.getEmpId()!=Integer.parseInt(Id))
				{
					e.writeFile(io1);
				}
				
			}
			
			io.close();
			io1.close();
		    (new File("emp.dat")).delete();
		    temp.renameTo(new File("emp.dat"));
		    MainFrame.io=new RandomAccessFile("emp.dat","rw");
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}

	private Employee e;
	private DeletePanel panel;
	private String Id;
	private int check;
	private int l;
	private JTextField jId;
}


class DeletePanel extends JPanel
{
	public DeletePanel()
	{
		
		//setting layout
				GridLayout g = new GridLayout(5,2);
				this.setLayout(g);
				
				//adding label and textarea
				JLabel EmpId = new JLabel("EMP ID:");
				EmpID = new JTextField(10);
				add(EmpId);
				add(EmpID);
				
				
				JLabel EmpNm = new JLabel("EMP NAME:");
				EmpNM = new JTextField(10);
				add(EmpNm);
				add(EmpNM);
				
				
				JLabel Emps = new JLabel("EMP SAL:");
				EmpS = new JTextField(10);
				add(Emps);
				add(EmpS);
				
				
				JLabel Empg = new JLabel("EMP GENDER:");
				EmpG = new JTextField(5);
				add(Empg);
				add(EmpG);
				
				
				JLabel Empd = new JLabel("EMP DESC:");
				EmpD = new JTextField(10);
				add(Empd);
				add(EmpD);
				
		
	}

	public void setID(String Id)
	{
		EmpID.setEditable(true);
		id = Id;
		EmpID.setText(id);
		EmpID.setEditable(false);
	}
	
	public void setName(String n)
	{
		EmpNM.setEditable(true);
		name = n;
		EmpNM.setText(name);
		EmpNM.setEditable(false);
	}
	
	public void setGender(String g)
	{
		EmpG.setEditable(true);
		gender = g;
		EmpG.setText(gender);
		EmpG.setEditable(false);
	}
	
	public void setSal(String s)
	{
		EmpS.setEditable(true);
		sal = s;
		EmpS.setText(sal);
		EmpS.setEditable(false);
	}
	
	public void setDesc(String d)
	{
		EmpD.setEditable(true);
		desc = d;
		EmpD.setText(desc);
		EmpD.setEditable(false);
	}
	
	private String id;
	private String name;
	private String gender;
	private String desc;
	private String sal;
	private JTextField EmpID;
	private JTextField EmpNM;
	private JTextField EmpG;
	private JTextField EmpS;
	private JTextField EmpD;
	
}
