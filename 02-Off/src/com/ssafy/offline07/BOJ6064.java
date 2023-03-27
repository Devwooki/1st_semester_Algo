package com.ssafy.offline07;
import java.io.*;
import java.util.*;


public class BOJ6064 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());
		//한 사이클 N, M 최소공배수,

		for(int tc = 1 ; tc <= TC ; ++tc) {
			st = new StringTokenizer(br.readLine(), " ");
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			int lcm = M * N / gcd(M,N); 
			//System.out.println(lcm);
			int cnt = 0;
			int primeX = 0;
			int primeY = 0;
			while(cnt != lcm) {

				int tempx = primeX%M+1;
				int tempy = primeY%N+1;
				//if(x == (primeX%M) && y == (primeY%N)) {
				if(x == tempx && y == tempy) {
					break;
				}
				primeX += 1;
				primeY += 1;
				cnt++;
			}
			sb.append(cnt == lcm ? -1 +"\n" : (cnt+1) + "\n");
		}
		System.out.println(sb);
	}
	
	static int gcd(int m, int n) {
		if(n == 0) return m;
		else return gcd(n, m%n);
	}
}
