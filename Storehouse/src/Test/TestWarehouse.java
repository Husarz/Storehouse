package Test;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import warehouse.WarehousePath;

public class TestWarehouse implements Testy{
	WarehousePath wp;
	short pL=L,pH=H;
	int pP=p;
	int pW=w;
	
	int pN=n;
	
	NumberFormat formatter = new DecimalFormat("#0.0000");
	
	TestWarehouse(){
		super();
		wp = new WarehousePath(pL,(short) (pH+2));
	}
	
	public TestWarehouse(short l, short h, int w, int p, int n) {
		super();
		pL = l;
		pH = h;
		this.pP = p;
		this.pN = n;
		this.pW = w;
		
		wp = new WarehousePath(pL,(short) (pH+2));
	}

	public void generate(){
		System.out.print("sShape:\t" + "Return:\t" + "MidPoint:\t"+ "\n");
		for (int i=0; i<pN ; i++){
			wp.setSt(pW);			// ustawia przy ilu komorkach ma sie zatrzymywac w magazynie
			wp.setP(pP);			// ustawia pobrania
			wp.randKom(pP);			// wypelnia losowo przystanki dla wozka. p-przystankow
			
			System.out.print( formatter.format(wp.getShape().getTime()) );
			System.out.print("\t");
			System.out.print( formatter.format(wp.getReturnPath().getTime()) );
			System.out.print("\t");
			System.out.print( formatter.format(wp.getMidPoint().getTime()) );
			System.out.print("\n");
			
			wp.cleanKoms();		// czysci komorki i zeruje pobrania.
		}
	}
	
}