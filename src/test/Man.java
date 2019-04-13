package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.TreeSet;

public class Man {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int i = 0; i < t; i++) {
			int n = Integer.parseInt(br.readLine());
			HashMap<String, Integer> map = new HashMap<>();
			TreeSet<Node> ts = new TreeSet<>();
			for (int j = 0; j < n; j++) {
				String s = br.readLine();
				if (!s.startsWith("top ")) {
					int val = 1;
					if (map.get(s) != null) {
						val = map.get(s) + 1;
					}
					map.put(s, val);
					Node nn = new Node(s, val);
					if (val > 1) {
						Node x = new Node(s, val - 1);
						if (ts.contains(x)) {
							NavigableSet<Node> sub = ts.tailSet(x, true);
							// System.out.println(sub);
							x = sub.pollFirst();
							ts.remove(x);
							// System.out.println(ts);
							if (x.ts.size() > 1) {
								x.ts.remove(s);
								ts.add(x);
							}
						}
					}
					if (ts.contains(nn)) {
						NavigableSet<Node> sub = ts.tailSet(nn, true);
						nn = sub.pollFirst();
						ts.remove(nn);
						nn.ts.add(s);
					}
					ts.add(nn);
				} else {
					String[] strs = s.split(" ");
					int m = Integer.parseInt(strs[1]);
					Iterator<Node> it = ts.iterator();
					StringBuffer sb = new StringBuffer();
					while (it.hasNext() && m > 0) {
						Node xn = it.next();
						for (String xss : xn.ts) {
							sb.append(xss).append(" ");
							m--;
							if(m==0) break;
						}
					}
					System.out.println(sb);
				}
				// System.out.println(ts);
			}
		}
	}

	static class Node implements Comparable<Node> {
		int x;
		TreeSet<String> ts;

		public Node(String s, int x) {
			this.x = x;
			if (ts == null)
				ts = new TreeSet<>();
			ts.add(s);
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", ts=" + ts.toString() + "]";
		}

		public int compareTo(Node n) {
			return -this.x + n.x;
		}
	}
}