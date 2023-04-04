package second.contest;

import java.io.*;
import java.util.*;

/**
 * 0 빈칸
 * 1은 벽
 * 2 바이러스
 *
 * 바이러스는 상하좌우 인접한 칸으로 확산됨
 */

//안전 영역의 최댓값을 구하기, 벽은 총 3개 놓을 수 이있다.
public class BOJ14502 {
    private static int N, M;
    private static int[][] map;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        map = new int[N][M];
        for (int i = 0; i < N; i++)
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();



    }
}
