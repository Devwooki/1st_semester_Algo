package com.ssafy.offline17;

import java.io.*;
import java.util.*;

public class SWEA1238 {

	static boolean[] visited;
	static ArrayList<Integer>[] emerCall;
	static int amount, start;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= 10; ++tc) {
			st = new StringTokenizer(br.readLine());
			amount = Integer.parseInt(st.nextToken());
			start = Integer.parseInt(st.nextToken());
			
			visited = new boolean[101];
			emerCall = new ArrayList[101];
			for(int i = 1 ; i < 101 ; ++i) {
				emerCall[i] = new ArrayList<>();
			}
			
			st = new StringTokenizer(br.readLine());
			while(st.hasMoreTokens()) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				emerCall[from].add(to);				
			}
			
			sb.append("#" + tc + " " + bfs(start) + "\n");
		}
		System.out.println(sb);
	}
	
	static int bfs(int sPoint) {
		Queue<int[]> q = new ArrayDeque<>();
		visited[sPoint] =  true;
		
		//범위가 확장될 때 마다 lv가 커짐
		q.offer(new int[] {sPoint, 0});
		
		int max = -1;
		int lv = 0;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			for(int near : emerCall[cur[0]]) {
				if(visited[near]) continue;
				//먼저 방문처리 해주지 않으면 특정연산에서 잘못된 값 저장됨.
				//접근하기 전에 미리 방문처리해주기
				visited[near] = true;
				q.offer(new int[] { near ,cur[1] + 1});
			}
			
			//큐에서  꺼냈을 때 lv가 깊어진거면 새로운 max를 구하기 위해 초기화
			if(cur[1] > lv) {
				max = -1;
				lv = cur[1];
			}
			//현재LV의 최댓값 갱신
			max = Math.max(cur[0], max);
		}
		
		return max;
	}
}