package com.ssafy.offline16;
import java.io.*;
import java.util.*;


public class SWEA3289 {
	static int N, M;
	static int[] parent;
	//합집합 0 a b
	//합쳐진건지 확인 1 a b
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for(int tc = 1 ; tc <= TC ; ++tc) {
			sb.append("#" + tc + " ");
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			parent = new int[N+1];
			makeSet();
			
			for(int i = 0 ; i < M ; ++i) {
				st = new StringTokenizer(br.readLine());
				int cmd = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				if(cmd == 0) {
					union(a,b);
				}else {
					if(check(a,b)) sb.append(1);
					else sb.append(0);
				}
			}
			
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	static void makeSet() {
		for(int i = 1 ; i <= N ; ++i) {
			parent[i] = i;
		}
	}
	
	static int findSet(int a) {
		if(parent[a] == a) return a;
		return parent[a] = findSet(parent[a]);
	}
//	static boolean union(int a, int b) {
//		int aRoot = findSet(a);
//		int bRoot = findSet(b);
//		
//		if(aRoot == bRoot) return false;
//		
//		parent[bRoot] = aRoot; 
//		return true;
//	}
	static void union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		parent[bRoot] = aRoot;
	}
	static boolean check(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if(aRoot == bRoot) return true;
		else return false;
	}
}
