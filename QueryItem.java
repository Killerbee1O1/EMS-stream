
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

public class QueryItem {

	public QueryItem(JFrame frame)
	{
		//creating frame for display option
		JFrame queryFrame = new QueryFrame(frame);
		queryFrame.setVisible(true);	
	}
	
}


class QueryFrame extends JFrame
{
	public QueryFrame(JFrame frame)
	{
	
		JFrame f = this;
		setSize(600 , 400);
		//Setting title of the frame
		setTitle("EMPLOYEE MANAGEMENT SYSTEM");
		
		JPanel queryPanel = new QueryPanel();
		JPanel btn = new QueryBtnPanel(queryPanel);
		JPanel exitPanel = new JPanel();
		
		add(exitPanel , BorderLayout.SOUTH);
		add(btn , BorderLayout.NORTH);
		add(queryPanel , BorderLayout.CENTER);
		
		JButton exitbtn = new JButton("EXIT");
		exitPanel.add(exitbtn,BorderLayout.SOUTH);
		exitbtn.addActionListener(new ActionListener(){
		
			public void actionPerformed(ActionEvent evt)
			{
					f.dispose();
					frame.setVisible(true);
			}
			
			
		});
		
		
	}



	ArrayList<Employee> list;
}

class QueryBtnPanel extends JPanel
{
	public QueryBtnPanel(JPanel ob )
	{	
		panel = (QueryPanel)ob;
		
		JLabel label = new JLabel("ENTER ID");
		JTextField jId = new JTextField(5);
		JButton disBtn = new JButton("SHOW");
		add(label);
		add(jId);
		add(disBtn);
		
		
		
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
					Employee e = new Employee();
					try {
						e.readEmpId(MainFrame.io,i);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					String temp = Integer.toString(e.getEmpId());
					if(temp.equals(Id))
					{		
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
		
		
		
		
	}

	private QueryPanel panel;
	private String Id;
	private int l;
}


class QueryPanel extends JPanel
{
	public QueryPanel()
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
		id = Id;
		EmpID.setText(id);
		EmpID.setEditable(false);
	}
	
	public void setName(String n)
	{
		name = n;
		EmpNM.setText(name);
		EmpNM.setEditable(false);
	}
	
	public void setGender(String g)
	{
		gender = g;
		EmpG.setText(gender);
		EmpG.setEditable(false);
	}
	
	public void setSal(String s)
	{
		sal = s;
		EmpS.setText(sal);
		EmpS.setEditable(false);
	}
	
	public void setDesc(String d)
	{
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


