package MIPS_Package;

import java.util.ArrayList;
import java.util.HashMap;

public class MIPS_VirtualMachine {

	// main memory
	private String memory[][] = new String[2000][2];
	/*
	 * memory[0][0] = "0x0000" + oct; // hexa start from 0x1000
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
	
	HashMap<String, String> funct = new HashMap<String, String>();
	
	int StartText; // = 0
	int StartSegment; // = 0x1000
	private int ProgramCounter; // start from 0
	
	public MIPS_VirtualMachine(String MIPSRegisters[][] , String memory[][]){
		// TODO Auto-generated constructor stub	
		this.memory = memory;
		this.MIPSRegisters = MIPSRegisters;
	}
	
	public void Execute(ArrayList<MIPSInstruction> instructions , String memory[][] , String MIPSRegisters[][])
	{
		MIPSInstruction inst;
		
		for(int i = 0 ; i < instructions.size() ; i++)
		{
			inst = instructions.get(i);
			if(inst.getType().equals("R-Type")) 
			{
				i = ExecuteRType(inst, i);
			}
			else if(inst.getType().equals("I-Type")) // addi , andi , ori , lw , sw , ... 
			{
				i = ExecuteIType(inst , i);
			}
			else if(inst.getType().equals("J-Type")) // j
			{
				String OP = ((J_TypeInstruction)inst).getOP();
				String addr = ((J_TypeInstruction)inst).getaddr();
				int addrindex = Integer.parseInt(addr, 2);
				if(OP.equals("000010")){ // j
					i = addrindex-1; //(it should jump to destination (addr) and skip all other instructions)
				}
			}
			setProgramCounter(i+1);
		}
		// change the program counter
		// execute all instructions in the array using the memory and MIPSRegisters in the parameters
		// let this.memroy = memory and this.MIPSRegisters = MIPSRegisters
	}

	public void Execute(MIPSInstruction instruction , String memory[][] , String MIPSRegisters[][])
	{
			MIPSInstruction inst;
			inst = instruction;
			if(inst.getType().equals("R-Type")) 
			{
				int i = getProgramCounter();
				i = ExecuteRType(inst, i);
				setProgramCounter(i+1);
			}
			else if(inst.getType().equals("I-Type")) // addi , andi , ori , lw , sw , ... 
			{
				int i = getProgramCounter();
				i = ExecuteIType(inst , i);
				setProgramCounter(i+1);
			}
			else if(inst.getType().equals("J-Type")) // jal, j
			{
				String OP = ((J_TypeInstruction)inst).getOP();
				String addr = ((J_TypeInstruction)inst).getaddr();
				int addrindex = Integer.parseInt(addr, 2);
				if(OP.equals("000010")){ // j
					setProgramCounter(addrindex);//(it should jump to destination (addr) and skip all other instructions)
				}
			}
		// change the program counter
		// execute all instructions in the array using the memory and MIPSRegisters in the parameters
		// let this.memroy = memory and this.MIPSRegisters = MIPSRegisters
	}

	private int ExecuteRType(MIPSInstruction inst , int i )
	{
		String funct = ((R_TypeInstRuction)inst).getfunct();

		String rd = ((R_TypeInstRuction)inst).getrd();
		String rs = ((R_TypeInstRuction)inst).getrs(); // 0101 = 5
		String rt = ((R_TypeInstRuction)inst).getrt(); // 1100 = 12
		String shamt = ((R_TypeInstRuction)inst).getshamt();

		int rdindex = Integer.parseInt(rd, 2);
		int rsindex = Integer.parseInt(rs, 2);
		int rtindex = Integer.parseInt(rt, 2);
		int shamtValue = Integer.parseInt(shamt, 2);

		String temprs = MIPSRegisters[rsindex][1].substring(2);
		String temprt = MIPSRegisters[rtindex][1].substring(2);

		int rsValue = Integer.parseInt(temprs , 16);
		int rtValue = Integer.parseInt(temprt , 16);
		int rdValue = 0;

		// add $s0, $s1, $s2
		if(funct.equals("100000")) // +
		{
			rdValue = rtValue + rsValue;
			System.out.println(rdValue  + "  = " + rtValue + " + " + rsValue);
		}
		else if(funct.equals("100010")) // -
		{
			rdValue = rsValue - rtValue;
			System.out.println(rdValue  + "  = " + rtValue + " - " + rsValue);
		}
		else if(funct.equals("100100")) // and
		{
			rdValue = rtValue & rsValue;
			System.out.println(rdValue  + "  = " + rtValue + " & " + rsValue);

		}
		else if(funct.equals("100101")) // or
		{
			rdValue = rtValue | rsValue;
			System.out.println(rdValue  + "  = " + rtValue + " | " + rsValue);

		}
		else if(funct.equals("000000")) //  sll <<
		{
			rdValue = rtValue << shamtValue;
			System.out.println(rdValue  + "  = " + rtValue + " << " + shamtValue);
		}
		else if(funct.equals("101010")) // slt
		{
			rdValue = (rtValue > rsValue)? 1 : 0 ;
		}
		else if(funct.equals("001000")) //jr
		{
			System.out.println( "jr : "+ rsValue);
			i = rsValue - 2; //3lashan lma ba return el i btzeed fel for loop
		}

		String rdTemp = "0x" + String.format("%8s", Integer.toHexString(rdValue)).replace(' ', '0');

		MIPSRegisters[rdindex][1] = rdTemp;
		return i;
	}
	//rt is the destination 
	private int ExecuteIType(MIPSInstruction inst, int i)
	{
		String rs = ((I_TypeInstruction)inst).getrs();
		String rt = ((I_TypeInstruction)inst).getrt(); // 0101 = 5
		String imm = ((I_TypeInstruction)inst).getimm();
		String OP = ((I_TypeInstruction)inst).getOP();

		int rsindex = Integer.parseInt(rs, 2);
		int rtindex = Integer.parseInt(rt, 2);

		String temprs = MIPSRegisters[rsindex][1].substring(2);
		String temprt = MIPSRegisters[rtindex][1].substring(2);
	
		int rsValue = Integer.parseInt(temprs , 16);
		int rtValue = Integer.parseInt(temprt , 16);

		if(OP.equals("001000")) // addi
		{
			int immValue = Integer.parseInt(imm, 2);
			rtValue = immValue + rsValue;
			System.out.println(rtValue + " = " +  immValue  + " + "+ rsValue);
			
			String rdTemp = "0x" + String.format("%8s", Integer.toHexString(rtValue)).replace(' ', '0');	
			MIPSRegisters[rtindex][1] = rdTemp;
		}
		else if(OP.equals("001101")) // ori
		{
			int immValue = Integer.parseInt(imm, 2);
			
			 rtValue = immValue | rsValue;
			
			String rdTemp = "0x" + String.format("%8s", Integer.toHexString(rtValue)).replace(' ', '0');	
			MIPSRegisters[rtindex][1] = rdTemp;		
		}
		else if(OP.equals("100011")) // lw // TODO
		{
			int immValue = Integer.parseInt(imm, 2);
			String rdTemp =   String.format("%8s", memory[((immValue/4)+rsValue)-4096][1].replace(' ', '0'));
			
			MIPSRegisters[rtindex][1] = rdTemp;
		}
		else if(OP.equals("000100")) // beq
		{
			if(rsValue == rtValue) {
				i = Integer.parseInt(imm , 2)-2;
			}
		}
		else if(OP.equals("101011")) // sw // TODO
		{
			int immValue = Integer.parseInt(imm, 2);
			
			int indx = 4069 - rsindex+(immValue/4) ;
			
			System.out.println( "change at memory : "+ rsValue+(immValue/4) + " = " + rtValue);
			System.out.println(indx);
			
			memory[(rsValue+(immValue/4))-4096][1] = "0x" + String.format("%8s", Integer.toHexString(rtValue)).replace(' ', '0');
		}
		else if(OP.equals("000101")) // bne
		{
			if(rsValue != rtValue) {
				i = Integer.parseInt(imm , 2)-2;
			}
		}
		else if(OP.equals("001010")) // slti
		{
			int immValue = Integer.parseInt(imm, 2);

			rtValue = (immValue < rsValue)? 1 : 0 ;
			String rdTemp = "0x" + String.format("%8s", Integer.toHexString(rtValue)).replace(' ', '0');	
			MIPSRegisters[rtindex][1] = rdTemp;
		}
		// imm = 100010101111
		else if(OP.equals("001111")) // lui
		{
			// rs should be 0s, imm has integer
			imm = imm + "0000000000000000";
			int immValue = Integer.parseInt(imm,2);

			String rdTemp = "0x" + String.format("%8s", Integer.toHexString(immValue)).replace(' ', '0');
			MIPSRegisters[rtindex][1] = rdTemp;
		}
		else if(OP.equals("001100")) // andi
		{
			int immValue = Integer.parseInt(imm, 2);
			rtValue = immValue & rsValue;
			
			String rdTemp = "0x" + String.format("%8s", Integer.toHexString(rtValue)).replace(' ', '0');	
			MIPSRegisters[rtindex][1] = rdTemp;
		}
		return i;
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
