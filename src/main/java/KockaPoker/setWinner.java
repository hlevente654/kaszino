package KockaPoker;

import alapPackage.Message;
import alapPackage.javaSql;
import asztalok.tet;

public class setWinner {
	
	public void setWinnerM() {
		if(KockaPoker.playerWining==true) {
			KockaPoker.Log.add(new Message("Player wins!","Player"));
			javaSql.setMoney(Integer.toString((javaSql.getMoney(KockaPoker.bejelentkez.get(0).login)+tet.getTet())), KockaPoker.bejelentkez.get(0).login);
			javaSql.gameLog(KockaPoker.bejelentkez.get(0).login, Integer.toString(tet.getTet()));
		}
		if(KockaPoker.playerWining==false) {
			KockaPoker.Log.add(new Message("Enemy wins!","Enemy"));
			javaSql.setMoney(Integer.toString((javaSql.getMoney(KockaPoker.bejelentkez.get(0).login)-tet.getTet())), KockaPoker.bejelentkez.get(0).login);
			javaSql.gameLog(KockaPoker.bejelentkez.get(0).login, Integer.toString(tet.getTet()*-1));
		}
		if(KockaPoker.draw==true) {
			KockaPoker.Log.add(new Message("Nobody wins!","Enemy"));
		}
		KockaPoker.haveWinner=true;
	}

}
