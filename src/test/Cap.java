package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

/* Name of the class has to be "Main" only if the class is public. */
public class Cap {
	public static void main(String[] args) throws java.lang.Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		while (t-- > 0) {
			String s = br.readLine();
			HashSet<Integer> hs = new HashSet<Integer>();
			int n = Integer.parseInt(br.readLine().trim());
			String[] x = br.readLine().trim().split(" ");
			String[] y = br.readLine().trim().split(" ");
			Point[] p = new Point[n];
			int count = 0;
			for (int i = 0; i < n; i++) {
				p[i] = new Point(Integer.parseInt(x[i]), Integer.parseInt(y[i]));
			}
			Arrays.sort(p);
			int k1 = Arrays.binarySearch(p, new Point(0, 0));
			if (k1 >= 0) {
				hs.add(k1);
				count = 1;
			}
			Bit l = new Bit(s.length());
			Bit r = new Bit(s.length());
			Bit d = new Bit(s.length());
			Bit u = new Bit(s.length());
			for (int i = 0; i < s.length(); i++) {
				char ch = s.charAt(i);
				if (ch == 'L') {
					l.update(i, 1);
				} else if (ch == 'R') {
					r.update(i, 1);
				} else if (ch == 'D') {
					d.update(i, 1);
				} else {
					u.update(i, 1);
				}
			}

			for (int i = 0; i < s.length(); i++) {
				for (int j = i; j < s.length(); j++) {
					// System.out.println(i+" "+j);
					// System.out.print("L:"+l.getRange(i,j)+"\t");
					// System.out.print("R:"+r.getRange(i,j)+"\t");
					// System.out.print("U:"+u.getRange(i,j)+"\t");
					// System.out.print("D:"+d.getRange(i,j)+"\t\n");
					int x1 = 0;
					int y1 = 0;
					x1 = x1 - l.getRange(i, j) + r.getRange(i, j);
					y1 = y1 + u.getRange(i, j) - d.getRange(i, j);

					int s1 = Arrays.binarySearch(p, new Point(x1, y1));
					if (s1 >= 0 && !hs.contains(s1)) {
						count++;
						hs.add(s1);
						// System.out.println(x1 + "\t" + y1);
					}
				}
			}
			System.out.println(count);
		}
	}

	static class Point implements Comparable<Point> {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int hashCode() {
			return x;
		}

		public boolean equals(Object obj) {
			Point p = (Point) obj;
			return this.x == p.x && this.y == p.y;
		}

		public int compareTo(Point p) {
			if (this.x != p.x)
				return this.x - p.x;
			return this.y - p.y;
		}
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
				i += (i & -i);
			}
		}

		public int getSum(int i) {
			int sum = 0;
			i = i + 1;
			while (i > 0) {
				sum = sum + x[i];
				i -= (i & -i);
			}
			return sum;
		}

		public int getRange(int i, int j) {
			int x = getSum(j);
			int y = getSum(i - 1);
			return x - y;
		}
	}
}
