package lab2;

import java.util.Random;

public class MAR {
	public int load(int x) {
		Random rand = new Random();

		// Generate random integers in range 0 to 999
		int address = rand.nextInt(1000);
		return address;
	}
}
