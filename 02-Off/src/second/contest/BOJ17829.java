package second.contest;

import java.io.*;
import java.util.*;

public class BOJ17829 {
    static int N,halfSize;
    static int[][] map;
    static int[][] resultMap;
    static List<Integer> result;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        resultMap = new int[N/2][N/2];
        for(int i = 0 ; i < N ; ++i){
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(N != 1){
            polling(N, 0,0);
            N /= 2;

        }

        System.out.println(map[0][0]);
    }
    //2번째로 큰 수를 기준으로 새로운 부분 집합 만들기
    static void polling(int size, int x, int y){
        if(size == 2){
            map[y/2][x/2] = getSecond(x, y);
        }else{
            int nextSize = size/2;
            for(int i = 0 ; i < 2 ; ++i){
                for (int j = 0; j < 2; ++j) {
                    polling(nextSize, x + nextSize * j , y + nextSize * i);
                }
            }
        }
    }

    static int getSecond(int x, int y){
        ArrayList<Integer> arrList = new ArrayList<>();
        for(int i = y ; i < y+2 ; ++i){
            for (int j = x;  j < x+2; ++j) {
                arrList.add(map[i][j]);
            }
        }
        Collections.sort(arrList);

        //두 번째로 가장 큰 값
        return arrList.get(2);
    }

}
