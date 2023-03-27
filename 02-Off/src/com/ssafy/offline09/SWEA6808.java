package com.ssafy.offline09;
import java.io.*;
import java.util.*;
public class SWEA6808 {
	//높은 카드를 낸 사람이 적힌수 합 만큼 점수 얻고, 낮으면 탈락
	//총점 높으면 승리, 동점 무승부
	//규영이 카드가 주어짐 -> 인영이는 규영이와 겹치지 않는 숫자
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		
		for(int tc= 1 ; tc <= TC ; ++tc) {
			 
			 boolean[] cards = new boolean[19];
			 int[] kyu = new int[9];
			 int[] inyoung = new int[9];
			 
			 st = new StringTokenizer(br.readLine(), " ");
			 for(int i = 0 ; i < 9 ;++i) {
				 kyu[i] = Integer.parseInt(st.nextToken());
				 cards[kyu[i]] = true;		 
			 }
			 
			 int icnt = 0;
			 for(int i = 1 ; i <= 18 ; ++i) {
				 if(!cards[i]) inyoung[icnt++] = i;
			 }
			 
			 int win = 0;
			 do {
				 int sumK = 0;
				 int sumI = 0;
				 
				 for(int i = 0 ; i < 9 ; ++i) {
					 if(kyu[i] > inyoung[i]) sumK += (kyu[i] + inyoung[i]);
					 else sumI += (kyu[i] + inyoung[i]); 
				 }
				 
				 if(sumK > sumI) win++;
			 }while(nextPerm(inyoung));
			 
			 sb.append("#" + tc + " " + win + " " + (facto(9)- win) +"\n");
		}
		System.out.println(sb.toString());
	}
	
	static int facto(int n) {
		if(n == 0) return 1;
		return n * facto(n-1);
	}
	
	static boolean nextPerm(int[] inputs) {
		int n = inputs.length;
		
		int i = n-1;
		while( i > 0 && inputs[i-1] >= inputs[i]) i -=1;
		if(i == 0) return false;
		
		
		int j = n-1;
		while (inputs[i-1] >= inputs[j]) j -= 1;
		
		swap(inputs, i-1, j);
		
		int k = n-1;
		while(i < k) swap(inputs, i++, k--);
		
		
		return true;
	}
	static void swap(int[] inputs, int i, int j) {
		int temp = inputs[i];
		inputs[i] = inputs[j];
		inputs[j] = temp;
	}
}
