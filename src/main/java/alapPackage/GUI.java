package alapPackage;
import javax.swing.*;

import BlackJack.BlackJack;
import BlackJack.Card;
import KockaPoker.KockaPoker;
import Parkereso.Parkereso;

import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import asztalok.bejelentkezve;
import asztalok.tet;

public class GUI  {
	BlackJack blackjack = new BlackJack();
	GameChooser gamechooser = new GameChooser();
	Register register = new Register();
	LogIn login = new LogIn();
	MainPage mainpage = new MainPage();
	KockaPoker kockapoker = new KockaPoker();
	Parkereso parkereso = new Parkereso();
	
	
//fonts
	Font fontCard = new Font("Times New Roman", Font.BOLD,40);
	Font fontQuestion = new Font("Times New Roman", Font.PLAIN, 40);
	Font fontCim = new Font("Times New Roman", Font.BOLD,70);
	Font fontLogNev = new Font("Calibri", Font.PLAIN,25);

//int 
	int kGridW = 1000;
	int kGridH = 500;
	
//arrays
	public static ArrayList <bejelentkezve> bejelentkez;
	
//JFrame
	public static JFrame frame = new JFrame ();
	public static JFrame getjframe() {
		return frame;
	}
//cardLayout
	public static CardLayout cl= new CardLayout();	
	public static CardLayout getcl() {
		return cl;
	}
//JPanel
	public static JPanel panelCont = new JPanel();
	public static JPanel getpanelCont() {
		return panelCont;
	}
//Az ablak mérete
	public static int aW = 1600;
	public static int aH = 960;
	
//színek
	public static Color colorBackground = new Color(102, 153, 0);

	public static Color colorButton = new Color(204, 204, 0);

//fonts
	public static Font fontButton = new Font("Times New Roman", Font.PLAIN,30);

//gombok
	JButton bBlackJack = new JButton();
	JButton bElsoYes = new JButton();
	JButton bElsoNo = new JButton();
	JButton bLogIn= new JButton();
	JButton bRegister= new JButton();
	JButton bLogout = new JButton();
	JButton bKockapoker = new JButton();
	JButton bParkereso = new JButton();
	
//Text fild
	JTextField Rname = new JTextField(30);
	JTextField Rpassword1 = new JTextField(30);
	JTextField Rpassword2 = new JTextField(30);
	JTextField RVezeték_Név = new JTextField(30);
	JTextField RKereszt_Név = new JTextField(30);
	JTextField REmail = new JTextField(30);
	JTextField Lname = new JTextField(30);
	JTextField Lpassword = new JTextField(30);
	
//jList 
	public static DefaultListModel<String> l1 = new DefaultListModel<String>();
	JList <String> gameLogList = new JList<String>(l1);


//log lista
	public static ArrayList <Message> Log = new ArrayList <Message>();
		
