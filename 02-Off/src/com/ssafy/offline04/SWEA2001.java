package com.ssafy.offline04;


import java.io.*;
import java.util.*;

public class SWEA2001 {
	static int[][] map;
	static int[][] prefixSum;
	static int N,M;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for(int tc = 1 ; tc <= TC ; ++tc) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			map = new int[N+1][N+1];
			prefixSum= new int[N+1][N+1];
			
	           for(int i = 1; i< N+1; i++){
	                st = new StringTokenizer(br.readLine());
	                for(int j = 1; j< N+1; j++){
	                    map[i][j] = Integer.parseInt(st.nextToken());
	                }
	 
	            }
			
			
			
//			int max = -1;
//			for(int i = 0 ; i <= N-M ; ++i) {
//				for(int j = 0 ; j <= N - M ;++j) {
//					max = Math.max(max, killFly(j,i));
//				}
//			}
//			sb.append("#" + tc + " " + max + "\n");
			sb.append("#" + tc + " " + killFly2() + "\n");
		}
		System.out.println(sb);
	}
	
	private static int killFly(int x, int y) {
		int kill =0;
		for(int i = y ; i < y+M ; ++i) {
			for(int j = x ; j < x + M ; ++j) {
				kill += map[i][j];
			}
		}
		return kill;
	}
	
	//누적 합 풀이
	private static int killFly2() {
		for(int i = 1 ; i <= N ; ++i) {
			for(int j = 1 ; j <= N ; ++j) {
				prefixSum[i][j] = map[i][j] + prefixSum[i-1][j] + prefixSum[i][j-1] - prefixSum[i-1][j-1];  
			}
		}
		int max = -1;
		for(int i = M ; i <= N ; ++i) {
			for(int j = M ; j <= N ; ++j) {
				max = Math.max(max, prefixSum[i][j] - prefixSum[i-M][j] - prefixSum[i][j-M] + prefixSum[i-M][j-M]);
			}
		}
		return max;
	}
}
