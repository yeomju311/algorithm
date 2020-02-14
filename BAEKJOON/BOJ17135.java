/*
 * BOJ 17135 ĳ�� ���潺
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ17135 {
	static int N, M, D;
	static int[][] map;
	static boolean[] used; // �ü� ��ġ ���� �� �� ��
	static List<Enemy> enemy = new ArrayList<>();
	static int ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // ��
		M = Integer.parseInt(st.nextToken()); // ��
		D = Integer.parseInt(st.nextToken()); // �ü��� ���� �Ÿ� ����
		map = new int[N+1][M];
		for (int i = 0; i < map.length-1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()); // 0�� �� ĭ, 1�� ��
				if(map[i][j] == 1) {
					enemy.add(new Enemy(i, j, false));
				}
			}
		}
		used = new boolean[M]; 
//		for (int i = 0; i < map[N].length; i++) {
//			map[N][i] = 2; // ��
//		}
		
//		for (int i = 0; i < map.length; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
		
		ans = Integer.MIN_VALUE; // �ü��� �������� ������ �� �ִ� ���� �ִ� ��
		// �ü� 3���� ��ġ�Ѵ�
		pick(0, 0);
		// 3�� ��ġ �Ϸ�Ǹ� ���� ����
		// 1�� - �ü��� ����. ���ݹ��� ���� �����
		// 		���� �Ʒ��� �� ĭ �̵�. ���� ���ų� ������ ����� �� ���� �����
		// ��� ���� ���ܵǸ� ���� ��
		// �ü��� �������� ������ �� �ִ� ���� �ִ� �� ���
		System.out.println(ans);
	}
	
	public static void pick(int idx, int cnt) { // idx : ��ġ�� �ε���, cnt : ��ġ�� ����-> �ü��� �� �� ��
		List<Archer> archer = new ArrayList<>();
		if(cnt == 3) { // �ü��� �� ���̴ϱ�
			for (int i = 0; i < M; i++) {
				if(used[i]) {
					archer.add(new Archer(N, i));
//					System.out.print(i); // �� ���� ������ �� �ִ� ��ġ
				}
			}
			game(archer);
//			System.out.println();
		}
		for (int i = idx; i < M; i++) {
			if(!used[i]) {
				used[i] = true;
				pick(i, cnt+1);
				used[i] = false;
			}
		}
	}
	
	public static void game(List<Archer> archer) {
//		System.out.println("archer");
//		for (int i = 0; i < archer.size(); i++) {
//			System.out.println("y : "+archer.get(i).y + " x : " + archer.get(i).x);
//		}
//		System.out.println("enemy");
//		for (int i = 0; i < enemy.size(); i++) {
//			System.out.println("y : "+enemy.get(i).y + " x : " + enemy.get(i).x);
//		}
		List<Enemy> en = new ArrayList<>();
		for (int i = 0; i < enemy.size(); i++) {
			en.add(new Enemy(enemy.get(i)));
		}
		
//		en = enemy;
		int count = 0; // ���� ���� ��
		while(!en.isEmpty()) { // ���� ������������ ������ ����ȴ�
			// �ü��� ���� �Ÿ� ���� �ִ� ���� ����� ���� ����
			for (int i = 0; i < archer.size(); i++) {
				int minDist = D+1;
				int minY = N+1, minX = M; // ���� �� ��ǥ ����
				int minIdx = en.size(); // ���� ���� �ε���
				for (int j = 0; j < en.size(); j++) { // ���� �Ÿ��� ��
					int dist = Math.abs(archer.get(i).y-en.get(j).y) + Math.abs(archer.get(i).x-en.get(j).x);
					// �ü��� ���� �ű� �Ŷ� �ü��� y��ǥ�� ���� y��ǥ���� �׻� Ŀ�ߵ�
					if(dist <= D /* && archer.get(i).y > en.get(j).y */) { // ���� �Ÿ� ���� ������
						// �ٵ� �Ÿ� ���� �ִ� �� �߿��� �ּҰŸ��� �� ã�ƾ���
						if(minDist > dist) {
							minDist = dist;
							minX = en.get(j).x;
							minY = en.get(j).y;
							minIdx = j;
						} else if(dist == minDist) { // �ּҰŸ��� ������ ���� ���ʿ� �ִ� �ַ� �Ѵ�.
							if(minX > en.get(j).x) {
								minX = en.get(j).x;
								minY = en.get(j).y;
								minIdx = j;
							} 
//							else {
//								minX = en.get(j).x;
//								minY = en.get(j).y;
//								minIdx = j;
//							}
						}
					}
				} // �� �ü��� ��� ���� �Ÿ� �� ��
//				System.out.println("minIdx " + minIdx);
				if(minIdx < en.size())
					en.get(minIdx).isDead = true;
			} // �ü� ���� ��
			for (int i = 0; i < en.size(); i++) {
				if(en.get(i).isDead) {
					en.remove(i);
					count++;
					i--;
				}
			}

			// �� �Ʒ��� �̵� -> �ü��� ���� �̵�
			for (int i = 0; i < archer.size(); i++) {
				archer.get(i).y -= 1;
			}
			for (int i = 0; i < en.size(); i++) {
				if(en.get(i).y >= archer.get(0).y) {
					en.remove(i); 
					i--;
				}
			}
		}
		// ������ �� ������ �ִ�� ���� ���� ans�� ����
//		System.out.println("count: " + count);
//		System.out.println("ans : "+ans);
		ans = Math.max(ans, count);
	}
}


class Archer { // �ü��� ��ǥ ����
	int x;
	int y;
	public Archer(int y, int x) {
		this.y = y;
		this.x = x;
	}
}

class Enemy { // ���� ��ǥ ����
	int x;
	int y;
	boolean isDead; // �׾����� Ȯ�� -> �ѹ��� �ߺ� ������ �����ϹǷ� ������. �ϴ� �׾��� ǥ���ϰ� �Ѳ����� remove�Ϸ���
	public Enemy(int y, int x, boolean isDead) {
		this.y = y;
		this.x = x;
		this.isDead = isDead;
	}
	public Enemy(Enemy enemy) {
		this.y = enemy.y;
		this.x = enemy.x;
		this.isDead = enemy.isDead;
	}
	public void setDead(int y, int x, boolean dead) {
		if(y == this.y && x == this.x) {
			isDead = dead;
		}
	}
	
}