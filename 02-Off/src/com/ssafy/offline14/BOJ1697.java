package com.ssafy.offline14;
import java.io.*;
import java.util.*;

public class BOJ1697 {
	static int N, K, result = 1 << 30;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		if(N >= K) {
			System.out.println(N - K);
			return;
		}else {
			visited = new boolean[100001];
			bfs();
		}
	}
	static void bfs() {
		Queue<int []> q = new ArrayDeque<>();
		
		q.offer(new int[] {N, 0});
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			if(temp[0] < 0 || temp[0] > 100000 || visited[temp[0]]) continue;
			
			
			visited[temp[0]] = true;
			
			if(temp[0] == K) {
				System.out.println(temp[1]);
				return;
			}
			
			int next1 = temp[0] - 1;
			int next2 = temp[0] + 1;
			int next3 = temp[0] * 2;
			
			if(next1 > 0 && !visited[next1] )	q.offer(new int[] {next1, temp[1] + 1});
			if(next2 <= 100000 && !visited[next2])	q.offer(new int[] {next2, temp[1] + 1});
			if(next3 <= 100000 && !visited[next3]) q.offer(new int[] {next3, temp[1] + 1});
		}
	}
}
