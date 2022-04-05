package lab5;
// Constraints: Inputs must be in pair

public class lab5 {
	public static void main(String[] args) {
		System.out.println("=== LAB 5 ===");
		int[] inputs = { 1, 2, 3 , 4};

		// print current inputs
		System.out.println("Inputs are:");
		for (int i = 0; i < inputs.length; i++) {
			System.out.print(inputs[i] + " ");
		}
		System.out.println("\n");

// SISD (only add instruction)
		int evenCount = 0;
		int oddCount = 1;
		int timeCount = 2;
		System.out.println("=== SISD - Single Instruction (ADD) Single Data ===");

		// print TIME 1 inputs
		System.out.println("[TIME 1]");
		int sum = inputs[evenCount] + inputs[oddCount];
		System.out.println(inputs[evenCount] + " + " + inputs[oddCount] + " = " + sum);
		evenCount += 2;
		oddCount += 2;

		// loop for other inputs
		for (int i = 0; i <= inputs.length; i++) {
			// if oddCount has the value of the last index, break
			if (oddCount >= inputs.length) {
				break;
			} else {
				System.out.println("[TIME " + timeCount + "]");
				sum = inputs[evenCount] + inputs[oddCount];
				System.out.println(inputs[evenCount] + " + " + inputs[oddCount] + " = " + sum);
				evenCount += 2;
				oddCount += 2;
				timeCount++;
			}
		}

// SIMD (only add instruction)
		evenCount = 0;
		oddCount = 1;
		timeCount = 1;
		System.out.println("\n=== SIMD - Single Instruction (ADD) Multiple Data ===");
		System.out.println("[TIME " + timeCount + "]");
		// loop for other inputs
		for (int i = 0; i <= inputs.length; i++) {
			if (oddCount >= inputs.length) {
				break;
			} else {
				sum = inputs[evenCount] + inputs[oddCount];
				System.out.println(inputs[evenCount] + " + " + inputs[oddCount] + " = " + sum);
				evenCount += 2;
				oddCount += 2;
				timeCount++;
			}
		}

// MISD (add, sub, mul)
		System.out.println("\n=== MISD - Multiple Instruction Single Data ===");

		evenCount = 0;
		oddCount = 1;
		timeCount = 2;

		// print first TIME 1 stuff
		System.out.println("[TIME 1]");
		sum = inputs[evenCount] + inputs[oddCount];
		int diff = inputs[evenCount] - inputs[oddCount];
		int prod = inputs[evenCount] * inputs[oddCount];
		System.out.println(inputs[evenCount] + " + " + inputs[oddCount] + " = " + sum);
		System.out.println(inputs[evenCount] + " - " + inputs[oddCount] + " = " + diff);
		System.out.println(inputs[evenCount] + " * " + inputs[oddCount] + " = " + prod);
		evenCount += 2;
		oddCount += 2;

		// loop for other inputs
		for (int i = 0; i <= inputs.length; i++) {
			if (oddCount >= inputs.length) {
				break;
			} else {
				System.out.println("[TIME " + timeCount + "]");
				sum = inputs[evenCount] + inputs[oddCount];
				diff = inputs[evenCount] - inputs[oddCount];
				prod = inputs[evenCount] * inputs[oddCount];
				System.out.println(inputs[evenCount] + " + " + inputs[oddCount] + " = " + sum);
				System.out.println(inputs[evenCount] + " - " + inputs[oddCount] + " = " + diff);
				System.out.println(inputs[evenCount] + " * " + inputs[oddCount] + " = " + prod);
				evenCount += 2;
				oddCount += 2;
				timeCount++;
			}
		}

// MIMD
		System.out.println("\n=== MIMD - Multiple Instruction Multiple Data===");
		evenCount = 0;
		oddCount = 1;
		timeCount = 2;

		// print first TIME 1 stuff
		System.out.println("[TIME 1]");
		sum = inputs[evenCount] + inputs[oddCount];
		diff = inputs[evenCount] - inputs[oddCount];
		prod = inputs[evenCount] * inputs[oddCount];
		System.out.println(inputs[evenCount] + " + " + inputs[oddCount] + " = " + sum);
		System.out.println(inputs[evenCount] + " - " + inputs[oddCount] + " = " + diff);
		System.out.println(inputs[evenCount] + " * " + inputs[oddCount] + " = " + prod);
		evenCount += 2;
		oddCount += 2;

		// loop for other inputs
		for (int i = 0; i <= inputs.length; i++) {
			if (oddCount >= inputs.length) {
				break;
			} else {
				System.out.println("--------");
				sum = inputs[evenCount] + inputs[oddCount];
				diff = inputs[evenCount] - inputs[oddCount];
				prod = inputs[evenCount] * inputs[oddCount];
				System.out.println(inputs[evenCount] + " + " + inputs[oddCount] + " = " + sum);
				System.out.println(inputs[evenCount] + " - " + inputs[oddCount] + " = " + diff);
				System.out.println(inputs[evenCount] + " * " + inputs[oddCount] + " = " + prod);
				evenCount += 2;
				oddCount += 2;
			}
		}

	}
}
