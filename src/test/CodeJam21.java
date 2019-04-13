package test;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.PriorityQueue;

public class CodeJam21 {

	public static void main(String[] args) throws Exception {
		Reader br = new Reader();
		int t = br.nextInt();
		StringBuffer res = new StringBuffer();
		for (int t1 = 1; t1 <= t; t1++) {
			map = new HashMap<Integer, Integer>();
			int n = br.nextInt();
			boolean[][] v = new boolean[n][n];
			char start = br.readLine(v);
			PriorityQueue<Point> queue = new PriorityQueue<>();
			int x = 0;
			int y = 0;
			if (start == 'S') {
				y = y + 1;
				queue.add(new Point(x, y, 'E', null, 0));
			} else {
				x = x + 1;
				queue.add(new Point(x, y, 'S', null, 0));
			}
			Point d = null;
			int count = 0;
			boolean[][] v1 = new boolean[n][n];
			while (!queue.isEmpty()) {

				Point p = queue.poll();
				count++;
				if (p.x == n - 1 && p.y == n - 1) {
					d = p;
					break;
				}
				int[] row = { 0, 1 };
				int[] col = { 1, 0 };
				for (int i = 0; i < 2; i++) {
					int r = p.x + row[i];
					int c = p.y + col[i];
					if (r <= n - 1 && c <= n - 1) {

						Point p1 = null;
						if (i == 0) {
							p1 = new Point(r, c, 'E', p, 0);
						} else {
							p1 = new Point(r, c, 'S', p, 0);
						}
						if (isSafe1(r, c, v, p1, n, p,v1)) {
							//p1.dist = 2 * (n - 1) - r - c + p.dist;
							p1.dist = Math.abs(map.get(r) - c) + p.dist;
							queue.add(p1);
							v1[r][c] = true;
						}
					}
				}

			}
			//System.out.println(count);
			StringBuffer sb = new StringBuffer();
			while (d != null) {
				sb.append(d.ch);
				d = d.parent;
			}
			res.append("Case #" + t1 + ": " + sb.reverse()).append("\n");
			
		}
		System.out.println(res);

	}

	static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

	public static boolean isSafe1(int r, int c, boolean[][] v, Point p1, int n, Point parent,boolean[][] v1) {

		boolean x = v[r][c];
		if (x) {
			if (v[parent.x][parent.y])
				return false;
		}
		if(v1[r][c]) return false;
		return true;
	}

	static class Point implements Comparable<Point> {
		int x;
		int y;
		char ch;
		Point parent;
		int dist;

		public Point(int x, int y, char ch, Point p, int dist) {
			this.x = x;
			this.y = y;
			this.ch = ch;
			this.parent = p;
			this.dist = 0;
		}

		@Override
		public int compareTo(Point p) {
			return this.dist - p.dist;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + ", ch=" + ch + ", parent=" + parent + ", dist=" + dist + "]";
		}
	}

	static class Reader {
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

		public char readLine(boolean[][] v) throws IOException {
			int x = 0, y = 0;
			int c;
			char start = ' ';
			map.put(x, y);
			while ((c = read()) != -1) {
				if (c == '\n')
					break;
				char ch = (char) c;
				if (start == ' ')
					start = ch;
				if (ch == 'S') {
					x = x + 1;
					v[x][y] = true;
				} else {
					y = y + 1;
					v[x][y] = true;
				}
				if (map.get(x) == null)
					map.put(x, y);
			}
			return start;
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

}
