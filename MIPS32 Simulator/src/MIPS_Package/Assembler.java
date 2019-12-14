package MIPS_Package;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Assembler
{
	
	public Assembler() {
		// TODO Auto-generated constructor stub	
		
	}
	
	public ArrayList<MIPSInstruction> Assembly(String FileName)
	{
		ArrayList<MIPSInstruction> mipsInstructions = new ArrayList<MIPSInstruction>();
		
		File file = new File(FileName); // test -> any file contain MIPS instructions
		  
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		} 
		  
		String st; 
		try {
			while ((st = br.readLine()) != null) 
			{
				// st is a valid instruction
				// convert it to machine instructions
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		} 
		return mipsInstructions;	
	}
}




