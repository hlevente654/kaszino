package KockaPoker;

public class Kocka {
	
	boolean kocka_id_usable=true;
	int kocka_id,value;
	String String_value;
	
	public Kocka(int kocka_id, int value) {
		this.kocka_id=kocka_id;
		this.value=value;
		this.String_value=Integer.toString(value);
	}
	
	public void setUsed() {
		kocka_id_usable = true;
	}
	
	public void setNotused() {
		kocka_id_usable = false;
	}
}
