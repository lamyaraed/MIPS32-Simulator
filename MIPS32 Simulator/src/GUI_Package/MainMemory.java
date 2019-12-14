package GUI_Package;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MainMemory extends JScrollPane 
{
	private JTable table;
	private String memory[][] = new String[2000][2];
	
	public MainMemory() {
		// TODO Auto-generated constructor stub
		String[] ColName = { "memory index", "value"};
		intializeCells("MipsRegisters.txt");
		
		table = new JTable(memory , ColName);
		table.setShowGrid(false);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		
        setViewportView(table);
	}

	private void intializeCells(String string) {
		// TODO Auto-generated method stub
		int start = 4096;
		int step = 0;
		for(int i = 0 ; i < 2000 ; i++)
		{
			int val = start+step;
			System.out.println(val);
			String oct = Integer.toHexString(val);
			memory[i][0] = "0x0000"+oct;
			memory[i][1] = "0000";
			step+=4;
		}
	}
}
