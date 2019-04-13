package test;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.TreeSet;

public class March1 {

	public static void main(String[] args) throws Exception {
		Reader r = new Reader();
		int n = r.nextInt();
		Segment[] segments = new Segment[n + 1];
		Point[] points = new Point[2 * n];
		int j = 0;
		System.gc();
		for (int i = 0; i < n; i++) {

			int x = r.nextInt();
			int y = r.nextInt();
			int z = r.nextInt();
			int w = r.nextInt();

			Point left = new Point(x, y, true, i + 1);
			Point right = new Point(z, w, false, i + 1);
			if (x > z) {
				Point temp = left;
				left = right;
				right = temp;
				left.left = true;
				right.left = false;
			}
			points[j++] = left;
			points[j++] = right;
			segments[i + 1] = new Segment(left, right, i + 1);
		}
		Arrays.sort(points);
		TreeSet<Point> ts = new TreeSet<>(new Comparator<Point>() {
			public int compare(Point p1, Point p2) {
				return p1.y - p2.y;
			}
		});
		Dst dst = new Dst(n);
		for (int i = 0; i < points.length; i++) {
			Point p = points[i];
			if (p.left) {
				ts.add(p);
				Segment s1 = segments[p.ls];
				Point pred = ts.lower(p);
				Point succ = ts.higher(p);
				if (pred != null) {
					Segment spred = segments[pred.ls];
					if (doIntersect(s1.left, s1.right, spred.left, spred.right)) {
						dst.union(p.ls, pred.ls);
					}
				}
				if (succ != null) {
					Segment ssucc = segments[pred.ls];
					if (doIntersect(s1.left, s1.right, ssucc.left, ssucc.right)) {
						dst.union(p.ls, succ.ls);
					}
				}
			} else {
				Segment s1 = segments[p.ls];
				Point l = s1.left;
				Point pred = ts.lower(p);
				Point succ = ts.higher(p);
				if (pred != null && succ != null && pred!=succ) {
					Segment spred = segments[pred.ls];
					Segment ssucc = segments[succ.ls];
					if (doIntersect(spred.left, spred.right, ssucc.left, ssucc.right)) {
						dst.union(spred.lineno, ssucc.lineno);
					}
				}
				ts.remove(l);
			}
		}
		HashSet<Integer> hs = new HashSet<>();
		System.out.println(Arrays.toString(dst.parent));
		for (int i = 1; i <= n; i++) {
			hs.add(dst.parent[i]);
		}
		System.out.println(hs.size());

	}

	public static boolean onSegment(Point p, Point q, Point r) {
		if (q.x <= Math.max(p.x, r.x) && q.x >= Math.min(p.x, r.x) && q.y <= Math.max(p.y, r.y)
				&& q.y >= Math.min(p.y, r.y))
			return true;

		return false;
	}

	public static int orientation(Point p, Point q, Point r) {
		// See https://www.geeksforgeeks.org/orientation-3-ordered-points/
		// for details of below formula.
		int val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);

		if (val == 0)
			return 0; // colinear

