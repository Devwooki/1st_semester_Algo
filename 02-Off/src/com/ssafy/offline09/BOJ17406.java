package com.ssafy.offline09;

import java.io.*;
import java.util.*;

public class BOJ17406 {
	static int[][] map;
	static int[][] tempMap;
	static int N, M;
	static int[] permRCS;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i = 0 ; i  < N ; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < M ;++j) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] rcs = new int[K][3];
		permRCS = new int[K];
		
		for(int i = 0 ; i  < K ; ++i) {
			rcs[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			permRCS[i] = i;
		}
		
		int min = Integer.MAX_VALUE;
		
		//RCS조합 만들기
		Arrays.sort(permRCS);
		do {
			//map의 복사본을 만들어서 RCS조합마다 계산함
			tempMap = new int[N][M];
			for(int i = 0 ; i  < N ; ++i) {
				for(int j = 0 ; j < M ;++j) {
					tempMap[i][j] = map[i][j];
				}
			}
			//RCS순열 순서대로 배열 회전 시킨 후 행별 합 구하기
			//행별 합 구해서 최솟값 저장
			for(int i = 0 ; i < permRCS.length ; ++i) {
				int now = permRCS[i];
				
				/** rcs
				 *  r : 0
				 *  c : 1
				 *  s : 2 -> 몇 층인지 나타내는 지표가 되기도 함
				 *  //1을 빼는 이유는 배열으 크기가 0~N-1이기 때문
				 */
				rotate(	(rcs[now][1]-1) - rcs[now][2],
						(rcs[now][0]-1) - rcs[now][2],
						(rcs[now][1]-1) + rcs[now][2],
						(rcs[now][0]-1) + rcs[now][2],
						 rcs[now][2]);
			}
			
			for(int[] row : tempMap) {
				min = Math.min(min, Arrays.stream(row).sum());
			}
		}while(nPn());
		System.out.println(min);
	}
	
	static void rotate(int x1, int y1, int x2, int y2, int s) {
		 int[] dx = {0, 1, 0, -1}; // ↓ → ↑ ←
	     int[] dy = {1, 0, -1, 0};
	     
	     for(int i = 0 ; i < s ; ++i) {
		     int direction = 0;
		     int x = x1+i;
		     int y = y1+i;
		     int temp = tempMap[y][x];
		     
		     while(direction != 4) {
		    	 int nowX = x + dx[direction];
		    	 int nowY = y + dy[direction];
		    	 
		    	 //범위만 잘 계산해서 해보자,,,
		    	 //
		    	 if( x1 + i <= nowX && nowX <= x2-i && y1 + i <= nowY && nowY <= y2-i) {
		    		 tempMap[y][x] = tempMap[nowY][nowX];
		    		 x = nowX;
		    		 y = nowY;
		    	 }else {
		    		 direction++;
		    	 }
		     }
		     tempMap[y][x + 1] = temp;
//		     for(int j = 0 ; j < N ; ++j) {
//		    	 System.out.println(Arrays.toString(tempMap[j]));
//		     }
//		     System.out.println();
		     
	     }
	}
	
	
	static boolean nPn() {
		int n = permRCS.length;
		
		int i = n - 1;
		while(i > 0 && permRCS[i-1] >= permRCS[i]) i -= 1;
		if( i == 0) return false;
		
		int j = n - 1;
		while(permRCS[i-1] >= permRCS[j] ) j -= 1;
		
		swap(i-1, j);
		
		int k = n-1;
		while(i < k) swap( i++, k--);
		
		return true;
	}
	
	static void swap(int front, int back) {
		int temp = permRCS[front];
		permRCS[front] =  permRCS[back];
		permRCS[back] = temp;
		
	}
}
