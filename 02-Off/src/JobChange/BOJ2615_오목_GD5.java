package JobChange;

import java.io.*;
import java.util.*;

public class BOJ2615_오목_GD5 {
    static int[][] map = new int[18][18];

    static int[] numbers;
    static boolean[] visited;
    static int N, R, totalCnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        numbers = new int[R];
        visited = new boolean[N];

        perm(0);
        System.out.printf("순열 %d \n", totalCnt);

        totalCnt = 0;
        combi(0, 0);
        System.out.printf("순열 %d \n", totalCnt);



        //subSet();
        subSetRecv(0);
    }

    private static void perm(int cnt){
        if(cnt == R){
            totalCnt++;
            System.out.println(Arrays.toString(numbers));
            return;
        }

        for(int i = 0 ; i < N ; ++i){
            if(visited[i]) continue;

            numbers[cnt] = i + 1;
            visited[i] = true;

            perm(cnt + 1);
            visited[i] = false;
        }
    }

    private static void combi(int cnt, int start){
        if(cnt == R){
            totalCnt++;
            System.out.println(Arrays.toString(numbers));
            return;
        }

        for(int i = start ; i < N ; ++i){
            numbers[cnt] = i + 1;
            combi(cnt+1, i + 1);
        }
    }

    private static void subSet(){
        StringBuilder sb = new StringBuilder();

        for(int i = 0 ; i < ( 1 << N ); ++i){
            for(int j = 0 ; j < N ; ++j){
                if((i & (1 << j)) != 0){
                    sb.append(j + " ");
                }
            }
            System.out.println(sb.toString());
            sb.setLength(0);
        }
    }

    private static void subSetRecv(int cnt){
        if(cnt == R){
            StringBuilder sb = new StringBuilder();
            for(int i = 0 ; i < R ; ++i){
                if(visited[i]) sb.append((i + 1) + " ");
            }

            if(sb.length() == 0){ return;}
            System.out.println(sb.toString());
            return;
        }

        visited[cnt] = true;
        subSetRecv(cnt+1);

        visited[cnt] = false;
        subSetRecv(cnt+1);
    }
}