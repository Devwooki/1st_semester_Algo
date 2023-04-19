package study;
import java.io.*;
import java.util.*;

public class BOJ2668 {
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static int N, max = Integer.MIN_VALUE;
    static PriorityQueue<Integer> pq;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        //인접행렬 초기화
        graph = new ArrayList[N+1];
        for(int i = 0 ; i <= N ; ++i){
            graph[i] = new ArrayList<>();
        }


        for(int i = 1 ; i <= N ; ++i){
            graph[i].add(Integer.parseInt(br.readLine()));
        }

        //bfs를 돌면서 가장 큰 사이클을 발견
        for(int i = 1 ; i <= N ; ++i){
            visited = new boolean[N+1];
            int cycle= dfs(i, 0);

            if(cycle != -1){

            }
        }
    }

    private static int dfs(int now, int cnt) {

        visited[now] = true;
        for(int next : graph[now]){
            if(visited[next]) return cnt;
            else{
                dfs(next, cnt+1);
            }
        }
    }

}

/*
가설 1.
사이클이 가장 큰 것을 찾고, 자기 자신을 가리키는 것 cnt
*/