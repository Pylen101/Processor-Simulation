
public class Register {
	int no=0;
	String data;
	
	public Register(String data) {
		++no;
		this.data = data;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
		
	}
//	public String toString() {
//		return data;
//	}
}
