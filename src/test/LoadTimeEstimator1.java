package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class LoadTimeEstimator1 {

	public static void main(String[] args) {
		int[] sizes = new int[] { 21, 10 };
		int[] u = { 100, 105 };
		int v = 2;
//		loadTimeEstimator(sizes, u, v);
//		sizes = new int[] { 20, 10 };
//		u = new int[] { 1, 1 };
//		v = 1;
//		loadTimeEstimator(sizes, u, v);
//		sizes = new int[] { 1, 1, 1 };
//		u = new int[] { 2, 2, 2 };
//		v = 3;
//		loadTimeEstimator(sizes, u, v);
//		sizes = new int[] { 1, 1, 1 };
//		u = new int[] { 10, 20, 30 };
//		v = 3;
//		loadTimeEstimator(sizes, u, v);
		sizes = new int[] { 12, 17, 2, 27, 23 };
		u = new int[] { 2, 5, 8, 6, 2 };
		v = 9;
		loadTimeEstimator(sizes, u, v);
//		sizes = new int[] { 30, 2, 5 };
//		u = new int[] { 3, 3, 8 };
//		v = 6;
//		loadTimeEstimator(sizes, u, v);
//		sizes = new int[] { 14, 8, 20, 25 };
//		u = new int[] { 3, 2, 6, 10 };
//		v = 7;
//		loadTimeEstimator(sizes, u, v);

	}

	static 	int[] loadTimeEstimator(int[] sizes, int[] uploadingStart, int v) {
		int[] res = new int[sizes.length];
		PriorityQueue<Node> pq = new PriorityQueue<>();
		for (int i = 0; i < sizes.length; i++) {
			pq.add(new Node(sizes[i], uploadingStart[i], i));
		}
		while (!pq.isEmpty()) {
			ArrayList<Node> al = new ArrayList<>();
			Node temp = pq.poll();
			al.add(temp);
			while (pq.size() > 0 && temp.start_time == pq.peek().start_time) {
				temp = pq.poll();
				al.add(temp);
			}

			float y = ((float) v) / al.size();
			int x = 0;
			if (pq.size() > 0)
				x = (pq.peek().start_time - al.get(al.size() - 1).start_time);
			if (x != 0) {
				x = Math.min(x, (int) Math.ceil(al.get(0).size / y));
			} else {
				x = (int) Math.ceil(al.get(0).size / y);
			}

			float z = y * x;
			if (x == 0)
				z = v;
			float ps = 0.0f;
			for (int i = 0; i < al.size(); i++) {
				if (al.get(i).size - ps > z) {
					al.get(i).size = al.get(i).size - z - ps;
					al.get(i).start_time += x;
					pq.add(al.get(i));
				} else if (al.get(i).size - ps == z) {
					res[al.get(i).index] = al.get(i).start_time + x;
				} else {
					res[al.get(i).index] = al.get(i).start_time + (int) Math.ceil(al.get(i).size / y);
					ps += ((z - al.get(i).size)/((al.size()-i-1)!=0?(al.size()-i-1):1));
				}
			}

		}
		return res;

	}

	static class Node implements Comparable<Node> {
		float size;
		int start_time;
		int index;

		public Node(float size, int s, int index) {
			this.size = size;
			this.start_time = s;
			this.index = index;
		}

		public int compareTo(Node n) {
			if (n.start_time == this.start_time) {
				if (this.size < n.size)
					return -1;
				else if (this.size > n.size)
					return 1;
				else
					return 0;
			}

			return this.start_time - n.start_time;
		}

		public String toString() {
			return "{" + size + " , " + start_time + " , " + index + "}";
		}
	}

}
