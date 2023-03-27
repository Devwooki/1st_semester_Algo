package com.ssafy.offline04;

import java.io.*;
import java.util.*;

public class BOJ12891 {
	public static int[] pwCondition, cntACGT;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int S = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		
		String dna = br.readLine();
		
		//비밀번호 조건
		pwCondition = new int[4];
		pwCondition = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		//부분 문자열 ACGT 등장 횟수
		cntACGT = new int[4];
		
		//첫 번째 슬라이딩 윈도우
		for(int i = 0 ; i < P ; ++i) {
			char c = dna.charAt(i);
			plus(c);
		}
		
		int result = 0;
		if(check()) result++;
		
		//두 번째 부터 슬라이딩 적용
		for(int i = P ; i < S ; ++i) {
			char start = dna.charAt(i-P);
			minus(start);
			char end = dna.charAt(i);
			plus(end);
			
			check();
			
			if(check()) result++;
		}
		
		
		System.out.println(result);
	}
	
	public static void plus(char c) {
		switch(c) {
		case 'A' : cntACGT[0]++; break;
		case 'C' : cntACGT[1]++; break;
		case 'G' : cntACGT[2]++; break;
		case 'T' : cntACGT[3]++; break;
		}
	}
	
	public static void minus(char c) {
		switch(c) {
		case 'A' : cntACGT[0]--; break;
		case 'C' : cntACGT[1]--; break;
		case 'G' : cntACGT[2]--; break;
		case 'T' : cntACGT[3]--; break;
		}
	}
	
	public static boolean check() {
		int cnt = 0;
		for(int i = 0 ; i < 4 ; ++i) {
			//부분 문자열의 수가 비밀번호 조건 보다 많을 경우 맞음
			if(cntACGT[i] >= pwCondition[i]) cnt++; 
		}
		if(cnt == 4) return true;
		else return false;
	}
}
