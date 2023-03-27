package com.ssafy.offline11;
import java.io.*;
import java.util.*;
public class BOJ7569 {
	static int N, M, H;
	static int[][][] tomatos;
	static boolean[][][] visited;
	static Queue<int[]> q = new ArrayDeque<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		
		tomatos = new int[H][M][N];
		visited = new boolean[H][M][N];
		int cnt = 0;
		for(int i = 0 ; i < H ; ++i) {//층
			for(int j = 0 ; j < M ; ++j) {
				st = new StringTokenizer(br.readLine());
				for(int k = 0 ; k < N ; ++k){
					tomatos[i][j][k] = Integer.parseInt(st.nextToken());
					if(tomatos[i][j][k] == 1) {
						q.add(new int[]{k, j ,i});//x,y,z순으로 큐에 넣음
						cnt++;
					}
				}
			}
		}

		if( (N*M*H) == cnt){
			System.out.println(0);
		}else{
			bfs();
			System.out.println(check());
		}

		
	}
	static void bfs() {
		int[] dx = {1, -1, 0, 0, 0, 0,};
		int[] dy = {0, 0, 1, -1, 0, 0};
		int[] dz = {0, 0, 0, 0, 1, -1};

		while(!q.isEmpty()) {
			int[] temp = q.poll();

			for(int i = 0 ; i < 6 ; ++i){
				int nx = temp[0] + dx[i];
				int ny = temp[1] + dy[i];
				int nz = temp[2] + dz[i];
				if(nx < 0 || ny < 0 || nz < 0
					|| nx == N || ny == M || nz ==H ) continue;

				if(tomatos[nz][ny][nx] == 0){
					tomatos[nz][ny][nx] =
							tomatos[temp[2]][temp[1]][temp[0]] + 1;
					q.offer(new int[]{nx, ny, nz});
				}
			}
		}
	}

	static int check(){
		int max = Integer.MIN_VALUE;
		for(int i = 0 ; i < H ; ++i) {//층
			for(int j = 0 ; j < M ; ++j) {
				for(int k = 0 ; k < N ; ++k){
					if(tomatos[i][j][k] == 0) return -1;

					max = Math.max(max, tomatos[i][j][k]);
				}
			}
		}
		return max-1;
	}
}
