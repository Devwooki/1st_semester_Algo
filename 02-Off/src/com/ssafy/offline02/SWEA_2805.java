package com.ssafy.offline02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_2805 {
	public static int N;
	public static int[][] map;
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
			
//			//상단 삼각형 더하기
//			for(int i = 0 ; i <= N/2 ; ++i) {
//				for(int j = N/2-i ; j <= N/2+i ; ++j) {
//					result += map[i][j];
//				}
//			}
//			
//			//하단 삼각형 더하기
//			for(int i = N/2+1 ; i < N ; ++i) {
//				for(int j = i-N/2; j < N/2 +N - i ; ++j) {
//					result += map[i][j];
//				}
//			}
//			
			
			
			sb.append("#" + tc + " " + getArea() +"\n");
		}
		
		System.out.println(sb);
	}
	static int getArea() {
		int half = N/2;
		int sum = 0;
		for(int i = 0 ; i < N ; ++i) {
			for(int j = 0 ; j < N ; ++j) {
				if(Math.abs(half-i) + Math.abs(half-j) <= half) {
					sum += map[i][j];
				}
			}
		}
		return sum;
	}
}
