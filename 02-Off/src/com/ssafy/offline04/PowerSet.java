package com.ssafy.offline04;

import java.util.Scanner;

public class PowerSet {
	static int[] inputs;
	static int N;
	static boolean[] isSelected;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		inputs = new int[N];
		isSelected = new boolean[N];
		
		for(int i = 0 ; i < N; ++i) {
			inputs[i] = i+1;
		}
		
		powerSet(0);
	}

	private static void powerSet(int cnt) {
		if(cnt == N ) {
			for(int i = 0 ; i < N ; ++i){
				System.out.print((isSelected[i] ? inputs[i] : " ") + " ");
			}
			System.out.println();
			return;
		}
		
		isSelected[cnt] = true;
		powerSet(cnt+1);
		isSelected[cnt] = false;
		powerSet(cnt+1);
	}

}
