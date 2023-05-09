package study;
import java.io.*;
import java.util.*;

public class BOJ2167 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N+1][M];
        int[][] sum = new int[N+1][M+1];
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        //누적합 구함
        for(int i = 0 ; i < N ; ++i) {
            for (int j = 0; j < M; ++j) {
                sum[i+1][j+1]= sum[i][j+1] + sum[i+1][j] - sum[i][j] + map[i][j];
            }
        }

        //구간함 계산
        int K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K ; i++) {
            st = new StringTokenizer(br.readLine());
            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());
            int ex = Integer.parseInt(st.nextToken());
            int ey = Integer.parseInt(st.nextToken());

            int result = sum[ex][ey] -sum[sx-1][ey] - sum[ex][sy-1] + sum[sx-1][sy-1];
            sb.append(result + "\n");
        }

        System.out.println(sb);
    }
}
