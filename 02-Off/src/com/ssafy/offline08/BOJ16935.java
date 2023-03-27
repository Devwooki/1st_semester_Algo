package com.ssafy.offline08;
import java.io.*;
import java.util.*;
/**
 * 1번 상하 반전
 * 2번 좌우 반전
 * 3번 90도 회전(시계)
 * 4번 90도 회전(반시계)
 * 5번 4개의 부분 배열로 나움 -> 1번은 2번, 2번은 3번, 3번은 4번, 4번은 1번
 * 6번 4개의 부분 배열로 나움 -> 1번은 4번, 4번은 3번, 3번은 2번, 2번은 1번
 */

public class BOJ16935 {
	static int[][] base;
	static int N, M, R;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		base = new int[N][M];
		for(int i = 0 ; i < N ; ++i) {
			base[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0 ; i < R ; ++i) {
			int cmd = Integer.parseInt(st.nextToken());
			switch(cmd) {
				case 1 : op1();break;
				case 2 : op2();break;
				case 3 : op3(); break;
				case 4 : op4(); break;
				case 5 : op5(); break;
				case 6 : op6(); break;
			}
		}
		for(int i = 0 ; i < N ; ++i){
			for(int j = 0 ; j < M ; ++j){
				sb.append(base[i][j] + " ");
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}
	public static void print(){
		for (int[] ints : base) {
			System.out.println(Arrays.toString(ints));
		}
		System.out.println();
	}
	public static void op1() {
//		for(int i = 0; i < N ; ++i) {
//			for(int j = 0; j < M ; ++j) {
//				result[i][j] = base[N-1-i][j];			
//			}
//		}
		//상하 반전은 col끼리 대칭시킬 수 있음
		int[][] result = new int[N][M];
		for (int i = 0; i < N; ++i) {
				result[i]= base[N - 1 - i];
		}
		base = result;

	}
	public static void op2() {
		int[][] result = new int[N][M];
		for(int i = 0; i < N ; ++i) {
			for(int j = 0; j < M ; ++j) {
				result[i][j] = base[i][M-1-j];			
			}
		}
		base = result;
	}
	public static void op3() {
		int[][] result = new int[M][N];
		for(int i = 0 ; i < N ;++i) {
			for(int j = 0 ; j < M ;++j) {
				result[j][N-1-i] = base[i][j];
			}
		}
		base = result;
		N = result.length;
		M = result[0].length;
	}
	public static void op4() {
		int[][] result = new int[M][N];
		for(int i = 0 ; i < N ;++i) {
			for(int j = 0 ; j < M ;++j) {
				result[M-1-j][i] = base[i][j];
			}
		}
		base = result;
		N = result.length;
		M = result[0].length;
	}
	public static void op5() {
		int[][] result = new int[N][M];
		
		draw(0,0 , M/2, 0, result);
		draw(M/2, 0, M/2, N/2, result);
		draw(M/2, N/2, 0, N/2, result);
		draw(0, N/2, 0,0, result);
		
		base = result;
	}
	public static void op6() {
		int[][] result = new int[N][M];

		draw(0,0 , 0, N/2, result);
		draw(0, N/2, M/2, N/2, result);
		draw(M/2, N/2,M/2, 0, result);
		draw(M/2,0, 0,0, result);

		base = result;
	}
	public static void draw(int cx, int cy, int nx, int ny, int[][] result) {
		for(int i = 0 ; i < N/2 ; ++i) {
			for(int j = 0 ; j < M/2 ; ++j) {
				result[ny+i][nx+j] = base[cy+i][cx+j];
			}
		}
	}
}
