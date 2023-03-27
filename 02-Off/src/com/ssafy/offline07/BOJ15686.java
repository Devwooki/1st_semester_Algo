package com.ssafy.offline07;

import java.io.*;
import java.util.*;

public class BOJ15686 {
	static List<int[]> home = new LinkedList<>();
	static List<int[]> chicken = new LinkedList<>();
	static int[] chickenSelec;
	static int min = Integer.MAX_VALUE;
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; ++j) {
				String str = st.nextToken();
				if (str.equals("2")) { // 치킨집들 저장
					chicken.add(new int[] { i, j });
				} else if (str.equals("1")) { // 사람 사는 곳 저장
					home.add(new int[] { i, j });
				}
			}

		}
		
		chickenSelec = new int[M];
		nCr(0, 0);
		System.out.println(min);
	}

	// 치킨집 끼리는 조합, 사람들 끼리는 브루트 포스
	static void nCr(int cnt, int start) {
		if (cnt == M) {
			int distance = 0;
			
			/**
			 * 치킨집에서 집을 계산 -> 각 집별로 독립적으로 계산하기에 누적되지 않음
			 * (치킨집은 1개의 집만)
			 * 집은 여러개의 치킨집을 통해 도시의 치킨거리를 구할 수 있기 때문에 집-> 치킨 순으로
			 */
			for (int[] h : home) {
				int temp = Integer.MAX_VALUE;
				for (int i = 0; i < M; ++i) {
					int[] store = chicken.get(chickenSelec[i]);
					temp = Math.min(temp, Math.abs(store[0] - h[0]) + Math.abs(store[1] - h[1]));
				}
				distance += temp;
			}
			min = Math.min(min, distance);

			//System.out.println(Arrays.toString(chickenSelec) + " " + distance);
			return;
		}

		for (int i = start; i < chicken.size(); ++i) {
			chickenSelec[cnt] = i;
			nCr(cnt + 1, i + 1);
		}

	}
}
