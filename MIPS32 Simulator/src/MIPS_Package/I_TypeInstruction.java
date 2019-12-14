package MIPS_Package;

import java.util.HashMap;

public class I_TypeInstruction extends MIPSInstruction
{
	HashMap<String, String> Instruction = new HashMap<String, String>();
	
	public I_TypeInstruction()
	{
		// TODO Auto-generated constructor stub
		Instruction.put("OPCode", "");
		Instruction.put("rs", "");
		Instruction.put("rt", "");
		Instruction.put("imm", "");
	}
	

	public String getOP()
	{
		return Instruction.get("OPCode");
	}
	
	public String getrs()
	{
		return Instruction.get("rs");
	}
	public String getrt()
	{
		return Instruction.get("rt");
	}
	public String getimm()
	{
		return Instruction.get("imm");
	}
	

	public void setOP(String in)
	{
		Instruction.put("OPCode", in);
	}
	
	public void setrs(String rs)
	{
		Instruction.put("rs", rs);
	}
	
	public void setrt(String in)
	{
		Instruction.put("rt", in);
	}
	
	public void setimm(String in)
	{
		Instruction.put("imm", in);
	}
	
}
