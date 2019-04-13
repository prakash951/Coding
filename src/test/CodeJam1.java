package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class CodeJam1 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int t1 = 0; t1 <= t; t1++) {
			int n = Integer.parseInt(br.readLine());
		}

	}

	public static int[] getInts(int n) {
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 1; i <= 9; i++) {
			if (i != 4) {
				map.put(i, i);
			}
		}
		ArrayList<Integer> al1 = new ArrayList<Integer>();
		ArrayList<Integer> al2 = new ArrayList<Integer>();
		
		return null;
	}

}
