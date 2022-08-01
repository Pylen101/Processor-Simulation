import java.util.ArrayList;

public class Executer {
	public Executer() {

	}

	public void execute(Instruction instruction, Register status, PC pc, data_memory mem,ArrayList<Register> registerList) {
		if(instruction == null) {
			
			System.out.println("no instruction executed");
		}
			else
	    {
	
		int decimal;
		String result;
		int res;
		decimal = Integer.parseInt(instruction.getRregister(), 2);
		
		switch (instruction.getOpcode()) {
		case "0000":
			 
			 result=ADD(instruction.getOperand1(), instruction.getOperand2(), status);
			 registerList.get(decimal).setData(result);  
			 System.out.println("Executing :  ADD  " + getTwosComplement(instruction.getOperand1()) +"  "+ getTwosComplement(instruction.getOperand2()) );
			 System.out.println("R"+decimal+" value changed to "+getTwosComplement(result));
			 
			break;
		case "0001":
			
		   
		    result=SUB(instruction.getOperand1(), instruction.getOperand2(), status);
			registerList.get(decimal).setData(result);
			 System.out.println("Executing :  SUB  " + getTwosComplement(instruction.getOperand1()) +"  "+ getTwosComplement(instruction.getOperand2()) );
			System.out.println("R"+decimal +" value changed to "+getTwosComplement(result));
			
			break;
		case "0010":

			result=MULI(instruction.getOperand1(), instruction.getOperand2(), status);
			 registerList.get(decimal).setData(result); 
			 System.out.println("Executing :  MUL  " + getTwosComplement(instruction.getOperand1()) +"  "+ getTwosComplement(instruction.getOperand2()) );
			 System.out.println("R"+decimal+" value changed to "+getTwosComplement(result));
			break;
			
		case "0011":
			if((instruction.getOperand2()).charAt(0)=='1')
			{
				instruction.setOperand2("11"+instruction.getOperand2());
			}else
			{
				instruction.setOperand2("00"+instruction.getOperand2());
			}
			
			registerList.get(decimal).setData(instruction.getOperand2());
			 System.out.println("Executing :  LDI   R" + Integer.parseInt(instruction.getOperand1(),2) +"  "+ getTwosComplement(instruction.getOperand2()) );
			 System.out.println("R"+decimal+" value changed to "+getTwosComplement(instruction.getOperand2()));
				
			 break;
		case "0100":
			
		    res = BEQZ(instruction.getOperand1(), instruction.getOperand2(), status,pc);
			System.out.println(res);
		    pc.setPc(res); 
			 System.out.println("Executing :  BEQZ  " + getTwosComplement(instruction.getOperand1()) +"  "+ getTwosComplement(instruction.getOperand2()));
			 System.out.println("PC "+" value changed to "+res);
			 
			break;
		case "0101":
			
			
			result=AND(instruction.getOperand1(), instruction.getOperand2(), status);
			registerList.get(decimal).setData(result);  
			 System.out.println("Executing :  AND  " + instruction.getOperand1() +"  "+ instruction.getOperand2() );
			System.out.println("R"+decimal+" value changed to "+result);
			
			break;
		case "0110":
			
			result=OR(instruction.getOperand1(), instruction.getOperand2(), status);
			registerList.get(decimal).setData(result);
			 System.out.println("Executing :  OR  " + instruction.getOperand1() +"  "+ instruction.getOperand2() );
			 System.out.println("R"+decimal+" value changed to "+result);
			 
			break;
		case "0111":
			
			res = JR(instruction.getOperand1(), instruction.getOperand2(), status);
			pc.setPc(res);
			 System.out.println("Executing :  JR  " + instruction.getOperand1() +"  "+ instruction.getOperand2() );
			 System.out.println("PC "+" value changed to "+res);
			break;
		case "1000":
			
			result=SLC(instruction.getOperand1(), instruction.getOperand2(), status);
			registerList.get(decimal).setData(result);
			 System.out.println("Executing :  SLC  " + Integer.parseInt(instruction.getOperand1(),2) +"  "+ Integer.parseInt(instruction.getOperand2(),2) );
			System.out.println("R"+decimal+" value changed to "+result);
			
			break;
		case "1001":
			
			result=SRC(instruction.getOperand1(), instruction.getOperand2(), status);
			registerList.get(decimal).setData(result);
			 System.out.println("Executing :  SRC  " + Integer.parseInt(instruction.getOperand1(),2) +"  "+ Integer.parseInt(instruction.getOperand2(),2) );
			System.out.println("R"+decimal+" value changed to "+result);
			
			break;
		case "1010":
			
			int address =Integer.parseInt(instruction.getOperand2(), 2);
			registerList.get(decimal).setData( mem.getMemory()[address]); 
			 System.out.println("Executing :  LB   R" + Integer.parseInt(instruction.getOperand1(),2) +"  "+ instruction.getOperand2() );
			 System.out.println("R"+decimal+" value changed to "+ getTwosComplement(mem.getMemory()[address]));
			break;
		case "1011":
			
			int addres =Integer.parseInt(instruction.getOperand2(), 2);
			(mem.getMemory()[addres])=instruction.getOperand1(); 
			 System.out.println("Executing :  SB   R" + Integer.parseInt(instruction.getRregister(),2) +"  "+ instruction.getOperand2() );
			 System.out.println("Memory Address "+addres +" value changed to " + getTwosComplement(instruction.getOperand1()));

			break;
		}}
	}

