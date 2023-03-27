package com.ssafy.offline13;
import java.awt.Point;
import java.io.*;
import java.util.*;


public class BOJ7576 {
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static int[][] box;
	static int N, M;
	static int result = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		box = new int[M][N];
		
		int cnt1 = 0;
		for(int i = 0 ; i < M ; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N ; ++j) {
				box[i][j]= Integer.parseInt(st.nextToken());
				if(box[i][j]==1) cnt1++;
			}
		}
		
		if(cnt1 == N * M) {
			System.out.println(0);
			return;
		}else {
			bfs();
			System.out.println(check());
		}
	}
	
	static void bfs() {
		Queue<Point> q = new ArrayDeque<>();
		
		for(int i = 0 ; i < M ; ++i) {
			for(int j = 0 ; j < N ; ++j) {
				if(box[i][j] == 1) q.add(new Point(j, i));
			}
		}
		
		//================================================
		while(!q.isEmpty()) {
			Point p = q.poll();
			
			for(int i = 0 ; i < 4 ; ++i) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				if( nx < 0 || ny < 0 || nx == N || ny == M) continue;
				
				if(box[ny][nx]  == 0) {
					box[ny][nx] = box[p.y][p.x] + 1;
					q.add(new Point(nx, ny)); 
				}
			}
//			for(int i = 0 ; i < M ; ++i) {
//				System.out.println(Arrays.toString(box[i]));
//			}
		}
		
	}
	
	static int check() {
		int max = Integer.MIN_VALUE;
		for(int i = 0 ; i < M ; ++i) {
			for(int j = 0 ; j < N ; ++j) {
				if(box[i][j] == 0) return -1;
				max = Math.max(box[i][j], max);
			}
		}
		return max-1;
	}
	
}
