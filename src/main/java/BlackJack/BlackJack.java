package BlackJack;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JPanel;

import alapPackage.GUI;
import alapPackage.Main;
import alapPackage.Message;
import alapPackage.javaSql;
import asztalok.tet;

public class BlackJack extends JPanel{
	
//integer used to generate random szám a kárgyához
	int rand = new Random ().nextInt(52);
	
//fonts
	public Font fontButton = new Font("Times New Roman", Font.PLAIN,30);
	
//player - dealer totals
		int total_player_max;
		int total_player_min;
		int total_dealer_max;
		int total_dealer_min;
	
//lista ami a kártyákat tartalmazza
	ArrayList <Card> allCards= new ArrayList <Card>();
	ArrayList <Card> playerCards = new ArrayList <Card>();
	ArrayList <Card> dealerCards = new ArrayList <Card>();
	
//boolean dealer dolgozik
	boolean dthinking = false;
	
//game phases booleans
	boolean bool_hit_stay = true;
	boolean bool_dealer_turn = false;
	boolean bool_play_more = false;
	
//kérdések
	String play_more_q = "Play more?";
	String hit_stay_q = "Hit or Stay?";
	
//fonts
	Font fontCard = new Font("Times New Roman", Font.BOLD,40);
	Font fontQuestion = new Font("Times New Roman", Font.PLAIN, 40);
	Font fontCim = new Font("Times New Roman", Font.BOLD,70);
	Font fontLogNev = new Font("Calibri", Font.PLAIN,25);
	
//gombok
	JButton bHit = new JButton();
	JButton bStay = new JButton();
	JButton bYes = new JButton();
	JButton bNo = new JButton();
	JButton bLeave = new JButton();
	JButton bRase = new JButton();
	JButton bLower = new JButton();
	
//GRID-ek
	int cardEdgeSofting = 10;
//kártya grid pozíció
	int kGridX = 50;
	int kGridY = 50;
	int kGridW = 1000;
	int kGridH = 500;
//pont grid pozíció
	int pGridX = 50;
	int pGridY = 50;
	int pGridW = 450;
	int pGridH = 280;
//black grid
	int bGridX = 50;
	int bGridY = 50;
	int bGridW = 1000;
	int bGridH = 400;
//score grid
	int sGridX = 50;
	int sGridY = 650;
	int sGridW = 225;
	int sGridH = 560;
		
//kártya hely és egyéb
	int cardTW = kGridW/6;
	int cardTH = (kGridH/6)*3;
	int cardSpaceing = 10;
	int cardAW = cardTW - 2*cardSpaceing;
	int cardAH = cardTH - 2*cardSpaceing;

//színek
	public Color colorBackground = new Color(102, 153, 0);
	public Color colorButton = new Color(204, 204, 0);
		
