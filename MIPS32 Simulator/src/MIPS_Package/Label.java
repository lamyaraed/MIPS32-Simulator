package MIPS_Package;

import java.util.HashMap;

public class Label extends MIPSInstruction
{
	HashMap<String, String> Instruction = new HashMap<String, String>();
	
	public Label()
	{
		// TODO Auto-generated constructor stub
		Instruction.put("label", "");
	}
	
	String getlabel()
	{
		return Instruction.get("label");
	}
	
}
