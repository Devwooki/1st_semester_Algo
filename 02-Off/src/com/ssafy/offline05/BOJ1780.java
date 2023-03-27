package com.ssafy.offline05;

import java.io.*;
import java.util.*;
public class BOJ1780 {
    static int[][] map;
    static int[] result;
    static int N;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        result = new int[3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        cntPaper(0,0,N);

        System.out.println(result[0] + "\n"
                        + result[1] + "\n"
                        + result[2] + "\n");
    }
    static boolean checkMap(int x, int y, int n){
        //시작 위치값과 나머지 비교
        int now = map[y][x];
        for(int i = y ; i < y + n ; ++i){
            for(int j = x ; j < x + n ; ++j){
                if(now != map[i][j]) //시작 위치랑 다름
                    return false;
            }
        }

        //탐색이 정상 종료되면 모두 같은것임
        return true;
    }

    static void cntPaper(int x, int y, int n) {

        /*
        처음 영역 탐색 -> 같으면 upperNum해서 결과 증가
                    -> 다르면 영역분할
         */
        if(checkMap(x,y,n)){
            upperNum(map[y][x]);
        }
        else {
            for (int i = 0; i < 3; ++i) {
                for (int j = 0; j < 3; ++j) {
                    //영역 9개로 분할
                    int tempY = y + i * n / 3;
                    int tempX = x + j * n / 3;
                    cntPaper(tempX, tempY, n / 3);

                }
            }
        }
    }
    static void upperNum(int a){
        switch(a){
            case -1 : result[0] += 1; break;
            case 0 : result[1] += 1; break;
            case 1 : result[2] += 1; break;
        }
    }
}
