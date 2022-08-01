import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser {
	String dataPath;
	public Parser(String dataPath) {
		this.dataPath = dataPath;
	}
	public void readFile(instruction_memory I) throws FileNotFoundException {
		File file = new File(this.dataPath);
		Scanner scan = new Scanner(file);
		int i = 0;
		
		while(scan.hasNextLine()) {
			String line = scan.nextLine();
			String[] splited = line.split(" ");
			String binaryInstruction="";
			switch(splited[0].toLowerCase()) {
			case "add": binaryInstruction= "0000";break;
			case "sub": binaryInstruction= "0001";break;
			case "mul": binaryInstruction= "0010";break;
			case "ldi": binaryInstruction= "0011";break;
			case "beqz": binaryInstruction= "0100";break;
			case "and": binaryInstruction= "0101";break;
			case "or": binaryInstruction= "0110";break;
			case "jr": binaryInstruction= "0111";break;
			case "slc": binaryInstruction= "1000";break;
			case "src": binaryInstruction= "1001";break;
			case "lb": binaryInstruction= "1010";break;
			case "sb": binaryInstruction= "1011";break;
			}
			String decimalString = splited[1].substring(1,splited[1].length());
			
			int decimal = Integer.parseInt(decimalString);
			String binary = Integer.toBinaryString(decimal);
			if(decimal<0) {
				binary = binary.substring(binary.length()-6,binary.length());
			}else
				while(binary.length()<6) {
					binary = "0"+binary;
				}
			binaryInstruction +=binary;

			switch(splited[0].toLowerCase()) {
			
			case "add": 
			case "sub": 
			case "and": 
			case "or": 
			case "jr": 
			case "mul":String decimalString2 = splited[2].substring(1,splited[2].length());
					int decimal2 = Integer.parseInt(decimalString2);
					String binary2 = Integer.toBinaryString(decimal2);
					while(binary2.length()<6) {
						binary2 = "0"+binary2;
					}
					binaryInstruction+=binary2;
					break;
			case "ldi":		
			case "beqz": 
			case "slc": 
			case "src":int decimal3 = Integer.parseInt(splited[2]);
				String binary3 = Integer.toBinaryString(decimal3);
				while(binary3.length()<6) {
					binary3 = "0"+binary3;
				}
				if(decimal3<0)
				{
					binary3=binary3.substring(26);
				}
				binaryInstruction+=binary3;
				
				break;
			case "lb": 
			case "sb": binaryInstruction+=splited[2];break;
			}
			I.memory[i] = binaryInstruction;
			
			i++;
			
		}
			
	}
	public static void main(String[] args) throws FileNotFoundException {
		
		
		
	}
}
