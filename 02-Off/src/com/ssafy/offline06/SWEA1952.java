package com.ssafy.offline06;
import java.io.*;
import java.util.*;

public class SWEA1952 {
	static int[] months = new int[12];
	static int[] prices = new int[4];
	//static int day, month, threeM, year;
	static int answer;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for(int tc = 1 ; tc <= TC ; ++tc ) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0 ; i < 4 ; ++i) {
				prices[i] = Integer.parseInt(st.nextToken());
			}
			/*
			 * 0 : day
			 * 1 : month
			 * 2 : 3month
			 * 3 : year
			 */
			
			
			st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0 ; i < 12 ; ++i) {
				months[i] = Integer.parseInt(st.nextToken());	
			}
			
			answer = prices[3];
			cheapest(0, 0);
			
			sb.append("#" + tc + " " + answer + "\n");
			answer = 0;
		}
		System.out.println(sb);

		
		
	}
	static void cheapest(int m, int price) {
		if(m>11) {
			answer = Math.min(price, answer);
			return;
		}
		//수영하는 달 일때
		if(months[m] > 0) {
			//일마다
			cheapest(m+1, price + (months[m] * prices[0]));
			//월마다
			cheapest(m+1, price + prices[1]);
			//3달마다
			cheapest(m+3, price + prices[2]);
		}else {//달이 아니면 스킵
			cheapest(m+1, price);
		}
		
	}
	
}
