package day06.linkedlist;

import java.io.*;
import java.util.*;


public class 점심순조부 {
	static int[] inputs, numbers;
	static boolean[] isSelected;
	static int N, R, totalCnt = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		R = Integer.parseInt(br.readLine());
		
		inputs = new int[N];
		numbers = new int[R];
		isSelected = new boolean[N];
		
		for(int i = 0 ; i  < N ; ++i) {
			inputs[i] = i+1;
		}
		
		nPr(0);
		System.out.println("일반순열  = " + totalCnt);
		totalCnt=0;
		nPIr(0);
		System.out.println("중복순열  = " + totalCnt);
		totalCnt=0;
		nCr(0,0);
		System.out.println("일반 조합 = " + totalCnt);
		totalCnt=0;
		nHr(0,0);
		System.out.println("중복 조합 = " + totalCnt);
		totalCnt=0;
		
		subsetRec(0);
		subsetBit();
		
	}
	private static void subsetBit() {
		for(int i = 0 ; i < ( 1 << N ) ; ++i) {
			for(int j = 0 ; j < N ; ++j) {
				if((i & (1 << j)) != 0) continue;
				
				System.out.print(inputs[j] + " ");
			}
			System.out.println();
		}
		
	}
	private static void subsetRec(int cnt) {
		if(cnt == N) {
			for(int i = 0 ; i < N ; ++i) {
				if(isSelected[i]) System.out.print(inputs[i] +" ");
			}
			System.out.println();
			return;
		}
		
		isSelected[cnt] = true;
		subsetRec(cnt+1);
		isSelected[cnt] = false;
		subsetRec(cnt+1);
	}
	private static void nHr(int cnt, int start) {
		if(cnt == R) {
			totalCnt++;
			System.out.println(Arrays.toString(numbers));
			return;
		}
		
		for(int i = start ; i < N ; ++i) {
			numbers[cnt] = inputs[i];
			nHr(cnt+1, i);
		}
	}
	private static void nCr(int cnt, int start) {
		if(cnt == R) {
			totalCnt++;
			System.out.println(Arrays.toString(numbers));
			return;
		}
		for(int i = start ; i < N ; ++i) {
			numbers[cnt] = inputs[i];
			nCr(cnt+1, i+1);
		}
	}
	private static void nPr(int cnt) {
		if(cnt == R) {
			totalCnt++;
			System.out.println(Arrays.toString(numbers));
			return;
		}
		
		for(int i = 0 ; i < N ; ++i) {
			if(isSelected[i]) continue;
			
			isSelected[i] = true;
			numbers[cnt] = inputs[i];
			
			nPr(cnt+1);
			isSelected[i] = false;
			
		}
		
	}
	private static void nPIr(int cnt) {
		if(cnt == R) {
			totalCnt++;
			System.out.println(Arrays.toString(numbers));
			return;
		}
		
		for(int i = 0 ; i < N ; ++i) {
			numbers[cnt] = inputs[i];
			nPIr(cnt+1);
		}
		
	}
}
