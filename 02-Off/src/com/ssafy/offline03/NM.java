package com.ssafy.offline03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class NM {
	public static int N, M;
	public static int[] inputs;
	public static int[] numbers;
	public static boolean[] isSelected;
	public static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		isSelected = new boolean[N];
		inputs = new int[N];
		numbers = new int[M];
		
		
		//NM 1~4
		for(int i = 0 ; i < N ;++i) {
			inputs[i] = i+1;
		}
		
//		//NM 5~8
//		st = new StringTokenizer(br.readLine());
//		for(int i = 0 ; i < N ;++i) {
//			inputs[i] = Integer.parseInt(st.nextToken());
//		}
		
		Arrays.sort(inputs);
		nPr(0);  	//NM 1,5
//		nPIr(0);	//NM 3,7
//		nCr(0,0);	//NM 2,6
//		nHr(0,0);	//NM 4,8
		System.out.println(sb);
	}

	public static void nPr(int cnt) {
		if(cnt == M) {
			for(int num : numbers) {
				sb.append(num + " ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i = 0 ; i < N ; ++i) {
			if(isSelected[i]) continue;
			
			isSelected[i] = true;
			numbers[cnt] = inputs[i];
			
			nPIr(cnt+1);
			isSelected[i] = false;
		}
	}
	
	public static void nPIr(int cnt) {
		if(cnt == M) {
			for(int num : numbers) {
				sb.append(num + " ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i = 0 ; i < N ; ++i) {		
			numbers[cnt] = inputs[i];
			nPIr(cnt+1);
		}
	
	}
	
	public static void nCr(int cnt, int start) {
		if(cnt == M) {
			for(int num : numbers) {
				sb.append(num + " ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i = start ; i < N ; ++i) {
			numbers[cnt] = inputs[i];
			nCr(cnt+1, i+1);
		}
		
	}
	
	public static void nHr(int cnt, int start) {
		if(cnt == M) {
			for(int num : numbers) {
				sb.append(num + " ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i = start ; i < N ; ++i) {
			numbers[cnt] = inputs[i];
			nHr(cnt+1, i);
		}
		
	}
}
