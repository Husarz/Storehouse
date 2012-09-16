package warehouse;



public class WarehousePath implements WParameters{
	/**
	 * 
	 */
	final Warehouse warehouse;
	
	int 	st = 0;			// iloc przystankow przy ktorych nalezy sie zatrzymac. 
							// w klasie Warehouse to wypelnione komorki dla k > 0
	int 	p = 0; 			// zmienna pomocnicza

	/**
	 * zminna pLk szerokosc korytarza roboczego 
	 * (domyslnie rowna stalej Lk)
	 */
	int 	pLk = Lk;
	
	double 	pF1 = F1;

	
	Path returnPath;
	Path midPoint;
	Path shape;


	public WarehousePath(Warehouse warehouse) {
		super();
		this.warehouse = warehouse;
	}

	public WarehousePath(short l, short h) {
		super();
		warehouse = new Warehouse(l,h);
	}
	
	public void randKom(int n){
		new RandKom(warehouse).rand(n);
		st=n;
	}
	public void randKom(){
		new RandKom(warehouse).rand(st);
	}
	
	
	public double getF1() {
		return pF1;
	}

	public void setF1(double f1) {
		pF1 = f1;
	}
	
	public void setF2(double f2) {
		pF1 = 0.06/f2;
	}
	
	public double getF2() {
		return 0.06/pF1;
	}

	public int getLk() {
		return pLk;
	}
	public void setLk(int lk) {
		pLk = lk;
	}	
	
	/**
	 * @return zwraca ilosc pobran
	 */
	public int getP() {
		return p+st;
	}

	/**
	 * @return ustawia ilosc pobran.
	 */
	public void setP(int t) {
		this.p = p+t;
		
	}

	/**
	 * 
	 * @see warehouse.Warehouse#cleanKoms()
	 */
	public void cleanKoms() {
		st=0; p=0;
		warehouse.cleanKoms();
	}

	/**
	 * @return the warehouse
	 */
	public Warehouse getWarehouse() {
		return warehouse;
	}
	
	/**
	 * @return the returnPath
	 */
	public Path getReturnPath() {
		returnPath = new Return(warehouse); 
		returnPath.setPath(); 
		returnPath.timePath(this);
		return returnPath;
	}

	/**
	 * @return the midPoint
	 */
	public Path getMidPoint() {
		midPoint = new MidPoint(warehouse);
		midPoint.setPath();
		midPoint.timePath(this);
		return midPoint;
	}

	/**
	 * @return the sSnape
	 */
	public Path getShape() {
		shape = new Shape(warehouse);
		shape.setPath();
		shape.timePath(this);
		return shape;
	}

	
	/**
	 * @return the p
	 */
	public int getSt() {
		return st;
	}

	/**
	 * @param w the w to set
	 */
	public void setSt(int w) {
		if (w < 2*this.getWarehouse().getL()*this.getWarehouse().getH()-2) {
			this.st = w;
		}
		else {
			this.st = 0;
		}
	}
	
	/**
	 * @param i
	 * @param j
	 * @param k
	 * @see warehouse.Warehouse#setKom(short, short, byte)
	 */
	public void setKom(short i, short j, byte k) {
		if (i<0 || i>warehouse.getL()-1 || j>=warehouse.getH()-1 || j<1) return;
	//	if ( j==warehouse.getH()-1 || j==0) return;
		byte k0 = warehouse.getKom(i, j);
		if (k==1 || k==2)
			if (k0==3 || k==k0){
				warehouse.setKom(i, j, (byte) (k0-k));
				st--;
			}
			else{ 
				warehouse.setKom(i, j, (byte) (k0+k));
				st++;
			}
	}

	@Override
	public double timePath(Path path) {
		
		double time = 0.0, dL = 0.0, p = 0.0;
//		int h=0, l=0, z=0; 					// ilosc poziomych, ilosc pionowych, ilosc zakretow
		int L=0, w=0;
		if (path.path.size()==0) return 0;
		
		Kom kom1 = path.path.get(0);
		Kom kom2;
		
		if(path.getSize()<2) return 0.0;
///////////////////////////////////////////////////////		
		L+=pLk/2;     // poczatek drogi dojazd do pkt srodkowego
		for (int i=1; i<path.path.size(); i++){
			
			kom2 = path.path.get(i);
			
			if (kom1==kom2) 
				continue;  // przy powrotach z tej samej strefy odbiorczej - 
									   //nie dolicza komorki (zaczyna cofac)
			
			if (kom1.getK() == -1 && kom2.getK() == -1) L+=pLk+2*Lm;
			// Dodaje pLk 2*Lm  gdy porusza sie w korytarzach poziomych (z jednej komowkri poziomej do drugiej)
			
			if (kom1.getK() != -1 && kom2.getK() != -1) L+=Hm;
			// Dodaje Hm gdy porusza sie tylko w korytarzach pionowych 
			
			if (kom1.getK() == -1 && kom2.getK() != -1) L+=pLk/2+Hm/2;
			if (kom1.getK() != -1 && kom2.getK() == -1) L+=pLk/2+Hm/2;
			//Dodaje do L pLk/2 i Hm/2 gdy robi zakret
			
			kom1 = kom2;
		}
		L+=pLk/2;  // koniec dojazd do strefy odbiorczej
///////////////////////////////////////////////////////		
//		for (int i=0; i<path.path.size(); i++){
//			
//			kom2 = path.path.get(i);
//			
//			if (kom1==kom2) continue;
//			if (kom1.getK() != -1 && kom2.getK() == -1) z++;
//			if (kom1.getK() == -1 && kom2.getK() == -1) l++;
//			if (kom1.getK() != -1 && kom2.getK() != -1) h++;
//			
//			kom1 = kom2;
//		}
//		L = l*(Lm*2+pLk) + h*Hm + z*(pLk+Hm) + pLk; 	// [cm]
		dL = L/100.0;								// [m]		
		
		path.setL(L); 						// zainicjowanie dlugosci sciezki
		w = this.getSt();
		p = this.getP()/w;
		
		time = (w+1)*A + dL*pF1 + tc4 + tc6 + tpro + w*(tro+p*tpl);
		
		return time;
	}
}