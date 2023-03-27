package com.ssafy.offline04;

import java.io.*;
import java.util.*;


public class BOJ2961 {
	public static boolean[] isSelected;
	public static int[][] taste;
	static int min2 = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		//신맛 S - 곱
		//쓴맛 B - 합
		//신맛과 쓴맛의 차이를 적게
		taste = new int[N][2];
		isSelected = new boolean[N];
		for(int i = 0 ; i < N ; ++i) {
			taste[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();			
			
		}
		
		int min = Integer.MAX_VALUE;
		
		//적어도 하나 사용해야한다 -> 공집합 제외
		for(int i = 1 ; i < (1 << N) ; ++i) {
			int sour = 1;
			int bitter = 0;
			for(int j = 0 ; j < N ; ++j) {
				if( (i & 1 << j) == 0 ) continue;
				
				sour *= taste[j][0];
				bitter += taste[j][1];
			}
			
			min = Math.min(min, Math.abs(sour - bitter));
		}
		
		//System.out.println(min);
		
		subset(0, N);
		System.out.println(min2);
		
	}
	
	private static void subset(int cnt, int N) {
		if(cnt == N ) {
			int sour = 1;
			int bitter = 0;
			int setCount = 0;
			for(int i = 0 ; i < N ; ++i) {
				if(isSelected[i]) {
					setCount++;
					sour *= taste[i][0];
					bitter += taste[i][1];
				}
				
			}
			if(setCount == 0) return; //공집합이면 패스
			min2 = Math.min(min2 , Math.abs(sour - bitter));
			return;
		}
		
		isSelected[cnt] = true;
		subset(cnt+1, N);
		isSelected[cnt] = false;
		subset(cnt+1, N);
		
		
	}
}