	public BlackJack(){
		this.setLayout(null);
		
		//HIT gombot adunk az ablakra
				ActHit aHit = new ActHit();
				bHit.addActionListener(aHit);
				bHit.setBounds((kGridX*2)+kGridW+((kGridW/2-kGridX)/2)+((sGridW-120)/2), kGridY+((sGridH*1)/10), 120, 80);
				bHit.setText("HIT");
				bHit.setBackground(colorButton);
				bHit.setFont(fontButton);
				this.add(bHit);
				
		//Stay gombot adunk az ablakra
				ActStay aStay = new ActStay();
				bStay.addActionListener(aStay);
				bStay.setBounds((kGridX*2)+kGridW+((kGridW/2-kGridX)/2)+((sGridW-120)/2), kGridY+((sGridH*7)/10), 120, 80);
				bStay.setText("Stay");
				bStay.setBackground(colorButton);
				bStay.setFont(fontButton);
				this.add(bStay);
				
		//YES gombot adunk az ablakra
				ActYes aYes = new ActYes();
				bYes.addActionListener(aYes);
				bYes.setBounds((kGridX*3)+kGridW-20, (kGridY+(kGridH-100)+(((kGridH-100)*5)/10))+(pGridH*6)/10, 120, 80);
				bYes.setText("YES");
				bYes.setBackground(colorButton);
				bYes.setFont(fontButton);
				this.add(bYes);
				
				
		//NO gombot adunk az ablakra
				ActNo aNo = new ActNo();
				bNo.addActionListener(aNo);
				bNo.setBounds((kGridX*3)+kGridW+(4*kGridX)+((kGridX*6)/10)+10, (kGridY+(kGridH-100)+(((kGridH-100)*5)/10))+(pGridH*6)/10, 120, 80);
				bNo.setText("Kilép");
				bNo.setBackground(colorButton);
				bNo.setFont(fontButton);
				this.add(bNo);
							
		//LEAVE gombot adunk az ablakra
				ActLeave aLeave = new ActLeave();
				bLeave.addActionListener(aLeave);
				bLeave.setBounds(((kGridX*3)+kGridW)+100, (kGridY+(kGridH-100)+(((kGridH-100)*5)/10))+(pGridH*6)/10, 140, 60);
				bLeave.setText("LEAVE");
				bLeave.setBackground(colorButton);
				bLeave.setFont(fontButton);
				bLeave.setVisible(false);
				this.add(bLeave);
				
		//RASE gombot adunk az ablakra
				ActRase aRase = new ActRase();
				bRase.addActionListener(aRase);
				bRase.setBounds(300, 560, 180, 80);
				bRase.setText("Rase");
				bRase.setBackground(colorButton);
				bRase.setFont(fontButton);
				this.add(bRase);
					
		//LOWER gombot adunk az ablakra
				ActLower aLower = new ActLower();
				bLower.addActionListener(aLower);
				bLower.setBounds(50, 560, 180, 80);
				bLower.setText("Lower");
				bLower.setBackground(colorButton);
				bLower.setFont(fontButton);
				this.add(bLower);
				
		//kártya generálás
				String shapeS1 = null;
				int id_setter = 0;
				for(int s = 0; s < 4;s++) {
					if(s == 0) {
						shapeS1 = "Spades";
					} else if( s == 1) {
						shapeS1 = "Hearts";
					} else if( s == 2) {
						shapeS1 = "Diamonds";
					} else {
						shapeS1 = "Clubs";
					}
					for(int i=2;i<15;i++) {
						allCards.add(new Card(i,shapeS1, id_setter));
						id_setter++;
					}
				}
				
				rand = new Random().nextInt(52);
				playerCards.add(allCards.get(rand));
				allCards.get(rand).setUsed();
				
				rand = new Random().nextInt(52);
				while(true) {
					if(allCards.get(rand).cardUsed == false) {
						dealerCards.add(allCards.get(rand));
						allCards.get(rand).cardUsed = true;
						break;
					} else {
						rand = new Random().nextInt(52);
					}
				}
				
				rand = new Random().nextInt(52);
				while(true) {
					if(allCards.get(rand).cardUsed == false) {
						playerCards.add(allCards.get(rand));
						allCards.get(rand).cardUsed = true;
						break;
					} else {
						rand = new Random().nextInt(52);
					}
				}
				
				rand = new Random().nextInt(52);
				while(true) {
					if(allCards.get(rand).cardUsed == false) {
						dealerCards.add(allCards.get(rand));
						allCards.get(rand).cardUsed = true;
						break;
					} else {
						rand = new Random().nextInt(52);
					}
				}
				//mennyen végig az összes player kártyán
				for(Card c : playerCards) {
					//System.out.println("Player has the card "+c.name+" of "+c.shape);
				}
				for(Card c : dealerCards) {
					//System.out.println("Dealler has the card "+c.name+" of "+c.shape);
				}

		
	}
	
