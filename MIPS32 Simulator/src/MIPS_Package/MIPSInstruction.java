package MIPS_Package;

public abstract class MIPSInstruction 
{
	private String Type;
	private String Instruction;
	public String getType() {
		return Type;
	}
	public String getInstruction() {
		return Instruction;
	}
	
	public void setInstruction(String Inst) {
		Instruction = Inst;
	}
	
	public void setType(String type) {
		Type = type;
	}

	public void print() {
		
	}

}
