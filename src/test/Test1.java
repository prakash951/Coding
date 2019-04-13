package test;

import java.io.*;
import java.util.*;

class Test1 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		while (t-- > 0) {
			int n = Integer.parseInt(br.readLine());
			int[][] x = new int[n][];
			for (int i = 0; i < n; i++) {
				String[] strs = br.readLine().split(" ");
				x[i] = new int[2];
				x[i][0] = Integer.parseInt(strs[1].replaceAll(":", ""));
				x[i][1] = Integer.parseInt(strs[2].replaceAll(":", ""));
			}
			Arrays.sort(x, new Comparator<int[]>() {
				public int compare(int[] a, int[] b) {
					if (a[1] != b[1])
						return a[1] - b[1];
					return a[0] - b[0];
				}
			});
			int k = 0;
			int count = 1;
			for (int i = 1; i < n; i++) {
				if (x[k][1] <= x[i][0]) {
					k = i;
					count += 1;
				}
			}
			System.out.println(count);
		}
	}

}