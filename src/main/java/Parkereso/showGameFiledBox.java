package Parkereso;

import java.util.List;

import javax.swing.JPanel;

public class showGameFiledBox {
	
	public void showGameFiledBox(int x, int y, List<JPanel> imagePanel, List<JPanel> gameFiledBoxes) {
		
		if(x==2) {
			for(int i=0;i<imagePanel.size();i++) {
				gameFiledBoxes.get(0).add(imagePanel.get(i));
			}
		}
		if(x==4) {
			System.out.println("jo");
			for(int i=0;i<imagePanel.size();i++) {
				gameFiledBoxes.get(1).add(imagePanel.get(i));
			}
		}
		if(x==6) {
			for(int i=0;i<imagePanel.size();i++) {
				gameFiledBoxes.get(2).add(imagePanel.get(i));
			}
		}
		
	}

}
