package com.ssafy.offline18;
import java.io.*;
import java.util.*;

public class DijkstraTest {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int V = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		int[][] matrix = new  int[V][V];
		for(int i = 0 ; i < V; ++i) {
			st =new StringTokenizer(br.readLine());
			for(int j = 0 ; j < V ; ++j) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		final int INF = Integer.MAX_VALUE;
		int[] distance = new int[V]; //출발정점에서 자신까지 오는 최소비용
		boolean[] visited = new boolean[V]; //경유지 방문여부 체크용 배열
		Arrays.fill(distance, INF);//최솟값 갱신 로직을 반영해야하므로 큰값으로 초기화
		distance[start] = 0;
		
		int min, cur;
		for(int i = 0 ; i < V ; ++i) {
			
			//step1 : 경유지로 처리되지 않은 정점 중 출발지에서 가장 가까운 것을 선택함
			cur = -1;
			min = INF; //cur까지의  값
			
			for(int j = 0 ; j < V ; ++j) {
				if(!visited[j] && min > distance[j]) {
					min = distance[i];
					cur = j;
				}
			}
			
			if(cur == -1) break; //아무리 돌아도 찾을 수 있는 녀석이 없음
			visited[cur] = true;
			
			//선택된 정점이 문제에서 요구하는 도착정점이면 끝내기
			if(cur == end) break;
			
			//step2 : 위에서 선택된 정점을 경유지로 해 갈 수 있는 다른 미방문 인접정점과 비용 최소값 갱신
			
			for(int j = 0 ; j < V ; ++j) {
				if(!visited[j] //vistied[j] 는 생략할 수 있음 왜냐? distance[j] > min + matrix[cur][j]가 항상 성립하기때문
						&& matrix[cur][j] != 0 
						&& distance[j] > min + matrix[cur][j]) {
					//다른 노드를 거쳐오는것이 이득이니까 - 정점하나가 고려되면 누적됨
					distance[j] = min + matrix[cur][j];
					
				}
			}			
		}
		//출발지에서 원하는 정점까지의 최소 정점이 모두 구해짐
		
		System.out.println( distance[end] != INF ? distance[end] : -1);
		
	}
}
