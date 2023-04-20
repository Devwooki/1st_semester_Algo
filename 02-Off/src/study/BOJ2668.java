package study;
import java.io.*;
import java.util.*;

public class BOJ2668 {
    static int[] graph;
    static boolean[] visited;
    static int N, max = Integer.MIN_VALUE;
    static PriorityQueue<Integer> pq;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        //인접행렬 초기화
        graph = new int[N+1];

        for(int i = 1 ; i <= N ; ++i){
            graph[i] =Integer.parseInt(br.readLine());
        }

        //bfs를 돌면서 가장 큰 사이클을 발견
        for(int i = 1 ; i <= N ; ++i){
            visited = new boolean[N+1];
            dfs1(i, 0);
            dfs2(i,i);
        }
    }
    //가설 1.
    //사이클이 가장 큰 것을 찾고, 자기 자신을 가리키는 것 cnt
    private static void dfs1(int now, int cnt) {
        if(!visited[graph[now]]) {
            visited[now] = true;
            dfs1(graph[now], cnt+1);
        }
    }

    //가설 2.
    //사이클의 갯수 파악 - 사이클이 발생하는 번호를 pq에 넣기
    private static void dfs2(int now, int cnt) {
        if(!visited[graph[now]]) {
            visited[now] = true;
            dfs2(graph[now], cnt+1);
        }
    }

}