package second.contest;

import java.io.*;
import java.util.*;

public class BOJ14889 {
    static int N, answer = Integer.MAX_VALUE;
    static int[][] map;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        solution();

        System.out.println(answer);
    }

    private static void solution() {

    }
}
