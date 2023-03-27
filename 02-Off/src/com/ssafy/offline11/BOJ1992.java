package com.ssafy.offline11;
import java.io.*;
import java.util.*;



public class BOJ1992 {
	static int[][] map;
	static int N;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i = 0 ; i < N ; ++i) {
			String str = br.readLine();
			for(int j = 0 ; j < N ; ++j)
				map[i][j] = str.charAt(j) - '0';
		}
		
		sb.append("(");
		dc(0,0, N);
		sb.append(")");
		System.out.println(sb.toString());
	}
	
	static void dc(int x, int y, int n) {
		if(check(x,y,n)) {
			sb.append(map[y][x]);
		}else {
			sb.append("(");
			for(int i = 0 ; i < 2 ; ++i) {
				for(int j = 0 ; j < 2 ; ++j) {
					dc(x + j * n/2 , y + i * n /2, n/2);
				}
			}
			sb.append(")");
		}
		
	}
	static boolean check(int x, int y, int n) {
		int start = map[y][x];
		for(int i = y ; i < y + n ; ++i) {
			for(int j = x ; j < x + n ; ++j) {
				if(start != map[i][j]) return false;
			}
		}
		return true;
	}
}
