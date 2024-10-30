package second.contest;
import java.awt.*;
import java.io.*;
import java.util.*;

public class BOJ1743음식물피하기 {
    static int N, M, K;
    static boolean[][] map;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        //통로에 떨어진 가장 큰 음식물만 피해단다.
        N = Integer.parseInt(st.nextToken()); //세로 길이
        M = Integer.parseInt(st.nextToken()); //가로 길이
        K = Integer.parseInt(st.nextToken()); //쓰레기 개수;

        map = new boolean[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            map[r][c] = true;
        }

        findMaxSizeFoodJunk();
    }

    private static void findMaxSizeFoodJunk() {
        int maxJunkSize = Integer.MIN_VALUE;

        //map을 탐색하며 쓰레기이며 방문하지 않았으면 쓰레기크기 측정 시작
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] && !visited[i][j])
                    maxJunkSize = Math.max(bfs(j, i), maxJunkSize);
            }
        }

        System.out.println(maxJunkSize);
    }

    private static int bfs(int x, int y) {
        int[][] dir = {{1,0}, {0, -1}, {-1,0}, {0,1}};//시계방향
        int junkSize = 0;

        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(x, y));

        visited[y][x] = true;

        while (!q.isEmpty()) {
            Point cur = q.poll();
            //visited[cur.y][cur.x] = true;
            junkSize++;

            for(int i = 0 ; i < 4 ; ++i){
                int nX = cur.x + dir[i][0];
                int nY = cur.y + dir[i][1];

                //유효값 체크
                if(!checkRange(nX, nY)) continue; //범위
                if(visited[nY][nX]) continue;     //방문여부
                if(!map[nY][nX]) continue;        //음식물쓰레기 아님


                q.offer(new Point(nX, nY));
                visited[nY][nX] = true;
            }
        }

        return junkSize;
    }

    private static boolean checkRange(int x, int y){
        return ( 0 <= x && x < M && 0 <= y && y < N);
    }
}
