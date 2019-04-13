package test;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.TreeMap;
import java.util.TreeSet;

public class CodeJam3 {
	static ArrayList<Integer> al = getPrimes();

	public static void main(String[] args) throws Exception {
		Reader br = new Reader();
		int t = br.nextInt();
		StringBuffer res = new StringBuffer();
		for (int t1 = 1; t1 <= t; t1++) {
			long n = br.nextLong();
			int l = br.nextInt();
			StringBuffer sb = new StringBuffer();

			long[] a = new long[l];
			list = new int[l][2];
			for (int i = 0; i < l; i++) {
				a[i] = br.nextLong();
			}
			TreeSet<Integer> ts = factors(a, al, n);
			TreeMap<Integer, Character> map = new TreeMap<>();
			char ch = 'A';
			// System.out.println(ts.size());
			if (ts.size() == 26) {
				for (int x : ts) {
					map.put(x, ch);
					ch = (char) (ch + 1);
				}
			} else {
				int low = ts.first();
				int high = ts.last();
				int k = Collections.binarySearch(al, low);
				for (int i = k; i < k + 26 && al.get(i) <= n; i++) {
					int x = al.get(i);
					map.put(x, ch);
					ch = (char) (ch + 1);
				}

			}
			// System.out.println(map);

			System.out.println(Arrays.deepToString(list));
			if (list[0][1] == list[1][0]) {
				sb.append(map.get(list[0][1]));
				sb.append(map.get(list[0][0]));
			} else {
				sb.append(map.get(list[0][0]));
				sb.append(map.get(list[0][1]));
			}
			for (int i = 1; i < l; i++) {
				sb.append(map.get(list[i][0]));
			}

			res.append("Case #" + t1 + ": " + sb.toString()).append("\n");
		}
		System.out.println(res);
	}

	static int[][] list;

	static TreeSet<Integer> factors(long[] x, ArrayList<Integer> al, long n) {
		TreeSet<Integer> ts = new TreeSet<>();
		for (int j = 0; j < al.size() && al.get(j) <= n; j++) {
			if (x[0] % al.get(j) == 0) {
				int a = (int) (x[0] / al.get(j));
				ts.add(a);
				ts.add(al.get(j));
				list[0][0] = al.get(j);
				list[0][1] = a;
				break;
			}
		}
		for (int i = 1; i < x.length; i++) {
			int f1 = list[i - 1][0];
			int f2 = list[i - 1][1];
			if (x[i] % f1 == 0) {
				int a = (int) (x[i] / f1);
				ts.add(a);
				ts.add(f1);
				list[i][0] = a;
				list[i][1] = f1;
			} else if (x[i] % f2 == 0) {
				int a = (int) (x[i] / f2);
				ts.add(a);
				ts.add(f2);
				list[i][0] = a;
				list[i][1] = f2;
				int t = list[i - 1][0];
				list[i - 1][0] = list[i - 1][1];
				list[i - 1][1] = t;
			}

		}
		return ts;
	}

	static ArrayList<Integer> getPrimes() {
		int k = 1000000;
		boolean[] b = new boolean[k];
		ArrayList<Integer> al = new ArrayList<Integer>();
		for (int i = 2; i <= (int) Math.sqrt(k); i++) {
			for (int j = 2; j * i - 1 < k; j++) {
				b[j * i - 1] = true;
			}
		}
		for (int i = 1; i < k; i++) {
			if (!b[i])
				al.add(i + 1);
		}
		return al;
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
