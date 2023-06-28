package study;

import java.io.*;
import java.util.*;

/*
한 칸 전진
-> 앞칸에
*/
public class BOJ20057 {
    static int[][] dirInfo = {{-1, 0}, {0, 1}, {1, 0}, {0, 1}};
    static int N, sum;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++)
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        simulataion();

        System.out.println(sum);
    }

    private static void simulataion() {
        //첫 시작 위치
        Loc now = new Loc(N / 2, N / 2);



        int dir = 0;  //현재 방향을 타나낸다
        int dirMove = 1; //현재 방향으로 나아갈 횟수

        while (true) {
            for (int dirChangeCnt = 0; dirChangeCnt < 2; ++dirChangeCnt) {
                for (int i = 0; i < dirMove; ++i) {

                    //토네이도가 움직인다
                    now.x += dirInfo[dir][0];
                    now.y += dirInfo[dir][1];

                    //토네이도는 1,1에 도착하면 소멸한다.
                    if (now.x == 0 && now.y == 0) return;

                    //아닌 경우 모레를 퍼트린다.
                    spreadSand(now);
                }
                dir = (dir+1)%4;
            }
        }
    }

    private static void spreadSand(Loc now) {


    }

    private static boolean checkRange(Loc now){
        return ( 0 <= now.x && now.x < N && 0 <= now.y && now.y < N);
    }
}

class Loc {
    int x;
    int y;

    public Loc(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
