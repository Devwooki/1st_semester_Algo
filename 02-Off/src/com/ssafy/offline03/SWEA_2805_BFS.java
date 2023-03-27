package com.ssafy.offline03;

import java.io.*;
import java.util.StringTokenizer;

public class SWEA_2805_BFS {
	public static int N;
	public static int[][] map;
	public static void main(String[] args) throws IOException {
		
		//SWEA파일읽는 법
		System.setIn(new FileInputStream("경로명"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		
		int TC =  Integer.parseInt(br.readLine());
		for(int tc = 1 ; tc <= TC ; ++tc) {
			int result = 0 ;
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			
			//맵 초기화
			for(int i = 0 ; i < N ; ++i) {
				String str = br.readLine();
				for(int j = 0 ; j < N ; ++j) {
					map[i][j] = str.charAt(j) - '0';
				}
			}
			
		
			int start = N/2;
			int end = N/2;
			for(int i = 0 ; i < N ; i++) {
				for(int j = start ; j <= end ; ++j) {
					//교수님 답안
					//if(Math.abs(start-i) + Math.abs(end-j) <= N/2)
					//result += map[i][j];
					//		
					/* N이 5라고 할 때 중심으로 부터 거리는
					 * 4 3 2 3 4
					 * 3 2 1 2 3
					 * 2 1 0 1 2 
					 * 3 2 1 2 3
					 * 4 3 2 3 4
					 * 이므로 중심좌표에서 행과 열의 차를 절댓값으로 구함(거리)로 마름모에 접근할 수 있다.
					 */
					result += map[i][j];
				}
				
				//행이 절반 전이면
				if(i <N/2) { 
					start -=1; //구간 범위 늘리기
					end +=1;
				}else {//행이 절반 부분 부터
					start +=1;//구간 줄이기
					end -=1;
				}
			}

			sb.append("#" + tc + " " + result + "\n");
		}
		System.out.println(sb);
	}
}
