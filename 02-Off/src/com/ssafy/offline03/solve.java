package com.ssafy.offline03;

import java.io.*;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class solve {
	/*
	 *  1 = 1					1
	 *  2 = 1^2 1^2				2
	 *  3 = 1^2 1^2 1^2			3
	 *  4 = 2^2					1
	 *  5 = 2^2 1^2				2
	 *  6 = 2^2 1^2 1^2			3
	 *  7 = 2^2 1^2 1^2 1^2		4
	 *  8 = 2^2 2^2				2
	 *  9 = 3^2					1
	 *  10 = 3^2 1^2			2
	 *  11 = 3^2 1^2 1^2 1		3
	 *  12 = 3^2 1^2 1^2 1^2	
	 *     = 2^2 2^2 2^2		3
	 *     
	 * dp[N] : N 제곱수들의 합
	 * = dp[n^2] + dp[N-n^2]
	 * = dp[N-n^2] + 1
	 * 
	 * 어떤 자연수 n을 골라 dp[N]을 구할 수 있는지 알 수 없음
	 * -> N <= n^2인 모든 n에 대해서 dp[N-n^2]를 구해서 최솟값을 갱신해야함.
	 * 그리고 그 값에 1을 더하면 dp[N]의 최솟값을 구할 수 있음 
	 * 
	 * 예시
	 * N = 27 -> n후보 5,4,3,2,1
	 * (이때 N = 26의 n후보도 5,4,3,2,1임 -> dp[1]+dp[25], dp[4] + dp[22],
	 * 								   dp[9] + dp[17], dp[16] + dp[10], dp[25] + dp[1] 중
	 * 									최솟값이 담겨 있음
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N+1];
		//dp[N] : 까지 dp값 저장
		dp[0] = 0;
		dp[1] = 1;
		for(int i = 2 ; i <= N ; ++i) {
			int min = 56789;
			//어떤 수 i의 최적 해 : i보다 작은 모든 제곱수 들 중 
			//				가장 작은 i - (제곱수 : j*j) 작은해 +1
			
			for(int j =  1 ; j*j <= i ; ++j) {

				/* 예시 N = 27임
				 * n=1 -> dp[1] + dp[26] = 1 + dp[26]
				 * n=2 -> dp[4] + dp[23] = 1 + dp[23]
				 * n=3 -> dp[9] + dp[18] = 1 + dp[18]
				 * n=4 -> dp[16] + dp[11] = 1 + dp[11]
				 * n=5 -> dp[25] + dp[2] = 1 + dp[2]
				 * 으로 표현할 수 있다.
				 *
				 * d[2], d[11], d[18], dp[23], dp[26]중에 제일 작은 값을 min에 저장하고
				 * d[1], d[4], d[9], d[16], d[25]는 값이 1이니까 +1을 해준다.
				 */
				min = Math.min(dp[i-j*j], min);
			}
			dp[i] = min+1;
		}		

		System.out.println(dp[N]);
		return;
		
	}

}