		return (val > 0) ? 1 : 2; // clock or counterclock wise
	}

	public static boolean doIntersect(Point p1, Point q1, Point p2, Point q2) {
		// Find the four orientations needed for general and
		// special cases
		int o1 = orientation(p1, q1, p2);
		int o2 = orientation(p1, q1, q2);
		int o3 = orientation(p2, q2, p1);
		int o4 = orientation(p2, q2, q1);

		// General case
		if (o1 != o2 && o3 != o4)
			return true;

		
		// Special Cases
		// p1, q1 and p2 are colinear and p2 lies on segment p1q1
		if (o1 == 0 && onSegment(p1, p2, q1))
			return true;

		// p1, q1 and q2 are colinear and q2 lies on segment p1q1
		if (o2 == 0 && onSegment(p1, q2, q1))
			return true;

		// p2, q2 and p1 are colinear and p1 lies on segment p2q2
		if (o3 == 0 && onSegment(p2, p1, q2))
			return true;

		// p2, q2 and q1 are colinear and q1 lies on segment p2q2
		if (o4 == 0 && onSegment(p2, q1, q2))
			return true;
		
		return false; // Doesn't fall in any of the above cases
	}

	static class Dst {
		int[] parent;
		int[] rank;

		public Dst(int n) {
			parent = new int[n + 1];
			rank = new int[n + 1];
			for (int i = 0; i <= n; i++) {
				parent[i] = i;
			}
		}

		public int find(int i) {
			if (i != parent[i])
				return find(parent[i]);
			return i;
		}

		public void union(int i, int j) {
			int pi = find(i);
			int pj = find(j);
			if (pi == pj)
				return;
			if (rank[pi] < rank[pj]) {
				parent[pi] = pj;
				rank[pj] = rank[pi] + 1;
			} else {
				parent[pj] = pi;
				rank[pi] = rank[pj] + 1;
			}
		}
	}

	static class Segment {
		Point left;
		Point right;
		int lineno;

		public Segment(Point left, Point right, int lno) {
			this.left = left;
			this.right = right;
			this.lineno = lno;
		}
	}

	static class Point implements Comparable<Point> {
		int x;
		int y;
		boolean left;
		int ls;

		public Point(int x, int y, boolean left, int ls) {
			this.x = x;
			this.y = y;
			this.left = left;
			this.ls = ls;
		}

		@Override
		public int compareTo(Point p) {
			if (this.x != p.x)
				return this.x - p.x;
			return this.y - p.y;
		}
	}

}

class Reader {
	final private int BUFFER_SIZE = 1 << 16;
	private DataInputStream din;
	private byte[] buffer;
	private int bufferPointer, bytesRead;

	public Reader() {
		din = new DataInputStream(System.in);
		buffer = new byte[BUFFER_SIZE];
		bufferPointer = bytesRead = 0;
	}

	public Reader(String file_name) throws IOException {
		din = new DataInputStream(new FileInputStream(file_name));
		buffer = new byte[BUFFER_SIZE];
		bufferPointer = bytesRead = 0;
	}

	public String readLine() throws IOException {
		byte[] buf = new byte[64]; // line length
		int cnt = 0, c;
		while ((c = read()) != -1) {
			if (c == '\n')
				break;
			buf[cnt++] = (byte) c;
		}
		return new String(buf, 0, cnt);
	}

	public int nextInt() throws IOException {
		int ret = 0;
		byte c = read();
		while (c <= ' ')
			c = read();
		boolean neg = (c == '-');
		if (neg)
			c = read();
		do {
			ret = ret * 10 + c - '0';
		} while ((c = read()) >= '0' && c <= '9');

		if (neg)
			return -ret;
		return ret;
	}

	public long nextLong() throws IOException {
		long ret = 0;
		byte c = read();
		while (c <= ' ')
			c = read();
		boolean neg = (c == '-');
		if (neg)
			c = read();
		do {
			ret = ret * 10 + c - '0';
		} while ((c = read()) >= '0' && c <= '9');
		if (neg)
			return -ret;
		return ret;
	}

	public double nextDouble() throws IOException {
		double ret = 0, div = 1;
		byte c = read();
		while (c <= ' ')
			c = read();
		boolean neg = (c == '-');
		if (neg)
			c = read();

		do {
			ret = ret * 10 + c - '0';
		} while ((c = read()) >= '0' && c <= '9');

		if (c == '.') {
			while ((c = read()) >= '0' && c <= '9') {
				ret += (c - '0') / (div *= 10);
			}
		}

		if (neg)
			return -ret;
		return ret;
	}

	private void fillBuffer() throws IOException {
		bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
		if (bytesRead == -1)
			buffer[0] = -1;
	}

	private byte read() throws IOException {
		if (bufferPointer == bytesRead)
			fillBuffer();
		return buffer[bufferPointer++];
	}

	public void close() throws IOException {
		if (din == null)
			return;
		din.close();
	}
}
