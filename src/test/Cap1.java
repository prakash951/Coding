package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Cap1 {

	public static void main(String[] args) throws java.lang.Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] a = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
		int q = Integer.parseInt(br.readLine());
		Bit bit = new Bit(n);
		Bit bit1 = new Bit(n);
		Bit bit2 = new Bit(n);
		for (int i = 0; i < n; i++) {
			bit.update(i, a[i]);
		}
		for (int i = 0; i < q; i++) {
			int[] x = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
			switch (x[0]) {
			case 0:
				update(bit1, bit2, x[1] - 1, x[2] - 1, x[3]);
				break;
			case 1:
				System.out.println(bit.getRange(x[1] - 1, x[2] - 1) + getSum(bit1, bit2, x[1] - 1, x[2] - 1));
				break;
			}
		}

	}

	public static int getSum(Bit b1, Bit b2, int l, int r) {
		return getSum(b1, b2, r) - getSum(b1, b2, l - 1);
	}

	public static int getSum(Bit b1, Bit b2, int l) {
		return b1.getSum(l) * l - b2.getSum(l);
	}

	public static void update(Bit b1, Bit b2, int l, int r, int val) {
		b1.update(l, r, val);
		b2.update(l, val * (l - 1));
		b2.update(r + 1, -val * r);
	}

	static class Bit {
		int[] x;

		public Bit(int n) {
			x = new int[n + 3];
		}

		public void update(int i, int val) {
			i = i + 1;
			while (i < x.length - 1) {
				x[i] = x[i] + val;
				i += (i & (-i));
			}
		}

		public int getSum(int i) {
			int sum = 0;
			i = i + 1;
			while (i > 0) {
				sum = sum + x[i];
				i -= (i & (-i));
			}
			return sum;
		}

		public int getRange(int i, int j) {
			int x = getSum(j);
			int y = getSum(i - 1);
			return x - y;
		}

		public void update(int i, int j, int v) {
			update(i, v);
			update(j + 1, -v);

		}
	}
}
