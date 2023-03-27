package com.ssafy.offline19;
import java.awt.Point;
import java.io.*;
import java.util.*;
 

public class BOJ17144 {
	static class Dust{
		int x;
		int y;
		int amount;
		@Override
		public String toString() {
			return "Dust [x=" + x + ", y=" + y + ", amount=" + amount + "]";
		}
		public Dust(int x, int y, int amount) {
			super();
			this.x = x;
			this.y = y;
			this.amount = amount;
		}
	}
	static int R, C, T;
	static int[][] map;
	static boolean[][] visited;
	static int airCleaner;
	static Queue<Dust> dust = new LinkedList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		
		for(int i = 0 ; i < R ; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < C ;++j) {
				map[i][j] = Integer.parseInt(st.nextToken());
							
				if(map[i][j] == -1) {
					airCleaner = i;
				}
			}
		}
	
		for(int i = 0 ; i < T ; ++i) {
			findDust();
			spreadDust();
			cleanAir();
		}
		
		System.out.println(cntDust());
		
	}
	//먼지 찾아 리스트에 저장
	static void findDust() {
		for(int i = 0 ; i < R ; ++i) {
			for(int j = 0 ; j < C ;++j) {
				if(map[i][j]  > 4) {
					dust.add(new Dust(j, i, map[i][j])); 
				}
			}
		}
	}

	//먼지 찾으면 확산
	/*
	 * 확산은 모든 칸에서 발생,
	 * 인접한 네 방향으로 확산
	 * 공기청정기가 있거나 칸이 없으면 확산안됨
	 *                                                                                                                                          
	 */
	static void spreadDust() {
		int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1,0}};
		while(!dust.isEmpty()) {
			Dust dustLoc = dust.poll();
		//for(Dust dustLoc : dust) {
			int cnt = 0;
			int near = dustLoc.amount/5;
			for(int i = 0 ; i < 4 ; ++i) {
				int nx = dustLoc.x +dir[i][0];
				int ny = dustLoc.y +dir[i][1];
				if(checkRange(nx, ny) && map[ny][nx] != -1){
					map[ny][nx] += near;
					cnt += 1;
				}
			}
			map[dustLoc.y][dustLoc.x] -= near * cnt;
		}
	}
	

	//공기청정기로 빨려들어가는 것 부터 해야 범위체크 하지 않아도 됨
	//공기청정기는 항상 x좌표가 0인 곳에 위치함
	static void cleanAir() {		
		int up = airCleaner-1; //공기청정기 위쪽
		int down = airCleaner; //공기청청지 아래쪽
		
		//위쪽은 반시계방향
		//아래로 당기기
		for(int i = up - 1; i > 0 ; --i) map[i][0] = map[i-1][0];
		//왼쪽으로 당김
		for(int i = 0; i < C-1 ; ++i) map[0][i] = map[0][i+1];
		//위로으로 당김
		for(int i = 0; i < up ; ++i) map[i][C-1] = map[i+1][C-1];
		//오른쪽으로 당김
		for(int i = C-1 ; i > 1 ; --i) map[up][i] = map[up][i-1];
		//공기청정기에서 나온 바람은 깨끗함
		map[up][1] = 0;
		
		
		//아래쪽은 반 시계 방향
		//위로 당김
		for(int i = down + 1; i < R-1 ; ++i) map[i][0] = map[i+1][0];
		//왼쪽으로 당김
		for(int i = 0; i < C-1 ; ++i) map[R-1][i] = map[R-1][i+1];
		//아래쪽으로 당김
		for(int i = R-1 ; i > down ; --i) map[i][C-1] = map[i-1][C-1];
		//오른쪽으로 당김
		for(int i = C-1 ; i > 1 ; --i) map[down][i] = map[down][i-1];
		map[down][1] = 0;
	}
		
	static boolean checkRange(int x, int y) {
		return ( 0 <= x && x < C && 0 <= y && y < R);
	}
	
	static void print() {
		for(int i = 0 ; i < R ; ++i) {
			System.out.println(Arrays.toString(map[i]));
		}
		System.out.println();
	}
	
	static int cntDust() {
		int microDust= 0;
		for(int i = 0 ; i < R ; ++i) {
			for(int j = 0 ; j < C ; ++j) {
				microDust += map[i][j];
			}
		}
		return microDust += 2;
	}
}
