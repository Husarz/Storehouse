package warehouse;

import java.util.ArrayList;

public class Shape extends Path{

		int i=0,j=0;
		
		public Shape(Warehouse warehouse) {
			super(warehouse);
			this.path = new ArrayList<Kom>();
			
		}

		void generate(int k, int l) {
			if ( k==0 && l==0){
				for (;j>0;j--) path.add(warehouse.kom[i][j]); 
				for (;i>=0;i--) path.add(warehouse.kom[i][j]);
				return;
			}

			if(j>0) for (j++;j<=this.warehouse.H-2;j++) this.path.add(this.warehouse.kom[i][j]);
			for (;i<k;i++) {
					this.path.add(this.warehouse.kom[i][j]);
			}
			for (;j<=l;j++) {
				this.path.add(this.warehouse.kom[i][j]);
				if (j==l) return;
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