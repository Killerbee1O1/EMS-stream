
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

public class EditItem {

	public EditItem(JFrame frame)
	{
		
		
		//creating frame for display option
		JFrame editFrame = new EditFrame(frame);
		editFrame.setVisible(true);
		
	}
	
	
}


class EditFrame extends JFrame
{
	public EditFrame(JFrame frame)
	{
		
		setSize(600 , 400);
		//Setting title of the frame
		setTitle("EMPLOYEE MANAGEMENT SYSTEM");
		JFrame f = this;
		JPanel editPanel = new EditPanel();
		JPanel btn = new EditBtnPanel(editPanel,frame,f);
		
		add(btn , BorderLayout.NORTH);
		add(editPanel , BorderLayout.CENTER);
		
	}
}

class EditBtnPanel extends JPanel
{
	public EditBtnPanel(JPanel ob ,JFrame frame,JFrame f)
	{	
		panel = (EditPanel)ob;
		
		JLabel label = new JLabel("ENTER ID");
		JTextField jId = new JTextField(5);
		JButton disBtn = new JButton("SHOW");
		JButton edit = new JButton("EDIT");
		JButton save = new JButton("SAVE");
		JButton exit = new JButton("EXIT");
		
		add(label);
		add(jId);
		add(disBtn);
		add(edit);
		add(save);
		add(exit);
		
		exit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent evt)
			{
				f.dispose();
				frame.setVisible(true);
				
			}
			
		});
		
		edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt)
			{
				panel.setEditable();
			}
			
		});
		
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt)
			{
				panel.notEditable();
				try {
					panel.save(ep);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
					//searchig in the arraylist
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
							ep=e;
							try {
								e.readFile(MainFrame.io,(i*132));
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							panel.setID(Integer.toString(e.getEmpId()));
							panel.setName(e.getEmpName());
							panel.setSal(Double.toString(e.getEmpSal()));
							panel.setGender(e.getEmpGen());
							panel.setDesc(e.getEmpDes());
							e.setCurrentPos(i*132);
						}
					
				}
				
				
			}
			
		});
		
		
		
		
	}

	private Employee ep;
	private EditPanel panel;
	private String Id;
	private int check; 
	private int l ;
}


class EditPanel extends JPanel
{
	public EditPanel()
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
	
	public void setEditable()
	{
		EmpD.setEditable(true);
		EmpS.setEditable(true);
		EmpID.setEditable(true);
		EmpNM.setEditable(true);
		EmpG.setEditable(true);
	}
	public void notEditable()
	{
		EmpD.setEditable(false);
		EmpS.setEditable(false);
		EmpID.setEditable(false);
		EmpNM.setEditable(false);
		EmpG.setEditable(false);
	}
	
	public void save(Employee e) throws IOException
	{

		e.setEmpDes(EmpD.getText());
		e.setEmpSal(Double.parseDouble(EmpS.getText()));
		e.setEmpId(Integer.parseInt(EmpID.getText()));
		e.setEmpName(EmpNM.getText());
		e.setEmpGen(EmpG.getText());
		int i = e.getCurrentPos();
		e.reWrite(i);
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


