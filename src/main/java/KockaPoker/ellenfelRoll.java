package KockaPoker;

import java.util.ArrayList;

import alapPackage.Message;

public class ellenfelRoll {
	
	public void ellenfelRollM() {
		Roll roll = new Roll();
		
		
		String dobottak="";
		String sorrend="";
		boolean hasValue1 = false,hasValue2 = false,hasValue3 = false,hasValue4 = false,hasValue5 = false,hasValue6 = false;
		int numberOf []= {0,0,0,0,0,0};
		for(int i=0;i<5;i++) {
			dobottak=dobottak+KockaPoker.ellenfel_kockak.get(i).value+" ";
			sorrend=sorrend+KockaPoker.ellenfel_kockak.get(i).kocka_id+" ";
			for(int j=0;j<6;j++) {
				if((j+1)==KockaPoker.ellenfel_kockak.get(i).value) {
					numberOf[j]++;
				}
			}
		}
		System.out.println("Egyesekböl van: "+numberOf[0]);
		System.out.println("Kettesbõl van: "+numberOf[1]);
		System.out.println("Hármasból van: "+numberOf[2]);
		System.out.println("Négyesbõl van: "+numberOf[3]);
		System.out.println("Ötösbõl van: "+numberOf[4]);
		System.out.println("Hatosból van: "+numberOf[5]);
		
		if(numberOf[0]>1) {hasValue1=true;}
		if(numberOf[1]>1) {hasValue2=true;}
		if(numberOf[2]>1) {hasValue3=true;}
		if(numberOf[3]>1) {hasValue4=true;}
		if(numberOf[4]>1) {hasValue5=true;}
		if(numberOf[5]>1) {hasValue6=true;}
		
		String rerolled="";
		if(KockaPoker.playerWining==true || KockaPoker.draw==true) {
			
			if(hasValue1==false) {
				for(int i=0;i<5;i++){
					if(KockaPoker.ellenfel_kockak.get(i).value==1)
					 {
						rerolled=rerolled+KockaPoker.ellenfel_kockak.get(i).value+" ";
						KockaPoker.ellenfel_kockak.get(i).value=roll.rollM();
					}
				}
			}
			if(hasValue2==false) {
				for(int i=0;i<5;i++){
					if(KockaPoker.ellenfel_kockak.get(i).value==2)
					 {
						rerolled=rerolled+KockaPoker.ellenfel_kockak.get(i).value+" ";
						KockaPoker.ellenfel_kockak.get(i).value=roll.rollM();
					}
				}
			}
			if(hasValue3==false) {
				for(int i=0;i<5;i++){
					if(KockaPoker.ellenfel_kockak.get(i).value==3)
					 {
						rerolled=rerolled+KockaPoker.ellenfel_kockak.get(i).value+" ";
						KockaPoker.ellenfel_kockak.get(i).value=roll.rollM();
					}
				}
			}
			if(hasValue4==false) {
				for(int i=0;i<5;i++){
					if(KockaPoker.ellenfel_kockak.get(i).value==4)
					 {
						rerolled=rerolled+KockaPoker.ellenfel_kockak.get(i).value+" ";
						KockaPoker.ellenfel_kockak.get(i).value=roll.rollM();
					}
				}
			}
			if(hasValue5==false) {
				for(int i=0;i<5;i++){
					if(KockaPoker.ellenfel_kockak.get(i).value==5)
					 {
						rerolled=rerolled+KockaPoker.ellenfel_kockak.get(i).value+" ";
						KockaPoker.ellenfel_kockak.get(i).value=roll.rollM();
					}
				}
			}
			if(hasValue6==false) {
				for(int i=0;i<5;i++){
					if(KockaPoker.ellenfel_kockak.get(i).value==6)
					 {
						rerolled=rerolled+KockaPoker.ellenfel_kockak.get(i).value+" ";
						KockaPoker.ellenfel_kockak.get(i).value=roll.rollM();
					}
				}
			}
		}
		String mess="";
		if(!rerolled.contains("1")||!rerolled.contains("2")||!rerolled.contains("3")||!rerolled.contains("4")||!rerolled.contains("5")||!rerolled.contains("6")) {
			mess="Enemy decided to stay!";
		}else {mess="Enemy rerolled: "+rerolled;}
		KockaPoker.Log.add(new Message(mess,"Enemy"));
		
		PlayerWin playerwin = new PlayerWin();
		playerwin.PlayerWin();
	}

}
