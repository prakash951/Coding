package chef;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ChefFence {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int t1 = 0; t1 < t; t1++) {
			int[] a1 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int n = a1[0];
			int q = a1[1];
			HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
			for (int i = 1; i <= n; i++) {
				graph.put(i, new ArrayList<Integer>());
			}
			int[] a = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			ArrayList<Integer>[] parent = new ArrayList[n + 1];
			parent[1] = new ArrayList<Integer>();
			parent[1].add(1);
			for (int i = 1; i < n; i++) {
				int[] x = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				graph.get(x[0]).add(x[1]);
				parent[i + 1] = new ArrayList<Integer>();
				parent[i + 1].add(i + 1);

			}
			sum(1, graph, parent);
			// System.out.println(Arrays.toString(parent));
			int v = 0;
			int k = 0;
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < q; i++) {
				int[] q1 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				v = v ^ q1[0];
				k = k ^ q1[1];
				int max = Integer.MIN_VALUE;
				int v1 = Integer.MAX_VALUE;
				if (v <= n) {
					for (int v2 : parent[v]) {
						int val = a[v2 - 1] ^ k;
						if (max < val) {
							max = val;
							v1 = v2;
						} else if (max == val) {
							v1 = Math.min(v1, v2);
						}
					}
				}
				else
				{
					v1 = 0;
					max = 0;
				}
				sb.append(v1 + " " + max).append("\n");
				v = v1;
				k = max;
				Thread.sleep(100);
			}
			System.out.println(sb);
		}

	}

	public static void sum(int x, HashMap<Integer, ArrayList<Integer>> graph, ArrayList<Integer>[] parent) {

		for (int u : graph.get(x)) {
			sum(u, graph, parent);
			parent[x].addAll(parent[u]);
		}
	}

}
