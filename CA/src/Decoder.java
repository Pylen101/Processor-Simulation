import java.util.ArrayList;

public class Decoder {
	public Instruction decode(String instruction,ArrayList<Register> registerList) {
		if (instruction == null || instruction.equals("")) {
			System.out.println("Decoding:  no instruction avialable");
			return null;
		} else {
			String[] splited = { instruction.substring(0, 4), instruction.substring(4, 10),
					instruction.substring(10, 16) };
			
			System.out.println("Decoding : opcode = "+ splited[0]+ " operand1 = "+ splited[1]+" operand2 =  "+ splited[2]);
			
			String r1 = searchRegister(splited[1], registerList);
			
			if (splited[0].equals("0000") || splited[0].equals("0001") || splited[0].equals("0010")
					|| splited[0].equals("0101") || splited[0].equals("0110") || splited[0].equals("0111")) {
				
				
				String r2 = searchRegister(splited[2], registerList);
				System.out.println("Decoded Instruction : Opcode = "+ Integer.parseInt( splited[0],2)+ " operand1 =  R"+ Integer.parseInt( splited[1],2)+" operand1 =  R"+ Integer.parseInt( splited[2],2));
				return new Instruction(splited[0], r1 ,r2,splited[1]);
				
			}
			else {
				System.out.println("Decoded Instruction :  Opcode = "+ Integer.parseInt( splited[0],2)+ " operand1 = R"+ Integer.parseInt( splited[1],2)+" operand2 = " + splited[2]);
				if(splited[0].equals("0011") || splited[0].equals("1010"))
				{
					
					return new Instruction(splited[0], splited[1], splited[2],splited[1]);
				}
					
			else {
					
					return new Instruction(splited[0], r1, splited[2],splited[1]);
				}
			}
		}
	}

	public String searchRegister(String registerAddress, ArrayList<Register> registerList) {
		int decimal = Integer.parseInt(registerAddress, 2);
		for (int i = 0; i < registerList.size(); i++) {
			if (i == decimal) {
				return registerList.get(i).getData();
			}
		}
		return null;
	}

//	public decodeInstruction (Instruction i,ArrayList<Register> registerList) {
//		if(i.getType() == ('R')) {
//		Register register1 = searchRegister(i.getOperand1(), registerList);
//		Register register2 = searchRegister(i.getOperand2(), registerList);
//		}else {
//			Register register1 = searchRegister(i.getOperand1(), registerList);
//		}
//	}

}
