package Parkereso;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class putOnTable {
	public List<ImageIcon> putOnTableM(List<ImageIcon> felforditottImageScaledIcons,int x,int y){
		List<ImageIcon> onTableCards =new ArrayList();
		List<ImageIcon> ideiglenesTarolo = new ArrayList();
		ideiglenesTarolo = felforditottImageScaledIcons;

		String usedCards="";
		int id;
		int neededNumberOfCards = (x*y)/2;
		for(int i=0;i<neededNumberOfCards;i++) {
			do {
				 id = (int)(Math.random() * felforditottImageScaledIcons.size()); 
			}while(usedCards.contains(Integer.toString(id)));
			usedCards=usedCards+Integer.toString(id)+" ";
			ImageIcon imageScaledIcon = null;
			getScaledImage getscaledimage = new getScaledImage();
			if(x == 2) {
			imageScaledIcon = new ImageIcon(getscaledimage.getScaledImageM(felforditottImageScaledIcons.get(id).getImage(),100,120));
			}else {
				if(x == 4) {
					imageScaledIcon = new ImageIcon(getscaledimage.getScaledImageM(felforditottImageScaledIcons.get(id).getImage(),80,100));
				}else {
					if(x == 6) {
						imageScaledIcon = new ImageIcon(getscaledimage.getScaledImageM(felforditottImageScaledIcons.get(id).getImage(),70,70));
					}
				}
			}
			onTableCards.add(imageScaledIcon);
			onTableCards.add(imageScaledIcon);
			
			ideiglenesTarolo.remove(i);
		}
		
		return osszeKever(onTableCards,x,y);
	}
	
	private  List<ImageIcon> osszeKever(List<ImageIcon> onTableCards, int x, int y){
		List<ImageIcon> kevert = new ArrayList();
		int numberOfC=(x*y);
		List<Integer> idSor=new ArrayList();
		for(int i=0;i<numberOfC;i++) {
			idSor.add(i);
		}
		
		int i=0;
		System.out.println("meret: "+onTableCards.size());
		while(idSor.size()>0) {
			
			int random = (int) (Math.random()*idSor.size());
			int id=idSor.get(random);
			idSor.remove(random);
			kevert.add(onTableCards.get(id));
			Parkereso.Mapgrids.put(i, id);
			i++;
		}	
		
		System.out.println(Parkereso.Mapgrids.get(0)+" "+Parkereso.Mapgrids.get(1));
		System.out.println(Parkereso.Mapgrids.get(2)+" "+Parkereso.Mapgrids.get(3));
		return kevert;
	}
}
