package warehouse;


public class Warehouse {
	
	final short L, H; 
	Kom [][] kom;
	short [] time;
	
public Warehouse() {
		L = 7;
		H = 8;
		kom = new Kom[L][H];
		Warehouse.gen(this);
	}
	
	public Warehouse(short l, short h) {
		super();
		if (l<2 || h<4) {
			L = 7;
			H = 8;
			kom = new Kom[L][H];
			Warehouse.gen(this);
		}
		else {
			L = l;
			H = h;
			kom = new Kom[L][H];
			Warehouse.gen(this);
		}
	}

	
	/**
	 * metoda gen(); 
	 * @param w - Warehouse
	 * metoda statyczna - alokuje w pamieci i generuje tablice komorek, nadajac im wartosc 0 (w konstruktorze Kom) 
	 */
	public static void gen(Warehouse w){
		for (int i=0, j=0;i<w.getL();i++){
			w.kom[i][0] = new Kom((byte)-1);
			for (j=1;j<w.getH()-1;j++){
				w.kom[i][j] = new Kom();
			}
			w.kom[i][j] = new Kom((byte)-1);
		}
	}

	/**
	 * @return the kom
	 */
	public byte getKom(int i, int j) {
		if (i>=0 && i<L && j<H && j>=0)
			return kom[i][j].getK();
		return 0;
	}

	/**
	 * @return the kom
	 */
	public Kom[][] getKom() {
		return kom;
	}

	/**
	 * @param kom the kom to set
	 */
	public void setKom(short i, short j, byte k) {
		
		if (i>=0 && i<=L-1 && j<H-1 && j>0)
				this.kom[i][j].setK(k);
	}
	
	public void cleanKoms(){
		for (int j=0, i=0; i<L; i++)
			for (j=1; j<H-1; j++)
				this.kom[i][j].setK((byte) 0);
	}

	/**
	 * @param path the path to set
	 */
	public void setPath() {
		new MidPoint(this).setPath();
	}

	public int[] getDimension(Kom e){
		int i=0,j;
		for (i=0;i<L;i++){
			for (j=0;j<H;j++){
				if (e==kom[i][j]){ 
					int[] d = {i,j};
					return d;
				}
			}
		}
		return null;
	}
	
	public short getL() {
		return L;
	}

	public short getH() {
		return H;
	}

}
