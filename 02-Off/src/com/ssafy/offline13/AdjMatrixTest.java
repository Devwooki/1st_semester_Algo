package com.ssafy.offline13;
import java.io.*;
import java.util.*;


public class AdjMatrixTest {
	static int[][] matrix;
	static int V,E;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		V = Integer.parseInt(br.readLine());
		E = Integer.parseInt(br.readLine());
		
		matrix = new int[V][V];
		
		for(int i = 0 ; i < E ; ++i) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			//무향 그래프
			matrix[from][to] = matrix[to][from] = 1;
			//유향 그래프면 대칭 x
			//matrix[from][to] = 1;

			
			//두 정점 사이에 간선은 여러개가 가능하다 -> 1개 이상이다
			//ex 0 1, 가중치3 이후에 0 1, 가중치 5
			//이럴땐 문제를 보고 판단, 최솟값을 요구하면 작은 값을 아니면 큰값
		}
		
		bfs(0);
	
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
			
			for(int i = 0 ; i < V ; ++i) {
				if(matrix[current][i] != 0 && ! visited[i]) {
					q.offer(i);
					visited[i] = true;
				}
			}
		}
	}
	
}
