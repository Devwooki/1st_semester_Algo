package com.ssafy.offline05;

import java.io.*;
import java.util.*;
public class BOJ1074 {
    static int cnt = 0;
    static int N, r, c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        N = (int) Math.pow(2, N);

        drawMap(0, 0, N, 0);
        //System.out.println(map[r][c]);
//        for(int i = 0 ; i < N ; ++i){
//            for(int j = 0 ; j < N ; ++j){
//                System.out.print(map[i][j] + "\t");
//            }
//            System.out.println();
//        }
    }

    static int[] dx = {0, 1, 0 ,1};
    static int[] dy = {0, 0, 1, 1};
    private static void drawMap(int x, int y, int n, int nowCnt){
        //메모리 초과 발생 -> 현재 rc 범위 초과하면 필요없음
        if(!isBoundary(x, y, n)){
            return;
        }
        if( n == 2){
            //System.out.printf("Outer Z : %d행 %d열 \n", y, x);
            for(int i = 0 ; i < 4 ; ++i){
                int tempX = x + dx[i];
                int tempY = y + dy[i];
                //System.out.printf("Inner Z : %d행 %d열 \n", tempY, tempX);
                if(tempX == c && tempY == r){
                    System.out.println(cnt);
                }
            }
            //System.out.println();
            return;
        }
        drawMap(x,             y,      n/2, nowCnt); // 1 사분면 그리기
        drawMap(x + n/2,    y,      n/2, nowCnt + (n / 2 * n / 2)); // 2 사분면 그리기
        drawMap(x,          y + n/2,n/2, nowCnt + (n / 2 * n / 2) * 2); // 3 사분면 그리기
        drawMap(x + n/2, y + n/2,n/2, nowCnt + (n / 2 * n / 2) * 3); // 4 사분면 그리기
    }

    private static boolean isBoundary(int x, int y, int n) {
        return ( c - n < x && x <= c) && ( r - n < y && y <= r);

    }

}

    /*
    private static void drawMap(int x, int y, int n) {
        if(n == 1){
            return;
        }
        check(x, y);
        for(int i = y ;  i < y + n ; ++i){
            for(int j = x ; j < x + n ; ++j){
                int tempX = j + x * n*2;
                int tempY = i + y * n*2;
                drawMap(tempX, tempY, n/2);
            }
        }

    }
    private static void check(int x, int y){
        for(int i = 0 ; i < 2 ; ++i){
            for(int j = 0 ; j < 2 ; ++j){
                map[y+i][x+j] = cnt++;
            }
        }
    }
}
*/