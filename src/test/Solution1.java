package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution1 {

	public static void main(String[] args) throws Exception {
		count = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long[] x = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
		int n = (int) x[0];
		long l = x[1];
		long r = x[2];
		String[] strs = br.readLine().split(" ");
		List<Integer> a = new ArrayList<Integer>();
		long sum = 0;
		for (String s : strs) {
			int s1 = Integer.parseInt(s);
			a.add(s1);
			sum += s1;
		}
		if (sum >= l && sum <= r)
			count++;
		for(int i=2;i<=n;i++)
		{
			List<List<List<Integer>>> h = helper(a,i,l,r);
			//System.out.println(h);
			for(List<List<Integer>> h1:h)
			{
				count+=getSum(h1,l,r);
			}
		}
		
		System.out.println(count);
	}

	static int count = 0;

	private static List<List<List<Integer>>> helper(List<Integer> ori, int m, long left, long right) {
		List<List<List<Integer>>> ret = new ArrayList<>();
		if (ori.size() < m || m < 1)
			return ret;

		if (m == 1) {
			List<List<Integer>> partition = new ArrayList<>();
			partition.add(new ArrayList<>(ori));
			ret.add(partition);
			return ret;
		}

		// f(n-1, m)
		List<List<List<Integer>>> prev1 = helper(ori.subList(0, ori.size() - 1), m, left, right);
		
		for (int i = 0; i < prev1.size(); i++) {
			for (int j = 0; j < prev1.get(i).size(); j++) {
				// Deep copy from prev1.get(i) to l
				List<List<Integer>> l = new ArrayList<>();
				for (List<Integer> inner : prev1.get(i)) {
					l.add(new ArrayList<>(inner));
				}

				l.get(j).add(ori.get(ori.size() - 1));
				ret.add(l);
			}
		}
		
		//ret.addAll(prev1);
		List<Integer> set = new ArrayList<>();
		set.add(ori.get(ori.size() - 1));
		// f(n-1, m-1)
		List<List<List<Integer>>> prev2 = helper(ori.subList(0, ori.size() - 1), m - 1, left, right);
		for (int i = 0; i < prev2.size(); i++) {
			List<List<Integer>> l = new ArrayList<>(prev2.get(i));
			l.add(set);
			ret.add(l);
		}
		//ret.addAll(prev2);
		return ret;
	}

	private static int getSum(List<List<Integer>> list, long left, long right) {

		boolean flag = true;
		for (int i = 0; i < list.size(); i++) {
			List<Integer> l = list.get(i);
			long sum = 0;
			for (int x : l) {
				sum += x;
			}
			if (!(sum >= left && sum <= right)) {
				flag = false;
				break;
			}
		}
		if (flag)
		{
			System.out.println(list);
			return 1;
		}
		return 0;

	}

}
