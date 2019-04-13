package test;

import java.util.TreeSet;

public class CodeJam44 {

	public static void main(String[] args) {
		int n = 100;
		int[] a = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		TreeSet<Integer> hs = new TreeSet<Integer>();
		hs.add(4);
		int x = 1;
		while (x <= n) {
			TreeSet<Integer> ts = new TreeSet<>();
			for (int y : hs) {
				StringBuffer sb = new StringBuffer();
				sb.append(y);
				for (int i = 0; i < a.length; i++) {
					StringBuffer sb1 = new StringBuffer(sb);
					ts.add(Integer.parseInt(sb1.append(a[i]).toString()));
					x++;
				}
			}
			for (int i = 0; i < a.length; i++) {
				StringBuffer sb = new StringBuffer(a[i]);
				sb.append('4');
				ts.add(Integer.parseInt(sb.toString()));
				x++;
			}
			hs.addAll(ts);
		}
		System.out.println(hs.size());

	}

}
