package warehouse;

import java.util.ArrayList;

public abstract class Path {

		public final Warehouse warehouse;
		//	Path path;
		public ArrayList<Kom> path = null;
		double time = 0.0;
		/**
		 * L - długosc sciezki w centymetrach
		 */
		int L = 0;
		
		/**
		 * @param warehouse
		 */
		Path(Warehouse warehouse) {
			this.warehouse = warehouse;
		}
		
		public int getSize(){
			if (path!=null)
				return path.size();
			else return 0;
		}
		
		abstract public void setPath();

		public double getTime() {
			return time;
		}

		public void timePath(WParameters param) {
			if (getSize()>1)
				this.time = param.timePath(this);
			else time = 0.0;
		}

		public int getL() {
			return L;
		}

		public void setL(int l) {
			L = l;
		}
}