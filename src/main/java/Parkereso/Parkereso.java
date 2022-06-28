package Parkereso;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import java.util.TimerTask;
import java.util.Timer;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import alapPackage.GUI;
import alapPackage.javaSql;
import asztalok.tet;

public class Parkereso extends JPanel{
	//cases
	gameLogParkereso gamelogparkereso = new gameLogParkereso();
	
//ints
	int megtaláltpárok = 0;
	int nemTaláltPárok = 0;
	int szuksegesParok = 0;
	
//pénz int/double
	double maxSzorzo = 0;
	double csokkento = 0;
	
//textfiled
	Font fontCard = new Font("Times New Roman", Font.BOLD,40);
	JLabel Tét = new JLabel();
	JLabel pénzed= new JLabel();
	JLabel nyertel= new JLabel();
	JLabel potencialEarning = new JLabel();
	JLabel talaltParok= new JLabel();
	JLabel currentBestMoney = new JLabel();;
	
//where what
	static HashMap<Integer,Integer> Mapgrids=new HashMap();
	
//image
	static ImageIcon tartalekHatter = new ImageIcon();
	ImageIcon elsoIcon = new ImageIcon();
	ImageIcon masodikIcon = new ImageIcon();
	
//forditva van
	ArrayList <Integer> fordiIthely = new ArrayList();
	
//boolean
	boolean firstClick = false;
	boolean secondClick = false;
	boolean startPage =true;
	
//int click value
	int firstClickValue=-1;
	int secondClickValue=-1;
	int elsoGombHelye = -1;
	int masodikGombHelye = -1;
	
//dileay boolean
	boolean kellDilay = false;
	
	List<JPanel> gameFiledBoxes;
	
	ImageIcon image1;
	
	String [] messagerString = {"Válassz nehézséget","2x2","4x4","6x6"};
	JComboBox chose = new JComboBox(messagerString);
	
//JPanelek
	JPanel ParkeresoJatek= new JPanel();
	
	JPanel gameFiled2x2 =new JPanel();
	JPanel gameFiled4x4 =new JPanel();
	JPanel gameFiled6x6 =new JPanel();
	
	JPanel gameFiled2x2Box =new JPanel();
	JPanel gameFiled4x4Box =new JPanel();
	JPanel gameFiled6x6Box =new JPanel();
	
	JPanel dumi1 =new JPanel();
	JPanel dumi2 =new JPanel();
	JPanel dumi3 =new JPanel();
	
	JPanel dumi4 =new JPanel();
	JPanel dumi5 =new JPanel();
	JPanel dumi6 =new JPanel();
	
	JPanel dumi7 =new JPanel();
	JPanel scoreBord =new JPanel();
	JPanel dumi9 =new JPanel();
	
//JButtons
	JButton bStart = new JButton();
	JButton bExit = new JButton();
	JButton newGame = new JButton();
	JButton exitGame = new JButton();
	JButton bRase = new JButton();
	JButton bLower = new JButton();
	JButton bBacktoStart = new JButton();
	
	Font fontQuestion= GUI.fontButton;

//szinek
	private static final Color BACKGROUND = new JPanel().getBackground();
	Color colorBackground = new Color(102, 153, 0);
	
	CardLayout Parcl= new CardLayout();
	
	JPanel start = new JPanel();
	
	int maximumNyeremeny;
	
