package com.ssafy.offline14;

//구격은 같은 색으로 이뤄져있고
//상하좌우가 인접한 경우 같은 구역에 속함
/*
RRRBB
GGBBB
BBBRR
BBRRR
RRRRR
정상인 : 구역 4개 R2, B1, G1
적록 : 파랑-초록2, 파랑 1
 */
import java.io.*;
import java.util.*;

public class BOJ10026 {
	static char[][] grid;
	static boolean[][] visitedNormal, visitedRG;
	static int N;
	static int[] result;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		grid = new char[N][N];
		result = new int[2];
		for(int i = 0 ; i < N ; ++i) {
			grid[i] = br.readLine().toCharArray();
		}
		
		visitedNormal = new boolean[N][N];
		visitedRG = new boolean[N][N];
		for(int i = 0 ; i < N ; ++i) {
			for(int j = 0 ; j < N ; ++j) {
				if(!visitedNormal[i][j]) {
					normal(j, i);
				}
				if(!visitedRG[i][j]) {
					gr(j, i);
				}
			}
		}
		
		System.out.printf("%d %d", result[0], result[1]);
	}
	static void normal(int x, int y) {
		int[] dx = {0, 1, 0, -1};
		int[] dy = {1, 0, -1, 0};
		Queue<int[]> q = new ArrayDeque();
		q.offer(new int[] {x,y} );
		
		char base = grid[y][x];
		
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			for(int i = 0 ; i < 4 ; ++i) {
				int nx = temp[0] + dx[i];
				int ny = temp[1] + dy[i];
				
				if( nx < 0 || ny < 0 || nx == N || ny == N) continue;
				if(visitedNormal[ny][nx] || grid[ny][nx] != base) continue;
				
				visitedNormal[ny][nx] = true;
				q.offer(new int[] {nx, ny});
			}
		}
		result[0]++;
	}
	static void gr(int x, int y) {
		int[] dx = {0, 1, 0, -1};
		int[] dy = {-1, 0, 1, 0};
		Queue<int[]> q = new ArrayDeque();
		q.offer(new int[] {x,y} );
		
		boolean isRG = false;
		char base = grid[y][x];
		if(base == 'R' || base == 'G') isRG = true;
	
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			for(int i = 0 ; i < 4 ; ++i) {
				int nx = temp[0] + dx[i];
				int ny = temp[1] + dy[i];
				
				if( nx < 0 || ny < 0 || nx == N || ny == N) continue;
				

				if(isRG) {
					if(visitedRG[ny][nx] || grid[ny][nx] == 'B') continue;
					visitedRG[ny][nx] = true;
					q.offer(new int[] {nx, ny});
				}else {
					if(visitedRG[ny][nx] || (grid[ny][nx] == 'R' || grid[ny][nx] == 'G' ) ) continue;
					visitedRG[ny][nx] = true;
					q.offer(new int[] {nx, ny});
				}
			}
		}
		result[1]++;
	}
	
	
}
