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
	static int state = 0; //0 : 가로, 1 : 세로, 2 : 대각선
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




	}

}
