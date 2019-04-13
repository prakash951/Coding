package test;

import java.util.HashMap;

public class NewPow {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

class Ideone {
	public static void main(String[] args) throws java.lang.Exception {
		int a = 1;
		int b = 23;
		int s1 = 25;
		int[] r = new int[33];
		r[0] = 7;
		for (int i = 1; i < r.length; i++) {
			r[i] = (r[i - 1] * r[i - 1]) % b;
		}
		String s = Integer.toBinaryString(s1);
		int j = s.length() - 1;
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (ch == '1') {
				a = a * r[j];
				a = a % b;
			}
			j--;
		}
		System.out.println(a);
	}
}

class Ideone1 {
	public static void main(String[] args) throws java.lang.Exception {
		map.clear();
		System.out.println(mod(7, 100, 23));
	}

	static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

	public static int mod(int a, int b, int m) {
		if (map.get(b) != null) {
			return map.get(b);
		}
		if (b == 1 || b == 0) {
			map.put(b, a % m);
			return a % m;
		}
		if (b % 2 == 1) {
			int x1 = (a * mod(a, b - 1, m)) % m;
			map.put(b, x1);
			return x1;
		}
		return (mod(a, b / 2, m) * mod(a, b / 2, m)) % m;
	}
}