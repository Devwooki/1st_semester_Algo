package com.ssafy.offline09;

import java.io.*;
import java.util.*;

public class BOJ11724 {
	static ArrayList<Integer>[] graph;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[N+1];
		visited = new boolean[N+1];
		for(int i = 1 ; i <= N ; ++i) {
			graph[i] = new ArrayList<Integer>();
		}
		
		for(int i = 0 ; i < M ; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			graph[u].add(v);
			graph[v].add(u);
		}
		
		int connectCNT = 0;
		for(int i = 1 ; i <=N ; ++i) {
			if(visited[i]) continue;
			dfs(i);
			connectCNT++;
		}
		System.out.println(connectCNT);
	}
	static void dfs(int node) {
		if(visited[node]) return;
		
		
		visited[node] = true;
//		for(Integer num : graph[node])
//			dfs(num);
		for(int i = 0 ; i < graph[node].size() ; ++i) {
			dfs(graph[node].get(i));
		}
		
	}
}
