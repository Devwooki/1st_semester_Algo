package com.ssafy.offline10;

import java.io.*;
import java.util.*;

public class BOJ15686 {
	static int N, M, min = Integer.MAX_VALUE;
	static int[][] map;
	static int[] numbers;
	static List<int[]> chicken, home;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		numbers = new int[M];
		home = new ArrayList<>();
		chicken = new ArrayList<>();

		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; ++j) {
				int x = Integer.parseInt(st.nextToken());
				if (x == 1) {
					home.add(new int[] { i, j });
				} else if (x == 2) {
					chicken.add(new int[] { i, j });
				}
			}
		}
		combination(0, 0);
		System.out.println(min);

	}
	
	/**
	 * 
	 * @param cityChicken : 도시 치킨 거리 : 치킨거리가 최소인 것들의 합에서 또 최소
	 * @param chickenDis : 치킨 거리
	 * @param chickenDisMin : 치킨 거리 최솟값
	 * @param min : 찐 최종
	 */
	static void combination(int cnt, int start) {
		if (cnt == M) {
			int cityChicken = 0;
			for (int j = 0; j < home.size(); ++j) {
				int chickenDis = 0;
				int chickenDisMin = Integer.MAX_VALUE;
				for (int i = 0; i < M; ++i) {
					chickenDis = Math.abs(chicken.get(numbers[i])[0] - home.get(j)[0])
								+ Math.abs(chicken.get(numbers[i])[1] - home.get(j)[1]);
					
					chickenDisMin = Math.min(chickenDis, chickenDisMin);
				}
				cityChicken += chickenDisMin;
			}
			min = Math.min(min, cityChicken);
			return;
		}
		// 치킨집 리스트 중 i번째 치킨집을 선정해서 조합 구성
		for (int i = start; i < chicken.size(); ++i) {
			numbers[cnt] = i;
			combination(cnt + 1, i + 1);
		}
	}

}
