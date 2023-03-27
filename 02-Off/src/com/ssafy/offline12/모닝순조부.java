package com.ssafy.offline12;
import java.io.*;
import java.util.*;

public class 모닝순조부 {
	static int N, R, totalCnt;
	static boolean[] visited;
	static int[] numbers, inputs;
	static int[] temparr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		R = Integer.parseInt(br.readLine());
		
		visited = new boolean[N];
		numbers = new int[R];
		inputs = new int[N];
		
		for(int i = 0 ; i < N ; ++i) {
			inputs[i] = i + 1;
		}
		temparr = Arrays.copyOf(inputs, N);
		nPr(0);
		System.out.println(totalCnt + "\n");
		totalCnt = 0;
		
		nPrBit(0, 0);
		System.out.println(totalCnt + "\n");
		totalCnt = 0;
		
		nCr(0, 0);
		System.out.println(totalCnt + "\n");
		totalCnt = 0;
		
		subsetBit();
		System.out.println();
		subsetRec(0);
		System.out.println();
		
		doNP();
		System.out.println();
		
		doNPC();
		System.out.println();
	}
	
	static void nPr(int cnt) {
		if(cnt == R) {
			totalCnt++;
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
	static void nPrBit(int cnt, int flag) {
		if(cnt == R) {
			totalCnt++;
			System.out.println(Arrays.toString(numbers));
			return;
		}
		for(int i = 0 ; i < N ; ++i) {
			if((flag & (1<<i)) != 0) continue;
			numbers[cnt] = inputs[i];
			nPrBit(cnt+1, (flag | (1 << i)));
		}
		
	}
	static void nCr(int cnt, int start) {
		if(cnt == R) {
			totalCnt++;
			System.out.println(Arrays.toString(numbers));
			return;
		}
		for(int i = start ; i < N ; ++i) {
			numbers[cnt] = inputs[i];			
			nCr(cnt+1, i + 1);
		}
		
	}
	static void subsetRec(int cnt) {
		if(cnt==N) {
			for(int i = 0 ; i < N ; ++i) {
				if(visited[i]) System.out.print(inputs[i] + " ");
			}
			System.out.println();
			return;
		}
		
		visited[cnt] = true;
		subsetRec(cnt+1);
		visited[cnt] = false;
		subsetRec(cnt+1);
	}
	static void subsetBit() {
		for(int i = 0 ; i < ( 1 << N ) ; ++i) {
			for(int j = 0 ; j < N; ++j) {
				if((i & (1 << j)) == 0) continue;
				
				System.out.print(inputs[j] + " ");
			}
			System.out.println();
		}
	}
	static boolean nextPerm(int[] inputs) {
		int n = inputs.length;
		
		int i = n - 1;
		while(i > 0 && inputs[i-1] >= inputs[i]) --i;
		if(i == 0) return false;
		
		int j = n-1;
		while(inputs[i-1] >= inputs[j]) --j;
		
		swap(inputs, i-1, j);
		
		int k = n - 1;
		while(i < k) swap(inputs ,i++, k--);
		
		return true;
	}
	static void swap(int[] data,int i, int j) {
		int temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}
	
	static void doNP() {
		do {
			for(int i = 0 ; i < N ; ++i)
				System.out.print(temparr[i] + " ");
			System.out.println();
		}while(nextPerm(temparr));
	}
	
	static void doNPC() {
		int[] flag = new int[N];
		for(int i = R-1 ; i < N ; ++i) {
			flag[i] = 1;
		}
		
		do {
			for(int i = 0 ; i < N ; ++i) {
				if(flag[i] == 1) System.out.print(inputs[i] + " ");
			}
			System.out.println();
		}while(nextPerm(flag));
	}
}
