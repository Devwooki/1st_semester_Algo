package com.ssafy.offline04;

import java.io.*;
import java.util.*;


public class PowerSetSum {
	static int[] inputs;
	static int N, sum = 0, TARGET;
	static boolean[] isSelected;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		TARGET = Integer.parseInt(st.nextToken());
		
		inputs = new int[N];
		isSelected = new boolean[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N; ++i) {
			inputs[i] = Integer.parseInt(st.nextToken());
		}
		
		powerSetSum(0,0);
		
	}
	
	/**
	 * 
	 * @param cnt : 직전까지 고려된 원소 수
	 * @param sum : 직전까지 선택된 원소들의 합
	 */
	private static void powerSetSum(int cnt, int sum) {
		if(cnt == N ) {
			for(int i = 0 ; i < N ; ++i){
				if(isSelected[i]) System.out.println(inputs[i] + "\t");
			}
			return;
		}
		
		isSelected[cnt] = false;
		powerSetSum(cnt+1, sum);
		isSelected[cnt] = true;
		powerSetSum(cnt+1, sum + inputs[cnt]);
	
	}
	
	
//	private static void powerSet(int cnt) {
//		
//		
//		if(cnt == N ) {
//			for(int i = 0 ; i < N ; ++i){
//				if(isSelected[i])sum += inputs[i];
//			}
//			
//			if(sum == TARGET) {
//				for(int i = 0 ; i < N ; ++i) {
//					if(isSelected[i]) System.out.println(inputs[i] + "\t");
//				}
//				System.out.println();
//			}
//			sum = 0;
//			return;
//		}
//		
//		isSelected[cnt] = true;
//		powerSet(cnt+1);
//		isSelected[cnt] = false;
//		powerSet(cnt+1);
//	}
}
