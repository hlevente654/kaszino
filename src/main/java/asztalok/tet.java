package asztalok;

import alapPackage.GUI;
import alapPackage.javaSql;

public class tet {
	static int tet = 0;

	public static int rase(int max) {
		tet= tet+1000;
		
		if(tet>max) {
			tet = max;
		}
		
		return tet;
	}
	
	public static int lower(int min) {
		tet = tet-1000;
		if(tet <0) {
			tet = tet +1000;
		}

		return tet;
	}
	
	public static int getTet() {

			return tet;
	}
}
