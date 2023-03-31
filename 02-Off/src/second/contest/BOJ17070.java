package second.contest;
import java.lang.reflect.Array;
import java.util.*;
import java.io.*;

/**
 * 벽지를 긁으면 안됨 -> 가장자리에서 끝
 * 파이프가 가로 -> 가로, 대각선만
 * 파이프가 세로 -> 세로, 대각선만
 * 파이프가 대각선 -> 가로, 세로, 대각선 전부 가능
 *
 * 회전은 카운트 하지 않는다.
 * 고로 회전 -> push
 */
public class BOJ17070 {
	static int[][] direction = {{1,0},{0,1},{1,1}};
	static int N;
	static int result = 0;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		dfs(1,0,0);
		//시작 위치 1,0, 방향 0
		System.out.println(result);
	}

	static void dfs(int x, int y, int dir){
		if(x == N-1 && y == N-1){
			result++;
			return;
		}
		//대각은 무조건간다.
		//dir 0 -> 세로안감
		//dir 1 -> 로안감

        /*
		체크리스트
		1. 파이프 방향에 따라 갈 수 있는 방향의 제한
			- 가로 : 세로 못감
			- 세로 : 가로 못감
			- 대각 : none
		2. 이동할 위치에 파이프(1)가 있으면 못감
		3. 이동할 범위의 좌표가 유효한가.
		 */
//		int nx = x + 1;
//		int ny = y + 1;
//		if(checkRange(nx, ny)) {
//			if (dir != 1 && map[y][nx] != 1) dfs(nx, y, 0);
//			if (dir != 0 && map[ny][x] != 1) dfs(x, ny, 1);
//			if (map[ny][x] != 1 && map[y][nx] != 1 && map[ny][nx] != 1) dfs(nx,ny, 2);
//		}

		if (x+1 < N && dir != 1
				&& map[y][x+1] != 1) dfs(x+1, y, 0);
		if (y+1 < N && dir != 0
				&& map[y+1][x] != 1) dfs(x, y+1, 1);
		if (x+1 < N && y+1 < N
				&& map[y+1][x] != 1 && map[y][x+1] != 1 && map[y+1][x+1] != 1) dfs(x+1, y+1, 2);
	}

	//범위를 동시에 체크하면 에러 발생 - 상황별 각각 범위를 체크해야힘 일괄 처리 X
	static boolean checkRange(int x, int y){
		return  (0 <= x && x < N && 0 <= y && y < N);
	}

}
