package warehouse;

/**
 * 
 */
public class Kom{
	byte k;

	public Kom() {
		super();
		k = 0;
	}
	
	public Kom(byte k) {
		super();
		if (k<4 && k>-2)
			this.k = k;
	}

	/**
	 * @return the k
	 */
	public byte getK() {
		return k;
	}

	/**
	 * @param k the k to set
	 */
	public void setK(byte k) {
		if (k>=0 && k<=3)
			this.k = k;
	}
}