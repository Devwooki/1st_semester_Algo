package com.ssafy.offline16;

import java.io.*;
import java.util.*;

public class SWEA7465 {
	static int N, M;
	static int[] p;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; ++tc) {
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			p = new int[N+1];
			makeSet();
			for(int i = 0 ; i < M ; ++i) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				union(a,b);
			}
			int cnt = 0;
			for(int i = 1 ; i <= N ; ++i) {
				if(p[i] == i) cnt++;
			}
			sb.append("#" + tc + " " + cnt + "\n");
		}
		System.out.println(sb);
	}
	
	static void makeSet() {
		for(int i = 1 ; i <= N ; ++i) {
			p[i] = i;
		}
	}
	
	static int find(int a) {
		if(p[a] == a) return a;
		return p[a] = find(p[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) return false;
		
		p[bRoot] = aRoot;
		return true;
	}
/*
 *  갯수가 다른 것을 반환하는게 아닌 대표노드의 갯수를 출력하는 것임
	TC 2번도 M까지 탐색을 마치면 [0, 1, 1, 1, 1, 1, 3]형태인데
	find(6) -> 3, find(3) -> 1이므로 결국엔 무리는 1개만 존재함.
	static int countSet() {
		HashSet<Integer> hash = new HashSet<>();
		for(int i = 1 ; i <= N ; ++i) {
			hash.add(p[i]);
		}
		
		return hash.size();
	}
 */
}
