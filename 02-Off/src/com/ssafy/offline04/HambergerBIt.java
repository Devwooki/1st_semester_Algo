package com.ssafy.offline04;

import java.io.*;
import java.util.*;


public class HambergerBIt {
	public static int[][] NL;
	public static int N,L;
	public static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int TC = Integer.parseInt(br.readLine());
		
		for(int tc = 1 ; tc <= TC ; ++tc) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			NL = new int[N][2];
			for(int i = 0 ; i < N ; ++i) {
				NL[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
			
			sb.append("#" + tc + " ");
			getHamberger();
			
		}
		System.out.println(sb);
	}
	public static void getHamberger() {
		int max = -1;
		for(int i = 0 ; i < (1 << N ); ++i) {
			int score = 0;
			int kcal = 0;
			for(int j = 0 ; j < N ; ++j ) {
				if( (i & 1 << j) == 0) continue;
				
				//System.out.print(j + " " + NL[j][0] + "\t");
				score += NL[j][0];
				kcal += NL[j][1];
			}
			if(kcal <= L) {
				max = Math.max(score, max);

			}
		}
		
		sb.append(max + "\n");
	}
}
