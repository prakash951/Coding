package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Test6 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int i = 0; i < t; i++) {
			int n = Integer.parseInt(br.readLine());
			long[] x = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
			construct(x);
		}
	}

	public static long getMax(long[] a, int[] seg, int s, int e, int l, int r) {
		if (l > r)
			return Long.MIN_VALUE;
		if (l == r)
			return a[r];
		int m = range(a, seg, s, e, l, r, 0);
		long max = ((long) (r - l + 1) * a[m]);
		long l1 = getMax(a, seg, s, e, l, m - 1);
		long r1 = getMax(a, seg, s, e, m + 1, r);
		max = Math.max(max, Math.max(l1, r1));
		return max;
	}

	public static void construct(long[] a) {
		int n = a.length;
		int n1 = (int) Math.pow(2, (long) Math.ceil(Math.log(n) / Math.log(2)) + 1) - 1;
		int[] seg = new int[n1];
		create(seg, 0, n - 1, 0, a);

		long max = getMax(a, seg, 0, n - 1, 0, n - 1);
		System.out.println(max);

	}

	public static int create(int[] seg, int s, int e, int i, long[] a) {
		if (s == e) {
			seg[i] = s;
			return s;
		}
		int mid = s + (e - s) / 2;
		int l = create(seg, s, mid, 2 * i + 1, a);
		int r = create(seg, mid + 1, e, 2 * i + 2, a);
		if (a[l] < a[r])
			seg[i] = l;
		else
			seg[i] = r;
		return seg[i];
	}

	public static int range(long[] a, int[] seg, int s, int e, int l, int r, int i) {
		if (l <= s && e <= r)
			return seg[i];
		if (r < s || l > e)
			return -1;
		int mid = s + (e - s) / 2;
		int l1 = range(a, seg, s, mid, l, r, 2 * i + 1);
		int r1 = range(a, seg, mid + 1, e, l, r, 2 * i + 2);
		if (l1 == -1)
			return r1;
		if (r1 == -1)
			return l1;
		if (a[l1] < a[r1])
			return l1;
		return r1;
	}

}
