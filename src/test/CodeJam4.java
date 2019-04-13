package test;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.TreeSet;

public class CodeJam4 {

	public static void main(String[] args) throws Exception {
		Reader br = new Reader();
		int t = br.nextInt();
		for (int t1 = 1; t1 <= t; t1++) {

			int n = br.nextInt();
			int[] res1 = get(n);
			System.out.println("Case #"+t1+": "+res1[0]+" "+res1[1]);
		}
	}

	public static int[] get(int n) {
		int[] x = new int[] { 1, 2 };
		int[] res = new int[2];
		char[] c = new String("" + n).toCharArray();
		for (int j = 0; j <= 1; j++) {
				for (int i = 0; i < c.length; i++) {
					char ch = c[i];
					if (ch == '4') {
						c[i] = ("" + x[j]).charAt(0);
					}
				}
				String s = new String(c);
				int y = n - Integer.parseInt(s);
				if(!(""+y).contains("4"))
				{
					res[0] = Integer.parseInt(s);
					res[1] = y;
					break;
				}
		}
		return res;
	}

	public static void get(int n, StringBuffer sb1, StringBuffer sb2, TreeSet<Integer> hs) {
		if (n == 0) {
			System.out.println(sb1 + " " + sb2);
			return;
		}
		int r = n % 10;
		n = n / 10;
		for (int x : hs) {
			if (r == 0) {
				StringBuffer sb11 = new StringBuffer(sb1);
				StringBuffer sb12 = new StringBuffer(sb1);
				if (n < 10) {
					int a = n / 2;

					sb11.append(a);
					sb12.append(a);
				} else {
					sb11.append(0);
					sb12.append(0);
				}
				get(n, sb11, sb12, hs);
			} else {
				int y = r - x;
				StringBuffer sb11 = new StringBuffer(sb1);
				StringBuffer sb12 = new StringBuffer(sb1);
				if (y > 0 && hs.contains(y)) {
					sb11.append(x);
					sb12.append(y);
					get(n, sb11, sb12, hs);
				} else {
					break;
				}

			}
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
