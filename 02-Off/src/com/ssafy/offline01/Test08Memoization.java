package com.ssafy.offline01;

public class Test08Memoization {
	
	public static long[] memoi;
	public static int cnt1, cnt2;
	public static void main(String[] args) {
		memoi = new long[100];
		memoi[0] = 0;
		memoi[1] = 1;
		
		System.out.println(+fibo1(15));
		System.out.println("cnt1 " + cnt1 + ", ");
		System.out.println(fibo2(15));
		System.out.println("cnt2 " + cnt2 + ", ");
		
	}
	
	private static long fibo1(int n) {
		++cnt1;
		if(n < 2) return n;
		return fibo1(n-2) + fibo1(n-1);
	}
	
	private static long fibo2(int n) {
		++cnt2;
		if(n >= 2 && memoi[n] == 0) memoi[n] = (fibo2(n-2) + fibo2(n-1));
		
		return memoi[n];
	}

}
