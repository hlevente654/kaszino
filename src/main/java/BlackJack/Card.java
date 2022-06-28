package BlackJack;

public class Card {
	
	int number;
	String shape;
	boolean cardUsed = false;
	String symbol;
	String name;
	int id;
	int value;
	
	public Card(int n, String s,int id) {
		this.number = n;
		this.shape = s;
		this.id = id;
		
		if(number < 11) {
			symbol = Integer.toString(number);
			name = Integer.toString(number);
			value = number;
		} else if(number == 11) {
			symbol = "J";
			name = "Jack";
			value = 10;
		} else if(number == 12) {
			symbol = "Q";
			name = "Queen";
			value = 10;
		} else if(number == 13) {
			symbol = "K";
			name = "King";
			value = 10;
		} else {
			symbol = "A";
			name = "Ace";
			value = 1;
		}
	}
	public void setUsed() {
		cardUsed = true;
	}
	public void setNotused() {
		cardUsed = false;
	}
}
