package warehouse;

/**
 * Właściwości i wymiary 
 *
 */
public interface WParameters {

	/**
	 * szerokość miejsca paletowego (oś X) = 130 [cm]
	 */
	int Lm = 130;
	
	/**
	 * długość mp (o Y) = 90 [cm]
	 */
	int Hm = 90;
	
	/**
	 * Lk - szerokość korytarza roboczego = 280 [cm]
	 */
	int 	Lk = 280;
	
	/**
	 * tc4 - czas podjęcia pustej palety = 0,03 [min]
	 */
	double tc4 = 0.03;
	
	/**
	 * tc6 - czas odstawienia jednostki skompletowanej 0,04 [min]
	 */
	double tc6 = 0.04;
	
	/**
	 * tpro - czas odczytania zlecenia = 0,25 [min]
	 */
	double tpro = 0.25;
	
	/**
	 * tro - czas odczytania i potwierdzenia zlecenia 0,15 [min]
	 */
	double tro = 0.15;
	
	/**
	 * A - przyspieszenia lub zatrzymania 0,029 [min]
	 */
	double A = 0.029;
	
	/**
	 * tpl - czas odbierania 1-go kartonu 0,1872 min 
	 */
	double tpl = 0.1872;
	
	/**
	 *  F1 - średni czas jazdy wózka  1m = 0,0133 [min/m]
	 */
	double 	F1 = 0.0133;
	
	/**
	 * Wylicza czas dla siezki path
	 * @param path - sciezka 
	 * @return zwraca czas
	 */
	double timePath(Path path);
	
	/**
	 * @return zwraca ilosc pobran
	 */
	int getP();
	
	
	/**
	 * @return zwraca szerokość korytarza roboczego
	 */
	int getLk();
	
	/**
	 * @return  zwraca średni czas jazdy wózka 
	 * do przodu lub do tyłu na odległość 1m = 0,0065 [min/m]
	 */
	double getF1();

}
