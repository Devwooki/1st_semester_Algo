package com.ssafy.offline13;
import java.io.*;
import java.util.*;


public class AdjListTest {
	static class Node{
		int vertex;
		Node link;
		public Node(int vertex, Node link) {
			super();
			this.vertex = vertex;
			this.link = link;
		}
		@Override
		public String toString() {
			return vertex + ", " + link;
		}
		
	}
	
	static Node[] adjList;
	static ArrayList<Integer>[] adj2;
	static int V, E;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		V = Integer.parseInt(br.readLine());
		E = Integer.parseInt(br.readLine());
		
		adjList = new Node[V]; //head가 null인 상태
		
		//리스트, Node와 달리 선언을 해줘야해서 처리 시간이 오래걸림
		adj2 = new ArrayList[V];
		for(int i = 0 ; i < V ; ++i) {
			adj2[i] = new ArrayList<>();
		}
		
		for(int i = 0 ; i < E ; ++i) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			//무향 그래프
			adjList[from] = new Node(to, adjList[from]);
			adjList[to] = new Node(from, adjList[to]);
			
			adj2[from].add(to);
			adj2[to].add(from);
			//유향 그래프면 대칭 x
			//matrix[from][to] = 1;

			
			//두 정점 사이에 간선은 여러개가 가능하다 -> 1개 이상이다
			//ex 0 1, 가중치3 이후에 0 1, 가중치 5
			//이럴땐 문제를 보고 판단, 최솟값을 요구하면 작은 값을 아니면 큰값
		}
//		print1();
//		System.out.println();
//		print2();
		bfs(0);
	}
	
	static void print1() {
		//탐색할 땐 null이 아닐때 까지만 수행하도록 하면 된다.
		for(Node node : adjList) {
			System.out.println(node);
		}
	}
	
	static void print2() {
		//탐색할 땐 null이 아닐때 까지만 수행하도록 하면 된다.
		for(ArrayList node : adj2) {
			System.out.println(node);
		}
	}
	
	private static void bfs(int start) {
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] visited = new boolean[V];
		
		q.offer(start);
		visited[start] = true;
		
		int current =0;
		while(!q.isEmpty()) {
			current = q.poll();
			System.out.println((char)(current + 65));
			
			for(Node temp = adjList[current] ; temp != null ; temp = temp.link) {
				if(!visited[temp.vertex]) {
					q.offer(temp.vertex);
					visited[temp.vertex] = true;
				}
			}
		}
	}
	
}
