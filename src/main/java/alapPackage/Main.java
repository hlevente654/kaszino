package alapPackage;
import java.sql.SQLException;

import BlackJack.BlackJack;
import KockaPoker.KockaPoker;


public class Main implements  Runnable{
	//Meghívhatóvá teszi a GUI class-t
	KockaPoker kockapoker = new KockaPoker();
	GUI gui = new GUI();
	javaSql jdb = new javaSql();
	long xTime = System.nanoTime();
	
	public static boolean terminator = false;
	//gyõzelmek száma
	public static int pWins = 0;
	public static int dWins = 0;
	//screen refresh rate
		public int Hz = 100;
	
	public static void main(String[] args) {
		new Thread ( new Main() ) .start();

	}

	@Override
	//Ez a refress miatt kell
	public void run() {
		
		try {
			jdb.sql();;
		} catch (SQLException e) {
			System.out.println("sql suck on main");
			e.printStackTrace();
		}
			while(terminator == false) {
			if (System.nanoTime() - xTime >= 1000000000/Hz) {
				gui.frame.repaint();
				xTime = System.nanoTime();
			}
			}
	}

}
