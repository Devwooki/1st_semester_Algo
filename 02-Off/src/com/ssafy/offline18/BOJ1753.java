package com.ssafy.offline18;
import java.io.*;
import java.util.*;

public class BOJ1753 {
	static class Node implements Comparable<Node>{
		int end;
		int cost;
		public Node(int end, int cost) {
			super();
			this.end = end;
			this.cost = cost;
		}
		@Override
		public String toString() {
			return "Node [end=" + end + ", cost=" + cost + "]";
		}
		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost; //가중치에 따른 오름차순 정렬을 위해 comparable구현
		}
	}
	static ArrayList<Node>[] graph;
	public static void main(String[] args) throws IOException {
		final int INF = Integer.MAX_VALUE;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		graph = new ArrayList[V+1];
		for(int i = 0 ; i <= V ; ++i) {
			graph[i] = new ArrayList<>();
		}
		
		int[] distance = new int[V+1];
		int start = Integer.parseInt(br.readLine());
	
		Arrays.fill(distance, INF);
		
		for(int i = 0 ; i < E ; ++i) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			graph[from].add(new Node(to, cost));
		}
	
		distance[start] = 0;
		
		PriorityQueue<Node> pq = new  PriorityQueue<>();
		boolean[] visited = new boolean[V+1];
		pq.offer(new Node(start, 0));
		
		while(!pq.isEmpty()) {//start-end, weight를 가지는데 최초의 노드는 ?-start, 0을가짐
			Node curNode = pq.poll();
			int cur = curNode.end;
			
			if(visited[cur]) continue; //최단거리가 갱신되었다면 더이상 해당 노드는 필요없으므로 
			visited[cur] = true;			
			
			for(Node near : graph[cur]) {
				if(!visited[near.end] && distance[near.end] > distance[cur] + near.cost) {
					distance[near.end] = distance[cur] + near.cost;
					pq.offer(new Node(near.end, distance[near.end]));
				}
				
			}
		}
		
		
		
		for(int i = 1 ; i <=V ; ++i ) {
			sb.append(distance[i] == INF ? "INF\n": distance[i] + "\n");
		}
		System.out.println(sb.toString());
	}
}
