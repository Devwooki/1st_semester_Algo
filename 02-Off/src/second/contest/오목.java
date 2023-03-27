//http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=1006&sca=2060
package second.contest;

import java.awt.Point;
import java.io.*;
import java.util.*;

public class 오목 {
	
	static int[][] map;
	static boolean ans;
	static int lastX, lastY;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		map = new int[19][19];
		
		for(int i=0; i < 19; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j < 19; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i < 19; i++) {
			for(int j=0; j < 19; j++) {
				if (map[i][j] != 0) bfs(i,j, map[i][j]);
				if(ans == true) {
					System.out.println(map[i][j]);
					System.out.println( (lastX+1) + " " + (lastY+1) );
					return;
				}
			
			}
		}
		
		System.out.println(0);
		
	}
	// 시작할 때랑 시작하는거 전에거 비교해서 같으면 패스 == 육목 제거
	private static void bfs(int i, int j, int flag) {
		//우상 우 우하 하
//		boolean[][] visited = new boolean[19][19];
//		int count;
		
		int[] dr1 = {-1, 0, 1, 1};
		int[] dc1 = {1,1, 1, 0};
		
		
		for (int d = 0; d < 4; d++) {
			int cnt = 1;
			int r = i + dr1[d];
			int c = j + dc1[d];
			int tempr= i - dr1[d];
			int tempc =  j - dc1[d];
			
		
			if(tempr < 0 || tempr >= 19 || tempc < 0 || tempc >= 19)continue;
			if (map[tempr][tempc] == flag) continue;			
			
			
			Queue<Point> queue = new LinkedList<>();
			queue.offer(new Point(i, j));
		
			while(!queue.isEmpty()) {
				Point now = queue.poll();
				int x = now.x;
				int y = now.y;
				
				int nx = x + dr1[d];
				int ny = y + dc1[d];
				
				if (0<= nx && nx < 19 && 0 <= ny && ny <19) {
					if (map[nx][ny]== flag) {
						cnt += 1;
						if(cnt == 6){
							break;
						}
						queue.offer(new Point(nx, ny));
					}
				}
			} // while
			if(cnt==5) { 
//				if(d==0) {
//					lastX = i + dr1[d]*4;
//					lastY= j + dc1[d]*4;
//				}
//				else {
//					lastX = i;
//					lastY = j;
//				}
				lastX = i;
				lastY = j;
				ans=true;
				return;
			}
		}
		
	}
}
