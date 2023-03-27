package com.ssafy.offline08;

import java.io.*;
import java.util.*;

public class 배열돌리기1 {
	static int R,C, rotateCnt;
	static int[][] map;
	static StringBuilder sb = new StringBuilder();
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		R = sc.nextInt();
		C = sc.nextInt();
		rotateCnt = sc.nextInt();
		
		inputMap();
		rotateMap();
		printMap();
		
	}
	static void printMap() {
		for(int i = 0; i < R ; ++i) {
			for(int j = 0; j < C; ++j) {
				sb.append(map[i][j] + " ");
			}
			sb.append("\n");
		}
		
	}
	static void inputMap() {
		map = new int[R][C];
		for(int i = 0 ; i < R ; ++i) for(int j = 0 ; j < C ; ++j) map[i][j] = sc.nextInt();
	}
	
	static void rotateMap() {
		int[] dx = {0,1,0,-1};
		int[] dy = {1,0,-1,0};
		int loopCnt = Math.min(R, C)/2;
		for(int cnt = 0 ; cnt <loopCnt ; ++cnt) {//반복의 초기위치가 cnt 위치기 때문에 cnt = 0으로
			int temp = map[cnt][cnt];
			
			int x = cnt;
			int y = cnt;
			
			for(int d = 0 ; d < 4 ; ++d) {
				while(true) {
					int nx = x + dx[d];
					int ny = y + dy[d];
					
					//배열 탈출을 위해서 ||연산을 함 &&도 좋긴한데 별로
					if( nx < cnt || nx >= R-cnt || ny < cnt || ny >= C-cnt) continue;
					
					map[x][y] = map[nx][ny];
					
					x = nx;
					y = ny;
				}
			}
			//로직이 끝난 뒤
			map[cnt+1][cnt] = temp;
		}
	}
}
