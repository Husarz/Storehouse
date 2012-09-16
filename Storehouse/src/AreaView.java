import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import warehouse.*;

public class AreaView extends JLabel implements PropertyChangeListener{
	
	private static final long serialVersionUID = 1L;
	/*
	* Tablica kolorow
	* 0-red, 1-green, 2-orange, 3-pink, 4-blue, 5-yellow, 6-cyan, 7-lightGrey
	*/
	public static Color [] Col = {Color.red,Color.green,Color.orange,
		Color.pink,Color.blue,Color.yellow,
		Color.cyan,Color.lightGray};
	public static String alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	static NumberFormat formatter = new DecimalFormat("#0.0000");
	
	int a=15; //wielkosc kratki
	int xc,yc;
	int[] dimension;

	WParameters wParamenters;
	Warehouse warehouse;
	Path path;
	
	public AreaView() {
		super();
		setBorder(BorderFactory.createLineBorder(Color.black,3));
		warehouse = null;
		path = null;
		dimension = new int [2];
	}

	public AreaView(WarehousePath warehouse) {
		super();
		this.warehouse = warehouse.getWarehouse();
		wParamenters = warehouse;
		dimension = new int [2];
		setBorder(BorderFactory.createLineBorder(Color.black));
	}


	public void paintComponent(Graphics g) {
		
		DrawGrid(g);
		DrawD(g);
		
		if(warehouse == null) return;
		
		g.setColor(Color.BLACK);
		
		g.drawRect(a-2, a-2, 3*a*(warehouse.getL()-1)+3*a+5, (warehouse.getH()-1)*a+a+5);
		g.drawRect(a-3, a-3, 3*a*(warehouse.getL()-1)+3*a+5, (warehouse.getH()-1)*a+a+5);
		
		for(int i,j=0;j<warehouse.getH();j++){			//wiersze

			for(i=0;i<warehouse.getL();i++){         	//koluny
				DrawElement(g,i,j);
			}
		}
		if (path!=null) DrawPath(g);
	}
	
	private void DrawD(Graphics g) {
		g.setColor(Color.BLACK);
		int pom = 0;
	//	g.setFont(g.getFont().deriveFont(38));
		for (int i=1; i<warehouse.getH()-1; i++){
			g.drawString(String.valueOf(i), 3*warehouse.getL()*a+2*a, a*(i+2)-2);
		}
		
		for (int i=0,j=0; i<warehouse.getL(); j++, i++){
			if(j*2+1>alphabet.length()) {
				pom++; j=0;
			}
			g.drawString(String.valueOf(alphabet.charAt(j*2)), a*(i*3)+a+2, warehouse.getH()*a+2*a);
			g.drawString(String.valueOf(alphabet.charAt(j*2+1)), a*(i*3)+3*a+2, warehouse.getH()*a+2*a);
			if(pom>0){
				g.drawString(String.valueOf(alphabet.charAt(pom-1)), a*(i*3)+a+2, warehouse.getH()*a+2*a+12);
				g.drawString(String.valueOf(alphabet.charAt(pom-1)), a*(i*3)+3*a+2, warehouse.getH()*a+2*a+12);
			}
		}
		
	}

	void DrawGrid(Graphics g){
		Rectangle rec;
		rec = g.getClipBounds();
		g.setColor(Col[7]);
		int D = rec.width>rec.height?rec.width:rec.height;
		for(int i=1;i*a<D;i++){
			g.drawLine(0, i*a, rec.width-150, i*a);
			if (i*a < rec.width-150)
				g.drawLine(i*a, 0, i*a, rec.height);
		}
		
		
	}

	void DrawElement(Graphics g, int x, int y){ // i=x, j=y

		if (y>0 && y<warehouse.getH()-1){
			g.setColor(Col[7]);
			if (warehouse.getKom(x, y)==1 || warehouse.getKom(x, y)==3) 
				g.setColor(Color.BLACK);
			g.fillRect(3*x*a+a, y*a+a, a-1, a-1);
			g.setColor(Color.BLACK);
			g.drawRect(3*x*a+a, y*a+a, a-2, a-2);
			g.drawRect(3*x*a+a, y*a+a, a-3, a-3);
			g.setColor(Col[7]);
			if (warehouse.getKom(x, y)>1) 
				g.setColor(Color.BLACK);
			g.fillRect((3*x+2)*a+a, y*a+a, a-1, a-1);
			g.setColor(Color.BLACK);
			g.drawRect((3*x+2)*a+a, y*a+a, a-2, a-2);
			g.drawRect((3*x+2)*a+a, y*a+a, a-3, a-3);
		}
	}
	

