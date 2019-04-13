package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail

public class TestMAC {
	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int x = 0; x < t; x++) {
			long n = Long.parseLong(br.readLine());
			long s = 0;
			if (n % 2 == 0)
				s = (n / 2) * (n + 1);
			else
				s = (n) * ((n + 1) / 2);
			if (n == 1) {
				System.out.println("YES");
			} else {
				boolean flag = getFact(s, n);
				if (flag)
					System.out.println("YES");
				else
					System.out.println("NO");
			}
		}

	}

	static boolean getFact(long s, long n) {
		long i = (long) Math.sqrt(s);
		for (; i >= 2; i--) {
			if (s % i == 0) {
				if ((s / i) >= 1 && (s / i) <= n)
					return true;
			}
		}
		return false;
	}
}
