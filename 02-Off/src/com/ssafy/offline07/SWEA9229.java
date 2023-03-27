package com.ssafy.offline07;

import java.io.*;
import java.util.*;


public class SWEA9229 {
	static int N, M;
	static int[] snack;
	static int max = -1;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		//과자 두 봉지 사면서 최대합, M보다 작아야함
		int TC = Integer.parseInt(br.readLine());
		for(int tc = 1 ; tc <= TC ; ++tc) {
			st = new StringTokenizer(br.readLine()," ");
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			snack = new int[N];
			snack = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			sb.append("#"+ tc + " ");
			nCr(0,0,0);
			//DFS(0,0,0); 시간초과
			sb.append(max + "\n");
			max = -1;
		}
		System.out.println(sb);
	}
	
	private static void nCr(int cnt, int start, int sum) {
		if(cnt == 2) {
			if(sum <= M) {
				max = Math.max(max, sum);
			}
			return;
		}
		
		for(int i = start ; i < N ;++i) {
			nCr(cnt+1, i+1, sum + snack[i]);
		}
	}
	
	
	private static void DFS(int cnt, int idx, int sum) {
		if(idx == N-1) {
			if(cnt == 2 && sum <= M) {
				max = Math.max(max, sum);
			}
			return;
		}
		
		//현재과자 선택
		DFS(cnt+1, idx+1, sum+snack[idx]);
		//현재과자 미선택
		DFS(cnt, idx+1, sum);
	}
}
