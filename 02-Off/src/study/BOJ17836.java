package study;

import java.awt.*;
import java.io.*;
import java.util.*;

class Move{
    int x;
    int y;
    int cnt;
    public Move(int x, int y, int cnt) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }
}
public class BOJ17836 {
    static int N, M, T;
    static int[][] map;
    static boolean[][] visited;

    static int[][] dir = {{1, 0},{0, 1},{-1, 0},{0, -1}};
    static Point gram;
    static boolean getGram = false;
    static int answer = Integer.MAX_VALUE;

    //경우의 수 2개
    //1. 공주한테 바로 가는 경우
    //2. 그람을 먹고 공주에게까지 직진하는 방법
    //공주에게 도착해도 cnt가 0d
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map =  new int[N][M];
        visited = new boolean[N][M];

        for(int i = 0 ; i < N ; ++i){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; ++j) {
                map[i][j] =Integer.parseInt(st.nextToken());

                //그람 위치 저장
                if(map[i][j] == 2) {
                    map[i][j] = 0;
                    gram = new Point(j, i);
                }
                if(map[i][j] == 1) map[i][j] = -1;
            }
        }

        // 3 <= N, M <= 100
        // 1 <= T <= 10000


        //dfs(0,0, 0); //최단경로를 구할 떄는 BFS를 활용하는게 성능면에서 뛰어나다..
        bfs(0,0);
        //탐색 끝난 후 공주의 값을 answer에 저장한다.
        answer = map[N-1][M-1];

        if(map[gram.y][gram.x] != 0){ //그람이 있는 곳이 0 아니면 얻을 수 있다..
            int useGram = map[gram.y][gram.x] + Math.abs(N-1 -gram.y) + Math.abs(M-1 - gram.x);


            answer = answer != 0 ? Math.min(useGram, answer) : useGram; //최솟값 answer에 저장
        }

        if(answer == 0 || answer > T) System.out.println("Fail");
        else System.out.println(answer);
//        System.out.println(answer <= T ? answer : "Fail");
    }

    private static void bfs(int x, int y) {
        Queue<Move> q = new LinkedList<>();
        q.offer(new Move(x, y, 0));

        while(!q.isEmpty()){
            Move now = q.poll();

            map[y][x] = -1;
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dir[i][0];
                int ny = now.y + dir[i][1];

                if(!checkRange(nx, ny)) continue; //좌표 유효성 체크
                if(map[ny][nx] == -1) continue; //벽 체크


                if(map[ny][nx] == 0){
                    //해당 경로로 갈 수 있으면 탐색 수행
                    //map[ny][nx] = map[ny][nx] == 0 ? now.cnt + 1 : Math.min(map[ny][nx], now.cnt+1);
                    map[ny][nx] = now.cnt+1;
                    q.offer(new Move(nx, ny, now.cnt+1));
                }
            }
        }
    }

    private static void dfs(int x, int y, int cnt) {
        if(x == M-1 && y == N-1){ //기저조건
            //T 이내에 공주에게 돋
            answer = Math.min(cnt, answer);
            return;
        }

        if(x == gram.x && y == gram.y) getGram = true;

        visited[y][x] = true;
        for (int i = 0; i < 4; i++) {
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];

            if(!checkRange(nx, ny)) continue; //좌표 유효성 체크
            if(map[ny][nx] == -1) continue; //벽 체크
            if(visited[ny][nx]) continue; //재방문 여부 체크

            //해당 경로로 갈 수 있으면 탐색 수행
            visited[ny][nx] = true;
            map[ny][nx] = map[ny][nx] == 0 ? cnt+1 : Math.min(map[ny][nx], cnt+1);
            dfs(nx, ny, cnt+1);
            visited[ny][nx] = false;
        }
    }

    private static boolean checkRange(int x, int y){
        return ( 0<= x && x < M && 0 <= y && y < N);
    }
}
