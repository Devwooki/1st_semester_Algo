package com.ssafy.offline20DP;
import java.util.*;
import java.io.*;

public class Floyd {
    static int INF = Integer.MAX_VALUE;
    static int N, distance[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        distance = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                distance[i][j] = Integer.parseInt(st.nextToken());

                //자기 자신은 0으로 하며
                if(i == j) distance[i][j] = 0;
                //자기 자신이 아닌 인접행렬인데 연결되지 않았을 때 INF
                if(i != j && distance[i][j] == 0) distance[i][j] = INF;
            }
        }

        System.out.println("========입력완료=======");

        //플로이드 워셜은 n^3으로 처리된다. 코드는 단순하지만 시간복잡도에서 디매리트
        //다익스트라를 n번 처리하는게 더 빠르단 얘기도 있다.

        //플로이드 워셜은 경출도~ 경유지, 출발지, 도착지 순서대로 for문을 실행한다.
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                if(i == k) continue; //출발지와 경유지가 같은 경우 다음 출발지로 지정
                for (int j = 0; j < N; j++) {
                    if(i == j || k == j) continue;  //경유지와 목적지가 같거나
                                                    //출발지==목적지면 스킵
                    if(distance[i][j] > distance[i][k] + distance[k][j])
                        distance[i][j] = distance[i][k] + distance[k][j];
                }
            }

        }

    }
    private static void print(){
        for(int i = 0 ; i < N ; ++i){
            for(int j = 0 ; j < N ; ++j){
                System.out.print(distance[i][j] + " ");
            }
            System.out.println();
        }
    }
}
