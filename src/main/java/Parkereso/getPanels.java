package Parkereso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class getPanels {

	List<JPanel> getPanelsM( List<JLabel> imageLabels){
		List<JPanel> imagePanel = new ArrayList<>();
		HashMap<Integer , JPanel> panelek = new HashMap<>();
		for(int i=0;i<imageLabels.size();i++) {
			panelek.put(i, new JPanel());
			panelek.get(i).add(imageLabels.get(i));
			imagePanel.add(panelek.get(i));
		}
		
		return imagePanel;
	}
	
}
