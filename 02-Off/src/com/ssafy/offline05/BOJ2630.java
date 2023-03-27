package com.ssafy.offline05;

import java.io.*;
import java.util.*;
public class BOJ2630 {
    static int[][] map;
    static int[] result;
    static int N;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        result = new int[2];
        for(int i = 0 ; i < N ; ++i){
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        divide(0,0, N);

        System.out.println(result[0] + "\n"
                        +  result[1]);
    }

    private static void divide(int x, int y, int n){
        if(check(x,y,n)){
            result[map[y][x]]++;
        }
        else{

            for(int i = y ; i < y + n ; ++i){
                for(int j = x; j < x + n ; ++j){
                    int tempX = x + j * n/2;
                    int tempY = y + i * n/2;
                    divide(tempX, tempY, n/2);
                }

            }
        }
    }
    private static boolean check(int x, int y, int n){
        int now = map[y][x];

        for(int i = 0 ; i < 2 ; ++i ){
            for (int j = 0; j < 2; j++) {
                if(now != map[i][j]) return false;
            }
        }
        return true;
    }
}

