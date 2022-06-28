package KockaPoker;

public class PlayerWin {
	
	public void PlayerWin() {
		int pStrength =0;
		int eStrength = 0;
		
		String pGotPont = "" ;
		String eGotPont ="";
		pStrength =0;
		eStrength = 0;

		int pDice[] = new int [5];
		int eDice[] = new int [5];
		
		for(int i=0;i<KockaPoker.te_kockak.size();i++) {
			pDice[i]= KockaPoker.te_kockak.get(i).value;
			eDice[i]= KockaPoker.ellenfel_kockak.get(i).value;
		}
		int [] Phas={0,0,0,0,0,0};
		int [] Ehas={0,0,0,0,0,0};
		int pLegmagasabbRoll=0;
		int eLegmagasabbRoll=0;
		for(int i=0;i<5;i++) {
			if(pDice[i]==1) {
				Phas[0]++;//ennyi darab 1-ese van az 1-esekbõl
			}if(pDice[i]==2) {
				Phas[1]++;//ennyi darab 2-ese van az 2-esekbõl
			}if(pDice[i]==3) {
				Phas[2]++;//ennyi darab 3-ese van az 3-esekbõl
			}if(pDice[i]==4) {
				Phas[3]++;//ennyi darab 4-ese van az 4-esekbõl
			}if(pDice[i]==5) {
				Phas[4]++;//ennyi darab 5-ese van az 5-esekbõl
			}if(pDice[i]==6) {
				Phas[5]++;//ennyi darab 6-ese van az 6-esekbõl
			}
		}
		if(Phas[5]>0) {
			pLegmagasabbRoll=6;
		}else {if(Phas[4]>0) {
			pLegmagasabbRoll=5;
		}else {if(Phas[3]>0) {
			pLegmagasabbRoll=4;
		}
		}	
		}
		for(int i=0;i<5;i++) {
			if(eDice[i]==1) {
				Ehas[0]++;//ennyi darab 1-ese van az 1-esekbõl
			}if(eDice[i]==2) {
				Ehas[1]++;//ennyi darab 2-ese van az 2-esekbõl
			}if(eDice[i]==3) {
				Ehas[2]++;//ennyi darab 3-ese van az 3-esekbõl
			}if(eDice[i]==4) {
				Ehas[3]++;//ennyi darab 4-ese van az 4-esekbõl
			}if(eDice[i]==5) {
				Ehas[4]++;//ennyi darab 5-ese van az 5-esekbõl
			}if(eDice[i]==6) {
				Ehas[5]++;//ennyi darab 6-ese van az 6-esekbõl
			}
		}
		if(Ehas[5]>0) {
			eLegmagasabbRoll=6;
		}else {if(Ehas[4]>0) {
			eLegmagasabbRoll=5;
		}else {if(Ehas[3]>0) {
			eLegmagasabbRoll=4;
		}
		}	
		}
		for(int i=0;i<6;i++) {
			if(Phas[i]==5) {
				//Five-of-a-Kind
				pGotPont=pGotPont+Integer.toString(i+1);
				pStrength=9;
			}
			if(Phas[i]==4 && pStrength<8) {
				//Four-of-a-Kind
				pGotPont=pGotPont+Integer.toString(i+1);
				pStrength=8;
			}
			if(Phas[i]==3 && pStrength<7) {
				for(int j=0;j<6;j++) {
					if(Phas[j]==2 && i!=j) {
						//Full House
						pGotPont=pGotPont+Integer.toString(i+1);
						pStrength=7;
						 ;
					}
				}
			}
			if((Phas[1]==1 && Phas[2]==1 && Phas[3]==1 && Phas[4]==1 && Phas[5]==1) && pStrength<6) {
				//Six High Straight
				pGotPont=pGotPont+Integer.toString(i+1);
				pStrength=6;
			}
			if((Phas[0]==1 && Phas[1]==1 && Phas[2]==1 && Phas[3]==1 && Phas[4]==1)&& pStrength<5) {
				//Five High Straight
				pGotPont=pGotPont+Integer.toString(i+1);
				pStrength=5;
			}
			if(Phas[i]==3 && pStrength<4) {
				System.out.println("3 db "+Phas[i]);
				//three-of-a-kind
				pGotPont=pGotPont+Integer.toString(i+1);
				pStrength=4;
			}
			if(Phas[i]==2 && pStrength<3) {
				System.out.println("egy pár "+Phas[i]);
				for(int j=0;j<6;j++) {
					if(Phas[j]==2 && i!=j) {
						System.out.println("két pár");
						//Two Pairs
						pGotPont=pGotPont+Integer.toString(i+1);
						pStrength=3;
						 ;
					}
				}
			}
			if(Phas[i]==2 && pStrength<2) {
				//Pair
				pGotPont=pGotPont+Integer.toString(i+1);
				pStrength=2;
			}
			if(pStrength==0){
				//nothing
				pStrength=1;
			}
		}
		
		for(int i=0;i<6;i++) {
			if(Ehas[i]==5) {
				//Five-of-a-Kind
				eGotPont=eGotPont+Integer.toString(i+1);
				eStrength=9;
			}
			if(Ehas[i]==4 && eStrength<8) {
				//Four-of-a-Kind
				eGotPont=eGotPont+Integer.toString(i+1);
				eStrength=8;
			}
			if(Ehas[i]==3 && eStrength<7) {
				for(int j=0;j<6;j++) {
					if(Ehas[j]==2 && i!=j) {
						//Full House
						eGotPont=eGotPont+Integer.toString(i+1);
						eStrength=7;
					}
				}
			}
			if((Ehas[1]==1 && Ehas[2]==1 && Ehas[3]==1 && Ehas[4]==1 && Ehas[5]==1) && eStrength<6) {
				//Six High Straight
				eGotPont=eGotPont+Integer.toString(i+1);
				eStrength=6;
			}
			if((Ehas[0]==1 && Ehas[1]==1 && Ehas[2]==1 && Ehas[3]==1 && Ehas[4]==1) && eStrength<5) {
				//Five High Straight
				eGotPont=eGotPont+Integer.toString(i+1);
				eStrength=5;
			}
			if(Ehas[i]==3 && eStrength<4) {
				//three-of-a-kind
				eGotPont=eGotPont+Integer.toString(i+1);
				eStrength=4;
			}
			if(Ehas[i]==2 && eStrength<3) {
				for(int j=0;j<6;j++) {
					if(Ehas[j]==2 && i!=j) {
						//Two Pairs
						eGotPont=eGotPont+Integer.toString(i+1);
						eStrength=3;
						 ;
					}
				}
			}
			if(Ehas[i]==2 && eStrength<2) {
				//Pair
				eGotPont=eGotPont+Integer.toString(i+1);
				eStrength=2;
			}
			if(eStrength==0){
				//nothing
				eStrength=1;
			}
		}
		System.out.println("Player Stregth: "+pStrength);
		System.out.println("Ellenfél Stregth: "+eStrength);
		if(eStrength<pStrength) {
			KockaPoker.playerWining=true;
		}
		if(eStrength>pStrength) {
			KockaPoker.playerWining=false;
		}
		if(eStrength==pStrength) {
			int playerMax=0;
			int enemyMax=0;
			String playerPontTop[]=pGotPont.split("0");
			String enemyPontTop[]=eGotPont.split("0");
			for(int i=0;i<playerPontTop.length;i++) {
				if(Integer.parseInt(playerPontTop[i])>playerMax) {
					playerMax=Integer.parseInt(playerPontTop[i]);
				}
				if(Integer.parseInt(enemyPontTop[i])>enemyMax) {
					enemyMax=Integer.parseInt(enemyPontTop[i]);
				}
			}
			if(playerMax>enemyMax) {
				KockaPoker.playerWining=true;
			}
			if(playerMax<enemyMax) {
				KockaPoker.playerWining=false;
			}
			if(eStrength==1 && pStrength==1) {
				if(pLegmagasabbRoll>eLegmagasabbRoll) {
					KockaPoker.playerWining=true;
				}else {
					KockaPoker.playerWining=false;	
				}
			}else {
			//ha power nem egy de minden egyezik vagy minden egyezik kivéve az értéktelent akkor döntetlen
			if(playerMax==enemyMax) {
				KockaPoker.draw=true;
			}
			}
		}
		System.out.println("Player nyert: "+KockaPoker.playerWining);
		System.out.println("Lett döntetlen: "+KockaPoker.draw);
		
	}

}
