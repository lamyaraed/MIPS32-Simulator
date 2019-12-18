package MIPS_Package;

import java.util.HashMap;

public class R_TypeInstRuction extends MIPSInstruction
{
	HashMap<String, String> Instruction = new HashMap<String, String>();
	
	public R_TypeInstRuction()
	{
		// TODO Auto-generated constructor stub
		Instruction.put("OPCode", "000000");
		Instruction.put("rs", "");
		Instruction.put("rt", "");
		Instruction.put("rd", "");
		Instruction.put("shamt", "");
		Instruction.put("funct", "");
	}
	
	public R_TypeInstRuction(String rs , String rt , String rd ,String shamt , String funct)
	{
		// TODO Auto-generated constructor stub
		Instruction.put("OPCode", "000000");
		Instruction.put("rs", rs);
		Instruction.put("rt", rt);
		Instruction.put("rd", rd);
		Instruction.put("shamt", shamt);
		Instruction.put("funct", funct);
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
	@Override
	public void print() {
		System.out.println("op : " + getOP() + " rs : " +getrs() + " rt : " +getrt() + " rd : " + getrd() + 
				" shamt : " +getshamt() + "funct : " +getfunct());

	}
	
}