	public GUI() {
		bejelentkez = new ArrayList <bejelentkezve>();
		
// Ablak beállításai
		frame.setPreferredSize(new Dimension(aW, aH));
		//frame.setSize(aW+6, aH+6);
		frame.setTitle("Kaszino");
		
//Automatikusan tegye középre: ki kellett venni a két monitor miatt....
		frame.setVisible(true);
		
//ne lehessen újra méretezni mert akkor elcsúsznak az arányok és a pozíciók
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		panelCont.setLayout(cl);
		
		panelCont.add(gamechooser,"gamechooser");//1
		panelCont.add(blackjack,"blackjack");//2
		panelCont.add(register, "register");//3
		panelCont.add(login,"login");//4
		panelCont.add(mainpage,"mainpage");//5
		panelCont.add(kockapoker,"kockapoker");
		panelCont.add(parkereso,"parkereso");
		
		cl.show(panelCont, "mainpage");
		
		frame.add(panelCont);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

//kockapoker gombok
	//ez át kell dolgozni	
		kockapoker.add(kockapoker.getYes());
		kockapoker.add(kockapoker.getNo());
		kockapoker.add(kockapoker.getLeave());
		
		kockapoker.add(kockapoker.getButtonStart());
		kockapoker.add(kockapoker.getButtonReRoll());
		kockapoker.add(kockapoker.getButtonRoll());
		kockapoker.add(kockapoker.getButtonRase());
		kockapoker.add(kockapoker.getButtonStay());
		
		kockapoker.add(kockapoker.getButtonChoseD1());
		kockapoker.add(kockapoker.getButtonChoseD2());
		kockapoker.add(kockapoker.getButtonChoseD3());
		kockapoker.add(kockapoker.getButtonChoseD4());
		kockapoker.add(kockapoker.getButtonChoseD5());
			
//gameLog
		gameLogList.setBounds(120, 320, 360, 560);
 
		gamechooser.add(gameLogList);
		
//enélkül nem jól mûködnek a gombok
		blackjack.setLayout(null);
		gamechooser.setLayout(null);
		register.setLayout(null);
		login.setLayout(null);
		mainpage.setLayout(null);
		kockapoker.setLayout(null);
		parkereso.setLayout(null);
		
//text fildek

		Lname.setBounds(300, 400, 300, 30);
		login.add(Lname);
		Lpassword.setBounds(300, 500,300, 30);
		login.add(Lpassword);
		
		Rname.setBounds(300,540,300,30);
		register.add(Rname);
		Rpassword1.setBounds(300,640,300,30);
		register.add(Rpassword1);
		Rpassword2.setBounds(300,740,300,30);
		register.add(Rpassword2);
		RKereszt_Név.setBounds(300,440,300,30);
		register.add(RKereszt_Név);
		RVezeték_Név.setBounds(300,340,300,30);
		register.add(RVezeték_Név);
		REmail.setBounds(300,240,300,30);
		register.add(REmail);
		

//Parkereso gomb
		ActParkereso aParkereso=new ActParkereso();
		bParkereso.addActionListener(aParkereso);
		bParkereso.setBounds((kGridW/2)+500, (kGridH/2)+200, 250, 100);
		bParkereso.setText("Párkeresõ");
		bParkereso.setBackground(colorButton);
		bParkereso.setFont(fontButton);
		gamechooser.add(bParkereso);
		
//BlackJack gomb
		ActBlackJack aBlackJack=new ActBlackJack();
		bBlackJack.addActionListener(aBlackJack);
		bBlackJack.setBounds((kGridW/2)+500, (kGridH/2)+500, 250, 100);
		bBlackJack.setText("BlackJack");
		bBlackJack.setBackground(colorButton);
		bBlackJack.setFont(fontButton);
		gamechooser.add(bBlackJack);
		
//KockaPoker gomb
		ActKockapoker aKockapoker=new ActKockapoker();
		bKockapoker.addActionListener(aKockapoker);
		bKockapoker.setBounds((kGridW/2)+500, (kGridH/2)+350, 250, 100);
		bKockapoker.setText("Kockapoker");
		bKockapoker.setBackground(colorButton);
		bKockapoker.setFont(fontButton);
		gamechooser.add(bKockapoker);
		
// van már filyókod: igen
		ActElsoYes aElsoYes = new ActElsoYes();
		bElsoYes.addActionListener(aElsoYes);
		bElsoYes.setBounds(700,500, 120, 80);
		bElsoYes.setText("Van");
		bElsoYes.setBackground(colorButton);
		bElsoYes.setFont(fontButton);
		bElsoYes.setVisible(true);
		mainpage.add(bElsoYes);
		
// van már filyókod: nem
		ActElsoNo aElsoNo = new ActElsoNo();
		bElsoNo.addActionListener(aElsoNo);
		bElsoNo.setBounds(700,700, 120, 80);
		bElsoNo.setText("Nincs");
		bElsoNo.setBackground(colorButton);
		bElsoNo.setFont(fontButton);
		bElsoNo.setVisible(true);
		mainpage.add(bElsoNo);
		
//log in
		ActLogIn aLogIn = new ActLogIn();
		bLogIn.addActionListener(aLogIn);
		bLogIn.setBounds(450, 600,150,50);
		bLogIn.setText("Log in");
		bLogIn.setBackground(colorButton);
		bLogIn.setFont(fontButton);
		bLogIn.setVisible(true);
		login.add(bLogIn);
		
//Regisztrálás
		ActRegister aRegister = new ActRegister();
		bRegister.addActionListener(aRegister);
		bRegister.setBounds(450, 800,150,50);
		bRegister.setText("Regisztrál");
		bRegister.setBackground(colorButton);
		bRegister.setFont(fontButton);
		bRegister.setVisible(true);
		register.add(bRegister);
				
//logout gomb
		ActLogout aLogout = new ActLogout();
		bLogout.addActionListener(aLogout);
		bLogout.setBounds((kGridW/2)+800, (kGridH/2)+550, 250, 100);
		bLogout.setText("Log out");
		bLogout.setBackground(colorButton);
		bLogout.setFont(fontButton);
		gamechooser.add(bLogout);
		
		
		
			
		
	}
	
	public void refresher() {
		blackjack.refresher();
	}
	
	
	public static ArrayList <bejelentkezve> getBejelentkezve(){
		return bejelentkez;
	}

