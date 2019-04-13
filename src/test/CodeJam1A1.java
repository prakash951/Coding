package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.TreeSet;

public class CodeJam1A1 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuffer sb = new StringBuffer();
		for (int t1 = 1; t1 <= t; t1++) {
			hs = new TreeSet<>();
			int n = Integer.parseInt(br.readLine());
			String[] strs = new String[n];
			Trie trie = new Trie();
			HashSet<String> strings = new HashSet<>();
			for (int i = 0; i < n; i++) {
				strs[i] = new StringBuffer(br.readLine().trim()).reverse().toString();
				trie.insert(strs[i]);
			}
			trie.traverse(trie.root);
			//System.out.println(hs);
			for (Pair p : hs) {
				//System.out.println(p);
				for (int i = 0; i < n; i++) {
					boolean flag = false;
					if (strs[i] != null && strs[i].startsWith(p.suf)) {

						for (int j = i + 1; j < n; j++) {
							if (strs[j] != null && strs[j].startsWith(p.suf)) {
								strings.add(strs[i]);
								strings.add(strs[j]);
								strs[i] = null;
								strs[j] = null;
								flag = true;
								break;
							}
						}
					}
					if (flag)
						break;
				}
			}
			sb.append("Case #"+t1+": "+strings.size()).append("\n");
			//sb.append("Case #"+t1+": "+hs.size()*2).append("\n");
		}
		System.out.println(sb);

	}

	static TreeSet<Pair> hs = new TreeSet<>();

	static class Pair implements Comparable<Pair> {
		String suf;
		int count;

		public Pair(String suf, int count) {
			this.suf = suf;
			this.count = count;
		}

		@Override
		public String toString() {
			return "Pair [suf=" + suf + ", count=" + count + "]";
		}


		@Override
		public int compareTo(Pair o) {
			if (this.count == o.count)
			{
				if(o.suf.length()!=this.suf.length())
				{
					return o.suf.length() - this.suf.length();
				}
				else
				{
					return o.suf.compareTo(this.suf);
				}
			}

			return this.count - o.count;
		}

	}

	static class Trie {
		TrieNode root;

		public void insert(String str) {
			if (root == null) {
				root = new TrieNode(' ');
			}
			insertUtil(str, root);
		}

		public boolean flag = false;

		public boolean find(String str, TrieNode root) {
			if (root == null)
				return false;
			for (int i = 0; i < str.length(); i++) {
				int z = atoi(str.charAt(i));
				if (root.nodes[z] == null) {
					return false;
				}
				if (root.nodes[z].word == true) {
					return true;
				}
				root = root.nodes[z];
			}
			return true;
		}

		public void traverse(TrieNode root) {
			if (root == null)
				return;
			char[] xc = new char[1024];
			traverseUtil(root, xc, 0);
		}

		public void traverseUtil(TrieNode root, char[] xc, int index) {
			if (root == null)
				return;
			for (int i = 0; i < 26; i++) {
				if (root.nodes[i] != null) {
					xc[index] = root.nodes[i].data;
					if (root.nodes[i].count > 1) {
						hs.add(new Pair(new String(xc, 0, index + 1), root.nodes[i].count));
					}
					traverseUtil(root.nodes[i], xc, index + 1);
				}
			}
		}

		public void insertUtil(String str, TrieNode root) {
			for (int i = 0; i < str.length(); i++) {
				int z = atoi(str.charAt(i));
				if (root.nodes[z] == null) {
					root.nodes[z] = new TrieNode(str.charAt(i));
				} else {
					root.nodes[z].count = root.nodes[z].count + 1;
				}
				root = root.nodes[z];
			}
			root.word = true;
		}

		public int atoi(char ch) {
			if (ch >= 65) {
				return ch - 65;
			}
			return -1;
		}
	}

	static class TrieNode {
		char data;
		TrieNode[] nodes;
		boolean word;
		int count;

		public TrieNode(char data) {
			this.data = data;
			nodes = new TrieNode[32];
			count = 1;
		}

		@Override
		public String toString() {
			return "TrieNode [data=" + data + ", nodes=" + Arrays.toString(nodes) + ", word=" + word + ", count="
					+ count + "]";
		}
	}

}
