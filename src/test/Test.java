package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.RecursiveAction;

public class Test {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		RecFinder[] rc = new RecFinder[t];
		for (int k = 0; k < t; k++) {
			int n = Integer.parseInt(br.readLine());
			int[][] x = new int[n][];
			for (int i = 0; i < n; i++) {
				String[] strs = br.readLine().split(" ");
				x[i] = new int[2];
				x[i][0] = Integer.parseInt(strs[1].replaceAll(":", ""));
				x[i][1] = Integer.parseInt(strs[2].replaceAll(":", ""));
			}
			rc[k] = new RecFinder(x);
		}
		for (int k = 0; k < t - 1; k++) {
			rc[k].fork();
		}
		rc[t - 1].compute();
		for (int i = 0; i < t - 1; i++) {
			rc[i].join();
			System.out.println(rc[i].getCount());
		}
		System.out.println(rc[t - 1].getCount());
	}

	static class RecFinder extends RecursiveAction {
		private static final long serialVersionUID = 1L;
		final int[][] x;
		final int n;
		public int count;

		RecFinder(int[][] x) {
			this.x = x;
			this.n = x.length;
		}

		public void compute() {
			Arrays.sort(x, new Comparator<int[]>() {
				public int compare(int[] a, int[] b) {
					if (a[1] != b[1])
						return a[1] - b[1];
					return a[0] - b[0];
				}
			});
			int k = 0;
			count = 1;
			for (int i = 1; i < n; i++) {
				if (x[k][1] <= x[i][0]) {
					k = i;
					count += 1;
				}
			}
		}

		public int getCount() {
			return count;
		}
	}

}