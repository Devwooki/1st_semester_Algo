package com.ssafy.offline11;
import java.io.*;
import java.util.*;


public class BOJ1654 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		long K = Integer.parseInt(st.nextToken());
		long N = Integer.parseInt(st.nextToken());
		long[] arr = new long[(int) K];

		long hi = Long.MIN_VALUE;
		for(int i = 0 ; i < K ; ++i) {
			arr[i] = Integer.parseInt(br.readLine());
			hi = Math.max(hi,arr[i]);
		}
		

		
		//
		long lo = 1;
		long mid;
		
		while(lo <= hi) {
			mid = lo + (hi - lo) / 2;
			
			long sum = getSum(arr, K , mid);			
//			if(sum == N) {
//				System.out.println(mid);
//				return;
//			}
			//sum == N일 때 출력 -> sum 갯수만 같으면 출력하기에 문제에서 요구하는
			//최대 높이가 되지 않음. 최소 높이
			//잘라야할 나무 높이를 계속 올려가며 sun == N이 되는 것을찾음
			//결과적으로 1 1, 5에서 lo = 5, hi =5를 가르키며, mid = 5일때 한 번 더 이분탐색 실시
			//lo=6이되는 순간 종료 -> hi와 lo,mid는 5를가르키고 있음 hi출력
						
			//너무 크게 잘라 개수가 부족 -> 얇게 자르기
			if(sum < N) hi = mid -1;
			
			//너무 잘게 잘라 개수가 많음 -> 굵게 자르기
			else if(sum >= N) lo = mid +1;
		}
		System.out.println(hi);
	}
	static long getSum(long[] arr, long k, long height) {
		long result = 0;
		for(int i = 0 ; i < k ; ++i) {
			result += (arr[i]/height);
		}
		return result;
	}
}
