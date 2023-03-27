package com.ssafy.offline03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11660 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N][];
		int[][] sums = new int[N+1][N+1];
		for(int i = 0; i < N ; ++i) {
			arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		
		
		//열의 합 구하기
		for(int i = 1 ; i <= N ; ++i) {
			for(int j = 1 ; j <= N ; ++j) {
				sums[i][j] = sums[i][j-1] + arr[i-1][j-1]; 
			}
		}
		
		
		//행의 합 구하기
		for(int i = 1 ; i <= N ; ++i) {
			for(int j = 1 ; j <= N ; ++j) {
				sums[j][i] += sums[j-1][i];
			}
		}
		
		
		for(int i = 0 ; i < M ; ++i) {
			int[] temp = new int[4];
			//(x1,y1), (x2,y2)
			temp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			
			// (x2,y2) - (x1,y2) - (x2,y1) + (x1,y1)
			int result = sums[temp[2]][temp[3]] 
					- sums[temp[0]-1][temp[3]]
					- sums[temp[2]][temp[1]-1]
					+ sums[temp[0]-1][temp[1]-1];
			sb.append(result +"\n");
		}
		
		System.out.println(sb);
		
	}

}
