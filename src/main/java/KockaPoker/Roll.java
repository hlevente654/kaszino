package KockaPoker;

public class Roll {
	
	//roll with 1 dice
		public int rollM() {
			int dobott = 0;
			dobott = (int)(Math.random() * 6)+1;
			return dobott;
		}

}
