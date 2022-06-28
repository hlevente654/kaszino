package KockaPoker;

import java.util.ArrayList;

public class Reroll {
	
	public void reroll(ArrayList <Kocka> lista) {
		Roll roll = new Roll();
		
		String buffer="";
		for(int i=0;i<KockaPoker.dobott_kockak.size();i++) {
			buffer =buffer+ KockaPoker.dobott_kockak.get(i).kocka_id;
		}
			String ideigL[] = buffer.split("");
			
			for(int i=0;i<lista.size();i++) {
				for(int j=0;j<KockaPoker.dobott_kockak.size();j++) {
					if(lista.get(i).kocka_id==KockaPoker.dobott_kockak.get(j).kocka_id) {
						lista.get(i).value=roll.rollM();
					}
					else
					{
						lista.get(i).kocka_id_usable = false;
					}
				}
			}
			KockaPoker.dobott_kockak.clear();
	}

}
