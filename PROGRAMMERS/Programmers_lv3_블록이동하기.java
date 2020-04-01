/*
 * 프로그래머스 블록 이동하기 레벨3
 */

import java.util.*;


/*
회전은 한쪽점 중심잡지말구
윗공간 남을때 2가지경우
밑공간 2가지 이렇게 하는게 더
분류화가 쉬움
- 김기훈 0402 조언해주다
 */

public class Programmers_lv3_블록이동하기 {
	public static void main(String[] args) {
		int[][] arr = {{0, 0, 0, 1, 1},{0, 0, 0, 1, 0},{0, 1, 0, 1, 1},{1, 1, 0, 0, 1},{0, 0, 0, 0, 0}};
		System.out.println(solution(arr));
	}
	
    public static int solution(int[][] board) {
        int answer = 0;
        int len = board.length;
        
        boolean[][][] visited = new boolean[len][len][2]; // 0 가로 1 세로
        
        Queue<int[]> q = new LinkedList<int[]>();
        visited[0][0][0] = true;
        q.offer(new int[] {0, 0, 0, 0}); // col, row, 가로/세로, cnt
        
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            //System.out.println(cur[0] + " , " + cur[1] + " , " + cur[2] + " , " + cur[3]);
            if(cur[2] == 0 && cur[0] == len-1 && cur[1]+1 == len-1) { // 가로일 때 도착
                answer = cur[3];
                break;
            }
            if(cur[2] == 1 && cur[0]+1 == len-1 && cur[1] == len-1) { // 세로일 때 도착
                answer = cur[3];
                break;
            }
            
            // 현재 가로일때
            if(cur[2]== 0) {
                // 이동
                // 상
                if(isLen(cur[0]-1, len) && board[cur[0]-1][cur[1]]==0 && board[cur[0]-1][cur[1]+1]==0 && !visited[cur[0]-1][cur[1]][0]) {
                    visited[cur[0]-1][cur[1]][0] = true;
                    q.offer(new int[] {cur[0]-1, cur[1], 0, cur[3]+1}); //여기까지 클리어.
                }
                // 하
                if(isLen(cur[0]+1, len) && board[cur[0]+1][cur[1]]==0 && board[cur[0]+1][cur[1]+1]==0 && !visited[cur[0]+1][cur[1]][0]) {
                    visited[cur[0]+1][cur[1]][0] = true;
                    q.offer(new int[] {cur[0]+1, cur[1], 0, cur[3]+1}); //여기도 클리어.
                }        
                // 우
                if(isLen(cur[1]+2, len) && board[cur[0]][cur[1]+2] == 0 && !visited[cur[0]][cur[1]+1][0]) {
                    visited[cur[0]][cur[1]+1][0] = true;
                    q.offer(new int[] {cur[0], cur[1]+1, 0, cur[3]+1}); //여기도 클리어.
                }
                // 좌
                if(isLen(cur[1]-1, len) && board[cur[0]][cur[1]-1] == 0 && !visited[cur[0]][cur[1]-1][0]) {
                    visited[cur[0]][cur[1]-1][0] = true;
                    q.offer(new int[] {cur[0], cur[1]-1, 0, cur[3]+1}); //여기도 클리어.
                }
                
                // 회전
                // 왼쪽을 축으로
                // 시계방향
                if(isLen(cur[0]+1, len) && board[cur[0]+1][cur[1]]==0 && board[cur[0]+1][cur[1]+1]==0 && !visited[cur[0]][cur[1]][1]) {
                    visited[cur[0]][cur[1]][1] = true;
                    q.offer(new int[] {cur[0], cur[1], 1, cur[3]+1});
                }
                // 반시계방향
                if(isLen(cur[0]-1, len) && board[cur[0]-1][cur[1]]==0 && board[cur[0]-1][cur[1]+1]==0 && !visited[cur[0]-1][cur[1]][1]) {
                    visited[cur[0]-1][cur[1]][1] = true;
                    q.offer(new int[] {cur[0]-1, cur[1], 1, cur[3]+1}); //여기까지도 됨.
                }
                // 오른쪽을 축으로
                // 반시계방향
                if(isLen(cur[0]+1, len) && board[cur[0]+1][cur[1]]==0 && board[cur[0]+1][cur[1]+1]==0 && !visited[cur[0]][cur[1]+1][1]) {
                    visited[cur[0]][cur[1]+1][1] = true;
                    q.offer(new int[] {cur[0], cur[1]+1, 1, cur[3]+1});
                }
                
                // 시계방향
                if(isLen(cur[0]-1, len) && board[cur[0]-1][cur[1]]==0 && board[cur[0]-1][cur[1]+1]==0 && !visited[cur[0]-1][cur[1]+1][1]) {
                    visited[cur[0]-1][cur[1]+1][1] = true;
                    q.offer(new int[] {cur[0]-1, cur[1]+1, 1, cur[3]+1});
                }
            } //가로는 완벽.
            // 현재 세로일때
            else if(cur[2] == 1) {
                // 이동
                // 상
                if(isLen(cur[0]-1, len) && board[cur[0]-1][cur[1]]==0 && !visited[cur[0]-1][cur[1]][1]) {
                    visited[cur[0]-1][cur[1]][1] = true;
                    q.offer(new int[] {cur[0]-1, cur[1], 1, cur[3]+1});
                }
                
                // 하
                if(isLen(cur[0]+2, len) && board[cur[0]+2][cur[1]]==0 && !visited[cur[0]+1][cur[1]][1]) {
                    visited[cur[0]+1][cur[1]][1] = true;
                    q.offer(new int[] {cur[0]+1, cur[1], 1, cur[3]+1});
                }
                
                // 우
                if(isLen(cur[1]+1, len) && board[cur[0]][cur[1]+1]==0 && board[cur[0]+1][cur[1]+1]==0 && !visited[cur[0]][cur[1]+1][1]) {
                    visited[cur[0]][cur[1]+1][1] = true;
                    q.offer(new int[] {cur[0], cur[1]+1, 1, cur[3]+1});
                }
                
                // 좌
                if(isLen(cur[1]-1, len) && board[cur[0]][cur[1]-1]==0 && board[cur[0]+1][cur[1]-1]==0 && !visited[cur[0]][cur[1]-1][1]) {
                    visited[cur[0]][cur[1]-1][1] = true;
                    q.offer(new int[] {cur[0], cur[1]-1, 1, cur[3]+1});
                }
                
                // 회전
                // 위쪽을 축으로
                // 반시계
                if(isLen(cur[1]+1, len) && board[cur[0]][cur[1]+1]==0 && board[cur[0]+1][cur[1]+1]==0 && !visited[cur[0]][cur[1]][0]) {
                    visited[cur[0]][cur[1]][0] = true;
                    q.offer(new int[] {cur[0], cur[1], 0, cur[3]+1});
                }
                // 시계
                if(isLen(cur[1]-1, len) && board[cur[0]][cur[1]-1]==0 && board[cur[0]+1][cur[1]-1]==0 && !visited[cur[0]][cur[1]-1][0]) {
                    visited[cur[0]][cur[1]-1][0] = true;
                    q.offer(new int[] {cur[0], cur[1]-1, 0, cur[3]+1});
                }
                
                // 아래쪽을 축으로
                // 시계
                if(isLen(cur[1]+1, len) && board[cur[0]+1][cur[1]+1]==0 && board[cur[0]][cur[1]+1]==0 && !visited[cur[0]+1][cur[1]][0]) {
                    visited[cur[0]+1][cur[1]][0] = true;
                    q.offer(new int[] {cur[0]+1, cur[1], 0, cur[3]+1});
                }
                // 반시계
                if(isLen(cur[1]-1, len) && board[cur[0]+1][cur[1]-1]==0 && board[cur[0]][cur[1]-1]==0 && !visited[cur[0]+1][cur[1]-1][0]) {
                    visited[cur[0]+1][cur[1]-1][0] = true;
                    q.offer(new int[] {cur[0]+1, cur[1]-1, 0, cur[3]+1});
                }
            }
        }

        return answer;
    }
    
    public static boolean isLen(int n, int len) {
        if(n>=0 && n<len) return true;
        return false;
    }

}
