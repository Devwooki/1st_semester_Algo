package second.contest;
import java.util.*;
import java.io.*;
public class SWEA5643_BFS {
    static int N, M, cnt = 0;
    static int[][] map;
    static boolean[] visited;
    static int[] taller, smaller;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());
        for(int tc = 1 ; tc <= TC ; ++tc){
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());

            map = new int[N][N];

            for(int i = 0 ; i < M ; ++i){
                st = new StringTokenizer(br.readLine());
                map[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = 1;
            }
            visited = new boolean[N+1];

            int ans = 0;
            for(int i = 0; i < N ; ++i){
                cnt = 0;
                tallerDfs(i, new boolean[N]);
                lowerDfs(i, new boolean[N]);
                if(cnt == N-1) ans++;
            }

            sb.append("#"+tc + " "+ ans + "\n");
        }
        System.out.println(sb);
    }
    static void tallerDfs(int node, boolean[] visited){ //자기보다 작은 정점

        visited[node] = true;
        for(int i = 0 ; i < N ; ++i){
            if(!visited[i] && map[node][i] == 1){
                cnt++;
                tallerDfs(i, visited);
            }
        }
    }
    static void lowerDfs(int node, boolean[] visited){ //자기보다 작은 정점

        visited[node] = true;
        for(int i = 0 ; i < N ; ++i){
            if(!visited[i] && map[i][node] == 1){
                cnt++;
                lowerDfs(i, visited);
            }
        }
    }
}

/*
해결 할 수 있는 방법
완전탐색 - BFS, DFS
위상정렬
 */