	public Parkereso() {
		
		potencialEarning.setBounds(900,350,800,200);
		potencialEarning.setFont(fontCard);
		potencialEarning.setText("Maximum nyeremény: ");
		Tét.setBounds(900,250,500,200);
		Tét.setFont(fontCard);
		Tét.setText("Jelenlegi tét: "+tet.getTet());
		start.add(Tét);
		start.add(pénzed);
		start.add(potencialEarning);
		
	//scorebord
		talaltParok.setText("Megtalált párok: "+megtaláltpárok);
		talaltParok.setBounds(220,0,150,150);
		scoreBord.setLayout(null);
		scoreBord.add(talaltParok);
	
		currentBestMoney.setText("Rossz párok száma: "+nemTaláltPárok);
		currentBestMoney.setBounds(220,50,150,150);
		scoreBord.add(currentBestMoney);
		
	//réteg rendezés
		GridLayout ParkJatek = new GridLayout(0,1);
		this.setLayout(ParkJatek);
		this.add(ParkeresoJatek);
		
		start.setBackground(colorBackground);
		
		gameFiled2x2.setBackground(Color.red);
		gameFiled4x4.setBackground(Color.blue);
		gameFiled6x6.setBackground(Color.gray);
		
	//start menu
		ActStart astart = new ActStart();
		bStart.addActionListener(astart);
		bStart.setBounds(730, 500, 100,70);
		bStart.setText("Start");
		bStart.setVisible(true);
		bStart.setBackground(GUI.colorButton);
		bStart.setFont(fontQuestion);
		start.add(bStart);

	//bnewGame button
		ActnewGame asnewGame = new ActnewGame();
		newGame.addActionListener(asnewGame);
		newGame.setBounds(65,200,200,70);
		newGame.setText("New Game");
		newGame.setVisible(false);
		newGame.setBackground(GUI.colorButton);
		newGame.setFont(fontQuestion);
		scoreBord.add(newGame);
	//exitGame
		ActexitGame asexitGame = new ActexitGame();
		exitGame.addActionListener(asexitGame);
		exitGame.setBounds(280,200,200,70);
		exitGame.setText("Exit Game");
		exitGame.setVisible(false);
		exitGame.setBackground(GUI.colorButton);
		exitGame.setFont(fontQuestion);
		scoreBord.add(exitGame);
	//back to manu button
		ActBacktoStart asBacktoStart = new ActBacktoStart();
		bBacktoStart.addActionListener(asBacktoStart);
		bBacktoStart.setBounds(700, 650, 200,70);
		bBacktoStart.setText("Back to menu");
		bBacktoStart.setVisible(true);
		bBacktoStart.setBackground(GUI.colorButton);
		bBacktoStart.setFont(fontQuestion);
		start.add(bBacktoStart);
	//rase button
		Actrase asrase = new Actrase();
		bRase.addActionListener(asrase);
		bRase.setBounds(600, 500, 120,60);
		bRase.setText("Rase");
		bRase.setVisible(true);
		bRase.setBackground(GUI.colorButton);
		bRase.setFont(fontQuestion);
		start.add(bRase);
	//lower nutton
		Actlower aslower = new Actlower();
		bLower.addActionListener(aslower);
		bLower.setBounds(840, 500, 120,60);
		bLower.setText("Lower");
		bLower.setVisible(true);
		bLower.setBackground(GUI.colorButton);
		bLower.setFont(fontQuestion);
		start.add(bLower);
		
		start.setLayout(null);
		chose.setBounds(680,380,200,20);
		chose.setSelectedIndex(0);
		Actchose aschose = new Actchose();
		chose.addActionListener(aschose);
		
		start.add(chose);
		
	//2x2 feltöltése
		GridLayout toSuzeGrid = new GridLayout(0,3);
		gameFiled2x2.setLayout(toSuzeGrid);
		gameFiled2x2.add(dumi1);
		dumi1.setBackground(colorBackground);
		
		gameFiled2x2.add(dumi2);
		dumi2.setBackground(colorBackground);
		
		gameFiled2x2.add(dumi3);
		dumi3.setBackground(colorBackground);
		
		gameFiled2x2.add(dumi4);
		dumi4.setBackground(colorBackground);
		
		gameFiled2x2.add(gameFiled2x2Box);
		gameFiled2x2.add(dumi6);
		dumi6.setBackground(colorBackground);
		
		gameFiled2x2.add(dumi7);
		dumi7.setBackground(colorBackground);
		
		gameFiled2x2.add(scoreBord);
		scoreBord.setBackground(new Color(153, 255, 153) );
		
		gameFiled2x2.add(dumi9);
		dumi9.setBackground(colorBackground);
	//4x4 feltöltése
		gameFiled4x4.setLayout(toSuzeGrid);
		dumi1.setBackground(colorBackground);
		
		dumi2.setBackground(colorBackground);
		
		dumi3.setBackground(colorBackground);
		
		dumi4.setBackground(colorBackground);
		
		dumi6.setBackground(colorBackground);
		
		dumi7.setBackground(colorBackground);
		
		scoreBord.setBackground(new Color(153, 255, 153));
		
		dumi9.setBackground(colorBackground);
		ParkeresoJatek.setBackground(Color.BLACK);
	//6x6 feltöltése
		gameFiled6x6.setLayout(toSuzeGrid);

		dumi1.setBackground(colorBackground);
		
		dumi2.setBackground(colorBackground);
		
		dumi3.setBackground(colorBackground);
		
		dumi4.setBackground(colorBackground);
		
		dumi6.setBackground(colorBackground);
		
		dumi7.setBackground(colorBackground);
		
		scoreBord.setBackground(colorBackground);
		
		dumi9.setBackground(colorBackground);
		ParkeresoJatek.setBackground(Color.BLACK);
		
		ParkeresoJatek.setLayout(Parcl);
		
		ParkeresoJatek.add(gameFiled2x2,"gameFiled2x2");
		ParkeresoJatek.add(gameFiled4x4,"gameFiled4x4");
		ParkeresoJatek.add(gameFiled6x6,"gameFiled6x6");
		ParkeresoJatek.add(start,"start");
		Parcl.show(ParkeresoJatek, "start");
		
		gameFiledBoxes = Arrays.asList(gameFiled2x2Box,gameFiled4x4Box,gameFiled6x6Box);
	}
	
//ez itt marad
public void setGrid(int x,int y) {
		
		if(x == 2){
			maxSzorzo = 1.05;
			csokkento = -0.5;
			gameFiled2x2.add(dumi1);
			dumi1.setBackground(colorBackground);
			
			gameFiled2x2.add(dumi2);
			dumi2.setBackground(colorBackground);
			
			gameFiled2x2.add(dumi3);
			dumi3.setBackground(colorBackground);
			
			gameFiled2x2.add(dumi4);
			dumi4.setBackground(colorBackground);
			
			gameFiled2x2.add(scoreBord);
			gameFiled2x2.add(dumi6);
			dumi6.setBackground(colorBackground);
			
			gameFiled2x2.add(dumi7);
			dumi7.setBackground(colorBackground);
			
			gameFiled2x2.add(gameFiled2x2Box);
			scoreBord.setBackground(new Color(153, 255, 153) );
			
			gameFiled2x2.add(dumi9);
			dumi9.setBackground(colorBackground);
	}
		
		if(x == 4){
			maxSzorzo = 160;
			csokkento = -40;
			gameFiled4x4.add(dumi1);
			dumi1.setBackground(colorBackground);
			
			gameFiled4x4.add(scoreBord);
			scoreBord.setBackground(new Color(153, 255, 153) );
			
			gameFiled4x4.add(dumi3);
			dumi3.setBackground(colorBackground);
			
			gameFiled4x4.add(dumi2);
			dumi4.setBackground(colorBackground);
			
			gameFiled4x4.add(gameFiled4x4Box);
			gameFiled4x4.add(dumi6);
			dumi6.setBackground(colorBackground);

			dumi9.setBackground(colorBackground);
	}
		if(x == 6){
			maxSzorzo = 1440;
			csokkento = -110;
			gameFiled6x6.add(dumi1);
			dumi1.setBackground(colorBackground);
			
			gameFiled6x6.add(scoreBord);
			scoreBord.setBackground(new Color(153, 255, 153) );
			
			gameFiled6x6.add(dumi3);
			dumi3.setBackground(colorBackground);
			
			gameFiled6x6.add(dumi2);
			dumi4.setBackground(colorBackground);
			
			gameFiled6x6.add(gameFiled6x6Box);
			gameFiled6x6.add(dumi6);
			dumi6.setBackground(colorBackground);

			dumi9.setBackground(colorBackground);
	}
	}
	
