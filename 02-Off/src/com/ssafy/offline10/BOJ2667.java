package com.ssafy.offline10;
import java.awt.*;
import java.io.*;
import java.util.*;
public class BOJ2667 {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static ArrayList<Integer> result;
    static int[] dx = {0,1,0,-1};
    static int[] dy = {-1,0,1,0};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        visited = new boolean[N][N];
        result = new ArrayList<>();
        for(int i = 0 ; i < N ; ++i){
            String str = br.readLine();
            for(int j = 0 ; j < N ; ++j){
                map[i][j] = str.charAt(j) - '0';
            }
        }

        for(int i = 0 ; i < N ; ++i){
            for(int j = 0 ; j < N ; ++j){
                if(map[i][j] == 1 && !visited[i][j]){
                    //bfs는 이렇게
                    //bfs(j,i);

                    //dfs는 이걸로
                    int data = dfs(j, i ,1);
                    result.add(data);
                }
            }
        }

        Collections.sort(result);

        sb.append(result.size() + "\n");
        for(int i = 0 ; i < result.size() ; ++i){
            sb.append(result.get(i) + "\n");
        }
        System.out.println(sb.toString());
    }
    static void bfs(int x, int y){
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(x, y));
        int cnt = 1;
        visited[y][x] = true;
        while(!q.isEmpty()){
            Point p = q.poll();

            for(int i = 0 ; i < 4 ; ++i){
                int nextX = p.x + dx[i];
                int nextY = p.y + dy[i];
                if( 0 <= nextX && nextX < N && 0 <= nextY && nextY < N && map[nextY][nextX] == 1){
                    if(visited[nextY][nextX]) continue;
                    cnt++;
                    visited[nextY][nextX] = true;
                    q.offer(new Point(nextX, nextY));
                }
            }
        }

        result.add(cnt);
    }

    static int dfs(int x, int y, int cnt) {
        if (visited[y][x]) return cnt;

        visited[y][x] = true;
        int now = cnt;
        for (int i = 0; i < 4; ++i) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];
            if (0 <= nextX && nextX < N && 0 <= nextY && nextY < N && map[nextY][nextX] == 1) {
                if (visited[nextY][nextX]) continue;
                now = dfs(nextX, nextY, now+1);
            }
        }
        return now;
    }
}

