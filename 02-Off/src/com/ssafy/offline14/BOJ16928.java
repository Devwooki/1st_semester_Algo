package com.ssafy.offline14;
import java.io.*;
import java.util.*;
/*
 * 도착 : 사다리 -> 위로 올라감
 * 도착 : 뱀 -> 뱀타고 내려옴
 * 목표 : 1번칸에서 시작해 100번칸에 도착,
 * 100에 도착하기 위한 최솟ㄱ밧
 *
 * 최소로 오르기 위해선 사다리를 최대한 활용
 * 사다리 정렬을 시작이 낮은 순서, 둘 다
 * 뱀들을 피해서 주사위 굴리기
 */
public class BOJ16928 {
	static int N, M;
	static int[] map;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[101];
		visited = new boolean[101];
		for(int i =  0 ; i < N + M ; ++i ){
			st = new StringTokenizer(br.readLine());
			//1차원 배열 map작성
			//인덱스가 뱀, 사다리의 시작위치, 값이 도착지점
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			map[start] = end;
		}

		bfs();

	}
	static void bfs(){
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[]{1,0}); //시작 지점과 시행횟수 입력

		while(!q.isEmpty()){
			int[] now = q.poll();

			if(now[0] == 100){
				System.out.println(now[1]);
				return;
			}

			for(int i = 1; i <= 6 ; ++i){
				int next = now[0] + i;

				if(next > 100) continue;
				if(visited[next]) continue;

				//현재 지점이 사다리, 뱀이면
				if(map[next] != 0){
					//방문 안했던 지점인지 확인
					if(!visited[next]){
						//방문처리, 다음지점 갱신
						next = map[next];
						visited[next] = true;
						q.offer(new int[] {next, now[1]+1});
					}
				}else{
					visited[next] = true;
					q.offer(new int[] {next, now[1]+1});
				}

			}
		}

	}
}
