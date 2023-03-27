package com.ssafy.offline03;

import java.io.*;
import java.util.*;


public class SWEA1954 {
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int[][] map;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		int TC = Integer.parseInt(br.readLine());
		for(int tc = 1 ; tc <= TC ; ++tc) {
			int N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			
			snail(N);
			sb.append("#" + tc + "\n");
			print(N);
		}
		System.out.println(sb);
	}
	
	public static void snail(int n) {
		int cnt = 1;
		int direction = 0;

		int x = 0;
		int y = 0;
		map[y][x] = 1;
		while(cnt != n * n) {
			
			x += dx[direction%4];
			y += dy[direction%4];
			
			if( 0 <= x && x < n && 0 <= y && y < n && map[y][x] == 0) {
				map[y][x] = ++cnt;
			}else {
				x -= dx[direction%4];
				y -= dy[direction%4];
				direction++;
			}
		}
	}
	
	public static void print(int n) {
		for(int i = 0 ; i < n ; ++i) {
			for(int j = 0 ; j < n ; ++j) {
				sb.append(map[i][j] + " ");
			}	
			sb.append("\n");
		}
	}
}
