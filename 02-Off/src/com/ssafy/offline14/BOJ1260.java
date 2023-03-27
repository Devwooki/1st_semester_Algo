package com.ssafy.offline14;
import java.io.*;
import java.util.*;

public class BOJ1260 {
	static int N, M;
	static ArrayList<Integer>[] graph;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int V =Integer.parseInt(st.nextToken());
		graph = new ArrayList[N+1];
		for(int i = 1 ; i <= N ; ++i) {
			graph[i] = new ArrayList<Integer>();
		}
		
		for(int i = 0 ; i < M ; ++i) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			graph[u].add(v);
			graph[v].add(u);
		}
		
        //번호가 작은 것 부터 방문하기 위해 정렬
        for(int i = 1 ; i <= N ; ++i){
            Collections.sort(graph[i]);
        }
		
		visited = new boolean[N+1];
		dfs(V);
	
		sb.append("\n");
		visited = new boolean[N+1];
		bfs(V);
		
		System.out.println(sb.toString());
	}
	private static void bfs(int node) {
		Queue<Integer> q = new ArrayDeque<>();
		
		q.offer(node);
		visited[node] = true;
		
		while(!q.isEmpty()) {
			int now = q.poll();
			sb.append(now + " ");
			
			for(int k : graph[now]) {
				if(!visited[k]) {
					q.offer(k);
					visited[k] = true;
				}
			}	
		}
	}
	private static void dfs(int node) {
		if(visited[node]) return;
		visited[node] = true;
		
		sb.append(node + " ");
		for(int k : graph[node]) {
			if(!visited[k]) dfs(k);
		}
	}
}
