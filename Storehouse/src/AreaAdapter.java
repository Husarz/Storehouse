import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputAdapter;


public class AreaAdapter extends MouseInputAdapter{
	
	/**
	 * 
	 */
	private final MainControl mainControl;
	private AreaView area1, area2, area3;
	
	public AreaAdapter(MainControl mainControl) {
		super();
		this.mainControl = mainControl;
		this.area1 = mainControl.area1;
		this.area2 = mainControl.area2;
		this.area3 = mainControl.area3;
		
		area1.addMouseListener(this);
		area1.addMouseMotionListener(this);
		area2.addMouseListener(this);
		area2.addMouseMotionListener(this);
		area3.addMouseListener(this);
		area3.addMouseMotionListener(this);
		
	}

	/**
	 * (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
	 * 
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		int x,y;
		AreaView area = (AreaView) e.getComponent();
		x=e.getX();
		y=e.getY();
		area.setXc(x);
		area.setYc(y);
		
		area.chxToi(x);
		area.rexToi(x);
		area.chyToj(y);
		byte k = (byte) (area.rexToi(x)>0?2:1);
		
		mainControl.setKom((short) area.chxToi(x), (short) area.chyToj(y), k);
		setAllPath();
		
		//System.out.printf("Ssnape %d \n", mainControl.warehousePath.getsSnape().getTime());
		//(mainControl.warehousePath.getsSnape().getTime());
		
		// Aktualizacja paneli:
		area1.repaint(); area2.repaint(); area3.repaint(); 
		mainControl.panel.rand.setText(String.valueOf(this.mainControl.warehousePath.getSt()));
		
//		int p = mainControl.warehousePath.getP();
//		int p1 = Integer.parseInt(mainControl.panel.take.getText());	
//		mainControl.warehousePath.setP(p + p1);
		mainControl.panel.take.setText("1");
		return;
	}

	/**
	 *  (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mouseMoved(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		super.mouseMoved(e);
		AreaView area = (AreaView) e.getComponent();
		area.setXc(e.getX());
		area.setYc(e.getY());
//		area1.repaint(); area2.repaint(); area3.repaint(); 
		
	}

	void setAllPath(){
		area1.setPath(mainControl.warehousePath.getShape());
		area2.setPath(mainControl.warehousePath.getReturnPath());
		area3.setPath(mainControl.warehousePath.getMidPoint());
	}
	
}