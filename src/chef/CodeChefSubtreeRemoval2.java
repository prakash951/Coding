package chef;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class CodeChefSubtreeRemoval2 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int t1 = 0; t1 < t; t1++) {
			int[] a1 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int n = a1[0];
			long k = a1[1];
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
			int[] prune = new int[n + 1];
			sum(1, graph, parent, sum, a, prune, k);

			// System.out.println(Arrays.toString(sum));
			// System.out.println(Arrays.toString(a));
			// System.out.println(Arrays.toString(parent));
			// System.out.println(Arrays.toString(prune));
			long k1 = 0;
			long max = 0;
			for (int i = 1; i <= n; i++) {
				if (prune[i] == 0) {
					max = max + a[i - 1];
				}
				if (prune[i] == 1) {
					prune[i] = 2;
					for (int x1 : parent[i]) {
						prune[x1] = 2;
					}
					k1++;
				}
			}
			// System.out.println(max);
			System.out.println(max - k);

		}

	}

	public static long[] sum(int x, HashMap<Integer, ArrayList<Integer>> graph, ArrayList<Integer>[] parent, long[] s,
			long[] a, int[] prune, long k) {

		if (graph.get(x).size() == 0) {
			s[x - 1] = a[x - 1];
			int f = 0;
			if (s[x - 1] < 0 && Math.abs(s[x - 1]) > k)
				f = 1;
			return new long[] { s[x - 1], f };
		}
		long s1 = 0;
		long sum = 0;
		int f = 0;
		for (int u : graph.get(x)) {
			long[] m = sum(u, graph, parent, s, a, prune, k);
			sum = m[0];
			s1 = s[x - 1] + sum;
			if (s1 >= s[x - 1]) {
				s[x - 1] = s1;
			} else {
				prune[u] = 1;
				f = 1;
			}
			parent[x].addAll(parent[u]);
			if (m[1] == 1)
				f = 1;
		}

		return new long[] { s[x - 1], f };
	}
}
