package com.ssafy.offline01;

public class Test06 {

	public static void main(String[] args) {
		System.out.println(factorial(5));
		System.out.println(recFac(5));

	}

	private static long factorial(int num) {
		int result = 1;
		for(int i = num ; i > 0 ; --i) {
			result *= i;
		}
		return result;
	}
	
	private static long recFac(int num) {
		if(num == 0) return 1;
		return recFac(num-1) * num;
	}

}
