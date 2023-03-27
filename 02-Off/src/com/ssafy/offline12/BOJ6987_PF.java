package com.ssafy.offline12;
import java.io.*;
import java.util.*;
/**
 * 
 * @param matches : 국가별 경기 조합
 * @param result : 조별 최종 예선결과
 * @param data : 입력결과
 */
public class BOJ6987_PF {
	static int[][] matches = new int[15][2];
	static int[][] result = new int[6][3];
	static int[][] data = new int[6][3];
	static int ans;
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(
				"5 0 0 3 0 2 2 0 3 0 0 5 4 0 1 1 0 4\r\n" + 
				"4 1 0 3 0 2 4 1 0 1 1 3 0 0 5 1 1 3\r\n" + 
				"5 0 0 4 0 1 2 2 1 2 0 3 1 0 4 0 0 5\r\n" + 
				"5 0 0 3 1 1 2 1 2 2 0 3 0 0 5 1 0 4");
		
		int idx = 0; //임시변수
		for(int i = 0 ; i < 5 ; ++i) {
			for(int j = i + 1; j < 6 ; ++j) {
				matches[idx++] = new int[] {i,j};
			}
		}
		
		
		for(int i = 0 ; i < 4; ++i) {
			
			//아래 코드는 한 번의 게임에 대한 결과
			int score = 0;
			for(int j = 0 ; j < 6 ; ++j) {
				data[j] = new int[] {sc.nextInt(),sc.nextInt(),sc.nextInt()};
				score += data[j][0] + data[j][1]+ data[j][2];
			}
			if(score == 30) {
				dfs_wook(0);
			}
			System.out.print(ans + " ");
			ans = 0;
		}
	}
	private static void dfs_wook(int cnt) {
		if(cnt == 15) {
			ans = 1;
			return; //끝까지 도달
		}
		
		//게임은 승/패, 무/무, 패/무 존재
		int teamA = matches[cnt][0];
		int teamB = matches[cnt][1];
		
		// i = 0  : teamA승/ teamB패
		// i = 1  : teamA무/ teamB무
		// i = 2  : teamA패/ teamB승
		for(int i = 0 ; i < 3 ; ++i) {
			if(data[teamA][i] > 0 && data[teamB][2-i] > 0) {
				data[teamA][i]--;
				data[teamB][2-i]--;
				dfs(cnt+1);
				data[teamA][i]++;
				data[teamB][2-i]++;
			}
		}
		
	}
	
	private static void dfs(int cnt) {
		if(cnt == 15) {
		
				ans=1;
			return;
		}
		
		//게임은 승/패, 무/무, 패/무 존재
		int teamA = matches[cnt][0];
		int teamB = matches[cnt][1];
		
		// i = 0  : teamA승/ teamB패
		// i = 1  : teamA무/ teamB무
		// i = 2  : teamA패/ teamB승
		for(int i = 0 ; i < 3 ; ++i) {
			if(result[teamA][i] +1 <= data[teamA][0]
					&& result[teamB][i] +1 <= data[teamB][1]) {
				//조합에 따른 경기 체크
				result[teamA][i]++;
				result[teamB][2-i]++;
				dfs(cnt+1);
				//다음 for문 (승/패 -> 무/무 -> 패/승) 비교를 위해 원상태로 돌리기
				result[teamA][i]--;
				result[teamB][2-i]--;
			}
		}
		
	}
}
