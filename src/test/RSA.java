package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class RSA {

	public static void main(String[] args) throws java.lang.Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long p = Integer.parseInt(br.readLine());
		long q = Integer.parseInt(br.readLine());
		long n = p * q;
		long x = (p - 1) * (q - 1);
		long e = 0;
		for (long i = 2; i <= x; i++) {
			if (gcd(x, i) == 1) {
				e = i;
				break;
			}
		}

		long[] x1 = inverse(x, e);
		long d = x1[1] < 0 ? x1[1] + x : x1[1];
		System.out.println(e + " " + d + " " + x + " " + n);
		BigInteger m = new BigInteger("10");
		BigInteger m1 = m.pow((int) e).mod(new BigInteger("" + n));
		System.out.println("m1:" + m1);
		System.out.println("m:" + m1.pow((int) d).mod(new BigInteger("" + n)));
	}

	public static long gcd(long a, long b) {
		if (b == 0)
			return a;
		return gcd(b, a % b);
	}

	public static long[] inverse(long a, long b) {
		if (b == 0) {
			return new long[] { 1, 0, a };
		} else {
			long[] x = inverse(b, a % b);
			long al = x[1];
			long be = x[0] - (a / b) * x[1];
			return new long[] { al, be, x[2] };
		}
	}
}