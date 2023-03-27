package com.ssafy.offline19;
import java.io.*;
import java.util.*;

public class ToplogySortTest {
	static class Node{
		int verTex;
		Node link;
		public Node(int verTex, Node link) {
			super();
			this.verTex = verTex;
			this.link = link;
		}
		
	}
	static int N, M;
	static Node[] graph;
	static int[] inDegree;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new Node[N+1];
		inDegree = new int[N+1];
		int from, to;
		for(int i = 0 ; i < M ; ++i) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			graph[from] = new Node(to, graph[from]);
			inDegree[to]++;
		}

		ArrayList<Integer> list = topologySort();
		if(list.size() == N) {
			for(Integer vertex : list) {
				System.out.println(vertex + " ");
			}
			System.out.println();
		}else {
			System.out.println("cycle");
			
		}
		
	}
	
	static ArrayList<Integer> topologySort(){
		ArrayList<Integer> orderList = new ArrayList<>();
		Queue<Integer> q = new ArrayDeque<Integer>();
		for(int i = 1 ; i<=N ; ++i) {
			if(inDegree[i] == 0) q.offer(i);
		}//진입차수가 0인 정점 큐에 넣기
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			orderList.add(cur);
			
			//현재 정점 기준으로 인접정점 처리
			for(Node temp = graph[cur] ; temp != null ; temp= temp.link) {
				if(--inDegree[temp.verTex] == 0) {
					q.offer(temp.verTex);
				}
			}
		}
		
		return orderList;
	}
}
