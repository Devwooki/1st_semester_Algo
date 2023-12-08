package com.ssafy.offline05;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

import javax.sound.midi.Soundbank;


public class 아침순열조합부분 {
	static boolean[] isSelected;
	static int[] numbers, inputs;
	static int N, R, totalCnt = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		R = Integer.parseInt(br.readLine());
		
		isSelected = new boolean[N];
		numbers = new int[R];
		inputs = new int[N];
		
		for(int i = 0 ;i < N ; ++i) {
			//inputs[i] = 2 * (i+1);
			inputs[i] = i+1;
		}
		
		System.out.println("순열");
		nPr(0);
		System.out.println("순열 갯수"+totalCnt);
		totalCnt = 0;
		
		System.out.println("중복순열");
		nPIr(0);
		System.out.println("중복순열 갯수"+totalCnt);
		totalCnt = 0;

		System.out.println("조합");
		nCr(0,0);
		System.out.println("조합 갯수"+totalCnt);
		totalCnt = 0;

		System.out.println("중복조합");
		nHr(0,0);
		System.out.println("중복조합 갯수"+totalCnt);
		totalCnt = 0;
		
		System.out.println("부부집합 재귀");
		subsetrec(0);		
		
		System.out.println("비트마스킹");
		bitMask();
		
 
	
	}
	private static void bitMask() {
		for(int i = 0 ; i < ( 1 << N ) ; ++i) {//표현할 수 있는 비트 자리 수
			for(int j = 0 ; j < N ; ++j) {	//부분 집합 크기 쪼개기
				
				if((i & ( 1 << j )) == 0) continue;
				//i와 1을 j만큼 밀어낸 것의 비트연산
				
				System.out.print(inputs[j] + " ");	
			}
			System.out.println();
		}
		
	}
	
	private static void subsetrec(int cnt) {
		if(cnt == N) {
			IntStream.range(0, N).filter(x -> isSelected[x]).forEach(num -> System.out.print(inputs[num] + " "));
			
			System.out.println();
			return;
		}
		
		isSelected[cnt] = true;
		subsetrec(cnt+1);
		isSelected[cnt] = false;
		subsetrec(cnt+1);
	}

	//중복 조합
	private static void nHr(int cnt, int start) {
		if(cnt == R ) {
			totalCnt++;
			System.out.println(Arrays.toString(numbers));
			return;
		}
		
		for(int i = start ; i < N ; ++i) {
			numbers[cnt] = inputs[i];
			nHr(cnt+1, i);
		}
		
	}

	//조합
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
	
	
	//순열
	private static void nPIr(int cnt) {
		if(cnt == R ) {
			totalCnt++;
			System.out.println(Arrays.toString(numbers));
			return;
		}
		
		for(int i = 0 ; i < N ; ++i) {
			numbers[cnt] = inputs[i];
			nPIr(cnt+1);
		}
		
	}
	//중복 순열
	private static void nPr(int cnt) {
		if(cnt == R ) {
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
	
	

}
