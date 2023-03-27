package com.ssafy.offline03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ3040 {
	public static int N = 9, M = 7;
	public static int[] inputs;
	public static int[] numbers;
	public static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		inputs = new int[N];
		numbers = new int[M];
		int sum = 0;
		
		for(int i = 0 ; i < N ; ++i) {
			inputs[i] = Integer.parseInt(br.readLine());
			sum += inputs[i];
		}
		
//		inputs = Arrays.stream(inputs).map(x -> {
//			try {
//				return Integer.parseInt(br.readLine());
//			}catch (IOException e) {
//				throw new RuntimeException(e);		
//			}
//		}).toArray();
//		
//		nCr(0,0);// 재귀 풀이
		
		
		//반복문 풀이 전체합에서 2마리를 빼서 100이 될 때 아닌 녀석들 검출
		for(int i = 0 ; i < 8 ; ++i) {
			for(int j = i+1 ; j < 9 ; ++j) {
				if(sum - inputs[i] - inputs[j] == 100) {
					
					for(int k = 0 ; k < 9 ; ++k) {
						if(k != i && k != j) {
							sb.append(inputs[k] + "\n");
						}
					}
					System.out.println(sb);
					return;
				}
			}
		}
		
	}
	
	public static void nCr(int cnt, int start) {
		if(cnt == M ) {
//			if(sumArr() == 100) {
//				for(int num : numbers) {
//					sb.append(num + "\n");
//				}
//				System.out.println(sb.toString());
//			}
			if(Arrays.stream(numbers).sum() == 100) Arrays.stream(numbers).forEach(System.out::println);
			return;
		}
		
		for(int i = start ; i < N ; ++i) {
			numbers[cnt] = inputs[i];
			nCr(cnt+1, i+1);
		}
	}
	
	public static int sumArr() {
		int result = 0;
		for(int i = 0 ; i < M ; ++i) {
			result += numbers[i];
		}
		return result;
	}
	

}
