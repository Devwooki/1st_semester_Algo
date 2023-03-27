package com.ssafy.offline17;
import java.io.*;
import java.util.*;

public class MST2_Prime {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N  = Integer.parseInt(br.readLine());
		
		int[][]  data = new int[N][N];
		boolean[] visited= new boolean[N];
		int[] minCost = new int[N];
		
		for(int i = 0 ; i < N ;++i) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0 ; j< N ;++j) {
				data[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//시작점이 있어야 출발지~목적지 사이의 가중치를 구해야함.
		//시작점에서 가장 갈 수 있는것을 for문을 통해 최소값 찾고 정점 교체
		//Arrays.fill(minCost, Integer.parseInt)해서 다시 반복
		
		//정점의 최소비용을 구하기 위해 최댓값으로 저장
		Arrays.fill(minCost, Integer.MAX_VALUE);
		
		//시작정점 세팅 : 0이 아니여도 됨 편할대로
		minCost[0] = 0;
		int ans 
		= 0; //최소 신장트리의 간선비용을 담을 변수
		for(int n = 0 ; n < N ; ++n) {
			int min = Integer.MAX_VALUE;
			int minVertex = -1;
			//minCost에서 최소값을 찾아 정점을 교체해야함
			for(int i = 0 ; i < N ; ++i) {
				if(min > minCost[i]) {
					min = minCost[i]; //최소비용 갱신
					minVertex = i;
					
					//선택된 녀석도 등장하면 안되니 visted체크
				}
			}
			
			//이제 내 편으로 (최소 신장트리에 포함되었다)
			visited[minVertex] = true;
			ans +=  min;
			
			
			//현재 정점과 연결된 정점들을 갱신 해야함
			//선택된 정점에 대해서 연결된 간선들의 비용과 최소비요을 가지고 있는 minCost와 비교해서 최소 비용 갱신 작업
			//정점에서 찾고 있기  때문에 정점
			for(int i = 0 ; i < N ; ++i) {
				//minVertex의 열의 값만 변경되면 됨
				//0이면 걸러야함. 자기자신이므로
				//visited도 걸러야함, 방문한 걸 또 방문하면 싸이클이 되므로
				if(!visited[i] 
						&& data[minVertex][i] != 0 
						&& minCost[i] > data[minVertex][i])
					minCost[i] = data[minVertex][i];
			}
		}
		System.out.println(ans);
	}	
}
