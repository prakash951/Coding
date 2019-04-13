package chef;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class CodeChefSubtreeRemoval {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int t1 = 0; t1 < t; t1++) {
			int[] a1 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int n = a1[0];
			int k = a1[1];
			HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
			for (int i = 1; i <= n; i++) {
				graph.put(i, new ArrayList<Integer>());
			}
			long[] a = Arrays.stream(br.readLine().split(" ")).mapToLong(Integer::parseInt).toArray();
			ArrayList<Integer>[] parent = new ArrayList[n + 1];
			parent[1] = new ArrayList<Integer>();
			parent[1].add(1);
			for (int i = 1; i < n; i++) {
				int[] x = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				graph.get(x[0]).add(x[1]);
				parent[i + 1] = new ArrayList<Integer>();
				parent[i + 1].add(i + 1);

			}
			long[] sum = a.clone();
			sum(1, graph, parent, sum, a);
			System.out.println(Arrays.toString(sum));
			// System.out.println(Arrays.toString(a));
			// System.out.println(Arrays.toString(parent));
			boolean[] visited = new boolean[n + 1];
			ArrayList<Pair> hs = new ArrayList<>();
			int k1 = 0;
			long tsum = a[0];
			for (int i = 2; i <= n; i++) {
				if (sum[i - 1] < 0 && !visited[i]) {
					hs.add(new Pair(i, sum[i - 1]));
					for (int x : parent[i]) {
						visited[x] = true;
					}
				} else if (sum[i - 1] >= 0 && !visited[i]) {
					tsum += sum[i - 1];
					k1++;
				}
			}

			Collections.sort(hs);
			// System.out.println(hs);
			long max = tsum - (graph.get(1).size() - k1) * k;
			;

			for (int i = 0; i < hs.size(); i++) {
				// System.out.println(tsum +" "+ hs.get(i).v + " "+(graph.get(1).size() - k1 - i
				// - 1) * k);
				long xsum = tsum + hs.get(i).v - (graph.get(1).size() - k1 - i - 1) * k;
				max = Math.max(xsum, max);
			}
			System.out.println(max);

		}

	}

	public static long sum(int x, HashMap<Integer, ArrayList<Integer>> graph, ArrayList<Integer>[] parent, long[] s,
			long[] a) {
		
		if (graph.get(x).size() == 0) {
			s[x - 1] = a[x - 1];
			return a[x - 1];
		}
		for (int u : graph.get(x)) {
			long sum = sum(u, graph, parent, s, a);
			s[x - 1] = s[x - 1] + sum;
			parent[x].addAll(parent[u]);
		}
		return s[x - 1];
	}

	static class Pair implements Comparable<Pair> {
		int index;
		long v;

		public Pair(int index, long v) {
			this.index = index;
			this.v = v;
		}

		@Override
		public int compareTo(Pair p) {
			if (this.v == p.v)
				return 0;
			else if (this.v < p.v)
				return +1;
			else
				return -1;
		}

		@Override
		public String toString() {
			return "Pair [index=" + index + ", v=" + v + "]";
		}
	}
}
