package com.ssafy.offline12;
import java.io.*;
import java.util.*;

public class SWEA1873 {
	static char[][] map;
	static int H, W;
	static int startX = -1;
	static int startY = -1;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		
		for(int tc = 1 ; tc <= TC ; ++tc) {
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			
			//맵 그리기
			map = new char[H][W];
			for(int i = 0 ; i < H ; ++i) {
				String str = br.readLine();
				for(int j = 0 ; j < W ; ++j) {
					map[i][j] = str.charAt(j);
					if( map[i][j] == '^' ||
						map[i][j] == 'v' ||
						map[i][j] == '<' ||
						map[i][j] == '>' ) {
							startX = j;
							startY = i;
						}
					}		
				}
			
			int cmdCnt = Integer.parseInt(br.readLine());
			String cmd = br.readLine();
			for(int i = 0 ; i < cmdCnt ; ++i) {
				char c = cmd.charAt(i);
				doit(c, startX, startY);
				
//				System.out.printf("====%d, %c=====\n", (i+1), c);
//				for(int j = 0 ; j < H ; ++j) {
//					for(int k = 0 ; k < W ; ++k) System.out.print(map[j][k] + " ");
//					System.out.println();
//				}
//				System.out.println();
			}
			sb.append("#" + tc + " ");
			for(int j = 0 ; j < H ; ++j) {
				for(int k = 0 ; k < W ; ++k) sb.append(map[j][k]);
				sb.append("\n");
			}
		}
		System.out.println(sb.toString());
	}

	
	/*
	 * 	.	평지(전차가 들어갈 수 있다.)
	 	*	벽돌로 만들어진 벽
		#	강철로 만들어진 벽
		-	물(전차는 들어갈 수 없다.)
		^	위쪽을 바라보는 전차(아래는 평지이다.)
		v	아래쪽을 바라보는 전차(아래는 평지이다.)
		<	왼쪽을 바라보는 전차(아래는 평지이다.)
		>	오른쪽을 바라보는 전차(아래는 평지이다.)
		
		U	Up : 전차가 바라보는 방향을 위쪽으로 바꾸고, 한 칸 위의 칸이 평지라면 위 그 칸으로 이동한다.
		D	Down : 전차가 바라보는 방향을 아래쪽으로 바꾸고, 한 칸 아래의 칸이 평지라면 그 칸으로 이동한다.
		L	Left : 전차가 바라보는 방향을 왼쪽으로 바꾸고, 한 칸 왼쪽의 칸이 평지라면 그 칸으로 이동한다.
		R	Right : 전차가 바라보는 방향을 오른쪽으로 바꾸고, 한 칸 오른쪽의 칸이 평지라면 그 칸으로 이동한다.
		S	Shoot : 전차가 현재 바라보고 있는 방향으로 포탄을 발사한다.
	 */
	
	static void doit(char cmd, int x, int y) {
		switch(cmd) {
			case 'U' : 
				map[y][x] = '^';
				if(0 <= y-1 && y-1 < H && map[y-1][x] == '.') {
					map[y-1][x] = '^';
					map[y][x] = '.';
					startX = x;
					startY = y-1;
				}
				break;
			case 'D' : 
				map[y][x] = 'v';
				if(0 <= y+1 && y+1 < H && map[y+1][x] == '.') {
					map[y+1][x] = 'v';
					map[y][x] = '.';
					startX = x;
					startY = y+1;
				}
				break;
			case 'L' : 
				map[y][x] = '<';
				if(0 <= x-1 && x-1 < W && map[y][x-1] == '.') {
					map[y][x-1] = '<';
					map[y][x] = '.';
					startX = x-1;
					startY = y;
				}
				break;
			case 'R' : 
				map[y][x] = '>';
				if(0 <= x+1 && x+1 < W && map[y][x+1] == '.') {
					map[y][x+1] = '>';
					map[y][x] = '.';
					startX = x+1;
					startY = y;
				}
				break;
			case 'S' : shoot(x, y); break;
		}
	}
	static void shoot(int x, int y) {
		char now = map[y][x];
		
		switch(now) {
		case '^' : 
			for(int i = y ; i >= 0 ; --i) {
				if(map[i][x] == '*') {
					map[i][x] = '.';
					break;
				}else if(map[i][x] == '#') 
					break;
			}
			break;
		case 'v' : 
			for(int i = y ; i < H ; ++i) {
				if(map[i][x] == '*') {
					map[i][x] = '.';
					break;
				}else if(map[i][x] == '#') 
					break;
			}break;
		case '<' :
			for(int i = x ; i >= 0 ; --i) {
				if(map[y][i] == '*') {
					map[y][i] = '.';
					break;
				}else if(map[y][i] == '#') 
					break;
			}break;
		case '>' : 
			for(int i = x ; i < W ; ++i) {
				if(map[y][i] == '*') {
					map[y][i] = '.';
					break;
				}else if(map[y][i] == '#') 
					break;
			}break;
		}
	}
}
