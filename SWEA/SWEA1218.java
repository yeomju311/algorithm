/*
 * SWEA 1218 ��ȣ ¦���� D4
 */

import java.util.Scanner;
import java.util.Stack;

public class SWEA1218 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int testCase = 1; testCase <= 10; testCase++) {
			int len = sc.nextInt();
			String s = sc.next();
			Stack st = new Stack();
			for (int i = 0; i < len; i++) {
				char ch = s.charAt(i);
				if (st.isEmpty()) { // ��������� �ִ´�
					st.push(ch);
					continue;
				} else { // ������� ������
					// �˻�
					if (ch == '(' || ch == '[' || ch == '{' || ch == '<') {
						st.push(ch);
						continue;
					}
					if (((char) st.peek() == '(' && ch == ')') || ((char) st.peek() == '[' && ch == ']')
							|| ((char) st.peek() == '{' && ch == '}') || ((char) st.peek() == '<' && ch == '>')) { 																		// ����
						st.pop(); // ¦�� ������ ����
						continue;
					}
					st.push(ch);
				}
			}
			int ans = 0;
			if(st.isEmpty())
				ans = 1;
			else
				ans = 0;
			
			System.out.println("#" + testCase + " " + ans);
		}
	}
}