	public void runGame(int x,int y){
		GridLayout grid = new GridLayout(x,y);
		
		gameFiled2x2Box.setLayout(grid);
		gameFiled4x4Box.setLayout(grid);
		gameFiled6x6Box.setLayout(grid);
		
		
		setGrid(x,y);
		
		//get all cards
		getCard getcard = new getCard();
		List<ImageIcon> felforditottImageScaledIcons = getcard.getCardM();
		
		//chose the needed amount of cards és add vissza labelként
		putOnTable putontable = new putOnTable();
		List<ImageIcon> imageScaledCards = putontable.putOnTableM(felforditottImageScaledIcons,x,y);
		
		getNumberOfBack getnumberofback = new getNumberOfBack();
		List<ImageIcon> imageScaledBacksIcons = getnumberofback.getNumberOfBackM(x,y);

		//itt az on click/ezen dolgozom
		List<JLabel> imageLabels = getLabels(imageScaledBacksIcons,imageScaledCards,x,y);

		//szét kell szedni
		getPanels getpanels = new getPanels();
		List<JPanel> imagePanel = getpanels.getPanelsM(imageLabels);
		
		//showGameFiledBox
		showGameFiledBox showgamefiledbox = new showGameFiledBox();
		showgamefiledbox.showGameFiledBox(x,y,imagePanel,gameFiledBoxes);
		
	}
	

