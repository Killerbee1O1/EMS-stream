import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.io.*;
import java.text.NumberFormat;

//creating class AddItem
class AddItem
{	
	//constructor with parameter to receive arraylist
	public AddItem(JFrame frame)
	{	
		
		//creating frame for add item selection
		JFrame addItemFrame = new AddItemFrame(frame);
		addItemFrame.setVisible(true);
		
	}

}


//class AddItemFrame 
class AddItemFrame extends JFrame
{
	public AddItemFrame(JFrame frame)
	{
		setSize(600,400);
		//Setting title of the frame
		setTitle("EMPLOYEE MANAGEMENT SYSTEM");
		
		//panel for text area
		JPanel panel = new TextPanel();
		
		//panel for buttons
		JPanel btnPanel = new AddButtonPanel( panel ,frame ,this);
		
		//adding panels to frame
		add(panel , BorderLayout.CENTER);
		add(btnPanel , BorderLayout.SOUTH);
	
	}
	

}

class AddButtonPanel extends JPanel
{	
	public AddButtonPanel(JPanel panel2 ,JFrame frame,JFrame f)
	{
		//textPanel object
		panel = (TextPanel)panel2;
		//downcasting panel
		
		//making buttons
		JButton New = new JButton("NEW");
		JButton Save = new JButton("SAVE");
		JButton Exit = new JButton("EXIT");
		
		//adding buttons to panel
		add(New);
		add(Save);
		add(Exit);
	
		//enabling and disablling button
		boolean b1 = true;
		boolean b2 = false;
		
		Save.setEnabled(b1);
		New.setEnabled(b2);
		
		//Save button action :
		Save.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) 
			{
				Employee e = new Employee();
				panel.editable(false);
				panel.saveField();
				//setting add to true and save to disable
				Save.setEnabled(b2);
				New.setEnabled(b1);
				
			}
				
		});

		//Add button listner
		New.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt)
			{
				panel.editable(true);
				panel.clearField();
				Save.setEnabled(b1);
				New.setEnabled(b2);	
			}
			
		});
		//Exit
		Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt)
			{
				f.dispose();
				frame.setVisible(true);
				
			}
			
		}); 
		
	}
	

	private TextPanel panel;
}

class TextPanel extends JPanel
{
	public TextPanel()
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
			//storing inputed text to a variable
		
		
	}
	
	public void saveField() 
	{
		Employee e=new Employee();
		e.setEmpId(Integer.parseInt(EmpID.getText()));
		e.setEmpName(EmpNM.getText());
		e.setEmpSal(Double.parseDouble(EmpS.getText()));
		e.setEmpGen(EmpG.getText());
		e.setEmpDes(EmpD.getText());
		try
		{
			MainFrame.io.seek(MainFrame.io.length());
			e.writeFile(MainFrame.io);
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}
	
	public void clearField()
	{
		EmpID.setText(" ");
		EmpNM.setText(" ");
		EmpS.setText(" ");
		EmpG.setText(" ");
		EmpD.setText(" ");
		
	}
	public void editable(boolean tf)
	{
		EmpID.setEditable(tf);
		EmpNM.setEditable(tf);
		EmpS.setEditable(tf);
		EmpG.setEditable(tf);
		EmpD.setEditable(tf);
		
	}
	
		//creating a function which would give us inputed text
		public int getEmpId()
		{
			return tempId;
		}
		public String getEmpName()
		{
			return tempName;
		}
		public String getEmpSal()
		{
			return tempSal;
		}
		public String getEmpG()
		{
			return tempG;
		}
		public String getEmpD()
		{
			return tempD;
		}

		private int tempId;
		private String tempName;
		private String tempSal;
		private String tempG;
		private String tempD;
		private JTextField EmpID;
		private JTextField EmpNM;
		private JTextField EmpS;
		private JTextField EmpG;
		private JTextField EmpD;
	
}

