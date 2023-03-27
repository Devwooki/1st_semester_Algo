package com.ssafy.offline07;

import java.io.*;
import java.util.*;
//큐에 넣고 풀어보기
public class BOJ16926 {
	static int[][] map;	//최초 배열
	static int[][] result;	//결과값 저장할 배열
	static int[] cycles; //각 계층별 1사이클을 위한 횟수
	static int N, M ,R;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		int repeat = Math.min(N, M)/2;
		
		map = new int[N][M];
		result = new int[N][M];
		cycles = new int[repeat];
		for(int i = 0 ; i < N ; ++i){
			map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		for(int i = 1 ; i <= N/2 ; ++i){
			cycles[i-1] = 2 * (N + M + 2 - 4 *i);
			//계층별 싸이클 i = 2 * ( N - (2 * i - 1) + M -(2 * i - 1));
		}
		
		Queue<Integer> q = new LinkedList<>();
		int x, y;
		for(int i = 0 ; i < repeat ; ++i) {
			x = i; y = i;
			q.add(map[y][x]);
			int direction = 0;
			while(direction < 4) {
				int nowX = x + dy[direction];
				int nowY = y + dx[direction];
				if(i <= nowX && nowX < M-i && i <= nowY && nowY < N-i) {
					q.add(map[nowY][nowX]);
					x = nowX;
					y = nowY;
				}else {
					direction++;
				}
			}
			q.poll();
			
			for(int j = 0 ; j < R % cycles[i] -1 ; ++j) {
				q.add(q.poll());
			}
			
			
			x = i; y = i;
			result[y][x] = q.poll();
			direction = 0;
			while(direction < 4) {
				int nowX = x + dy[direction];
				int nowY = y + dx[direction];
				if(i <= nowX && nowX < M-i && i <= nowY && nowY < N-i) {
					if(!q.isEmpty()) {
						result[nowY][nowX] = q.poll();
						x = nowX;
						y = nowY;
					}
					else break;
					
				}else {
					direction++;
				}
			}
			
		}
		

	
		
		for(int i = 0 ; i < N ; ++i) {
			for(int j = 0 ; j < M ; ++j) {
				sb.append(result[i][j] + " ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);		
		
	}
}



//package com.ssafy.offline07;
//
//import java.io.*;
//import java.util.*;
////큐에 넣고 풀어보기
//public class BOJ16926 {
//	static int[][] map;	//최초 배열
//	static int[][] result;	//결과값 저장할 배열
//	static int[] cycles; //각 계층별 1사이클을 위한 횟수
//	static int N, M ,R;
//	static int[] dx = {0, 1, 0, -1};
//	static int[] dy = {1, 0, -1, 0};
//	
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		StringBuilder sb = new StringBuilder();
//		
//		N = Integer.parseInt(st.nextToken());
//		M = Integer.parseInt(st.nextToken());
//		R = Integer.parseInt(st.nextToken());
//		
//		int repeat = Math.min(N, M)/2;
//		
//		map = new int[N][M];
//		result = new int[N][M];
//		cycles = new int[repeat];
//		
//		for(int i = 0 ; i < N ; ++i){
//			map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
//		}
//		
//		for(int i = 1 ; i <= N/2 ; ++i){
//			cycles[i-1] = 2 * (N + M + 2 - 4 *i);
//			//계층별 싸이클 i = 2 * ( N - (2 * i - 1) + M -(2 * i - 1));
//		}
//		
//		int x, y, temp, direction;
//		for(int k = 0 ; k < R ; ++k) {
//			for(int i = 0 ; i <= repeat ; ++i ) {
//				x = i;
//				y = i;
//				temp = map[y][x];
//				direction = 0;
//				while(direction < 4) {
//					int nowX = x + dx[direction];
//					int nowY = y + dy[direction];
//					
//					if(i <= nowX && nowX < N-i && i <= nowY && nowY < M-i) {
////						result[nowY][nowX] = temp;
////						x = nowX;
////						y = nowY;
////						temp = map[nowY][nowX];
//						int current = map[nowY][nowX];
//						map[nowY][nowX] = temp;
//						x = nowX;
//						y = nowY;
//						temp = current;
//					}
//					else {
//						direction++;
//					}
//					
//				}
//			}
//		}
//
//		
//		
//		for(int i = 0 ; i < N ; ++i) {
//			for(int j = 0 ; j < M ; ++j) {
//				sb.append(map[i][j] + " ");
//			}
//			sb.append("\n");
//		}
//		
//		System.out.println(sb);		
//		
//	}
//}
