package test;

import java.util.Stack;

public class GoogleStringExpander {

	public static void main(String[] args) {
		System.out.println(decodeString("sd2[f2[e]g]i"));
	}

	static String decodeString(String s) {
		System.out.println(s);
		Stack<String> stack1 = new Stack<>();
		Stack<Integer> stack2 = new Stack<>();
		int n = s.length();
		for (int i = 0; i < n; i++) {
			char ch = s.charAt(i);
			int j = i;
			if (Character.isDigit(ch)) {
				StringBuffer sb = new StringBuffer();
				while (j < n && Character.isDigit(s.charAt(j))) {
					sb.append(s.charAt(j));
					j += 1;
				}
				stack2.push(Integer.parseInt(sb.toString()));
			} else if (Character.isLetter(ch)) {
				StringBuffer sb = new StringBuffer();
				while (j < n && Character.isLetter(s.charAt(j))) {
					sb.append(s.charAt(j));
					j += 1;
				}
				stack1.push(sb.toString());
			} else if (ch == ']') {
				String x = stack1.pop();
				StringBuffer sb = new StringBuffer();
				if (stack2.size() > 0) {
					int y = stack2.pop();

					for (int k = 0; k < y; k++) {
						sb.append(x);
					}
				}
				if (stack1.size() > 0) {
					x = stack1.pop();
					x = x + sb.toString();
					stack1.push(x);
				} else {
					stack1.push(sb.toString());
				}
			}
			if (stack1.size() > stack2.size()) {
				int k = stack1.size() - stack2.size();
				StringBuffer sd = new StringBuffer();
				while (k > 0) {
					sd = new StringBuffer(stack1.pop()).append(sd);
					k--;
				}
				stack1.push(sd.toString());
			}
			if (j == n)
				break;
			if (j > i && j != n)
				i = j - 1;

		}
		StringBuffer sb = new StringBuffer();
		while (!stack1.isEmpty()) {
			s = stack1.pop();
			sb = new StringBuffer(s).append(sb);
		}
		return sb.toString();
	}

}
