package com.ssafy.offline04;

import java.io.*;
import java.util.*;


public class 아침순조부 {
	static int N, R; //부분 집합만 사용할 경우 R이 필요없음
	static int[] numbers, inputs; // input의 인덱스는 비트에 담긴 거와 같다.. 어렵누
	static boolean[] isSelected; //선택 요소는 비트가 판단하므로 isSelected도 필요 없음
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		R = Integer.parseInt(br.readLine());
		
		numbers = new int[R];
		inputs = new int[N];
		isSelected = new boolean[N];
		
		inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		
		
		System.out.println("순열");
		nPr(0);
		System.out.println("조합");
		nCr(0, 0);
		System.out.println("부분집합 - 재귀");
		subSetRec(0);
		System.out.println("부분집합 - 비트");
		subSetBit();
	}
	private static void subSetBit() {
		for(int i = 0 ; i < (1 << N) ; ++i) { //부분 집합 수는 숫자값 2^N개
											// i가 1부터 시작하면 공집합은 제거됨. 선택하지 안흐니까
			
			//숫자마다 체크를 해줘야하니까 N번
			for(int j = 0;  j < N ; ++j) { //원소 수 만큼 비트 비교
				//0이면 선택 안된거니 패스
				if( (i & ( 1 << j)) == 0) continue; //i의 j번째 비트가 1이면 j번째 원소 출력
				
					
				System.out.print(j + " ");
			}
			System.out.println();
		}
		
	}
	private static void subSetRec(int cnt) {
		if(cnt == N ) {
			for(int i = 0 ; i < N ; ++i) {
				if(isSelected[i]) System.out.print(inputs[i] + " ");
			}
			System.out.println();
			return;
		}
		
		isSelected[cnt] = true;
		subSetRec(cnt+1);
		isSelected[cnt] = false;
		subSetRec(cnt+1);
		
	}
	private static void nCr(int cnt, int start) {
		// TODO Auto-generated method stub
		
	}
	private static void nPr(int cnt) {
		// TODO Auto-generated method stub
		
	}
}
