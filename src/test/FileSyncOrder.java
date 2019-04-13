package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.TreeSet;

/* Name of the class has to be "Main" only if the class is public. */
public class FileSyncOrder {
	public static void main(String[] args) throws java.lang.Exception {
		int[][] x = new int[][] { { 10, 5 }, { 10, 7 }, { 8, 10 }, { 2, 20 } };
		int sl = 20;
		int us = 2;
		int d = 100;
		fileSyncOrder(x, sl, us, d);
		x = new int[][] { { 10, 5 } };
		sl = 100;
		us = 1;
		d = 10;
		fileSyncOrder(x, sl, us, d);
		x = new int[][] { { 10, 1 }, { 20, 1 }, { 30, 1 } };
		sl = 60;
		us = 2;
		d = 31;
		fileSyncOrder(x, sl, us, d);
		x = new int[][] { { 20, 5 }, { 10, 5 }, { 3, 7 } };
		sl = 100;
		us = 1;
		d = 40;
		fileSyncOrder(x, sl, us, d);
		x = new int[][] { { 20, 5 }, { 10, 5 }, { 30, 7 } };
		sl = 100;
		us = 1;
		d = 40;
		fileSyncOrder(x, sl, us, d);
		x = new int[][] { { 10, 5 }, { 10, 5 }, { 3, 7 } };
		sl = 100;
		us = 1;
		d = 28;
		fileSyncOrder(x, sl, us, d);
	}

	static int[] fileSyncOrder(int[][] f, int sl, int us, int d) {

		int total_size = 0;
		int total_duration = 0;
		int[] time = new int[f.length];
		PriorityQueue<Node> pq = new PriorityQueue<>();
		for (int i = 0; i < time.length; i++) {
			time[i] = f[i][0] / us;
		}

		for (int i = 0; i < f.length; i++) {
			pq.add(new Node(f[i][0], f[i][1], i));
		}
		TreeSet<Integer> ts = new TreeSet<>();
		ArrayList<Node> al = new ArrayList<>();
		while (!pq.isEmpty()) {
			Node node = pq.poll();
			//System.out.println(node);
			if (node.size + total_size <= sl) {
				int x = ts.contains(node.time) ? 0 : node.time;
				//System.out.println(total_duration);
				if (x + total_duration + time[node.index] <= d) {
					if (total_duration <= node.time) {
						al.add(node);
						total_size = node.size + total_size;
						total_duration = x + total_duration + time[node.index];
						ts.add(node.time);
					} else if (node.time < total_duration) {
						node.time = total_duration;
						ts.add(total_duration);
						pq.add(node);
					}
				}

			}
		}
		int[] res = new int[al.size()];
		for (int i = 0; i < al.size(); i++) {
			res[i] = al.get(i).index;
		}
		System.out.println(Arrays.toString(res));
		return res;
	}

	static class Node implements Comparable<Node> {
		int size;
		int time;
		int index;

		public Node(int size, int time, int index) {
			this.size = size;
			this.time = time;
			this.index = index;
		}

		public int compareTo(Node n) {
			if (this.time == n.time) {
				return this.size - n.size;
			}
			return this.time - n.time;
		}

		public String toString() {
			return "[ " + size + " , " + time + " ]";
		}
	}
}
