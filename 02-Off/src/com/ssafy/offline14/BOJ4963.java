package com.ssafy.offline14;
import java.awt.Point;
import java.io.*;
import java.util.*;

public class BOJ4963 {
	static int w, h;
	static int[][] map;
	static boolean[][] visited;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			int cntLand = 0;
			if(w == 0 && h == 0) {
				System.out.println(sb);
				return;
			}
			
			map = new int[h][w];
			visited = new boolean[h][w];
			for(int i = 0 ; i < h ; ++i) 
				map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			
			for(int i = 0 ; i < h ; ++i) {
				for(int j = 0 ; j < w ; ++j) {
					if(visited[i][j]) continue;
					if(map[i][j] == 1) {
						bfs(j,i);
						cntLand++;
					}
				}
			}
			
			sb.append(cntLand + "\n");
		}
	}
	
	static void bfs(int x, int y) {
		Queue<Point> q = new ArrayDeque<>();
		q.offer(new Point(x,y));
		int[] dx = {0,1,1,1,0,-1,-1,-1};
		int[] dy = {-1,-1,0,1,1,1,0,-1};
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			for(int i = 0 ; i < dx.length ; ++i) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if( nx < 0 || ny < 0 || nx == w || ny == h) continue;
				if( visited[ny][nx] || map[ny][nx] != map[y][x]) continue;
				
				visited[ny][nx] = true;
				q.offer(new Point(nx, ny));
			}
		}
		
	}
}
