package test;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class FpTech1 {

	public static void main(String[] args) throws Exception {
		Reader r = new Reader();
		int t = r.nextInt();
		for (int t1 = 0; t1 < t; t1++) {
			int n = r.nextInt();
			if (n == 1) {
				r.nextInt();
				r.nextInt();
				System.out.println("0 1");
			} else {
				int minr = Integer.MIN_VALUE;
				int maxc = Integer.MIN_VALUE;
				ArrayList<Point> al = new ArrayList<>();
				for (int i = 0; i < n; i++) {

					int x = r.nextInt();
					int y = r.nextInt();
					minr = Math.max(minr, x);
					maxc = Math.max(maxc, y);
					al.add(new Point(x, y));
				}
				Collections.sort(al);
				int maxd = Integer.MAX_VALUE;
				HashMap<Integer, Integer> map = new HashMap<>();
				for (int i = 0; i <= minr; i++) {
					for (int j = 0; j <= maxc; j++) {
						int d = getDist(i, j, al);
						int val = 1;
						if (map.get(d) != null) {
							val = map.get(d) + 1;
						}
						map.put(d, val);
						maxd = Math.min(maxd, d);
					}
				}
				System.out.println(maxd + " " + map.get(maxd));
			}

		}
	}

	static int getDist(int r, int c, ArrayList<Point> al) {
		int d = 0;
		for (Point p : al) {
			d = d + Math.abs(p.r - r) + Math.abs(p.c - c);
		}
		return d;
	}

	static class Point implements Comparable<Point> {
		int r;
		int c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}

		public int compareTo(Point p) {
			if (p.r != this.r)
				return this.r - p.r;
			return this.c - p.c;
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

}