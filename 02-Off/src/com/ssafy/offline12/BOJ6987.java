package com.ssafy.offline12;
import java.io.*;
import java.util.*;

public class BOJ6987 {
	static int[][] score;
	static int matchCNT = 0;
	static int[] matches;
	static int[] team = {0, 1, 2, 3, 4, 5};
	static List<int[]> gamelist = new ArrayList<>();
	static boolean possible;
	//게임은 6개국에서 15판,
	//승리 조건은 승/패, 무/무, 패/승
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		score = new int[6][3];
		matches = new int[2];
		
		getMatches(0,0);
		
		tc :
		for(int tc = 0 ; tc < 4 ; ++tc) {
			possible = false;
			st = new StringTokenizer(br.readLine());
			
			for(int i = 0 ; i < 6 ; ++i) {
				int sum = 0;
				for(int j = 0 ; j < 3 ; ++j) {
					score[i][j] = Integer.valueOf(st.nextToken());
					sum += score[i][j];
				}
				if(sum != 5) {
					sb.append(0 + " ");
					continue tc;
				}
			}
			
			backTracking(0);
			
			if(possible) sb.append(1 + " ");
			else sb.append(0 + " ");
			
		}
		System.out.println(sb.toString());
	}
	static void getMatches(int cnt, int start) {
		if(cnt == 2) {
			gamelist.add(new int[]{matches[0], matches[1]});
			matchCNT++;
			return;
		}
		
		for(int i = start ; i < 6 ; ++i) {
			matches[cnt] = team[i];
			getMatches(cnt+1, i + 1);
		}
	}
	
	static void backTracking(int cnt) {
		if(possible) return;
		
		if(cnt == matchCNT) {//끝까지 도달했으면 해당 월드컵은 수행 가능
			possible = true;
			return;
		}
		
		int teamA = gamelist.get(cnt)[0];
		int teamB = gamelist.get(cnt)[1];
		
		//게임 승/패 -score[A][0] / score[B][2] 
		//게임 무/무 - score[A][1] / score[B][1]
		//게임 패/승 - score[A][2] / score[B][0]
		for(int i = 0 ; i < 3 ; ++i) {
			if(score[teamA][i] > 0 && score[teamB][(3-1)-i] > 0) {
				score[teamA][i]--;
				score[teamB][2-i]--;
				backTracking(cnt+1);
				score[teamA][i]++;
				score[teamB][2-i]++;
			}
		}
		
	}
	
}
