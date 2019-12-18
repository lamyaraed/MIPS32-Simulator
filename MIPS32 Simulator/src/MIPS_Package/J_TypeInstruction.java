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
	public J_TypeInstruction(String OpCode , String addr)
	{
		// TODO Auto-generated constructor stub
		Instruction.put("OPCode", OpCode);
		Instruction.put("addr", addr);
	}
	
	@Override
	public void print() {
		System.out.println(getOP() +getaddr());
	}
	
	public String getOP()
	{
		return Instruction.get("OPCode");
	}
	
	public String getaddr()
	{
		return Instruction.get("addr");
	}
}
