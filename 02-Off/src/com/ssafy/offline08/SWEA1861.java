package com.ssafy.offline08;
import java.io.*;
import java.util.*;


public class SWEA1861 {
	static int[][] map;
	static boolean[][] visited;
	static int resultRoom, N, RoomCnt;
	static int[][] visitCnt;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {1, -1, 0, 0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		
		for(int tc = 1 ; tc <= TC ; ++tc) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			visited = new boolean[N][N];
			
			for(int i = 0 ; i < N ; ++i) {
				map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
			
			visitCnt = new int[N][N];
			
			for(int i = 0 ; i < N ; ++i) {
				for(int j = 0 ; j < N ; ++j) {
					visited = new boolean[N][N];
					RoomCnt = 0;
					dfs(j,i);
					visitCnt[i][j] = RoomCnt;
				}				
			}
			
			int max = -1;
			int resulti = -1;
			int resultj = -1;
			for(int i = 0 ; i < N ; ++i) {
				for(int j = 0 ; j < N ; ++j) {
					if(max < visitCnt[i][j]) {
						resulti = i;
						resultj = j;
						max = visitCnt[i][j];
					}
				}
				
			}
		
			sb.append("#" + tc + " " +map[resulti][resultj] + " " + max + "\n");
		}
		
		
		System.out.println(sb);
	}
	
	public static void dfs(int x, int y) {
		visited[y][x] = true;
		RoomCnt++;
		
		for(int i = 0 ; i < 4 ; ++i) {
			int nowX = x + dx[i];
			int nowY = y + dy[i];
			if( 0 <= nowX && nowX < N && 0 <= nowY && nowY < N ) {
				if((map[nowY][nowX] == map[y][x] + 1) && !visited[nowY][nowX]) {
					dfs(nowX, nowY);
				}
			}
		}
	}
}
