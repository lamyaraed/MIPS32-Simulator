package GUI_Package;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;

import MIPS_Package.I_TypeInstruction;
import MIPS_Package.MIPSInstruction;
import MIPS_Package.R_TypeInstRuction;

import javax.swing.JLabel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Label;
import java.awt.Font;
import java.awt.Panel;
import java.awt.Color;
import javax.swing.JScrollPane;

public class MainFrame extends JFrame 
{
	MIPSRegisters registers = new MIPSRegisters();
	MainMemory memory = new MainMemory();
	InstructionConsole console = new InstructionConsole(); 
	CurrentInstructionInfo currInstructionInfo = new CurrentInstructionInfo();
	int PC;
	
	private final JTabbedPane ConsolTab = new JTabbedPane(JTabbedPane.TOP);
	
	public MainFrame(){
		PC = 0;
		
		setResizable(false);
		setTitle("MIPS-32 Simulator");
		getContentPane().setLayout(null);
		
		JTabbedPane MemRegTabs = new JTabbedPane(JTabbedPane.TOP);
		MemRegTabs.setFont(new Font("Tahoma", Font.BOLD, 11));
		MemRegTabs.setBounds(22, 9, 216, 316);
		getContentPane().add(MemRegTabs);
		MemRegTabs.addTab("memory", null, memory, null);
		MemRegTabs.addTab("regiser", null, registers, null);
			
		ConsolTab.setBounds(266, 22, 484, 193);
		getContentPane().add(ConsolTab);
		ConsolTab.addTab("MIPS Console", null, console, null);
		
		
		JTabbedPane CurrInstTab = new JTabbedPane(JTabbedPane.TOP);
		CurrInstTab.setBounds(266, 275, 484, 110);
		getContentPane().add(CurrInstTab);
		
		JPanel CurrentInstruction = new JPanel();
		CurrInstTab.addTab("Current Assembly Code", null, CurrentInstruction, null);
		
		CurrInstTab.addTab("Details", null, currInstructionInfo, null);
		
		JButton btnExecuteAll = new JButton("Execute all");
		btnExecuteAll.setBounds(315, 226, 127, 32);
		getContentPane().add(btnExecuteAll);
		
		JButton btnExecuteNext = new JButton("Execute Next");
		btnExecuteNext.setBounds(587, 226, 127, 32);
		getContentPane().add(btnExecuteNext);
		
		JButton btnExecuteExternalFile = new JButton("Execute External File");
		btnExecuteExternalFile.setBounds(340, 396, 184, 32);
		getContentPane().add(btnExecuteExternalFile);
		
		TextField FileNameTxtBox = new TextField();
		FileNameTxtBox.setBounds(557, 396, 157, 33);
		getContentPane().add(FileNameTxtBox);
		
		Label PC = new Label("PC :");
		PC.setFont(new Font("Dialog", Font.BOLD, 12));
		PC.setBounds(32, 345, 37, 23);
		getContentPane().add(PC);
		
		JButton PCValue = new JButton("0");
		PCValue.setEnabled(false);
		PCValue.setForeground(Color.BLACK);
		PCValue.setBackground(Color.WHITE);
		PCValue.setBounds(75, 336, 96, 32);
		getContentPane().add(PCValue);
		
		setSize(800, 480);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
		
		btnExecuteAll.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String FileName = console.ReadInstructions();
				System.out.println(FileName);
				// send the file to parser
				// if valid
				// send the file to assembler and get array of machine instruction
				// send the machine instruction to VM to execute it
				// get the updated memory , reg , pc from VM 
				// update the memory and reg tabs and PC Counter
			}
		});
		
		btnExecuteNext.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btnExecuteExternalFile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String FileName = FileNameTxtBox.getText();
				// send the file to parser
				// if valid
				// send the file to assembler and get array of machine instruction
				// send the machine instruction to VM to execute it
				// get the updated memory , reg , pc from VM 
				// update the memory and reg tabs and PC Counter
			}
		});
	}
	
}
