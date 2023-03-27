package com.ssafy.offline17;

import java.io.*;
import java.util.*;
public class MSTTest1 {
	static class Edge implements Comparable<Edge>{
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}

		@Override
		public String toString() {
			return "Edge [from=" + from + ", to=" + to + ", weight=" + weight + "]";
		}
		
		
	}
	static int V, E;
	static Edge[] edges;
	static int[] parents;
	
	static void makeSet() {
		parents = new int[V];
		for(int i = 0 ; i < V ; ++i) {
			parents[i] = i;
		}
	}
	
	static int findSet(int a) {
		if(a ==  parents[a]) return a;//자기 자신이 부모의 값과 같음 == 대장임
		return parents[a] = findSet(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if(aRoot == bRoot) return false;
		
		parents[bRoot] = aRoot;
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		edges = new Edge[E];
		
		for(int i = 0 ; i < E; ++i) {
			st = new StringTokenizer(br.readLine());
			edges[i] = new Edge(Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()));
		}
		
		//간적쿠 간만프(간선이 적으면 쿠르스칼,  간선이 많으면 프림)
		//간선 비용이 작은 순서대로 정렬
		//정점 V가 10000개, 간선 E도 10000개  간선은 최대 N(N-1)-방향/ 무방향은N(N-1)/2
		//그래프 이론은  흐름과 코드를 직접 짜보고 칠 수 있어야함
		//매일 해야할 것 순조부, 비트, dfs, bfs, 서로소, 프림, 크루스칼, 다익스트라까지
		Arrays.sort(edges);// O(ElogE + E) -> O(ElogE) 
		
	
		makeSet();
		int result = 0, count = 0;
		for(Edge e : edges) {
			System.out.println(e);
			if(union(e.from, e.to)) {
				result += e.weight;
				if(++count == V - 1) break;
			}
		}
		System.out.println();
		System.out.println(result + ", " + count);
	}
}
