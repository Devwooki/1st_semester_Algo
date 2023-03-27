package com.ssafy.offline16;
import java.io.*;
import java.util.*;

public class 서로소집합 {
	static int N;
	static int[] p, p2;//부모 배열
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		
		N = 5;
		p = new int[N];		
		//서로소 첫 번재 작업 makeSet; - 집합 생성
		makeSet();
		System.out.println(Arrays.toString(p));
		
		//서로소 집합 만들기- union
//		union(0,1);
//		System.out.println(Arrays.toString(p));
		
		union(3,4);
		System.out.println(Arrays.toString(p));
		union(2,4);
		System.out.println(Arrays.toString(p));
		union(1,4);
		System.out.println(Arrays.toString(p));
		union(0,4);
		System.out.println(Arrays.toString(p));
		
		/*
		 * 0 0 1 2 3 -> find(4)하면 3을, find(3)을하니 2를, find(2)하니 1, find(1)하니0을 부름
		 * -> 재귀로 find(2) : find(1)의 리턴 0
		 * -> find(3) : find(2)의리턴 =  find(1)의 리턴 0
		 * 압축하지 않아 O(N)의 시간복잡도가 나타남
		 * 최초로 탐색할 때 p[find(a)]를 넣음으로써 압축하 수 있다.
		 */
		
		
		System.out.println("=================같은 집합인지 체크해서 반환==================");
		N = 5;
		p2 = new int[N];		
		makeSet();
		
		System.out.println(union2(3,4));
		System.out.println(Arrays.toString(p));
		System.out.println(union2(3,4));
		union2(2,4);
		System.out.println(Arrays.toString(p));
		union2(1,4);
		System.out.println(Arrays.toString(p));
		union2(0,4);
		System.out.println(Arrays.toString(p));
		
	}
	
	private static void union(int a, int b) {
		// 바로 합치기 금지, 대표자를 추출해서 대표자가 다른 경우에만 합치기
		
		int aRoot = find(a);
		int bRoot = find(b);
	
		p[find(b)] = find(a);
		
	}
	
	//두 요소가 같은 집합인지 아닌지 판단하는 union
	private static boolean union2(int a, int b) {
		// 바로 합치기 금지, 대표자를 추출해서 대표자가 다른 경우에만 합치기
		
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) return false; //같은 집합이면 false반환하게
		
		p[find(b)] = find(a); //다른 집합이니까 작업 후 true 반환
		return true;
		
	}
	
	
	//요소 값을 받아, 요소의 대표를 찾음
	private static int find(int a) {
		if(a == p[a]) return a; //a가 해당 집합의 대표자일 때
		
		//else return find(p[a]);
		/*
		 * 	[0, 1, 2, 3, 4]
			[0, 1, 2, 3, 3]
			[0, 1, 2, 2, 3]
			[0, 1, 1, 2, 3]
			[0, 0, 1, 2, 3]
		 */
		
		return p[a] = find(p[a]);
		/*
			[0, 1, 2, 3, 4]
			[0, 1, 2, 3, 3]
			[0, 1, 2, 2, 3]
			[0, 1, 1, 2, 2]
			[0, 0, 1, 2, 1] 으로 압축됨
		 */
		
	}

	private static void makeSet() {
		//각 집합의 대표는 자기 자신이 됨
		for(int i = 0 ; i < N ; ++i) {
			//인덱스 번호랑 1:1 매칭 or 자신-1 == 인덱스번호 등의 로 표현
			p[i] = i;
		}
		
	}
}