	//totalschacker
		//setwinner
		public void setWinner() {
			int pPoints = 0;
			int dPoints = 0;
			
			if(total_player_max > 21) {
				pPoints = total_player_min;
			} else {
				pPoints = total_player_max;
			}
			
			if(total_dealer_max > 21) {
				dPoints = total_dealer_min;
			} else {
				dPoints = total_dealer_max;
			}
			
			if (pPoints > 21 && dPoints > 21) {
				GUI.Log.add(new Message("Nobody wins!", "Dealer"));
			} else if (dPoints > 21) {
				GUI.Log.add(new Message("You win!", "Player"));
				javaSql.setMoney(Integer.toString((javaSql.getMoney(GUI.bejelentkez.get(0).login)+tet.getTet())), GUI.bejelentkez.get(0).login);
				javaSql.gameLog(GUI.bejelentkez.get(0).login, Integer.toString(tet.getTet()));
				Main.pWins++;
			} else if (pPoints > 21) {
				GUI.Log.add(new Message("Dealer wins!", "Dealer"));
				javaSql.setMoney(Integer.toString((javaSql.getMoney(GUI.bejelentkez.get(0).login)-tet.getTet())), GUI.bejelentkez.get(0).login);
				javaSql.gameLog(GUI.bejelentkez.get(0).login, Integer.toString(tet.getTet()*-1));
				Main.dWins++;
			} else if (pPoints > dPoints) {
				GUI.Log.add(new Message("You win!", "Player"));
				javaSql.setMoney(Integer.toString((javaSql.getMoney(GUI.bejelentkez.get(0).login)+tet.getTet())), GUI.bejelentkez.get(0).login);
				javaSql.gameLog(GUI.bejelentkez.get(0).login, Integer.toString(tet.getTet()));
				Main.pWins++;
			} else if(pPoints < dPoints){
				GUI.Log.add(new Message("Dealer wins!", "Dealer"));
				javaSql.setMoney(Integer.toString((javaSql.getMoney(GUI.bejelentkez.get(0).login)-tet.getTet())), GUI.bejelentkez.get(0).login);
				javaSql.gameLog(GUI.bejelentkez.get(0).login, Integer.toString(tet.getTet()*-1));
				Main.dWins++;
			} else {
				GUI.Log.add(new Message("Nobody wins!", "Dealer"));
			}
			
			
		}
		
