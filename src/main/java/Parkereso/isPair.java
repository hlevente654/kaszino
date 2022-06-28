package Parkereso;

public class isPair {
	
	public boolean isPairM(int firstClickValue,int secondClickValue) {
		boolean isPair = false;
		if(firstClickValue%2==0 ) {
			if(secondClickValue%2==1) {
				int gep=secondClickValue-firstClickValue;
				if(firstClickValue<secondClickValue && gep==1) {
					isPair = true;
				}
			}
		}
		if(firstClickValue%2==1 ) {
			if(secondClickValue%2==0) {
				int gep=firstClickValue-secondClickValue;
				if(firstClickValue>secondClickValue && gep==1) {
					isPair = true;
				}
			}
		}
		if(secondClickValue%2==0 ) {
			if(firstClickValue%2==1) {
				int gep=secondClickValue-firstClickValue;
				if(secondClickValue<firstClickValue && gep==1) {
					isPair = true;
				}
			}
		}
		
		return isPair;
	}

}
