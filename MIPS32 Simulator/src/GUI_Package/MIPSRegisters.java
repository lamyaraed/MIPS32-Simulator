package GUI_Package;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MIPSRegisters extends JScrollPane 
{
	private JTable table;
	private String Registers[][] = new String[32][2];
	private ArrayList<String> reg = new ArrayList<String>();
	
	public MIPSRegisters()
	{
		String[] ColName = { "MIPS Reg", "value"};
		intializeRegisters();
		
		table = new JTable(Registers , ColName);
		table.setShowGrid(false);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		
        setViewportView(table);
	}

	private void intializeRegisters() {
		// TODO Auto-generated method stub
		reg.add("$0");
		reg.add("$at");
		reg.add("$v0");
		reg.add("$v1");
		reg.add("$a0");
		reg.add("$a1");
		reg.add("$a2");
		reg.add("$a3");
		reg.add("$t0");
		reg.add("$t1");
		reg.add("$t2");
		reg.add("$t3");
		reg.add("$t4");
		reg.add("$t5");
		reg.add("$t6");
		reg.add("$t7");
		reg.add("$s0");
		reg.add("$s1");
		reg.add("$s2");
		reg.add("$s3");
		reg.add("$s4");
		reg.add("$s5");
		reg.add("$s6");
		reg.add("$s7");
		reg.add("$t8");
		reg.add("$t9");
		reg.add("$k0");
		reg.add("$k1");
		reg.add("$gp");
		reg.add("$sp");
		reg.add("$fp");
		reg.add("$ra");
		
		
		for(int i = 0 ; i < reg.size() ;i++)
		{
			Registers[i][0] = new String (reg.get(i));
			Registers[i][1] = new String ("0000");
		}
	}
	
	public void updateRegister(String reg , String bin)
	{
		//setValueAt(Object value, int row, int col)
		int row = FindReg(reg);
		if(row != -1)
		{
			table.setValueAt(bin, row, 1);
		}
	}
	
	public void updateRegister(int reg , String bin)
	{
		//setValueAt(Object value, int row, int col)
		if(reg != -1)
		{
			table.setValueAt(bin, reg, 1);
		}
	}
	
	private int FindReg(String reg) {
		// TODO Auto-generated method stub
		for(int i = 0 ; i < 16 ; i++)
		{
			if(Registers[i][0].equals(reg))
			{
				return i;
			}
		}
		return -1;
	}
	
}
