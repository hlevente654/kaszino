package KockaPoker;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.Timer;

import alapPackage.GUI;
import alapPackage.Main;
import alapPackage.Message;
import alapPackage.javaSql;
import asztalok.tet;
import asztalok.bejelentkezve;
import asztalok.jatekos;
import asztalok.newPlayer;

public class KockaPoker extends JPanel{
//cases
	Roll roll = new Roll(); 
	PlayerWin playerwin = new PlayerWin();
	
//String
	//Kérdések
	String play_more_q = "Play more?";
	
	
//boolean
	static boolean haveWinner=false;
	boolean jatekVege=false;
	static boolean playerWining = false;
	static boolean draw = false;
	boolean bool_megy = false;
	boolean bool_can_chouse = false;
	boolean bool_chose_a_dice=false;
	
//array-ek
	static ArrayList <Kocka> dobott_kockak=new ArrayList<Kocka>();
	static ArrayList <Kocka> te_kockak = new ArrayList <Kocka>();
	static ArrayList <Kocka> ellenfel_kockak = new ArrayList <Kocka>(); 
	static ArrayList <Message> Log = new ArrayList <Message>();
	
//gombok
	JButton bReRoll = new JButton();
	public JButton getButtonReRoll() {
	       return bReRoll;
	    }
	JButton bRoll = new JButton();
	public JButton getButtonRoll() {
	       return bRoll;
	    }
	JButton bStart = new JButton();
	public JButton getButtonStart() {
	       return bStart;
	    }
	JButton bStay = new JButton();
	public JButton getButtonStay() {
	       return bStay;
	    }
	JButton bRase = new JButton();
	public JButton getButtonRase() {
	       return bRase;
	    }
	JButton bChoseD1 = new JButton();
	public JButton getButtonChoseD1() {
	       return bChoseD1;
	    }
	JButton bChoseD2 = new JButton();
	public JButton getButtonChoseD2() {
	       return bChoseD2;
	    }
	JButton bChoseD3 = new JButton();
	public JButton getButtonChoseD3() {
	       return bChoseD3;
	    }
	JButton bChoseD4 = new JButton();
	public JButton getButtonChoseD4() {
	       return bChoseD4;
	    }
	JButton bChoseD5 = new JButton();
	public JButton getButtonChoseD5() {
	       return bChoseD5;
	    }
	JButton bYes = new JButton();
	public JButton getYes() {
	       return bYes;
	    }
	JButton bNo = new JButton();
	public JButton getNo() {
	       return bNo;
	    }
	JButton bLeave = new JButton();
	public JButton getLeave() {
		return bLeave;
	}
	
//colors
	Color colorButton = GUI.colorButton;
	Color colorBackground = GUI.colorBackground;
	
//Lekért
	//Array
	public static ArrayList <bejelentkezve> bejelentkez=new ArrayList<>();
	//Font
	Font fontCard = new Font("Times New Roman", Font.BOLD,40);
	Font fontButton = GUI.fontButton;
	Font fontQuestion= GUI.fontButton;
	Font fontLog = new Font("Calibri", Font.PLAIN,25);
//cl
	CardLayout cl= GUI.getcl();
//panelCont
	JPanel panelCont=GUI.getpanelCont();
	
