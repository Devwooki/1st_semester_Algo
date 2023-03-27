package com.ssafy.offline15;

import java.io.*;
import java.util.*;

public class BOJ1987 {
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };
	static int R, C, result = -1;
	static char[][] map;
	static boolean[] alpha;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		alpha = new boolean[26];
		for (int i = 0; i < R; ++i) {
			String str = br.readLine();
			for (int j = 0; j < C; ++j) {
				char c = str.charAt(j);
				map[i][j] = c;
			}
		}
		dfs(0, 0, 1);
		System.out.println(result);
	}

	static void dfs(int x, int y, int cnt) {
		alpha[map[y][x] - 65] = true;
		result = Math.max(cnt,  result);
		
		for(int i = 0 ; i < 4 ; ++i) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if( nx < 0 || ny < 0 || nx == C || ny == R) continue;
			if( !alpha[map[ny][nx] - 65] ) {
				dfs(nx, ny, cnt+1);
				alpha[map[ny][nx] - 65] = false;
			}
		}
	}
}