	public String ADD(String operand1, String operand2, Register status) {
		
		int c1 = 0, cOut = 0;
		String result = "";
		for (int i = 7; i >= 0; i--) {
			String r1 = String.valueOf(operand1.charAt(i));
			String r2 = String.valueOf(operand2.charAt(i));
			int intResult = Integer.parseInt(r1) + Integer.parseInt(r2) + cOut;
			if (i == 0) {
				c1 = cOut;
			}
			if (intResult == 2) {
				cOut = 1;
				result = '0' + result;
			} else if (intResult == 3) {
				cOut = 1;
				result = '1' + result;
			} else if (intResult == 1) {
				cOut = 0;
				result = '1' + result;
			} else {
				cOut = 0;
				result = '0' + result;
			}
		}
		
		char N = result.charAt(0);
		String V = String.valueOf(cOut ^ c1);
		char Z;
		if ((result.equals("00000000")))
			Z = '1';
		else
			Z = '0';

		status.setData("000" + cOut + V + N + (N ^ V.charAt(0)) + Z);
		
		
		
		return result;
	}

	public String SUB(String operand1 , String operand2, Register status) {
		
		for (int i = 0; i < operand2.length(); i++) {
			if (operand2.charAt(i) == '0') {
				operand2 = operand2.substring(0, i) + '1' + operand2.substring(i + 1);
			} else
				operand2 = operand2.substring(0, i) + '0' + operand2.substring(i + 1);
		}
		
		String add1Data = ADD(operand2, "00000001", status);
		
		
		String result = ADD(operand1, add1Data, status);
		System.out.println(result);
	     return result;
	     
		
		
	}

	public String MULI(String operand1, String operand2, Register status) {
		
		int f = Integer.parseInt(operand1, 2);
		int d = Integer.parseInt(operand2, 2);
		
		String result = operand1;
		
		for (int i = 1; i < d; i++) {
			result = ADD(result,operand1 , status);
		}
		
		
		
		return result;
	}

