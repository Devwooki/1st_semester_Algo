package com.ssafy.offline19;
import java.io.*;
import java.util.*;

public class BOJ1916 {
	static class Node implements Comparable<Node>{
		int to;
		int cost;
		public Node(int to, int cost) {
			super();
			this.to = to;
			this.cost = cost;
		}
		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		ArrayList<Node>[] nodes = new ArrayList[N+1];
		for(int i = 1 ; i <= N ; ++i) {
			nodes[i] = new ArrayList<>();
		}
		for(int i = 0 ; i < M ; ++i){
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			nodes[u].add(new Node(v, cost));
		}
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int dest = Integer.parseInt(st.nextToken());
		int[] distance = new int[N+1];
		
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[start] = 0;
		
		PriorityQueue<Node> q = new PriorityQueue<>();
		//우선순위큐에 (0,시작점,비용0)추가
		/*
		 * 1우선순위 큐에서 가장 거리가 작은 숸소 선택, 
		 * 2해당 거리가 최단 거리에 테이블에 있는 값과 다르면스킵
		 * 3. 원소가 가르키는 정점 v, v와 이웃한 정점들에 대해 최단 거거리테이블 보다 v를 거쳐가는게 작을 경우
		 *		거리 갱신 후 우선순위큐에 추가
		 * 4. 큐 빌 때 까지 반복 
		 */
		q.offer(new Node(start, 0));
		boolean[] visited = new boolean[N+1];
		
		
		while(!q.isEmpty()) {
			Node curNode = q.poll();
			int now = curNode.to;
			
			//방문한 노드(최단거리가 갱신 된 노드)면 건너뛰고
			if(!visited[now]) {
				visited[now] = true;
				
				for(Node near : nodes[now]) {
					//인접노드 방문 안했고, 인접노드까지의 거리가, 현재 거리 + 입접노드까지의 거리보다 크면 최소거리 갱신 및 q에 넣음
					if(!visited[near.to] && distance[near.to] > distance[now] + near.cost) {
						distance[near.to] = distance[now] + near.cost;
						q.offer(new Node(near.to, distance[near.to]));
					}
				}
			}
			
			
		}
		System.out.println(distance[dest]);
		
	}
}
