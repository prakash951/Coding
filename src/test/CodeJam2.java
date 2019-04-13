package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class CodeJam2 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int t1 = 1; t1 <= t; t1++) {
			int n = Integer.parseInt(br.readLine());
			char[] a = br.readLine().toCharArray();
			
			int x = 0, y = 0;
			ArrayList<Point> path = new ArrayList<Point>();
			if (a[0] == 'S') {
				x = x + 1;
				path.add(new Point(x, y, a[0], null));
			} else {
				y = y + 1;
				path.add(new Point(x, y, a[0], null));
			}
			for (int i = 1; i < a.length; i++) {
				if (a[i] == 'S') {
					x = x + 1;
					path.add(new Point(x, y, a[i], path.get(i - 1)));
				} else {
					y = y + 1;
					path.add(new Point(x, y, a[i], path.get(i - 1)));
				}
			}
			Queue<Point> queue = new LinkedList<>();
			x = 0;
			y = 0;
			if (a[0] == 'S') {
				y = y + 1;
				queue.add(new Point(x, y, 'E', null));
			} else {
				x = x + 1;
				queue.add(new Point(x, y, 'S', null));
			}
			//System.out.println("Path:"+path+"\n\n\n");
			Point d = null;
			while (!queue.isEmpty()) {
				int size = queue.size();
				if(d!=null) break;
				while (size > 0) {
					
					Point p = queue.poll();
					// System.out.println(p);
					if (p.x == n - 1 && p.y == n - 1) {
						d = p;
						break;
					}

					int[] row = { 0, 1 };
					int[] col = { 1, 0 };
					for (int i = 0; i < 2; i++) {
						int r = p.x + row[i];
						int c = p.y + col[i];
						Point p1 = null;
						if (i == 0) {
							p1 = new Point(r, c, 'E', p);
						} else {
							p1 = new Point(r, c, 'S', p);
						}
						if (isSafe(r, c, path, p1, n, p)) {
							queue.add(p1);
						}
					}
					size = size - 1;
				}
			}
			StringBuffer sb = new StringBuffer();
			while (d != null) {
				//System.out.println(d);
				sb.append(d.ch);
				d = d.parent;
			}
			System.out.println("Case #"+t1+": "+sb.reverse());
		}

	}

	public static boolean isSafe(int r, int c, ArrayList<Point> path, Point p1, int n, Point parent) {
		if (r > n - 1 || c > n - 1) {
			return false;
		}
		int x = Collections.binarySearch(path, p1);
		if (x >= 0) {
			Point sp = path.get(x);
			if (sp.parent == null)
				return false;
			else if (sp.parent.x == parent.x && sp.parent.y == parent.y)
				return false;
		}
		return true;
	}

	static class Point implements Comparable<Point> {
		int x;
		int y;
		char ch;
		Point parent;

		public Point(int x, int y, char ch, Point p) {
			this.x = x;
			this.y = y;
			this.ch = ch;
			this.parent = p;
		}

		@Override
		public int compareTo(Point p) {
			if (this.x != p.x)
				return this.x - p.x;
			return this.y - p.y;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + ", ch=" + ch + ", parent=" + parent + "]\n";
		}


	}

}
