package com.ssafy.offline10;
import java.io.*;
import java.util.*;

public class 모닝순조부10일차 {
	static int[] numbers, inputs;
	static int[] flag;
	static boolean[] visited;
	static int N, R, totalCNT;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		R = Integer.parseInt(br.readLine());
		
		numbers = new int[R];
		flag = new int[N];
		inputs = new int[N];
		visited = new boolean[N];
		
		for(int i = 0 ; i < N ;++i) {
			inputs[i] = i+1;
		}
				
		nPr(0);
		System.out.printf("순열 total : %d개\n\n", totalCNT);
		totalCNT = 0;
		
		nCr(0,0);
		System.out.printf("조합 total : %d개\n\n", totalCNT);
		totalCNT = 0;
		
		subSetRec(0);
		System.out.printf("부분집합  Rec : %d개\n\n", totalCNT);
		totalCNT = 0;
		
		subSetBit();
		System.out.printf("부분집합 Bit : %d개\n\n", totalCNT);
		totalCNT = 0;
		
		nPrBit(0,0);
		System.out.printf("순열 Bit : %d개\n\n", totalCNT);
		totalCNT = 0;
		
		nPP();
		System.out.printf("순열 NextPerm : %d개\n\n", totalCNT);
		totalCNT = 0;
		
		nPC();
		System.out.printf("조합 NextPerm total : %d개\n\n", totalCNT);
		totalCNT = 0;
		
		
		
//		System.out.println("=========번외========");
//		System.out.println("=========중복 순열========");
//		nPIr(0);
//		System.out.println("=========중복 조합========");
//		nHr(0,0);
	}
	private static void nPrBit(int cnt, int flag) {
//		if(cnt == R) {
//			totalCNT++;
//			System.out.println(Arrays.toString(numbers));
//			return;
//		}
//		for(int i = 0 ; i < N ; ++i) {
//			if((flag & (1 << i)) != 0) continue;
//			numbers[cnt] = inputs[i];
//			nPrBit(cnt+1 , flag | (1 << i));
//		}
		
		if(cnt == R) {
			System.out.println(Arrays.toString(numbers));
			return;
		}
		
		for(int i = 0 ; i < N ; ++i) {
			if((flag & (1 << i )) != 0 ) continue;
			numbers[cnt] = inputs[i];
			nPrBit(cnt+1, flag | (1 << i));
		} 
		
	}
	private static void nPC() {
		for(int i = N-1; i >= N-R ; --i) {
			flag[i] = 1;
		}
		
		
		do {
			for(int i = 0 ; i < N ; ++i) {
				if(flag[i] == 1) System.out.print(inputs[i] + " ");
			}
			System.out.println();
			totalCNT++;
		}while(nextPerm2(flag));
		
		
	}
	private static void nPP() {
		
		do {
			for(int i = 0 ; i < N ; ++i)System.out.print(inputs[i] + " ");
			System.out.println();
			totalCNT++;
		}while(nextPerm2(inputs));
		
	}
	private static boolean nextPerm(int[] data) {
		int n = data.length;
		
		int i = n - 1;
		while(i > 0 && data[i-1] >= data[i])--i;
		if(i == 0 ) return false;
		
		int j = n - 1;
		while(data[i-1] >= data[j] ) --j;
		
		swap(data, i-1, j);
		
		
		int k = n - 1;
		while(i < k) swap(data, i++, k--);
		
		return true;
	}
	
	private static boolean nextPerm2(int[] data) {
		int n = data.length;
		
		int i = n - 1;
		while(i > 0 && data[i-1] >= data[i]) --i;
		if(i == 0) return false;
		
		int j = n - 1;
		while(data[i-1] >= data[j]) --j;
		
		swap(data,i-1,j);
		
		int k = n-1;
		while(i < k) swap(data, i++, k--);
		return true;
	}
	private static void swap(int[] data, int i , int j) {
		int temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}
	private static void subSetBit() {		
		for(int i = 0 ; i < (1 << N) ; ++i) {
			for(int j = 0 ; j < N ; ++j) {
				if( (i & ( 1 << j )) == 0) continue;
				System.out.print(inputs[j] + " ");
			}
			System.out.println();
		}
	}
	
	private static void subSetRec(int cnt) {
		if(cnt == N ) {
			for(int i = 0 ; i < N ; ++i) {
				if(visited[i]) System.out.print(inputs[i] + " ");
			}
			System.out.println();
			return;
		}
		
		visited[cnt] = true;
		subSetRec(cnt+1);
		visited[cnt] = false;
		subSetRec(cnt+1);
		
	}
	
	private static void nCr(int cnt, int start) {
		if(cnt == R) {
			totalCNT++;
			System.out.println(Arrays.toString(numbers));
			return;
		}
		
		for(int i = start ; i < N ; ++i) {			
			numbers[cnt] = inputs[i];
			nCr(cnt+1, i + 1);
		}
		
	}
	
	private static void nPr(int cnt) {
		if(cnt == R) {
			totalCNT++;
			System.out.println(Arrays.toString(numbers));
			return;
		}
		
		for(int i = 0 ; i < N ; ++i) {
			if(visited[i]) continue;
			
			numbers[cnt] = inputs[i];
			visited[i] = true;
			
			nPr(cnt+1);
			visited[i] = false;
		}
		
	}
}
