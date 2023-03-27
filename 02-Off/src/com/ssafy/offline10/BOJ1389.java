package com.ssafy.offline10;
import java.io.*;
import java.util.*;

public class BOJ1389 {
	static ArrayList<Integer>[] graph;
	static int[] bacon;
	static boolean[] visited;
	static int N, M;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new ArrayList[N+1];
		bacon = new int[N+1];
		for(int i = 1 ; i <= N ; ++i) {
			graph[i] = new ArrayList<>(); 
		}
		
		for(int i = 0 ; i < M ; ++i) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			graph[u].add(v);
			graph[v].add(u);
		}
		
		for(int i = 1 ; i <= N ; ++i) {
			visited = new boolean[N+1]; //모든 노드마다 검사를 해야하므로 초기화
			bfs(i);
		}
		
		int min = Integer.MAX_VALUE;
		int result = -1;
		for(int i = 1 ; i <= N ; ++i) {
			if(bacon[i] < min){
				min = bacon[i];
				result = i;
			}

		}
		System.out.println(result);
	}
	
	static void bfs(int node) {
		visited[node] = true; //현재 노드는 탐색하면 안되므로 true
		Queue<int[]> q = new LinkedList<>();
		int cnt = 0;
		q.offer(new int[] {node, cnt}); //시작 노드부터 큐에 넣고 bfs 실시
		// 큐에서는 add보다 offer의 처리속도가 더 빠름,,! 알고 있으면 좋다!!

		while(!q.isEmpty()){
			int[] temp = q.poll();
			int nowNode = temp[0];
			int nowCnt = temp[1];

			//node에서 nowNode까지 몇 단계 거쳤는지 누적해서 케빈 베이컨의 수를 구한다
			bacon[node] += nowCnt;

 			for(int i = 0 ; i < graph[nowNode].size(); ++i){
				int nearNode = graph[nowNode].get(i);

				if(visited[nearNode]) continue;

				//방문 안했으면 방문처리하고 현재 노드에서 1칸 떨어져 있으니 nowCnt+1을 해준다.
				visited[nearNode] = true;
				q.offer(new int[]{nearNode,nowCnt+1});
			}
		}

	}
}
