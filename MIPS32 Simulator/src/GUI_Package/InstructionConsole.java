package GUI_Package;

import java.awt.Panel;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Font;


public class InstructionConsole extends JScrollPane  
{
	JTextArea console = new JTextArea("Hello");
	
	public InstructionConsole() {
		console.setFont(new Font("Courier New", Font.BOLD, 13));
		// TODO Auto-generated constructor stub
		setViewportView(console);
	}
	
	String ReadInstructions()
	{
		String txt = console.getText();
		String FileName = "inst.txt";
		System.out.println(txt);
		
		WriteOnFile(FileName , txt);
		
		return FileName;
	}
	
	private void WriteOnFile(String FilePath , String Lines)
    {
    	PrintWriter out;		
    	try {
    		String[] text = Lines.split("\n");
    		out = new PrintWriter(FilePath);
    		
    		  for (String word: text) {
    			  out.println(word);
    		  }
			out.close();
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println(e.getMessage());
		}
    }

}
