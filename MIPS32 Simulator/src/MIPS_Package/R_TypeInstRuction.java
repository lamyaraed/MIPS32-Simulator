package MIPS_Package;

import java.util.HashMap;

public class R_TypeInstRuction extends MIPSInstruction
{
	public HashMap<String, String> Instruction = new HashMap<String, String>();
	
	public R_TypeInstRuction()
	{
		// TODO Auto-generated constructor stub
		Instruction.put("OPCode", "00000");
		Instruction.put("rs", "");
		Instruction.put("rt", "");
		Instruction.put("rd", "");
		Instruction.put("shamt", "");
		Instruction.put("funct", "");
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
	public String getrd()
	{
		return Instruction.get("rd");
	}
	public String getshamt()
	{
		return Instruction.get("shamt");
	}
	public String getfunct()
	{
		return Instruction.get("funct");
	}
	
	public void setrs(String rs)
	{
		Instruction.put("rs", rs);
	}
	
	public void setrt(String in)
	{
		Instruction.put("rt", in);
	}
	public void setrd(String in)
	{
		Instruction.put("rd", in);
	}
	public void setshamt(String in)
	{
		Instruction.put("shamt", in);
	}
	public void setfunct(String in)
	{
		Instruction.put("funct", in);
	}
	
}
