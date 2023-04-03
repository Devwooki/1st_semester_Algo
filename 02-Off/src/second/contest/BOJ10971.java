package second.contest;
import java.util.*;
import java.io.*;

public class BOJ10971 {
    static class MyV{
        int end;
        int cost;

        public MyV(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
    }
    static int N, res = Integer.MAX_VALUE;
    static boolean[] visited;
    static int[][] map;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N];
        for(int i = 0 ; i < N ; ++i){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < N ; ++j){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0 ; i < N ; ++i){
            visited[i] = true;
            dfs(0,i,i,0);
            visited[i] = false;
        }

        System.out.println(res);
    }

    static void dfs(int cnt, int start, int now, int cost){
        //순회를 마쳤을 때 처리
        if(cnt == N-1){
            if(map[now][start]!=0){
                cost += map[now][start];
                res = Math.min(res, cost);
            }
            return;
        }

        for(int i = 0 ; i < N ; ++i){
            if(!visited[i] && map[now][i] !=0){
                visited[i] = true;
                dfs(cnt+1, start, i, cost+map[now][i]);
                visited[i] = false;
            }
        }
    }
}