		public void dealerHitStay() {
			dthinking = true;
			
			int dAvailable = 0;
			if(total_dealer_max >21) {
				dAvailable = total_dealer_min;
			} else {
				dAvailable = total_dealer_max;
			}
			
			int pAvailable = 0;
			if(total_player_max > 21) {
				pAvailable = total_player_min;;
			} else {
				pAvailable = total_player_max;
			}
			
			GUI.frame.repaint();
			
			try {
				Thread.sleep(2000);//idõ húzó
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if(dAvailable < pAvailable && pAvailable <= 21 || dAvailable < 16) {
				int tempMax = 0;
				if(total_dealer_max <= 21) {
					tempMax = total_dealer_max;
				} else {
					tempMax = total_dealer_min;
				}
				
				String mess = ("Dealer decided to hit! (total: " + Integer.toString(tempMax) + ")");
				GUI.Log.add(new Message(mess, "Dealer"));
				
				rand = new Random().nextInt(52);
				while(allCards.get(rand).cardUsed == true) {
					rand = new Random().nextInt(52);
				}
				dealerCards.add(allCards.get(rand));
				allCards.get(rand).setUsed();
			} else {
				int tempMax = 0;
				if(total_dealer_max <= 21) {
					tempMax = total_dealer_max;
				} else {
					tempMax = total_dealer_min;
				}
				String mess = ("Dealer decided to stay! (total: " + Integer.toString(tempMax) + ")");
				GUI.Log.add(new Message(mess, "Dealer"));
				setWinner();
				bool_dealer_turn = false;
				bool_play_more = true;
			}
			dthinking = false;
		}
		
		//totals checker
		public void totalsChecker() {
			int acesCount = 0;
			
			total_player_max = 0;
			total_player_min =0;
			for(Card c : playerCards) {
				
				total_player_min += c.value;
				total_player_max += c.value;
				if(c.name == "Ace") {
					acesCount++;
				}
			}
			if (acesCount > 0) {
				total_player_max += 10;
			}
			
			total_dealer_min = 0;
			total_dealer_max = 0;
			acesCount = 0;
			
			for(Card c : dealerCards) {
				total_dealer_min += c.value;
				total_dealer_max += c.value;
				if(c.name == "Ace") {
					acesCount++;
				}
				if (acesCount > 0) {
					total_dealer_max += 10;
				}
			}
		}
		
		public void refresher() {
			
			//button visibility checker
			if(bool_dealer_turn == true && dthinking == false) {
				dealerHitStay();
			}
			if(bool_hit_stay == true) {
				bHit.setVisible(true);
				bStay.setVisible(true);
				bLower.setVisible(true);
				bRase.setVisible(true);
			} 
			else 
			{
				bHit.setVisible(false);
				bStay.setVisible(false);
				bLower.setVisible(false);
				bRase.setVisible(false);
			}
			
			if(bool_play_more == true) {
				bYes.setVisible(true);
				bNo.setVisible(true);
				bLeave.setVisible(true);
			}
			else
			{
				bYes.setVisible(false);
				bNo.setVisible(false);
				bLeave.setVisible(false);
			}
			
			totalsChecker();
			
			if((total_player_max == 21 || total_player_min >=21) && bool_hit_stay == true) {
				int tempMax = 0;
				if(total_player_max <= 21) {
					tempMax = total_player_max;
				} else {
					tempMax = total_player_min;
				}
				String uzenet = ("Auto pass! (total: " + Integer.toString(tempMax) + ")");
				GUI.Log.add(new Message(uzenet, "Player"));
				bool_hit_stay = false;
				bool_dealer_turn = true;
			}
			
			if ((total_dealer_max == 21 || total_dealer_min >= 21) && bool_dealer_turn == true) {
				int tempMax = 0;
				if (total_dealer_max <= 21) {
					tempMax = total_dealer_max;
				} else {
					tempMax = total_dealer_min;
				}
				String mess = ("Dealer auto pass! (total: " + Integer.toString(tempMax) + ")");
				GUI.Log.add(new Message(mess, "Dealer"));
				setWinner();
				bool_dealer_turn = false;
				bool_play_more = true;
			}
			GUI.frame.repaint();
		}
		
		public void paintComponent(Graphics g) {
			refresher();
		//g az egész lapot hívja meg
			g.setColor(colorBackground);
		//az alap lap adatait adja meg
			g.fillRect(0, 0, GUI.aW, GUI.aH);
			
		//money
			g.setFont(fontCard);
			g.setColor(Color.black);
			g.drawString("Pénzed: ", 500, 600);
			
			int penz = 0;
			
			penz =  javaSql.getMoney(GUI.bejelentkez.get(0).login);
			
			g.drawString(Integer.toString(penz), 500, 640);
			
			g.drawString("Téted: ", 800, 600);
			
			int tete = tet.getTet();
			
			g.drawString(Integer.toString(tete), 800, 640);
			
			//kérdések
			if(bool_hit_stay == true) {
				g.setColor(Color.black);
				g.setFont(fontQuestion);
				g.drawString(hit_stay_q, kGridX*2+kGridW+((kGridW/2-kGridX)/2)+20, kGridY+40);
				g.drawString("Össz pont: ", (kGridX*2)+kGridW+((kGridW/2-kGridX)/2)+((sGridW-120))-100, kGridY+((sGridH*1)/10)+180);
				if(total_player_min == total_player_max) {
					g.drawString(Integer.toString(total_player_max), (kGridX*2)+kGridW+((kGridW/2-kGridX)/2)+((sGridW-120))-100, kGridY+((sGridH*1)/10)+230);
				} else if(total_player_max <=21) {
					g.drawString(Integer.toString(total_player_min)+" or "+Integer.toString(total_player_max), (kGridX*2)+kGridW+((kGridW/2-kGridX)/2)+((sGridW-120))-100, kGridY+((sGridH*1)/10)+230);
				} else {
					g.drawString(Integer.toString(total_player_min), (kGridX*2)+kGridW+((kGridW/2-kGridX)/2)+((sGridW-120))-100, kGridY+((sGridH*1)/10)+230);
				} 
			} else if(bool_play_more == true) {
				g.setColor(Color.black);
				g.setFont(fontQuestion);
				g.drawString(play_more_q, ((kGridX*2)+kGridW)+120, (kGridY+kGridH+((kGridH*3)/10))+80);
			}
			
			//Log tábla
			g.setColor(Color.black);
			g.fillRect(sGridX, sGridY, kGridW, kGridH-((kGridH*3)/10));
			
			//Log
			g.setFont(fontCard);
			int Logi =0;
			for(Message m : GUI.Log) {
				if(m.getWho().equalsIgnoreCase("Dealer")) {
					g.setColor(Color.red);
				} else {
					g.setColor(Color.blue);
				}
				g.drawString(m.getMessage(), sGridX+15, sGridY+30+Logi*35);
				Logi++;
			}
			
			//score
			g.setColor(Color.BLACK);
			g.setFont(fontQuestion);
			String score = ("Score: " + Integer.toString(Main.pWins) + " - " + Integer.toString(Main.dWins));
			g.drawString(score, (kGridX*2)+kGridW, kGridY+kGridH+((kGridH*3)/10)-25);
			
		//Keretek
			//Kártya tábla keret
			g.setColor(Color.black);
			g.drawRect(kGridX, kGridY, kGridW, kGridH);
			//pont grid keret
			g.drawRect(kGridX*2+kGridW, kGridY, (kGridW/2-kGridX)/2, kGridH+(((kGridH-350)*4)/10));
			//gomb box
			g.drawRect(kGridX*2+kGridW+((kGridW/2-kGridX)/2), kGridY, sGridW, sGridH);
			//new game/win-lose count
			g.drawRect((kGridX*2)+kGridW, kGridY+kGridH+((kGridH*3)/10), pGridW, pGridH);

			//kártya hely
			for(int i = 0;i<6;i++) {
				g.drawRect(kGridX+i*cardTW+cardSpaceing, kGridY+cardSpaceing, cardAW, cardAH);
				g.drawRect(kGridX+i*cardTW+cardSpaceing, kGridY+cardSpaceing+cardTH, cardAW, cardAH);
			}
			
			//kárgy rajzoló
			//player cártyák
			int index=0;
			for(Card c : playerCards) {
				g.setColor(Color.white);
				g.fillRect(kGridX+index*cardTW+cardSpaceing, kGridY+cardSpaceing+cardEdgeSofting, cardAW, cardAH-2*cardEdgeSofting);
				g.fillRect(kGridX+index*cardTW+cardSpaceing+cardEdgeSofting, kGridY+cardSpaceing, cardAW-2*cardEdgeSofting, cardAH);
				g.fillOval(kGridX+index*cardTW+cardSpaceing, kGridY+cardSpaceing, 2*cardEdgeSofting, 2*cardEdgeSofting);
				g.fillOval(kGridX+index*cardTW+cardSpaceing+cardAW-2*cardEdgeSofting, kGridY+cardSpaceing, 2*cardEdgeSofting, 2*cardEdgeSofting);
				g.fillOval(kGridX+index*cardTW+cardSpaceing, kGridY+cardSpaceing+cardAH-2*cardEdgeSofting, 2*cardEdgeSofting, 2*cardEdgeSofting);
				g.fillOval(kGridX+index*cardTW+cardSpaceing+cardAW-2*cardEdgeSofting, kGridY+cardSpaceing+cardAH-2*cardEdgeSofting, 2*cardEdgeSofting, 2*cardEdgeSofting);
				g.setColor(Color.black);
				if(c.shape.equalsIgnoreCase("Hearts") || c.shape.equalsIgnoreCase("Diamonds")) {
					g.setColor(Color.red);
				}
				g.setFont(fontCard);
				g.drawString(c.symbol, kGridX+index*cardTW+cardSpaceing*2, kGridY+cardAH);
				
				if(c.shape.equalsIgnoreCase("Spades")) {
					g.setColor(Color.black);
					g.fillOval(kGridX+index*cardTW+40, kGridY+85, 60, 60);
					g.fillOval(kGridX+index*cardTW+70, kGridY+85, 60, 60);
					g.fillArc(kGridX+index*cardTW+30, kGridY+22, 110, 80, 230, 80);
					g.fillRect(kGridX+index*cardTW+80, kGridY+90, 10, 70);
				} else if(c.shape.equalsIgnoreCase("Hearts")) {
					g.setColor(Color.red);
					g.fillOval(kGridX+index*cardTW+40, kGridY+85, 60, 60);
					g.fillOval(kGridX+index*cardTW+70, kGridY+85, 60, 60);
					g.fillArc(kGridX+index*cardTW+25, kGridY+122, 120, 100, 50, 80);
				} else if(c.shape.equalsIgnoreCase("Diamonds")) {
					g.setColor(Color.red);
					int x1,x2,x3,x4,y1,y2,y3,y4;
					x1 = 84 +kGridX+index*cardTW;
					x2 = 48 +kGridX+index*cardTW;
					x3 = 84 +kGridX+index*cardTW;
					x4 = 120 +kGridX+index*cardTW;
					y1 = 73 +kGridY-cardSpaceing;
					y2 = 130 +kGridY-cardSpaceing;
					y3 = 186 +kGridY-cardSpaceing;
					y4 = 130 +kGridY-cardSpaceing;
					int [] xPoly = {x1,x2,x3,x4};
					int [] yPoly = {y1,y2,y3,y4};
					g.fillPolygon(xPoly,yPoly,4);
				} else {
					g.setColor(Color.black);
					g.fillOval(kGridX+index*cardTW+40, kGridY+85, 45, 45);
					g.fillOval(kGridX+index*cardTW+85, kGridY+85, 45, 45);
					g.fillOval(kGridX+index*cardTW+63, kGridY+50, 45, 45);
					g.fillRect(kGridX+index*cardTW+80, kGridY+90, 10, 70);
				}
				
				index++;
			}

			//dealer cards
			if(bool_dealer_turn == true || bool_play_more == true) {
				index=0;
				for(Card c : dealerCards) {
					g.setColor(Color.white);
					g.fillRect(kGridX+index*cardTW+cardSpaceing, kGridY+cardTH+cardSpaceing+cardEdgeSofting, cardAW, cardAH-2*cardEdgeSofting);
					g.fillRect(kGridX+index*cardTW+cardSpaceing+cardEdgeSofting, kGridY+cardTH+cardSpaceing, cardAW-2*cardEdgeSofting, cardAH);
					g.fillOval(kGridX+index*cardTW+cardSpaceing, kGridY+cardTH+cardSpaceing, 2*cardEdgeSofting, 2*cardEdgeSofting);
					g.fillOval(kGridX+index*cardTW+cardSpaceing+cardAW-2*cardEdgeSofting, kGridY+cardTH+cardSpaceing, 2*cardEdgeSofting, 2*cardEdgeSofting);
					g.fillOval(kGridX+index*cardTW+cardSpaceing, kGridY+cardTH+cardSpaceing+cardAH-2*cardEdgeSofting, 2*cardEdgeSofting, 2*cardEdgeSofting);
					g.fillOval(kGridX+index*cardTW+cardSpaceing+cardAW-2*cardEdgeSofting, kGridY+cardTH+cardSpaceing+cardAH-2*cardEdgeSofting, 2*cardEdgeSofting, 2*cardEdgeSofting);
					g.setColor(Color.black);
					if(c.shape.equalsIgnoreCase("Hearts") || c.shape.equalsIgnoreCase("Diamonds")) {
						g.setColor(Color.red);
					}
					g.setFont(fontCard);
					g.drawString(c.symbol, kGridX+index*cardTW+cardSpaceing*2, kGridY+cardTH+cardAH);
					
					if(c.shape.equalsIgnoreCase("Spades")) {
						g.setColor(Color.black);
						g.fillOval(kGridX+index*cardTW+40, kGridY+cardTH+85, 60, 60);
						g.fillOval(kGridX+index*cardTW+70, kGridY+cardTH+85, 60, 60);
						g.fillArc(kGridX+index*cardTW+30, kGridY+cardTH+22, 110, 80, 230, 80);
						g.fillRect(kGridX+index*cardTW+80, kGridY+cardTH+90, 10, 70);
					} else if(c.shape.equalsIgnoreCase("Hearts")) {
						g.setColor(Color.red);
						g.fillOval(kGridX+index*cardTW+40, kGridY+cardTH+85, 60, 60);
						g.fillOval(kGridX+index*cardTW+70, kGridY+cardTH+85, 60, 60);
						g.fillArc(kGridX+index*cardTW+25, kGridY+cardTH+122, 120, 100, 50, 80);
					} else if(c.shape.equalsIgnoreCase("Diamonds")) {
						g.setColor(Color.red);
						int x1,x2,x3,x4,y1,y2,y3,y4;
						x1 = 84 +kGridX+index*cardTW;
						x2 = 48 +kGridX+index*cardTW;
						x3 = 84 +kGridX+index*cardTW;
						x4 = 120 +kGridX+index*cardTW;
						y1 = 73 +kGridY-cardSpaceing+cardTH;
						y2 = 130 +kGridY-cardSpaceing+cardTH;
						y3 = 186 +kGridY-cardSpaceing+cardTH;
						y4 = 130 +kGridY-cardSpaceing+cardTH;
						int [] xPoly = {x1,x2,x3,x4};
						int [] yPoly = {y1,y2,y3,y4};
						g.fillPolygon(xPoly,yPoly,4);
					} else {
						g.setColor(Color.black);
						g.fillOval(kGridX+index*cardTW+40, kGridY+cardTH+85, 45, 45);
						g.fillOval(kGridX+index*cardTW+85, kGridY+cardTH+85, 45, 45);
						g.fillOval(kGridX+index*cardTW+63, kGridY+cardTH+50, 45, 45);
						g.fillRect(kGridX+index*cardTW+80, kGridY+cardTH+90, 10, 70);
					}
					
					index++;
				}
				
				g.setColor(Color.black);
				g.setFont(fontQuestion);
				g.drawString("Your total: ", (kGridX*2)+kGridW+((kGridW/2-kGridX)/2)+((sGridW-120))-100, kGridY+((sGridH*1)/10)+180);
				if(total_player_max <= 21) {
					g.drawString(Integer.toString(total_player_max),(kGridX*2)+kGridW+((kGridW/2-kGridX)/2)+((sGridW-120))-100, kGridY+((sGridH*1)/10)+220);
				} else {
					g.drawString(Integer.toString(total_player_min),(kGridX*2)+kGridW+((kGridW/2-kGridX)/2)+((sGridW-120))-100, kGridY+((sGridH*1)/10)+220);
				}
				g.drawString("Dealer's total: ", (kGridX*2)+kGridW+((kGridW/2-kGridX)/2)+((sGridW-120))-100, kGridY+((sGridH*1)/10)+300);
				if(total_dealer_max <= 21) {
					g.drawString(Integer.toString(total_dealer_max),(kGridX*2)+kGridW+((kGridW/2-kGridX)/2)+((sGridW-120))-100, kGridY+((sGridH*1)/10)+380);
				} else {
					g.drawString(Integer.toString(total_dealer_min),(kGridX*2)+kGridW+((kGridW/2-kGridX)/2)+((sGridW-120))-100, kGridY+((sGridH*1)/10)+380);
				}
			}
		}
		
		//Mi t csináljon amikor a Hit gomb meg lessz nyomva
		public class ActHit implements ActionListener {
			
			public void actionPerformed(ActionEvent e) {
				if(tet.getTet() !=0) {
					if(bool_hit_stay == true) {
						System.out.println("You just hit the HIT button! ");
						int tempMax = 0;
						if(total_player_max <= 21) {
							tempMax = total_player_max;
						} else {
							tempMax = total_player_min;
						}
						
						String mess = ("You decided to hit! (total: " + Integer.toString(tempMax) + ")");
						GUI.Log.add(new Message(mess, "Player"));
						
						rand = new Random().nextInt(52);
						while(allCards.get(rand).cardUsed == true) {
							rand = new Random().nextInt(52);
						}
						playerCards.add(allCards.get(rand));
						allCards.get(rand).setUsed();
					}
				}
			}
		}
		//Mi t csináljon amikor a STAY gomb meg lessz nyomva	
		public class ActStay implements ActionListener {

			public void actionPerformed(ActionEvent e) {
				if(tet.getTet() !=0) {
					if(bool_hit_stay == true) {
					System.out.println("You just hit the STAY button! ");
					int tempMax = 0;
					
					if(total_player_max <= 21) {
						tempMax = total_player_max;
					}else {
						tempMax = total_player_min;
					}
					String mess = ("You decided to stay! (total: " + Integer.toString(tempMax) + ")");
					GUI.Log.add(new Message(mess, "Player"));
					
					bool_hit_stay = false;
					bool_dealer_turn = true;
					}
				}
			}
		}

		//Mi t csináljon amikor a YES gomb meg lessz nyomva
		public class ActYes implements ActionListener {

			public void actionPerformed(ActionEvent e) {
				System.out.println("You just hit the YES button! ");
				for (Card c : allCards) {
					c.setNotused();
				}
				
				playerCards.clear();
				dealerCards.clear();
				GUI.Log.clear();
				
				bool_play_more = false;
				bool_hit_stay = true;
				
				rand = new Random().nextInt(52);
				playerCards.add(allCards.get(rand));
				allCards.get(rand).setUsed();
				
				rand = new Random().nextInt(52);
				while(allCards.get(rand).cardUsed == true) {
					rand = new Random().nextInt(52);
				}
				dealerCards.add(allCards.get(rand));
				allCards.get(rand).setUsed();
				
				rand = new Random().nextInt(52);
				while(allCards.get(rand).cardUsed == true) {
					rand = new Random().nextInt(52);
				}
				
				playerCards.add(allCards.get(rand));
				allCards.get(rand).setUsed();
				
				rand = new Random().nextInt(52);
				while(allCards.get(rand).cardUsed == true) {
					rand = new Random().nextInt(52);
				}
				
				dealerCards.add(allCards.get(rand));
				allCards.get(rand).setUsed();
			}
		}
	
		//Mi t csináljon amikor a NO gomb meg lessz nyomva
		public class ActNo implements ActionListener{

			public void actionPerformed(ActionEvent e) {
				System.out.println("You just hit the NO button! ");
				Main.terminator = true;
				GUI.frame.dispose();
			}
		}
		
		//Mi t csináljon amikor a LEAVE gomb meg lessz nyomva
		public class ActLeave implements ActionListener{

			public void actionPerformed(ActionEvent e) {
				System.out.println("You just hit the LEAVE button! ");
				
				bool_play_more = false;
				bool_hit_stay = true;
				bLeave.setVisible(false);
				
				for (Card c : allCards) {
					c.setNotused();
				}
				
				playerCards.clear();
				dealerCards.clear();
				GUI.Log.clear();
				
				Main.pWins = 0;
				Main.dWins = 0;
				
				GUI.gamelog();
				
				for (Card c : allCards) {
					c.setNotused();
				}
				
				playerCards.clear();
				dealerCards.clear();
				GUI.Log.clear();
				
				bool_play_more = false;
				bool_hit_stay = true;
				
				rand = new Random().nextInt(52);
				playerCards.add(allCards.get(rand));
				allCards.get(rand).setUsed();
				
				rand = new Random().nextInt(52);
				while(allCards.get(rand).cardUsed == true) {
					rand = new Random().nextInt(52);
				}
				dealerCards.add(allCards.get(rand));
				allCards.get(rand).setUsed();
				
				rand = new Random().nextInt(52);
				while(allCards.get(rand).cardUsed == true) {
					rand = new Random().nextInt(52);
				}
				
				playerCards.add(allCards.get(rand));
				allCards.get(rand).setUsed();
				
				rand = new Random().nextInt(52);
				while(allCards.get(rand).cardUsed == true) {
					rand = new Random().nextInt(52);
				}
				
				dealerCards.add(allCards.get(rand));
				allCards.get(rand).setUsed();
				
				GUI.cl.show(GUI.panelCont, "gamechooser");
			}
		}
		
		//Mi t csináljon amikor a RASE gomb meg lessz nyomva
		public class ActRase implements ActionListener{

			public void actionPerformed(ActionEvent e) {
				System.out.println("You just hit the RASE button! ");
				int maxRese =1000;
				if(javaSql.getMoney(GUI.bejelentkez.get(0).login) < maxRese) {
					maxRese = javaSql.getMoney(GUI.bejelentkez.get(0).login);
				}
				tet.rase(javaSql.getMoney(GUI.bejelentkez.get(0).login));
			}
		}

	//Mi t csináljon amikor a LOWER gomb meg lessz nyomva
		public class ActLower implements ActionListener{

			public void actionPerformed(ActionEvent e) {
				System.out.println("You just hit the LOWER button! ");
				int maxLower =1000;
				tet.lower(javaSql.getMoney(GUI.bejelentkez.get(0).login));
			}
		}
		
		

}
