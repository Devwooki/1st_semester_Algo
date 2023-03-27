package second.contest;

import java.io.*;
import java.util.*;
/*
로직
1. x,y에서 시작함
2. 주사위 인덱스 2번 : 위, 인덱스 4번 : 바닥
3. 작업 순서
 - 명령에 따라 주사위 회전
 - 범위 체크 -> 범위 밖이면 출력도, 회전도 하지 않음
    . 주사위를 굴렸을 때,
    이동한 칸에 쓰여 있는 수가 0이면, 주사위의 바닥면에 쓰여 있는 수가 칸에 복사된다.
    0이 아닌 경우에는 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사되며, 칸에 쓰여 있는 수는 0이 된다.
 - 주사위 배열 회전 후 if(dice[4] == 0) dice[4]= map[x][y];
 */

public class BOJ14499 {
    static int[][] map;
    static int[] dice = new int[7]; //주사위의 바닥4, 주사위의 위쪽2
    static int[][] dir = {{}, {1,0},{-1,0},{0,-1},{0,1}};//동, 서, 북, 남
    static int N, M, x, y, cmdCnt;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        cmdCnt = Integer.parseInt(st.nextToken());

        //맵 초기화
        map = new int[N][M];
        for(int i = 0 ; i < N ; ++i){
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        //명령에 따른 연산 수행
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < cmdCnt ; ++i){
            int cmd = Integer.parseInt(st.nextToken());
            simulation(cmd);
        }

        System.out.println(sb);
    }

    private static void simulation(int cmd) {
        //명령에 따른 다음 위치 구함
        int nx = x + dir[cmd][0];
        int ny = y + dir[cmd][1];
        if(checkRange(nx,ny)){ //범위 유효하면
            diceRotate(cmd);//주사위 굴림

            //map == 0 -> map[ny][nx] = dice[4;]
            //map!= 0 -> dice[4] = map[ny][nx]
            if(map[ny][nx] == 0) map[ny][nx] = dice[4];
            else {
                dice[4] = map[ny][nx];
                map[ny][nx] = 0;
            }
            sb.append(dice[2] + "\n");//주사위 위쪽 값 출력

            //주사위 위치갱신
            x = nx;
            y = ny;
        }else return;

    }

    //범위를 벗어나면 무시, 출력도 안함
    static boolean checkRange(int nx, int ny){
        return 0 <= nx && nx < M && 0 <= ny && ny < N;
    }

    static void diceRotate(int cmd){
        /*
         * 1 동쪽 4->5->2->6->4
         * 2 서쪽 4->6->2->5->4
         *
         * 3 위 4->3->2->1->4
         * 4 아래 1->2->3->4->1
         */
        int temp;
        switch(cmd){
            case 1 :
                temp = dice[4];
                dice[4] = dice[6];
                dice[6] = dice[2];
                dice[2] = dice[5];
                dice[5] = temp;
                break;
            case 2 :
                temp = dice[4];
                dice[4] = dice[5];
                dice[5] = dice[2];
                dice[2] = dice[6];
                dice[6] = temp;
                break;
            case 3 :
                temp = dice[4];
                dice[4] = dice[1];
                dice[1] = dice[2];
                dice[2] = dice[3];
                dice[3] = temp;
                break;
            case 4 :
                temp = dice[1];
                dice[1] = dice[4];
                dice[4] = dice[3];
                dice[3] = dice[2];
                dice[2] = temp;
                break;
        }
    }
}
