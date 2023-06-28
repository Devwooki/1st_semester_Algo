package study;

import com.sun.istack.internal.FinalArrayList;

import java.io.*;
import java.util.*;

public class BOJ14938 {
    static int N, M, R;
    static int[][] areas;
    static int[] itemAmount;
    final static int INF = 500;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        areas = new int[N][N];

        //플로이드 워셜을 구현하기 위해 모든 배열을 최대 아이템 값인 INF로 설정한다.
        for (int i = 0; i < N; i++) for (int j = 0; j < N; j++) areas[i][j] = INF;


        for(int i = 0 ; i < R ; ++i){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken())-1;
            int v = Integer.parseInt(st.nextToken())-1;
            int w = Integer.parseInt(st.nextToken());

            areas[u][v] = w;
            areas[v][u] = w;
        }

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    areas[i][j] = Math.min(areas[i][j], areas[i][k] + areas[j][k]);
                }
            }
        }






    }
}
