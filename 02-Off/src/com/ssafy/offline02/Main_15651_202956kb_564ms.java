package com.ssafy.offline02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_15651_202956kb_564ms {
	public static int[] numbers;
	public static int[] inputs; 
	public static boolean[] isSelected; 
	public static int N, M;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		numbers = new int[M];
		inputs = new int[N];
		isSelected = new boolean[N];
		for(int i = 0 ; i < N ; ++i) {
			inputs[i] = i+1;
		}
		
		perm(0);
		System.out.println(sb);
	}

	public static void perm(int cnt) {
		if(cnt == M) {
			print();
			return;
		}
		
		for(int i = 0 ; i < N ; ++i) {
			
			isSelected[i] = true;
			numbers[cnt] = inputs[i];
			
			perm(cnt+1);
		}
	}
	
	private static void print() {
		for(int i = 0 ; i < M ; ++i) {
			sb.append(numbers[i] + " ");
		}
		sb.append("\n");
	}
}
