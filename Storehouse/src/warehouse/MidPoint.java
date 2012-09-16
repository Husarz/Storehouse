package warehouse;

import java.util.ArrayList;

public class MidPoint extends Path{

	int i0=0,j0=0;
	int h;   //wysokosc punktu srodkowego
	
	public MidPoint(Warehouse warehouse) {
		super(warehouse);
		this.path = new ArrayList<Kom>();
	}
	void generate(int k, int l) {
		
		if (k==0 && i0>0 && l==0) {
			for (;i0>=0;i0--) path.add(warehouse.kom[i0][j0]);
			return;
		}
		if (k==0 && i0==0){
			if (j0>0){
				for (j0--; j0>=0; j0--)
					path.add(warehouse.kom[i0][j0]);
				return;
			}
			for (;j0<=l;j0++) path.add(warehouse.kom[i0][j0]);
			return;
		}
		if (k>i0){
			for (; i0<k && j0==warehouse.H-1; i0++) path.add(warehouse.kom[i0][j0]);
			for (; j0>=l; j0--) path.add(warehouse.kom[i0][j0]);
			if 	(l==1) return;
			for (j0++; j0<warehouse.H-1; j0++) path.add(warehouse.kom[i0][j0]);
			return;
		}
		if (k<i0){
			for (;i0>k;i0--) path.add(warehouse.kom[i0][j0]);
			for (;j0<=l;j0++) path.add(warehouse.kom[i0][j0]);
			for (j0--;j0>=1;j0--) path.add(warehouse.kom[i0][j0]);
		}
	}
	
	void searchPath(){
		int i=0,j=0,L=0;
		 

		h = (warehouse.H-2)%2!=0?(warehouse.H-2)/2+1:(warehouse.H-2)/2;
		
		for (i=warehouse.getL()-1;i>0;i--){
			for (j=1;j<=warehouse.H-2;j++){
				if (this.warehouse.kom[i][j].getK()==0) continue;
				L = i; i=0; break;
			}
		}

		if (L==0) {
			for(j=warehouse.H-2;j>0;j--){
				if (this.warehouse.kom[i][j].getK()>0){
					this.generate(0,j);
					this.generate(0,0);
					return;
				}
			}
			this.generate(0,0);
			return;
		}
		
		this.generate(0,warehouse.H-2);

		for (i=1;i<L;i++){
			for (j=h+1;j<warehouse.H-1;j++)
				if (this.warehouse.kom[i][j].getK()>0){
					this.generate(i,j);
					break;
				}
		}
		this.generate(i,1);

		for (i--;i>0;i--){
			for (j=h;j>0;j--)
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