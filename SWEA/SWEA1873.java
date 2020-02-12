/*
 * SWEA 1873 ��ȣ�� ��Ʋ�ʵ� D3
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1873 {
	static int[] dc = {-1, 1, 0, 0}; // �����¿�
	static int[] dr = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int TC = Integer.parseInt(st.nextToken());
		for (int testCase = 1; testCase <= TC; testCase++) {
			st = new StringTokenizer(br.readLine(), " ");
			int H = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			char[][] map = new char[H][W];
			int[] tank = new int[3]; // ������ y,x ��ǥ �� ���� ����
			for (int i = 0; i < map.length; i++) {
				st = new StringTokenizer(br.readLine());
				String s = st.nextToken();
				for (int j = 0; j < map[i].length; j++) {
					map[i][j] = s.charAt(j);
					if(map[i][j]=='^' || map[i][j]=='v' || map[i][j]=='<' || map[i][j]=='>') { // ���� ��ġ ����
						tank[0] = i;
						tank[1] = j;
						tank[2] = map[i][j];
					}
				}
			}
			
			st = new StringTokenizer(br.readLine());			
			st = new StringTokenizer(br.readLine());
			String orders = st.nextToken();
			
			for (int i = 0; i < orders.length(); i++) {
				// ��ɾ� UDLRS
				char order = orders.charAt(i);
				if(order == 'U') {
					tank[2] = '^';
					map[tank[0]][tank[1]] = '^';
					int ny = tank[0]-1;
					if(ny>=0 && map[ny][tank[1]] == '.') {
						map[tank[0]][tank[1]] = '.'; // ���� �ִ� �ڸ� ������ �ٲٰ�
						map[ny][tank[1]] = '^'; // ��ũ �־��ְ�
						tank[0] -= 1; // ��ũ �̵� �����ش�
					}
				} else if(order == 'D') {
					tank[2] = 'v';
					int ny = tank[0]+1;
					map[tank[0]][tank[1]] = 'v';
					if(ny<map.length && map[ny][tank[1]] == '.') {
						map[tank[0]][tank[1]] = '.'; // ���� �ִ� �ڸ� ������ �ٲٰ�
						map[ny][tank[1]] = 'v'; // ��ũ �־��ְ�
						tank[0] += 1; // ��ũ �̵� �����ش�
					}
				} else if(order == 'L') {
					tank[2] = '<';
					map[tank[0]][tank[1]] = '<';
					int nx = tank[1]-1;
					if(nx>=0 && map[tank[0]][nx] == '.') {
						map[tank[0]][tank[1]] = '.'; // ���� �ִ� �ڸ� ������ �ٲٰ�
						map[tank[0]][nx] = '<'; // ��ũ �־��ְ�
						tank[1] -= 1; // ��ũ �̵� �����ش�
					}
				} else if(order == 'R') {
					tank[2] = '>';
					map[tank[0]][tank[1]] = '>';
					int nx = tank[1]+1;
					if(nx<map[0].length && map[tank[0]][nx] == '.') {
						map[tank[0]][tank[1]] = '.'; // ���� �ִ� �ڸ� ������ �ٲٰ�
						map[tank[0]][nx] = '>'; // ��ũ �־��ְ�
						tank[1] += 1; // ��ũ �̵� �����ش�
					}
				} else if(order == 'S') {
					if(tank[2] == '>') {
						for (int k = tank[1]; k < map[0].length; k++) {
							if(map[tank[0]][k] == '*') {
								map[tank[0]][k] = '.';
								break;
							} else if(map[tank[0]][k] == '#') {
								break;
							}
						}
					} else if(tank[2] == 'v') {
						for (int k = tank[0]; k < map.length; k++) {
							if(map[k][tank[1]] == '*') {
								map[k][tank[1]] = '.';
								break;
							} else if(map[k][tank[1]] == '#') {
								break;
							}
						}
					} else if(tank[2] == '<') {
						for (int k = tank[1]; k >= 0; k--) {
							if(map[tank[0]][k] == '*') {
								map[tank[0]][k] = '.';
								break;
							} else if(map[tank[0]][k] == '#') {
								break;
							}
						}
					} else if(tank[2] == '^') {
						for (int k = tank[0]; k >= 0; k--) {
							if(map[k][tank[1]] == '*') {
								map[k][tank[1]] = '.';
								break;
							} else if(map[k][tank[1]] == '#') {
								break;
							}
						}
					}
				}
				
//				System.out.println("#" + i + " " + order);
//				for (int l = 0; l < map.length; l++) {
//					for (int m = 0; m < map[l].length; m++) {
//						System.out.print(map[l][m]);
//					}
//					System.out.println();
//				}
			} // end of for
			
			System.out.print("#" + testCase + " ");
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[i].length; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
		}
	}
}