	public void DrawPath(Graphics g){
	//	String dataPath = new String(" ");
		int pv0 = a/3, pv = a/3; // przesuniecie poziome lini w kratce 
		int ph0 = a/3, ph = a/3; // przesuniecie w pionie lini w kratce
		int[] d0,d;
		
		g.setColor(Col[0]);
	//	dataPath = "0,0->";
		
		if (path != null){
			d0 = warehouse.getDimension(path.path.get(0));
			d = warehouse.getDimension(path.path.get(0));
			for (int i=1; i<path.path.size();i++){
				d0 = warehouse.getDimension(path.path.get(i-1));
				d = warehouse.getDimension(path.path.get(i));
//				dataPath += d[0] + "," + d[1] + "->";
				
				if (path.path.get(i-1) == path.path.get(i)){
					pv0=2*a/3;
					g.drawLine((3*d0[0]+1)*a+a+pv, d0[1]*a+2*a-ph, (3*d0[0]+1)*a+a+pv0, d0[1]*a+2*a-ph);
					pv=2*a/3;
				}
				if (d[0] < d0[0]){
					ph=2*a/3;ph0=2*a/3;
				}
				        
				g.drawLine((3*d0[0]+1)*a+a+pv0, d0[1]*a+2*a-ph0, (3*d[0]+1)*a+a+pv, d[1]*a+2*a-ph);
				
				if (d[1]==0 || d[1]==warehouse.getH()-1) 
					pv=a/3;
			
				if (d0[1]==0 || d0[1]==warehouse.getH()-1) 
					pv0=a/3;
				
				/**
				 * d0 i d to wymiary, d oraz jego poprzednik. 
				 * Jesli jedena czesc wymiarowa bedzie mniejsza od poprzedniej znaczy ze sie wraca
				 * Nalezy zrobic warunek przy rysowaniu.
				 */
			}
			
			char [] pathL = formatter.format(path.getL()/100.0).toCharArray();
			char [] pathTime = formatter.format(path.getTime()).toCharArray();
			char [] take = Integer.toString(wParamenters.getP()).toCharArray();
			String mTime = Integer.toString(((int)(path.getTime()*60))/60);
			String sTime = Integer.toString(((int)(path.getTime()*60))%60);
			
			g.drawChars("czas".toCharArray(), 0, "czas".length(), 
					g.getClipBounds().width - 150, g.getClipBounds().height - 80);
			g.drawChars(pathTime, 0, pathTime.length, 
					g.getClipBounds().width - 100, g.getClipBounds().height - 80);
			g.drawChars("[min]".toCharArray(), 0, "[min]".length(), 
					g.getClipBounds().width - 35, g.getClipBounds().height - 80);
			
			g.drawString(mTime + " min", g.getClipBounds().width - 100, g.getClipBounds().height - 65);
			g.drawString(sTime + " s", g.getClipBounds().width - 40, g.getClipBounds().height - 65);
			
			g.drawChars("droga".toCharArray(), 0, "droga".length(), 
					g.getClipBounds().width - 150, g.getClipBounds().height - 30);
			g.drawChars(pathL, 0, pathL.length, 
					g.getClipBounds().width - 100, g.getClipBounds().height - 30);
			g.drawChars("[m]".toCharArray(), 0, "[m]".length(), 
					g.getClipBounds().width - 35, g.getClipBounds().height - 30);
			
			g.drawChars("pobr.".toCharArray(), 0, "pobr.".length(), 
					g.getClipBounds().width - 150, g.getClipBounds().height - 10);
			g.drawChars(take, 0, take.length, 
					g.getClipBounds().width - 100, g.getClipBounds().height - 10);
			
		}
//		g.drawChars(dataPath.toCharArray(), 0, dataPath.length(), 15, g.getClipBounds().height - 35);
	}
	
	/**
	 * @param warehouse the warehouse to set
	 */
	public void setWarehouse(WarehousePath warehouse) {
		path = null;
		this.warehouse = warehouse.getWarehouse();
		wParamenters = warehouse;
		
		
	}

	/**
	 * @param path the path to set
	 */
	public boolean setPath(Path path) {
		if (path.warehouse != warehouse)
			return false;
		this.path = path;
		return true;
	}
	
	/**
	 * @return the a
	 */
	public int getA() {
		return a;
	}

	/**
	 * @param a the a to set
	 */
	public void setA(int a) {
		this.a = a;
		this.repaint();
	}

	/**
	 * @return the xc
	 */
	public int getXc() {
		return xc;
	}

	/**
	 * @param xc the xc to set
	 */
	public void setXc(int xc) {
		this.xc = xc;
	}

	/**
	 * @return the yc
	 */
	public int getYc() {
		return yc;
	}

	/**
	 * @param yc the yc to set
	 */
	public void setYc(int yc) {
		this.yc = yc;
	}
	
	int chxToi(int x){   // x-->i
		return (x/a-1)/3;
	}
	
	int rexToi(int x){
		return (x/a-1)%3;
	}
	
	int chyToj(int y){   // y-->j
		return  y/a-1;
	}
		
	
	public void propertyChange(PropertyChangeEvent arg0) {
		this.repaint();
	}
}