	public List<JLabel> getLabels(final List<ImageIcon> scaledBackCards,final List<ImageIcon> imageScaledCards,int x,int y){
		final List<JLabel> imageLabels = new ArrayList<>();
		List<JLabel> imageLabelsCards = new ArrayList<>();
		HashMap<Integer , JLabel> grids = new HashMap<>();
		HashMap<Integer , JLabel> cards = new HashMap<>();
		szuksegesParok=x;
	
		
		for(int i=0;i<scaledBackCards.size();i++) {
			imageLabels.add(new JLabel(scaledBackCards.get(i)));
			imageLabelsCards.add(new JLabel(imageScaledCards.get(i)));
			grids.put(i, imageLabels.get(i));
			cards.put(i, imageLabelsCards.get(i));
		}

		//scaledCards = back
		for(int i=0;i<scaledBackCards.size();i++) {
			
			imageLabels.get(i).addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent e) {
					for(int i=0;i<scaledBackCards.size();i++) {
						if(e.getSource()==imageLabels.get(i)) {
							if(elsoGombHelye == -1 || masodikGombHelye==-1) {
							
							System.out.println("a "+i+" (e)s gombot nyomtad meg" );
							fordiIthely.add(i);

							if(secondClickValue==-1 && firstClickValue!=-1) {
								secondClickValue=Mapgrids.get(i);
								masodikGombHelye=i;
								System.out.println("az masodik erteke "+secondClickValue);
								imageLabels.get(i).setIcon(imageScaledCards.get(i));
							}
							if(firstClickValue==-1) {
								firstClickValue=Mapgrids.get(i);
								elsoGombHelye=i;
								System.out.println("az elsõ erteke "+firstClickValue);
								imageLabels.get(i).setIcon(imageScaledCards.get(i));
							}
							
							if( firstClickValue!=-1 && secondClickValue!=-1) {

								isPair ispair = new isPair();
								if(ispair.isPairM(firstClickValue,secondClickValue)==true) {
									System.out.println("is pair");
									megtaláltpárok++;
									//megvan az összes pár
									if(megtaláltpárok==szuksegesParok) {
										exitGame.setVisible(true);
										newGame.setVisible(true);
										currentBestMoney.setText("Nyertél: "+(tet.getTet())*(maxSzorzo+(csokkento*nemTaláltPárok)));
										
										javaSql.setMoney(Integer.toString((int)Math.round(javaSql.getMoney(GUI.getBejelentkezve().get(0).login)+(tet.getTet())*(maxSzorzo+(csokkento*nemTaláltPárok)))),GUI.getBejelentkezve().get(0).login);
										javaSql.gameLog(GUI.getBejelentkezve().get(0).login, Integer.toString((int)Math.round((tet.getTet())*(maxSzorzo+(csokkento*nemTaláltPárok)))));
										int nyertOsszeg = (int) Math.round((tet.getTet())*((maxSzorzo+(csokkento*nemTaláltPárok))));
										System.out.println("nyert: "+ nyertOsszeg);
									}
									talaltParok.setText("Megtalált párok: "+megtaláltpárok);
									System.out.println(megtaláltpárok);
									imageLabels.get(fordiIthely.get(0)).setIcon(null);
									imageLabels.get(fordiIthely.get(1)).setIcon(null);
									fordiIthely.clear();
									elsoGombHelye=-1;
									masodikGombHelye=-1;
								}
								else
								{
									System.out.println("not pair");
									nemTaláltPárok++;
									currentBestMoney.setText("Rossz párok száma: "+nemTaláltPárok);
									
									//várás
									Timer timer = new Timer();
									
									TimerTask task = new TimerTask() {

										@Override
										public void run() {
											imageLabels.get(elsoGombHelye).setIcon(tartalekHatter);
											imageLabels.get(masodikGombHelye).setIcon(tartalekHatter);
											elsoGombHelye=-1;
											masodikGombHelye=-1;
										}};
										timer.schedule(task, 2000);
									//várás után
									fordiIthely.clear();
									
								}
								
								//dilay();
								firstClickValue=-1;
								secondClickValue=-1;
							}
						}
						}
					}
				}
				@Override
				public void mouseClicked(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseEntered(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mousePressed(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
			});
		}
		
		return imageLabels;
	}
	

