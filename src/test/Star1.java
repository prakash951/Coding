package test;

import java.util.LinkedList;
import java.util.Queue;

public class Star1 {

	public static void main(String[] args) {
		
		String s = "SE";
		int x = 100;
		for(int i=1;i<x-1;i++)
		{
			s = s+"SE";
		}
		System.out.println(s.length());
		System.out.println(s);

	}

	// adj as adjecencymatrix
	// u is strating vertex
	public static int getDimStar(int[][] adj, int u) {
		Queue<Integer> queue = new LinkedList<Integer>();
		int min = Integer.MAX_VALUE;
		boolean[] visited = new boolean[adj.length];
		queue.add(u);
		while (!queue.isEmpty()) {
			// size is the variable track nodes at each level
			int size = queue.size();
			min = Integer.MAX_VALUE;
			while (size > 0) {
				int x = queue.poll();
				min = Math.min(min, x);
				visited[x] = true;
				for (int i = 0; i < adj.length; i++) {
					if (adj[x][i] == 1 && !visited[i]) {
						queue.add(i);
						visited[i] = true;
					}
				}
				size = size - 1;
			}
		}
		return min;
	}

}
