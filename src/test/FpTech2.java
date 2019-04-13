package test;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class FpTech2 {

	public static void main(String[] args) throws Exception {
		Reader r = new Reader();
		int t = r.nextInt();
		for (int t1 = 0; t1 < t; t1++) {
			String s1 = r.readLine();
			String s2 = s1;
			if (!isValid(s1)) {
				System.out.println("INVALID");
			} else {
				if (s1.equals("23:59:59")) {
					System.out.println(s1);
				} else {
					for (int i = 0; i < 100; i++) {
						String x = next(s1);
						if (isValid1(x)) {
							s1 = x;
							break;
						}
						s1 = x;
					}
					if(isValid1(s1))
					{
						int h = Integer.parseInt(s1.substring(0, 2));
						int m = Integer.parseInt(s1.substring(2, 4));
						int s = Integer.parseInt(s1.substring(4, s1.length()));
						StringBuffer sb = new StringBuffer();
						if(h<10)
							sb.append(0);
						sb.append(h+":");
						if(m<10)
							sb.append(0);
						sb.append(m+":");
						if(s<10)
							sb.append(0);
						sb.append(s);
						System.out.println(sb);
					}
					else
					{
						System.out.println(s2);
					}
				}
			}

		}
	}

	static String next(String s1) {

		s1 = s1.replaceAll(":", "");
		char[] x = s1.toCharArray();
		int j = -1;
		for (int i = x.length - 1; i > 0; i--) {
			if (x[i] > x[i - 1]) {
				j = i;
				break;
			}
		}
		// 12:31:09
		if (j == -1)
			return s1;
		int k = j - 1;
		char ch = x[k];
		int min = j;
		for (int i = j + 1; i < x.length; i++) {
			if (x[i] > ch && x[i] < x[min]) {
				min = i;
			}
		}
		swap(x, min, k);
		Arrays.sort(x, j, x.length);
		return new String(x);
	}

	static void swap(char[] x, int i, int j) {
		char temp = x[i];
		x[i] = x[j];
		x[j] = temp;
	}

	static boolean isValid1(String s1) {

		int h = Integer.parseInt(s1.substring(0, 2));
		int m = Integer.parseInt(s1.substring(2, 4));
		int s = Integer.parseInt(s1.substring(4, s1.length()));
		//System.out.println(h + " " + m + " " + s);
		return isValid(h, m, s);
	}

	static boolean isValid(String s1) {
		String[] strs = s1.split(":");
		int h = Integer.parseInt(strs[0]);
		int m = Integer.parseInt(strs[1]);
		int s = Integer.parseInt(strs[2]);
		return isValid(h, m, s);
	}

	static boolean isValid(int h, int m, int s) {
		return h >= 0 && h <= 23 && m >= 0 && m <= 59 && s >= 0 && s <= 59;
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