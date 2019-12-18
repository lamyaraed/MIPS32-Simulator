package GUI_Package;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import MIPS_Package.Assembler;
import MIPS_Package.MIPSInstruction;
import MIPS_Package.MIPS_VirtualMachine;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Label;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;

public class MainFrame extends JFrame 
{
	MIPSRegisters registers = new MIPSRegisters();
	MainMemory memory = new MainMemory();
	InstructionConsole console = new InstructionConsole(); 
	CurrentInstructionInfo currInstructionInfo = new CurrentInstructionInfo();
	
	ArrayList<MIPSInstruction> mipsInstructions;
	Assembler assembler = new Assembler();
	MIPS_VirtualMachine virtualMachine = new MIPS_VirtualMachine(registers.getRegisters(), memory.getMemory());
	
	private final JTabbedPane ConsolTab = new JTabbedPane(JTabbedPane.TOP);
	int ProgramCounter = 1;
	Boolean isCompiled = false;
	
	public MainFrame()
	{
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
		ConsolTab.addTab("MIPS IDE", null, console, null);
		
		
		JTabbedPane CurrInstTab = new JTabbedPane(JTabbedPane.TOP);
		CurrInstTab.setBounds(266, 275, 484, 110);
		getContentPane().add(CurrInstTab);
		
		JPanel CurrentInstruction = new JPanel();
		CurrInstTab.addTab("Current Assembly Code", null, CurrentInstruction, null);
		CurrentInstruction.setLayout(null);
		
		JButton CurrentInst = new JButton("-");
		CurrentInst.setBackground(SystemColor.inactiveCaptionBorder);
		CurrentInst.setFont(new Font("Tahoma", Font.PLAIN, 22));
		CurrentInst.setForeground(Color.BLACK);
		CurrentInst.setEnabled(false);
		CurrentInst.setBounds(0, 0, 479, 82);
		CurrentInstruction.add(CurrentInst);
		
		CurrInstTab.addTab("Machine Code", null, currInstructionInfo, null);
		
		JButton btnExecuteAll = new JButton("Execute all");
		btnExecuteAll.setBounds(276, 226, 127, 32);
		getContentPane().add(btnExecuteAll);
		
		JButton btnExecuteNext = new JButton("Execute Next");
		btnExecuteNext.setBounds(623, 226, 127, 32);
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
		
		JButton btnCompile = new JButton("Compile");
		
		btnCompile.setBounds(464, 226, 109, 33);
		getContentPane().add(btnCompile);
		
		setSize(800, 480);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
		
		btnExecuteAll.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				assembler.ClearInstructionsArray();
				String FileName = console.ReadInstructions();
				PCValue.setText("0");
				ProgramCounter = 0;
				virtualMachine.setProgramCounter(0);
				
				String assemblerMsg = assembler.validate(FileName);
				if(assemblerMsg.equals("valid"))
				{
					mipsInstructions = assembler.getInstructions();
					// TODO - 					
					CurrentInst.setText(mipsInstructions.get(mipsInstructions.size()-1).getInstruction());
					PCValue.setText(String.valueOf(mipsInstructions.size()));
					ProgramCounter = mipsInstructions.size();
					currInstructionInfo.setCurrentInst(mipsInstructions.get(mipsInstructions.size()-1));
					
					virtualMachine.Execute(mipsInstructions, memory.getMemory(), registers.getRegisters());
					
					registers.refreshRegisters();
					memory.refreshMemory();
					
					assembler.ClearInstructionsArray();
				}
				else
				{
					JOptionPane.showMessageDialog(new JFrame(), assemblerMsg, "Error", JOptionPane.ERROR_MESSAGE);
					assembler.ClearInstructionsArray();
					//System.out.println(assemblerMsg);
				}
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
				
				if(isCompiled)
				{
					System.out.println(ProgramCounter + "  " + mipsInstructions.size());
					if(ProgramCounter > mipsInstructions.size())
					{
						isCompiled = false;
						assembler.ClearInstructionsArray();
						return;
					}				
					System.out.println("PC : " + ProgramCounter + " , " +  " size : " + mipsInstructions.size());
					virtualMachine.Execute(mipsInstructions.get(ProgramCounter - 1), memory.getMemory(), registers.getRegisters());
					// program counter = index + 1
					
					CurrentInst.setText(mipsInstructions.get(ProgramCounter - 1).getInstruction());
					
					PCValue.setText(String.valueOf(ProgramCounter));
					currInstructionInfo.setCurrentInst(mipsInstructions.get(ProgramCounter - 1));
					
					//ProgramCounter++;
					ProgramCounter = virtualMachine.getProgramCounter()+1;
					
					registers.refreshRegisters();
					memory.refreshMemory();
				}
				else
				{
					JOptionPane.showMessageDialog(new JFrame(), "You need to compile the code first", "Error", JOptionPane.ERROR_MESSAGE);
					
					//System.out.println("Compile..");
				}
			}
		});
		
		btnExecuteExternalFile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(FileNameTxtBox.getText() == null || FileNameTxtBox.getText().equals(""))
				{
					JOptionPane.showMessageDialog(new JFrame(), "You need to write a valid file name\nex. Example.txt", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				String FileName = FileNameTxtBox.getText();
				PCValue.setText("0");
				ProgramCounter = 1;
				String assemblerMsg = assembler.validate(FileName);
				if(assemblerMsg.equals("valid"))
				{
					mipsInstructions = assembler.getInstructions();
					
					for(int i = 0 ; i < mipsInstructions.size() ; i++)
					{
						mipsInstructions.get(i).print();
					}
					
					//TODO - add text to JPanel CurrentInstruction
					CurrentInst.setText(mipsInstructions.get(mipsInstructions.size()-1).getInstruction());
					PCValue.setText(String.valueOf(mipsInstructions.size()));
					ProgramCounter = mipsInstructions.size();
					currInstructionInfo.setCurrentInst(mipsInstructions.get(mipsInstructions.size()-1));
					
					virtualMachine.Execute(mipsInstructions, memory.getMemory(), registers.getRegisters());
					
					memory.refreshMemory();
					registers.refreshRegisters();
					console.imposrtInstructions(FileName);
					
					assembler.ClearInstructionsArray();
				}
				else
				{
					assembler.ClearInstructionsArray();
					JOptionPane.showMessageDialog(new JFrame(), assemblerMsg, "Error", JOptionPane.ERROR_MESSAGE);
					//System.out.println(assemblerMsg);
				}
				// send the file to parser
				// if valid
				// send the file to assembler and get array of machine instruction
				// send the machine instruction to VM to execute it
				// get the updated memory , reg , pc from VM 
				// update the memory and reg tabs and PC Counter
			}
		});
			
		btnCompile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				assembler.ClearInstructionsArray();
				String FileName = console.ReadInstructions();
				String assemblerMsg = assembler.validate(FileName);
				
				if(assemblerMsg.equals("valid")) {
					mipsInstructions = assembler.getInstructions();
					isCompiled = true;
					PCValue.setText("0");
					ProgramCounter = 1;
					virtualMachine.setProgramCounter(0);
				}
				else
				{
					assembler.ClearInstructionsArray();
					JOptionPane.showMessageDialog(new JFrame(), assemblerMsg, "Error", JOptionPane.ERROR_MESSAGE);
					
					//System.out.println(assemblerMsg);
				}
			}
		});
	}
}
