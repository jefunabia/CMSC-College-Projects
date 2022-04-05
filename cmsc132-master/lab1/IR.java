package lab1;

// R0, R1 and R2 are in the IR
public class IR {
	private int R0; // destination register
	private int R1; // source register
	private int R2; // source register
	
	public IR() {
		R0 = 0;
		R1 = 0;
		R2 = 0;
	}

	public int getR0() {
		return R0;
	}

	public void setR0(int r0) {
		R0 = r0;
	}

	public int getR1() {
		return R1;
	}

	public void setR1(int r1) {
		R1 = r1;
	}

	public int getR2() {
		return R2;
	}

	public void setR2(int r2) {
		R2 = r2;
	}
}
