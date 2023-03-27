package com.ssafy.offline02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1244 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int[] lights = new int[N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1 ; i <= N ; ++i) {
			lights[i] = Integer.parseInt(st.nextToken());
		}
		
		int TC = Integer.parseInt(br.readLine());
		for(int tc = 0 ; tc < TC ; ++tc) {
			st = new StringTokenizer(br.readLine());
			int gender = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			//남학생1 num의 배수 ^=
			//여학생2 num중심 반전
			
			if(gender == 1) {
				for(int i = num ; i <= N ; i += num) {
					lights[i] ^= 1;
				}
			}else {
				int lo = num-1;
				int hi = num+1;	
				while( 1 <= lo && hi <= N) {
					if(lights[lo] != lights[hi]) break;
					
					lo--;
					hi++;
				}
				
				for(int i = lo+1 ; i < hi ; ++i) {
					lights[i] ^= 1;
				}
			}
		}
		
		for(int i = 1; i <= N ; ++i) {
			sb.append(lights[i] + " ");
			
			if(i%20 == 0) sb.append("\n");
		}
		
		System.out.println(sb);
	}
}
