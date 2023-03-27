package com.ssafy.offline19;
import java.io.*;
import java.util.*;

//복구 시간이 가장 짧은 경로에 대한 총 복구 시간
//이동은 시간 고려x 복구만
public class SWEA1249 {
	static class Point implements Comparable<Point>{
		int x;
		int y;
		int cost;
	
		public Point(int x, int y, int cost) {
			super();
			this.x = x;
			this.y = y;
			this.cost = cost;
		}
		
		//가중치
		public int compareTo(Point o) {
			return this.cost - o.cost;
		}
	}
	
	static int N;
	static int[][] map, distance;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; ++tc) {
			
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			distance = new int[N][N];
			
			for(int i = 0 ; i < N ; ++i) {
				map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
				Arrays.fill(distance[i], Integer.MAX_VALUE);
			}

			bfs();
			sb.append("#" + tc + " " + distance[N-1][N-1] + "\n");
		}
		System.out.println(sb);
	}
	
	//작업 시간이 가장 적은 경로의 복구 시간
	static void bfs() {		
		int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1,0}};
		PriorityQueue<Point> q = new PriorityQueue<>();
		q.offer(new Point(0,0,map[0][0]));
		distance[0][0] = 0;
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			
			for(int i = 0 ; i < 4 ;  ++i) {
				int nx = p.x + dir[i][0];
				int ny = p.y + dir[i][1];
				
				
				//좌표 유효성, 방문 여부 체크
				if(checkRange(nx, ny)) {
					if(distance[ny][nx] > distance[p.y][p.x] + map[ny][nx]) {
						distance[ny][nx] = distance[p.y][p.x] + map[ny][nx]; 
						q.offer(new Point(nx, ny, distance[ny][nx]));
					}
				}
			}
		}
	}
	
	static boolean checkRange(int x, int y) {
		return ( 0 <= x && x < N && 0 <= y && y < N);
	}
}
