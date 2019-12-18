package GUI_Package;

import java.awt.Panel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Font;


public class InstructionConsole extends JScrollPane  
{
	JTextArea console = new JTextArea("");
	
	public InstructionConsole() {
		console.setFont(new Font("Courier New", Font.BOLD, 13));
		// TODO Auto-generated constructor stub
		setViewportView(console);
	}
	
	String ReadInstructions()
	{
		String txt = console.getText();
		String FileName = "inst.txt";
		System.out.println("Instructions From File : \n" + txt); 
		
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
	
	public void imposrtInstructions(String path)
	{
		BufferedReader br;
		String Lines = "";
			
		try {
			// print the file name
			if(new File(path).isFile()) {
				
				// print the content
				br = new BufferedReader(new FileReader(path));
				 String line;
				 while ((line = br.readLine()) != null) {
					 Lines+= line + '\n';
				}
			}
			else
			{
				throw new FileNotFoundException();
			}
		} catch (FileNotFoundException e ) {
			// TODO Auto-generated catch block
			System.out.println("File Not Found");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		console.setText(Lines);
		setViewportView(console);
	}
}
