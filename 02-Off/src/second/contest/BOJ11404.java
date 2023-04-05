package second.contest;
import java.util.*;
import java.io.*;

public class BOJ11404 {
    static int N, M;
    public static final int INF = 1000000000;
    static int[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        dist = new int[N+1][N+1];

//        for(int i = 1 ; i <= N ; ++i) {
//            Arrays.fill(dist[i], 999999);
//            dist[i][i] = 0;
//        }
        for(int i=1; i <= N; i++) {
            for(int j=1; j <= N; j++) {
                if(i == j) continue;
                dist[i][j] = INF;
            }
        }
        for(int i = 0 ; i < M ; ++i){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            dist[start][end] = Math.min(cost, dist[start][end]);
        }

        makeFloyd();

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if(dist[i][j] >= INF) sb.append("0 ");
                else sb.append(dist[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    static void makeFloyd(){
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                if(i == k) continue;
                for (int j = 1; j <= N; j++) {
                    if(i==j || k ==j) continue;
                        dist[i][j] = Math.min(dist[i][k] + dist[k][j], dist[i][j]);
                }

            }

        }
    }
}

