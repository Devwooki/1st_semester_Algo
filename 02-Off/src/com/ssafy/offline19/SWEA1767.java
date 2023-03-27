package com.ssafy.offline19;
import java.awt.*;
import java.io.*;
import java.util.*;

/**
 *  가장자리에 전류 흐름
 *  1개의 cell에는 프로세서 | 전선이 놓임
 *  core와 전선은 직선으로만 설치
 *      전선은 교차 불가능
 *  가장자리에 위치한 core는 전원이 연결된 것
 *  최대한 많은 core에 전원 연결 - 최대한 많이 연결해도 연결되지 않는 core가 존재할 수 있음, 전선 길이의 합구하기(최소가 되는 경우)
 *
 */
public class SWEA1767 {

    static int N, size, result = Integer.MAX_VALUE, maxCoreCnt = size;
    static int[][] map, dir = { {1, 0}, {0,1}, {-1, 0}, {0, -1}}; //상 좌 하 우
    static Point[] cores;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= TC; tc++) {

            N = Integer.parseInt(br.readLine());
            size = 0;
            result = Integer.MAX_VALUE;
            map = new int[N][N];
            cores = new Point[12];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j] == 1) {
                        if(i == 0 || i == N-1 || j == 0 || j == N-1) continue; //core가 가장자리에 있는 경우는 고려하지 않음
                        cores[size++] = new Point(j,i);
                    }
                }
            }
            drawLine(0,0,0);
            sb.append("#" + tc + " " + result+ "\n");
        }
        System.out.println(sb);
    }

    //전선마다 4방향으로 확장하면서 벽을 만나면 전선 갯수와 최대 코어수 증가 -> 다음 코어 4방향 확장
    //모든 코어 순호 완료하면 최대코어 갯수 비교, 최대 코어라면 길이 갱신, 최대코어 아니면 길이 비교 후 갱신
    //dfs종료 시 최소전선길이 출력
    //  idx : cores 객체 접근 인덱스
    //  coreCnt : 코어의 수를 센다
    //  길이

    /*
        다음위치 구함. 다음위치 범위체크
        -> 유효함
            -> map != 0 : 전선이나 프로세서가 놓여있으니 해당 방향에는 놓을 수 없음. 시작점까지 돌아가며 0으로 만들기
            -> map == 0 : 전선 깔고 cnt증가, 다음 탐색 시작
        -> 유효하지 않음 : nx - dir, ny - dir이 끝에 도달함. 다음 탐색 시작
    * */
    static void drawLine(int idx, int coreCnt, int len){
        if(idx == size){ //dfs 탐색 마침 -> 기저조건 실행
            if(maxCoreCnt < coreCnt){ //코어수가 가장 많을 때 len 갱신
                maxCoreCnt = coreCnt;
                result = len;
            }else if(maxCoreCnt == coreCnt){ //코어 수 같으면 가장 작은 것을 저장
                //길이 최솟값 갱신
                result = Math.min(result, len);
            }
            return;
        }

        for(int i = 0 ; i < 4 ; ++i){
            int baseX = cores[idx].x;
            int baseY = cores[idx].y;
            int cnt = 0; //전선의 수
            int nx = baseX;
            int ny = baseY;

            while(true){
                nx += dir[i][0];
                ny += dir[i][1];
                if(!checkRange(nx,ny)) break; //가장자리에 도달함 -> 다음 core로 넘어가기 위해 break;
                if(map[ny][nx] != 0) { //중간에 전선이나 core가 깔려있으면 연산 초기화
                    for(int j = 0 ; j < cnt ; ++j){
                        nx -= dir[i][0];
                        ny -= dir[i][1];
                        map[ny][nx] = 0;
                    }
                    cnt = 0;
                    break;
                }

                //필터링 되지 않으면 현재 위치에 전선깔기
                map[ny][nx] = 2;
                cnt++;
            }

            if(cnt == 0) drawLine(idx + 1, coreCnt, len);
            else {
                drawLine(idx + 1, coreCnt + 1, len + cnt);

                //다음 for문 시작시 현재 코어에 놓인 전선은 초기화해야함
                for(int j = 0 ; j < cnt ; ++j){
                    nx -= dir[i][0];
                    ny -= dir[i][1];
                    map[ny][nx] = 0;
                }
            }
        }


    }
    static boolean checkRange(int x, int y){
        return (0 <= x && x < N && 0 <= y && y < N);
    }
}


/*
if(idx == size){
            if(maxCore < coreCnt){ //최대코어가 아니면 길이 값을 비교해서 갱신
                maxCore = coreCnt;
                result = len;
            }
            else if(maxCore == coreCnt){//최대 코어의 갯수와 같으면 최솟값 갱신
                result = Math.min(result, len);
            }
            return;
        }

        for (int i = 0; i < 4; i++) {
            int cnt = 0;
            int nx = cores[idx].x;
            int ny = cores[idx].y;
            while(true){
                nx += dir[i][0];
                ny += dir[i][1];
                if(!checkRange(nx, ny)) break;//끝에 도달했으니 break하고 다음 탐색 다시

                if(map[ny][nx] != 0){ //다음 위치에 전선, 코어가 놓여있음. x까지 되돌아가며 2를 0으로 바꿈
                    while(nx != cores[idx].x && ny != cores[idx].y){
                        nx -= dir[i][0];
                        ny -= dir[i][1];
                        map[ny][nx] = 0;
                    }
                    cnt = 0;
                    break;
                }
                cnt++;
                map[ny][nx] = 2;
            }
            //연결할 수 있는 전선이 없다. -> 다음 코어로 탐색 시작
            if(cnt == 0)  drawLine(idx + 1, coreCnt, len);
            else {
                drawLine(idx + 1, coreCnt + 1, len + cnt);

                //전선깔린부분을 초기화한다
                for(int j = 0 ; j < cnt ; ++j){
                    nx -= dir[i][0];
                    ny -= dir[i][1];
                    map[ny][nx] = 0;
                }
            }
        }
 */