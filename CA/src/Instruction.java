
public class Instruction {
	String Opcode;
	String operand1,operand2;
	String Rregister ;
	

	public String getOperand1() {
		return operand1;
	}



	public void setOperand1(String operand1) {
		this.operand1 = operand1;
	}



	public String getOperand2() {
		return operand2;
	}



	public void setOperand2(String operand2) {
		this.operand2 = operand2;
	}



	public String getRregister() {
		return Rregister;
	}



	public void setRregister(String rregister) {
		Rregister = rregister;
	}



	public Instruction(String Opcode, String  operand1, String  operand2,String Rregister) {
		this.Opcode = Opcode;
		this.operand1 = operand1;
		this.operand2 = operand2;
		this.Rregister=Rregister;
}
	


	public String getOpcode() {
		return Opcode;
	}
	public void setOpcode(String opcode) {
		Opcode = opcode;
	}
	
	public String toString() {
		return Opcode +" "+operand1+" "+operand2+" "+Rregister;
		
	}
}
