package com.ssafy.offline09;

import java.io.*;
import java.util.*;


public class SWEA4012 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for(int tc= 1 ; tc <= TC ; ++tc) {
			 int N  = Integer.parseInt(br.readLine());
			 
			 int[][] map = new int[N][N];
			 for(int i = 0 ; i < N ; ++i) {
				 map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			 }
			 
			 int[] flag = new int[N];
			 for(int i = N/2 ; i < N ; ++i ) {
				 flag[i] = 1;
			 } 

			 int min = Integer.MAX_VALUE;
			 
			 //flag 0011과 1100이 겹침.. 어떻게 하면 NP로 더 좋은 코드를 짤 수 있을까
			 //일반 조합이 best같음
			 do {

				 min = Math.min(min, getMin(flag, map));				 
				 
			 }while(np(flag));
			 
		
			 sb.append("#" + tc + " " + min + "\n");
		}
		System.out.println(sb.toString());
	}
	static boolean np(int[] inputs) {
		int n = inputs.length;
		
		int i = n - 1;
		while(i > 0 && inputs[i-1] >= inputs[i] ) i -= 1;
		if(i == 0 ) return false;
		
		int j = n - 1;
		while(inputs[i-1] >= inputs[j]) j -= 1;
		
		swap(inputs, i-1, j);
		
		int k = n -1;
		while(i < k) {
			swap(inputs, i++, k--);
		}
		
		return true;
	}
	static void swap(int[] inputs, int i, int k) {
		int temp = inputs[i];
		inputs[i] = inputs[k];
		inputs[k] = temp;
	}
	static int getMin(int[] flag, int[][] map) {
		 List<Integer> S1List = new LinkedList<>();
		 List<Integer> S2List = new LinkedList<>(); 
		 
		 int S1 = 0;
		 int S2 = 0;
		 
		 int N = flag.length;
		 for(int i = 0 ; i < flag.length ; ++i) {
			 if(flag[i] == 0) S1List.add(i);
			 else S2List.add(i);
		 }
		 
		 for(int i = 0 ; i < S1List.size(); ++i) {
			 for(int j = 0 ; j < S1List.size() ; ++j) {
				 if(i == j ) continue;
				 S1 += map[S1List.get(i)][S1List.get(j)];
				 S2 += map[S2List.get(i)][S2List.get(j)];
			 }
		 }
		 
		 
		 return Math.abs(S1-S2);
	}
	

}
