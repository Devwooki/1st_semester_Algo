package com.ssafy.offline17;
import java.io.*;
import java.util.*;
//정점 부분집합 만들기
//부분 집합이 연결되었는지 확인.
//연결 되었다면 인구차 구하기 - 인구차는 최솟값으로
public class BOJ17471 {
	static int N;
	static int[] costs;
	static ArrayList<Integer>[] graph;
	static int minDiff = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		costs = new int[N+1];
		graph = new ArrayList[N+1];
	
		st = new StringTokenizer(br.readLine());
		for(int i = 1 ; i <= N ; ++i) {
			graph[i] = new ArrayList<Integer>();
			costs[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 1 ; i <= N ; ++i) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			for(int j = 0 ; j < k ; ++j) {
				int x = Integer.parseInt(st.nextToken());
				graph[i].add(x);
			}
		}
		
		subset();
		
		System.out.println(minDiff);
	}
	
	private static void subset() {
		//부부집합 만들기 - 중복이 되어서 반으로 나눔
		for(int i = 1 ; i < ( 1 << N )/2 ; ++i) {		
			//부분집합을 저장할 리스트
			List<Integer> areaA = new LinkedList<>();
			List<Integer> areaB = new LinkedList<>();;
			for(int j = 0 ; j < N ; ++j) {
				//각 부분집합 저장
				if( (i & (1<<j)) == 0 ) areaA.add(j+1);
				else areaB.add(j+1);	
			}			
//			System.out.print(areaA.toString() + ", " +  areaB.toString() );
//			System.out.println();
			
			//부분집합이 모두 연결되었으면 인구 수 구하기
			if(bfs(areaA) && bfs(areaB)) {
				getPopular(areaA, areaB);
			}
		}
	}
	
	//부분집합이 연결되었는지 체크
	private static boolean bfs(List<Integer> list) {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		
		q.offer(list.get(0));
		visited[list.get(0)] = true;
		
		int cnt  = 1;
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(int near : graph[cur]) {
				if(!visited[near] && list.contains(near)) {
					cnt++;
					visited[near] = true;
					q.offer(near);
				}
			}	
		}
		
		//탐색 완료한 뒤
		return cnt == list.size() ? true : false; 
	}
	
	//인구수 측정
	private static void getPopular(List<Integer> listA, List<Integer> listB) {
		int sumA = 0, sumB = 0;
		for(int element : listA) {
			sumA += costs[element];
		}
		for(int element : listB) {
			sumB += costs[element];
		}
		
		minDiff = Math.min(minDiff, Math.abs(sumA - sumB));
	}
}


