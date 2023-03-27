package com.ssafy.offline12;
import java.io.*;
import java.util.*;


public class BOJ3109 {
	static int[] dy = {-1,0,1};
	static char[][] map;
	static int R, C;
	static int result = 0;
	static boolean flag = false;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];		
		for(int i = 0 ; i < R ; ++i) {
//			String str = br.readLine();
//			for(int j = 0 ; j < C ; ++j) {
//				map[i][j] = str.charAt(j);
//			}
//	
			map[i] = br.readLine().toCharArray();
		}
		
		for(int i = 0 ; i < R ; ++i) {
			flag=false;
			dfs2(0,i);
		}
		
		System.out.println(result);
	}
	
	static boolean dfs(int x, int y){
		if(x == C-1) {
			map[y][x] = 'x';
			result += 1;
			return true;
		}
	
		
		map[y][x] = 'x';
//		for(int i = 0 ; i < R ; ++i) {
//			System.out.println(Arrays.toString(map[i]));
//		}
//		System.out.println();
		
		for(int i = 0 ; i < 3 ; ++i) {
			int nextX = x + 1; 
			int nextY = y + dy[i];

			//if( nextX < 0 || nextY < 0 || nextX == C || nextY == R) continue;
			if(nextY < 0 || nextY == R) continue;
			//col에 대한 범위는 검사할 필요 없음, 범위 도착하면 성공했다고 표시하기 때문에
			if( map[nextY][nextX] == 'x') continue;
			
			if(dfs(nextX, nextY)) return true;
		}
		return false;
	}
	
	static void dfs2(int x, int y){
		map[y][x] = '+';
		if(x == C-1) {
			result += 1;
			flag = true;
			return;
		}

		//파이프를 되돌리면 큰일남
		//실패해도 파이프를 놔야함 -> 그렇지 않으면 실패한 곳에서 또 작업발생 -> 지속적인 상황에서 문제 발생
		//겹치는 상황이 발생함-> 성공하든 실패하든 자리표시를 해야함
		//위쪽부터 가야 최대한 많은 경로를 얻을 수있다.
		
		for(int i = 0 ; i < 3 ; ++i) {
			if(flag) return;
			int nextX = x + 1; 
			int nextY = y + dy[i];

			if(nextY < 0 || nextY == R) continue;
			//col에 대한 범위는 검사할 필요 없음, 범위 도착하면 성공했다고 표시하기 때문에
			if( map[nextY][nextX] == 'x') continue;
			dfs2(nextX, nextY);
		}
	}
}
