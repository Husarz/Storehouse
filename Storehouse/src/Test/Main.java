package Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

class IOTest {
	public static int[] read() throws IOException, NoSuchElementException {

		int[] data = new int[5];

		BufferedReader stdin = new BufferedReader(new InputStreamReader(
				System.in));

		System.out
				.print("Podaj wartosci w kolejnosci(dlugosc, wysokosc, ilosc przystankow, ilosc pobran, probka): L H w p n:");
		System.out.flush();
	
		StringTokenizer st = new StringTokenizer(stdin.readLine());

		for (int i = 0; i < 5; i++) {
			data[i] = Integer.parseInt(st.nextToken());
		}

		System.out.println("");

		return data;
	}
}

public class Main {
	/**
	 * @param args
	 */

	public static void main(String[] args) {
		TestWarehouse test;

		try {
			int[] data = IOTest.read();
			test = new TestWarehouse((short) data[0], (short) data[1], data[2],
					data[3], data[4]);
			test.generate();
		}
		catch (NoSuchElementException e) {
			test = new TestWarehouse();
			test.generate();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}

}
