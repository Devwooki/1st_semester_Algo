package com.ssafy.offline01;

public class Test03 {

	public static void main(String[] args) {
		System.out.println("반복" + solve1(10));
		System.out.println();
		System.out.println("재귀" + solve2(10));

	}
	private static int solve1(int num) {
		int sum = 0; 
		for(int i = 1 ; i <= num ; ++i) {
			sum += i;
		}
		return sum;
	}	

	private static int solve2(int num) {
		if(num == 1) return 1;		
		return solve2(num-1)+num; 
	}
}
