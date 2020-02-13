/*
 * SWEA 4408 �ڱ� ������ ���ư��� D4
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Student{
	int start; // current room
	int end; // origin room
	public Student (int start, int end) {
		// Ȧ��, ¦���� �����ؼ� ���� �־������
		int tmpS = start;
		int tmpE = end;
		if(start>end) {
			tmpS = end;
			tmpE = start;
		}
		if(tmpS%2==0) { // ¦����
			tmpS = tmpS/2;
		} else { // Ȧ����
			tmpS = tmpS/2 + 1;
		}
		if(tmpE%2==0) {
			tmpE = tmpE/2;
		} else {
			tmpE = tmpE/2 + 1;
		}
		this.start = tmpS;
		this.end = tmpE;
	}
}
public class SWEA4408 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int TC = Integer.parseInt(st.nextToken());
		for (int testCase = 1; testCase <= TC; testCase++) {
//			int[] room = new int[401];
			st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
//			System.out.println(n);
//			int[][] student = new int[n][2];
			ArrayList<Student> list = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int current = Integer.parseInt(st.nextToken()); // ���� ��
				int origin = Integer.parseInt(st.nextToken()); // ���ư� ��
				list.add(new Student(current, origin));
			}

			
			int[] room = new int[201];
			for (int i = 0; i < list.size(); i++) {
				int start = list.get(i).start;
				int end = list.get(i).end;
//				System.out.println(i);
				for (int j = start; j <= end; j++) {
					room[j]++;
				}
			}
			Arrays.sort(room);
//			System.out.println(Arrays.toString(room));
			
			System.out.println("#" + testCase + " " + room[200]);
		}
	}
}
