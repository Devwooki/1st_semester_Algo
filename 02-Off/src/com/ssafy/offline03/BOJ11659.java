package com.ssafy.offline03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11659 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		int[] sums = new int[N+1];
		arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		
		sums[1] = arr[0];
		for(int i = 2 ; i <= N ; ++i) {
			sums[i] = arr[i-1] + sums[i-1];
		}
		
		for(int i = 0 ; i < M ; ++i) {
			int[] temp = new int[2];
			temp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			
			
			sb.append((sums[temp[1]] - sums[temp[0]-1] )+"\n");
		}
		
		System.out.println(sb);
		
	}

}
