package chef;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class ChefCERSOL {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int t1 = 0; t1 < t; t1++) {
			HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
			int[] a = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int n = a[0];
			int m = a[1];
			int k = a[2];
			for (int i = 1; i <= n; i++) {
				graph.put(i, new ArrayList<Integer>());
			}
			int[] c = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			HashSet<Integer> hs = new HashSet<>();
			for (int i = 0; i < n; i++) {
				hs.add(c[i]);
			}
			if (hs.size() < k) {
				System.out.println(-1);
			} else {
				boolean flag = true;
				for (int i = 0; i < m; i++) {
					a = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
					graph.get(a[0]).add(a[1]);
					if(graph.get(a[0]).size()>2)
					{
						flag = false;
						break;
					}
				}
				if(!flag)
					System.out.println(-1);
				else
					System.out.println(k);
			}
		}

	}

}
