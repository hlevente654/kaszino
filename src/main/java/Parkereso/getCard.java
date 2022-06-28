package Parkereso;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class getCard{
	 public List<ImageIcon> getCardM(){
		List<ImageIcon> felforditottImageScaledIcons = new ArrayList();
		
		final File dir = new File("cardsImgs");
		
		final String[] kivetel = new String[]{"png"};
		
	    final FilenameFilter szuro = new FilenameFilter() {

	        public boolean accept(final File dir, final String name) {
	            for (final String ext : kivetel) {
	                if (name.endsWith("back.png")) {
	                    return (false);
	                }
	            }
	            return (true);
	        }
	    };
		if(dir.isDirectory()) {
			for (final File f : dir.listFiles(szuro)) {
				BufferedImage img = null;

                try {
                    img = ImageIO.read(f);
                    
                    ImageIcon icon = new ImageIcon(img);
                    
                    felforditottImageScaledIcons.add(icon);
                } catch (final IOException e) {
                    // handle errors here
                }
			}
		}
		return felforditottImageScaledIcons;
	}
	 
}

