package com.ssafy.offline04;

import java.io.*;
import java.util.*;

public class BOJ2023 {
	static int N;
	static StringBuilder sb = new StringBuilder();
	static int[] fstPrime = {2,3,5,7};
	static int[] nthPrime = {1,3,7,9};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		if(N == 1) {
			for(int a : fstPrime) {
				sb.append(a + "\n");
			}
			System.out.println(sb);
			return;
		}
		
		for(int a : fstPrime) {
			getMystery(a, 1);
		}
		
		System.out.println(sb);
	}
	
	private static void getMystery(int num, int cnt) {
		//숫자자 길이(cnt)가 N과 같으면
		if(cnt == N) {
			//소수인지 판단 출력
			if(isPrime(num)) sb.append(num + "\n");
			return;	
		}
		
		for(int a : nthPrime) {
			int nextNum = num * 10 + a;
			if(isPrime(nextNum)) getMystery(nextNum, cnt+1);
		}
	}
	
	private static boolean isPrime(int num) {
		if(num ==2) return true;
		
		for(int i = 3 ; i<= Math.sqrt(num) ; ++i) {
			if(num%i == 0) return false;
		}
		return true;
	}
}
