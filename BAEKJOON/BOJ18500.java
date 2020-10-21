/*
 * BOJ 18500 �̳׶� 2
 * https://www.acmicpc.net/problem/18500
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ18500 {
	static int R, C, N;
	static char[][] map;
	static int[][] dir = {{0,1}, {0,-1}, {1,0}, {-1,0}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			map[i] = str.toCharArray();
		}
		N = Integer.parseInt(br.readLine().trim());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int height = R - Integer.parseInt(st.nextToken());
			if (i % 2 == 0) {
				left(height);
			} else {
				right(height);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				sb.append(map[i][j]);
			}
			sb.append('\n');
		}
		System.out.print(sb);
	}
	
	public static void left(int h) {
		for (int i = 0; i < C; i++) {
			if (map[h][i] == 'x') {
				map[h][i] = '.';
				findCluster(h, i);
				return;
			}
		}
	}
	
	public static void right(int h) {
		for (int i = C-1; i >= 0; i--) {
			if (map[h][i] == 'x') {
				map[h][i] = '.';
				findCluster(h, i);
				return;
			}
		}
	}
	
	public static void findCluster(int x, int y) {
		// ����� �̳׶� �ֺ��� ���߿� ���ִ� Ŭ�����Ͱ� �ִ��� ã�´�
		for (int i = 0; i < 4; i++) {
			int nx = x + dir[i][0];
			int ny = y + dir[i][1];
			if (nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
			if (map[nx][ny] == 'x') bfs(nx, ny);
		}
	}
	
	static boolean[][] visit;
	public static void bfs(int x, int y) {
		visit = new boolean[R][C];
		boolean flag = false; // Ŭ�����Ͱ� ���߿� ���ִ��� �ƴ��� -> Ŭ������ �Ϻ� �� �ϳ��� ���� ��Ҵٸ� true
		List<int[]> list = new ArrayList<>(); // Ŭ�������� ��ǥ ����
		Queue<int[]> q = new LinkedList<>();
		visit[x][y] = true;
		q.offer(new int[] {x, y});
		list.add(new int[] {x, y});
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			if (cur[0] == R-1) flag = true;
			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + dir[i][0];
				int ny = cur[1] + dir[i][1];
				if (nx < 0 || nx >= R || ny < 0 || ny >= C || 
						visit[nx][ny] || map[nx][ny] == '.') continue;
				visit[nx][ny] = true;
				q.offer(new int[] {nx, ny});
				list.add(new int[] {nx, ny});
			}
		}
		
		if (!flag) fallCluster(list);
	}
	
	public static void fallCluster(List<int[]> list) {
		for (int i = 0; i < list.size(); i++) {
			int[] cur = list.get(i);
			map[cur[0]][cur[1]] = '.';
		}
		
		int min = R; // Ŭ�����Ͱ� ������ �� �ִ� �ּ� ����
		// 1. Ŭ�����Ͱ� ������ �ּ� ���̸� �����ش�
		for (int i = 0; i < list.size(); i++) {
			int[] cur = list.get(i);
			int x = cur[0];
			int y = cur[1];
			
			for (int j = x; j < R; j++) {
				// �ٸ� Ŭ�����Ϳ� ���� ���� ���� ���� �� �̵� �Ÿ� ���� �ٸ���
				if ((map[j][y] == 'x' && !visit[j][y])) {
					int tmp = Math.abs(j - x) - 1;
					if (min > tmp) min = tmp;
				} else if (j == R-1) {
					int tmp = Math.abs(j - x);
					if (min > tmp) min = tmp;
				}
			}
		}
				
		// 2. �ּ� ���̸�ŭ Ŭ�����͸� ����߷��ش�
		for (int i = 0; i < list.size(); i++) {
			int[] cur = list.get(i);
			int x = cur[0] + min;
			int y = cur[1];
			map[x][y] = 'x';
		}
	}
}
