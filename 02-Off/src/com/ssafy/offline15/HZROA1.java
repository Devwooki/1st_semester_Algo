package com.ssafy.offline15;

import java.io.*;
import java.util.*;

public class HZROA1 {
    static int[][] dir = {
            {0, -1}, {0, 1}, {1, 0}, {-1, 0}}; //상하 좌우 순서대로 탐색
    static int[][] map;
    static boolean[][] visited;
    static int N, M, len = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        visited = new boolean[M][N];
        if(N % 2 == 0 || M % 2 == 0) System.out.println(N * M);
        else System.out.println(N * M - 1);
//        dfs(0, 0, 1);
//        System.out.println(len);

    }

    static void dfs(int x, int y, int cnt) {

        if (x == 1 && y == 0) {
            len = Math.max(len, cnt);
        }

        for (int i = 0; i < 4; ++i) {
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];
            if (0 <= nx && nx < N && 0 <= ny && ny < M && map[ny][nx] == 0 && !visited[ny][nx]) {
                visited[ny][nx] = true;
                dfs(nx, ny, cnt + 1);
                visited[ny][nx] = false;
            }
        }
    }

//    static boolean checkRange(int x, int y) {
//        return (0 <= x && x < N && 0 <= y && y < M && map[y][x] == 0 && !visited[y][x]);
//    }
}