	public static void gamelog() {
		String id = (javaSql.getId(bejelentkez.get(0).login));
		
		String adat=javaSql.getGameLog(id);;
		
		String adatok[]=adat.split(";");
		l1.clear();
		for(int i=0;i<adatok.length;i++) {
			l1.addElement(adatok[i]);
		}
	}
	
	


//Main page
	// fõoldal
	public class MainPage extends JPanel{
		
		public void paintComponent(Graphics g) {
			g.setColor(colorBackground);
			g.fillRect(0, 0, aW, aH);
			g.setFont(fontCim);
			g.setColor(Color.black);
			g.drawString("Üdvözöllek a kis kaszinóban: ", 400, 280);
			g.setFont(fontQuestion);
			g.drawString("Van már fiókod: ", 620, 360);
			g.setFont(fontQuestion);
			g.drawString("", 400, 380);
		}
	}
	
//log in felület
	public class LogIn extends JPanel{
		
		public void paintComponent(Graphics g) {
			g.setColor(colorBackground);
			g.fillRect(0, 0, aW, aH);
			g.setColor(Color.black);
			g.setFont(fontQuestion);
			g.drawString("Log in:", 250, 300);
			g.setFont(fontLogNev);
			g.drawString("Felhasználó név: ", 300, 390);
			g.drawString("Jelszó:", 300, 490);
		}
	}
	
//game choser felület
	public class GameChooser extends JPanel{
		
		public void paintComponent(Graphics g) {
			g.setColor(colorBackground);
			g.fillRect(0, 0, aW, aH);
			bBlackJack.setVisible(true);
			
			g.setColor(Color.gray);
			g.fillRect(100, 300, 400, 600);
			
			g.setColor(Color.blue);
			g.setFont(fontCim);
			g.drawString("Játékaink: ", 900, 350);
			g.setFont(fontCard);
			String penz = "0";
			if(!bejelentkez.get(0).login.isEmpty()) {
				penz = bejelentkez.get(0).login;
			}
			g.drawString("Üdvözlünk: "+penz, 90, 150);
			g.drawString("Jelenlegi összeged: "+javaSql.getMoney(bejelentkez.get(0).login), 90, 250);
		}
		
	}
	
//Regisztráló
	public class Register extends JPanel{
		
		public void paintComponent(Graphics g) {
			g.setColor(colorBackground);
			g.fillRect(0, 0, aW, aH);
			g.setColor(Color.black);
			g.setFont(fontQuestion);
			g.drawString("Register:", 250, 100);
			
			g.setFont(fontLogNev);
			g.drawString("Email cím: ", 300, 210);
			g.drawString("Vezeték név: ", 300, 310);
			g.drawString("Kereszt név:", 300, 410);
			g.drawString("Felhasználó név: ", 300, 510);
			g.drawString("Jelszó: ", 300, 610);
			g.drawString("Jelszó ismétlése: ", 300, 710);
		}
	}


