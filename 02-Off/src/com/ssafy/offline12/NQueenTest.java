package com.ssafy.offline12;
import java.io.*;
import java.util.*;

public class NQueenTest {
	static int[] map;
	static int N, cnt;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		//놓아진 퀸 관리하기
		map = new int[N];
		
		
		getQueen(0);
		System.out.println(cnt);
	}
	static void getQueen(int now) {//놓으려는 퀸의 행 번호		
		//기저조건
		if(now == N) {
			cnt += 1;
			return;
		}
		for(int i = 0 ; i < N ; ++i) {
			map[now] = i;
			if(possiblity(now)) {
				getQueen(now+1);
			}
		}
	}
	private static boolean possiblity(int col) {
		for(int i = 0 ; i < col ; ++i) {			
			if(map[i] == map[col] ||
					Math.abs(map[i] - map[col]) == Math.abs(col-i)) return false;
		}
		return true;
	}
}
