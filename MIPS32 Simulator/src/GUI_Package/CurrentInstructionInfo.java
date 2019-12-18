package GUI_Package;

import MIPS_Package.*;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CurrentInstructionInfo extends JScrollPane 
{
	private JTable table;
	private MIPSInstruction CurrentInst;
	
	
	public MIPSInstruction getCurrentInst() {
		return CurrentInst;
	}
	
	public void setCurrentInst(MIPSInstruction currentInst) {
		CurrentInst = currentInst;
		createTable(CurrentInst.getType());
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		
        setViewportView(table);
	}
	
	private void createTable(String Type)
	{
		if(Type.equals("R-Type"))
		{
			String ColName[] = { "Type" , "Op" , "rs" , "rt" , "rd" , "shamt" , "funct" };
			String date[][] = {{ Type , ((R_TypeInstRuction)CurrentInst).getOP()
				,((R_TypeInstRuction)CurrentInst).getrs() , ((R_TypeInstRuction)CurrentInst).getrt()
				,((R_TypeInstRuction)CurrentInst).getrd() , ((R_TypeInstRuction)CurrentInst).getshamt()
				,((R_TypeInstRuction)CurrentInst).getfunct()
			}};
			
			table = new JTable(date, ColName);
		}
		else if(Type.equals("I-Type"))
		{
			String ColName[] = {"Type" , "Op" , "rs" , "rt" , "imm" };
			String date[][] = {{Type , ((I_TypeInstruction)CurrentInst).getOP()
				,((I_TypeInstruction)CurrentInst).getrs() , ((I_TypeInstruction)CurrentInst).getrt()
				,((I_TypeInstruction)CurrentInst).getimm()
			}};
			
			table = new JTable(date, ColName);
		}
		else if(Type.equals("J-Type"))
		{
			String ColName[] = {"Type" , "Op" , "addr" };
			String date[][] = {{Type , ((J_TypeInstruction)CurrentInst).getOP()
				,((J_TypeInstruction)CurrentInst).getaddr()
			}};
			
			table = new JTable(date, ColName);
		}
	}
	
}
