package com.ssafy.offline07;
import java.io.*;
import java.util.*;

public class BOJ1676 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		/*
		 * 0이나오는 경우 -> 5와 2의 곱 = 10이 나오는 횟수에달림
		 * N!까지는 2가 5보다 훨씬 많으므로 가 몇 번 나오는지만 체크하면 됨
		 */
		int cnt = 0;
		while(N > 0) {
			cnt += N/5;
			N /= 5;
		}
		System.out.println(cnt);
	}
}
