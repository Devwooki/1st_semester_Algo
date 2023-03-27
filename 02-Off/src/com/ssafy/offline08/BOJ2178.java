package com.ssafy.offline08;

import java.io.*;
import java.util.*;


class Point{
	public int x;
	public int y;
	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}
}
public class BOJ2178 {
	static int N, M, cnt = 0;
	static int[][] map, dfsDistance;
	static boolean[][] visited;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		dfsDistance = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i = 0 ; i < N ; ++i) {
			String str = br.readLine();
			for(int j = 0 ; j < M ; ++j) {
				map[i][j] = str.charAt(j) - '0';
				dfsDistance[i][j] = str.charAt(j) - '0';
			}
		}
		
		bfs(0,0);
		System.out.println(map[N-1][M-1]);
	
	}
	static void bfs(int x, int y) {
		Queue<Point> q = new LinkedList<>();
		Point p = new Point(x,y);
		
		q.add(p);
		visited[y][x] = true;
		while(!q.isEmpty()) {
			Point now = q.poll();
			
			for(int i = 0 ; i < 4 ; ++i) {
				int nowX = now.x + dx[i];
				int nowY = now.y + dy[i];
				if(0 <= nowX && nowX < M && 0 <= nowY && nowY < N) {
					if(!visited[nowY][nowX] && map[nowY][nowX] != 0) {
						visited[nowY][nowX] = true;
						
						//cnt++; 큐에서 꺼내 위치가 이동될 때 마다 cnt가 증가하므로 일정하지 못함.. maze마다 누적시켜야됌
						map[nowY][nowX] = map[now.y][now.x] + 1;
						q.add(new Point(nowX, nowY));
						
					}

				}
			}
		}
	}
}
