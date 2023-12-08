package com.ssafy.offline08;

import java.awt.*;
import java.io.*;
import java.util.*;

public class BOJ2178 {
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx =  {1, 0, -1, 0};
	static int[] dy =  {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
		}

		bfs(0, 0);
		System.out.println(map[N-1][M-1]);
	}
	private static void bfs(int startX, int startY){
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(startX, startY));
		visited[startY][startX] = true;

		while(!q.isEmpty()){
			Point now = q.poll();

			for(int i = 0 ; i < 4 ; ++i){
				int nextX = now.x + dx[i];
				int nextY = now.y + dy[i];

				//좌표 유효성 검사
				if(!checkRange(nextX, nextY)) continue;
				if(map[nextY][nextX] == 0) continue;
				if(visited[nextY][nextX]) continue;

				visited[nextY][nextX] = true;
				q.offer(new Point(nextX, nextY));
				map[nextY][nextX] =map[now.y][now.x] + 1;
			}
		}
	}

	private static boolean checkRange(int x, int y){
		return (0 <= x && x < M && 0 <= y && y < N);
	}
}

