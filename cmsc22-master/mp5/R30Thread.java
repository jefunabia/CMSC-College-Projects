package mp5;

public class R30Thread extends Thread {
	private int min;
	private int max;
	private String str;
	private String res;

	public R30Thread(int min, int max, String str) {

		if (min > max || min < 0 || max < 0) {
			throw new IllegalArgumentException("Bad arguments");
		}

		this.min = min;
		this.max = max;
		this.str = str;
	}
	
	public void start() {
		for (int i = min; i <= max; i++) {
			res += SolveR30(i);
		}
	}

	public char SolveR30(int x) {
		char ans = '0';
		if (x == 0) {
			if (str.charAt(x) == '0' && str.charAt(x + 1) == '1') {
				ans = '1';
			}
			if (str.charAt(x) == '1' && str.charAt(x + 1) == '0') {
				ans = '1';
			}
			if (str.charAt(x) == '1' && str.charAt(x + 1) == '1') {
				ans = '1';
			}
		} else if (x == str.length() - 1) {
			if (str.charAt(x - 1) == '1' && str.charAt(x) == '0') {
				ans = '1';
			}
			if (str.charAt(x - 1) == '0' && str.charAt(x) == '1') {
				ans = '1';
			}
		} else {
			if (str.charAt(x - 1) == '1' && str.charAt(x) == '0' && str.charAt(x + 1) == '0') {
				ans = '1';
			}
			if (str.charAt(x - 1) == '0' && str.charAt(x) == '1' && str.charAt(x + 1) == '1') {
				ans = '1';
			}
			if (str.charAt(x - 1) == '0' && str.charAt(x) == '1' && str.charAt(x + 1) == '0') {
				ans = '1';
			}
			if (str.charAt(x - 1) == '0' && str.charAt(x) == '0' && str.charAt(x + 1) == '1') {
				ans = '1';
			}
		}
		return ans;
	}

	public String getRes() {
		return res;
	}

}
