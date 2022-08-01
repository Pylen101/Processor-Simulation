public class instruction_memory {
	String[] memory;

	public instruction_memory() {
		memory = new String[1024];
	}

	public String load_Data(int pc) {
		return memory[pc];

	}

	public String[] getMemory() {
		return memory;
	}

	public void setMemory(String[] memory) {
		this.memory = memory;
	}

}
