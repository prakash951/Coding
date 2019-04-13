package test;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

public class CodeJam31 {
	static ArrayList<Integer> al = getPrimes();

	public static void main(String[] args) throws Exception {
		Reader br = new Reader();
		int t = br.nextInt();
		StringBuffer res = new StringBuffer();
		for (int t1 = 1; t1 <= t; t1++) {
			int n = br.nextInt();
			int l = br.nextInt();
			StringBuffer sb = new StringBuffer();

			int[] a = new int[l];
			listset = new ArrayList[l];
			for (int i = 0; i < l; i++) {
				a[i] = br.nextInt();
			}
			TreeSet<Integer> ts = factors(a, al, n);
			TreeMap<Integer, Character> map = new TreeMap<>();
			char ch = 'A';

			for (int x : ts) {
				map.put(x, ch);
				ch = (char) (ch + 1);
			}

			int[] result = new int[l+1];
			result[0] = getCommon1(listset[1],listset[0]);
			for(int i=1;i<l;i++)
			{
				result[i] = getCommon(listset[i-1],listset[i]);
			}
			result[l] = getCommon1(listset[l-2],listset[l-1]);
			//System.out.println(Arrays.toString(result));

			for (int i = 0; i <= l; i++) {
				sb.append(map.get(result[i]));
			}

			res.append("Case #" + t1 + ": " + sb.toString()).append("\n");
		}
		System.out.println(res);
	}

	static ArrayList<Integer>[] listset;
	
	static int getCommon1(ArrayList<Integer> al1,ArrayList<Integer> al2)
	{
		for(int x:al2)
		{
			if(!al1.contains(x))
				return x;
		}
		return al1.get(0);
	}
	
	static int getCommon(ArrayList<Integer> al1,ArrayList<Integer> al2)
	{
		for(int x:al2)
		{
			if(al1.contains(x))
				return x;
		}
		return -1;
	}

	static TreeSet<Integer> factors(int[] x, ArrayList<Integer> al, int n) {
		TreeSet<Integer> ts = new TreeSet<>();
		listset[0] = new ArrayList<Integer>();
		for (int j = 0; j < al.size(); j++) {
			if (x[0] % al.get(j) == 0) {
				int a = (x[0] / al.get(j));
				ts.add(a);
				ts.add(al.get(j));
				listset[0].add(a);
				listset[0].add(al.get(j));
				break;
			}
		}
		for (int i = 1; i < x.length; i++) {
			listset[i] = new ArrayList<>();
			int f1 = listset[i - 1].get(0);
			int f2 = listset[i - 1].get(1);
			if (x[i] % f1 == 0) {
				int a = (x[i] / f1);
				ts.add(a);
				ts.add(f1);
				listset[i].add(a);
				listset[i].add(f1);
			} else if (x[i] % f2 == 0) {
				int a = (x[i] / f2);
				ts.add(a);
				ts.add(f2);
				listset[i].add(a);
				listset[i].add(f2);
			}

		}
		return ts;
	}

	static ArrayList<Integer> getPrimes() {
		int k = 10000;
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