	public  KockaPoker() {
	
//gombok
	//leave
	ActLeave aLeave = new ActLeave();
	bLeave.addActionListener(aLeave);
	bLeave.setBounds(950, 830, 110, 50);
	bLeave.setText("Leave");
	bLeave.setVisible(false);
	bLeave.setBackground(colorButton);
	bLeave.setFont(fontQuestion);
	//yes
	ActYes aYes = new ActYes();
	bYes.addActionListener(aYes);
	bYes.setBounds(830, 830, 110, 70);
	bYes.setText("Yes");
	bYes.setVisible(false);
	bYes.setBackground(colorButton);
	bYes.setFont(fontButton);
	
	//no
	ActNo aNo = new ActNo();
	bNo.addActionListener(aNo);
	bNo.setBounds(1070, 830, 110, 70);
	bNo.setText("No");
	bNo.setVisible(false);
	bNo.setBackground(colorButton);
	bNo.setFont(fontButton);
	
	//dise get1
	ActChoseD1 aChoseD1 = new ActChoseD1();
	bChoseD1.addActionListener(aChoseD1);
	bChoseD1.setBounds(32, 700, 110, 70);
	bChoseD1.setText("Reroll");
	bChoseD1.setVisible(false);
	bChoseD1.setBackground(colorButton);
	bChoseD1.setFont(fontButton);
	
	//dise get2
	ActChoseD2 aChoseD2 = new ActChoseD2();
	bChoseD2.addActionListener(aChoseD2);
	bChoseD2.setBounds(179, 700, 110, 70);
	bChoseD2.setText("Reroll");
	bChoseD2.setVisible(false);
	bChoseD2.setBackground(colorButton);
	bChoseD2.setFont(fontButton);	
	
	//dise get3
	ActChoseD3 aChoseD3 = new ActChoseD3();
	bChoseD3.addActionListener(aChoseD3);
	bChoseD3.setBounds(326, 700, 110, 70);
	bChoseD3.setText("Reroll");
	bChoseD3.setVisible(false);
	bChoseD3.setBackground(colorButton);
	bChoseD3.setFont(fontButton);
	
			
	//dise get4
	ActChoseD4 aChoseD4 = new ActChoseD4();
	bChoseD4.addActionListener(aChoseD4);
	bChoseD4.setBounds(473, 700, 110, 70);
	bChoseD4.setText("Reroll");
	bChoseD4.setVisible(false);
	bChoseD4.setBackground(colorButton);
	bChoseD4.setFont(fontButton);	
	
	//dise get5
	ActChoseD5 aChoseD5 = new ActChoseD5();
	bChoseD5.addActionListener(aChoseD5);
	bChoseD5.setBounds(620, 700, 110, 70);
	bChoseD5.setText("Reroll");
	bChoseD5.setVisible(false);
	bChoseD5.setBackground(colorButton);
	bChoseD5.setFont(fontButton);	
	
	
	//start
	ActStart aStart = new ActStart();
	bStart.addActionListener(aStart);
	bStart.setBounds(1200, 600, 400, 150);
	bStart.setText("Start");
	bStart.setVisible(true);
	bStart.setBackground(colorButton);
	bStart.setFont(fontButton);
	
	//want to Reroll roll
	ActReroll aReroll = new ActReroll();
	bReRoll.addActionListener(aReroll);
	bReRoll.setBounds(1250, 450, 200, 100);
	bReRoll.setText("Re-Roll");
	bReRoll.setVisible(false);
	bReRoll.setBackground(colorButton);
	bReRoll.setFont(fontButton);
	
	//roll
	ActRoll aRoll = new ActRoll();
	bRoll.addActionListener(aRoll);
	bRoll.setBounds(1200, 600, 400, 150);
	bRoll.setText("Roll");
	bRoll.setVisible(false);
	bRoll.setBackground(colorButton);
	bRoll.setFont(fontButton);
	
	//stay
	ActStay aStay = new ActStay();
	bStay.addActionListener(aStay);
	bStay.setBounds(750, 450, 200, 100);
	bStay.setText("Stay");
	bStay.setVisible(false);
	bStay.setBackground(colorButton);
	bStay.setFont(fontButton);
	
	//rase
	ActRase aRase = new ActRase();
	bRase.addActionListener(aRase);
	bRase.setBounds(1000, 450, 200, 100);
	bRase.setText("Rase");
	bRase.setVisible(false);
	bRase.setBackground(colorButton);
	bRase.setFont(fontButton);

	}
	
	public void refresher() {
		
		if(bool_chose_a_dice==true) {
			bRoll.setVisible(true);
		}
		
		if(jatekVege==true) {
			bYes.setVisible(true);
			bNo.setVisible(true);
			bLeave.setVisible(true);
		}else {
			bYes.setVisible(false);
			bNo.setVisible(false);
			bLeave.setVisible(false);
		}
		
		GUI.frame.repaint();
	}

