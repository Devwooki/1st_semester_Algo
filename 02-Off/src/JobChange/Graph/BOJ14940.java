package JobChange.Graph;

import java.io.*;
import java.util.*;
import java.awt.Point;
public class BOJ14940 {
    static int N, M;
    static int[][] map;
    static int[][] dist;
    static Point goal;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i = 0 ; i < N ; ++i){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < M ; ++j){
                int t = Integer.parseInt(st.nextToken());
                if(t == 2 ) goal = new Point(j, i);

                //못가는길은 오늘부터 -9 지정
                else if( t == 0 ) map[i][j] = -9;

            }
        }

        bfs(goal.x, goal.y);

        for(int i = 0 ; i < N ; ++i){
            for(int j = 0 ; j < M ; ++j){
                if(j == goal.x && i == goal.y){
                    sb.append("0 ");
                    continue;
                }

                //갈 수 있는 땅인데 도달 못함 -1
                if(map[i][j] == 0) map[i][j] = -1;
                //갈수 없는 땅 -9를 0으로 롤백
                if(map[i][j] == -9) map[i][j] = 0;



                sb.append(map[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void bfs(int x, int y){
        Queue<Point> q = new ArrayDeque<>();
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        q.offer(new Point(x, y));
        while(!q.isEmpty()){
            Point cur = q.poll();

            for(int i = 0 ; i < 4 ; ++i){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                //유효성 검사
                if(!checkRange(nx, ny)) continue;
                if(ny == goal.y && nx == goal.x) continue;
                if(map[ny][nx] != 0) continue; //0이 아닌 값은 이미 방문한 곳 -> 거름

                map[ny][nx] = map[cur.y][cur.x] + 1;
                q.offer(new Point(nx, ny));
            }
        }
    }

    private static boolean checkRange(int x, int y){
        return (0 <= x && x < M && 0 <= y && y < N);
    }
}
