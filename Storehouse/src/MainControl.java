import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.util.ArrayList;
import javax.swing.*;

import warehouse.*;

/**
 * @author adi
 *
 */
public class MainControl extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Glowne pola aplikacji (filed summary): contentPane, area, panel, warehouse;
	 */
	JPanel contentPane;		// contentPane	-	glowny panel aplikacji
	AreaView area1, area2, area3;			// area 		- 	panele rysujace znajdujacy sie w contentPane
	MainView mainView;
	PanelStatus panel;		// panel 		- 	panel z ustawieniami aplikacji
	public Warehouse warehouse;	// warehouse	-	silnik aplikacji (algorytmy i struktura danych)
	public WarehousePath warehousePath;

	ArrayList<WarehousePath> warehouses;
	
	AreaAdapter areaAdapter;
	
	
	public MainControl() throws HeadlessException {
		super();			// pusty konstruktor
	}
	
	/**
	 *  Glowny konstruktor aplikacji;
	 */

	public MainControl(String title) throws HeadlessException {
		super(title);
		
		////////TESTY/////////
		
		warehouses = new ArrayList<WarehousePath>();
		warehouses.add(new WarehousePath(new Warehouse()));
		warehousePath = warehouses.get(0);
		warehouse = warehouses.get(0).getWarehouse();           // rysowanie pewnego magazynu z listy  warehouses.
		
		area1 = new AreaView(warehousePath);						// do metody sSnape
		area1.setPath(warehousePath.getShape());
		area2 = new AreaView(warehousePath);						// do metody return
		area2.setPath(warehousePath.getReturnPath());
		area3 = new AreaView(warehousePath);						// do metody midPoint
		area3.setPath(warehousePath.getMidPoint());
		//		MainView mainView2 = new MainView();
//		jp1.add(area2,  BorderLayout.CENTER);
		//////////////////////

		contentPane = new JPanel(new BorderLayout());
		mainView = new MainView(this);                           // tu nalezy umieszczac obikty area
		panel = new PanelStatus(this); 
		
		areaAdapter = new AreaAdapter(this);
		
		setContentPane(contentPane);
		
		mainView.add(area1); 
		mainView.add(area2);
		mainView.add(area3);
		
		contentPane.add(mainView, BorderLayout.CENTER);
		contentPane.add(panel, BorderLayout.EAST);
	}
	
	/**
	 * @param warehouse the warehouse to set
	 */
	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
		warehousePath = new WarehousePath(warehouse);
		setArea();
	}
	
	/**
	 * @param area the area to set
	 */
	public void setArea() {
		
			area1.setWarehouse(warehousePath);
			area1.setPath(warehousePath.getShape());

			area2.setWarehouse(warehousePath);
			area2.setPath(warehousePath.getReturnPath());
	
			area3.setWarehouse(warehousePath);
			area3.setPath(warehousePath.getMidPoint());

			rePaintAreas();
	}
	private void rePaintAreas(){
		this.area1.repaint();
		this.area2.repaint();
		this.area3.repaint();
	}
	
	public void setAllPath(){
		warehousePath.getShape();
		warehousePath.getReturnPath();
		warehousePath.getMidPoint();
	}

	/**
	 * @param i
	 * @param j
	 * @param k
	 * @see warehouse.WarehousePath#setKom(short, short, byte)
	 */
	public void setKom(short i, short j, byte k) {
		warehousePath.setKom(i, j, k);
	}

}
