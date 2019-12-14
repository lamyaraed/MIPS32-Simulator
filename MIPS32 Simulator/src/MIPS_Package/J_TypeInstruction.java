package MIPS_Package;

import java.util.HashMap;

public class J_TypeInstruction extends MIPSInstruction
{
	HashMap<String, String> Instruction = new HashMap<String, String>();
	
	public J_TypeInstruction()
	{
		// TODO Auto-generated constructor stub
		Instruction.put("OPCode", "");
		Instruction.put("addr", "");
	}
	
	public String getOP()
	{
		return Instruction.get("OPCode");
	}
	
	public String getaddr()
	{
		return Instruction.get("addr");
	}
	
	public void setOP(String in)
	{
		Instruction.put("OPCode", in);
	}
	
	public void setaddr(String in)
	{
		Instruction.put("addr", in);
	}
	
}
