package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Parkereso.*;

class ParkeresoTest {

	
	//ez tesztelt: jó
	@Test
	@DisplayName("Pár ellenõrzõ: is pair true/false") 
	public void test_isPair() {
		isPair ispair = new isPair();
		boolean pair = ispair.isPairM(0, 1);
		boolean ans = true;
		
        assertEquals(ans,pair);	
	}
	
	@Test
	@DisplayName("visszaad 52 imageicont, ez tesztelt: jó") 
	public void getCardTest() {
		getCard getcard = new getCard();
		List<ImageIcon> AllnotBacks = new ArrayList();
		AllnotBacks = getcard.getCardM();
		assertEquals(52,AllnotBacks.size());
	}
	
	//ez tesztelt: jó
	@Test
	@DisplayName("kiszed annyit az összes kártyábol ammenyi éppen kell") 
	public void putOnTableTest() {
		putOnTable putontabletest = new putOnTable();
		getCard getcard = new getCard();
		List<ImageIcon> AllnotBacks = new ArrayList();
		AllnotBacks = getcard.getCardM();
		
		List<ImageIcon> neededCards = new ArrayList();
		int x=2,y=2;
		neededCards=putontabletest.putOnTableM(AllnotBacks, x, y);
		assertEquals(x*y,neededCards.size());
	}
	
	//ez tesztelt: jó
	@Test
	@DisplayName("kiszed annyi back-et ammenyi éppen kell")
	public void getNumberOfBackTest() {
		getNumberOfBack getnumberofback = new getNumberOfBack();
		int x=2,y=2;
		List<ImageIcon> backs= getnumberofback.getNumberOfBackM(x, y);
		assertEquals(x*y,backs.size());
	}
	
	//ez tesztelt: jó
	@Test
	@DisplayName("lessz e annyi label amenyi kárgya van?") 
	public void ParkeresogetLabelsTest(){
		Parkereso parkereso = new Parkereso();
		getNumberOfBack getnumberofback = new getNumberOfBack();
		int x=2,y=2;
		List<ImageIcon> backs= getnumberofback.getNumberOfBackM(x, y);
		
		
		getCard getcard = new getCard();
		List<ImageIcon> AllnotBacks = new ArrayList();
		AllnotBacks = getcard.getCardM();
		putOnTable putontabletest = new putOnTable();
		List<ImageIcon> cards = new ArrayList();
		cards=putontabletest.putOnTableM(AllnotBacks, x, y);
		
		List<JLabel> listEnerLabels = new ArrayList();
		listEnerLabels = parkereso.getLabels(backs, cards, x, y);
		assertEquals(x*y,listEnerLabels.size());
		
	}

}
