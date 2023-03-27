package com.ssafy.offline18;
import java.awt.*;
import java.io.*;
import java.util.*;

/*
 * 상어크기 2, 상하좌우 1번씩 이동
 *
 * 상어크기 < 물고기   : 못먹고 못지나감
 * 상어크기 == 물고기 : 지나감
 * 상어크기 > 물고기 : 먹음
 *
 * 먹을 수 있는거 1마리 : 물고기 먹으러감
 * 먹을 수 있는거 여러마리 : 가까운 거리 > 위쪽 > 왼쪽
 *
 * 상어크기만큼 먹으면 크기1증가
 */
public class BOJ16236 {
	static class Fish implements Comparable<Fish>{
		int x;
		int y;
		int distance;
		public Fish(int x, int y, int distance) {
			this.x = x;
			this.y = y;
			this.distance = distance;
		}

		@Override
		public String toString() {
			return "Fish{" +
					"x=" + x +
					", y=" + y +
					", distance=" + distance +
					'}';
		}
		@Override
		public int compareTo(Fish o) { //물고기가 여러마리면 가까운 거리 > 위쪽(내림차순)  > 왼쪽(오름차순 순서대로
			return this.distance != o.distance ? this.distance - o.distance : (this.y == o.y ? this.x - o.x : this.y - o.y );
		}
	}
	static int N, sharkSize = 2, eatCnt = 0, moveTime = 0;
	static int[] shark;
	static int[][] map, distance;
	static PriorityQueue<Fish> fishes;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		for(int i = 0 ; i < N ; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N ; ++j) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) {
					shark = new int[]{j ,i};
					map[i][j] = 0;
				}

			}
		}
		findFeed();
		System.out.println(moveTime);

	}
	static void findFeed(){
		//상어가 물고기를 잡아 먹지 못할 때 까지 실행해야함 -> while(true)로 놓고 조건주기
		while(true){
			//매 시행마다 거리, 물고기 우선순위 큐는 초기화한다.
			fishes = new PriorityQueue<>();
			distance = new int[N][N];
			bfs(shark);

			//BFS탐색 마쳤는데 잡아 먹을 수있는 물고기가 없음 -> break;
			if(fishes.isEmpty()) break;

			//물고기가 있으므로 잡아 먹는다
			getMove();
		}
	}
	static void getMove(){
		//물고기가 여러마리면 가까운 거리 > 위쪽  > 왼쪽 순서대로 -> 우선순위큐에 만들어 놓음
		//잡아먹고 상어 위치 갱신, 물고기 위치 0으로 변경
		//움직인 시간moveTime 증가시키기
		Fish fish = fishes.poll();
		shark[0] = fish.x;
		shark[1] = fish.y;
		map[fish.y][fish.x] = 0;
		eatCnt +=1;

		if(eatCnt == sharkSize){
			eatCnt = 0;
			sharkSize += 1;
		}

		moveTime += fish.distance;
	}


	static void bfs(int[] shark){
		int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1,0}};
		Queue<Point> q = new LinkedList<>();

		//상어위치 넣고 상어위치에서 갈 수 있는 곳 까지 거리 구함
		q.offer(new Point(shark[0], shark[1]));

		while(!q.isEmpty()){
			Point cur = q.poll();
			//사방탐색
			for(int i = 0 ; i < 4 ; ++i){
				int nx = cur.x + dir[i][0];
				int ny = cur.y + dir[i][1];

				if(checkRange(nx, ny) && checkMove(nx, ny) && distance[ny][nx] == 0){
					distance[ny][nx] = distance[cur.y][cur.x] + 1;
					q.offer(new Point(nx, ny));

					//해당 좌표가 물고기 물고기라면 상어보다 작은지 체크
					if(checkEatFish(nx, ny)) fishes.add(new Fish(nx, ny, distance[ny][nx]));
				}
			}
		}

	}

	//좌표유효성 검사
	static boolean checkRange(int x, int y) {
		return  (0 <= x && x < N && 0 <= y && y < N );
	}
	//갈 수 있는 길 -> sharkSize보다 작거나 같음
	static boolean checkMove(int x, int y) {
		return map[y][x] <= sharkSize;
	}
	static boolean checkEatFish(int x, int y){
		return map[y][x] != 0 && map[y][x] < sharkSize;
	}
}
