package com.ssafy.offline01;

/**
 * n개중에 k개를 뽑는 조합의 경우 -> nCk
 * 
 * 1. 4번째를 뽑는 경우
 * 	-> 3개 뽑을 때 3C2
 * 	-> 2개 뽑을 때 2C1
 * .. * 
 * 2. 4번째에 뽑지 않는 경우
 *  _. 3개 뽑을 때 3개 뽑은 3C3
 *  ..
 *  
 *  결론 nCk = n-1Ck-1 + n-1Ck;
 *
 */
public class Test07RecursiveCombination {
	public static void main(String[] args) {
		System.out.println(combination(5,3));
	}
	
	private static int  combination(int n, int k) {
		if(n == k || k == 0) return 1;
		return combination(n-1, k-1) + combination(n-1, k);
	}
}
