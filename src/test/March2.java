package test;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;

public class March2 {

	public static void main(String[] args) throws Exception {
		Reader r = new Reader();
		int n = r.nextInt();
		int k = r.nextInt();
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		for (int i = 0; i < n - 1; i++) {
			int x = r.nextInt();
			int y = r.nextInt();
			int w = r.nextInt();
			pq.add(new Pair(x, y, w));
		}
		DST dst = new DST(n);
		while (!pq.isEmpty()) {
			Pair p = pq.poll();
			if (p.w <= k)
				dst.union(p.x, p.y, p.w, k);
		}

		HashSet<Integer> hs = new HashSet<Integer>();
		for (int i = 1; i <= n; i++) {
			hs.add(dst.parent[i]);
		}
		//System.out.println(Arrays.toString(dst.parent));
		System.out.println(hs.size());
	}

	static class DST {
		int[] rank;
		int[] parent;
		int[] weight;

		public DST(int n) {
			rank = new int[n + 1];
			parent = new int[n + 1];
			weight = new int[n + 1];
			for (int i = 1; i <= n; i++)
				parent[i] = i;
		}

		public int find(int i) {
			if (i != parent[i])
				return find(parent[i]);
			return i;
		}

		public void union(int i, int j, int w, int k) {
			int pi = find(i);
			int pj = find(j);
			if (pi == pj)
				return;
			if (rank[pi] < rank[pj] && weight[pj] + w <= k) {
				parent[pi] = pj;
				rank[pj] = rank[pi] + 1;
				weight[pj] = weight[pj] + w;
			} else if (rank[pj] <= rank[pi] && weight[pi] + w <= k) {
				parent[pj] = pi;
				rank[pi] = rank[pj] + 1;
				weight[pi] = weight[pi] + w;
			}
		}
	}

	static class Pair implements Comparable<Pair> {
		int x;
		int y;
		int w;

		public Pair(int x, int y, int w) {
			this.x = x;
			this.y = y;
			this.w = w;
		}

		@Override
		public int compareTo(Pair p) {
			if (this.w != p.w)
				return this.w - p.w;
			return this.x - p.x;
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