	public class ActStart implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.out.println("You just hit the Start button!");
			
			if(chose.getSelectedIndex()==1) {
				System.out.println("2x2 választottál");
				runGame(2,2);
				Parcl.show(ParkeresoJatek, "gameFiled2x2");
			}
			if(chose.getSelectedIndex()==2) {
				System.out.println("4x4 választottál");
				runGame(4,4);
				Parcl.show(ParkeresoJatek, "gameFiled4x4");
			}
			if(chose.getSelectedIndex()==3) {
				System.out.println("6x6 választottál");
				runGame(6,6);
				Parcl.show(ParkeresoJatek, "gameFiled6x6");
			}
		}
	}
	
	public class ActnewGame implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			gameFiled2x2Box.removeAll();
			gameFiled2x2Box.revalidate();
			gameFiled2x2Box.repaint();
			
			gameFiled4x4Box.removeAll();
			gameFiled4x4Box.revalidate();
			gameFiled4x4Box.repaint();
			
			gameFiled6x6Box.removeAll();
			gameFiled6x6Box.revalidate();
			gameFiled6x6Box.repaint();
			
			megtaláltpárok=0;
			exitGame.setVisible(false);
			newGame.setVisible(false);
			
			Parcl.show(ParkeresoJatek, "start");
			
		}
	}
	
	public class ActexitGame implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.out.println("Megnyomtad az EXIT-et");
			
			gameFiled2x2Box.removeAll();
			gameFiled2x2Box.revalidate();
			gameFiled2x2Box.repaint();
			
			gameFiled4x4Box.removeAll();
			gameFiled4x4Box.revalidate();
			gameFiled4x4Box.repaint();
			
			gameFiled6x6Box.removeAll();
			gameFiled6x6Box.revalidate();
			gameFiled6x6Box.repaint();
			
			megtaláltpárok=0;
			exitGame.setVisible(false);
			newGame.setVisible(false);
			
			gamelogparkereso.gamelog();
			
			Parcl.show(ParkeresoJatek, "start");
			GUI.getcl().show(GUI.getpanelCont(), "gamechooser");
			}
		}
	public class Actchose implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == chose) {
				JComboBox cb = (JComboBox)e.getSource();
				String msg = (String)cb.getSelectedItem();
				if(javaSql.getMoney(GUI.getBejelentkezve().get(0).login)< 1000) {
					switch (msg) {
					case "2x2": potencialEarning.setText("Maximum nyeremény: "+(javaSql.getMoney(GUI.getBejelentkezve().get(0).login)*1.05));
					break;
					case "4x4": potencialEarning.setText("Maximum nyeremény: "+(javaSql.getMoney(GUI.getBejelentkezve().get(0).login)*160));
					break;
					case "6x6": potencialEarning.setText("Maximum nyeremény: "+(javaSql.getMoney(GUI.getBejelentkezve().get(0).login)*1440));
					break;
					default : potencialEarning.setText("");
					}
				}else {
				switch (msg) {
				case "2x2": potencialEarning.setText("Maximum nyeremény: "+(tet.getTet()*1.05));
				break;
				case "4x4": potencialEarning.setText("Maximum nyeremény: "+(tet.getTet()*160));
				break;
				case "6x6": potencialEarning.setText("Maximum nyeremény: "+(tet.getTet()*1440));
				break;
				default : potencialEarning.setText("");
				}
				}
				
			}
			
		}
	}
	
