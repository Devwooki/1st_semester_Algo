package com.ssafy.offline19;
import java.io.*;
import java.util.*;
/*
 * 낚시왕 오른쪽 한 칸 이동
 * 낚시왕 열중 가장 가까운 상어 사냥,  상어 잡으면 격자판에서 사라짐
 * 상어 이동 (입력된 속도만큼, 단위 칸/초) -> 범위 초과 -> 반대방향으로
 *
 * r c 위치
 * s 속력
 * d 방향 1위 2아래 3오 4왼
 * z 크기 : 같은 칸에 상어가 있을 때 크기 큰 놈이 전부 잡아 먹는다
 * 
 * 낚시왕이 잡은 상어 크기 구하기
 */
public class BOJ17143 {
	static class Shark{
		int x, y;
		int speed;
		int dir;
		int size;
		public Shark(int x, int y, int speed, int dir, int size) {
			super();
			this.x = x;
			this.y = y;
			this.speed = speed;
			this.dir = dir;
			this.size = size;
		}
	}
	static int[][] map;
	static int R, C, M, sum = 0;
	static HashMap<Integer, Shark> sharks = new HashMap<>(); //맵이 아니라 Shark배열 쓰면 더빨랐을지도,,?
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		
		//상어 입력
		for(int i = 1 ; i <= M ; ++i) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken()) - 1;
			int x = Integer.parseInt(st.nextToken()) - 1;
			int speed = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			//1상 2하 3좌 4우 -> 0상 1좌 2하 3우로 바꾸기(시계방향으로 움직임)
			if(dir == 1) dir = 0;
			else if(dir == 4) dir =1;
			int size = Integer.parseInt(st.nextToken());

			//입력 순서대로 상어의 key는 번호
			sharks.put(i ,new Shark(x, y, speed, dir, size));
			map[y][x] = i;
		}
		fishing();
		System.out.println(sum);
	}
	static void fishing() {

		for(int i = 0 ; i < C ; ++i) {
			//낚시꾼이 i위치에서 상어잡음
			findShark(i);
			//상어 이동
			moveShark();
			//맵을 새로그림. 겹치는 대상 있으면 큰 사이즈를 남기고 지움
			drawMap();
		}
	}
	
	//첫 번째 상어 잡음
	private static void findShark(int time) {
		int sharkNum = -1;
		for(int i = 0 ; i < R ; ++i) {
			if(map[i][time] != 0) {

				//sum에 상어 사이즈 더하고
				sum += sharks.get(map[i][time]).size;
				//HashMap에서 상어 삭제
				sharks.remove(map[i][time]);
				map[i][time] = 0;
				return;
			}
		}
	}
	//방향 1위 2아래 3오 4왼
	private static void moveShark() {
		int[][] dir = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
		//상어 위치 (현재 상어방향 + 2 ) % 4 -> 반대방향이 됨
		/*
		스피드  R || C의 n배 만큼 움직임 -> 제자리이며 ㄱ
		방향 전환 : ( 현재 상어방향 + 2 ) % 4
		(speed / R) % 2 == 0 같은방향으로 시작
		(speed / C) % 2 == 1 반대 방향으로 움직임
		speed % R : 최소한 움직일 횟수
		-----------위 방식 틀림-----------
		R-1 || C-1의 2배 만큼 움직임 -> 제자리
		거기서 최소만큼 움직이고, 벽을 만났을 때 방향전환 해서 연산하는 방법으로 변경
		 */
		for(int key : sharks.keySet()){
			Shark cur = sharks.get(key);
			int speed = cur.speed;

			//최소 연산 구함
			if(cur.dir == 0 || cur.dir == 2){ //상어 방향이 상하인지 체크
				//if((speed / R) % 2 != 0) cur.dir = (cur.dir+2) % 4; // 방향 전환
				speed %= (R - 1) * 2;

			}else if(cur.dir == 1 || cur.dir == 3){
				//if((speed / C) % 2 != 0) cur.dir = (cur.dir+2) % 4; // 방향 전환
				speed %= (C - 1)* 2;
			}

			//speed 만큼 이동
			for(int i = 0 ; i < speed ; ++i){
				int nx = cur.x + dir[cur.dir][0];
				int ny = cur.y + dir[cur.dir][1];
				if(!checkRange(nx, ny)){ //범위가 벽을 벗어남
					//값을 빼주고
					nx -= dir[cur.dir][0];
					ny -= dir[cur.dir][1];
					//방향전환 후 이동
					cur.dir = (cur.dir + 2) % 4;
					nx += dir[cur.dir][0];
					ny += dir[cur.dir][1];
				}
				cur.x = nx;
				cur.y = ny;
			}
			//sharks Map에 cur 갱신
			sharks.put(key, cur);
		}
	}

	//상어 움직인 후 맵에 재 배치
	private static void drawMap(){
		//map 새로 생성
		map = new int[R][C];
		List<Integer> removeShark = new LinkedList<>();
		//map에 있는 상어들 재배치
		for(int key : sharks.keySet()){
			//배치할 상어
			Shark cur = sharks.get(key);
			//배치할 곳에 상어가 있다면 사이즈 비교. size 큰놈은 두고 작은놈은 삭제
			if(map[cur.y][cur.x] != 0){
				Shark putted = sharks.get(map[cur.y][cur.x]); //현재 위치에 있는 놈을 찾아옴

				//상어 cur의 크기가 putted상어보다 크면
				if(cur.size > putted.size) {
					//기존 상어 삭제하고 cur상어로 대체
					removeShark.add(map[cur.y][cur.x]);
					map[cur.y][cur.x] = key;
				}else if(cur.size < putted.size) { //놓여진 상어가 더 크다면
					//삭제할 상어 리스트 만듬
					removeShark.add(key);
				}
				continue;
			}
			//배치할 곳에 상어 없으면 그냥 배치
			map[cur.y][cur.x] = key;
		}

		//리스트에 저장된 지워야할 상어들 제거 - 중간에 Map에서 제거할 경우 에러 발생
		for(int key : removeShark){
			sharks.remove(key);
		}
	}

	private static boolean checkRange(int x, int y){
		return ( 0 <= x && x < C && 0 <=y && y < R);
	}
}
