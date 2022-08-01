import java.io.FileNotFoundException;
import java.util.ArrayList;



public class Main {

	public static void main(String[] args) throws FileNotFoundException {
	
		Boolean jump=false;
		int cycles = 0;
		Register status = new Register("0000000");
		PC pc = new PC();
		instruction_memory instructionMemory = new instruction_memory();
		data_memory dataMemory = new data_memory();
		ArrayList<Register> registerList = new ArrayList<Register>();
		for (int i = 0; i < 64; i++) {
			registerList.add(new Register(""));
		}
		

		Parser parser = new Parser("test.txt");
		parser.readFile(instructionMemory);
		// System.out.println(instructionMemory.memory[0]);
		Fetcher fetcher = new Fetcher(pc, instructionMemory);
		Decoder decoder = new Decoder();
		Executer executer = new Executer();
		String instruction1 = "";
		Instruction instruction2 = null;
		Instruction temp2 = null;

		while (true) {
			System.out.println("start of cycle : " + cycles);
			System.out.println("PC = " + pc.getPc());
			
			
			executer.execute(instruction2, status, pc, dataMemory,registerList);
			if( instruction2 !=null && instruction2.getOpcode().equalsIgnoreCase("0100") && instruction2.getOperand1().equalsIgnoreCase("000000") )
			{
				jump=true;
			}
			if(instruction2 !=null && instruction2.getOpcode().equalsIgnoreCase("0111"))
			{
				jump=true;
				pc.setPc(pc.getPc()-1);
			}
			
			temp2 = decoder.decode(instruction1, registerList);
			
			
			String temp1 = fetcher.fetch();
			
			
			
			instruction1 = temp1;
			instruction2 = temp2;
			System.out.println("status Register = "+status.data);
			/// Printing Reg file and Memory
			Register curr ;
			for(int i=0;i<registerList.size();i++)
			{
				
				curr = registerList.get(i);
				if(!curr.getData().equalsIgnoreCase(""))
				{
				System.out.println("R"+i+" = "+ curr.getData()) ;
				
			}}
			
			for(int i=0;i<dataMemory.getMemory().length;i++)
			{
				
				if(!(dataMemory.getMemory()[i]==null))
				{
					System.out.println("Address "+i +" = "+dataMemory.getMemory()[i]);
					
			}}
			///
			if( jump )
			{
				instruction1="";
				instruction2=null;
				jump=false;

			}
			if (instruction1 == null && instruction2 == null)
			{
				System.out.println("----------------------------------------------------------");
				System.out.println();
				Register curr1 ;
				for(int i=0;i<registerList.size();i++)
				{
					
					curr1 = registerList.get(i);
					if(!curr1.getData().equalsIgnoreCase("")) {
					System.out.println("R"+i+" = "+ curr1.getData()) ;
					
					}else
					{
						System.out.println("R"+i+" = empty" ) ;
					
					}
					
				}
				for(int i=0;i<dataMemory.getMemory().length;i++)
				{
					if(!(dataMemory.getMemory()[i]==null)) {
						System.out.println("Address "+i +" = "+dataMemory.getMemory()[i]);
						
					}else
					{
						System.out.println("Address "+i +" = empty");

					}
				}
				
				for(int i=0;i<instructionMemory.getMemory().length;i++)
				{
					if(!(instructionMemory.getMemory()[i]==null))
						System.out.println("Address "+i +" = "+instructionMemory.getMemory()[i]);
					else
					{
						System.out.println("Address "+i +" = empty");

					}	
					
					
			}
				System.out.println("PC ="+pc.getPc());
				System.out.println("status register ="+status.getData());
		
				break;
			}
			
			
			
			
			cycles++;
			System.out.println();
			System.out.println("-----------------------------------------------------");
		
			
		}
		
		
	}
	
}
