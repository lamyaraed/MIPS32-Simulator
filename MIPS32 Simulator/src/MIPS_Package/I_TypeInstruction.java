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
	
	public I_TypeInstruction(String OPCode , String rs ,String rt ,String imm)
	{
		// TODO Auto-generated constructor stub
		Instruction.put("OPCode", OPCode);
		Instruction.put("rs", rs);
		Instruction.put("rt", rt);
		Instruction.put("imm", imm);
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
	@Override
	public void print() {
		System.out.println(getOP() + getrs() + getrt() + getimm());
	}

}
