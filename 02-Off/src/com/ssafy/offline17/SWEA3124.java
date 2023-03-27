package com.ssafy.offline17;
import java.io.*;
import java.util.*;

public class SWEA3124 {
	static class Edge implements Comparable<Edge>{
		int start;
		int end;
		int cost;
		public Edge(int start, int end, int cost) {
			super();
			this.start = start;
			this.end = end;
			this.cost = cost;
		}
		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
		
	}
	static void makeSet() {
		p = new int[V+1];
		for(int i = 1 ; i <= V ; ++i) {
			p[i] = i;
		}
	}
	static int find(int a) {
		if(p[a] == a) return a;
		
		return p[a] = find(p[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot ) return false;
		
		p[bRoot] = aRoot;
		return true;
	}
	
	static int V, E;
	static Edge[] edges;
	static int p[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; ++tc) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			edges = new Edge[E];
			
			makeSet();
			for(int i = 0 ; i < E ; ++i) {
				st = new StringTokenizer(br.readLine());
				//순서대로 시작점, 끝점, 가중치 입력
				edges[i] = new Edge(Integer.parseInt(st.nextToken()),
									Integer.parseInt(st.nextToken()),
									Integer.parseInt(st.nextToken()));
			}
			
			
			long result = 0;
			int cnt = 0;
			Arrays.sort(edges);
			for(Edge e : edges) {
				if(union(e.start, e.end)) {
					result += e.cost;
					
					if(++cnt == V-1) break;
				}
			}
			sb.append("#" + tc + " " + result + "\n");
		}
		System.out.println(sb);
	}
}