//rase button
	public class Actrase implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.out.println("You just hit the RASE button! ");

			GUI.frame.invalidate();
			GUI.frame.validate();
			GUI.frame.repaint();
			
			if(chose.getSelectedIndex()==1) {
				tet.rase(javaSql.getMoney(GUI.getBejelentkezve().get(0).login));
				potencialEarning.setText("Maximum nyeremény: "+(tet.getTet()*1.05));
			}
			if(chose.getSelectedIndex()==2) {
				tet.rase(javaSql.getMoney(GUI.getBejelentkezve().get(0).login));
				potencialEarning.setText("Maximum nyeremény: "+(tet.getTet()*160));
			}
			if(chose.getSelectedIndex()==3) {
				tet.rase(javaSql.getMoney(GUI.getBejelentkezve().get(0).login));
				potencialEarning.setText("Maximum nyeremény: "+(tet.getTet()*1440));
			}
			
			Tét.setText("Jelenlegi tét: "+tet.getTet());
			
		}
	}
	
	public class Actlower implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.out.println("You just hit the Lower button! ");

			GUI.frame.invalidate();
			GUI.frame.validate();
			GUI.frame.repaint();
			
			if(chose.getSelectedIndex()==1) {
				tet.lower(javaSql.getMoney(GUI.getBejelentkezve().get(0).login));
				potencialEarning.setText("Maximum nyeremény: "+(tet.getTet()*1.05));
			}
			if(chose.getSelectedIndex()==2) {
				tet.lower(javaSql.getMoney(GUI.getBejelentkezve().get(0).login));
				potencialEarning.setText("Maximum nyeremény: "+(tet.getTet()*160));
			}
			if(chose.getSelectedIndex()==3) {
				tet.lower(javaSql.getMoney(GUI.getBejelentkezve().get(0).login));
				potencialEarning.setText("Maximum nyeremény: "+(tet.getTet()*1440));
			}
			
			Tét.setText("Jelenlegi tét: "+tet.getTet());
			
		}
	}
	
	public class ActBacktoStart implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.out.println("Megnyomtad az EXIT-et");
			gamelogparkereso.gamelog();
			GUI.getcl().show(GUI.getpanelCont(), "gamechooser");
		}
	}
	
}
