package com.ssafy.offline10;
import java.io.*;
import java.util.*;

public class BOJ16435 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		int[] heights = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		
		Arrays.sort(heights);
		
		int cnt = 0;
		for(int i = 0 ; i < N ;++i) {
//			if(heights[i] <= L) {
//				cnt++;
//				L += 1;
//			}
			if(heights[i] > L) break;
			
			cnt++;
			L += 1;
				
		}
		System.out.println(L);
	}
}

