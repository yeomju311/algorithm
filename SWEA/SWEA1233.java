/*
 * SWEA 1233 ��Ģ���� ��ȿ�� �˻� D4
 */

import java.util.Scanner;

public class SWEA1233 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int testCase = 1; testCase <= 10; testCase++) {
			int ans = 1; // ��ȿ�ϴٸ� 1, �ƴϸ� 0
			int N = sc.nextInt(); // ��� ����
			sc.nextLine();
			String[] tree = new String[N+1];
			for (int i = 1; i < N+1; i++) {
				String str = sc.nextLine();
				String[] strArr = str.split(" ");
				int node = Integer.parseInt(strArr[0]);
				tree[node] = strArr[1];
			}
			// ���ܳ�忡 �����ڰ� ������ ��ȿ���� �ʴ�
//			System.out.println(tree[1].equals("-"));
			for (int i = (N/2)+1; i < N+1; i++) {
//				System.out.println("i : " + i);
				if(tree[i].equals("+") || tree[i].equals("-") || tree[i].equals("*") || tree[i].equals("/")) {
//					System.out.println("if");
					ans = 0;
					break;
				}
			}
			
			System.out.println("#" + testCase + " " + ans);
		}
	}
}
