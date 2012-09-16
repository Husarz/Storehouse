import javax.swing.JFrame;

public class Main {

	/**
	 * Tu nastepuje uruchomienie programu;
	 */
	public static void main(String[] args) {

		MainControl maincontrol;
		maincontrol = new MainControl("Warehouse");
		
		
		maincontrol.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		maincontrol.setSize(910, 680);
		maincontrol.setVisible(true);
		
	}

}
