package com.ssafy.offline07;
import java.io.*;
import java.util.*;

/**
 즉 x == M, y == N에 도달할 경우 1로 바뀐다.

 하나씩 구하면 O(NM)이라는 시간 복잡도를 구하게 됨
 x, y의 값은 구하고자 하는 해에서 N과 M으로 나눈 나머지

 ex 10, 12
 1	1, 1
 2	2, 2
 3	3, 3
 ....
 13	3, 1
 ...
 23	3, 11

M이나 M중 하나를 기준으로 잡는다
cnt * M + x -> M의 값 + y한게 M, N
 */

public class BOJ6064 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());
		//한 사이클 N, M 최소공배수,
		for(int tc = 1 ; tc <= TC ; ++tc) {
			st = new StringTokenizer(br.readLine(), " ");
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			//최소공배수 구한다.
			int lcm = M * N / gcd(M,N);

			//몇 번 시행하는지 계산
			int cnt = 0;
			int result = -1;

			while( cnt * M < lcm){
				if((cnt * M + x - y)% N == 0){
					result = cnt * M + x;
					break;
				}
				cnt++;
			}

			sb.append(result + "\n");
		}
		System.out.println(sb);
	}
	
	static int gcd(int m, int n) {
		if(n == 0) return m;
		else return gcd(n, m%n);
	}
}
/*
O(MN)인 시간 복잡도, 모듈러 연산을 통해 간략화 해야한다.
int primeX = 0;
			int primeY = 0;
			while(cnt != lcm) {

				int tempx = primeX%M+1;
				int tempy = primeY%N+1;
				//if(x == (primeX%M) && y == (primeY%N)) {
				if(x == tempx && y == tempy) {
					break;
				}
				primeX += 1;
				primeY += 1;
				cnt++;
			}
			sb.append(cnt == lcm ? -1 +"\n" : (cnt+1) + "\n");
 */