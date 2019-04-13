package test;

import java.util.LinkedList;
import java.util.Queue;

public class Tree1 {

	public static void main(String[] args) {
		int[] a = { 15, 12, 17, 18, 20, 13, 4, 3, 11, 10, 21 };
		for (int x : a)
			insert(x);
		// level();
		getStatus(root, 12, 2);
	}

	public static void level() {
		Queue<Node> queue = new LinkedList<>();
		Node r = root;
		queue.add(r);
		while (!queue.isEmpty()) {
			int x = queue.size();
			StringBuffer sb = new StringBuffer();
			while (x > 0) {
				r = queue.poll();
				sb.append(r.v).append(" ");
				if (r.left != null)
					queue.add(r.left);
				if (r.right != null)
					queue.add(r.right);
				x--;
			}
			System.out.println(sb);
		}
	}

	public static int getStatus(Node root, int val, int k) {
		if (root == null)
			return -1;
		if (root.v == val) {
			knodeDown(root, k);
			return 0;
		}
		int dl = getStatus(root.left, val, k);
		if (dl != -1) {
			if (dl + 1 == k) {
				System.out.println(root.v);
			} else {
				knodeDown(root.right, k - dl - 2);
			}
			return dl + 1;
		}
		int dr = getStatus(root.right, val, k);
		if (dr != -1) {
			if (dr + 1 == k) {
				System.out.println(root.v);
			} else {
				knodeDown(root.left, k - dr - 2);
			}
			return dr + 1;
		}
		return -1;

	}

	public static void knodeDown(Node root, int k) {
		if (root == null || k < 0)
			return;
		if (k == 0) {
			System.out.println(root.v);
			return;
		}
		knodeDown(root.left, k - 1);
		knodeDown(root.right, k - 1);
	}

	public static void insert(int val) {
		Node data = new Node(val);
		if (root == null) {
			root = data;
			return;
		}
		Node prev = null;
		Node r = root;
		while (r != null) {
			prev = r;
			if (r.v < val)
				r = r.right;
			else
				r = r.left;
		}
		if (prev.v < val)
			prev.right = data;
		else
			prev.left = data;
	}

	static Node root;

	static class Node {
		int v;
		Node left;
		Node right;

		public Node(int v) {
			this.v = v;
		}
	}
}
