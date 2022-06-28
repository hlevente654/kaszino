package Parkereso;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class getNumberOfBack {

	public List<ImageIcon> getNumberOfBackM(int x,int y){
		List<ImageIcon> imageScaledIcons = new ArrayList<>();
		HashMap<Integer , BufferedImage> bufferer = new HashMap<>();
		
		for(int i=0;i<x*y;i++) {
			bufferer.put(i, null);
			BufferedImage ideiglenesBI = bufferer.get(i);
			
			try {
				ideiglenesBI= ImageIO.read(new File("cardsImgs/back.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			bufferer.put(i, ideiglenesBI);
			ImageIcon imageScaledIcon = null;
			getScaledImage getscaledimage = new getScaledImage();
			if(x == 2) {
			imageScaledIcon = new ImageIcon(getscaledimage.getScaledImageM(bufferer.get(i),100,120));
			}else {
				if(x == 4) {
					imageScaledIcon = new ImageIcon(getscaledimage.getScaledImageM(bufferer.get(i),80,100));
					}else {
						if(x == 6) {
							imageScaledIcon = new ImageIcon(getscaledimage.getScaledImageM(bufferer.get(i),70,70));
							}
					}
			}
			imageScaledIcons.add(imageScaledIcon);
		}
		Parkereso.tartalekHatter = imageScaledIcons.get(0);
		return imageScaledIcons;
	}
}
