package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

class TestPow1 {

	static ArrayList<Integer> al = getPrimes();

	public static void main(String[] args) throws Exception {


		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuffer sb = new StringBuffer();
		for (int x = 0; x < t; x++) {
			//long n = Long.parseLong(br.readLine().trim());
			long n = x+1;
			long s = 0;
			if (n % 2 == 0)
				s = (n / 2) * (n + 1);
			else
				s = (n) * ((n + 1) / 2);
			if (n == 1) {
				sb.append("YES").append(" "+n).append("\n");
			} else {
				HashMap<Integer, Integer> map = new HashMap<>();
				getMap(map, s);
				HashMap<Integer, Integer> map1 = new HashMap<>();
				getFactMap(map1, n);
				boolean flag = getFact(map, map1);
				if (flag) {
					//sb.append("YES").append(" "+n).append(" "+Long.toBinaryString(n)).append("\n");
					
				}
					//sb.append("YES").append(" "+n).append("\n");
				else
					//sb.append("NO").append(" "+n).append("\n");
				{sb.append("NO").append(" "+n).append(" "+Long.toBinaryString(n)).append("\n");
				}
				
			}
		}
		System.out.println(sb);
	}

	
	static boolean getFact(HashMap<Integer, Integer> map1, HashMap<Integer, Integer> map2) {
		Iterator<Integer> it = map1.keySet().iterator();
		while(it.hasNext())
		{
			int x = it.next();
			if(map2.get(x)==null || map2.get(x).intValue()<map1.get(x).intValue())
				return false;
		}
		return true;
	}
	
	public static void getFactMap(HashMap<Integer, Integer> map, long n) {
		long a = n;
		for (int i = 0; i < al.size() && al.get(i)<=n ; i++) {
			int x = al.get(i);
			int c = 0;
			int y = x;
			int j = 1;
			while ((a / y) > 0) {
				c = c + (int)(a / y);
				j++;
				y = (int) Math.pow(x, j);
			}
			if (c != 0)
				map.put(x, c);
		}
	}

	public static void getMap(HashMap<Integer, Integer> map, long n) {
		for (int i = 0; i < al.size() && al.get(i)<=n ; i++) {
			int x = al.get(i);
			int c = 0;
			while (n % x == 0 && n != 1) {
				c++;
				n = n / x;
			}
			if (c != 0)
				map.put(x, c);
			if (n == 1)
				break;
		}
	}

	public static ArrayList<Integer> getPrimes() {
		ArrayList<Integer> al = new ArrayList<Integer>();
		int x = 9000000;
		boolean[] b = new boolean[x];
		for (int i = 2; i <= (int) Math.sqrt(x) + 1; i++) {
			for (int j = i; j * i - 1 < x; j++) {
				b[j * i - 1] = true;
			}
		}
		for (int i = 1; i < b.length; i++) {
			if (!b[i])
				al.add(i + 1);
		}
		return al;
	}

}