package study;

import java.io.*;
import java.util.*;

public class BOJ14940 {
    static int N, M;
    static int[][] map;
    static int[][] dir = {{1, 0},{0, 1},{-1, 0},{0, -1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        Point destination = null;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int input = Integer.parseInt(st.nextToken());

                if(input==2) destination = new Point(j, i, 0);
                //못가는 길은 오늘부터 -9
                else if(input==0) map[i][j] = -9;
            }
        }

        bfs(destination);
    }

    private static void changeCantMove(Point dest){
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(j == dest.x && i == dest.y) {
                    sb.append("0 ");
                    continue; //목표지점과 같으면 검사 안한다.
                }
                if(map[i][j] == 0) map[i][j] = -1;
                if(map[i][j] == -9) map[i][j] = 0;
                sb.append(map[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
        return;
    }

    private static void bfs(Point dest){
        Queue<Point> q=  new LinkedList<>();
        q.offer(dest);

        while(!q.isEmpty()){
            Point now = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dir[i][0];
                int ny = now.y + dir[i][1];

                if(!checkRange(nx, ny)) continue; //좌표 유효성 검사
                if(nx == dest.x && ny == dest.y) continue; //목표지점과 같으면 검사 안한다.
                if(map[ny][nx] != 0) continue;   //갈 수 없는 길이면 스킵

                map[ny][nx] = now.count+1;
                q.offer(new Point(nx, ny, now.count+1));
            }
        }

        changeCantMove(dest);
    }
    private static boolean checkRange(int x, int y){
        return ( 0 <= x && x < M && 0 <= y && y < N);
    }
}

class Point{
    int x;
    int y;
    int count;

    public Point(int x, int y, int count) {
        this.x = x;
        this.y = y;
        this.count = count;
    }

    public Point(int x, int y) {
<<<<<<< Updated upstream
        this.x = x;
        this.y = y;
=======
>>>>>>> Stashed changes
    }
}
