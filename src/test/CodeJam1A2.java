package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;

public class CodeJam1A2 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuffer sb = new StringBuffer();
		for (int t1 = 1; t1 <= t; t1++) {
			int[] a1 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int r = a1[0];
			int c = a1[1];
			Queue<Pair> queue = new LinkedList<>();
			queue.add(new Pair(0, 0));
			LinkedHashSet<Pair> hs = new LinkedHashSet<Pair>();
			boolean f = false;
			int[] row = { 1, -1, 1, -1, 1, 1, -1, -1 };
			int[] col = { 2, -2, 3, -3, -2, -3, 2, 3 };
			while (!queue.isEmpty()) {
				Pair p = queue.poll();
				if (p.x != 0 && p.y != 0)
					hs.add(p);
				if (hs.size() == r * c) {
					f = true;
					break;
				}

				// hs.add(p);

				for (int i = 0; i < row.length; i++) {
					int a = p.x + row[i];
					int b = p.y + col[i];
					if (a >= 1 && a <= r && b >= 1 && b <= c) {
						Pair p1 = new Pair(a, b);
						if (!hs.contains(p1)) {

							queue.add(p1);
						}
					}
				}
			}
			System.out.println(hs);
			if (!f) {
				System.out.println("Case #" + t1 + ": " + "IMPOSSIBLE");
			} else {
				System.out.println("Case #" + t1 + ": " + "POSSIBLE");
				for (Pair p : hs) {
					System.out.println(p);
				}
			}
		}

	}

	static class Pair {
		int x;

		int y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "" + x + " " + y;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Pair other = (Pair) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}
	}

}
