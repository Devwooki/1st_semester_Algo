package study;

import java.io.*;
import java.util.*;

//봄버맨 역할
// 폭탄 설치
// 1초 대기
// 미설치 구역에 폭탄 설치
// 폭탄 펑, 터질 때 인접한 폭탄은 파괴됨. 연쇄반응은 없다.

//0초 입력(초기상태)
//1초 전체 폭탄

//2초 없는 위치에 폭탄 설치
//3초 0초에 심은 폰단 폭발 + 인접폭팔

//4초 없는 위치에 폭탄 설치
//5초 2초에 심은 폭탄 펑

//6초 폭탄이 없는 위치에 폭탄 설차
//7초 4초에 심은 폭탄 터짐

//폭탄이 터질 떄 인접한 폭탄은 같이 터진다.
public class BOJ16918 {
    static int R, C, N;
    static char[][] map;
    static int[][] checkBomb;
    static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        //입력값 처리
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken()); //N초 후의 결과를 출력하자
        //N == 3 -> 3초후 밥을 먹으니
        map = new char[R][C];
        checkBomb = new int[R][C];

        //map 처리
        for (int i = 0; i < R; ++i) {
            String str = br.readLine();
            for (int j = 0; j < C; ++j) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'O') checkBomb[i][j] = 3; // 3초 뒤에 터지니까.
            }
        }

        int time = 2;//봄버맨은 1초의 대기시간을 가지므로 폭탄을 배치하는 2초부터 움직임
        while (true) {
            if (N + 1 == time) break;

            if (time % 2 == 0) setBomb(time);
            else deleteBomb(time);

            time++;
        }


        //결과 출력한다.
        for (int i = 0; i < R; ++i) {
            for (int j = 0; j < C; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void deleteBomb(int time) {
        for (int i = 0; i < R; ++i) {
            for (int j = 0; j < C; j++) {
                if (checkBomb[i][j] == time) {//setBomb나 초기화 할 때 당시 시간 +3을 해줬으므로
                    //상하좌우 폭파
                    boom(j, i);
                }
            }
        }
    }

    private static void setBomb(int time) {//폭탄을 설치한다
        for (int i = 0; i < R; ++i) {
            for (int j = 0; j < C; j++) {
                //현재시간 + 3초뒤에 터지므로 폭탄을 셋팅한다.
                if (map[i][j] != 'O') {
                    map[i][j] = 'O';
                    checkBomb[i][j] = time + 3;
                }
            }
        }

    }

    private static void boom(int x, int y) {
        //폭탄 터짐
        map[y][x] = '.';
        for (int i = 0; i < 4; ++i) {
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];

            if (!checkRange(nx, ny)) continue;
            if (map[ny][nx] == '.') continue;

            map[ny][nx] = '.';
        }
        //폭탄 시간 초기화
        checkBomb[y][x]= 0;
    }

    private static boolean checkRange(int x, int y) {
        return (0 <= x && x < C && 0 <= y && y < R);
    }
}
