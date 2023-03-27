package com.ssafy.offline03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BOJ2798 {
	static int N, M, max = -1;
	static int[] cards;
	static int[] choice = new int[3];
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		cards = new int[N];
		//st = new StringTokenizer(br.readLine(), " ");
		cards = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				
		//blackjack(0,0,0);
		blackjack2(0,0);
		//iter();
		
		System.out.println(max);
	}
	private static void iter() {
      for(int i = 0 ; i < N-2 ; ++i) {
    	for(int j = i+1 ; j < N-1 ; ++j) {
    		for(int k = j+1 ; k < N ; ++k) {
            	int sum = cards[i] + cards[j] + cards[k];
            	
            	if(sum > M) continue;
            	max = Math.max(max, sum);
            }
        }
    }
	}
		
	private static void blackjack2(int cnt, int start) {
		if(cnt == 3) {
			int sum = Arrays.stream(choice).sum();
			if(sum > max && sum <= M) max = Math.max(sum, max);
		}
		
		for(int i = 0 ; i < N ; ++i) {
			choice[cnt] = cards[i];
			blackjack2(cnt+1, i+1);
		}
	}
	
	private static void blackjack(int idx, int cnt, int sum) {
		if(cnt == 3) {
			if(sum <= M ) {
				max = Math.max(max, sum);
				return;
			}
		}
		
		if(idx >= N || sum > M) return;
		
		blackjack(idx+1, cnt, sum);
		blackjack(idx+1, cnt+1, sum+cards[idx]);
	}
}