	public void paintComponent(Graphics g) {
		
		g.setColor(colorBackground);
		g.fillRect(0, 0, GUI.aW, GUI.aH);
		
		
//keretek
		g.setColor(Color.black);
	//logs
		g.fillRect(0, 0, 400, 550);
	//te kockatartó
		g.drawRect(0, 750, 800, 210);
	//rase/lower/yes/no
		g.drawRect(800, 750, 400, 210);
	//pénz/tét
		g.drawRect(1200, 750, 400, 210);
	//ellenfél kockák
		g.drawRect(900, 0, 700, 160);
	
	//number of rolls this turn
		refresher();
		g.setColor(Color.black);
	//end game
		if(jatekVege==true && haveWinner==false) {
			setWinner setwinner = new setWinner();
			setwinner.setWinnerM();
		}
		
	//money
		g.setFont(fontCard);
		g.setColor(Color.black);
		g.drawString("Pénzed: ", 1220, 800);
		
		int penz = 0;
		
		if(bejelentkez.size()>0) {
		penz =  javaSql.getMoney(bejelentkez.get(0).login);
		
		g.drawString(Integer.toString(penz), 1220, 850);
		
		}
		g.drawString("Téted: ", 1380, 800);
		
		int tete = tet.getTet();
		
		g.drawString(Integer.toString(tete), 1380, 850);
		
	//Log
		int Logi =0;
		for(Message m : Log) {
			g.setFont(fontLog);
			if(m.getWho().equalsIgnoreCase("Enemy")) {
				g.setColor(Color.red);
			} else {
				g.setColor(Color.blue);
			}
			g.drawString(m.getMessage(), 10, 20+Logi*20);
			Logi++;
		}
	//kocka helyek
		//te kockáid
		
		//kérdések
		if(jatekVege==true) {
			g.setColor(Color.black);
			g.setFont(fontQuestion);
			g.drawString(play_more_q, 925, 800);
		}
			int PSindex = 0;
			for(Kocka c : te_kockak) {
				if(c.value==1) {
					g.setColor(Color.white);
					g.fillRect(27 + PSindex*(120+27), 780, 120, 120);
					g.setColor(Color.black);
					g.fillOval(27 + PSindex*(120+27)+45, 820, 30, 30);
				}
				
				if(c.value==2) {
					g.setColor(Color.white);
					g.fillRect(27 + PSindex*(120+27), 780, 120, 120);
					g.setColor(Color.black);
					g.fillOval(27 + PSindex*(120+27)+5, 785, 30, 30);
					g.fillOval(27 + PSindex*(120+27)+85, 865, 30, 30);
				}
				
				if(c.value==3) {
					g.setColor(Color.white);
					g.fillRect(27 + PSindex*(120+27), 780, 120, 120);
					g.setColor(Color.black);
					g.fillOval(27 + PSindex*(120+27)+5, 785, 30, 30);
					g.fillOval(27 + PSindex*(120+27)+85, 865, 30, 30);
					g.fillOval(27 + PSindex*(120+27)+45, 825, 30, 30);
				}
				if(c.value==4) {
					g.setColor(Color.white);
					g.fillRect(27 + PSindex*(120+27), 780, 120, 120);
					g.setColor(Color.black);
					g.fillOval(27 + PSindex*(120+27)+5, 785, 30, 30);
					g.fillOval(27 + PSindex*(120+27)+85, 865, 30, 30);
					g.fillOval(27 + PSindex*(120+27)+5, 865, 30, 30);
					g.fillOval(27 + PSindex*(120+27)+85, 785, 30, 30);
				}
				if(c.value==5) {
					g.setColor(Color.white);
					g.fillRect(27 + PSindex*(120+27), 780, 120, 120);
					g.setColor(Color.black);
					g.fillOval(27 + PSindex*(120+27)+5, 785, 30, 30);
					g.fillOval(27 + PSindex*(120+27)+85, 865, 30, 30);
					g.fillOval(27 + PSindex*(120+27)+5, 865, 30, 30);
					g.fillOval(27 + PSindex*(120+27)+85, 785, 30, 30);
					g.fillOval(27 + PSindex*(120+27)+45, 825, 30, 30);
				}
				if(c.value==6) {
					g.setColor(Color.white);
					g.fillRect(27 + PSindex*(120+27), 780, 120, 120);
					g.setColor(Color.black);
					g.fillOval(27 + PSindex*(120+27)+5, 785, 30, 30);
					g.fillOval(27 + PSindex*(120+27)+85, 865, 30, 30);
					g.fillOval(27 + PSindex*(120+27)+5, 865, 30, 30);
					g.fillOval(27 + PSindex*(120+27)+85, 785, 30, 30);
					g.fillOval(27 + PSindex*(120+27)+5, 825, 30, 30);
					g.fillOval(27 + PSindex*(120+27)+85, 825, 30, 30);
				}
				PSindex++;
			}
		
		//dobott kockák
		int Sindex = 0;
		for(Kocka c : dobott_kockak) {
			if(c.value==1) {
				g.setColor(Color.white);
				g.fillRect(490 + Sindex*(120+90), 390, 120, 120);
				g.setColor(Color.black);
				g.fillOval(490 + Sindex*(120+90)+45, 435, 30, 30);
			}
			if(c.value==2) {
				g.setColor(Color.white);
				g.fillRect(490 + Sindex*(120+90), 390, 120, 120);
				g.setColor(Color.black);
				g.fillOval(500 + Sindex*(120+90),400, 30, 30);
				g.fillOval(570 + Sindex*(120+90),470, 30, 30);
			}
			if(c.value==3) {
				g.setColor(Color.white);
				g.fillRect(490 + Sindex*(120+90), 390, 120, 120);
				g.setColor(Color.black);
				g.fillOval(500 + Sindex*(120+90),400, 30, 30);
				g.fillOval(570 + Sindex*(120+90),470, 30, 30);
				g.fillOval(535 + Sindex*(120+90),435, 30, 30);
			}
			if(c.value==4) {
				g.setColor(Color.white);
				g.fillRect(490 + Sindex*(120+90), 390, 120, 120);
				g.setColor(Color.black);
				g.fillOval(500 + Sindex*(120+90),400, 30, 30);
				g.fillOval(500 + Sindex*(120+90),470, 30, 30);
				g.fillOval(570 + Sindex*(120+90),470, 30, 30);
				g.fillOval(570 + Sindex*(120+90),400, 30, 30);
				
			}
			if(c.value==5) {
				g.setColor(Color.white);
				g.fillRect(490 + Sindex*(120+90), 390, 120, 120);
				g.setColor(Color.black);
				g.fillOval(500 + Sindex*(120+90),400, 30, 30);
				g.fillOval(500 + Sindex*(120+90),470, 30, 30);
				g.fillOval(570 + Sindex*(120+90),470, 30, 30);
				g.fillOval(570 + Sindex*(120+90),400, 30, 30);
				g.fillOval(535 + Sindex*(120+90),435, 30, 30);
			}
			if(c.value==6) {
				g.setColor(Color.white);
				g.fillRect(490 + Sindex*(120+90), 390, 120, 120);
				g.setColor(Color.black);
				g.fillOval(500 + Sindex*(120+90),400, 30, 30);
				g.fillOval(500 + Sindex*(120+90),470, 30, 30);
				g.fillOval(570 + Sindex*(120+90),470, 30, 30);
				g.fillOval(570 + Sindex*(120+90),400, 30, 30);
				g.fillOval(500 + Sindex*(120+90),435, 30, 30);
				g.fillOval(570 + Sindex*(120+90),435, 30, 30);
			}
			Sindex++;
			
		}
		
		//ellenfél kockái

		int Eindex = 0;
		for(Kocka c : ellenfel_kockak) {
			if(c.value==1) {
					g.setColor(Color.white);
					g.fillRect(927 + Eindex*(27+100), 30, 100, 100);
					g.setColor(Color.black);
					g.fillOval(927 + Eindex*(27+100)+40, 70, 20, 20);
			}
			if(c.value==2) {
					g.setColor(Color.white);
					g.fillRect(927 + Eindex*(27+100), 30, 100, 100);
					g.setColor(Color.black);
					g.fillOval(927 + Eindex*(27+100)+5, 35, 20, 20);
					g.fillOval(927 + Eindex*(27+100)+75, 105, 20, 20);
			}
			if(c.value==3) {
					g.setColor(Color.white);
					g.fillRect(927 + Eindex*(27+100), 30, 100, 100);
					g.setColor(Color.black);
					g.fillOval(927 + Eindex*(27+100)+5, 35, 20, 20);
					g.fillOval(927 + Eindex*(27+100)+75, 105, 20, 20);
					g.fillOval(927 + Eindex*(27+100)+40, 70, 20, 20);
			}
			if(c.value==4) {
					g.setColor(Color.white);
					g.fillRect(927 + Eindex*(27+100), 30, 100, 100);
					g.setColor(Color.black);
					g.fillOval(927 + Eindex*(27+100)+5, 35, 20, 20);
					g.fillOval(927 + Eindex*(27+100)+75, 105, 20, 20);
					g.fillOval(927 + Eindex*(27+100)+5, 105, 20, 20);
					g.fillOval(927 + Eindex*(27+100)+75, 35, 20, 20);
				
			}
			if(c.value==5) {
					g.setColor(Color.white);
					g.fillRect(927 + Eindex*(27+100), 30, 100, 100);
					g.setColor(Color.black);
					g.fillOval(927 + Eindex*(27+100)+5, 35, 20, 20);
					g.fillOval(927 + Eindex*(27+100)+75, 105, 20, 20);
					g.fillOval(927 + Eindex*(27+100)+5, 105, 20, 20);
					g.fillOval(927 + Eindex*(27+100)+75, 35, 20, 20);
					g.fillOval(927 + Eindex*(27+100)+40, 70, 20, 20);
			}
			if(c.value==6) {
					g.setColor(Color.white);
					g.fillRect(927 + Eindex*(27+100), 30, 100, 100);
					g.setColor(Color.black);
					g.fillOval(927 + Eindex*(27+100)+5, 35, 20, 20);
					g.fillOval(927 + Eindex*(27+100)+75, 105, 20, 20);
					g.fillOval(927 + Eindex*(27+100)+5, 105, 20, 20);
					g.fillOval(927 + Eindex*(27+100)+75, 35, 20, 20);
					g.fillOval(927 + Eindex*(27+100)+5, 70, 20, 20);
					g.fillOval(927 + Eindex*(27+100)+75, 70, 20, 20);
			}
			Eindex++;
			
		}
	}
	