	public String AND(String r1, String r2, Register status) {
		String result = "";
	
		for (int i = 0; i < r1.length(); i++) {
			if (r1.charAt(i) == '1' && r2.charAt(i) == '1') {
				result = result + '1';
			} else {
				result = result + '0';
			}
			
		}
		char Z;
		
		if ((result.equals("00000000")))
			Z = '1';
		else
			Z = '0';
		char N = result.charAt(0);
		char cOut= status.getData().charAt(3);
		char V= status.getData().charAt(4);		
		status.setData("000" + cOut + V + N + (N ^ V) + Z);
		return result;

	}

	
	public String OR(String r1, String r2, Register status) {
		String result = "";
		for (int i = 0; i < r1.length(); i++) {
			if (r1.charAt(i) == '1' || r2.charAt(i) == '1') {
				result = result + '1';
			} else {
				result = result + '0';
			}
		}
		char Z;
		if ((result.equals("00000000")))
			Z = '1';
		else
			Z = '0';
		
		
		char N = result.charAt(0);
		char cOut= status.getData().charAt(3);
		char V= status.getData().charAt(4);		
		status.setData("000" + cOut + V + N + (N ^ V) + Z);
		return result;

	}

	public int JR(String r1, String r2, Register status) {
		String k = "";
		k = r1 + r2;
		int result = Integer.parseInt(k, 2);
		return result; // to be inserted into PC
	}

	public int BEQZ(String r1, String operand2, Register status, PC pc) {
		int k = 0;
		
		if (r1.equals("00000000")) {
			k =  (pc.getPc()-2)+ 1 + getTwosComplement(operand2);
		}
		else {
			k=pc.getPc();
		}
			
		return k; // to be inserted into PC
	}

	public String SRC(String op1, String op2, Register status) {
		int r = Integer.parseInt(op1, 2);
		int r2 = Integer.parseInt(op2, 2);
		int x = (r >>> r2) | (r << (8 - r2));
		String k = Integer.toBinaryString(x);
		String result = "";

		if (k.length() > 8) {
			result = k.substring(k.length() - 8);
		} else {
			result = k;
		}
		char Z;
		if ((result.equals("00000000")))
			Z = '1';
		else
			Z = '0';
		char N = result.charAt(0);
		char cOut= status.getData().charAt(3);
		char V= status.getData().charAt(4);		
		status.setData("000" + cOut + V + N + (N ^ V) + Z);
		return result;
	}

	public String SLC(String op1, String op2, Register status) {
		
		int r = Integer.parseInt(op1, 2);
		int r2 = Integer.parseInt(op2, 2);
		int x = (r << r2) | (r >>> (8 - r2));
		String k = Integer.toBinaryString(x);
		String result = "";

		if (k.length() > 8) {
			result = k.substring(k.length() - 8);
		} else {
			result = k;
		}
		while(result.length()<8){
			result="0"+result;
		}
		char Z;
		if ((result.equals("00000000")))
			Z = '1';
		else
			Z = '0';
		char N = result.charAt(0);
		char cOut= status.getData().charAt(3);
		char V= status.getData().charAt(4);		
		status.setData("000" + cOut + V + N + (N ^ V) + Z);
		return result;
	}

	public static int getTwosComplement(String binaryInt) {
	    //Check if the number is negative.
	    //We know it's negative if it starts with a 1
	    if (binaryInt.charAt(0) == '1') {
	        //Call our invert digits method
	        String invertedInt = invertDigits(binaryInt);
	        //Change this to decimal format.
	        int decimalValue = Integer.parseInt(invertedInt, 2);
	        //Add 1 to the curernt decimal and multiply it by -1
	        //because we know it's a negative number
	        decimalValue = (decimalValue + 1) * -1;
	        //return the final result
	        return decimalValue;
	    } else {
	        //Else we know it's a positive number, so just convert
	        //the number to decimal base.
	        return Integer.parseInt(binaryInt, 2);
	    }
	}

	

	private static String invertDigits(String binaryInt) {

		for (int i = 0; i < binaryInt.length(); i++) {
			if (binaryInt.charAt(i) == '0') {
				binaryInt = binaryInt.substring(0, i) + '1' + binaryInt.substring(i + 1);
			} else
				binaryInt = binaryInt.substring(0, i) + '0' + binaryInt.substring(i + 1);
		}
		
		return binaryInt;
	}

	public static void main(String[] args) {
//		
		System.out.println(getTwosComplement("000010"));
//		
		
	}
}
