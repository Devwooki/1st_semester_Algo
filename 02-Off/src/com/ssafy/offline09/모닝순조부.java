package com.ssafy.offline09;
import java.io.*;
import java.util.*;


public class 모닝순조부 {
	static int[] inputs, numbers;
	static boolean[] visited;
	static int N,R;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		R = Integer.parseInt(br.readLine());
		
		inputs = new int[N];
		numbers = new int[R];
		visited = new boolean[N];
		
		for(int i = 0 ; i < N ; ++i) {
			inputs[i] = i+1;
		}
		nPr(0);
		visited = new boolean[N];
		System.out.println();
		
		nPrBit(0,0);
		visited = new boolean[N];
		System.out.println();
		
		nCr(0, 0);
		visited = new boolean[N];
		System.out.println();
		subsetBit();
		visited = new boolean[N];
		System.out.println();
		
		System.out.println("Rec");
		subsetRec(0);
		visited = new boolean[N];
		System.out.println();
		
	}
	static void nPr(int cnt) {
		if(cnt == R ) {
			System.out.println(Arrays.toString(numbers));
			return;
		}
		
		for(int i = 0 ; i < N ; ++i) {
			if(visited[i]) continue;
			
			visited[i] = true;
			numbers[cnt] = inputs[i];
			
			nPr(cnt+1);
			visited[i] = false;
		}
	}
	
	static void nPrBit(int cnt, int flag) {
		if(cnt == R ) {
			System.out.println(Arrays.toString(numbers));
			return;
		}
		
		for(int i = 0 ; i < N ; ++i) {		
			if((flag & (1 << i)) != 0) continue;
			
			numbers[cnt] = inputs[i];
			nPrBit(cnt+1, flag|( 1 << i));

		}
	}
	
	
	static void nCr(int cnt, int start) {
		if(cnt == R ) {
			System.out.println(Arrays.toString(numbers));
			return;
		}
		
		for(int i = start ; i < N ; ++i) {
			numbers[cnt] = inputs[i];
			nCr(cnt+1, i + 1);
		}
	}
	
	static void subsetRec(int cnt) {
		if(cnt == N) {
			for(int i = 0 ; i < N ; ++i) {
				if(visited[i]) System.out.print(inputs[i] + " ");
			}
			
			
			System.out.println();
			return;
		}
		
		visited[cnt] = true;
		subsetRec(cnt + 1);
		visited[cnt] = false;
		subsetRec(cnt + 1);
		
	}
	
	static void subsetBit() {
		for(int i = 0 ; i < (1 << N ) ; ++i) {
			for(int j = 0 ; j < N ; ++j) {
				if( (i & ( 1 << j )) != 0) continue;
				
				System.out.print(inputs[j] +" ");
			}
			System.out.println();
		}
	}
}

