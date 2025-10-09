package JobChange;

import java.io.*;
import java.util.*;

public class BOJ1012 {
    static boolean[][] map;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(T-->0){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            map = new boolean[N][M];
            visited= new boolean[N][M];

            for(int i = 0 ; i < K ; ++i){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                map[y][x] = true;
            }

            int cnt = 0;
            for(int i = 0 ; i < N ; ++i){
                for(int j = 0 ;  j< M ;++j){
                    if(map[i][j] && !visited[i][j]){
                        bfs(j,i, M, N);
                        cnt++;
                    }
                }
            }

            sb.append(cnt).append("\n");
        }

        System.out.println(sb.toString());
    }

    private static void bfs(int x, int y, int M, int N){
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        Queue<int[]> q = new ArrayDeque<>();

        q.offer(new int[]{x, y});

        while(!q.isEmpty()){
            int[] cur = q.poll();
            visited[cur[1]][cur[0]] = true;

            for(int i = 0 ; i < 4; ++i){
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                //유효값 검증
                if(!isRange(nx, ny, M, N)) continue;
                if(!map[ny][nx]) continue;
                if(visited[ny][nx]) continue;

                visited[ny][nx] = true;
                q.offer(new int[]{nx, ny});
            }
        }
    }

    private static boolean isRange(int x, int y, int M, int N){
        return ( 0 <= x && x < M) && ( 0 <= y && y < N);
    }
}
