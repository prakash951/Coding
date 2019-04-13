package chef;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CodeShefSTRCH {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int t1 = 0; t1 < t; t1++) {
			ArrayList<Pair> al = new ArrayList<>();
			int n = Integer.parseInt(br.readLine());
			Pair p = new Pair(0, 0);
			String[] s = br.readLine().split(" ");
			char ch = s[1].charAt(0);
			char[] a = s[0].toCharArray();
			for (int i = 0; i < n; i++) {
				if (ch == a[i]) {
					p.y = i;
					al.add(p);
					p = new Pair(0, 0);
					p.x = i + 1;
				}
			}
			p.y = n;
			al.add(p);
			long sum = ((long) n * (long) (n + 1)) / 2;
			for (int i = 0; i < al.size(); i++) {
				p = al.get(i);
				long d = (p.y - p.x);
				sum = sum - (d * (d + 1)) / 2;
			}
			System.out.println(sum);
		}
	}

	static class Pair {
		long x;
		long y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Pair [x=" + x + ", y=" + y + "]";
		}
	}

}
