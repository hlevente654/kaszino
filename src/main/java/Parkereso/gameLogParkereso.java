package Parkereso;

import alapPackage.GUI;
import alapPackage.javaSql;

public class gameLogParkereso {

	public void gamelog() {
		String id = (javaSql.getId(GUI.getBejelentkezve().get(0).login));
		
		String adat=javaSql.getGameLog(id);;
		
		String adatok[]=adat.split(";");
		GUI.l1.clear();
		for(int i=0;i<adatok.length;i++) {
			GUI.l1.addElement(adatok[i]);
		}
	}
}
