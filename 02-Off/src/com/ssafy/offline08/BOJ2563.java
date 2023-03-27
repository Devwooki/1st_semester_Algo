package com.ssafy.offline08;

import java.io.*;
import java.util.*;


public class BOJ2563 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int[][] area = new int[101][101];
		int cnt = 0;
		for(int i = 0 ; i < N ; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			
			for(int j = A ; j < A+10 ; j++) {
				for(int k = B ; k < B+10 ; k++) {
					if(area[j][k] > 0) {
						continue;
					}
					area[j][k]++;
					cnt++;
					
				}
			}
		}
		System.out.println(cnt);
	}
}
