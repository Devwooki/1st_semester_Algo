package com.ssafy.offline14;
import java.awt.Point;
import java.io.*;
import java.util.*;

public class BOJ2667 {
	static int N, cnt;
	static int[][] map;
	static boolean[][] visited;
	static ArrayList<Integer> result;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		visited = new boolean[N][N];
		result = new ArrayList<>();
		for(int i = 0; i < N; ++i) {
			String temp = br.readLine();
			for(int j = 0; j < N; ++j) {
				map[i][j] = (int)temp.charAt(j) - '0';
			}
		}
		
		for(int i = 0; i < N; ++i) {
			for(int j = 0; j < N; ++j) {
				if(map[i][j] == 1 && !visited[i][j]) {
					bfs(j , i);
				}
			}
		}
		
		Collections.sort(result);
		
		sb.append(result.size() + "\n");
		for(int value : result) {
			sb.append(value + "\n");
		}
		System.out.println(sb.toString());
	}
	static void bfs(int x , int y) {
		int[] dx = {0, 1, 0, -1};
		int[] dy = {-1, 0, 1, 0};
		Queue<Point> q = new ArrayDeque<>();
		q.offer(new Point(x,y));
		int cnt = 1;
		visited[y][x] = true;
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			for(int i = 0 ; i < dx.length ; ++i) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				if( nx < 0 || ny < 0 || nx == N || ny == N || visited[ny][nx]) continue;
				
				if(map[ny][nx] == 1 && ! visited[ny][nx]) {
					visited[ny][nx] = true;
					cnt++;
					q.offer(new Point(nx, ny));
				}
								
			}
		}
		result.add(cnt);
	}
}
