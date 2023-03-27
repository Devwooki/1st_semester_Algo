package second.contest;

import java.io.*;
import java.util.*;

public class BOJ1926 {
	static int[][] direction = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	static int n, m;
	static boolean[][] visited;
	static int[][] map;
	static int picCnt = 0, size =0,maxSize = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		visited = new boolean[n][m];
		map = new int[n][m];
		for (int i = 0; i < n; ++i) {
			map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}

		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				if (map[i][j] == 0) continue;

				if (!visited[i][j]) {
					dfs(j, i);
					
					picCnt++;
					maxSize = Math.max(size, maxSize);
					size =0;
				}

			}
		}
		if(picCnt ==0) maxSize = 0;
		System.out.println(picCnt + "\n" + maxSize);
	}

	static void dfs(int x, int y) {
		//if (visited[y][x]) return;

		size++;
		visited[y][x] = true;
		
		for (int i = 0; i < 4; ++i) {
			int nx = x + direction[i][0];
			int ny = y + direction[i][1];
			
			if (checkRange(nx, ny))
				if (!visited[ny][nx]  && map[ny][nx] == 1) {
					dfs(nx, ny);
				}
		}
	}

	static boolean checkRange(int x, int y) {
		return (0 <= x && x < m && 0 <= y && y < n);
	}
}
