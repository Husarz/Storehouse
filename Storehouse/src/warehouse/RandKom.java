package warehouse;

import java.util.ArrayList;
import java.util.Random;


public class RandKom {
	final Warehouse warehouse;
	
	ArrayList<Kom> listKom;

	public RandKom(Warehouse warehouse) {
		super();
		this.warehouse = warehouse;
		
		listKom = new ArrayList<Kom>();
		
		for (int j=0, i=0;i<this.warehouse.getL(); i++)
			for (j=1; j<this.warehouse.getH()-1; j++){
				listKom.add(this.warehouse.getKom()[i][j]);
			}
	}
	
	public void rand(int n){
		Random r = new Random();
		int k;
		for(int i=0; i<n; i++){
			k = r.nextInt(listKom.size());
			if (listKom.get(k).getK()==0) listKom.get(k).setK((byte) 1);
			else {
				listKom.get(k).setK((byte) 3);
				listKom.remove(k);
			}
		}
	}
}
