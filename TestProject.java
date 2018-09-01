import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;



//Main Class
public class TestProject {

	public static void main(String[] args)
	{
		//Creating Frame for EMS Project the main frame
		
		JFrame mainFrame = new MainFrame();
		
		mainFrame.setVisible(true);
		
		mainFrame.setDefaultCloseOperation(mainFrame.EXIT_ON_CLOSE);

	}

}

//MainFrame class main panel will be made and attached to it
class MainFrame extends JFrame
{
	private JFrame main;
	public MainFrame()
	{	
		
		
		//storing this.mainFrame in mainFrame
		main = this;
		
		//Setting size of the frame
		setSize(600,400);

		//Setting title of the frame
		setTitle("EMPLOYEE MANAGEMENT SYSTEM");
		
		//Main panel
		mainPanel = new JPanel();
	
		//creating grid layout
		GridLayout g = new GridLayout(6,1);
		
		//setting panel layout grid
		mainPanel.setLayout(g); 
		
		//adding panel to frame
		add(mainPanel);
		
		//panel for select button
		JPanel selectPanel = new JPanel();
		
		// adding selectPanel to frame
		add(selectPanel , BorderLayout.SOUTH);
		
		//creating button Select
		JButton btn = new JButton("SELECT");
		
		//adding btn to panel
			selectPanel.add(btn);
			
			
		//creating array of radio btns
			JRadioButton[] rbtn = new JRadioButton[6];
			
			//creating name array
			String[] bName = {"ADD ITEM" , "DISPLAY DATA" , "QUERY DATA" , "EDIT DATA" , "DELETE DATA" , "QUIT"};
		
			//Creating ButtonGroup
			ButtonGroup b = new ButtonGroup();
			
			
			//setting radioButtons
			for(int i =0;i<rbtn.length;i++)
			{
				rbtn[i]= makeButton( b , bName[i]);
			}
			//Random Access file pointer
		try 
		{
					io = new RandomAccessFile("emp.dat","rw");
		
			//action listener function for btn class
				btn.addActionListener(new ActionListener() 
			{
			
			public void actionPerformed(ActionEvent evt) 
			{	// UNIT TESING CODE
				if(rbtn[0].isSelected())
				{
					main.setVisible(false);
					AddItem add = new AddItem(main);
				}
				if(rbtn[1].isSelected())
				{
					main.setVisible(false);
					
					try {
						io.seek(0);
						DisplayItem display = new DisplayItem(main);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(rbtn[2].isSelected())
				{
					main.setVisible(false);
					QueryItem display = new QueryItem(main);
				}
				if(rbtn[3].isSelected())
				{
					main.setVisible(false);
					EditItem display = new EditItem(main);
				}
				if(rbtn[4].isSelected())
				{	
					main.setVisible(false);
					DeleteItem display = new DeleteItem(main);
				}
				if(rbtn[5].isSelected())
				{
					main.dispose();
					try {
						io.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} 
				
			}	
				});
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		
	}
	
	public JRadioButton makeButton( ButtonGroup g , String name )
	{	//creating rbtn
		JRadioButton rbtn = new JRadioButton(name);
		
		//Adding button to buttongroup
		g.add(rbtn);
		
		//adding rbtn to panel
		mainPanel.add(rbtn);
		
		return rbtn;
	}

	private JPanel mainPanel;
	public static RandomAccessFile io;
	
}

//Employee Class 
class Employee
{	
	//Funtions for assinging value
		public void setEmpId(int i)
	{
		empId = i;
	}

	public void setEmpSal(double s)
	{
		empSal = s;
	}

	public void setEmpName(String n)
	{
		 empName =n;
	}

	public void setEmpDes(String j)
	{
		empDesignation = j;
	}

	public void setEmpGen(String g)
	{
		empGender = g;
	}
	
	//Funtions for returning value to object
	public int getEmpId()
	{
		return empId;
	}
	
	public double getEmpSal()
	{
		return empSal;
	}
	
	public String getEmpName()
	{
		return empName;
	}
	
	public String getEmpDes()
	{
		return empDesignation;
	}
	
	public String getEmpGen()
	{
		return empGender;
	}
	
	//file methods s
	public void reWrite(long i) throws IOException
	{
		MainFrame.io.seek(i);
		MainFrame.io.writeInt(empId);
		writeString(MainFrame.io,empName);
		MainFrame.io.writeDouble(empSal);
		writeString(MainFrame.io,empGender);
		writeString(MainFrame.io,empDesignation);
	}
	public void writeFile(RandomAccessFile io) throws IOException
	{
		io.writeInt(empId);
		writeString(io,empName);
		io.writeDouble(empSal);
		writeString(io,empGender);
		writeString(io,empDesignation);
	}
	public void writeString(RandomAccessFile io,String name) throws IOException
	{
		for(int i=0;i<NAMESIZE;i++)
		{
			char ch=0;	
			if(i<name.length())
			{
				ch=name.charAt(i);		
			}
			io.writeChar(ch);
		}
	}
	
	
	public void readFile(RandomAccessFile io, long i) throws IOException
	{
		
		io.seek(i);
		empId=io.readInt();
		empName=readFixedString(io);
		empSal=io.readDouble();
		empGender=readFixedString(io);
		empDesignation=readFixedString(io);
		
	}

	public String readFixedString(RandomAccessFile io) throws IOException
	{	
		String str="";
		for(int i=0;i<NAMESIZE;i++)
		{
			char ch=io.readChar();	
			if(ch!=0)
			{
				str=str+ch;		
			}
		}
		return str;
	}
	
	public int readEmpId(RandomAccessFile io, long i) throws IOException
	{   i=i*132;
		io.seek(i);
		empId=io.readInt();
		return empId;
		
	}
	public void setCurrentPos(int c)
	{
		currentPos = c;
	}
	public int getCurrentPos()
	{
		return currentPos;
	}
	
	private int empId;
	private String empName;
	private double empSal;
	private String empDesignation;
	private String empGender;
	public static final int NAMESIZE = 20;
	public static final int SIZE = 132;
	private int currentPos;
	

}







