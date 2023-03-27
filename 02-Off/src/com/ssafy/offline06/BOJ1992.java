package com.ssafy.offline06;
import java.io.*;
import java.util.*;

public class BOJ1992 {
    static int N;
    static int[][] map;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for(int i = 0 ; i < N ; ++i){
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(str.charAt(j) - '0'));
            }
        }

        search(0,0,N);

        System.out.println(sb);

    }
    private static void search(int x, int y, int size){

        if(check(x,y,size)){
            sb.append(map[y][x]);
            return;
        }else{
            sb.append("(");
            for(int i = 0 ; i < 2 ; ++i){
                for (int j = 0; j < 2; j++) {
                    int tempX = x + j * size/2;
                    int tempY = y + i * size/2;

                    search(tempX, tempY, size/2);

                }
            }
            sb.append(")");
        }

    }
    private static boolean check(int x, int y, int size){
        int base = map[y][x];
        for(int i = y ; i < y + size ; ++i){
            for (int j = x; j < x + size; j++) {
                if(base != map[i][j]) return false;
            }
        }
        return true;
    }
}
