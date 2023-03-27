package com.ssafy.offline15;

import java.io.*;
import java.util.*;

public class BOJ15683 {

//	static int[] dx = {0, 1, 0, -1};
//	static int[] dy = {-1, 0, 1, 0};
//	static int N, M, result = Integer.MAX_VALUE;
//	static char[][] map;
//	static List<int []> cctv = new ArrayList<>();
//	static List<Point> cctv5 = new ArrayList<>();
//	static int[] numbers;
//	//cctv = { x좌표, y좌표, cctv번호};
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		StringBuilder sb = new StringBuilder();
//		N = Integer.parseInt(st.nextToken());
//		M = Integer.parseInt(st.nextToken());
//		
//		map = new char[N][M];
//		for(int i = 0 ; i < N ; ++i) {
//			st = new StringTokenizer(br.readLine());
//			for(int j = 0 ; j < M ; ++j) {
//				map[i][j] = st.nextToken().charAt(0);
//				if( '1' <= map[i][j] && map[i][j] <= '4') cctv.add(new int[] {j, i, map[i][j]-'0'});
//				if( map[i][j] == '5') cctv5.add(new Point(j, i));
//			}
//		}
//		numbers = new int[cctv.size()];
//		
//		//5가 있는ㄴ 영역은 먼저 사각지대 처리를 한다.
//		for(int i = 0 ; i < cctv5.size(); ++i) {
//			drawMap(cctv5.get(i));
//		}
//		
//		nPIr(0);
////		for(int i = 0 ; i < N ; ++i) {
////			System.out.println(Arrays.toString(map[i]));
////		}
//		
//	}
//	static void drawMap(Point p) {
//		for(int i = 0; i < 4 ; ++i) {
//			int nx = p.x;
//			int ny = p.y;
//			
//			while(true) {
//				nx += dx[i];
//				ny +=  dy[i];
//				if( nx < 0 || ny < 0 || nx >= M || ny >= N || map[ny][nx] != '0') break;
//				map[ny][nx] = '#';
//			}
//		}	
//	}
//	
//	static void drawMap(int[] data, int rotate) {
//		if(data[2] == 1) {
//			//한 방향만
//			int nx = data[0];
//			int ny = data[1];
//			
//			for(int i = 0 ; i < 1 ; ++i) {
//				while(true) {
//					nx += dx[i + rotate];
//					ny +=  dy[i + rotate];
//					if( nx < 0 || ny < 0 || nx >= M || ny >= N) break;
//					if( map[ny][nx] == '0' || map[ny][nx] == '0') {
//						map[ny][nx] = '#';
//					}else {
//						break;
//					}
//				}
//			}
//		}
//		if(data[2] == 2) {
//			
//		}
//		if(data[2] == 3) {
//			
//		}
//		if(data[2] == 4) {
//			
//		}
//		
//	}
//	
//	
//	
//	static void nPIr(int cnt) {
//		if(cnt == cctv.size()) {
//			for(int i = 0 ; i < cctv.size(); ++i) {
//				drawMap(cctv.get(i), numbers[i]);
//			}
//			result = Math.min(result, checkBlind());
//			return;
//		}
//		
//		for(int i = 0 ; i < 4 ; ++i) {
//			numbers[cnt] = i;
//			nPIr(cnt+1);
//		}
//	}
//	
//	
//	static int checkBlind() {
//		int cnt = 0;
//		for(int i = 0 ; i < N ; ++i) {
//			for(int j = 0 ; j < M ; ++j) {
//				if(map[i][j] == 0) cnt++;
//
    static class CCTV {
        int x;
        int y;
        int num;
        public CCTV(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }

    static int[][] direct = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
    static int[][][] select = {
            {},
            {{0}, {1}, {2}, {3}},                            //cctv 1
            {{0, 2}, {1, 3}, {0, 2}, {1, 3}},                //cctv 2
            {{0, 1}, {1, 2}, {2, 3}, {3, 0}},                //cctv 3
            {{0, 1, 2}, {1, 2, 3}, {2, 3, 0}, {3, 0, 1}},    //cctv 4
    };
    static int N, M, result = Integer.MAX_VALUE;
    static char[][] map;
    static char[][] copy;
    static List<CCTV> cctvs = new ArrayList<>();
    static int[] numbers;

    //cctv = { x좌표, y좌표, cctv번호};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
		List<CCTV> cctvs5 = new ArrayList<>();

        map = new char[N][M];
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; ++j) {
                map[i][j] = st.nextToken().charAt(0);
                if ('1' <= map[i][j] && map[i][j] <= '4') {
                    cctvs.add(new CCTV(j, i, map[i][j] - '0'));
					continue;
                }else if(map[i][j] == '5') cctvs5.add(new CCTV(j, i, map[i][j] - '0'));
            }
        }
        //cctv5는 4방향 촬영을 하기 때문에 시작 전에 그린다.
		draw5(cctvs5);
		numbers = new int[cctvs.size()];
		nPIr(0);

		System.out.println(result);
    }

	static void draw5(List<CCTV> cctv5) {
		for(CCTV c5 : cctv5) {
			for (int[] dx : direct) {
				int nx = c5.x;
				int ny = c5.y;
				while (true) {
					nx += dx[0];
					ny += dx[1];
					if (!checkRange(nx, ny)) break;
					if (map[ny][nx] == '6') break;
					if (map[ny][nx] == '0') map[ny][nx] = '#';
				}

			}
		}
	}
    //cctv 1~4의 맵을 그린다.
    static void drawMap() {
        for (int i = 0; i < numbers.length; ++i) {
            int rotate = numbers[i];
            CCTV cctv = cctvs.get(i);
            //cctv번호에 해당하고 순열 결과에 따라 rotate
            for (int c : select[cctv.num][rotate]) {
                int nx = cctv.x;
                int ny = cctv.y;
                while (true) {
                    nx += direct[c][0];
                    ny += direct[c][1];
                    //복제된 맵을 그린다. 원본데이터는 수정X
                    if (!checkRange(nx, ny)) break;
                    if (copy[ny][nx] == '6') break;
                    if (copy[ny][nx] == '0') copy[ny][nx] = '#';
                    else continue;
                }
            }
        }
    }

    //cctv 배치는 은 4^n개다 (n : cctv 갯수) 고로 중복순열을 활용한다.
    static void nPIr(int cnt) {
        if (cnt == cctvs.size()) {
            //cctv 배치에 있어 최솟값을 출력하기 위해 매번 맵을 카피
            copy = new char[N][M];
            for (int i = 0; i < N; ++i) {
                copy[i] = map[i].clone();
            }
            drawMap();
            result = Math.min(result, checkBlind());
            return;
        }
        for (int i = 0; i < 4; ++i) {
            numbers[cnt] = i;
            nPIr(cnt + 1);
        }
    }

    //맵의 0갯수 세기
    static int checkBlind() {
        int cnt = 0;
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                if (copy[i][j] == '0') cnt++;
            }
        }
        return cnt;
    }
    //cctv감시 범위 측정 시 범위 측정
    static boolean checkRange(int x, int y) {
        return ((0 <= x && x < M) && (0 <= y && y < N));
    }
}

