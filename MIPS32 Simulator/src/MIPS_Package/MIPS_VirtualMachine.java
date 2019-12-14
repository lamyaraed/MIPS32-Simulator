package MIPS_Package;

import java.util.ArrayList;

public class MIPS_VirtualMachine {

	// main memory
	private String memory[][] = new String[2000][2];
	/*
	 * memory[0][0] = "0x0000" + oct; // oct start from 0x1000
	   memory[0][1] = "0000"; // binary values
	 */
	// processor registers
	private String MIPSRegisters[][] = new String[32][2];
	/*
	 * Registers[0][0] = "$t0"; 
	   Registers[0][1] = "0000";
	 * Registers[1][0] = $t1;
	   Registers[1][1] = "0000"; // binary values
	 *  and so on
	 */
	
	//currently executing instructions and its type (R, I, J , Label)
	ArrayList<MIPSInstruction> instructions = new ArrayList<MIPSInstruction>();
	
	int StartText; // = 0
	int StartSegment; // = 0x1000
	private int ProgramCounter; // start from 0
	
	public MIPS_VirtualMachine() {
		// TODO Auto-generated constructor stub	
	
	}
	
	public void Execute(ArrayList<MIPSInstruction> instructions , String memory[][] , String MIPSRegisters[][])
	{
		// change the program counter
		// execute all instructions in the array using the memory and MIPSRegisters in the parameters
		// let this.memroy = memory and this.MIPSRegisters = MIPSRegisters
	}

	public int getProgramCounter() {
		return ProgramCounter;
	}

	public void setProgramCounter(int programCounter) {
		ProgramCounter = programCounter;
	}

	public String[][] getMemory() {
		return memory;
	}

	public void setMemory(String memory[][]) {
		this.memory = memory;
	}

	public String[][] getMIPSRegisters() {
		return MIPSRegisters;
	}

	public void setMIPSRegisters(String mIPSRegisters[][]) {
		MIPSRegisters = mIPSRegisters;
	}
	
	
}