	public class ActElsoNo implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			System.out.println("Neked nincs saját fijókod! ");
			cl.show(panelCont, "register");
		}
	}
	public class ActElsoYes implements ActionListener{

		public void actionPerformed(ActionEvent e) {
		System.out.println("Neked van fiókod! ");
		cl.show(panelCont, "login");
		}
	}
	public class ActLogIn implements ActionListener{

		public void actionPerformed(ActionEvent e) {
		
		String nev = "";
		nev = Lname.getText();
		String password = "";
		password = Lpassword.getText();
		
		String loggingIn = javaSql.logIn(nev, password);
		
		String adatok[] = loggingIn.split(" ");
		
		System.out.println(adatok[0]+" "+adatok[1]+" "+adatok[2]);
		
		if(adatok[0].equals("true")) {
			System.out.println("Bejelentkeztél! ");
			
			bejelentkez.add(new bejelentkezve(adatok[1],adatok[2]));
			
			KockaPoker.bejelentkez.add(new bejelentkezve(adatok[1],adatok[2]));
			
			gamelog();
			cl.show(panelCont, "gamechooser");
		}else {
			System.out.println("Rossz név/jelszó");
		}
		}
	}

	public class ActRegister implements ActionListener{

		public void actionPerformed(ActionEvent e) {
		System.out.println("Regisztráltál! ");
		String email = "";
		email=REmail.getText();
		boolean emailJo=false;
		
		String vez_nev = "";
		vez_nev = RVezeték_Név.getText();
		boolean RVezeték_NévJo=false;
		
		String ker_nev = "";
		ker_nev = RKereszt_Név.getText();
		boolean ker_nevJo = false;
		
		String nev = "";
		nev = Rname.getText();
		boolean nevJo = false;
		
		String password1 = "";
		password1 = Rpassword1.getText();
		String password2 = "";
		password2 = Rpassword2.getText();
		boolean passwordJo = false;

		if(!email.equals("")) {
			try {
				if(javaSql.email_ellenor(email)==true) {
					emailJo=true;
				}
				else {
					JOptionPane.showMessageDialog(frame,
						    "Rossz a megadott email-cím, adjon meg egy másikat",
						    "Rossz email-cím",
						    JOptionPane.WARNING_MESSAGE);
				}
			} catch (SQLException e1) {e1.printStackTrace();}
			
		}else {
			System.out.println("tölcs ki");
			emailJo=false;
		}
		
		if(!vez_nev.equals("")) {
			try {
				if(javaSql.vez_nev_ellenor(vez_nev)) {
					RVezeték_NévJo = true;
				} else {
				}
			} catch (SQLException e1) {e1.printStackTrace();}
		}
		
		if(!ker_nev.equals("")) {
			try {
				if(javaSql.ker_nev_ellenor(ker_nev)) {
					ker_nevJo = true;
				} else {
				}
			} catch (SQLException e1) {e1.printStackTrace();}
		}
		
		if(!nev.equals("")) {
			try {
				if(javaSql.nev_ellenor(nev)) {
					nevJo = true;
				} else {
					JOptionPane.showMessageDialog(frame,
						    "Ez a felhasználónév már foglalt",
						    "Rossz név",
						    JOptionPane.WARNING_MESSAGE);
				}
			} catch (SQLException e1) {e1.printStackTrace();}
		}
		
		if(!password1.equals("")&&password1.equals(password2)) {
				try {
					if(javaSql.jelszo_ellenor(nev)) {
						passwordJo = true;
					} else {
						JOptionPane.showMessageDialog(frame,
							    "Rossz a jelszó: Minimum 8 karakternek kell lennie, szóközt nem tartalmazhat és egyedinek kell lennie.",
							    "Rossz jelszó",
							    JOptionPane.WARNING_MESSAGE);
					}
				} catch (SQLException e1) {e1.printStackTrace();}
		}else {
			JOptionPane.showMessageDialog(frame,
				    "Nem egyeznek a jelszók",
				    "Rossz jelszó",
				    JOptionPane.WARNING_MESSAGE);
		}
		if(emailJo==true&&RVezeték_NévJo==true&&ker_nevJo==true&&nevJo==true&&passwordJo==true) {
			try {
				javaSql.newPlayer(vez_nev, ker_nev, email, nev, password1);
			} catch (SQLException e1) {
				System.out.println("a beadás a baj");
				e1.printStackTrace();
			}
			cl.show(panelCont, "login");
		}else {
			System.out.println("baj van");
		}
		}
	}
	
	public class ActKockapoker implements ActionListener{

		public void actionPerformed(ActionEvent e) {
		System.out.println("Kockapókert játszol! ");
		
		if(javaSql.getMoney(GUI.getBejelentkezve().get(0).login) == 0) {
			JOptionPane.showMessageDialog(GUI.frame,
				    "Játék elõtt tegyél pénzt a fiókodra.",
				    "Nem tudsz játszani!",
				    JOptionPane.WARNING_MESSAGE);
			}else {
		cl.show(panelCont, "kockapoker");
			}
		}
	}

//Mit csináljon amikor a BlackJack
	public class ActBlackJack implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
		if(javaSql.getMoney(GUI.getBejelentkezve().get(0).login) == 0) {
			JOptionPane.showMessageDialog(GUI.frame,
				    "Játék elõtt tegyél pénzt a fiókodra.",
				    "Nem tudsz játszani!",
				    JOptionPane.WARNING_MESSAGE);
			}else {
		cl.show(panelCont, "blackjack");
			}
		}
	}

	public class ActParkereso implements ActionListener{

		public void actionPerformed(ActionEvent e) {
		System.out.println("Kockapókert játszol! ");
		
		if(javaSql.getMoney(GUI.getBejelentkezve().get(0).login) == 0) {
			JOptionPane.showMessageDialog(GUI.frame,
				    "Játék elõtt tegyél pénzt a fiókodra.",
				    "Nem tudsz játszani!",
				    JOptionPane.WARNING_MESSAGE);
			}else {
		
		cl.show(panelCont, "parkereso");
			}
		}
	}

	public class ActLogout implements ActionListener{

		public void actionPerformed(ActionEvent e) {
		System.out.println("Kilyelentkeztél! ");
		Lname.setText("");
		Lpassword.setText("");
		
		bejelentkez.clear();
		cl.show(panelCont, "login");
		}
	}
}
