package com.ssafy.offline11;
import java.io.*;
import java.util.*;

public class BOJ1074 {
	static int N, R, C;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		int size = (int) Math.pow(2, N);

		
		draw(0,0,size, 0 );
		
	}
	
	static void draw(int x, int y, int n, int nowCnt) {
		//메모리초과 발생 -> 필요없는 영역은 그리지 않기.
		//if(!isIn(x, y, n, nowCnt)) return;
		
		
		//크기가 2이면 그리고 종료 
		if(n == 2) {
			for(int i = y ; i < y + 2 ; ++i) {
				for(int j = x ; j < x + 2 ; ++j) {
					nowCnt++;
					if(i == R && j == C) {
						System.out.println(nowCnt-1);
						return;
					}
				}
			}			
		}else {
			if(x <= C && C < x+ n/2 && y <= R && R < y + n/2) 
				draw(x, y, n/2, nowCnt + (n/2 * n/2) * 0);
			
			if(x + n/2 <= C && C < x + n && y<= R && R < y + n/2) 
				draw(x + n/2, y, n/2, nowCnt + (n/2 * n/2) * 1);
			
			if(x <= C && C < x + n/2 && y + n/2 <= R && R < y + n) 
				draw(x, y + n/2, n/2, nowCnt + (n/2 * n/2) * 2);
			
			if(x + n/2 <= C && C < x + n && y + n/2 <= R && R < y + n) 
				 draw(x + n/2, y + n/2, n/2, nowCnt + (n/2 * n/2) * 3);
		}
	}

}
