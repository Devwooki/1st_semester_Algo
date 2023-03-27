package com.ssafy.offline19;
import java.io.*;
import java.util.*;
public class SWEA1953 {
    static class Point{
        int x;
        int y;
        int cnt;
        public Point(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
    static int[][][] dir = {
            {},
            {{0, 1},{1,0},{0,-1},{-1, 0}},  //1번 파이프 상하좌우
            {{0, 1},{0, -1}},               //2번 파이프 상하
            {{1, 0},{-1, 0}},               //3번 파이프 좌우
            {{0,-1},{1,0}},
            {{1,0}, {0,1}},
            {{0,1}, {-1,0}},
            {{-1,0}, {0,-1}}
    };
    static int N, M, R, C, T, cnt;
    static int[][] map;
    static boolean[][] visited;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int TC = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= TC; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            T = Integer.parseInt(st.nextToken());
            cnt = 0;
            map = new int[N][M];
            for(int i = 0 ; i < N ; ++i){
                map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }
            visited = new boolean[N][M];
            bfs();
            sb.append("#" + tc + " " + cnt + "\n");
        }
        System.out.println(sb.toString());
    }
    /*
    1 : 사방, 2: 상하, 3:좌우 4:하우 5:우상 6:상좌 7:좌하
     */
    static void bfs(){
        Queue<Point> q= new LinkedList<>();
        q.offer(new Point(C, R, 1));
        visited[R][C] = true;

        //파이프가 연결되었는지만 체크하면 클리어
        while(!q.isEmpty()){
            Point cur = q.poll();
            if(cur.cnt > T) break;
            cnt++;
            int pipe = map[cur.y][cur.x];
            for(int i = 0 ; i < dir[pipe].length; ++i){
                int nx = cur.x + dir[pipe][i][0];
                int ny = cur.y + dir[pipe][i][1];

                if(checkRange(nx,ny)){
                    //방문 안한 파이프면 갈 수 있음
                    if(!visited[ny][nx] && map[ny][nx] != 0){
                        if(isConnect(cur.x, cur.y, nx, ny)){
                            q.offer(new Point(nx, ny, cur.cnt+1));
                            visited[ny][nx] = true;
                        }
                    }
                }
            }
        }
    }
    static boolean checkRange(int x, int y){
        return (0 <= x && x < M && 0 <= y && y < N);
    }
    static boolean isConnect(int x, int y, int nx, int ny){
        int pipe = map[ny][nx];
        for(int i = 0 ; i < dir[pipe].length ; ++i){
            int preX = nx + dir[pipe][i][0];
            int preY = ny + dir[pipe][i][1];
            if(preX == x && preY == y) return true;
        }
        return false;
    }
}