	public class ActStay implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.out.println("You chouse to stay!");
			
			bRase.setVisible(false);
			bStay.setVisible(false);
			bReRoll.setVisible(false);
			
			playerwin.PlayerWin();
			
			try {
				Thread.sleep(2000);//idõ húzó
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
			String mess =("You decided to stay!");
			Log.add(new Message(mess,"Player"));
			
			ellenfelRoll ellenfelroll =new ellenfelRoll();
			ellenfelroll.ellenfelRollM();
			jatekVege=true;
		}
	}
	public class ActRoll implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.out.println("You rolled!");
			
			String ideig="";
			for(int i=0;i<dobott_kockak.size();i++) {
				ideig=ideig+dobott_kockak.get(i).value+" ";
			}
			String mess=("You rerolled: "+ideig);
			Log.add(new Message(mess, "Player"));
			Reroll reroll = new Reroll();
			reroll.reroll(te_kockak);
			
			bool_chose_a_dice=false;
			bRoll.setVisible(false);
			bChoseD1.setVisible(false);
			bChoseD2.setVisible(false);
			bChoseD3.setVisible(false);
			bChoseD4.setVisible(false);
			bChoseD5.setVisible(false);
			
			playerwin.PlayerWin();
			try {
				Thread.sleep(2000);//idõ húzó
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			ellenfelRoll ellenfelroll =new ellenfelRoll();
			ellenfelroll.ellenfelRollM();
			jatekVege=true;
		}
	}
	
	public class ActReroll implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.out.println("You chouse to re-roll!");
			
			bChoseD1.setVisible(true);
			bChoseD2.setVisible(true);
			bChoseD3.setVisible(true);
			bChoseD4.setVisible(true);
			bChoseD5.setVisible(true);
			
			bReRoll.setVisible(false);
			bStay.setVisible(false);
			bRase.setVisible(false);
			
			bool_chose_a_dice=false;
			
			String mess =("You decided to reroll!");
			Log.add(new Message(mess, "Player"));
		}
	}
	
	public class ActStart implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.out.println("You just hit Start!");
			
			for(int i=0;i<5;i++) {
				te_kockak.add(new Kocka(i+1,roll.rollM()));
				System.out.println("A te "+te_kockak.get(i).kocka_id+" kocka dobása: "+te_kockak.get(i).value);
			}
			for(int i=0;i<5;i++) {
				ellenfel_kockak.add(new Kocka(i+1,roll.rollM()));
				System.out.println("Az ellenfel "+ellenfel_kockak.get(i).kocka_id+" kocka dobása: "+ellenfel_kockak.get(i).value);
			}
			bool_megy=true;
			bool_can_chouse=true;
			bStart.setVisible(false);
			bRase.setVisible(true);
			bStay.setVisible(true);
			bReRoll.setVisible(true);
			
			bool_chose_a_dice=false;
		}
	}
	public class ActChoseD1 implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			System.out.println("You chouse dise 1!");
			dobott_kockak.add(new Kocka(te_kockak.get(0).kocka_id,te_kockak.get(0).value)); 
			System.out.println("you chouse "+te_kockak.get(0).value);
			bChoseD1.setVisible(false);
			bool_chose_a_dice=true;
		}
	}
	
	public class ActChoseD2 implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			System.out.println("You chouse dise 2!");
			dobott_kockak.add(new Kocka(te_kockak.get(1).kocka_id,te_kockak.get(1).value)); 
			System.out.println("you chouse "+te_kockak.get(1).value);
			bChoseD2.setVisible(false);
			bool_chose_a_dice=true;
		}
	}

	public class ActChoseD3 implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			System.out.println("You chouse dise 3!");
			dobott_kockak.add(new Kocka(te_kockak.get(2).kocka_id,te_kockak.get(2).value)); 
			System.out.println("you chouse "+te_kockak.get(2).value);
			bChoseD3.setVisible(false);
			bool_chose_a_dice=true;
		}
	}
	
	public class ActChoseD4 implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			System.out.println("You chouse dise 4!");
			dobott_kockak.add(new Kocka(te_kockak.get(3).kocka_id,te_kockak.get(3).value)); 
			System.out.println("you chouse "+te_kockak.get(3).value);
			bChoseD4.setVisible(false);
			bool_chose_a_dice=true;
		}
	}
	
	public class ActChoseD5 implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			System.out.println("You chouse dise 4!");
			dobott_kockak.add(new Kocka(te_kockak.get(4).kocka_id,te_kockak.get(4).value)); 
			System.out.println("you chouse "+te_kockak.get(4).value);
			bChoseD5.setVisible(false);
			bool_chose_a_dice=true;
		}
	}
	
	public class ActNo implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			System.out.println("You pressed No!");
			Main.terminator = true;
			GUI.frame.dispose();
		}
	}
	
	public class ActYes implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			System.out.println("You pressed Yes!");
			haveWinner=false;
			jatekVege=false;
			playerWining = false;
			draw = false;
			bool_megy = false;
			bool_can_chouse = false;
			bool_chose_a_dice=false;
			te_kockak.clear();
			dobott_kockak.clear();
			ellenfel_kockak.clear();
			Log.clear();
			bStart.setVisible(true);
		}
	}
	
	public class ActLeave implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			System.out.println("You pressed Leave!");
			gameLogKockaPoker gamelogKockapoker = new gameLogKockaPoker();
			gamelogKockapoker.gamelog();
			haveWinner=false;
			jatekVege=false;
			playerWining = false;
			draw = false;
			bool_megy = false;
			bool_can_chouse = false;
			bool_chose_a_dice=false;
			te_kockak.clear();
			dobott_kockak.clear();
			ellenfel_kockak.clear();
			Log.clear();
			bStart.setVisible(true);
			cl.show(panelCont, "gamechooser");
		}
	}
	
	public class ActRase implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			System.out.println("You just hit the RASE button!");
			int maxRese =1000;
			if(javaSql.getMoney(GUI.bejelentkez.get(0).login) < maxRese) {
				maxRese = javaSql.getMoney(GUI.bejelentkez.get(0).login);
			}
			tet.rase(javaSql.getMoney(bejelentkez.get(0).login));
		}
	}
	
}