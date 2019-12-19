package MIPS_Package;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Assembler
{
	private HashMap<String, String> map = new HashMap<String, String>();
	private ArrayList<MIPSInstruction> mipsInstructions = new ArrayList<MIPSInstruction>();

	//public ArrayList<MIPSInstruction> Assembly(String FileName)
	public String validate(String FileName)
	{
		buildMap();
		String Err_msg = "there is an error at line ";
		int counter = 0;
		File file = new File(FileName); // test -> any file contain MIPS instructions
		  
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		} 
		  
		String st; 
		try {
			while ((st = br.readLine()) != null) 
			{
					if(st.matches("\\w+[\\s]*:$")) {
						System.out.println(st + counter);
		    			map.put(st.substring(0,st.length()-1),Integer.toBinaryString(counter-1));
		    		}
					counter++;
			}
			br = new BufferedReader(new FileReader(file));
			counter = 1;
			while ((st = br.readLine()) != null) 
			{
				if(st.contains("#"))
                {
                    st = st.substring(0 , st.indexOf("#"));
                }
                if(st.matches("")) {
                    continue;
                }
                
				// st is a valid instruction
				st = st.trim();
		    	String[] parsed = st.split("[\\s,]+");//splitting each instruction into their main components
		    	
		    	if(parsed.length == 4) {
		    		if(parsed[0].matches("add|sub|and|or|slt") &&
		    			parsed[1].matches("\\$((s[0-7])|0|v[01]|a[0-3]|t[0-9]|ra)") &&
		    			parsed[2].matches("\\$((s[0-7])|0|v[01]|a[0-3]|t[0-9]|ra)") &&
		    			parsed[3].matches("\\$((s[0-7])|0|v[01]|a[0-3]|t[0-9]|ra)")	
		    		)//R-TYPE
		    		{
		    			R_TypeInstRuction mipsInstr =new R_TypeInstRuction(String.format("%5s", map.get(parsed[2])).replace(' ','0')
	    						, String.format("%5s", map.get(parsed[3])).replace(' ','0')
	    						, String.format("%5s", map.get(parsed[1])).replace(' ', '0'), 
	    						"00000", map.get(parsed[0]));
		    			
		    			mipsInstr.setType("R-Type");
		    			mipsInstr.setInstruction(st);
		    			mipsInstructions.add(mipsInstr);
		    			
		    		}
		    		else if(parsed[0].matches("sll|addi|andi|ori|slti|beq|bne") &&
		    			parsed[1].matches("\\$((s[0-7])|0|v[01]|a[0-3]|t[0-9]|ra)") &&
		    			parsed[2].matches("\\$((s[0-7])|0|v[01]|a[0-3]|t[0-9]|ra)") &&
		    			parsed[3].matches("\\w+|[0-9]+")) {
		    			//System.out.println("here "   + counter);
			    			if(parsed[0].equals("sll") && parsed[3].matches("[0-9]+")) {//RTYPE
			    				
			    				R_TypeInstRuction mipsInstr = new R_TypeInstRuction("00000",
			    						String.format("%5s", map.get(parsed[2])).replace(' ', '0')
			    						,String.format("%5s", map.get(parsed[1])).replace(' ', '0')
			    						,String.format("%5s", Integer.toBinaryString(Integer.parseInt(parsed[3]))).replace(' ', '0')
			    						, map.get(parsed[0]));
			    				
				    			mipsInstr.setInstruction(st);
			    				mipsInstr.setType("R-Type");
			    				mipsInstructions.add(mipsInstr);
			    				
			    			}
			    			else if(parsed[0].matches("beq|bne" )&& parsed[3].matches("[0-9]+")) {//ITYPE
			    				I_TypeInstruction mipsInstr  = new I_TypeInstruction(map.get(parsed[0]), 
			    						String.format("%5s", map.get(parsed[1])).replace(' ', '0'),
			    						String.format("%5s", map.get(parsed[2])).replace(' ','0'),
			    						String.format("%16s", Integer.toBinaryString(Integer.parseInt(parsed[3]))).replace(' ', '0'));
					    		
				    			mipsInstr.setInstruction(st);
			    				mipsInstr.setType("I-Type");
					    		mipsInstructions.add(mipsInstr);

			    			}
			    			else if(parsed[0].matches("addi|andi|ori|slti" )&& parsed[3].matches("[0-9]+")) {//ITYPE
			    				I_TypeInstruction mipsInstr  = new I_TypeInstruction(map.get(parsed[0]), 
			    						String.format("%5s", map.get(parsed[2])).replace(' ', '0'),
			    						String.format("%5s", map.get(parsed[1])).replace(' ','0'),
			    						String.format("%16s", Integer.toBinaryString(Integer.parseInt(parsed[3]))).replace(' ', '0'));

				    			mipsInstr.setInstruction(st);
			    				mipsInstr.setType("I-Type");
				    			mipsInstructions.add(mipsInstr);	
			    			}
			    			else if(parsed[0].matches("beq|bne" )&& parsed[3].matches("\\w+")) {
			    				if(map.get(parsed[3])!=null) {	
			    					I_TypeInstruction mipsInstr  =new I_TypeInstruction(map.get(parsed[0]), 
				    						String.format("%5s", map.get(parsed[1])).replace(' ', '0'),
				    						String.format("%5s", map.get(parsed[2])).replace(' ','0'),
				    						String.format("%16s", map.get(parsed[3])).replace(' ', '0'));
			    				
					    			mipsInstr.setInstruction(st);
			    					mipsInstr.setType("I-Type");
			    					mipsInstructions.add(mipsInstr);
			    				
			    				}
			    				else {
						    		return Err_msg + counter;
			    				}
			    				
			    			}
			    			else {
					    		return Err_msg + counter;
					    	}
		    		}
		    		else {
			    		return Err_msg + counter;
			    	}
		    		
		    	}
		    	else if(parsed.length == 3) {
		    		
					if(parsed[0].matches("lui|lw|sw") &&
			    		parsed[1].matches("\\$((s[0-7])|0|v[01]|a[0-3]|t[0-9]|ra)") &&
			    		parsed[2].matches("([0-9]+)?|[0-9]*\\(\\$(s[0-7]|0|v[01]|a[0-3]|t[0-9]|ra)\\)")) {

						if(parsed[0].matches("lui")){
							I_TypeInstruction mipsInstr  =new I_TypeInstruction(map.get(parsed[0]), 
									"00000" , String.format("%5s", map.get(parsed[1])).replace(' ', '0') ,
									String.format("%16s", Integer.toBinaryString(Integer.parseInt(parsed[2]))).replace(' ', '0'));
	
			    			mipsInstr.setInstruction(st);
							mipsInstr.setType("I-Type");
							mipsInstructions.add(mipsInstr);
						}
						else if(parsed[0].matches("lw|sw")) {
							String []parse = parsed[2].split("[\\(||\\)]");
							if(parse[0].equals(""))parse[0] = "0";
							
							if((Integer.parseInt(parse[0]) % 4) == 0) {
								I_TypeInstruction mipsInstr  =new I_TypeInstruction(map.get(parsed[0]), 
										String.format("%5s", map.get(parse[1])).replace(' ', '0') , 
										String.format("%5s", map.get(parsed[1])).replace(' ', '0') ,
										String.format("%16s", Integer.toBinaryString(Integer.parseInt(parse[0]))).replace(' ', '0'));
		
				    			mipsInstr.setInstruction(st);
								mipsInstr.setType("I-Type");
								mipsInstructions.add(mipsInstr);
							}
							else {
					    		return Err_msg + counter;
					    	}
						}
	    						
					}
					
					else {
			    		return Err_msg + counter;
			    	}
				}
		    	else if(parsed.length == 2) {
		    		if(parsed[0].matches("jr")&&parsed[1].matches("\\$((s[0-7])|0|v[01]|a[0-3]|t[0-9]|ra)"))
		    		{
		    			R_TypeInstRuction instr  = new R_TypeInstRuction(String.format("%5s", map.get(parsed[1])).replace(' ', '0'), 
				    			"00000", "00000", "00000", map.get(parsed[0]));
		    			
		    			instr.setInstruction(st);
		    			instr.setType("R-Type");
		    			mipsInstructions.add(instr);
		    			
		    		}
		    		else if(parsed[0].matches("j") && parsed[1].matches("[0-9]+")) {
		    			J_TypeInstruction instr = new J_TypeInstruction( map.get(parsed[0]) 
						,String.format("%26s", Integer.toBinaryString(Integer.parseInt(parsed[1]))).replace(' ', '0'));

		    			instr.setInstruction(st);
		    			instr.setType("J-Type");
		    			mipsInstructions.add(instr);
		    			
		    		}
		    		else if(parsed[0].matches("j") && parsed[1].matches("\\w+")) {
	    				if(map.get(parsed[1])!=null) {	
	    					J_TypeInstruction instr = new J_TypeInstruction( map.get(parsed[0]) 
	    							,String.format("%26s", map.get(parsed[1])).replace(' ', '0'));
			    			
			    			instr.setInstruction(st);
	    					instr.setType("J-Type");
	    					mipsInstructions.add(instr);
			    			
		    			}
	    				else 
	    					return Err_msg + counter;
		    		}
		    		else {
			    		return Err_msg + counter;
			    	}

		    	}
		    	else if(parsed.length == 1) {
					if(!st.matches("\\w+[\\s]*:$")) 
		    			return Err_msg + counter;
		    	}
		    	else {
		    		return Err_msg + counter;
		    	}
				
		    	counter++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		} 
		return "valid";	
	}
	

	public ArrayList<MIPSInstruction> getInstructions() {
		return mipsInstructions;
	}
	public void ClearInstructionsArray() {
		mipsInstructions.clear();
	}
	private void buildMap() {
		map.put("$0",Integer.toBinaryString(0)); map.put("$at",Integer.toBinaryString(1));
        map.put("$v0",Integer.toBinaryString(2));map.put("$v1",Integer.toBinaryString(3));
        map.put("$a0",Integer.toBinaryString(4));map.put("$a1",Integer.toBinaryString(5));
        map.put("$a2",Integer.toBinaryString(6));map.put("$a3",Integer.toBinaryString(7));
        map.put("$t0",Integer.toBinaryString(8));map.put("$t1",Integer.toBinaryString(9));
        map.put("$t2",Integer.toBinaryString(10));map.put("$t3",Integer.toBinaryString(11));
        map.put("$t4",Integer.toBinaryString(12));map.put("$t5",Integer.toBinaryString(13));
        map.put("$t6",Integer.toBinaryString(14));map.put("$t7",Integer.toBinaryString(15));
        map.put("$s0",Integer.toBinaryString(16));map.put("$s1",Integer.toBinaryString(17));
        map.put("$s2",Integer.toBinaryString(18));map.put("$s3",Integer.toBinaryString(19));
        map.put("$s4",Integer.toBinaryString(20));map.put("$s5",Integer.toBinaryString(21));
        map.put("$s6",Integer.toBinaryString(22));map.put("$s7",Integer.toBinaryString(23));
        map.put("$t8",Integer.toBinaryString(24));map.put("$t9",Integer.toBinaryString(25));
        map.put("$k0",Integer.toBinaryString(26));map.put("$k1",Integer.toBinaryString(27));
        map.put("$gp",Integer.toBinaryString(28));map.put("$sp",Integer.toBinaryString(29));
        map.put("$fp",Integer.toBinaryString(30));map.put("$ra",Integer.toBinaryString(31));
        
        map.put("addi","001000"); map.put("add","100000");
        map.put("sub","100010"); map.put("and","100100");
        map.put("or","100101"); map.put("sll","000000");
        map.put("slt","101010"); map.put("jr","001000");
        map.put("ori","001101"); map.put("lw","100011");
        map.put("beq","000100"); map.put("sw","101011");
        map.put("bne","000101"); map.put("slti","001010");
        map.put("lui","001111"); map.put("j","000010");
        map.put("andi","001100");
	}
}




