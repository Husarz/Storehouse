package warehouse;

import java.util.ArrayList;

public class Return extends Path{

	int i=0,j=0;
	
	public Return(Warehouse warehouse) {
		super(warehouse);
		this.path = new ArrayList<Kom>();
	}
	
	void generate(int k, int l) {
		if (k<i || i==warehouse.getL()-1){
			for (;j>0;j--) path.add(warehouse.kom[i][j]); 
			for (;i>=0;i--) path.add(warehouse.kom[i][j]);
			return;
		}
		for (;i<k;i++) {
				this.path.add(this.warehouse.kom[i][j]);
		}
		for (;j<=l;j++) {
			this.path.add(this.warehouse.kom[i][j]);
			if (j==l) break;
		}
		for (;j>0;j--) this.path.add(this.warehouse.kom[i][j]);			
	}
	
	void searchPath(){
		short i=0,j=0;
		for (;i<this.warehouse.getL();i++){
			for (j=(short) (this.warehouse.H-2);j>0;j--)
				if (this.warehouse.kom[i][j].getK()>0){
					this.generate(i,j);
					break;
				}
		}
		this.generate(0,0);
	}


	/* (non-Javadoc)
	 * @see warehouse.Path#getTime()
	 */
	@Override
	public int getSize() {
		return super.getSize();
	}

	@Override
	public void setPath() {
		this.path.clear();
		this.searchPath();
	}
}