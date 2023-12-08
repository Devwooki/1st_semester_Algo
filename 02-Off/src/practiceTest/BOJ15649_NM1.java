package practiceTest;

import java.io.*;
import java.util.*;

public class BOJ15649_NM1 {
    static int N, M;
    static StringBuilder sb = new StringBuilder();
    static int[] inputs, results;
    static boolean[] visited;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        inputs = new int[N];
        results = new int[M];
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            inputs[i] = i + 1;
        }

        nPr(0);
        System.out.print(sb);

        sb.setLength(0);
        System.out.println("==================");
        nCr(0, 0);
        System.out.print(sb);
    }

    public static void nPr(int cnt){
        if(cnt == M){
            for(int i = 0; i < M ; ++i){
                sb.append(results[i] + " ");
            }
            sb.append("\n");
            return;
        }

        for(int i = 0 ; i < N ; ++i){
            if(visited[i]) continue;

            visited[i] = true;
            results[cnt] = inputs[i];

            nPr(cnt+1);
            visited[i] = false;
        }

    }

    public static void nCr(int cnt, int start){
        if(cnt == M){
            for(int i = 0; i < M ; ++i){
                sb.append(results[i] + " ");
            }
            sb.append("\n");
            return;
        }

        for(int i = start ; i < N ; ++i){
            results[cnt] = inputs[i];
            nCr(cnt+1, i+1);
        }

    }

    public voi
}
