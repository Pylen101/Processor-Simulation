public class data_memory {
	String[] memory;

	public String[] getMemory() {
		return memory;
	}

	public void setMemory(String[] memory) {
		this.memory = memory;
	}

	public data_memory() {
		memory = new String[2048];
	}

	public void load_Data(int index, Register Reg) {
		Reg.data = memory[index];

	}

	public void Store_Data(int index, Register Reg) {
		memory[index] = Reg.data;
	}
}
