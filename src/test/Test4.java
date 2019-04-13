package test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class Test4 {

	public static void main(String[] args) throws java.lang.Exception {

		String[][] tasks = new String[][] { { "BWTYQ", "2017-09-07", "2017-09-09", "Kaleb" },
				{ "IVNTU", "2017-06-15", "2017-09-07", "Vincenzo", "Kai", "Landyn", "Jenson" },
				{ "YZNVZ", "2017-06-06", "2017-06-20", "Corey", "Reuben", "Kyle", "Daxton" },
				{ "UKZGD", "2017-09-16", "2017-09-19", "Kai", "Kyle", "Jenson", "Jamal" },
				{ "ZLYNM", "2017-08-24", "2017-10-01", "Kyle", "Kai", "Jamal" },
				{ "EXLAG", "2017-06-05", "2017-09-19", "Kaleb", "Kyle", "Kai" },
				{ "ANBUQ", "2017-05-16", "2017-09-08", "Kai" },
				{ "MVTMI", "2017-07-16", "2017-08-24", "Kyle", "Landyn" },
				{ "ITXJC", "2017-09-05", "2017-09-06", "Reuben", "Corey", "Daxton" },
				{ "KLFDO", "2017-05-25", "2017-09-15", "Corey", "Jenson", "Kai" } };
		String[][] queries = { { "Kaleb", "2017-05-01" }, { "Kyle", "2017-09-11" }, { "Kai", "2017-10-22" },
				{ "Jenson", "2017-08-17" }, { "Jenson", "2017-08-06" }, { "Reuben", "2017-06-25" },
				{ "Jenson", "2017-08-10" }, { "Kaleb", "2017-06-03" }, { "Jenson", "2017-06-10" },
				{ "Vincenzo", "2017-06-24" }, { "Vincenzo", "2017-06-14" }, { "Kai", "2017-08-01" },
				{ "Kaleb", "2017-09-14" }, { "Landyn", "2017-07-24" }, { "Landyn", "2017-05-03" },
				{ "Jenson", "2017-10-13" }, { "Kyle", "2017-07-07" }, { "Corey", "2017-07-08" },
				{ "Landyn", "2017-05-04" }, { "Corey", "2017-09-06" } };

		System.out.println(Arrays.deepToString((Object[]) roadmap(tasks, queries)));

	}

	static String[][] roadmap(String[][] tasks, String[][] queries) {

		HashMap<String, ArrayList<Node>> map = new HashMap<>();

		for (String[] task : tasks) {
			Node x = new Node(task[1], task[2], task[0]);
			for (int i = 3; i < task.length; i++) {
				String s = task[i];
				if (map.get(s) == null) {
					map.put(s, new ArrayList<Node>());
				}
				map.get(s).add(x);
			}
		}
		ArrayList<ArrayList<Node>> res = new ArrayList<>(queries.length);
		for (int i = 0; i < queries.length; i++) {
			res.add(new ArrayList<Node>());
			String[] q = queries[i];
			ArrayList<Node> nodes = map.get(q[0]);
			LocalDate x = LocalDate.parse(q[1]);
			for (Node n : nodes) {
				if (n.st.compareTo(x) <= 0 && n.ed.compareTo(x) >= 0) {
					res.get(i).add(n);
				}
			}
		}
		String[][] r = new String[res.size()][];
		for (int i = 0; i < r.length; i++) {
			r[i] = new String[res.get(i).size()];
			ArrayList<Node> nodes = res.get(i);
			Collections.sort(nodes);
			for (int j = 0; j < nodes.size(); j++) {
				r[i][j] = nodes.get(j).task;
			}
		}
		return r;
	}

	static class Node implements Comparable<Node> {
		LocalDate st;
		LocalDate ed;
		String task;

		public Node(String st, String ed, String task) {
			this.st = LocalDate.parse(st);
			this.ed = LocalDate.parse(ed);
			this.task = task;
		}

		@Override
		public int compareTo(Node o) {

			if (o.ed.compareTo(this.ed) == 0)
				return this.task.compareTo(o.task);
			return this.ed.compareTo(o.ed);
		}

	}

}
