
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class MainView extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	final MainControl mainControl;
	int a=20; //wielkosc kratki

	public MainView(MainControl mainControl) {
		super();
		this.mainControl = mainControl;
		this.setLayout(new GridLayout(3,1));	
	}


	/* (non-Javadoc)
	 * @see java.awt.Container#add(java.awt.Component)
	 */
	@Override
	public Component add(Component comp) {
		// TODO Auto-generated method stub
		JPanel p = new JPanel(new BorderLayout());
		p.add(comp, BorderLayout.CENTER);
		return super.add(p);
	}


	/* (non-Javadoc)
	 * @see java.awt.Container#remove(java.awt.Component)
	 */
	@Override
	public void remove(Component comp) {
		// TODO Auto-generated method stub
		Component [] c = this.getComponents();
		for (int i=0;i<c.length;i++)
			if(((Container) c[i]).isAncestorOf(comp)){
				((Container) c[i]).remove(comp);
				super.remove(c[i]);
				return;
			}
		//super.remove(comp);
	}

	/* (non-Javadoc)
	 * @see java.awt.Container#removeAll()
	 */
	@Override
	public void removeAll() {
		remove(mainControl.area1);
		remove(mainControl.area2);
		remove(mainControl.area3);
		super.removeAll();
	}
	
	public void changeView(){
		int i = 0;
		removeAll();
		if (this.mainControl.area1.isVisible()) i++;
		if (this.mainControl.area2.isVisible()) i++;
		if (this.mainControl.area3.isVisible()) i++;
		
		this.setLayout(new GridLayout(i,1));

		if (this.mainControl.area1.isVisible()) this.add(mainControl.area1);
		if (this.mainControl.area2.isVisible()) this.add(mainControl.area2);
		if (this.mainControl.area3.isVisible()) this.add(mainControl.area3);
	}
	
	public void paintComponent(Graphics g) {
		
//		DrawGrid(g);
	}
	
	void DrawGrid(Graphics g){
		Rectangle rec;
		rec = g.getClipBounds();
		g.setColor(Color.lightGray);
		int D = rec.width>rec.height?rec.width:rec.height;
		for(int i=1;i*a<D;i++){
			g.drawLine(0, i*a, rec.width, i*a);
			g.drawLine(i*a, 0, i*a, rec.height);
		}
		
		
	}

}
