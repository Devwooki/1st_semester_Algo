package com.ssafy.offline18;
import java.io.*;
import java.util.*;


public class SWEA5656 {
	static int N, W, H, totalCnt = 0;
	static int[][] map, copy;
	static int[] perms;
	static int min;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; ++tc) {
			min = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			map = new int[H][W];
			perms = new int[N];
			
			for(int i = 0 ; i < H ; ++i) {
				map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
			
			nPIr(0);
			sb.append("#" + tc + " " + min + "\n");
		}
		System.out.println(sb);
	}
	
	//벽돌은 같은곳에도 떨어트릴 수 있다.
	static void nPIr(int cnt) {
		if(cnt == N) {
			//중복조합이 완성,벽돌 깨기 시물레이션 시작
			totalCnt++;
			copy =  new int[H][W];
			for(int i = 0 ; i < H ; ++i) copy[i] = map[i].clone();
			
			simulation();
			min = Math.min(min, cntBrick(copy));
			return;
		}
		
		for(int i = 0 ; i < W ; ++i) {
			perms[cnt] = i;
			nPIr(cnt+1);
		}
	}
	
	//벽돌깨기 함수
	private static void simulation() {
		for(int block : perms) {
			int height = -1;
		
			for(int i = 0 ; i < H ; ++i) {
				if(copy[i][block] != 0) {
					height = i;
					break;
				}
			}
			if(height == -1) continue;//깰 것이 없으니 다음으로
			
			brickBreaker(height, block);
			downBlock();
		}
	}
	
	//벽돌깨기를 bfs로 실시한다.
	private static void brickBreaker(int height, int block) {
		int[][] direction = {//순서대로 상,좌,하,우
				{0, 1}, {1, 0}, {0, -1}, {-1, 0}
		};
		
		Queue<int[]> q = new LinkedList<>();
		if(copy[height][block]>1) {
			q.offer(new int[] {block, height, copy[height][block]});
		}
		copy[height][block] = 0;
		
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			int x = now[0];
			int y = now[1];
			int range = now[2];
			for(int i = 0 ; i < 4 ; ++i) {
				for(int j = 1 ; j < range ; ++j) {
					int nx = x + j * direction[i][0];
					int ny = y + j * direction[i][1];
					if(checkRange(nx,ny) && copy[ny][nx] !=0) {
						//주변이 1인경우에는 0으로 만들고 continue;
						if(copy[ny][nx] > 1) 
							q.offer(new int[] {nx, ny, copy[ny][nx]});
						copy[ny][nx] = 0;
					}
				}
			}
		}
	}
	
	//벽돌깨기 끝나면 밑으로 모으기
	private static void downBlock() {
		Queue<Integer> q = new LinkedList<>();
		for(int i = 0 ; i < W; ++i) {
			//0이 아닌 것들 q에넣고
			for(int j = H-1 ; j >= 0  ; --j) {
				if(copy[j][i] != 0) q.add(copy[j][i]);
			}
			//밑에서부터 채운다. 큐에 요소가 없으면 0으로 대체
			for(int j = H-1 ; j >= 0  ; --j) {
				copy[j][i] = ( !q.isEmpty() ) ? q.poll() : 0; 
			}
		}
	}
	
	//벽돌 수 세기
	private static int cntBrick(int[][] field) {
		int cnt = 0;
		for(int i = H-1 ; i >= 0  ; --i) {
			for(int j = 0 ; j < W  ; ++j) {
				if(field[i][j] >= 1) cnt++;
			}
		}
		return cnt;
	}
	
	//범위체크
	private static boolean checkRange(int x, int y) {
		return ( 0 <= x &&  x < W &&  0 <=y && y < H);
	}
}
