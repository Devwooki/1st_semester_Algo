package com.ssafy.offline19;
import java.io.*;
import java.util.*;


/**
 테트로 미노는 총 5개의 종류가 있다.
 5개중 1개를 선택해서 배치,
 회전이나 대칭 시켜도 된다.

 -> 테트로미노가 놓인 합의 최댓값 출력
 ㅡ : 한 방향으로만 dfs
 사각형 :
 */

/*
탐색 순서 정의하기
도형 선택 -> 0~5까지, 0 : 직선, 1 : 정사각형, 2 : ㄱ, 3 : 번개, 4 : ㅗ
	0,0부터 도형 모양에 맞게 탐색
		- 도형 모양따라 탐색 종료 -> 대소비교 수행

	탐색끝나면 회전 시키기
		- 0 : 직선 : 90도 1번
		- 1 : 정사각형 : 회전 패스
		- 2 : ㄱ 모양 : 회전 후 대칭
		- 3 : 번개 : 회전 2번, 대칭 1번
		- 4 : 회전 4번

==============
누적합으로 풀 수 있지 않을까란 미친 생각 시작
직사각형
정사각형 -> 그대로 구함

번개, ㄱ, ㅗ

번개

*/

public class BOJ14500 {
	static int N, M;
	static int[][] map;
	static int[][] sumArr;
	static int[] rotate = {0,1,2,3}; // 0 -> 3 : 12시부터 시계방향 (상 우 하 좌)
	static int[][][] remove = {
			{{0,0},{0,1}},{{0,1},{0,2}},{{1,0},{1,1}},{{1,1},{1,2}}, // ㄱ자 관련
			{{0,0},{0,2}},{{1,0},{1,2}},         // ㅗ 관련
			{{0,0},{1,2}},{{1,0},{0,2}}         // 번개모양 관련
	};

	static int result = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		simulation();

		System.out.println(result);
	}

	private static void simulation() {
		//누적합 구하기
		sumArr = new int[N+1][M+1];

		for(int i = 0 ; i < N ; ++i){
			for (int j = 0; j < M; ++j) {
				sumArr[i+1][j+1] = sumArr[i+1][j] + sumArr[i][j+1] - sumArr[i][j] + map[i][j];
			}
		}

		for(int i = 1 ; i <= N ; ++i){
			for (int j = 1; j <= M; ++j) {

				if( j <= M-1 && i <= N-1 ) square(j, i);
				if( i <= N-3 ) straightRow(j, i);
				if( j <= M-3 ) straightCol(j, i);
				if( j <= M-1 && i <= N-2 ) othersRow(j, i);
				if( j <= M-2 && i <= N-1 ) othersCol(j, i);
			}
		}
	}

	private static void square(int x, int y){
		int sum = sumArr[y+1][x+1] - sumArr[y-1][x+1] - sumArr[y+1][x-1] + sumArr[y-1][x-1];
		result = Math.max(result, sum);
	}

	private static void straightRow(int x, int y){
		int sum = sumArr[y+3][x] - sumArr[y+3][x-1] - sumArr[y-1][x] + sumArr[y-1][x-1];
		result = Math.max(result, sum);
	}

	private static void straightCol(int x, int y){
		int sum = sumArr[y][x+3] - sumArr[y][x-1] - sumArr[y-1][x+3] + sumArr[y-1][x-1];
		result = Math.max(result, sum);
	}

	private static void othersRow(int x, int y){ // 세로 직사각형
		for(int i = 0 ; i < 8 ; ++i){
			int sum = sumArr[y+2][x+1] - sumArr[y+2][x-1] - sumArr[y-1][x+1] + sumArr[y-1][x-1];

			int[][] deletePoints = remove[i];
			int[] point1 = deletePoints[0];
			int[] point2 = deletePoints[1];
//			System.out.println( (y+point1[1]-1) + ", " + (x+point1[0]-1) + " ::: " + (y+point2[1]-1 ) + ", " + (x+point2[0]-1));
//			System.out.println( map[y+point1[1]-1][x+point1[0]-1] + " ::: " + map[y+point2[1]-1][x+point2[0]-1]);
			sum -= (map[y+point1[1]-1][x+point1[0]-1] + map[y+point2[1]-1][x+point2[0]-1]);
			result = Math.max(result, sum);
		}
	}

	private static void othersCol(int x, int y){ //가로 직사각형
		for(int i = 0 ; i < 8 ; ++i){

			int sum = sumArr[y+1][x+2] - sumArr[y+1][x-1] - sumArr[y-1][x+2] + sumArr[y-1][x-1];
			int[][] deletePoints = remove[i];
			int[] point1 = deletePoints[0];
			int[] point2 = deletePoints[1];

			sum -= (map[y+point1[0]-1][x+point1[1]-1] + map[y+point2[0]-1][x+point2[1]-1]);
			result = Math.max(result, sum);
		}
	}
}
